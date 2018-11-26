package Controller;

import database.SubjectDB;
import database.SubjectPassDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Subject;
import model.SubjectPass;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {
    @FXML protected ChoiceBox<Integer> year,semester;
    @FXML protected ImageView homeIcon,kuSign,course,subject;
    @FXML protected Button yourCourse,subjects,home,show;
    @FXML protected TableView<Subject> tableView;
    @FXML protected TableColumn courseID,cTitle,credits,preCourse,difficult;
    private ArrayList<String> subjectPass = SubjectPassDB.getSubjectPass();
    ObservableList<String> addToTable = FXCollections.observableArrayList(subjectPass);
    ObservableList<Integer> yearList = FXCollections.observableArrayList(1,2,3,4);
    ObservableList<Integer> semesterList = FXCollections.observableArrayList(1,2);

    @FXML
    public void initialize(){
        year.getItems().addAll(yearList);
        semester.getItems().addAll(semesterList);
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
        stage.setScene(new Scene(loader.load(),1080,720));
        stage.show();
    }
    @FXML
    public void showSubjectBtn(){
        if(year.getValue() != null && semester.getValue() != null){
            courseID.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("courseID"));
            cTitle.setCellValueFactory(new PropertyValueFactory<Subject,String>("courseTitle"));
            preCourse.setCellValueFactory(new PropertyValueFactory<Subject,String>("preCourse"));
            credits.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("credit"));
            difficult.setCellValueFactory(new PropertyValueFactory<Subject,String>("difficult"));
//            ObservableList ob = FXCollections.observableArrayList(SubjectDB.getAllSubjects(year.getValue(),semester.getValue()));
//            for(Subject s:ob)
//            for(Object object: tableView.getItems().stream().forEach(o);)
//            difficult.setStyle("-fx-text-fill: red");
            tableView.setItems(SubjectDB.getAllSubjects(year.getValue(),semester.getValue()));
        }
    }


}
