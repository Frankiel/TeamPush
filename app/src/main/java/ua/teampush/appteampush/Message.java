package ua.teampush.appteampush;

/**
 * Created by Anna on 27.05.2015.
 */
public class Message {
    private int id;
    private String text ;
    private String user;
    private String date;
    private String room;
    Message(int id, String text, String user, String date, String room ){
        this.id = id;
        this.text = text;
        this.user = user;
        this.date = date;
        this.room = room;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
