import Controller.MainController;
import DataBase.DataBase;
import Entity.Booking;
import Entity.Time;
import For_DTO.StadiumDTO;
import Service.Service;


public class Main {
    public static void main(String[] args) {

        Service service=Service.getService();

        DataBase base=DataBase.getInstance();

        MainController mainController = new MainController();

        mainController.MainMenu();


    }
}