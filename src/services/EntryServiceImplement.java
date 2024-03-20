package services;

import data.model.Entry;
import data.repository.EntryRepository;
import data.repository.EntryRepositoryImplement;
import exceptions.EntryNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EntryServiceImplement implements EntryServices{
    private static EntryRepository entryRepository = new EntryRepositoryImplement();

    @Override
    public void addEntry(Entry entry) {
        entryRepository.save(entry);
    }

    @Override
    public List<Entry> findEntriesByUsername(String username) {
        return entryRepository.findByAuthor(username);
    }

    @Override
    public void deleteEntrybyId(int id) {
        entryRepository.delete(id);
    }

    @Override
    public Entry getEntrybyId(int id) {
        Entry entry = entryRepository.findById(id);
        if(entry == null) throw new EntryNotFoundException("Entry not found");

        return entry;
    }

}
