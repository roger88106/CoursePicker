package com.example.demo.controller;

import com.example.demo.services.ifs.StudentService;
import com.example.demo.vo.request.StudentCourseRequest;
import com.example.demo.vo.request.StudentRequest;
import com.example.demo.vo.response.CoursesResponse;
import com.example.demo.vo.response.SelectCourseResponse;
import com.example.demo.vo.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("Create_Student")
    public StudentResponse createStudent(@RequestBody StudentRequest request){
        return studentService.createStudent(request);
    }
    @PostMapping("Select_Course")
    public SelectCourseResponse selectCourse(@RequestBody StudentCourseRequest request){
        return studentService.selectCourse(request);
    }
    @PostMapping("Unselect_Course")
    public SelectCourseResponse unselectCourse(@RequestBody StudentCourseRequest request){
        return studentService.unselectCourse(request);

    }
    @PostMapping("Get_Selected_Course_Record")
    public CoursesResponse getSelectCourseRecord(@RequestBody StudentCourseRequest request){
        return studentService.getSelectCourseRecord(request);

    }
}
