package org.owasp.workshop.candies.administration.repository;

import org.owasp.workshop.candies.administration.model.Course;
import org.owasp.workshop.candies.administration.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface StudentRepository extends JpaRepository<Student, Long> {
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findByName(String name);
}
