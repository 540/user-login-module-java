package Infrastructure;

public class FacebookSessionManager {
    public boolean login(String userName, String password)
    {
        //Imaginad que esto en realidad realiza una llamada al API de Facebook
        return Math.random() < 0.5;
    }

    public int getSessions()
    {
        //Imaginad que esto en realidad realiza una llamada al API de Facebook
        return (int) (Math.random() * 100);
    }
}
