package Controller;

import database.SubjectDB;
import database.SubjectPassDB;
import database.SubjectPlanDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Subject;
import model.SubjectPass;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {
    private Subject subjectSelection;
    @FXML protected Label alert;
    @FXML protected ChoiceBox<Integer> year,semester;
    @FXML protected ImageView homeIcon,kuSign,course,subject;
    @FXML protected Button yourCourse,subjects,home,show,save,saveSubject,logout;
    @FXML protected TableView<Subject> tableView;
    @FXML protected TableColumn courseID,cTitle,credits,preCourse,withCourseID;
    @FXML protected TableColumn<Subject,Pane> difficult;
    private ArrayList<String> subjectPass = SubjectPassDB.getSubjectPass();
    ObservableList<String> addToTable = FXCollections.observableArrayList(subjectPass);
    ObservableList<Integer> yearList = FXCollections.observableArrayList(1,2,3,4);
    ObservableList<Integer> semesterList = FXCollections.observableArrayList(1,2);


    @FXML
    public void initialize(){
        courseID.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("courseID"));
        cTitle.setCellValueFactory(new PropertyValueFactory<Subject,String>("courseTitle"));
        preCourse.setCellValueFactory(new PropertyValueFactory<Subject,String>("preCourse"));
        credits.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("credit"));
        difficult.setCellValueFactory(new PropertyValueFactory<Subject,Pane>("difficultPane"));
        withCourseID.setCellValueFactory(new PropertyValueFactory<Subject,String>("duoCourseID"));
        tableView.setItems(SubjectDB.getAllSubjects());
        //save.setVisible(false);
        alert.setText("");
        year.getItems().addAll(yearList);
        semester.getItems().addAll(semesterList);
        year.getSelectionModel().selectFirst();
        semester.getSelectionModel().selectFirst();
        setResizeable();
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(tableView.getSelectionModel().getSelectedItem());
            }
        });
    }

    public void setResizeable()
    {
        courseID.setResizable(false);
        cTitle.setResizable(false);
        credits.setResizable(false);
        preCourse.setResizable(false);
        withCourseID.setResizable(false);
    }
    @FXML
    public void saveSubjectsBtn(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SaveSubject.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
    @FXML
    public void yourCourseBtn(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Courses.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @FXML
    public void subjectsBtn(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Subject.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
    @FXML
    public void homeBtn(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
        stage.setScene(new Scene(loader.load(),1303,720));
        stage.show();
    }
    @FXML
    public void showSubjectBtn(){
        if(year.getValue() != null && semester.getValue() != null){
            save.setVisible(true);
            courseID.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("courseID"));
            cTitle.setCellValueFactory(new PropertyValueFactory<Subject,String>("courseTitle"));
            preCourse.setCellValueFactory(new PropertyValueFactory<Subject,String>("preCourse"));
            credits.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("credit"));
            difficult.setCellValueFactory(new PropertyValueFactory<Subject,Pane>("difficultPane"));
            withCourseID.setCellValueFactory(new PropertyValueFactory<Subject,String>("duoCourseID"));
//            ObservableList ob = FXCollections.observableArrayList(SubjectDB.getAllSubjects(year.getValue(),semester.getValue()));
//            for(Subject s:ob)
//           // for(Object object: tableView.getItems().stream().forEach(o);)
            tableView.setItems(SubjectDB.getAllSubjects(year.getValue(),semester.getValue()));
            alert.setText("");
            System.out.println();
        }
    }
    @FXML
    public void saveBtn(ActionEvent event) throws IOException {
//        if (year.getValue() != null && semester.getValue() != null) {
//
//        }
        subjectSelection = tableView.getSelectionModel().getSelectedItem();
        try{
            if (SubjectPlanDB.getCheckCourseID(subjectSelection.getCourseID()) && subjectSelection != null) {
                String courseID = subjectSelection.getCourseID();
                String courseTitle = subjectSelection.getCourseTitle();
                String preCourse = subjectSelection.getPreCourse();
                int year = subjectSelection.getYear();
                int semester = subjectSelection.getSemester();
                int credit = subjectSelection.getCredit();
                String difficult = subjectSelection.getDifficult();
                String duoCourseID = subjectSelection.getDuoCourseID();
                //System.out.println(courseID);
                SubjectPlanDB.saveWantToStudy(courseID, courseTitle, preCourse, year, semester, credit, difficult, duoCourseID);

                //System.out.println(subjectSelection.getDuoCourseID());
                if (!subjectSelection.getDuoCourseID().equals("-")) {
                    System.out.println("if2");
                    System.out.println(subjectSelection.getDuoCourseID());
                    subjectSelection = SubjectDB.getDuoCourseID(subjectSelection.getDuoCourseID());
                    if (subjectSelection != null) {
                        String courseID2 = subjectSelection.getCourseID();
                        String courseTitle2 = subjectSelection.getCourseTitle();
                        String preCourse2 = subjectSelection.getPreCourse();
                        int year2 = subjectSelection.getYear();
                        int semester2 = subjectSelection.getSemester();
                        int credit2 = subjectSelection.getCredit();
                        String difficult2 = subjectSelection.getDifficult();
                        String duoCourseID2 = subjectSelection.getDuoCourseID();
                        SubjectPlanDB.saveWantToStudy(courseID2, courseTitle2, preCourse2, year2, semester2, credit2, difficult2, duoCourseID2);
                    }
                }
                goToShowSaveSubject(event);
            }
            else {
                alert.setText("You already have save this subject.");
            }
        }
        catch (Exception e){
            alert.setText("Please select subject");
        }
    }
    @FXML
    public void goToShowSaveSubject(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SaveSubject.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
    @FXML
    public void logoutBtn(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Info.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
