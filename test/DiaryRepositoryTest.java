import data.exceptions.DiaryNotFoundException;
import data.exceptions.InvalidUsernameException;
import data.exceptions.UsernameAlreadyExistException;
import data.model.Diary;
import data.repository.DiaryRepository;
import data.repository.DiaryRepositoryImplement;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testSaveOneDiary_cantSaveAnotherDiaryWithSameUsername(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        repository.save(diary);
        Diary diary2 = new Diary("Username", "password");
        assertThrows(UsernameAlreadyExistException.class, ()->repository.save(diary2));
    }

    @Test
    public void testSaveOneDiary_cantDeleteDiaryThatNotSaved(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        repository.save(diary);
        Diary diary2 = new Diary("Username2", "password2");
        assertThrows(DiaryNotFoundException.class, ()-> repository.delete(diary2));
    }

    @Test
    public void testSaveOneDiary_cantDeleteDiaryWithWrongUsername(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        repository.save(diary);
        assertThrows(DiaryNotFoundException.class, ()-> repository.delete("Username2"));
    }

    @Test
    public void testSaveOneDiary_cantFindDiaryByWrongUsername(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary("Username", "password");
        repository.save(diary);
        assertThrows(InvalidUsernameException.class, ()->repository.findByUsername("Username2"));
    }
}
