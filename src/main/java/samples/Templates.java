//package samples;
//
//import org.json.simple.JSONObject;
//import racing_team.RacingDatabaseQueries;
//
//import java.sql.PreparedStatement;
//
//public class Templates {
//
//    connection.createStatement().getConnection();
//    PreparedStatement preparedStatement = connection.prepareStatement(RacingDatabaseQueries.INSERT_SAMPLE_RACING_DRIVERS);
//                for (Object object : jsonArray) {
//        JSONObject record = (JSONObject) object;
//        int id = Integer.parseInt((String) record.get("id"));
//        int age = Integer.parseInt((String) record.get("age"));
//        String name = (String) record.get("name");
//        String nationality = (String) record.get("nationality");
//        String outfitColor = (String) record.get("outfitColor");
//        int salary = Integer.parseInt((String) record.get("salary"));
//        preparedStatement.setInt(1, id);
//        preparedStatement.setInt(2, age);
//        preparedStatement.setString(3, name);
//        preparedStatement.setString(4, nationality);
//        preparedStatement.setString(5, outfitColor);
//        preparedStatement.setInt(6, salary);
//
//    }
//
//
//
//}
