package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtil {

    public static final String DELIMITER = ";";

    public static List<String> FromCsvToList(String path)
    {
        List<String>lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static List<String> getColumnTitle (String path)
    {
        List<String> collumTitle= new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            collumTitle = Arrays.asList(br.readLine().split(CsvUtil.DELIMITER));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return collumTitle;
    }


}
