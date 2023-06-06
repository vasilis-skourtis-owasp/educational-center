package org.owasp.workshop.candies.administration.controllers.ui;

import org.owasp.workshop.candies.administration.dtos.StudentUser;
import org.owasp.workshop.candies.administration.dtos.UserSessionInfo;
import org.owasp.workshop.candies.administration.model.User;
import org.owasp.workshop.candies.administration.repository.StudentRepository;
import org.owasp.workshop.candies.administration.repository.UserRepository;
import org.owasp.workshop.candies.administration.services.SessionService;
import org.owasp.workshop.candies.administration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.owasp.workshop.candies.administration.directories.URIs.*;

@Controller
@RequestMapping("/users/")
public class UserController {

    private final SessionService sessionService;
    private final UserService userService;
    private final UserRepository userRepository;  // DO I NEED IT ???
    private final StudentRepository studentRepository;

    @Autowired
    public UserController(SessionService sessionService, UserService userService, UserRepository userRepository, StudentRepository studentRepository) {
        this.sessionService = sessionService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("register")
    public String registerUser(StudentUser studentUser) {
        return REGISTER_USER;
    }

    @GetMapping("login")
    public String loginUser(User user) {
        return LOGIN_USER;
    }

    @GetMapping("list")
    public String viewUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return VIEW_USERS;
    }

    @PostMapping("create")
    public String addStudentUser(StudentUser studentUser, Model model) {
        userService.registerStudentUser(studentUser);
        return REDIRECT_TO_LOGIN_USER;
    }

    @PostMapping("login")
    public String loginUser(User user, Model model) {
        String redirectURL = REDIRECT_TO_LOGIN_USER;

        User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
        if(null!=loggedInUser){
            UserSessionInfo userSessionInfo = sessionService.createSessionForUser(loggedInUser);
            if(!sessionService.isUserAdmin(userSessionInfo.getSessionToken())) {
                userSessionInfo.setStudentID(studentRepository.findByEmail(loggedInUser.getEmail()).get(0).getId());
            }

            redirectURL = sessionService.constructPathWithSessionInfo(REDIRECT_TO_COURSES_LIST, userSessionInfo.getSessionToken());
        }

        return redirectURL;
    }

}

