package Controller;

import Entity.Role;
import Entity.User;
import Service.Service;
import Util.Util;

import java.util.Optional;

public class MainController {

    Service service = Service.getService();
    AdminController adminController = AdminController.getAdminController();
    UserController userController = UserController.getUserController();

    public void MainMenu() {
        System.out.println("==== FES (Find Empty Stadium) ====");
        while (true) {
            System.out.println("""
                    1.Log in
                    2.Registration
                    0.Exit""");
            int menu = Util.inputInt("Chose one");
            switch (menu) {
                case 1 -> login();
                case 2 -> registration();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void login() {
        String phoneNum = Util.inputStr("Phone number");
        String password = Util.inputStr("Password");
        Optional<User> optionalUser = service.login(phoneNum, password);
        if (optionalUser.isEmpty()) {
            System.out.println("Invalid username or password");
            return;
        }
        User user = optionalUser.get();
        switch (user.getRole()) {
            case Role.Admin -> {
                System.out.println("Successful!");
                adminController.adminPage();
            }
            case Role.User -> {
                System.out.println("Successful!");
                userController.userPage();
            }
        }
    }

    private void registration() {
        String name = Util.inputStr("Name");
        String phoneNum = Util.inputStr("Phone number");
        String pass = Util.inputStr("Password");
        if (service.registration(name, phoneNum, pass)) {
            System.out.println("Successful!");
            userController.userPage();
        } else {
            System.out.println("Error!");
        }

    }
}
