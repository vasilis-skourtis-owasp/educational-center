package org.owasp.workshop.candies.administration.services;

import org.owasp.workshop.candies.administration.dtos.StudentUser;
import org.owasp.workshop.candies.administration.model.Student;
import org.owasp.workshop.candies.administration.model.User;
import org.owasp.workshop.candies.administration.repository.StudentRepository;
import org.owasp.workshop.candies.administration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return null;
    }

    @Override
    public User loginUser(String username, String password) {
        //TODO
        User user = userRepository.findByUsername(username);
        if(null!=user) {
            user.getPassword().equals(password);
        }

        return user;
    }

    @Override
    public User registerStudentUser(StudentUser studentUser) {
        //TODO
        User newUser = userRepository.save(new User(studentUser));
        Student newStudent = studentRepository.save(new Student(newUser));
        return newUser;
    }


}
