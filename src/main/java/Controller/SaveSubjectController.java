package Controller;

import database.SubjectPassDB;
import database.SubjectPlanDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.SubjectPlan;

import java.io.IOException;

public class SaveSubjectController {
    @FXML protected Button home,course,subjects,saveSubject,delete;
    @FXML protected ImageView homeIcon,subjectI,courseI,kuSign;
    @FXML TableView<SubjectPlan> tableView;
    @FXML protected TableColumn semester,year,courseID,courseTitle,credit,preCourse,difficult,withCourseID;

    @FXML
    public void initialize(){
        courseID.setCellValueFactory(new PropertyValueFactory<SubjectPlan,String>("courseID"));
        courseTitle.setCellValueFactory(new PropertyValueFactory<SubjectPlan,String>("courseTitle"));
        preCourse.setCellValueFactory(new PropertyValueFactory<SubjectPlan,String>("preCourse"));
        year.setCellValueFactory(new PropertyValueFactory<SubjectPlan,String>("year"));
        semester.setCellValueFactory(new PropertyValueFactory<SubjectPlan,String>("semester"));
        credit.setCellValueFactory(new PropertyValueFactory<SubjectPlan,String>("credit"));
        difficult.setCellValueFactory(new PropertyValueFactory<SubjectPlan,Pane>("difficultPane"));
        withCourseID.setCellValueFactory(new PropertyValueFactory<SubjectPlan,String>("withCourseID"));

        tableView.setItems(SubjectPlanDB.getAllSubject());
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
    public void saveSubjectsBtn(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SaveSubject.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
    @FXML
    public void deleteBtn(){
        if(tableView.getSelectionModel().getSelectedItem() != null){
            String courseID = tableView.getSelectionModel().getSelectedItem().getCourseID();
            SubjectPlanDB.deleteSubjectPlan(courseID);
            tableView.setItems(SubjectPlanDB.getAllSubject());
        }
    }


}
