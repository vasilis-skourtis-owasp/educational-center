package org.owasp.workshop.candies.administration.repository;

import org.owasp.workshop.candies.administration.model.CourseExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseExamRepository extends JpaRepository<CourseExam, Long> {
}
