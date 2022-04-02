package menuInMenu;

import lombok.AllArgsConstructor;
import lombok.Data;
import racing_team.RacingCar;
import racing_team.RacingDataUser;
import racing_team.RacingDriver;
import racing_team.RacingDriverRepository;

import javax.persistence.Entity;
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
                        char selection1;

                        System.out.println("A. add new driver:");
                        System.out.println("B. add new car:");
                        System.out.println("C. add new team:");
                        System.out.println("D. back to main :");

                        selection1 = printChoiceRequest(scanner);



                        switch (selection1) {

                            case 'A':
                                System.out.println("add new driver");
                                break;

                            case 'D':
                                printMenuSelection();
                        }

                        break;

                    case 'B':
                        System.out.println("xwwxw");
                        break;

                    case 'C':

                        System.out.println("bbxcwwbbwjwjw");
                        break;

                    case 'D':
                        System.out.println("Enter drivers ID which you wish to delete: ");

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
        System.out.println("A. Add new  : ");
        System.out.println("B. Find driver by ID: ");
        System.out.println("C. Add preset driver : ");
        System.out.println("D. Choose driver to delete : ");
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
