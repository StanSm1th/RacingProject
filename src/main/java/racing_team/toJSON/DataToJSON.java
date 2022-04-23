package racing_team.toJSON;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataToJSON {

   public static void RetrieveData() throws Exception {


      //Registering the Driver
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
      //Getting the connection
      String mysqlUrl = "jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC";
      Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
      System.out.println("Connection established......");
      //Creating the Statement
      Statement stmt = con.createStatement();
      //Retrieving the records
      ResultSet rs = stmt.executeQuery("SELECT * FROM hibernate.driver");
      JSONObject jsonObject = new JSONObject();
      //Creating a json array
      JSONArray array = new JSONArray();
      //ResultSet rs = RetrieveData();
      //Inserting ResultSet data into the json object
      while(rs.next()) {
         JSONObject record = new JSONObject();
         //Inserting key-value pairs into the json object
         record.put("driverId", rs.getInt("driverId"));
         record.put("dateOfBirth", rs.getString("dateOfBirth"));
         record.put("name", rs.getString("name"));
         record.put("nationality", rs.getString("nationality"));
         record.put("salary", rs.getInt("salary"));
         record.put("sponsor", rs.getString("sponsor"));
         record.put("surname", rs.getString("surname"));
         record.put("carId", rs.getInt("carId"));
         record.put("team_teamId", rs.getInt("team_teamId"));

         array.add(record);
      }
      jsonObject.put("Drivers_data", array);
      try {
         FileWriter file = new FileWriter("src/main/resources/DBoutput.json");
         file.write(jsonObject.toJSONString());
         file.close();
      } catch (IOException exception) {
         // TODO Auto-generated catch block
         exception.printStackTrace();
      }

      System.out.println("JSON file created......");

   }


}