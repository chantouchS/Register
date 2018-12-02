package model;

import javafx.scene.layout.Pane;

public class Subject {
    private int semester;
    private int year;
    private String courseID;
    private String courseTitle;
    private int credit;
    private String preCourse;
    private String difficult;
    private String duoCourseID;
    private Pane difficultPane;

    public Subject(int semester, int year, String courseID, String courseTitle, int credit, String preCourse, String difficult,String duoCourseID) {
        this.semester = semester;
        this.year = year;
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.credit = credit;
        this.preCourse = preCourse;
        this.difficult = difficult;
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

        this.duoCourseID = duoCourseID;
    }

    public int getSemester() {
        return semester;
    }

    public int getYear() {
        return year;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getCredit() {
        return credit;
    }

    public String getPreCourse() {
        return preCourse;
    }

    public String getDifficult() {
        return difficult;
    }

    public Pane getDifficultPane() {
        return difficultPane;
    }

    public String getDuoCourseID() {
        return duoCourseID;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "semester=" + semester +
                ", year=" + year +
                ", courseID='" + courseID + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", credit=" + credit +
                ", preCourse='" + preCourse + '\'' +
                ", difficult='" + difficult + '\'' +
                ", duoCourseID='" + duoCourseID + '\'' +
                '}';
    }
}
