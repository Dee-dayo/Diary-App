package data.model;

import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String username;
    private String password;
    private boolean isLocked = false;
    private List<Entry> entries = new ArrayList<Entry>();
    public Diary(){}

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock(){
        isLocked = false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

}
