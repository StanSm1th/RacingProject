package racing_team;

import java.sql.SQLException;

public class RacingMain {

    public static void main(String[] args) throws SQLException {
        RacingDataUser racingDataUser = new RacingDataUser(1111, "SDA");
        RacingDataService service = new RacingDataService("SDA", 1);
        service.showMenu(racingDataUser);
    }
}
