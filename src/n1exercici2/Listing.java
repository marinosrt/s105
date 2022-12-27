package n1exercici2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Listing {
    private static File[] filesList;
    private static ArrayList<String> ListToString = new ArrayList<>();

    public void readRoute(File route, String sep) throws IOException {
        SimpleDateFormat Date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        filesList = route.listFiles(); //list all files inside given directory
        String sepInicial = " ";

        try {
            if (filesList != null){
                for (File f : filesList) {
                    if (f.isDirectory()){ //OJU AMB LES SEPARACIONS. CALCULAR
                        ListToString.add("- " + f.getName() + " (D) Last modified: " + Date.format(f.lastModified()));
                        String newSep = sep + "  ";
                        readRoute(f, newSep);
                        sep = sepInicial;
                    } else {
                        ListToString.add(sep + f.getName() + " (F) Last modified: " + Date.format(f.lastModified()));
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sort(){
        for (String s : ListToString){
            System.out.println(s);
        }
    }
}
