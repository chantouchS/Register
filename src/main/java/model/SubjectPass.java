package model;

public class SubjectPass {
    private String courseID;
    private String courseTitle;
    private int credit;

    public SubjectPass(String courseID, String courseTitle, int credit) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.credit = credit;
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
}
