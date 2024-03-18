package services;

import data.model.Entry;

import java.util.ArrayList;
import java.util.List;

public interface EntryServices {

    void addEntry(Entry entry);

    List<Entry> findEntriesByUsername(String username);
}
