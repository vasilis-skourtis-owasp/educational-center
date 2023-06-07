package org.owasp.workshop.candies.administration.controllers.ui;

import org.owasp.workshop.candies.administration.dtos.UserSessionInfo;
import org.owasp.workshop.candies.administration.model.Course;
import org.owasp.workshop.candies.administration.model.CourseRegistration;
import org.owasp.workshop.candies.administration.repository.CourseRegistrationRepository;
import org.owasp.workshop.candies.administration.repository.CourseRepository;
import org.owasp.workshop.candies.administration.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.owasp.workshop.candies.administration.directories.URIs.*;
import static org.owasp.workshop.candies.administration.model.UserRoleValue.ADMIN_ROLE;

@Controller
@RequestMapping("/courses/registrations/")
public class CourseRegistrationController {

    private final CourseRegistrationRepository courseRegistrationRepository;

    private final SessionService sessionService;

    @Autowired
    public CourseRegistrationController(CourseRegistrationRepository courseRegistrationRepository, SessionService sessionService) {
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.sessionService = sessionService;
    }


    @GetMapping("list")
    public String showCourseRegistrations(@RequestParam Long studentID, @RequestParam String userrole, @RequestParam String session, Model model) {
        if(!sessionService.isUserLoggedIn(session) || !sessionService.isUserAdmin(session) )
            return REDIRECT_TO_HOME;

        model.addAttribute("courseExams", courseRegistrationRepository.findAll());
        model.addAttribute("userSessionInfo", sessionService.getUserSessionInfo(session));

        return VIEW_COURSES_REGISTRATIONS;
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, @RequestParam Long studentID, @RequestParam String userrole, @RequestParam String session, Model model) {
        if(!sessionService.isUserLoggedIn(session) || !sessionService.isUserAdmin(session) )
            return REDIRECT_TO_HOME;

        CourseRegistration courseRegistration = courseRegistrationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        model.addAttribute("courseExam", courseRegistration);
        model.addAttribute("userSessionInfo", sessionService.getUserSessionInfo(session));
        return UPDATE_COURSE_GRADE;
    }

    @PostMapping("update/{id}")
    public String updatecourse(@PathVariable("id") long id, CourseRegistration courseRegistration, UserSessionInfo userSessionInfo,
                               @RequestParam Long studentID, @RequestParam String userrole, @RequestParam String session,
                               Model model) {
        if(!sessionService.isUserLoggedIn(session) || !sessionService.isUserAdmin(session) )
            return REDIRECT_TO_HOME;

        courseRegistrationRepository.save(courseRegistration);
        model.addAttribute("courses", courseRegistrationRepository.findAll());

        return sessionService.constructPathWithSessionInfo(REDIRECT_TO_ADMIN_COURSES_REGISTRATIONS_LIST, session);

//        return REDIRECT_TO_ADMIN_COURSES_REGISTRATIONS_LIST;
    }


}

