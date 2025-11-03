package Entity;

public class Stadium {
    private int id;
    private String name;
    private String category;
    private String location;
    private double price;
    private Time time;

    public Stadium(int id, String name, String category, String location, double price, Time time) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
        this.price = price;
        this.time = time;
    }

    public Stadium() {}

    public void setTime(Time time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "%d#%s#%s#%s#%s#%s\n".formatted(id,name,category,location,price,time.name());
    }

}
