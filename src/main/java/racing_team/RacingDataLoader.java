package racing_team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.lang.Integer.parseInt;

public class RacingDataLoader {
    public static void uploadRandomRacingDriverData(String username, String password, String jdbcURL) {
        try {
            int batchSize = 20;
            Connection connection = null;
            String filePath = "src/main/resources/randomDrivers.csv";

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);


            String sql = "INSERT INTO racingdriver (driverId, dateOfBirth, name, surname, nationality, sponsor, salary) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            int count = 0;

            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");

                String driverId = data[0];
                String dateOfBirth = data[1];
                String name = data[2];
                String surname = data[3];
                String nationality = data[4];
                String sponsor = data[5];
                String salary = data[6];


                statement.setInt(1, parseInt(driverId));
                statement.setString(2, dateOfBirth);
                statement.setString(3, name);
                statement.setString(4, surname);
                statement.setString(5, nationality);
                statement.setString(6, sponsor);
                statement.setInt(7, parseInt(salary));
                statement.addBatch();
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Data has been inserted");


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
