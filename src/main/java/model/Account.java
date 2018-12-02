package model;

public class Account {
    private String firstName;
    private String lastName;
    private int year;
    private int semester;

    public Account(String firstName, String lastName, int year, int semester) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.semester = semester;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }
}
