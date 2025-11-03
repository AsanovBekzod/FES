package DataBase;

import Entity.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataBase {

    private static DataBase dataBase;

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    private DataBase() {
    }


    Path users = Path.of("src/DataBase/users.txt");
    Path stadiums = Path.of("src/DataBase/stadiums.txt");
    Path bookings = Path.of("src/DataBase/bookings.txt");

    public boolean createUser(User user) {
        try {
            Files.writeString(users, user.toString(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException _) {
            return false;
        }
    }

    public void createStadiums(Stadium stadium) {
        try {
            Files.writeString(stadiums, stadium.toString(), StandardOpenOption.APPEND);
        } catch (IOException _) {
        }
    }

    public boolean createBooking(Booking booking) {
        try {
            Files.writeString(bookings, booking.toString(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException _) {
            return false;
        }
    }


    public ArrayList<User> getUsers() {
        ArrayList<User> usersBase = new ArrayList<>();
        try {
            List<String> list = Files.readAllLines(users);

            for (String s : list) {
                String[] user = s.split("#");
                int id = Integer.parseInt(user[0]);
                String name = user[1];
                String phoneNum = user[2];
                String password = user[3];
                Role role = Role.valueOf(user[4]);
                usersBase.add(new User(id, name, phoneNum, password, role));
            }
        } catch (IOException _) {

        }
        return usersBase;
    }

    public ArrayList<Stadium> getStadiums() {
        ArrayList<Stadium> stadiumsBase = new ArrayList<>();

        try {
            List<String> list = Files.readAllLines(stadiums);
            for (String s : list) {
                String[] stadium = s.split("#");
                int id = Integer.parseInt(stadium[0]);
                String name = stadium[1];
                String category = stadium[2];
                String location = stadium[3];
                double price = Double.parseDouble(stadium[4]);
                Time time = Time.valueOf(stadium[5]);
                stadiumsBase.add(new Stadium(id, name, category, location, price, time));
            }
        } catch (IOException _) {
        }

        return stadiumsBase;
    }

    public ArrayList<Booking> getBookings() {
        ArrayList<Booking> bookingsBase = new ArrayList<>();

        try {
            List<String> list = Files.readAllLines(bookings);

            for (String s : list) {
                String[] booking = s.split("#");
                int id = Integer.parseInt(booking[0]);
                int userId = Integer.parseInt(booking[1]);
                int stadiumId = Integer.parseInt(booking[2]);
                Time time = Time.valueOf(booking[3]);
                bookingsBase.add(new Booking(id, userId, stadiumId, time));
            }
        } catch (IOException _) {
        }

        return bookingsBase;
    }


    public boolean deleteUser(int id) {
        if (getUserById(id).isEmpty()) {
            return false;
        }

        ArrayList<User> users = getUsers();
        try {
            Files.writeString(this.users, "");
        } catch (IOException _) {}

        for (User user : users) {
            if (user.getId() != id) {
                createUser(user);
            }
        }
        return true;
    }

    public boolean deleteStadium(int id) {
        if (getStadiumById(id).isEmpty()) {
            return false;
        }

        ArrayList<Stadium> stadiums = getStadiums();

        try {
            Files.writeString(this.stadiums, "");
        } catch (IOException _) {
        }

        for (Stadium stadium : stadiums) {
            if (stadium.getId() != id) {
                createStadiums(stadium);
            }
        }
        return true;
    }

    public boolean deleteBooking(int id) {
        if (getBookingById(id).isEmpty()) {
            return false;
        }

        ArrayList<Booking> bookings = getBookings();

        try {
            Files.writeString(this.bookings, "");
        } catch (IOException _) {
            return false;
        }

        for (Booking booking : bookings) {
            if (booking.getId() != id) {
                createBooking(booking);
            }
        }
        return true;
    }


    public Optional<User> getUserById(int id) {
        for (User user : getUsers()) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<Stadium> getStadiumById(int id, Time time) {
        for (Stadium stadium : getStadiums()) {
            if (stadium.getId() == id && stadium.getTime().equals(time)) {
                return Optional.of(stadium);
            }
        }
        return Optional.empty();
    }

    public Optional<Stadium> getStadiumById(int id) {
        for (Stadium stadium : getStadiums()) {
            if (stadium.getId() == id) {
                return Optional.of(stadium);
            }
        }
        return Optional.empty();
    }


    public Optional<Booking> getBookingById(int id) {
        for (Booking booking : getBookings()) {
            if (booking.getId() == id) {
                return Optional.of(booking);
            }
        }
        return Optional.empty();
    }

    public Optional<User> getUserByPhoneNum(String phoneNum) {
        for (User user : getUsers()) {
            if (user.getPhoneNum().equals(phoneNum)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
