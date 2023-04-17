package com.example.demo.controller;

import com.example.demo.services.ifs.CoursesService;
import com.example.demo.vo.request.CoursesRequest;
import com.example.demo.vo.response.CoursesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    CoursesService coursesService;

    @PatchMapping("createCourse")
    public CoursesResponse createCourse(@RequestBody CoursesRequest request){
        return coursesService.createCourse(request);
    }
}
