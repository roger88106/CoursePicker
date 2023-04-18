package com.example.demo.services.ifs;


import com.example.demo.vo.request.CoursesRequest;
import com.example.demo.vo.response.CoursesResponse;

public interface CoursesService {
    public CoursesResponse createCourse(CoursesRequest request);
    public CoursesResponse deleteCourse(CoursesRequest request);
    public CoursesResponse selectCourse(CoursesResponse response);

}
