package org.owasp.workshop.candies.administration.repository;

import org.owasp.workshop.candies.administration.model.Student;
import org.owasp.workshop.candies.administration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
