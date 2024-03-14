package data.repository;

import data.exceptions.DiaryNotFoundException;
import data.exceptions.InvalidUsernameException;
import data.exceptions.UsernameAlreadyExistException;
import data.model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImplement implements DiaryRepository{
    private List<Diary> diaries = new ArrayList<>();
    private int count;

    @Override
    public Diary save(Diary diary) {
        for (Diary d : diaries) {
            if (d.getUsername().equals(diary.getUsername())) {
                throw new UsernameAlreadyExistException("Username already exist");
            }
        }
        diaries.add(diary);
        count++;
        return diary;
    }

    @Override
    public List<Diary> findAll() {
        return null;
    }

    @Override
    public Diary findByUsername(String username) {
        for (Diary diary : diaries) {
            if (diary.getUsername().equals(username)) {
                return diary;
            }
            break;
        }
        throw new InvalidUsernameException("Username not found");
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void delete(String username) {
        for (Diary diary : diaries) {
            if (diary.getUsername().equals(username)) {
                diaries.remove(diary);
                count--;
            }
            else throw new DiaryNotFoundException("Diary not found");
        }
    }

    @Override
    public void delete(Diary diary) {
        for (Diary d : diaries) {
            if (d.equals(diary)) {
                diaries.remove(d);
                count--;
                break;
            }
            else throw new DiaryNotFoundException("Diary not found");

        }
    }
}
