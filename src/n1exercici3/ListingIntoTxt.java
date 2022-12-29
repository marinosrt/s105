package n1exercici3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ListingIntoTxt {
    private static File [] filesList;

    public void readRoute(File route, String sep) throws IOException {
        SimpleDateFormat Date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        filesList = route.listFiles(); //list all files inside given directory
        String sepInicial = " ", data;

        try {
            if (filesList != null){
                for (File f : filesList) {
                    if (f.isDirectory()){ //OJU AMB LES SEPARACIONS. CALCULAR
                        data = "- " + f.getName() + " (D) Last modified: " + Date.format(f.lastModified());
                        SaveIntoNewFile(data);
                        String newSep = sep + "  ";
                        readRoute(f, newSep); //call method to write into the txt file
                        sep = sepInicial;
                    } else {
                        data = sep + f.getName() + " (F) Last modified: " + Date.format(f.lastModified());
                        SaveIntoNewFile(data);
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Method to create/write into the txt file output
    public void SaveIntoNewFile(String data){
        //BufferedWriter bw = null;
        FileWriter fw = null;
        File OutFile;
        boolean first = false;

        try {
            OutFile = new File("./src/n1exercici3/directories.txt");

            if (!OutFile.exists()){
                OutFile.getParentFile().mkdir();
                OutFile.createNewFile();
            }

            fw = new FileWriter(OutFile, true);

            if (!first){
                first = true;
                fw.write(data);
            } else {
                fw.write("\n" + data);
            }


        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            try {
                if (fw != null){
                    fw.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
