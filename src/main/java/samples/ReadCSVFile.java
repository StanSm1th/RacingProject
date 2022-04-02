package samples;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

import static java.lang.Integer.parseInt;

public class ReadCSVFile {

    public static void main(String[] args) {

        final String username = "root";
        final String password = "root";
        final String jdbcURL = "jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC";


        uploadRandomRacingDriverData(username, password, jdbcURL);


    }

    private static void uploadRandomRacingDriverData(String username, String password, String jdbcURL) {
        try {
            int batchSize = 20;
            Connection connection = null;
            String filePath = "src/main/resources/randomDrivers.csv";

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);


            String sql = "INSERT INTO racingdriver (driverId, age, name, nationality, outfitColor, salary) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            int count = 0;

            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");

                String driverId = data[0];
                String age = data[1];
                String name = data[2];
                String nationality = data[3];
                String outfitColor = data[4];
                String salary = data[5];


                statement.setInt(1, parseInt(driverId));
                statement.setInt(2, parseInt(age));
                statement.setString(3, name);
                statement.setString(4, nationality);
                statement.setString(5, outfitColor);
                statement.setInt(6, parseInt(salary));
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

