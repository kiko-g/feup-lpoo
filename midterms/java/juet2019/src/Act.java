import java.util.Objects;

public abstract class Act {
    private String name;
    private String country;

    public Act(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCountry());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Act act = (Act) o;
        return name.equals(act.getName()) && country.equals(act.getCountry());
    }

    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
}
