package org.owasp.workshop.candies.administration.services;

import org.owasp.workshop.candies.administration.model.Course;

import java.util.List;

public interface CourseService {

    void registerToCourse(long courseID, long studentID);

//    void registerToCourse(long courseID, String username);

    void registerToCourse(String courseName, String email);

    List<Course> findByStudentId(long studentID);

    Course save(Course course);
}
