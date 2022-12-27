package n1exercici2;


import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{

        String route = "", sep = " ";
        File dir;
        Listing listing = new Listing();

        if (args.length > 1) { // if there's more than 1 par
            System.out.println("Too many parameters, only 1 shall pass");
        } else if (args.length == 0) { // if there's no par
            System.out.println("Insert a parameter");
        } else {
            route = args[0];

            dir = new File(route);

            listing.readRoute(dir, sep);
            listing.sort();

        }
    }
}