package services;

import data.model.Diary;
import data.model.Entry;
import data.repository.DiaryRepository;
import data.repository.DiaryRepositoryImplement;
import dtos.requests.EntryRequest;
import dtos.requests.LoginRequest;
import dtos.requests.LogoutRequest;
import dtos.requests.RegisterRequest;
import exceptions.DiaryNotFoundException;
import exceptions.UsernameAlreadyExistException;

public class DiaryServiceImplement implements DiaryServices {
    private DiaryRepository diaryRepository = new DiaryRepositoryImplement();
    private EntryServices entryServices = new EntryServiceImplement();

    @Override
    public void register(RegisterRequest registerRequest) {
        validateUsername(registerRequest);

        Diary diary = new Diary();
        diary.setUsername(registerRequest.getUsername());
        diary.setPassword(registerRequest.getPassword());
        diaryRepository.save(diary);
    }

    private void validateUsername(RegisterRequest registerRequest) {
        Diary diary = diaryRepository.findByUsername(registerRequest.getUsername());
        if(diary != null)throw new UsernameAlreadyExistException("Username already exist");
    }

    public long getNoOfCustomers(){
        return diaryRepository.count();
    }

    @Override
    public void login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Diary diary = diaryRepository.findByUsername(username);
        if (diary.getPassword().equals(password)) {
            diary.setLocked(false);
        }
        diaryRepository.save(diary);
    }

    @Override
    public Diary findDiaryByUsername(String username) {
        Diary diary = diaryRepository.findByUsername(username);
        if (diary == null) {
            throw new DiaryNotFoundException("User not found");
        }
        return diary;
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        String username = logoutRequest.getUsername();
        Diary diary = diaryRepository.findByUsername(username);
        diary.setLocked(true);
        diaryRepository.save(diary);
    }

    @Override
    public void createEntry(EntryRequest entryRequest) {
        Entry entry = new Entry();
        entry.setBody(entryRequest.getBody());
        entry.setTitle(entryRequest.getTitle());
        entry.setAuthor(entryRequest.getAuthor());
        entryServices.addEntry(entry);
    }

}
