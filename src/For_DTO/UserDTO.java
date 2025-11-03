package For_DTO;

import Entity.Role;

public record UserDTO(int id, String name, String phoneNum, String password, Role role) {
}
