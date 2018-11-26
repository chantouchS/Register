package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class SubjectDB {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static ArrayList<String> getCourseID(){
        ArrayList<String> courseSet = new ArrayList<>();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select CourseID from Subjects";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    String course = resultSet.getString("CourseID");
                    courseSet.add(course);
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseSet;
    }
    public static void saveSubjects(int semester,int year,String courseID,String courseTitle,int credit,String preCourse,String colorDifficult){
        System.out.println(preCourse);
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "insert into Subjects (Semester,Year,CourseID,CourseTitle,Credit,PreCourse,Difficult) values " +
                        "('" + semester + "' , '" + year + "' , '" + courseID + "' , '" + courseTitle + "' , '" + credit + "' , '" + preCourse + "' , '" + colorDifficult + "')";
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
    public static ObservableList getAllSubjects(){
        ObservableList<Subject> subjectObservableList = FXCollections.observableArrayList();
        try{
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select * from Subjects";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    int semester = resultSet.getInt("Semester");
                    int year = resultSet.getInt("Year");
                    String courseID = resultSet.getString("CourseID");
                    String courseTitle = resultSet.getString("CourseTitle");
                    int credit = resultSet.getInt("Credit");
                    String preCourse = resultSet.getString("PreCourse");
                    String difficult = resultSet.getString("Difficult");
                    if(preCourse == null){
                        preCourse = "-";
                        //System.out.println(preCourse);
                    }
                    subjectObservableList.add(new Subject(semester,year,courseID,courseTitle,credit,preCourse,difficult));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectObservableList;
    }
    public static void deleteSubject(String courseID){
        try{
            Class.forName(dbName);
            Connection connection = DriverManager .getConnection(dbURL);
            if(connection != null){
                String query  = "Delete from Subjects where CourseID == '" + courseID + "'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Subject getSubject(String courseIDP){
        Subject subject = null;
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select * from Subjects where CourseID == '" + courseIDP + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    int semester = resultSet.getInt("Semester");
                    int year = resultSet.getInt("Year");
                    String courseID = resultSet.getString("CourseID");
                    String courseTitle = resultSet.getString("CourseTitle");
                    int credit = resultSet.getInt("Credit");
                    String preCourse = resultSet.getString("PreCourse");
                    String difficult = resultSet.getString("Difficult");
                    if(courseID.equals(courseIDP)){
                        if(preCourse == null){
                            preCourse = "-";
                        }
                        subject = new Subject(semester,year,courseID,courseTitle,credit,preCourse,difficult);
                    }
                    else {
                        subject = new Subject(semester,year,courseID,courseTitle,credit,preCourse,difficult);
                    }
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }
    public static boolean checkSameCourseID(String courseID){
        boolean check = true;
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select CourseID from Subjects";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    String cID = resultSet.getString("CourseID");
                    if(cID.equals(courseID)){
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
        System.out.println(check);
        return check;
    }
    public static boolean checkSameCourseTitle(String courseTitle){
        boolean check = true;
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select CourseTitle from Subjects";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    String cID = resultSet.getString("CourseTitle");
                    if(cID.equals(courseTitle)){
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
        System.out.println(check);
        return check;
    }
    public static ObservableList getAllSubjects(int year, int semester) {
        ObservableList<Subject> subjectObservableList = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Subjects";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int semesterDB = resultSet.getInt("Semester");
                    int yearDB = resultSet.getInt("Year");
                    String courseID = resultSet.getString("CourseID");
                    String courseTitle = resultSet.getString("CourseTitle");
                    int credit = resultSet.getInt("Credit");
                    String preCourse = resultSet.getString("PreCourse");
                    String difficult = resultSet.getString("Difficult");
                    if (yearDB == year && semesterDB == semester) {
                        subjectObservableList.add(new Subject(semesterDB, yearDB, courseID, courseTitle, credit, preCourse, difficult));
                    }
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectObservableList;
    }
}
