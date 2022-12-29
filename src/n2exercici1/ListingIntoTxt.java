package n2exercici1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class ListingIntoTxt {
    private static File [] filesList;
    private static File OutFile;

    public static void readRoute(File route, String sep) throws IOException {
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
    public static void SaveIntoNewFile(String data){
        String PathFile = "/Users/marinaroyoterol/GitHub/s105/src/n1exercici3/directories.txt";
        FileWriter fw = null;
        boolean first = false;

        try {
            OutFile = new File(PathFile);

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

    //method to set properties & create .properties file & print content from file

    public static void CreateAndReadPropertiesFile(File dir){
        Properties properties = new Properties();
        String PropFile = "/Users/marinaroyoterol/GitHub/s105/src/n2exercici1/prop.properties";
        InputStream inputStream = null;

        try {
            //set and store properties into file. Null is empty 'cos we don't want to comment just store the property
            properties.setProperty("ROUTE", "Route to read directory: " + dir.getPath());
            properties.store(new FileWriter(PropFile), null);
            properties.setProperty("TXTfile", "Name of the .txt file: " + OutFile.getName() + ". Path to it: " + OutFile.getPath());
            properties.store(new FileWriter(PropFile), null);

            //save and create .properties file
            inputStream = new FileInputStream(PropFile);
            properties.load(inputStream);

            //add comment to .properties file
            properties.store(new FileWriter(PropFile), "The parameterized values are:\n");

            System.out.println("\n The prop.properties file contains this parameterized values: \n");
            System.out.println(properties.getProperty("ROUTE"));
            System.out.println(properties.getProperty("TXTfile"));


            //print properties from file
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
