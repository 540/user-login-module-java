package Application;

public interface SessionManager {
    public int getSessions();

    public boolean login(String userName, String password);
}
