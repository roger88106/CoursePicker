package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "id")
    private String courseID;
    @Column(name = "name")
    private String courseName;
    @Column(name = "week")
    private Integer courseWeek;
    @Column(name = "start")
    private LocalTime courseStart;
    @Column(name = "end")
    private LocalTime courseEnd;
    @Column(name = "credit")
    private Integer credit;

    //================== 建構子 & Getter/Setter ==================
    public Course() {
    }

    public Course(String courseID, String courseName, Integer courseWeek, LocalTime courseStart, LocalTime courseEnd, Integer credit) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseWeek = courseWeek;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.credit = credit;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseWeek() {
        return courseWeek;
    }

    public void setCourseWeek(Integer courseWeek) {
        this.courseWeek = courseWeek;
    }

    public LocalTime getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(LocalTime courseStart) {
        this.courseStart = courseStart;
    }

    public LocalTime getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(LocalTime courseEnd) {
        this.courseEnd = courseEnd;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}
