package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "id")
    private String studentsID;
    @Column(name = "name")
    private String studentsName;


    //================== 建構子 & Getter/Setter ==================
    public Student() {
    }

    public Student(String studentsID, String studentsName) {
        this.studentsID = studentsID;
        this.studentsName = studentsName;
    }

    public String getStudentsID() {
        return studentsID;
    }

    public void setStudentsID(String studentsID) {
        this.studentsID = studentsID;
    }

    public String getStudentsName() {
        return studentsName;
    }

    public void setStudentsName(String studentsName) {
        this.studentsName = studentsName;
    }
}
