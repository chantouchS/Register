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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.Subject;
import model.SubjectPass;
import model.SubjectPlan;

import java.io.IOException;

public class InfoController {
    @FXML protected ChoiceBox<Integer> years,semester;
    @FXML protected Button info;
    ObservableList<Integer> yearList = FXCollections.observableArrayList(1,2,3,4);
    ObservableList<Integer> semesterList = FXCollections.observableArrayList(1,2);
    ObservableList<SubjectPass> subjectPasses = SubjectPassDB.getSubjectPassToTable();
    ObservableList<SubjectPlan> subjectPlans = SubjectPlanDB.getAllSubject();
    int[] tmp;
    SaveSubjectController saveSubjectController = new SaveSubjectController();

    public void initialize(){
        AccountDB.delete("Nattapat");
        years.getItems().addAll(yearList);
        semester.getItems().addAll(semesterList);
        years.getSelectionModel().selectFirst();
        semester.getSelectionModel().selectFirst();
    }
    @FXML
    public void infoBtn(ActionEvent event) throws IOException {
        for(SubjectPass o:subjectPasses){
            System.out.println("SubjectPass: " + o.getCourseID());
            tmp = SubjectDB.getYearAndSemester(o.getCourseID());
            tmp[0] = tmp[0]*2 + tmp[1];
            int yearAccount = years.getValue() * 2 + semester.getValue();
            if(tmp[0] != yearAccount){
                SubjectPassDB.deleteAll();
            }
        }
        for(SubjectPlan o:subjectPlans){
            int yearSubject = o.getYear() * 2 + o.getSemester();
            int yearAccount = years.getValue() * 2 + semester.getValue();
            if(yearAccount != yearSubject){
                SubjectPlanDB.deleteAll();
            }
        }
        AccountDB.addAccount("Nattapat","Chotiyarnnon",years.getValue(),semester.getValue());
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
