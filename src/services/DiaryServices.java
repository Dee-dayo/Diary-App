package services;

import data.model.Diary;
import data.model.Entry;
import dtos.requests.*;

import java.util.List;

public interface DiaryServices {
    void register(RegisterRequest registerRequest);
    long getNoOfCustomers();
    void login(LoginRequest loginRequest);
    Diary findDiaryByUsername(String username);
    void logout(LogoutRequest logoutRequest);
    void createEntry(EntryRequest entryRequest);
    void deleteDiary(DeleteUserRequest deleteUserRequest);
    List<Entry> getEntries(String username);

    void updateEntry(EntryRequest entryRequest2);

    void deleteEntry(DeleteEntryRequest deleteEntryRequest);
}
