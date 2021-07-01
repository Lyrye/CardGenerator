package front;

import java.awt.*;
import java.util.HashMap;

public class Images {
    private HashMap<String, Image> images;
    public Images(){
        images = new HashMap<>();
    }
    public void addImage(String name, Image image){
        if(!exist(name))
        images.put(name,image);
    }
    public Image getImage(String name){
        return images.get(name);
    }
    public boolean exist(String name){
        return images.containsKey(name);
    }
}
