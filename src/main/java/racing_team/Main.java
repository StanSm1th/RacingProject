package racing_team;

import racing_team.services.MenuService;

public class Main {

    public static void main(String[] args) throws Exception {
       // MenuUser racingMenuUser = new MenuUser(1111, "SDA");
        MenuService service = new MenuService("");
        service.showMenu();

    }
}
