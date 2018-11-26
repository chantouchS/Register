package Controller;

import database.SubjectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class CreateSubjectController {
    private boolean checkAddMore = false;
    @FXML protected ImageView homeIcon,kuSign,courseI,subjectI;
    @FXML protected ChoiceBox<String> year,semester,credit,preCourse1,preCourse2,difficult;
    @FXML protected TextField courseId,courseTitle;
    @FXML protected Button addMore,back,create,home,course,subjectB;
    @FXML protected Label showCourseID,showCourseTitle,showError;
    private SubjectDB subjectDB;
    private ArrayList<String> courseArrayList = subjectDB.getCourseID();

    private Set<String> courseSet = new TreeSet<>(courseArrayList);
    ObservableList<String> courseObserve = FXCollections.observableArrayList(courseSet);
    ObservableList<String> yearList = FXCollections.observableArrayList("1","2","3","4");
    ObservableList<String> semesterList = FXCollections.observableArrayList("1","2");
    ObservableList<String> difficultList = FXCollections.observableArrayList("Green","Blue","Red");


    @FXML
    public void initialize(){
        year.getItems().addAll(yearList);
        semester.getItems().addAll(semesterList);
        credit.getItems().addAll(yearList);
        preCourse1.getItems().addAll(courseObserve);
        preCourse2.getItems().addAll(courseObserve);
        difficult.getItems().addAll(difficultList);

    }


    @FXML
    public void createSubject(ActionEvent event) throws Exception {
        if(year.getValue() != null && semester.getValue() != null && credit.getValue() != null && difficult.getValue() != null){
            if(SubjectDB.checkSameCourseID(courseId.getText()) && SubjectDB.checkSameCourseTitle(courseTitle.getText())){
                String preCourse = "";
                int semesterValue = Integer.parseInt(semester.getValue());
                int yearValue = Integer.parseInt(year.getValue());
                String courseID = courseId.getText();
                String courseT = courseTitle.getText();
                int creditNochoice = Integer.parseInt(credit.getValue());
                if(checkAddMore){
                    if(preCourse1.getValue() == null || preCourse2.getValue() == null){
                        preCourse = "-";
                    }
                    else{
                        preCourse = preCourse1.getValue() + "," +  preCourse2.getValue();
                    }
                }
                else{
                    if(preCourse1.getValue() == null){
                        preCourse = "-";
                    }
                    else{
                        preCourse = preCourse1.getValue();
                    }
                }

                String colorDifficult = difficult.getValue();

                subjectDB.saveSubjects(semesterValue,yearValue,courseID,courseT,creditNochoice,preCourse,colorDifficult);
                goToShowSubjectAfterCreateSubject(event);
            }
            else if(!SubjectDB.checkSameCourseID(courseId.getText()) && SubjectDB.checkSameCourseTitle(courseTitle.getText())){
                showCourseID.setText("This course id has already");
                showCourseTitle.setText("");
                showError.setText("");
            }
            else if(!SubjectDB.checkSameCourseTitle(courseTitle.getText()) && SubjectDB.checkSameCourseID(courseId.getText())){
                showCourseTitle.setText("This course title has already");
                showCourseID.setText("");
                showError.setText("");
            }
            else if(!SubjectDB.checkSameCourseID(courseId.getText()) && !SubjectDB.checkSameCourseTitle(courseTitle.getText())){
                showCourseID.setText("This course id has already");
                showCourseTitle.setText("This course title has already");
                showError.setText("");
            }
        }
        else{
            showError.setText("Please fill in the blank");
            showCourseID.setText("");
            showCourseTitle.setText("");
        }



    }

    @FXML
    public void goToShowSubjectAfterCreateSubject(ActionEvent event) throws Exception {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Subject.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
    @FXML
    public void addMoreM(){
        checkAddMore = true;
        preCourse2.setDisable(false);
    }
    @FXML
    public void backBtn(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Subject.fxml"));
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
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

}
