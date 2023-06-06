package org.owasp.workshop.candies.administration.repository;

import org.owasp.workshop.candies.administration.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface StudentRepository extends JpaRepository<Student, Long> {
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByName(String name);
    List<Student> findByEmail(String email);
}
