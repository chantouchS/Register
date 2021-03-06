package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.SubjectPass;

import java.sql.*;
import java.util.ArrayList;

public class SubjectPassDB {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";
    public static boolean getCheckCourseID(String courseID) {
        boolean check = true;
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select CourseID from SubjectPass";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String cID = resultSet.getString("CourseID");
                    if(courseID.equals(cID)){
                        check = false;
                    }
                }
                statement.close();
                resultSet.close();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }
    public static boolean getCheck(String preCourse){
        String[] pCourseSplit = preCourse.split(",");
        //System.out.println(preCourse);
        boolean checkStudy = false;
        int numOfPcourse = 0;
        try{
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select CourseID from SubjectPass";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(preCourse.equals("-")){
                    checkStudy = true;
                }
                else {
                    while (resultSet.next()){
                        String pCourseID = resultSet.getString("CourseID");
                        if(preCourse.contains(",")){
                            String[] pCourse = preCourse.split(",");
//                            for(String c:pCourse){
//                                System.out.println(c);
//                            }
                            for(String pCID: pCourse){
//                                System.out.println("PCID: " + pCID);
//                                System.out.println("preCourse: " + pCourseID);
                                if(pCID.equals(pCourseID)){
                                    numOfPcourse += 1;
                                }
                            }
                            if(pCourseSplit.length == 2){
                                if(numOfPcourse == 2){
                                    checkStudy = true;
                                }
                            }
                            else if(pCourseSplit.length == 3){
                                if(numOfPcourse == 3){
                                    checkStudy = true;
                                }
                            }
                        }
                        else{
                            if(pCourseID.equals(preCourse)){
                                checkStudy = true;
                            }
                        }
                    }
                }
                statement.close();
                resultSet.close();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkStudy;
    }
    public static void saveSubjectPass(String courseID, String courseTitle, int credit,String status){
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "insert into SubjectPass (CourseID,CourseTitle,Credit,Status) values" +
                        "('" + courseID + "' , '" + courseTitle + "' , '" + credit + "' , '" + status + "')";
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
    public static ObservableList getSubjectPassToTable(){
        ObservableList<SubjectPass> subjectPasses = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select * from SubjectPass";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String courseID = resultSet.getString("CourseID");
                    String courseTitle = resultSet.getString("CourseTitle");
                    int credit = resultSet.getInt("Credit");
                    String status = resultSet.getString("Status");
                    subjectPasses.add(new SubjectPass(courseID,courseTitle,credit,status));
                    //System.out.println(courseID + " " + courseTitle + " " + credit);
                }
                statement.close();
                resultSet.close();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectPasses;
    }
    public static ArrayList<String> getSubjectNeedToStudy(){
            ArrayList<String> study = new ArrayList<>();
            try {
                Class.forName(dbName);
                Connection connection = DriverManager.getConnection(dbURL);
                if(connection != null){
                    String query = "select CourseTitle from SubjectPass";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()){
                        String courseTitle = resultSet.getString("CourseTitle");
                        study.add(courseTitle);
                    }
                    statement.close();
                    resultSet.close();
                    connection.close();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            for(String c:study){
//                System.out.println("From Class DB: " + c);
//            }
            return study;
    }
    public static ArrayList getSubjectPass(){
        ArrayList<SubjectPass> subjectPasses = new ArrayList<>();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from SubjectPass";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String courseID = resultSet.getString("CourseID");
                    String courseTitle = resultSet.getString("CourseTitle");
                    int credit = resultSet.getInt("Credit");
                    String status = resultSet.getString("Status");
                    subjectPasses.add(new SubjectPass(courseID,courseTitle,credit,status));
                }
                statement.close();
                resultSet.close();
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return subjectPasses;
    }
    public static void deleteSubjectPass(String courseID){
        System.out.println("SubjectPassDB Delete:" + courseID);
        try{
            Class.forName(dbName);
            Connection connection = DriverManager .getConnection(dbURL);
            if(connection != null){
                String query  = "Delete from SubjectPass where CourseID == '" + courseID + "'";
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
    public static void deleteAll(){
        try{
            Class.forName(dbName);
            Connection connection = DriverManager .getConnection(dbURL);
            if(connection != null){
                String query  = "Delete from SubjectPass";
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
