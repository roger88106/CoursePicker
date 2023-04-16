package com.example.demo.vo.response;

import com.example.demo.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoursesResponse {
    private String courseID;
    private String courseName;
    private Integer courseWeek;
    private LocalTime courseStart;
    private LocalTime courseEnd;
    private Integer credit;
    private String msg;
    private List<Course> courseList;


    public CoursesResponse() {
    }
    //課程基本資料
    public CoursesResponse(String courseID, String courseName, Integer courseWeek, LocalTime courseStart, LocalTime courseEnd, Integer credit) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseWeek = courseWeek;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.credit = credit;
    }
    public CoursesResponse(String msg) {
        this.msg = msg;
    }
    public CoursesResponse(List<Course> courseList) {
        this.courseList = courseList;
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
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public List<Course> getCourseList() {
        return courseList;
    }
    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
