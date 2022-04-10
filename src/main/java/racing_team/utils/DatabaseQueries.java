package racing_team.utils;

public class DatabaseQueries {

    public static final String INSERT_SAMPLE_DRIVERS =
            "INSERT INTO driver (dateOfBirth,name,surname,nationality,sponsor,salary,carId) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_SAMPLE_CARS =
            "INSERT INTO racingcar (carMake,carModel,carModelYear,topSpeed,price)" +
                    " values (?, ?, ?, ?, ?)";
    public static final String INSERT_SAMPLE_TEAMS =
            "INSERT INTO team (budget,numberOfWins,teamName)" +
                    " values (?, ?, ?)";


    public static final String PRINT_ALL_RACING_CARS_AND_DRIVERS =
    "SELECT driverId, name, surname, teamName, sponsor, salary, carMake, carModel, carModelYear, topSpeed, price\n" +
            " FROM hibernate.driver JOIN hibernate.racingcar ON hibernate.driver.carId = hibernate.racingcar.carId\n" +
            "INNER JOIN hibernate.team ON hibernate.driver.team_teamId = hibernate.team.teamId;";

}

