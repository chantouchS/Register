package Controller;

import database.SubjectPlanDB;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.SubjectPlan;

public class SaveSubjectController {
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
        difficult.setCellValueFactory(new PropertyValueFactory<SubjectPlan,String>("difficult"));
        withCourseID.setCellValueFactory(new PropertyValueFactory<SubjectPlan,String>("withCourseID"));

        tableView.setItems(SubjectPlanDB.getAllSubject());
    }


}
