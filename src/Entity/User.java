package Entity;

public class User {
    private int Id;
    private String name;
    private String phoneNum;
    private String password;
    private Role role;

    public User(int id, String name, String phoneNum, String password, Role role) {
        Id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "%d#%s#%s#%s#%s\n".formatted(Id,name,phoneNum,password,role);
    }
}
