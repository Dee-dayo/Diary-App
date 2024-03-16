package dataRepository;

import data.model.Diary;
import data.repository.DiaryRepository;
import data.repository.DiaryRepositoryImplement;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryRepositoryTest {

    @Test
    public void testSaveDiary_countIsOne(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        repository.save(diary);
        assertEquals(1, repository.count());
    }

    @Test
    public void testSaveTwoDiary_countIsTwo(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        repository.save(diary);
        Diary diary2 = new Diary("Username2", "password");
        repository.save(diary2);
        assertEquals(2, repository.count());
    }

    @Test
    public void testSaveTwoDiary_deleteOne_countIsOne(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        repository.save(diary);
        Diary diary2 = new Diary("Username2", "password");
        repository.save(diary2);
        assertEquals(2, repository.count());

        repository.delete(diary);
        assertEquals(1, repository.count());
    }

     @Test
    public void testSaveTwoDiary_deleteOneWithUsername_countIsOne(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        repository.save(diary);
        Diary diary2 = new Diary("Username2", "password2");
        repository.save(diary2);
        repository.delete("Username");
        assertEquals(1, repository.count());
    }

    @Test
    public void testSaveTwoDiary_deleteTwo_countIsZero(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        Diary diary2 = new Diary("Username2", "password");
        repository.save(diary);
        repository.save(diary2);
        repository.delete(diary);
        repository.delete(diary2);
        assertEquals(0, repository.count());
    }

    @Test
    public void testSaveOneDiary_canFindDiaryByUsername(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        repository.save(diary);
        assertEquals(diary, repository.findByUsername("Username"));
    }

    @Test
    public void saveTwoDiary_canFindAllDiariesSaved(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        repository.save(diary);
        repository.save(diary);
        assertEquals(2, repository.count());

        var foundDiaries = repository.findAll();
        assertEquals(2, foundDiaries.size());
    }

    @Test
    public void diaryIsUnlockedDefault(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        Diary savedDiary = repository.save(diary);
        assertFalse(savedDiary.isLocked());
    }

    @Test
    public void diaryisUnlocked_diaryCanBeLocked(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        Diary saved = repository.save(diary);
        repository.lock(diary);
        assertTrue(saved.isLocked());
    }

//    @Test
//    public void diaryisLocked_canUnlockWithPassword(){
//        DiaryRepository repository = new DiaryRepositoryImplement();
//        Diary diary = new Diary("Username", "password");
//        repository.save(diary);
//        repository.unlockDiary("");
//        Diary foundDiary = repository.findByUsername("Username");
//        foundDiary.lock();
//        assertTrue(foundDiary.isLocked());
//
//        foundDiary.unlock("password");
//        assertFalse(foundDiary.isLocked());
//    }
}
