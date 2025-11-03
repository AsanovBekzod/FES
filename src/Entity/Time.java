package Entity;

public enum Time {
    SLOT1("09:00-11:00"),
    SLOT2("17:00-19:00"),
    SLOT3("19:00-21:00"),
    SLOT4("21:00-23:00");

    private final String range;

    Time(String range){
        this.range = range;
    }

    public String getRange(){
        return this.range;
    }

    public String toString(){
        return range;
    }

}
