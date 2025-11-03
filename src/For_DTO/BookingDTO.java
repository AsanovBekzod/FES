package For_DTO;

import Entity.Time;

public record BookingDTO(int id, int userId, int stadiumId, Time time) {
}
