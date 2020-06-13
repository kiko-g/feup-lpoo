import java.util.ArrayList;
import java.util.List;

public class BoxOffice
{
    public static List<Ticket> buy(Concert concert, int amount) throws InvalidTicket {
        List<Ticket> tickets = new ArrayList<>();
        for(int i=0; i<amount; ++i) {
            tickets.add(new Ticket(concert.getID(), concert));
            concert.incrementID();
        }
        return tickets;
    }
}
