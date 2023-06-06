package org.owasp.workshop.candies.administration.repository;

import org.owasp.workshop.candies.administration.model.Course;
import org.owasp.workshop.candies.administration.model.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    @Query("select new Course(cr.course.name, cr.course.description) from CourseRegistration cr where cr.student.id = :studentID ")
    List<Course> findByStudentID(long studentID);
}
