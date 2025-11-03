package Controller;

import For_DTO.UserDTO;
import Service.Service;

import static Util.Util.*;

public class AdminController {

    private static AdminController adminController;

    public static AdminController getAdminController() {
        if (adminController == null) {
            return new AdminController();
        }
        return adminController;
    }

    private AdminController() {
    }


    Service service = Service.getService();
    UserController userController = UserController.getUserController();


    public void adminPage() {
        while(true){
            System.out.println("""
                        \n==== Control Menu ====
                    1.Add Stadium
                    2.Show Stadiums
                    3.Update Stadium
                    4.Delete Stadium
                    5.Show User
                    0.Exit""");
            int menu = inputInt("Choose one");
            switch (menu) {
                case 1 -> addStadium();
                case 2 ->showStadiums();
                case 3 -> updateStadium();
                case 4 -> deleteStadium();
                case 5 -> showUsers();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void showStadiums() {
        System.out.println("\n  All Stadiums");

        if (service.getStadiums().isEmpty()) {
            System.out.println("No Stadiums found");
            return;
        }

        userController.printStadiums(service.getOneStadiums());
    }

    private void addStadium() {

        System.out.println("\n  Create Stadium");
        String name = inputStr("Enter name");
        String category = inputStr("Enter category");
        String location = inputStr("Enter location");
        double price = inputDouble("Enter price");

        if (service.createStadium(name, category, location, price)) {
            System.out.println("Stadium created");
        } else {
            System.out.println("Stadium not created");
        }
    }

    private void updateStadium() {

        showStadiums();

        int id = inputInt("Choose stadiumID for update( 0-Exit )");
        if (id==0) {
            return;
        }
        String name = inputStr("Enter name");
        String category = inputStr("Enter category");
        String location = inputStr("Enter location");
        double price = inputDouble("Enter price");

        if (service.updateStadium(id,name,category,location,price)) {
            System.out.println("Stadium updated");
        }  else {
            System.out.println("Stadium not updated");
        }

    }

    private void deleteStadium() {

        showStadiums();

        int id = inputInt("Choose stadiumID for delete( 0-Exit )");
        if (id==0) {
            return;
        }
        if (service.deleteStadium(id)) {
            System.out.println("Stadium deleted");
        } else  {
            System.out.println("Stadium not deleted");
        }
    }

    private void showUsers() {
        System.out.println("\n  All Users");

        if (service.getUsers().isEmpty()) {
            System.out.println("No users found");
            return;
        }

        for (UserDTO user : service.getUsers()) {
            System.out.println("---------------------------");
            System.out.printf("ID: %d, Name: %s\nPhoneNumber: %s\nPassword: %s\nRole: %s\n",user.id(),user.name(),user.phoneNum(),user.password(),user.role());
        }
            System.out.println("---------------------------");

        int id = inputInt("Choose userID for delete(0-Exit )");
        if (service.deleteUser(id)) {
            System.out.println("User deleted");
        } else   {
            System.out.println("User not deleted");
        }
    }


}
