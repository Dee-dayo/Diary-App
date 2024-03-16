package services;

import data.model.Diary;
import dtos.requests.RegisterRequest;

public interface DiaryService {
    void register(RegisterRequest registerRequest);
    Diary login(RegisterRequest registerRequest);
}
