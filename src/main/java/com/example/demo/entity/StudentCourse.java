package com.example.demo.entity;

import com.example.demo.entity.PrimaryKeys.StudentCourseID;

import javax.persistence.*;

@Entity
@Table(name = "students_courses")
@IdClass(StudentCourseID.class)
public class StudentCourse {
    @Id
    @Column(name = "student_ID")
    private String studentID;

    @Id
    @Column(name = "course_ID")
    private String courseID;

    //================== 建構子 & Getter/Setter ==================
    public StudentCourse() {
    }

    public StudentCourse(String studentID, String courseID) {
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