package racing_team.utils;

public class DataQueries {

    public static final String INSERT_SAMPLE_DRIVERS =
            "INSERT INTO driver (dateOfBirth,name,surname,nationality,sponsor,salary,carId) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_SAMPLE_CARS =
            "INSERT INTO car (carMake,carModel,carModelYear,topSpeed,price)" +
                    " values (?, ?, ?, ?, ?)";
    public static final String INSERT_SAMPLE_TEAMS =
            "INSERT INTO team (budget,numberOfWins,teamName)" +
                    " values (?, ?, ?)";


    public static final String SELECT_ALL_DRIVERS_AND_CARS =
    "SELECT driverId, name, surname, dateOfBirth as 'date of birth', nationality, sponsor, salary," +
            " carMake, carModel, carModelYear, topSpeed, price FROM hibernate.driver JOIN hibernate.car " +
            "ON hibernate.driver.carId = hibernate.car.carId;";

}

