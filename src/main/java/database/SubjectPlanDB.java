package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import model.SubjectPlan;

import java.sql.*;

public class SubjectPlanDB {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static void saveWantToStudy(String courseID, String courseTitle, String preCourse, int year, int semester, int credit, String difficult, String duoCourse){
        try{
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "insert into SubjectPlan (CourseID,CourseTitle,PreCourse,Year,Semester,Credit,Difficult,WithCourseID) values " +
                        "('" + courseID + "' , '" + courseTitle + "' , '" + preCourse + "' , '" + year + "' , '" + semester + "' , '" + credit + "' , '" + difficult + "' , '" + duoCourse + "')";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList getAllSubject(){
        ObservableList<SubjectPlan> observableList = FXCollections.observableArrayList();
        try{
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select * from SubjectPlan";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String courseID = resultSet.getString("CourseID");
                    String courseTitle = resultSet.getString("CourseTitle");
                    String preCourse = resultSet.getString("PreCourse");
                    int year = resultSet.getInt("Year");
                    int semester = resultSet.getInt("Semester");
                    int credit = resultSet.getInt("Credit");
                    String difficult = resultSet.getString("Difficult");
                    String withCourseID = resultSet.getString("WithCourseID");
                    observableList.add(new SubjectPlan(courseID,courseTitle,preCourse,year,semester,credit,difficult,withCourseID));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }
    public static boolean getCheckCourseID(String courseID){
        boolean check = true;
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select CourseID from SubjectPlan";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String dbCourseID = resultSet.getString("CourseID");
                    if (dbCourseID.equals(courseID)){
                        check = false;
                    }
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
    public static void deleteSubjectPlan(String courseID){
        try{
            Class.forName(dbName);
            Connection connection = DriverManager .getConnection(dbURL);
            if(connection != null){
                String query  = "Delete from SubjectPlan where CourseID == '" + courseID + "'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
