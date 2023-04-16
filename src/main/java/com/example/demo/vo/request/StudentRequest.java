package com.example.demo.vo.request;

import com.example.demo.entity.Student;
import com.example.demo.entity.StudentCourse;
import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import java.util.Collection;
import java.util.List;

public class StudentRequest {
    private String studentsID;
    private String studentsName;
    private List<Student> studentList;
    private List<StudentCourse> studentCourseList;

    public StudentRequest() {
    }

    public StudentRequest(String studentsID, String studentsName) {
        this.studentsID = studentsID;
        this.studentsName = studentsName;
    }

    //依照不同list內容的list去新增成對應list的建構子 <== 寫好玩的，其實用setter就好
    public StudentRequest(List<?> list) {
        //先判斷list是否為空
        if (!CollectionUtils.isEmpty(list)) {
            var item = list.get(0);
            //判斷list第一個元素型別，並儲存進對應的list內
            if (item instanceof Student) { // <== 如果是學生清單
                this.studentList = (List<Student>) list;
            } else if (item instanceof StudentCourse) { // <== 如果是選課清單
                this.studentCourseList = (List<StudentCourse>) list;
            }
        }
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<StudentCourse> getStudentCourseList() {
        return studentCourseList;
    }

    public void setStudentCourseList(List<StudentCourse> studentCourseList) {
        this.studentCourseList = studentCourseList;
    }
}
