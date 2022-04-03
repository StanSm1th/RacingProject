package racing_team;

import org.hibernate.Session;

import javax.persistence.Entity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

@Entity
public class RacingDataLoader {
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void uploadRandomRacingDriverData(String username, String password, String jdbcURL) {
        try {
            int batchSize = 100;
            Connection connection = null;
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(RacingDatabaseQueries.INSERT_SAMPLE_RACING_DRIVERS);

            BufferedReader driversReader = new BufferedReader(new FileReader("src/main/resources/randomDrivers.csv"));

            String lineText = null;
            int count = 0;
            driversReader.readLine();
            while ((lineText = driversReader.readLine()) != null) {
                String[] data = lineText.split(",");
                //String driverId = data[0];
                String dateOfBirth = data[0];
                String name = data[1];
                String surname = data[2];
                String nationality = data[3];
                String sponsor = data[4];
                String salary = data[5];
                //String carId = data[7];

                //statement.setInt(1, parseInt(driverId));
                statement.setString(1, dateOfBirth);
                statement.setString(2, name);
                statement.setString(3, surname);
                statement.setString(4, nationality);
                statement.setString(5, sponsor);
                statement.setInt(6, parseInt(salary));
                //statement.setInt(8, parseInt(carId));
                statement.addBatch();
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            driversReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Random drivers has been inserted");



        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void uploadRandomRacingCarData(String username, String password, String jdbcURL) {

        try {
            int batchSize = 100;
            Connection connection = null;
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(RacingDatabaseQueries.INSERT_SAMPLE_RACING_CARS);
            BufferedReader carsReader = new BufferedReader(new FileReader("src/main/resources/randomCars.csv"));
            String lineText = null;
            int count = 0;
            carsReader.readLine();
            while ((lineText = carsReader.readLine()) != null) {
                String[] data = lineText.split(",");
                //String carId = data[0];
                String carMake = data[0];
                String carModel = data[1];
                String carModelYear = data[2];
                String topSpeed = data[3];
                String price = data[4];


                //statement.setInt(1, parseInt(carId));
                statement.setString(1, carMake);
                statement.setString(2, carModel);
                statement.setString(3, carModelYear);
                statement.setInt(4, parseInt(topSpeed));
                statement.setInt(5, parseInt(price));
                statement.addBatch();
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            carsReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Random cars has been inserted");


        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }


    }
}
