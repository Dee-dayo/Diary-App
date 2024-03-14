package data.repository;

import data.model.Entry;

import java.util.List;

public class EntryRepositoryImplement implements EntryRepository{
    @Override
    public Entry save(Entry entry) {
        return null;
    }

    @Override
    public List<Entry> findAll() {
        return null;
    }

    @Override
    public Entry findById(int id) {
        return null;
    }

    @Override
    public Entry findByAuthor(String author) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(Entry entry) {

    }
}
