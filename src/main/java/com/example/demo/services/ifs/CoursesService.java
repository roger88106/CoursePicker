package com.example.demo.services.ifs;


import com.example.demo.vo.request.CoursesRequest;
import com.example.demo.vo.response.CoursesResponse;

public interface CoursesService {
    //新增課程
    public CoursesResponse createCourse(CoursesRequest request);

    //刪除課程
    public CoursesResponse deleteCourse(CoursesRequest request);

    //修改課程
    public CoursesResponse updateCourse(CoursesRequest request);

    //查詢課程
    public CoursesResponse searchCourse(CoursesRequest request);

}
