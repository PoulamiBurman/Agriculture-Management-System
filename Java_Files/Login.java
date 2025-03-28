package Top_Classes;

import java.util.HashMap;


public abstract class Login {
    HashMap<String, String> Credentials = new HashMap<String, String>();

    public void addCredentials(String u, String p) {
        Credentials.put(u, p);
    }
    public boolean authenticate(String u, String p){
        if(!Credentials.containsKey(u)) return false;
        return (Credentials.get(u).equals(p));
    }
    
}
