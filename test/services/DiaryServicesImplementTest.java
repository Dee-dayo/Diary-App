package services;

import data.repository.EntryRepository;
import data.repository.EntryRepositoryImplement;
import dtos.requests.*;
import exceptions.UsernameAlreadyExistException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServicesImplementTest {
    private DiaryServices diaryServices;
    private EntryServices entryServices;
    private EntryRepository repository = new EntryRepositoryImplement();

    @Before
    public void setup(){
        diaryServices = new DiaryServiceImplement();
        entryServices = new EntryServiceImplement();

    }

    @After
    public void tearDown(){
        repository.deleteAll();
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
        deleteEntryRequest.setUsername("username");
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
        assertEquals("Title", entryServices.findEntriesByUsername("Username").getFirst().getTitle());
        assertEquals(1, entryServices.findEntriesByUsername("Username").getFirst().getId());


        EntryRequest entryRequest2 = new EntryRequest();
        entryRequest2.setTitle("New Title");
        entryRequest2.setBody("New Body");
        entryRequest2.setAuthor("Username");
        entryRequest2.setId(1);
        diaryServices.updateEntry(entryRequest2);
        assertEquals("New Title", entryServices.findEntriesByUsername("Username").getFirst().getTitle());
        assertEquals(1, entryServices.findEntriesByUsername("USERname").size());
    }
}