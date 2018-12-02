package Controller;

import database.SubjectDB;
import database.SubjectPassDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Subject;
import model.SubjectPass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class SubjectsPassController {
    @FXML protected ImageView homeIcon,kuSign,courseI,subjectI;
    @FXML protected TextField textFieldCourseID;
    @FXML protected Button find,pass,home,course,subjects;
    @FXML protected TableView passTableView;
    @FXML protected Label showDetails,showErrorDetails;
    @FXML protected TableColumn courseID,courseTitle,credit;

    private SubjectDB subjectDB;
    private Subject subject;
    //private ArrayList<String> courseIDA = subjectDB.getCourseID();
    //private Set<String> courseIDSet = new TreeSet<>(courseIDA);
    //ObservableList<String> courseIDObserve = FXCollections.observableArrayList(courseIDSet);

    private ArrayList<String> subjectPass = SubjectPassDB.getSubjectPass();
    ObservableList<String> addToTable = FXCollections.observableArrayList(subjectPass);

    @FXML
    public void initialize(){
        courseID.setCellValueFactory(new PropertyValueFactory<SubjectPass,String>("courseID"));
        courseTitle.setCellValueFactory(new PropertyValueFactory<SubjectPass,String>("courseTitle"));
        credit.setCellValueFactory(new PropertyValueFactory<SubjectPass,String>("credit"));
        passTableView.setItems(addToTable);
        pass.setVisible(false);
    }
    @FXML
    public void findBtn(){
        if(showErrorDetails.getText().equals("Please click find button.")){
            showErrorDetails.setText("");
        }
        if(textFieldCourseID.getText() != null){
            pass.setVisible(true);
            showErrorDetails.setText("");
            subject = subjectDB.getSubject(textFieldCourseID.getText());
            //System.out.println(subject.getCourseID());
            if(subject != null){
                if(textFieldCourseID.getText().equals(subject.getCourseID())){
                    //System.out.println(subject.getCourseID());
                    showDetails.setText("Name: " + subject.getCourseTitle() + "\n"
                            + "ID: " + subject.getCourseID() + "\n"
                            + "Credit: " + subject.getCredit() + "\n"
                            + "Year: " + subject.getYear() + "\n"
                            + "Semester: " + subject.getSemester() + "\n"
                            + "Pre-Course: " + subject.getPreCourse() + "\n"
                            + "Difficult: " + subject.getDifficult() + "\n"
                            + "Duo Course ID: " + subject.getDuoCourseID());
                }
            }
            else{
                pass.setVisible(false);
                showDetails.setText("Cannot Find Subject.");
            }
        }
        else{
            pass.setVisible(false);
            showErrorDetails.setText("");
            showDetails.setText("Please fill in course id");
        }

    }
    @FXML
    public void passBtn(){
        if(SubjectPassDB.getCheck(subject.getPreCourse()) && SubjectPassDB.getCheckCourseID(textFieldCourseID.getText()) && textFieldCourseID.getText().equals(subject.getCourseID())){
            showDetails.setText("");
            SubjectPassDB.saveSubjectPass(subject.getCourseID(),subject.getCourseTitle(),subject.getCredit());
//            courseID.setCellValueFactory(new PropertyValueFactory<SubjectPass,String>("courseID"));
//            courseTitle.setCellValueFactory(new PropertyValueFactory<SubjectPass,String>("courseTitle"));
//            credit.setCellValueFactory(new PropertyValueFactory<SubjectPass,String>("credit"));
            //System.out.println("111");
            showErrorDetails.setText("");
            passTableView.setItems(SubjectPassDB.getSubjectPassToTable());
        }
        else if(!SubjectPassDB.getCheckCourseID(textFieldCourseID.getText())){
            System.out.println("else if");
            System.out.println("12123");
            showDetails.setText("You pass this subject.");
            textFieldCourseID.setText("");
        }
        else{
            if(textFieldCourseID.getText().equals(subject.getCourseID())){
                int countSubject = 0;
                String preCourse1 = "";
                String preCourse2 = "";
                String preCourse3 = "";
                boolean check = false;
                ArrayList<String> pCourseFromDB = SubjectPassDB.getSubjectNeedToStudy();
                if(subject.getPreCourse().contains(",")){
                    String[] pCourseSplit = subject.getPreCourse().split(",");
                    for(String pCourse:pCourseSplit){
                        countSubject+=1;
                        for (String pCourseDB:pCourseFromDB){
                            if(pCourseDB.equals(pCourse)){
                                check = true;
                            }
                        }
                        if(!check && countSubject == 1){
                            preCourse1 = pCourse;
                        }
                        else if(!check && countSubject == 2){
                            preCourse2 = pCourse;
                        }
                        else if(!check && countSubject == 3){
                            preCourse3 = pCourse;
                        }
                        check = false;
                    }
                    if(countSubject == 2){
                        showErrorDetails.setText("This subject need to study " + preCourse1 + " and " + preCourse2 + ".");
                    }
                    else if(countSubject == 3){
                        showErrorDetails.setText("This subject need to study " + preCourse1 + ", " + preCourse2 + "\nand " + preCourse3 + ".");
                    }
                }
                else {
                    if(!pCourseFromDB.contains(subject.getPreCourse())){
                        showErrorDetails.setText("This subject need to study " + subject.getPreCourse() + ".");
                    }
                }
            }
            else{
                showErrorDetails.setText("Please click find button.");
            }

        }
        setVisiblePassBtn();

    }
    @FXML
    public void homeBtn(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
    public void setVisiblePassBtn(){
        pass.setVisible(false);
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
