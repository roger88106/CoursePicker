package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students_courses")
public class StudentCourse {
    @Id
    @Column(name = "student_id")
    private String studentID;
    @Id
    @Column(name = "course_ID")
    private String course_ID;

    //================== 建構子 & Getter/Setter ==================
    public StudentCourse() {
    }

    public StudentCourse(String studentID, String course_ID) {
        this.studentID = studentID;
        this.course_ID = course_ID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }
}
