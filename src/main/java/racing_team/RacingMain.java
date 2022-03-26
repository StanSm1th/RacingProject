package racing_team;

public class RacingMain {

    public static void main(String[] args) {
        RacingDataUser racingDataUser = new RacingDataUser(1111, "SDA");
        RacingDataService service = new RacingDataService("SDA", 1);
        service.showMenu(racingDataUser);


    }
}
