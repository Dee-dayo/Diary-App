package controllers;

import data.model.Entry;
import dtos.requests.*;
import exceptions.DiaryAppException;
import services.DiaryServiceImplement;
import services.DiaryServices;
import services.EntryServiceImplement;
import services.EntryServices;

import java.util.List;

public class DiaryController {
    private static DiaryServices diaryServices = new DiaryServiceImplement();
    private static EntryServices entryServices = new EntryServiceImplement();

    public static String register(RegisterRequest registerRequest){
        try {
            diaryServices.register(registerRequest);
            return "Registration Successful";
        }
        catch (DiaryAppException message){
            return message.getMessage();
        }
    }

    public static String login(LoginRequest loginRequest){
        try {
            diaryServices.login(loginRequest);
            return "Login Successful";
        } catch (DiaryAppException message){
            return message.getMessage();
        }
    }

    public static String logout(LogoutRequest logoutRequest){
        try {
            diaryServices.logout(logoutRequest);
            return "Logout Successful";
        } catch (DiaryAppException message){
            return message.getMessage();
        }
    }

    public static String CreateEntry(EntryRequest entryRequest){
        try {
            diaryServices.createEntry(entryRequest);
            return "Entry Created Successfully";
        } catch (DiaryAppException message){
            return message.getMessage();
        }
    }

    public static String updateEntry(EntryRequest entryRequest){
        try {
            diaryServices.updateEntry(entryRequest);
            return "Entry Updated Successfully";
        } catch (DiaryAppException message){
            return message.getMessage();
        }
    }

    public static String deleteEntry(DeleteEntryRequest deleteEntryRequest){
        try {
            diaryServices.deleteEntry(deleteEntryRequest);
            return "Entry Deleted Successfully";
        } catch (DiaryAppException message){
            return message.getMessage();
        }
    }

    public static List<Entry> getAllEntries(String username){
        try {
            entryServices.findEntriesByUsername(username);
        } catch (DiaryAppException message){
            return List.of();
        }
        return null;
    }

    public static String deleteDiary(DeleteUserRequest deleteUserRequest){
        try {
            diaryServices.deleteDiary(deleteUserRequest);
            return "Diary Deleted Successfully";
        } catch (DiaryAppException message){
            return message.getMessage();
        }
    }
}
