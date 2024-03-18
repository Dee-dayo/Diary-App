package services;

import dtos.requests.EntryRequest;
import dtos.requests.LoginRequest;
import dtos.requests.LogoutRequest;
import dtos.requests.RegisterRequest;
import exceptions.UsernameAlreadyExistException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServicesImplementTest {
    private DiaryServices diaryServices = new DiaryServiceImplement();
    private static EntryServices entryServices = new EntryServiceImplement();

    @Test
    public void registerOneUser_countIsOne(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryServices.register(registerRequest);
        assertEquals(1, diaryServices.getNoOfCustomers());
    }

    @Test
    public void registerOneUser_UserTriesToRegisterAgain_ThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryServices.register(registerRequest);
        assertThrows(UsernameAlreadyExistException.class, ()-> diaryServices.register(registerRequest));
    }

    @Test
    public void registerOneUser_userDiaryIsUnlocked(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryServices.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Username");
        loginRequest.setPassword("password");

        diaryServices.login(loginRequest);
        assertFalse(diaryServices.findDiaryByUsername("username").isLocked());
    }


    @Test
    public void registerOneUser_userLoginIn_userCanLogout(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryServices.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Username");
        loginRequest.setPassword("password");

        diaryServices.login(loginRequest);
        assertFalse(diaryServices.findDiaryByUsername("username").isLocked());

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("USERNAME");
        diaryServices.logout(logoutRequest);
        assertTrue(diaryServices.findDiaryByUsername("username").isLocked());
    }

    @Test
    public void registerOneUser_userCanCreateEntry(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryServices.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Username");
        loginRequest.setPassword("password");
        diaryServices.login(loginRequest);

        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("Username");
        diaryServices.createEntry(entryRequest);

        assertEquals(1, entryServices.findEntriesByUsername("Username").size());
    }
}