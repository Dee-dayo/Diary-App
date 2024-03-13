import data.model.Diary;
import data.repository.DiaryRepository;
import data.repository.DiaryRepositoryImplement;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiaryRepositoryTest {
    @Test
    public void testSaveDiary_countIsOne(){
        DiaryRepository repository = new DiaryRepositoryImplement();
        Diary diary = new Diary();
        repository.save(diary);
        assertEquals(1, repository.count());
    }
}
