package com.example.demo.services.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.PrimaryKeys.StudentCourseID;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentCourse;
import com.example.demo.repository.CourseDAO;
import com.example.demo.repository.StudentCourseDAO;
import com.example.demo.repository.StudentDAO;
import com.example.demo.services.ifs.StudentService;
import com.example.demo.vo.request.StudentCourseRequest;
import com.example.demo.vo.request.StudentRequest;
import com.example.demo.vo.response.CoursesResponse;
import com.example.demo.vo.response.SelectCourseResponse;
import com.example.demo.vo.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDAO studentDAO;
    @Autowired
    CourseDAO courseDAO;
    @Autowired
    StudentCourseDAO studentCourseDAO;

    //新增學生
    @Override
    public StudentResponse createStudent(StudentRequest request) {
        String id = request.getStudentsID();
        //判斷id是否為空以及是否重複
        if (StringUtils.isEmpty(id)) {
            return new StudentResponse("有欄位忘了填喔");
        } else if (id.length() > 10) {
            return new StudentResponse("ID長度不能超過10個字");
        } else if (studentDAO.existsById(id)) {
            return new StudentResponse("ID重複了喔");
        }

        //ID強制轉大寫，防止大小寫問題
        id = id.toUpperCase();

        String name = request.getStudentsName();
        //判斷必填欄位是否都有填寫
        if (StringUtils.isEmpty(name)) {
            return new StudentResponse("有欄位忘了填喔");
        } else if (name.length() > 10) {
            return new StudentResponse("學生名稱長度不能超過10個字");
        } else {
            Student student = studentDAO.save(new Student(id, name));
            if (student == null) {
                return new StudentResponse("儲存失敗");
            } else {
                return new StudentResponse(id, name);
            }
        }
    }

    //學生加選
    public SelectCourseResponse selectCourse(StudentCourseRequest request) {
        String studentID = request.getStudentID();
        String courseID = request.getCourseID();
        //先搜出該課程的資料方便之後使用
        Optional<Course> selectCourseOptional = courseDAO.findById(courseID);
        //判斷學生ID、課程ID是否為正確資料
        if (StringUtils.isEmpty(studentID) || StringUtils.isEmpty(courseID)) {
            return new SelectCourseResponse("必填欄位請確實填寫");
        } else if (!studentDAO.existsById(studentID)) {
            return new SelectCourseResponse("查無此學生");
        } else if (!selectCourseOptional.isPresent()) {
            return new SelectCourseResponse("查無此課程");
        }

        //ID強制轉大寫，防止大小寫問題
        studentID = studentID.toUpperCase();
        courseID = courseID.toUpperCase();

        //判斷這堂課是否符合人數規定(上限三人)
        if (studentCourseDAO.countByCourseID(courseID) >= 3) {
            return new SelectCourseResponse("超過課堂人數上限");
        }

        //搜出該學生選過的課程，並取出裡面的課程ID組成清單
        List<String> courseIDList = new ArrayList<>();
        for (StudentCourse item : studentCourseDAO.findByStudentID(studentID)) {
            courseIDList.add(item.getCourseID());
        }

        //判斷學生是否已選了這堂課
        if (courseIDList.contains(courseID)) {
            return new SelectCourseResponse("請勿重複加選相同課程");
        }

        //去資料庫抓出學生選的課程的資料
        List<Course> courseList = courseDAO.findAllById(courseIDList);
        //抓出我希望選的這堂課程的資料
        Course selectCourse = selectCourseOptional.get();
        //判斷是否選修相同課程名稱的課程
        if (courseList.stream().anyMatch(course -> course.getCourseName().equals(selectCourse.getCourseName()))) {
            return new SelectCourseResponse("已有選修同名課程");
        }

        //計算該學生當前的學分數量，並判斷選課後是否超過十學分
        int creditSum = courseList.stream().mapToInt(Course::getCredit).sum();
        if ((creditSum + selectCourse.getCredit()) > 10) {
            return new SelectCourseResponse("選課後學分數超過上限，目前選修學分數為： " + creditSum + " 學分");
        }

        //判斷是否衝堂
        int week = selectCourse.getCourseWeek();
        LocalTime start = selectCourse.getCourseStart();
        LocalTime end = selectCourse.getCourseEnd();
        //從學生所選的課程清單內，去跑foreach，比對是否有時間重疊的問題
        for (var item : courseList) {
            if (week == item.getCourseWeek()) {
                if (isOverlap(start, end, item.getCourseStart(), item.getCourseEnd())) {
                    return new SelectCourseResponse("課程與： " + item.getCourseName() + " 衝堂，選課失敗");
                }
            }
        }

        StudentCourse studentCourse = new StudentCourse(studentID, courseID);

        StudentCourse saved = studentCourseDAO.save(studentCourse);
        if (saved == null) {
            return new SelectCourseResponse("加選失敗");
        }
        return new SelectCourseResponse("加選成功");
    }

    //學生退選
    @Override
    public SelectCourseResponse unselectCourse(StudentCourseRequest request) {
        //ID強制轉大寫，防止大小寫問題
        String studentID = request.getStudentID().toUpperCase();
        String courseID = request.getCourseID().toUpperCase();
        //判斷學生ID、課程ID是否為正確資料
        if (StringUtils.isEmpty(studentID) || StringUtils.isEmpty(courseID)) {
            return new SelectCourseResponse("必填欄位請確實填寫");
        } else if (!studentDAO.existsById(studentID)) {
            return new SelectCourseResponse("查無此學生");
        }

        StudentCourseID studentCourseID = new StudentCourseID(studentID, courseID);
        // 如果裡面存在這筆選課資料
        if (studentCourseDAO.findById(studentCourseID).isPresent()) {
            studentCourseDAO.deleteById(studentCourseID);
            return new SelectCourseResponse("退選成功");
        } else {
            return new SelectCourseResponse("並無加選此課程，無法退選");
        }
    }

    //查詢學生選課紀錄
    @Override
    public CoursesResponse getSelectCourseRecord(StudentCourseRequest request) {
        String studentID = request.getStudentID();
        //判斷學生ID是否為正確資料
        if (StringUtils.isEmpty(studentID)) {
            return new CoursesResponse("學號請確實填寫");
        } else if (!studentDAO.existsById(studentID)) {
            return new CoursesResponse("查無此學生");
        }

        //ID強制轉大寫，防止大小寫問題
        studentID = studentID.toUpperCase();

        //查詢學生選課紀錄，並判斷是否有查詢出紀錄
        var studentCourseList = studentCourseDAO.findByStudentID(studentID);
        if (CollectionUtils.isEmpty(studentCourseList)){
            return new CoursesResponse("無選課紀錄");
        }


        //創建課程ID清單
        var courseIDList = new ArrayList<String>();
        for (var item : studentCourseList) {
            courseIDList.add(item.getCourseID());
        }

        //進資料庫查詢後，回傳選課資料
        return new CoursesResponse(courseDAO.findAllById(courseIDList));
    }




    //查詢時間是否與目標時間重疊
    private boolean isOverlap(LocalTime start, LocalTime end, LocalTime targetStart, LocalTime targetEnd) {
        boolean overlap = false;
        if ((start.isAfter(targetStart) && start.isBefore(targetEnd)) || (targetStart.isAfter(start) && targetStart.isBefore(end))) {
            //開始時間或結束時間是否位於目標範圍內
            overlap = true;
        } else if (start.equals(targetEnd) || end.equals(targetStart)) {
            //開始時間是否等於目標結束時間，或目標結束時間是否等於開始時間
            overlap = true;
        } else if (targetStart.isBefore(start) && targetEnd.isAfter(end)) {
            //查詢的時間是否位於目標時間之間
            overlap = true;
        } else if (start.isBefore(targetStart) && end.isAfter(targetEnd)) {
            //目標時間是否包含查詢時間
            overlap = true;
        }
        return overlap;
    }
}
