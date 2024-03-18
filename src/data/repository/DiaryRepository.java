package data.repository;

import data.model.Diary;

import java.util.List;

public interface DiaryRepository {
    Diary save(Diary diary);
    List<Diary> findAll();
    Diary findByUsername(String username);
    long count();
    void delete(String username);
    void delete(Diary diary);
}
