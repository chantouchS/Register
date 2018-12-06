package model;

import javafx.scene.layout.Pane;

public class SubjectPlan {
    private String courseID;
    private String courseTitle;
    private String preCourse;
    private int year;
    private int semester;
    private int credit;
    private String difficult;
    private String withCourseID;
    private Pane difficultPane;

    public SubjectPlan(String courseID, String courseTitle, String preCourse, int year, int semester, int credit, String difficult, String withCourseID) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.preCourse = preCourse;
        this.year = year;
        this.semester = semester;
        this.credit = credit;
        this.difficultPane = new Pane();
        if(difficult.equals("Red")){
            this.difficultPane.setStyle("-fx-background-color: red;");
        }
        else if (difficult.equals("Blue")){
            this.difficultPane.setStyle("-fx-background-color: blue;");
        }
        else{
            this.difficultPane.setStyle("-fx-background-color: green;");
        }
        this.withCourseID = withCourseID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getPreCourse() {
        return preCourse;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public int getCredit() {
        return credit;
    }

    public String getDifficult() {
        return difficult;
    }

    public String getWithCourseID() {
        return withCourseID;
    }

    public Pane getDifficultPane() {
        return difficultPane;
    }

    @Override
    public String toString() {
        return "SubjectPlan{" +
                "courseID='" + courseID + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", preCourse='" + preCourse + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                ", credit=" + credit +
                ", difficult='" + difficult + '\'' +
                ", withCourseID='" + withCourseID + '\'' +
                ", difficultPane=" + difficultPane +
                '}';
    }
}
