package ua.teampush.appteampush;

/**
 * Created by Анна on 26.05.2015.
 */
public class Room {
    private int id;
    private String name;
    private String pass;
    private String admin;
    private int users;

    Room(int id, String name, String pass, String admin, int users){
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.admin = admin;
        this.users = users;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getUsers() {
        return "Users:" +users;
    }

    public void setUsers(int users) {
        this.users = users;
    }
}