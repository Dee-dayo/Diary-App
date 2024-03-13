package data.repository;

import data.model.Diary;

import java.util.List;

public class DiaryRepositoryImplement implements DiaryRepository{
    private int count;
    @Override
    public Diary save(Diary diary) {
        count++;`
        return null;
    }

    @Override
    public List<Diary> findAll() {
        return null;
    }

    @Override
    public Diary findByUsername(String username) {
        return null;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void delete(String username) {

    }

    @Override
    public void delete(Diary diary) {

    }
}
