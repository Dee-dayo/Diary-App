package data.repository;

import data.model.Diary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiaryRepositoryImplement implements DiaryRepository{
    private final List<Diary> diaries = new ArrayList<>();
    private int countDiary;

    @Override
    public Diary save(Diary diary) {
        if (isNew(diary)) addIdTo(diary);
        else update(diary);
        
        diaries.add(diary);
        return diary;
    }

    private boolean isNew(Diary diary) {
        return diary.getId() == 0;
    }

    private void addIdTo(Diary diary) {
        diary.setId(generateId());
    }

    private int generateId() {
        return ++countDiary;
    }

    private void update(Diary diary) {
        diaries.removeIf(d -> Objects.equals(d.getUsername(), diary.getUsername()));
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
