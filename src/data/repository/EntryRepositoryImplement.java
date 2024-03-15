package data.repository;

import data.model.Diary;
import data.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImplement implements EntryRepository{
    private List<Entry> entries = new ArrayList<>();
    private int count = 0;
    @Override
    public Entry save(Entry entry) {
        if (isNew(entry)) {
            addIdTo(entry);
            entries.add(entry);
            return entry;
        }
        else update(entry);
        return null;
    }

    private void update(Entry entry) {
        for (Entry findEntry: entries){
            if (findEntry.getId() == entry.getId()){
                entries.remove(findEntry);
                entries.add(entry);
            }
        }
    }

    private void addIdTo(Entry entry) {
        entry.setId(generateId());
    }

    private int generateId() {
        return ++count;
    }

    private boolean isNew(Entry entry) {
        return entry.getId() == 0;
    }

    @Override
    public List<Entry> findAll() {
        return entries;
    }

    @Override
    public Entry findById(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public Entry findByAuthor(String author) {
        return null;
    }

    @Override
    public long count() {
        return entries.size();
    }

    @Override
    public void delete(int id) {
        Entry entry = findById(id);
        entries.remove(entry);
    }

    @Override
    public void delete(Entry entry) {
        for (Entry d : entries) {
            if (d.equals(entry)) {
                entries.remove(d);
                break;
            }
        }
    }
}
