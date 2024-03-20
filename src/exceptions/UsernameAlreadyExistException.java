package exceptions;

public class UsernameAlreadyExistException extends DiaryAppException{
    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
