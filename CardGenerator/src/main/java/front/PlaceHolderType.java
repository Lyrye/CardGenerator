package front;

public class PlaceHolderType {

    private String type ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PlaceHolderType (String type)
    {
        this.type = type;
    }

    public String toString()
    {
        return this.type;
    }

}
