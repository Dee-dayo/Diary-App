package services;

import data.model.Entry;
import data.repository.EntryRepository;
import data.repository.EntryRepositoryImplement;

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
}
