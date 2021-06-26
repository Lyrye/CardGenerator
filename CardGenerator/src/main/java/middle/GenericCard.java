package middle;

import java.util.HashMap;
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

        System.out.println("To String");
        this.datas.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    public String getData(String key)
    {
        return  datas.get(key);
    }
}
