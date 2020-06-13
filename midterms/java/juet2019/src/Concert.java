import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Concert {
    private String city;
    private String country;
    private String date;
    private List<Act> acts = new ArrayList<>();
    private int ID = 1;

    public Concert(String city, String country, String date) {
        this.city = city;
        this.country = country;
        this.date = date;
    }

    public void addAct(Act act) {
        acts.add(act);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Concert c = (Concert) o;
        return city.equals(c.getCity()) &&
            country.equals(c.getCountry()) &&
            date.equals(c.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, date);
    }

    public List<Act> getActs() { return acts; }
    public String getCity() { return city; }
    public String getCountry() { return country; }
    public String getDate() { return date; }
    public int getID() { return ID; }
    public void incrementID() { ID++; }

    public boolean isValid(Ticket ticket) {
        return ticket.getConcert().equals(this);
    }

    public boolean participates(Artist artist) {
        for(Act act : acts) {
            if(act instanceof Band && ((Band)act).getArtists().contains(artist))
                return true;
            else if(act.equals(artist))
                return true;
        }

        return false;
    }
}
