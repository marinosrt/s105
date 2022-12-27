package n1exercici4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Scanner;

public class ListingFromTxt {

    static Scanner sc = new Scanner(System.in);

    public void chooseATxtFile(File dir){
        File[] txtFiles;

        FilenameFilter txtFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };

        txtFiles = dir.listFiles(txtFilter);

        if (txtFiles.length == 0){
            System.out.println("\nError. No .txt files found in the given directory");
        } else if (txtFiles.length == 1){
            printInsideTxtFile(txtFiles[0]);
        } else {
            printInsideTxtFile(userChooseFile(txtFiles));
        }


    }

    public File userChooseFile(File[] txtFiles){
        int userIndex;

        System.out.println("\nSelect a .txt file to read");
        for (int i = 0; i < txtFiles.length; i++){
            System.out.println(i+1 + " " + txtFiles[i].getName());
        }
        System.out.println("\nEnter a number to select the file you want to read");
        userIndex = sc.nextInt();

        return txtFiles[userIndex-1];
    }

    public void printInsideTxtFile(File selectedFile){
        BufferedReader br = null;
        String line;

        try {
            br = new BufferedReader(new FileReader(selectedFile));
            line = null;

            System.out.println("\nInside " + selectedFile.getName() + " there's:\n");
            while ((line = br.readLine()) != null){
                if(!line.isBlank()){
                    System.out.println(line);
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (Exception ex){

                }
            }
        }
    }


}
