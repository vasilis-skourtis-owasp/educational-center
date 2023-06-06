package org.owasp.workshop.candies.administration.services;

import org.owasp.workshop.candies.administration.model.Course;
import org.owasp.workshop.candies.administration.model.CourseRegistration;
import org.owasp.workshop.candies.administration.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseExamRepository courseExamRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CourseExamRepository courseExamRepository, CourseRegistrationRepository courseRegistrationRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.courseExamRepository = courseExamRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void registerToCourse(long courseID, long studentID) {
        CourseRegistration courseRegistration = new CourseRegistration(studentRepository.findById(studentID).get(), courseRepository.findById(courseID).get());
        courseRegistrationRepository.save(courseRegistration);
    }

    @Override
    public void registerToCourse(String courseName, String email) {
        registerToCourse(courseRepository.findByName(courseName).get(0).getId(), studentRepository.findByEmail(email).get(0).getId());
    }

    @Override
    public List<Course> findByStudentId(long studentID) {
        return courseRegistrationRepository.findByStudentID(studentID);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }
}
