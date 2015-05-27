package ua.teampush.appteampush;

/**
 * Created by Анна on 26.05.2015.
 */
public class Item {
    private String name ;
    private int imageNumber;
    Item(String name, int imageNumber){
        this.name = name;
        this.imageNumber = imageNumber;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }
}