package front;

import java.util.ArrayList;

public class PlaceHolders {

    private ArrayList<PlaceHolder> placeHolderList;

    public PlaceHolders(){
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
