package org.owasp.workshop.candies.administration.services;

import org.owasp.workshop.candies.administration.dtos.StudentUser;
import org.owasp.workshop.candies.administration.model.User;

public interface UserService {

    User createUser(User user);

    User loginUser(String username, String password);

    User registerStudentUser(StudentUser studentUser);

}
