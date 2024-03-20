package exceptions;

public class DiaryLockedException extends RuntimeException {
    public DiaryLockedException(String message) {
        super(message);
    }
}
