package dataRepository;

import data.model.Entry;
import data.repository.EntryRepository;
import data.repository.EntryRepositoryImplement;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryRepositoryTest {
    @Test
    public void testCreateEntry_countIsOne(){
        EntryRepository repository = new EntryRepositoryImplement();
        Entry entry = new Entry(1, "Title", "Body");
        repository.save(entry);
        assertEquals(1, repository.count());
    }

    @Test
    public void testCreateTwoEntry_countIsTwo(){
        EntryRepository repository = new EntryRepositoryImplement();
        Entry entry = new Entry(1, "Title", "Body");
        repository.save(entry);
        repository.save(new Entry(2, "Title2", "Body2"));
        assertEquals(2, repository.count());
    }

    @Test
    public void testCreateTwoEntry_deleteOne_countIsOne(){
        EntryRepository repository = new EntryRepositoryImplement();
        Entry entry = new Entry(1, "Title", "Body");
        repository.save(entry);
        repository.save(new Entry(2, "Title2", "Body2"));
        repository.delete(entry);
        assertEquals(1, repository.count());
    }

     @Test
    public void testCreateTwoEntry_deleteOneWithId_countIsOne(){
        EntryRepository repository = new EntryRepositoryImplement();
        Entry entry = new Entry(1, "Title", "Body");
        repository.save(entry);
        repository.save(new Entry(2, "Title2", "Body2"));
        assertEquals(2, repository.count());
        repository.delete(2);
        assertEquals(1, repository.count());
    }

    @Test
    public void testCreateTwoEntry_deleteTwo_countIsZero(){
        EntryRepository repository = new EntryRepositoryImplement();
        Entry entry = new Entry(1, "Title", "Body");
        Entry entry2 = new Entry(2, "Title2", "Body2");
        repository.save(entry);
        repository.save(entry2);
        repository.delete(entry);
        repository.delete(2);
        assertEquals(0, repository.count());
    }

    @Test
    public void testSaveOneDiary_canFindDiaryByUsername(){
         EntryRepository repository = new EntryRepositoryImplement();
        Entry entry = new Entry(1, "Title", "Body");
        repository.save(entry);
        assertEquals(entry, repository.findById(1));
    }

    @Test
    public void createTwoEntry_canFindAllEntriesSaved(){
        EntryRepository repository = new EntryRepositoryImplement();
        Entry entry = new Entry(1, "Title", "Body");
        Entry entry2 = new Entry(2, "Title2", "Body2");
        repository.save(entry);
        repository.save(entry2);
        assertEquals(2, repository.count());

        var foundEntries = repository.findAll();
        assertEquals(2, foundEntries.size());
    }

    @Test
    public void saveOneEntry_returnEntryWithIdNo(){
        EntryRepository repository = new EntryRepositoryImplement();
        Entry entry = new Entry(1, "Title", "Body");
        Entry newEntry = repository.save(entry);
        assertEquals(1, newEntry.getId());
    }

    @Test
    public void saveOneEntry_updateEntry_countStillZero(){
        EntryRepository repository = new EntryRepositoryImplement();
        Entry entry = new Entry();
        entry.setId(entry.getId());
        entry.setTitle("Title");
        entry.setBody("Body");
        repository.save(entry);
        assertEquals(1, repository.count());

        entry.setId(1);
        entry.setTitle("New Title");
        entry.setBody("New Body");
        repository.save(entry);
        assertEquals(1, repository.count());
    }
}
