package racing_team;

import java.sql.SQLException;

public class RacingMain {

    public static void main(String[] args) throws SQLException {
        RacingMenuUser racingMenuUser = new RacingMenuUser(1111, "SDA");
        RacingMenuService service = new RacingMenuService("SDA", 1);
        service.showMenu(racingMenuUser);
    }
}
