package com.example.demo.entity.PrimaryKeys;

import java.io.Serializable;

public class StudentCourseID implements Serializable {
    private String studentID;
    private String courseID;

    //================== 建構子 & Getter/Setter ==================
    public StudentCourseID() {
    }

    public StudentCourseID(String studentID, String courseID) {
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