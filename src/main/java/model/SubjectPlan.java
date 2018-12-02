package model;

public class SubjectPlan {
    private String courseID;
    private String courseTitle;
    private String preCourse;
    private int year;
    private int semester;
    private int credit;
    private String difficult;
    private String withCourseID;

    public SubjectPlan(String courseID, String courseTitle, String preCourse, int year, int semester, int credit, String difficult, String withCourseID) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.preCourse = preCourse;
        this.year = year;
        this.semester = semester;
        this.credit = credit;
        this.difficult = difficult;
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
}
