package com.example.demo.services.impl;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseDAO;
import com.example.demo.repository.StudentCourseDAO;
import com.example.demo.services.ifs.CoursesService;
import com.example.demo.vo.request.CoursesRequest;
import com.example.demo.vo.response.CoursesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    CourseDAO courseDAO;
    @Autowired
    StudentCourseDAO studentCourseDAO;

    //新增學分
    @Override
    public CoursesResponse createCourse(CoursesRequest request) {
        String id = request.getCourseID();
        //判斷id是否為空以及是否重複
        if (StringUtils.isEmpty(id)){
            return new CoursesResponse("有欄位忘了填喔");
        } else if (id.length() > 10) {
            return new CoursesResponse("ID長度不能超過10個字");
        } else if (courseDAO.existsById(id)){
            return new CoursesResponse("ID重複了喔");
        }

        // 取出request內的值
        String name = request.getCourseName();
        Integer week = request.getCourseWeek();
        LocalTime start = request.getCourseStart();
        LocalTime end = request.getCourseEnd();
        Integer credit = request.getCredit();

        //判斷是否全部必填的值都有輸入
        if (StringUtils.isEmpty(name) || week == null || start == null || end == null || credit == null) {
            return new CoursesResponse("有欄位忘了填喔");
        } else if (name.length() > 10) {
            return new CoursesResponse("課程名稱長度不能超過10個字");
        } else if (week <= 1 || week >=7 ) {
            return new CoursesResponse("星期只能填 1~7");
        }else if (credit < 0){
            return new CoursesResponse("學分不能小於 0");
        }else {
            Course course = courseDAO.save(new Course(id,name,week,start,end,credit));

            if (course == null){
                return new CoursesResponse("儲存失敗");
            }else {
                return new CoursesResponse(course.getCourseID(), course.getCourseName(), course.getCourseWeek(), course.getCourseStart(),
                        course.getCourseEnd(), course.getCredit());
            }
        }
    }

    @Override
    public CoursesResponse deleteCourse(CoursesRequest request) {
        String courseID = request.getCourseID();
        if (!courseDAO.existsById(courseID)) {
            return new CoursesResponse("無此課程");
        } else if (studentCourseDAO.countByCourseID(courseID) != 0) {   // 從選課表查有無學生選修
            return new CoursesResponse("尚有學生在選修此課程，無法刪除");
        }
        courseDAO.deleteById(courseID);
        if (!courseDAO.existsById(courseID))
            return new CoursesResponse("刪除成功");
        else
            return new CoursesResponse("刪除失敗");
    }
}
