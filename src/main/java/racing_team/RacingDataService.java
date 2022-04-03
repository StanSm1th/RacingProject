package racing_team;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.sql.SQLException;
import java.util.Scanner;

@Data
@AllArgsConstructor
@Entity
public class RacingDataService {
    public static final RacingCarRepository RACING_CAR_REPOSITORY = new RacingCarRepository();
    final String username = "root";
    final String password = "root";
    final String jdbcURL = "jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC";

    public static final RacingDriverRepository RACING_DRIVER_REPOSITORY = new RacingDriverRepository();
    private String userName;
    private int userId;

    public void showMenu(RacingDataUser racingDataUser) throws SQLException {

        boolean isAuthenticated = authenticate(racingDataUser);

        if (isAuthenticated) {
            char selection;
            Scanner scanner = new Scanner(System.in);
            printGreeting();

            do {
                printMenuSelection();
                selection = printChoiceRequest(scanner);

                switch (selection) {
                    case 'A':

                        RacingDataLoader.uploadRandomRacingDriverData(username, password, jdbcURL);
                        RacingDataLoader.uploadRandomRacingCarData(username, password, jdbcURL);
                        break;

                    case 'B':
                        findRacingDriverById(scanner);
                        break;

                    case 'C':
                        System.out.println();
                        RacingCar stanCar = RacingCar.builder()
                                .carMake("audi").carModel("A4").carModelYear(2010).topSpeed(255).price(630000).build();
                        RacingDriver stan = RacingDriver.builder()
                                .name("Stan").surname("Smith").dateOfBirth("07/07/1997").nationality("American")
                                .sponsor("Nike").salary(15000).racingCar(stanCar).build();
                        RACING_DRIVER_REPOSITORY.saveDriver(stan);
                        System.out.println();
                        break;

                    case 'D':
                        System.out.println("Enter drivers ID who you wish to delete: ");
                        RacingDriver racingDriverById = RACING_DRIVER_REPOSITORY.findDriverById(scanner.nextInt());
                        RACING_DRIVER_REPOSITORY.deleteDriverByID(racingDriverById);
                        break;

                    case 'E':
                        printSubMenuAddNew();
                        char choice = scanner.next().charAt(0);

                        switch (choice) {
                            case 'A':
                                System.out.println("Add new driver: ");
                                addNewRacingDriver(scanner);
                                break;

                            case 'B':
                                System.out.println("Add new racing car: ");
                                addNewRacingCar(scanner);
                                break;

                            case 'D':
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

    private void addNewRacingCar(Scanner scanner) {
        RacingCar newRacingCar = new RacingCar();
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
        RACING_CAR_REPOSITORY.saveCar(newRacingCar);
    }

    private void findRacingDriverById(Scanner scanner) {
        System.out.println("Enter drivers ID : ");
        RacingDriver racingDriverById = RACING_DRIVER_REPOSITORY.findDriverById(scanner.nextInt());
        System.out.println("Driver found by ID: " + racingDriverById);
    }


    private void addNewRacingDriver(Scanner scanner) {
        RacingDriver newRacingDriver = new RacingDriver();
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
        RACING_DRIVER_REPOSITORY.saveDriver(newRacingDriver);
    }

    private void printSubMenuAddNew() {
        System.out.println("A. add new driver:");
        System.out.println("B. add new car");
        System.out.println("D. back to main :");
    }

    private char printChoiceRequest(Scanner scanner) {
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

    private boolean authenticate(RacingDataUser racingDataUser) {
        System.out.println("Enter password:");
        Scanner scanner = new Scanner(System.in);
        int password = scanner.nextInt();
        return password == racingDataUser.getUserPassword();
    }


}
