package Service;

import DataBase.DataBase;
import Entity.*;
import For_DTO.StadiumDTO;
import For_DTO.UserDTO;

import static Util.Util.*;


import java.util.ArrayList;
import java.util.Optional;

public class Service {

    private static Service service;

    DataBase dataBase = DataBase.getInstance();

    public static Service getService() {
        if (service == null) {
            service = new Service();
        }
        return service;
    }

    private Service() {
    }

    public Optional<User> login(String phoneNum, String password) {

        Optional<User> optionalUser = dataBase.getUserByPhoneNum(phoneNum);

        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }

        User user = optionalUser.get();
        if (user.getPassword().equals(password)) {
            currentId = user.getId();
            return Optional.of(user);
        }

        return Optional.empty();
    }

    public boolean registration(String name, String phoneNum, String pass) {
        ArrayList<User> users = dataBase.getUsers();
        for (User user : users) {
            if (user.getPhoneNum().equals(phoneNum)) {
                return false;
            }
        }
        User user = new User(UserId(), name, phoneNum, pass, Role.User);
        if (dataBase.createUser(user)) {
            currentId = user.getId();
            return true;
        }
        return false;
    }

    public ArrayList<UserDTO> getUsers() {
        ArrayList<UserDTO> users = new ArrayList<>();
        for (User user : dataBase.getUsers()) {
            users.add(mapper(user));
        }
        return users;
    }

    public ArrayList<StadiumDTO> getStadiums() {
        ArrayList<StadiumDTO> stadiums = new ArrayList<>();
        for (Stadium stadium : dataBase.getStadiums()) {
            stadiums.add(mapper(stadium));
        }
        return stadiums;
    }

    public ArrayList<StadiumDTO> getOneStadiums() {
        ArrayList<StadiumDTO> stadiums = new ArrayList<>();
        for (Stadium stadium : dataBase.getStadiums()) {
            if (stadium.getTime().equals(Time.SLOT1)) {
                stadiums.add(mapper(stadium));
            }
        }
        return stadiums;
    }


    public ArrayList<StadiumDTO> getEmptyStadiums() {
        ArrayList<StadiumDTO> stadiums = getStadiums();
        for (Booking booking : dataBase.getBookings()) {

            for (int i=0; i<stadiums.size(); i++) {
                if (stadiums.get(i).id()==booking.getStadiumId() && stadiums.get(i).time().equals(booking.getTime())) {
                    stadiums.remove(i);
                }
            }
        }
        return stadiums;
    }

    public ArrayList<StadiumDTO> getEmptyTimes(int id) {
        ArrayList<StadiumDTO> stadiums = new ArrayList<>();
        for (StadiumDTO stadium : getEmptyStadiums()) {
            if (stadium.id() == id) {
                stadiums.add(stadium);
            }
        }
        return stadiums;
    }

    public boolean createBooking(int stadiumId, Time time) {
        Booking booking = new Booking(BookingId(), currentId, stadiumId, time);
        return dataBase.createBooking(booking);
    }

    public ArrayList<StadiumDTO> getEmptyStadiumsByTime(Time time) {
        ArrayList<StadiumDTO> stadiums = new ArrayList<>();
        for (StadiumDTO stadium : getEmptyStadiums()) {
            if (stadium.time().equals(time)) {
                stadiums.add(stadium);
            }
        }
        return stadiums;
    }

    public ArrayList<StadiumDTO> myBookings() {
        ArrayList<StadiumDTO> stadiums = new ArrayList<>();
        for (Booking booking : dataBase.getBookings()) {
            if (booking.getUserId() == currentId) {
                Optional<Stadium> optionalStadium = dataBase.getStadiumById(booking.getStadiumId(), booking.getTime());
                optionalStadium.ifPresent(stadium -> stadiums.add(mapper(stadium)));
            }
        }
        return stadiums;
    }

    public boolean deleteBooking(int id) {
        return dataBase.deleteBooking(id);
    }


    public boolean createStadium(String name, String category, String location, double price) {
        Stadium stadium = new Stadium(StadiumId(), name, category, location, price, Time.SLOT1);
        dataBase.createStadiums(stadium);
        stadium.setTime(Time.SLOT2);
        dataBase.createStadiums(stadium);
        stadium.setTime(Time.SLOT3);
        dataBase.createStadiums(stadium);
        stadium.setTime(Time.SLOT4);
        dataBase.createStadiums(stadium);
        return true;
    }

    public boolean updateStadium(int id, String name, String category, String location, double price) {
        if (!dataBase.deleteStadium(id)) {
            return false;
        }
        Stadium stadium = new Stadium(id, name, category, location, price, Time.SLOT1);
        dataBase.createStadiums(stadium);
        stadium.setTime(Time.SLOT2);
        dataBase.createStadiums(stadium);
        stadium.setTime(Time.SLOT3);
        dataBase.createStadiums(stadium);
        stadium.setTime(Time.SLOT4);
        dataBase.createStadiums(stadium);
        return true;
    }

    public boolean deleteStadium(int id) {
        return dataBase.deleteStadium(id);
    }

    public boolean deleteUser(int id) {
        return dataBase.deleteUser(id);
    }


}
