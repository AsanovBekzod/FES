package Controller;

import Entity.Time;
import For_DTO.StadiumDTO;
import Service.Service;

import java.util.ArrayList;
import java.util.Objects;

import static Util.Util.*;

public class UserController {

    private static UserController userController;

    public static UserController getUserController() {
        if (userController == null) {
            return new UserController();
        }
        return userController;
    }

    private UserController() {
    }

    Service service = Service.getService();

    public void userPage() {
        while (true) {
            System.out.println("\n    ==== MainMenu ====");
            System.out.println("""
                    1.Show stadiums
                    2.Booking stadium
                    3.Show empty stadiums by time
                    4.Show my bookings
                    0.Exit""");
            int menu = inputInt("Chose one");
            switch (menu) {
                case 1 -> showStadiums();
                case 2 -> bookingStadium();
                case 3 -> showEmptyByTime();
                case 4 -> showMyBookings();
                case 0 -> {
                    return;
                }
            }
        }
    }


    private void showStadiums() {
        System.out.println("\n  All stadiums:");
        printStadiums(service.getOneStadiums());
    }

    private void bookingStadium() {
        System.out.println("\n  All stadiums:");
        printStadiums(service.getOneStadiums());
        if (service.getOneStadiums().isEmpty()) {
            System.out.println("No stadiums found");
            return;
        }
        int id = inputInt("Booking by stadium ID( 0-Exit )");
        if (id == 0) {
            return;
        }
        ArrayList<StadiumDTO> stadiums = service.getEmptyTimes(id);
        System.out.println("Free times:");
        if (stadiums.isEmpty()) {
            System.out.println("This stadium not free time");
            return;
        }
        for (int i = 0; i < stadiums.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, stadiums.get(i).time());
        }
        int index = inputInt("Chose time( 0-Exit )");
        if (index <= 0) {
            return;
        }

        if (index >= stadiums.size() ) {
            System.out.println("Error StadiumID");
            return;
        }
        System.out.println(service.createBooking(id, stadiums.get(index - 1).time()) ? "Booking created" : "Error");
    }

    private void showEmptyByTime() {
        while (true) {
            System.out.printf("\nTimetable:\n1.%s\n2.%s\n3.%s\n4.%s\n0-Exit", Time.SLOT1, Time.SLOT2, Time.SLOT3, Time.SLOT4);
            int menu = inputInt("Chose time");
            switch (menu) {
                case 1 -> stadiumsByTime(Time.SLOT1);
                case 2 -> stadiumsByTime(Time.SLOT2);
                case 3 -> stadiumsByTime(Time.SLOT3);
                case 4 -> stadiumsByTime(Time.SLOT4);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void stadiumsByTime(Time time) {
        System.out.println("\nEmpty stadiums by this time:");
        printStadiums(service.getEmptyStadiumsByTime(time));
        if (service.getEmptyStadiumsByTime(time).isEmpty()) {
            return;
        }
        int id = inputInt("Booking by stadium ID( 0-Exit )");
        if (id == 0) {
            return;
        }
        System.out.println(service.createBooking(id, time) ? "Booking created" : "Error");
    }

    private void showMyBookings() {
        System.out.println("\n  My bookings:");
        ArrayList<StadiumDTO> stadiumDTOS = service.myBookings();
        if (stadiumDTOS.isEmpty()) {
            System.out.println("You are not bookings!");
            return;
        }
        for (StadiumDTO stadium : stadiumDTOS) {
            System.out.println("-----------------------------");
            System.out.printf("Time: %s\nID: %d  Name: %s\nCategory: %s\nPrice: %.2f\nLocation: %s\n", stadium.time(), stadium.id(), stadium.name(), stadium.category(), stadium.price(), stadium.location());

        }
            System.out.println("-----------------------------");
        int id = inputInt("Chose BookingID for cancel Booking( 0-Exit )");
        if (id == 0) {
            return;
        }
        if (service.deleteBooking(id)) {
            System.out.println("Canceled booking");
        } else {
            System.out.println("Error while deleting booking");
        }

    }


    public void printStadiums(ArrayList<StadiumDTO> stadiums) {
        if (stadiums.isEmpty()) {
            System.out.println("No stadiums!");
            return;
        }
        for (StadiumDTO stadium : stadiums) {
            System.out.println("-----------------------------");
            System.out.printf("ID: %d  Name: %s\nCategory: %s\nPrice: %.2f\nLocation: %s\n", stadium.id(), stadium.name(), stadium.category(), stadium.price(), stadium.location());
        }
        System.out.println("-----------------------------");
    }

}
