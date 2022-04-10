package racing_team;

import racing_team.entities.MenuUser;
import racing_team.services.MenuService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {
        MenuUser racingMenuUser = new MenuUser(1111, "SDA");
        MenuService service = new MenuService("SDA", 1);
        service.showMenu(racingMenuUser);
    }
}
