package services;

import data.model.Diary;
import data.repository.DiaryRepository;
import data.repository.DiaryRepositoryImplement;
import dtos.requests.RegisterRequest;
import exceptions.UsernameAlreadyExistException;

public class DiaryServiceImplement implements DiaryService{
    private DiaryRepository diaryRepository = new DiaryRepositoryImplement();
    @Override
    public void register(RegisterRequest registerRequest) {
        validateUsername(registerRequest);

        Diary diary = new Diary();
        diary.setUsername(registerRequest.getUsername());
        diary.setPassword(registerRequest.getPassword());
        diaryRepository.save(diary);
    }

    @Override
    public Diary login(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        Diary diary = diaryRepository.findByUsername(username);

        if (diary != null && diary.getPassword().equals(password)) {
            return diary;
        }
        return null;
    }

    private void validateUsername(RegisterRequest registerRequest) {
        Diary diary = diaryRepository.findByUsername(registerRequest.getUsername());
        if(diary != null)throw new UsernameAlreadyExistException("Username already exist");
    }

    public long getNoOfCustomers(){
        return diaryRepository.count();
    }
}
