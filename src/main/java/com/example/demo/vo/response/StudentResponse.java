package com.example.demo.vo.response;

import com.example.demo.entity.Student;
import com.example.demo.entity.StudentCourse;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.CollectionUtils;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {
    private String studentsID;
    private String studentsName;
    private List<Student> studentList;
    private List<StudentCourse> studentCourseList;
    private String msg;


    public StudentResponse() {
    }

    public StudentResponse(String studentsID, String studentsName) {
        this.studentsID = studentsID;
        this.studentsName = studentsName;
    }

    public StudentResponse(List<?> list) {
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

    public StudentResponse(String msg) {
        this.msg = msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
