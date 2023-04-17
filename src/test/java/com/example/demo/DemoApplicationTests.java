package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.entity.StudentCourse;
import com.example.demo.repository.StudentCourseDAO;
import com.example.demo.services.ifs.CoursesService;
import com.example.demo.services.ifs.StudentService;
import com.example.demo.vo.request.CoursesRequest;
import com.example.demo.vo.request.SelectCourseRequest;
import com.example.demo.vo.request.StudentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	StudentCourseDAO studentCourseDAO;
	@Autowired
	CoursesService coursesService;
	@Autowired
	StudentService studentService;

	@Test
	void contextLoads() {
		studentCourseDAO.deleteAll();

		System.out.println(studentService.selectCourse(new SelectCourseRequest("D01234567","A01")).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.selectCourse(new SelectCourseRequest("D01234567","A02")).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.selectCourse(new SelectCourseRequest("D01234567","A03")).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.selectCourse(new SelectCourseRequest("D01234567","A04")).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.selectCourse(new SelectCourseRequest("D01234567","A05")).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");

	}
}
