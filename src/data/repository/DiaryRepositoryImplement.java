package data.repository;

import data.model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImplement implements DiaryRepository{
    private List<Diary> diaries = new ArrayList<>();

    @Override
    public Diary save(Diary diary) {
        diaries.add(diary);
        return diary;
    }

    @Override
    public List<Diary> findAll() {
        return diaries;
    }

    @Override
    public Diary findByUsername(String username) {
        for (Diary diary : diaries) {
            if (diary.getUsername().equals(username)) {
                return diary;
            }
            break;
        }
        return null;
    }

    @Override
    public long count() {
        return diaries.size();
    }

    @Override
    public void delete(String username) {
        Diary diary = findByUsername(username);
        if (diary != null) {
            diaries.remove(diary);
        }
    }

    @Override
    public void delete(Diary diary) {
//        for (Diary d : diaries) {
//            if (d.equals(diary)) {
//                diaries.remove(d);
//                count--;
//                break;
//            }
//        }
        diaries.remove(diary);
    }

}
