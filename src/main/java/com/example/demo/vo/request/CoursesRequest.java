package com.example.demo.vo.request;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalTime;

public class CoursesRequest {

    private String courseID;
    private String courseName;
    private Integer courseWeek;
    private LocalTime courseStart;
    private LocalTime courseEnd;
    private Integer credit;

    public CoursesRequest() {
    }

    public CoursesRequest(String courseID, String courseName, Integer courseWeek, LocalTime courseStart, LocalTime courseEnd, Integer credit) {
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
