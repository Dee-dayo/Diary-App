package services;

import dtos.requests.RegisterRequest;
import exceptions.UsernameAlreadyExistException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiaryServiceImplementTest {

    @Test
    public void registerOneUser_countIsOne(){
        DiaryServiceImplement diaryService = new DiaryServiceImplement();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("John");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);
        assertEquals(1, diaryService.getNoOfCustomers());
    }

    @Test
    public void registerOneUser_UserTriesToRegisterAgain_ThrowsException(){
        DiaryServiceImplement diaryService = new DiaryServiceImplement();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("John");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);
        assertThrows(UsernameAlreadyExistException.class, ()->diaryService.register(registerRequest));
    }
}
