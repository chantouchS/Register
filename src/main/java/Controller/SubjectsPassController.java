package Controller;

import database.AccountDB;
import database.SubjectDB;
import database.SubjectPassDB;
import database.SubjectPlanDB;
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
    @FXML protected ChoiceBox<String> courseIDC;
    @FXML protected Button find,pass,home,course,subjects,f,saveSubject,delete,logout;
    @FXML protected TableView<SubjectPass> passTableView;
    @FXML protected Label showDetails,showErrorDetails;
    @FXML protected TableColumn courseID,courseTitle,credit,status;

    ObservableList<String> courseIDList = SubjectDB.getCourseIDOb();

    private SubjectDB subjectDB;
    private Subject subject;
    //private ArrayList<String> courseIDA = subjectDB.getCourseID();
    //private Set<String> courseIDSet = new TreeSet<>(courseIDA);
    //ObservableList<String> courseIDObserve = FXCollections.observableArrayList(courseIDSet);

    private ArrayList<SubjectPass> subjectPass = SubjectPassDB.getSubjectPass();
    ObservableList<SubjectPass> addToTable = FXCollections.observableArrayList(subjectPass);

    @FXML
    public void initialize(){
        courseIDC.getItems().addAll(courseIDList);
        courseID.setCellValueFactory(new PropertyValueFactory<SubjectPass,String>("courseID"));
        courseTitle.setCellValueFactory(new PropertyValueFactory<SubjectPass,String>("courseTitle"));
        credit.setCellValueFactory(new PropertyValueFactory<SubjectPass,String>("credit"));
        status.setCellValueFactory(new PropertyValueFactory<Subject,String>("status"));
        System.out.println(addToTable.toString());
        passTableView.setItems(addToTable);
        setVisibleFBtn();
        setVisiblePassBtn();
        setResizeable();
    }

    public void setResizeable()
    {
        courseID.setResizable(false);
        courseTitle.setResizable(false);
        credit.setResizable(false);
        status.setResizable(false);
    }
    @FXML
    public void findBtn(){
        if(showErrorDetails.getText().equals("Please click find button.")){
            showErrorDetails.setText("");
        }
        if(courseIDC.getValue() != null){
            pass.setVisible(true);
            f.setVisible(true);
            showErrorDetails.setText("");
            subject = subjectDB.getSubject(courseIDC.getValue());
            //System.out.println(subject.getCourseID());
            if(subject != null){
                if(courseIDC.getValue().equals(subject.getCourseID())){
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
                f.setVisible(false);
                showDetails.setText("Cannot Find Subject.");
            }
        }
        else{
            pass.setVisible(false);
            f.setVisible(false);
            showErrorDetails.setText("");
            showDetails.setText("Please select course id");
        }

    }
    @FXML
    public void passBtn(){
        int arrayAccount[] = AccountDB.checkYAS();
        int arraySubject[] = SubjectDB.checkYAS(courseIDC.getValue());
        int totalAccount = (arrayAccount[0] * 2) + arrayAccount[1];
        int totalSubject = (arraySubject[0] * 2) + arraySubject[1];
        System.out.println("Account: " + totalAccount);
        System.out.println("Subject: " + totalSubject);
        if(totalAccount  >= totalSubject){
            if(SubjectPassDB.getCheck(subject.getPreCourse()) && SubjectPassDB.getCheckCourseID(courseIDC.getValue()) && courseIDC.getValue().equals(subject.getCourseID())){
                showDetails.setText("");
                SubjectPassDB.saveSubjectPass(subject.getCourseID(),subject.getCourseTitle(),subject.getCredit(),"pass");
                showErrorDetails.setText("");
                passTableView.setItems(SubjectPassDB.getSubjectPassToTable());
                SubjectPlanDB.deleteSubjectPlan(subject.getCourseID());
            }
            else if(!SubjectPassDB.getCheckCourseID(courseIDC.getValue())){
                showDetails.setText("You pass or F this subject.");
                //textFieldCourseID.setText("");
            }
            else{
                if(courseIDC.getValue().equals(subject.getCourseID())){
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
        }
        else {
            showErrorDetails.setText("Your year and semester are not enough.");
        }
        setVisiblePassBtn();
        f.setVisible(false);
        //textFieldCourseID.setText("");

    }
    @FXML
    public void fBtn(){
        int arrayAccount[] = AccountDB.checkYAS();
        int arraySubject[] = SubjectDB.checkYAS(courseIDC.getValue());
        int totalAccount = (arrayAccount[0] * 2) + arrayAccount[1];
        int totalSubject = (arraySubject[0] * 2) + arraySubject[1];
        if(totalAccount  >= totalSubject){
            if(SubjectPassDB.getCheck(subject.getPreCourse()) && SubjectPassDB.getCheckCourseID(courseIDC.getValue()) && courseIDC.getValue().equals(subject.getCourseID())){
                System.out.println("if");
                showDetails.setText("");
                SubjectPassDB.saveSubjectPass(subject.getCourseID(),subject.getCourseTitle(),subject.getCredit(),"F");
                showErrorDetails.setText("");
                passTableView.setItems(SubjectPassDB.getSubjectPassToTable());
            }
            else if(!SubjectPassDB.getCheckCourseID(courseIDC.getValue())){
                showDetails.setText("You pass or F this subject.");
                //textFieldCourseID.setText("");
            }
            else{
                if(courseIDC.getValue().equals(subject.getCourseID())){
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
        }
        else {
            showErrorDetails.setText("Your year and semester are not enough.");
        }
        setVisibleFBtn();
        pass.setVisible(false);
        //textFieldCourseID.setText("");
    }
    @FXML
    public void deleteBtn(){
        if(passTableView.getSelectionModel().getSelectedItem() != null){
            String courseID = passTableView.getSelectionModel().getSelectedItem().getCourseID();
            SubjectPassDB.deleteSubjectPass(courseID);
            passTableView.setItems(SubjectPassDB.getSubjectPassToTable());
        }
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
    public void setVisibleFBtn(){
        f.setVisible(false);
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
    public void saveSubjectsBtn(ActionEvent event) throws IOException {
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
