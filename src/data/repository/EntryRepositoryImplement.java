package data.repository;

import data.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImplement implements EntryRepository{
    private static List<Entry> entries = new ArrayList<>();
    private int countEntry;
    @Override
    public Entry save(Entry entry) {
        if (isNew(entry)) addIdTo(entry);
        else update(entry);

        entries.add(entry);
        return entry;
    }

    private void update(Entry entry) {
        Entry oldEntry = findById(entry.getId());
        entries.remove(oldEntry);
    }

    private void addIdTo(Entry entry) {
        entry.setId(generateId());
    }

    private int generateId() {
        return ++countEntry;
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
    public List<Entry> findByAuthor(String author) {
        List<Entry> found = new ArrayList<>();
        for (Entry entry: entries){
            if (entry.getAuthor().equalsIgnoreCase(author)){
                found.add(entry);
            }
        }
        return found;
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
//        for (Entry d : entries) {
//            if (d.equals(entry)) {
//                entries.remove(d);
//                break;
//            }
//        }
        entries.remove(entry);
    }

    @Override
    public void deleteAll() {
        entries.clear();
    }
}
