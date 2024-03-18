package services;

import data.model.Diary;
import dtos.requests.EntryRequest;
import dtos.requests.LoginRequest;
import dtos.requests.LogoutRequest;
import dtos.requests.RegisterRequest;

public interface DiaryServices {
    void register(RegisterRequest registerRequest);
    long getNoOfCustomers();
    void login(LoginRequest loginRequest);
    Diary findDiaryByUsername(String username);
    void logout(LogoutRequest logoutRequest);

    void createEntry(EntryRequest entryRequest);
}
