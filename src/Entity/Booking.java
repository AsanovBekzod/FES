package Entity;

public class Booking {
    private int id;
    private int userId;
    private int stadiumId;
    private Time time;


    public Booking(int id, int userId, int stadiumId, Time time) {
        this.id = id;
        this.userId = userId;
        this.stadiumId = stadiumId;
        this.time = time;
    }

    public Booking() {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getStadiumId() {
        return stadiumId;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "%d#%d#%d#%s\n".formatted(id,userId,stadiumId,time.name());
    }
}
