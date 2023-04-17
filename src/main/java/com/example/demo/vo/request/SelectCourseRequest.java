package com.example.demo.vo.request;

public class SelectCourseRequest {
    private String studentID;
    private String CourseID;

    public SelectCourseRequest() {
    }

    public SelectCourseRequest(String studentID, String courseID) {
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
