package n1exercici1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Listing {
    private static File[] filesList;
    private static ArrayList<String> ListToString = new ArrayList<>();

    public void readRoute(File route, String sep) throws IOException {
        SimpleDateFormat Date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        filesList = route.listFiles(); //list all files inside given directory
        String sepInicial = " ";

        if (filesList != null){
            for (File f : filesList) {
                ListToString.add(f.getName());
            }
        }
    }

    public void sort(){
        Collections.sort(ListToString);
        for (String s : ListToString){
            System.out.println(s);
        }
    }
}
