package au.com.interactivehippo.sweethome;

/**
 * Created by Admin on 9/27/2015.
 */
public class User {

    private int userId;
    private String userFirstname;

    public User(){}

    public User(int userId, String userFirstname){
        this.userId = userId;
        this.userFirstname = userFirstname;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public void setUserFirstname(String userFirstname){
        this.userFirstname = userFirstname;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getUserFirstname(){
        return this.userFirstname;
    }
}
