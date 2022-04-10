package racing_team.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import racing_team.entities.Car;
import racing_team.entities.Driver;
import racing_team.entities.MenuUser;
import racing_team.repositories.CarRepository;
import racing_team.repositories.DriverRepository;
//import racing_team.toJSON.DataBaseToJson;
import racing_team.toJSON.DataBaseToJson;
import racing_team.utils.HibernateUtil;

import javax.persistence.Entity;
import java.util.Scanner;

@Data
@AllArgsConstructor
@Entity
public class MenuService {
    public static final CarRepository racingCarRepository = new CarRepository();
    public static final DriverRepository racingDriverRepository = new DriverRepository();
    private static final Scanner scanner = new Scanner(System.in);


    final String username = "root";
    final String password = "root";
    final String jdbcURL = "jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC";

    private static Session session = HibernateUtil.getSessionFactory().openSession();


    private String userName;
    private int userId;


    public void showMenu(MenuUser racingDataUser) throws Exception {

        boolean isAuthenticated = authenticate(racingDataUser);

        if (isAuthenticated) {
            char selection;
            printGreeting();

            do {
                printMenuSelection();
                selection = printChoiceRequest();

                switch (selection) {
                    case 'A':

                        RandomDataLoader.uploadRandomCarData(username, password, jdbcURL);
                        RandomDataLoader.uploadRandomDriverData(username, password, jdbcURL);
                        break;

                    case 'B':

                        findRacingDriverById();
                        break;

                    case 'C':
                        addPresetDriver();
                        break;



                    case 'D':
                        System.out.println("Enter drivers ID who you wish to delete: ");
                        Driver racingDriverById = racingDriverRepository.findDriverById(scanner.nextInt());
                        racingDriverRepository.deleteDriverByID(racingDriverById);
                        break;

                    case 'E':
                        printSubMenuAddNew();
                        char choice = scanner.next().charAt(0);

                        switch (choice) {
                            case 'A':
                                System.out.println("Add new driver: ");
                                addNewRacingDriver();
                                break;

                            case 'B':
                                System.out.println("Add new racing car: ");
                                addNewRacingCar();
                                break;
                            case 'C':
                                System.out.println("data to json");
                                DataBaseToJson.RetrieveData();
                                break;

                            case 'D':
                                System.out.println("read data");
                                //DataBaseToJson.readJsonSimpleDemo();
                                break;

                            case 'X':
                                System.out.println("Start again");
                                // nezinau tiksliai del sito
                                break;



                            default:
                                System.out.println("Your choice is invalid!");
                                System.out.println("Choose from available options and use CAPITAL letters: ");
                        }
                        break;

                    case 'Z':
                        System.out.println("Thank you, have a good day!");
                        break;

                    default:
                        System.out.println("Your choice is invalid!");
                        System.out.println("Choose from available options and use CAPITAL letters: ");
                }
            } while (selection != 'Z');
        }
    }

    private void addPresetDriver() {
        System.out.println();
        Car stanCar = Car.builder()
                .carMake("audi").carModel("A4").carModelYear(2010).topSpeed(255).price(630000).build();
        Driver stan = Driver.builder()
                .name("Stan").surname("Smith").dateOfBirth("07/07/1997").nationality("American")
                .sponsor("Nike").salary(15000).racingCar(stanCar).build();
        racingDriverRepository.saveDriver(stan);
        System.out.println();
    }

    private void addNewRacingCar() {
        Car newRacingCar = new Car();
        System.out.println("Enter new racing cars make:");
        newRacingCar.setCarMake(scanner.next());
        System.out.println("Enter new racing cars model: ");
        newRacingCar.setCarModel(scanner.next());
        System.out.println("Enter new racing cars year of model make: ");
        newRacingCar.setCarModelYear(scanner.nextInt());
        System.out.println("Enter new racing cars top speed (/mph): ");
        newRacingCar.setTopSpeed(scanner.nextInt());
        System.out.println("Enter new racing cars price (USD $): ");
        newRacingCar.setPrice(scanner.nextInt());
        racingCarRepository.saveCar(newRacingCar);
    }

    private void findRacingDriverById() {
        System.out.println("Enter drivers ID : ");
        int driverId = scanner.nextInt();
        Driver racingDriverById = racingDriverRepository.findDriverById(driverId);
        System.out.println("Driver found by ID: \n" + "Drivers id: " + racingDriverById.getDriverId()
                + ", full name: " + racingDriverById.getName() + " " + racingDriverById.getSurname());
        System.out.println();
    }


    private void addNewRacingDriver() {
        Driver newRacingDriver = new Driver();
        System.out.println();
        System.out.println("Enter new drivers name: ");
        newRacingDriver.setName(scanner.next());
        System.out.println("Enter new drivers surname: ");
        newRacingDriver.setSurname(scanner.next());
        System.out.println("Enter new drivers date of birth (dd/mm/yyyy): ");
        newRacingDriver.setDateOfBirth(scanner.next());
        System.out.println("Enter new drivers outfit nationality (country): ");
        newRacingDriver.setNationality(scanner.next());
        System.out.println("Enter new drivers sponsor: ");
        newRacingDriver.setSponsor(scanner.next());
        System.out.println("Enter new drivers salary: ");
        newRacingDriver.setSalary(scanner.nextInt());

        Car newRacingCar = new Car();
        System.out.println("Enter new racing cars make:");
        newRacingCar.setCarMake(scanner.next());
        System.out.println("Enter new racing cars model: ");
        newRacingCar.setCarModel(scanner.next());
        System.out.println("Enter new racing cars year of model make: ");
        newRacingCar.setCarModelYear(scanner.nextInt());
        System.out.println("Enter new racing cars top speed (/mph): ");
        newRacingCar.setTopSpeed(scanner.nextInt());
        System.out.println("Enter new racing cars price (USD $): ");
        newRacingCar.setPrice(scanner.nextInt());
        racingCarRepository.saveCar(newRacingCar);

        newRacingDriver.setRacingCar(newRacingCar);
        racingDriverRepository.saveDriver(newRacingDriver);
    }

    private void printSubMenuAddNew() {
        System.out.println("A. add new driver:");
        System.out.println("B. add new car");
        System.out.println("C: data to JSON: ");
        System.out.println("D: read data: ");
        System.out.println("X. back to main :");

    }

    private char printChoiceRequest() {
        char selection;
        System.out.println("____________________________");
        System.out.println("Choose action to perform");
        System.out.println("____________________________");
        selection = scanner.next().charAt(0);
        System.out.println();
        return selection;
    }

    private void printMenuSelection() {
        System.out.println("A. Upload random cars and drivers : ");
        System.out.println("B. Find driver by ID: ");
        System.out.println("C. Add preset driver : ");

        System.out.println("D. Choose driver to delete : ");
        System.out.println("E. Add new...");
        System.out.println("Z. Exit");
    }

    private void printGreeting() {
        System.out.println("+----------------------------+");
        System.out.println("|    WELCOME TO RACING!      |");
        System.out.println("+----------------------------+");
        System.out.println("Your user ID is: " + userId);
        System.out.println("Your username is: " + userName);
        System.out.println();
    }

    private boolean authenticate(MenuUser racingDataUser) {
        System.out.println("Enter password:");
        Scanner scanner = new Scanner(System.in);
        int password = scanner.nextInt();
        return password == racingDataUser.getUserPassword();
    }


}