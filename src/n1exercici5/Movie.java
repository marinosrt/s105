package n1exercici5;

import java.io.Serializable;

public class Movie implements Serializable {

    private String MovieName;
    private double TicketPrice;
    private transient String Pw;


    public Movie(String movieName, double ticketPrice, String pw) {
        MovieName = movieName;
        TicketPrice = ticketPrice;
        Pw = pw;
    }

    public String getMovieName() {
        return MovieName;
    }

    public double getTicketPrice() {
        return TicketPrice;
    }

    public String getPw() {
        return Pw;
    }

    @Override
    public String toString() {
        return "To see " + getMovieName() +
                ", it cost " + getTicketPrice() + "€ total. " +
                "Password is: " + getPw();
    }
}
