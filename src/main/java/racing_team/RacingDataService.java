package racing_team;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Scanner;

@Data
@AllArgsConstructor
@Entity
public class RacingDataService {

  //  public static final Session OPEN_SESSION = HibernateUtil.getSessionFactory().openSession();
    public static final RacingDriverRepository RACING_DRIVER_REPOSITORY = new RacingDriverRepository();
    private String userName;
    private int userId;

    public void showMenu(RacingDataUser racingDataUser) {

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

                        RacingDriver newRacingDriver = new RacingDriver();
                        System.out.println();
//                        System.out.println("Enter new drivers ID: ");
//                        newRacingDriver.setDriverId(scanner.nextInt());
                        System.out.println("Enter new drivers name: ");
                        newRacingDriver.setName(scanner.next());
                        System.out.println("Enter new drivers age: ");
                        newRacingDriver.setAge(scanner.nextInt());
                        System.out.println("Enter new drivers nationality: ");
                        newRacingDriver.setNationality(scanner.next());
                        System.out.println("Enter new drivers outfit color: ");
                        newRacingDriver.setOutfitColor(scanner.next());
                        System.out.println("Enter new drivers salary: ");
                        newRacingDriver.setSalary(scanner.nextLong());
                        RACING_DRIVER_REPOSITORY.saveDriver(newRacingDriver);

                        break;
                    case 'B':
                        System.out.println("Choose driver to delete: ");

                        RacingDriver driverToDelete = new RacingDriver();
                        scanner.nextInt();
                        driverToDelete.getDriverId();
                        RACING_DRIVER_REPOSITORY.deleteDriver(driverToDelete);


                        break;

                    case 'C':

                        System.out.println();
                        RacingCar stanCar = RacingCar.builder()
                                .brand("audi").color("black").topSpeed(235).price(36000).build();
                        RacingDriver stan = RacingDriver.builder()
                                .name("Stan Smith").age(25).nationality("American")
                                .outfitColor("red").salary(25000L).racingCar(stanCar).build();
//                        OPEN_SESSION.beginTransaction();
//                        OPEN_SESSION.save(stan);
//                        OPEN_SESSION.getTransaction().commit();
//                        System.out.println("latest driver and car entry: ");
//                        System.out.println(stan);
                        RACING_DRIVER_REPOSITORY.saveDriver(stan);

                        System.out.println();


                        break;

                    case 'D':



                        break;

                    case 'E':
                        System.out.println("Thank you, have a good day!");
                        break;

                    default:
                        System.out.println("Your choice is invalid!");
                        System.out.println("Choose from available options and use CAPITAL letters: ");
                        break;


                }


            } while (selection != 'E');

        }
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
        System.out.println("B. Choose driver to delete : ");
        System.out.println("C. Add preset driver : ");
        System.out.println("D. ---------------: ");
        System.out.println("E. Exit");
    }

    private void printGreeting() {
        System.out.println("Welcome to Racing!");
        System.out.println("Your user ID is: " + userId);
        System.out.println("Your username is: " + userName);
        System.out.println();
    }

    private boolean authenticate(RacingDataUser racingDataUser) {
        System.out.println("Enter password:");
        Scanner scanner = new Scanner(System.in);
        int pinCode = scanner.nextInt();
        return pinCode == racingDataUser.getUserPassword();
    }


}
