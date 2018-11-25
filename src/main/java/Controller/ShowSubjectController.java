package Controller;

import database.SubjectDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Subject;

import java.io.IOException;

public class ShowSubjectController {
    @FXML protected ImageView homeIcon,kuSign,courseI,subjectI;
    private SubjectDB subjectDB;
    @FXML protected Button home,createSubject,delete,course,subject;
    @FXML private TableView<Subject> tableView;
    @FXML protected TableColumn semester,year,courseID,courseTitle,credit,preCourse,difficult;

    @FXML
    public void initialize(){
        subjectDB = new SubjectDB();
        semester.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("semester"));
        year.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("year"));
        courseID.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("courseID"));
        courseTitle.setCellValueFactory(new PropertyValueFactory<Subject,String>("courseTitle"));
        credit.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("credit"));
        preCourse.setCellValueFactory(new PropertyValueFactory<Subject,String>("preCourse"));
        difficult.setCellValueFactory(new PropertyValueFactory<Subject,String>("difficult"));
        tableView.setItems(subjectDB.getAllSubjects());
    }
    @FXML
    public void create(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateSubject.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
    @FXML
    public void delete(){
        String courseID = tableView.getSelectionModel().getSelectedItem().getCourseID();
        subjectDB.deleteSubject(courseID);
        tableView.setItems(subjectDB.getAllSubjects());
    }

    @FXML
    public void homeBtn(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
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

}
