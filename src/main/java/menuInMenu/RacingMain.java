package menuInMenu;

import racing_team.RacingDataService;
import racing_team.RacingDataUser;

public class RacingMain {

    public static void main(String[] args) {
        RacingDataUser racingDataUser = new RacingDataUser(2222, "SDA");
        racing_team.RacingDataService service = new RacingDataService("SDA", 1);
        service.showMenu(racingDataUser);


    }
}
