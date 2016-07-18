package au.com.interactivehippo.sweethome;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Admin on 9/27/2015.
 */

public class User implements Serializable{

    private int userId;
    private String userFirstname;
    private String userLastname;
    private String userEmail;


    public User(){ super(); }

    public User(int userId, String userFirstname, String userEmail, String userLastname){
        this.userId = userId;
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.userEmail = userEmail;
    }


    public void setUserId(int userId){
        this.userId = userId;
    }

    public void setUserFirstname(String userFirstname){
        this.userFirstname = userFirstname;
    }

    public void setUserLastname(String userLastname){
        this.userLastname = userLastname;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public int getUserId(){
        return this.userId;
    }

   public String getUserFirstname(){
        return this.userFirstname;
    }

    public String getUserLastname(){
        return this.userLastname;
    }

    public String getUserEmail(){
        return this.userEmail;
    }

}
