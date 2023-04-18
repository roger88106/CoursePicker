package com.example.demo;

import com.example.demo.repository.CourseDAO;
import com.example.demo.repository.StudentCourseDAO;
import com.example.demo.repository.StudentDAO;
import com.example.demo.services.ifs.CoursesService;
import com.example.demo.services.ifs.StudentService;
import com.example.demo.vo.request.CoursesRequest;
import com.example.demo.vo.request.StudentCourseRequest;
import com.example.demo.vo.request.StudentRequest;
import com.example.demo.vo.response.CoursesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	StudentCourseDAO studentCourseDAO;
	@Autowired
	StudentDAO studentDAO;
	@Autowired
	CourseDAO courseDAO;
	@Autowired
	CoursesService coursesService;
	@Autowired
	StudentService studentService;

	@Test
	void testCoursesAdd(){
		studentCourseDAO.deleteAll();
		studentDAO.deleteAll();
		courseDAO.deleteAll();

		System.out.println("\n新增課程\n");
		System.out.println(coursesService.createCourse(new CoursesRequest("c01","aaa",
				1, "12:00","12:50",1)).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(coursesService.createCourse(new CoursesRequest("c02","bbb",
				1, "11:00","12:00",1)).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(coursesService.createCourse(new CoursesRequest("c03","ccc",
				1, "13:00","14:00",1)).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(coursesService.createCourse(new CoursesRequest("c04","aaa",
				1, "11:00","15:00",1)).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(coursesService.createCourse(new CoursesRequest("c05","eee",
				1, "12:10","12:20",1)).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");

		System.out.println("\n新增學生\n");
		System.out.println(studentService.createStudent(new StudentRequest("D01234567","aaa")));
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.createStudent(new StudentRequest("D012345672","aaa")));
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.createStudent(new StudentRequest("D012345673","aaa")));
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.createStudent(new StudentRequest("D012345674","aaa")));
		System.out.println("\n------------------------------------------------------------------------------------------------\n");

		System.out.println();
		System.out.println("\n選課\n");
		System.out.println(studentService.selectCourse(new StudentCourseRequest("D01234567","c01")).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.selectCourse(new StudentCourseRequest("D01234567","c02")).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.selectCourse(new StudentCourseRequest("D01234567","c03")).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.selectCourse(new StudentCourseRequest("D01234567","c04")).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		System.out.println(studentService.selectCourse(new StudentCourseRequest("D01234567","c05")).getMsg());
		System.out.println("\n------------------------------------------------------------------------------------------------\n");

		CoursesResponse a = coursesService.selectCourse(new CoursesResponse("C03", null, null, null, null, null));
		CoursesResponse b = coursesService.selectCourse(new CoursesResponse(null,"aaa",null,null,null,null));
	}

}
