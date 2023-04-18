package com.example.demo.vo.request;

public class StudentCourseRequest {
    private String studentID;
    private String CourseID;

    public StudentCourseRequest() {
    }

    public StudentCourseRequest(String studentID, String courseID) {
        this.studentID = studentID;
        CourseID = courseID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }
}
