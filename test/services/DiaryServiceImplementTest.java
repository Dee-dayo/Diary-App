package services;

import data.model.Diary;
import dtos.requests.RegisterRequest;
import exceptions.DiaryNotFoundException;
import exceptions.UsernameAlreadyExistException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServiceImplementTest {

    @Test
    public void registerOneUser_countIsOne(){
        DiaryServiceImplement diaryService = new DiaryServiceImplement();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);
        assertEquals(1, diaryService.getNoOfCustomers());
    }

    @Test
    public void registerOneUser_UserTriesToRegisterAgain_ThrowsException(){
        DiaryServiceImplement diaryService = new DiaryServiceImplement();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);
        assertThrows(UsernameAlreadyExistException.class, ()->diaryService.register(registerRequest));
    }

    @Test
    public void registerOneUser_userCanLoginWithValidCredentials(){
        DiaryServiceImplement diaryService = new DiaryServiceImplement();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);
        assertEquals(1, diaryService.getNoOfCustomers());

        Diary loggedInDiary = diaryService.login(registerRequest);
        assertNotNull(loggedInDiary);
    }

    @Test
    public void registerOneUser_UserCanLoginWithAnyCaseSensitivity(){
        DiaryServiceImplement diaryService = new DiaryServiceImplement();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);

        registerRequest.setUsername("userNAME");
        Diary loggedInDiary = diaryService.login(registerRequest);
        assertNotNull(loggedInDiary);
    }

    @Test
    public void registerOneUser_userCantLoginWithWrongPassword(){
        DiaryServiceImplement diaryService = new DiaryServiceImplement();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);

        registerRequest.setPassword("wrongPassword");
        Diary loggedInDiary = diaryService.login(registerRequest);
        assertNull(loggedInDiary);
    }

    @Test
    public void registerOneUser_userCantLoginWithIncorrectCaseSensitivePassword(){
        DiaryServiceImplement diaryService = new DiaryServiceImplement();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);

        registerRequest.setPassword("PASSword");
        Diary loggedInDiary = diaryService.login(registerRequest);
        assertNull(loggedInDiary);
    }

    @Test
    public void userCantLoginIfNotRegistered_throwDiaryNotFoundException(){
        DiaryServiceImplement diaryService = new DiaryServiceImplement();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");

        assertThrows(DiaryNotFoundException.class, ()->diaryService.login(registerRequest));
    }
}
