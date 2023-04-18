package com.example.demo.controller;

import com.example.demo.services.ifs.CoursesService;
import com.example.demo.vo.request.CoursesRequest;
import com.example.demo.vo.response.CoursesResponse;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    CoursesService coursesService;

    @PostMapping("Create_Course")//輸入課程資料(ID Name Week Start End Credit)
    public CoursesResponse createCourse(@RequestBody CoursesRequest request){
        return coursesService.createCourse(request);
    }
    @PostMapping("Delete_Course")//輸入課程ID
    public CoursesResponse deleteCourse(@RequestBody CoursesRequest request){
        return coursesService.deleteCourse(request);
    }

    @PostMapping("Update_Course")//輸入課程ID以及想修改的課程資料(Name Week Start End Credit))
    public CoursesResponse updateCourse(@RequestBody CoursesRequest request){
        return coursesService.updateCourse(request);
    }

    @PostMapping("Search_Course")//輸入課程ID或課程名稱(都輸入時以ID為主)
    public CoursesResponse searchCourse(@RequestBody CoursesRequest request){
        return coursesService.searchCourse(request);

    }
}
