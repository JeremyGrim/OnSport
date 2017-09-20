package cdi.com.onsport.MyContext;

import cdi.com.onsport.Utilisateur;

/**
 * Created by Administrateur on 11/09/2017.
 */

public class UserHandler {

    private static UserHandler INSTANCE;

    private Utilisateur user;

    private UserHandler() {

    }

    public static UserHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new UserHandler();
        }
        return INSTANCE;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
}
