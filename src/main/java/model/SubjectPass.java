package model;

public class SubjectPass {
    private String courseID;
    private String courseTitle;
    private int credit;
    private String status;

    public SubjectPass(String courseID, String courseTitle, int credit,String status) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.credit = credit;
        this.status = status;
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

    public String getStatus() {
        return status;
    }
}
