package For_DTO;

import Entity.Time;

public record StadiumDTO(int id, String name, String category, String location, double price, Time time) {
}
