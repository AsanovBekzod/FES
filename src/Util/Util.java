package Util;

import Entity.Booking;
import Entity.Stadium;
import Entity.Time;
import Entity.User;
import For_DTO.BookingDTO;
import For_DTO.StadiumDTO;
import For_DTO.UserDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Util {


    public static int currentId;

    static Scanner setInt = new Scanner(System.in);
    static Scanner setDouble = new Scanner(System.in);
    static Scanner setStr = new Scanner(System.in);

    static Path usersId = Path.of("src/DataBase/usersID.txt");
    static Path stadiumsID = Path.of("src/DataBase/stadiumsID.txt");
    static Path bookingsID = Path.of("src/DataBase/bookingsID.txt");

    public static int UserId() {
        int userId=0;
        try {
            userId = Integer.parseInt(Files.readString(usersId));
        } catch (IOException _) {
        }
        userId += 3;

        try {
            Files.writeString(usersId, Integer.toString(userId));
        } catch (IOException _) {}

        return userId;
    }

    public static int StadiumId() {
        int stadiumId=0;
        try {
            stadiumId = Integer.parseInt(Files.readString(stadiumsID));
        }  catch (IOException _) {}

        stadiumId += 3;

        try {
            Files.writeString(stadiumsID, Integer.toString(stadiumId));
        } catch (IOException _) {}

        return stadiumId;
    }

    public static int BookingId() {
        int bookingId=0;
        try {
            bookingId = Integer.parseInt(Files.readString(bookingsID));
        } catch (IOException _){}

        bookingId += 3;

        try {
            Files.writeString(bookingsID, Integer.toString(bookingId));
        }  catch (IOException _) {}

        return bookingId;
    }


    public static int inputInt(String text) {
        while (true) {
            System.out.print(text + ": ");
            if (setInt.hasNextInt()) {
                return setInt.nextInt();
            }
            setInt.nextLine();
        }
    }

    public static double inputDouble(String text) {
        while (true) {
            System.out.print(text + ": ");
            if (setDouble.hasNextDouble()) {
                return setDouble.nextDouble();
            }
            setDouble.nextLine();
        }
    }

    public static String inputStr(String text) {
        System.out.print(text + ": ");
        return setStr.nextLine();
    }


    public static UserDTO mapper(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getPhoneNum(), user.getPassword(), user.getRole());
    }

    public static StadiumDTO mapper(Stadium stadium) {
        return new StadiumDTO(stadium.getId(), stadium.getName(), stadium.getCategory(), stadium.getLocation(), stadium.getPrice(), stadium.getTime());
    }

    public static Stadium mapper(StadiumDTO stadium) {
        return new Stadium(stadium.id(), stadium.name(), stadium.category(), stadium.location(), stadium.price(), stadium.time());
    }


    public static BookingDTO mapper(Booking booking) {
        return new BookingDTO(booking.getId(), booking.getUserId(), booking.getStadiumId(), booking.getTime());
    }
}
