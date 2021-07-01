package middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericCard {

    private Map<String,String> datas;

    public GenericCard ()
    {
        datas = new HashMap<String, String>();
    }

    public void addData (String key, String value)
    {
        this.datas.put(key,value);
    }

    public void print() {

        this.datas.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    public String getData(String key)
    {
        return  datas.get(key);
    }
    public List<String> getHeaders(){
        List<String> types = new ArrayList<>();
        for (String s: datas.values() ) {
            if(s==null)
                s="Le type de PlaceHolder n'existe pas";
            types.add(s);
        }
        return types;
    }
}
