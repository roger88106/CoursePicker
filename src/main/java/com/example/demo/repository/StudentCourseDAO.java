package com.example.demo.repository;

import com.example.demo.entity.PrimaryKeys.StudentCourseID;
import com.example.demo.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseDAO extends JpaRepository<StudentCourse, StudentCourseID> {
    List<StudentCourse> findByStudentID(String studentID);
    List<StudentCourse> findByCourseID(String courseID);
    long countByCourseID(String courseID);;
}
