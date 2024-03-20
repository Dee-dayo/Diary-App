package services;

import data.model.Entry;

import java.util.List;

public interface EntryServices {

    void addEntry(Entry entry);
    List<Entry> findEntriesByUsername(String username);
    void deleteEntrybyId(int id);

    Entry getEntrybyId(int id);
}
