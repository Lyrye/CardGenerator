package front;

import java.util.ArrayList;

public class Template {

    private ArrayList<PlaceHolder> placeHolderList;

    public Template(){
        placeHolderList = new ArrayList<PlaceHolder>();
    }

    public void addPlaceHolder(PlaceHolder placeHolder){
        placeHolderList.add(placeHolder);
    }

    public ArrayList<PlaceHolder> getPlaceHolderList()
    {
        return placeHolderList;
    }



}
