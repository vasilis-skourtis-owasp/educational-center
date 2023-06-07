package org.owasp.workshop.candies.administration.controllers.ui;

import org.owasp.workshop.candies.administration.model.Course;
import org.owasp.workshop.candies.administration.model.CourseRegistration;
import org.owasp.workshop.candies.administration.repository.CourseRegistrationRepository;
import org.owasp.workshop.candies.administration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.owasp.workshop.candies.administration.directories.URIs.*;

@Controller
@RequestMapping("/courses/registrations/")
public class CourseRegistrationController {

    private final CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    public CourseRegistrationController(CourseRegistrationRepository courseRegistrationRepository) {
        this.courseRegistrationRepository = courseRegistrationRepository;
    }


    //    @GetMapping("")
//    public String showCourseRegistrations(@RequestParam String userrole, @RequestParam String session, Model model) {
//        model.addAttribute("courseExams", courseRegistrationRepository.findAll());
//        return EDIT_COURSE;
//    }

    @GetMapping("list")
    public String showCourseRegistrations(Model model) {
        model.addAttribute("courseExams", courseRegistrationRepository.findAll());
        return VIEW_COURSES_REGISTRATIONS;
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        CourseRegistration courseRegistration = courseRegistrationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        model.addAttribute("courseExam", courseRegistration);
        return UPDATE_COURSE_GRADE;
    }

    @PostMapping("update/{id}")
    public String updatecourse(@PathVariable("id") long id, CourseRegistration courseRegistration, Model model) {
        courseRegistrationRepository.save(courseRegistration);
        model.addAttribute("courses", courseRegistrationRepository.findAll());
        return REDIRECT_TO_ADMIN_COURSES_REGISTRATIONS_LIST;
    }


}

