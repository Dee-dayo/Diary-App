package services;

import data.model.Entry;
import dtos.requests.*;
import exceptions.UsernameAlreadyExistException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServicesImplementTest {
    private DiaryServices diaryServices;
    private EntryServices entryServices = new EntryServiceImplement();

    @BeforeEach
    public void setUp(){
        diaryServices = new DiaryServiceImplement();
    }


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
        assertEquals(1, diaryServices.getNoOfCustomers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Username");
        loginRequest.setPassword("password");
        diaryServices.login(loginRequest);

        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("Username");
        diaryServices.createEntry(entryRequest);

        assertEquals(1, entryServices.findEntriesByUsername("USERname").size());
    }

    @Test
    public void registerOneUser_canDeleteUserWithCorrectDetails(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryServices.register(registerRequest);
        assertEquals(1, diaryServices.getNoOfCustomers());

        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setUsername("USERNAme");
        deleteUserRequest.setPassword("password");

        diaryServices.deleteDiary(deleteUserRequest);
        assertEquals(0, diaryServices.getNoOfCustomers());
    }

    @Test
    public void registerOneUser_userCanCreateEntry_userCanDeleteEntry(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryServices.register(registerRequest);
        assertEquals(1, diaryServices.getNoOfCustomers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Username");
        loginRequest.setPassword("password");
        diaryServices.login(loginRequest);

        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("Username");
        diaryServices.createEntry(entryRequest);

        assertEquals(1, entryServices.findEntriesByUsername("USERname").size());

        DeleteEntryRequest deleteEntryRequest = new DeleteEntryRequest();
        deleteEntryRequest.setEntryId(1);
        diaryServices.deleteEntry(deleteEntryRequest);
        assertEquals(0, entryServices.findEntriesByUsername("USERname").size());
    }

    @Test
    public void registerOneUser_userCanCreateEntry_userCanUpdateEntry(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("password");
        diaryServices.register(registerRequest);
        assertEquals(1, diaryServices.getNoOfCustomers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Username");
        loginRequest.setPassword("password");
        diaryServices.login(loginRequest);

        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("Username");
        diaryServices.createEntry(entryRequest);
        assertEquals(1, entryServices.findEntriesByUsername("USERname").size());

        List<Entry> foundEntries = diaryServices.getEntries("username");
        assertEquals(1, foundEntries.size());

        EntryRequest entryRequest2 = new EntryRequest();
        entryRequest2.setTitle("New Title");
        entryRequest2.setBody("New Body");
        entryRequest2.setAuthor("Username");
        diaryServices.updateEntry(entryRequest2);
        assertEquals(1, entryServices.findEntriesByUsername("USERname").size());
    }
}