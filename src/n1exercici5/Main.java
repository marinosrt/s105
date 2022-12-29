package n1exercici5;

import java.io.*;
import java.util.Scanner;



public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean FirstSer = false, MovieSer = false, ArraySer = false;
        int klk = -1;
        String answer = "";

        do {
            switch (menu(FirstSer, klk)){
                case 0:
                    System.out.println("\nBye!");
                    break;
                case 1:
                    FirstSer = true;
                    if (SerializeWhat()) {
                        SerializeArrayOfStringsObject(ArraySer);
                    } else {
                        SerializeMovieObject(MovieSer);
                    }
                    break;
                case 2:
                    if (DeSerializeWhat() && ArraySer) {
                        DeserializeArrayOfStringsObject(ArraySer);
                    } else if (MovieSer){
                        DeserializeMovieObject(MovieSer);
                    } else {
                        System.out.println("\nYou need to serialize the object first.");
                    }
                    break;
            }
        } while (klk != 0);


    }

    public static int menu(boolean FirstSer, int klk){

        do{
            System.out.println("\nWhat do you want to do?" +
                    "\n0. Exit program." +
                    "\n1. Serialize an Object." +
                    "\n2. Deserialize an Object.");
            System.out.println("Choose an option.");
            klk = sc.nextInt();
            if (klk == 2 && !FirstSer){
                klk = -1;
                System.out.println("\nCan't deserialize an object if you haven't serialize it in the first place.");
            }
        } while (klk < 0 && klk > 2);

        return klk;
    }

    public static boolean SerializeWhat(){
        int answer;

        System.out.println("\nPress 0 if you want to serialize a Movie Object " +
                "\nPress 1 if you want to serialize an array of movies.");
        answer = sc.nextInt();

        return answer == 1 ? true : false;


    }

    public static boolean DeSerializeWhat(){
        int answer;

        System.out.println("\nPress 0 if you want to deserialize a Movie Object " +
                "\nPress 1 if you want to deserialize an array of movies.");
        answer = sc.nextInt();

        return answer == 1 ? true : false;


    }

    public static boolean SerializeMovieObject(boolean MovieSer){
        FileOutputStream fos = null;
        ObjectOutputStream ObjOut = null;
        Movie movie;

        try {
            //create output file
            fos = new FileOutputStream("./src/n1exercici5/movies.ser");
            ObjOut = new ObjectOutputStream(fos);

            //create objects and write them to the file
            movie = new Movie("Avatar 2", 14.50, "wait13MoreYears");
            ObjOut.writeObject(movie);
            movie = new Movie("A Quiet Place 2", 9.50, "lowQualityWithNewMovie");
            ObjOut.writeObject(movie);
            movie = new Movie("A Long Story", 5.30, "DavidLinchDaBest");
            ObjOut.writeObject(movie);

            System.out.println("\nFile movies.ser correctly created!");
            MovieSer = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (fos != null){
                    fos.close();
                }
                if (ObjOut != null){
                    ObjOut.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return MovieSer;
    }

    public static boolean SerializeArrayOfStringsObject(boolean ArraySer){
        String[] movies = new String[3];
        FileOutputStream fos = null;
        ObjectOutputStream ObjOut = null;

        try {
            movies[0] = "Avatar";
            movies[1] = "A Quiet Place 2";
            movies[2] = "A Long Story";

            fos = new FileOutputStream("./src/n1exercici5/moviesArray.ser");
            ObjOut = new ObjectOutputStream(fos);

            ObjOut.writeObject(movies);

            System.out.println("\nFile moviesArray.ser created successfully!");
            ArraySer = true;

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (fos != null){
                    fos.close();
                }
                if (ObjOut != null){
                    ObjOut.close();
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return ArraySer;
    }

    public static void DeserializeMovieObject(boolean MovieSer){
        FileInputStream fis = null;
        ObjectInputStream ObjIn = null;
        Movie movie;


        try {
            //FileInputStream will bring the object from the specified directory
            fis = new FileInputStream("./src/n1exercici5/movies.ser");
            ObjIn = new ObjectInputStream(fis);

            System.out.println("\nObjects deserialized. Those are the objects: \n");
            System.out.println((movie = (Movie) ObjIn.readObject()).toString());
            System.out.println((movie = (Movie) ObjIn.readObject()).toString());
            System.out.println((movie = (Movie) ObjIn.readObject()).toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null){
                    fis.close();
                }
                if (ObjIn != null){
                    ObjIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void DeserializeArrayOfStringsObject(boolean ArraySer){
        FileInputStream fis = null;
        ObjectInputStream ObjIn = null;
        String[] ListOf = new String[3];

        try {

            fis = new FileInputStream("./src/n1exercici5/moviesArray.ser");
            ObjIn = new ObjectInputStream(fis);

            ListOf = (String[]) ObjIn.readObject();

            System.out.println("\nObjects deserialized with exit! Those are the results:\n");

            for (String o : ListOf){
                System.out.println(o.toString());
            }


        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null){
                    fis.close();
                }
                if (ObjIn != null){
                    ObjIn.close();
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

}
