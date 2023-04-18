package com.example.demo.services.impl;

import com.example.demo.entity.Course;
import com.example.demo.my_class.LocalTimeUtils;
import com.example.demo.repository.CourseDAO;
import com.example.demo.repository.StudentCourseDAO;
import com.example.demo.services.ifs.CoursesService;
import com.example.demo.vo.request.CoursesRequest;
import com.example.demo.vo.response.CoursesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.*;

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
        if (StringUtils.isEmpty(id)) {
            return new CoursesResponse("有欄位忘了填喔");
        } else if (id.length() > 10) {
            return new CoursesResponse("ID長度不能超過10個字");
        } else if (courseDAO.existsById(id)) {
            return new CoursesResponse("ID重複了喔");
        }

        //ID強制轉大寫，防止大小寫問題
        id = id.toUpperCase();
        // 取出request內的值
        String name = request.getCourseName();
        Integer week = request.getCourseWeek();
        String start = request.getCourseStart();
        String end = request.getCourseEnd();
        Integer credit = request.getCredit();

        //判斷是否全部必填的值都有輸入
        if (StringUtils.isEmpty(name) || week == null || StringUtils.isEmpty(start) ||
                StringUtils.isEmpty(end) || credit == null) {
            return new CoursesResponse("有欄位忘了填喔");
        } else if (name.length() > 10) {
            return new CoursesResponse("課程名稱長度不能超過10個字");
        } else if (week < 1 || week > 7) {
            return new CoursesResponse("星期只能填 1~7");
        } else if (credit < 0) {
            return new CoursesResponse("學分不能小於 0");
        } else {
            LocalTime startTime;
            LocalTime endTime;
            //如果時間轉換出錯，代表格式不符，提示錯誤訊息
            try {
                startTime = LocalTimeUtils.toLocalTime(start);
                endTime = LocalTimeUtils.toLocalTime(end);
            } catch (DateTimeException e) {
                return new CoursesResponse("時間格式錯誤");
            }
            if (startTime.isAfter(endTime)) {
                return new CoursesResponse("開始時間不可小於結束時間");
            }

            Course course = courseDAO.save(new Course(id, name, week, startTime, endTime, credit));
            if (course == null) {
                return new CoursesResponse("儲存失敗");
            } else {
                return new CoursesResponse(course.getCourseID(), course.getCourseName(), course.getCourseWeek(),
                        course.getCourseStart(), course.getCourseEnd(), course.getCredit());
            }
        }
    }

    //刪除課程
    @Override
    public CoursesResponse deleteCourse(CoursesRequest request) {
        String courseID = request.getCourseID();
        long count = 0;
        //判斷是否有輸入ID
        if (StringUtils.hasText(courseID)){
            //ID強制轉大寫，防止大小寫問題
            courseID = courseID.toUpperCase();
            count = studentCourseDAO.countByCourseID(courseID);
        } else {
            return new CoursesResponse("請確實輸入ID");
        }

        if (!courseDAO.existsById(courseID)) {
            return new CoursesResponse("無此課程");
        } else if (count != (long) 0) {   // 從選課表查有無學生選修
            return new CoursesResponse("尚有 " + count + "位學生在選修此課程，無法刪除");
        }
        courseDAO.deleteById(courseID);
        if (!courseDAO.existsById(courseID))
            return new CoursesResponse("刪除成功");
        else
            return new CoursesResponse("刪除失敗");
    }

    @Override
    public CoursesResponse selectCourse(CoursesResponse response) {
        String id = response.getCourseID();
        String name = response.getCourseName();

        //判斷是在搜尋ID還是課程名稱(ID優先)，搜尋後輸出
        if (StringUtils.hasText(id)){
            id = id.toUpperCase();
            Optional<Course> optionalCourse =courseDAO.findById(id);
            if (optionalCourse.isPresent()){
                return new CoursesResponse(Arrays.asList(optionalCourse.get()));
            }
            return new CoursesResponse("查無資料");
        } else if (StringUtils.hasText(name)) {
            List<Course> courseList = courseDAO.findByCourseName(name);
            if (courseList != null) {
                return new CoursesResponse(courseList);
            }
            return new CoursesResponse("查無資料");
        }

        //ID 姓名都無資料，查詢失敗
        return new CoursesResponse("請確實輸入資料");

    }

}
