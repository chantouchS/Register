package database;

import java.sql.*;

public class AccountDB {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static int[] checkYAS(){
        int[] check = new int[2];
        try{
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select * from Account";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    int yearDB = resultSet.getInt("Year");
                    int semesterDB = resultSet.getInt("Semester");
                    check[0] = yearDB;
                    check[1] = semesterDB;
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }
}
