package racing_team;

public class RacingDatabaseQueries {

    public static final String INSERT_SAMPLE_RACING_DRIVERS =
            "INSERT INTO racingdriver (dateOfBirth,name,surname,nationality,sponsor,salary) values (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_SAMPLE_RACING_CARS =
            "INSERT INTO racingcar (carMake,carModel,carModelYear,topSpeed,price) values (?, ?, ?, ?, ?)";

}

