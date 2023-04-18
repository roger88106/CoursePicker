package com.example.demo.vo.request;

public class StudentCourseRequest {
    private String studentID;
    private String courseID;

    public StudentCourseRequest() {
    }

    public StudentCourseRequest(String studentID, String courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
