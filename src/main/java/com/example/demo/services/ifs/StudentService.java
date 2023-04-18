package com.example.demo.services.ifs;

import com.example.demo.vo.request.StudentCourseRequest;
import com.example.demo.vo.request.StudentRequest;
import com.example.demo.vo.response.CoursesResponse;
import com.example.demo.vo.response.SelectCourseResponse;
import com.example.demo.vo.response.StudentResponse;

public interface StudentService {
    public StudentResponse createStudent(StudentRequest request);
    public SelectCourseResponse selectCourse(StudentCourseRequest request);
    public SelectCourseResponse unselectCourse(StudentCourseRequest request);
    public CoursesResponse getSelectCourseRecord(StudentCourseRequest request);


}
