package racing_team;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.simple.JSONArray;

import javax.persistence.Entity;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

@Data
@AllArgsConstructor
@Entity
public class RacingDataService {

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
                        final String username = "root";
                        final String password = "root";
                        final String jdbcURL = "jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC";
                        RacingDataLoader.uploadRandomRacingDriverData(username, password, jdbcURL);
                        break;

                    case 'B':
                        findRacingDriverById(scanner);
                        break;

                    case 'C':

                        System.out.println();
                        RacingCar stanCar = RacingCar.builder()
                                .brand("audi").color("black").topSpeed(235).price(36000).build();
                        RacingDriver stan = RacingDriver.builder()
                                .name("Stan").surname("Smith").dateOfBirth("25").nationality("American")
                                .sponsor("red").salary(25000).racingCar(stanCar).build();
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
                                System.out.println("add new driver");
                                addNewRacingDriver(scanner);
                                break;
                            case 'B':
                                System.out.println("Add new car");
                                RacingCar racingCar = new RacingCar();


                            case 'D':
                                System.out.println("Start again");
                                // nezinau tiksliai del sito
                                break;

                            default:
                                System.out.println("Your choice is invalid!");
                                System.out.println("Choose from available options and use CAPITAL letters: ");                        }
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

    private void findRacingDriverById(Scanner scanner) {
        System.out.println("Enter drivers ID : ");
        RacingDriver racingDriverById = RACING_DRIVER_REPOSITORY.findDriverById(scanner.nextInt());
        System.out.println("Driver found by ID: " + racingDriverById);
    }


    private void addNewRacingDriver(Scanner scanner) {
        RacingDriver newRacingDriver = new RacingDriver();
        System.out.println();
//                        System.out.println("Enter new drivers ID: ");
//                        newRacingDriver.setDriverId(scanner.nextInt());
        System.out.println("Enter new drivers name: ");
        newRacingDriver.setName(scanner.next());
        System.out.println("Enter new drivers age: ");
        newRacingDriver.setDateOfBirth(scanner.next());
        System.out.println("Enter new drivers nationality: ");
        newRacingDriver.setNationality(scanner.next());
        System.out.println("Enter new drivers outfit color: ");
        newRacingDriver.setSponsor(scanner.next());
        System.out.println("Enter new drivers salary: ");
        newRacingDriver.setSalary(scanner.nextInt());
        RACING_DRIVER_REPOSITORY.saveDriver(newRacingDriver);
    }

    private void printSubMenuAddNew() {
        System.out.println("A. add new driver:");
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
        System.out.println("A. Add new driver : ");
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
