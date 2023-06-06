package org.owasp.workshop.candies.administration.controllers.ui;

import org.owasp.workshop.candies.administration.model.Course;
import org.owasp.workshop.candies.administration.repository.CourseRepository;
import org.owasp.workshop.candies.administration.services.CourseService;
import org.owasp.workshop.candies.administration.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.owasp.workshop.candies.administration.directories.URIs.*;

@Controller
@RequestMapping("/courses/")
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final SessionService sessionService;

    @Autowired
    public CourseController(CourseRepository courseRepository, CourseService courseService, SessionService sessionService) {
        this.courseRepository = courseRepository;
        this.courseService = courseService;
        this.sessionService = sessionService;
    }

    @GetMapping("create")
    public String createCourse(Course course) {
        return ADD_COURSE;
    }

    @GetMapping("list/public")
    public String showPublicCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return VIEW_PUBLIC_COURSES;
    }

    @GetMapping("list")
    public String showCourses(@RequestParam Long studentID, @RequestParam String userrole, @RequestParam String session, Model model) {
        if(!sessionService.isUserLoggedIn(session))
            return VIEW_PUBLIC_COURSES;

        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("userSessionInfo", sessionService.getUserSessionInfo(session));

        return VIEW_COURSES;
    }

    @GetMapping("admin/list")
    public String showAdminCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return VIEW_COURSES_ADMIN;
    }

    @PostMapping("add")
    public String addCourse(Course course, Model model) {
        courseRepository.save(course);
        return REDIRECT_TO_ADMIN_COURSES_LIST;
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        model.addAttribute("course", course);
        return EDIT_COURSE;
    }

    @PostMapping("update/{id}")
    public String updatecourse(@PathVariable("id") long id, Course course, Model model) {
        courseRepository.save(course);
        model.addAttribute("courses", courseRepository.findAll());
        return REDIRECT_TO_ADMIN_COURSES_LIST;
    }


    @GetMapping("register/{id}")
    public String registerCourse(@PathVariable("id") long id, @RequestParam Long studentID, @RequestParam String userrole, @RequestParam String session, Model model) {
        //TODO

        return sessionService.constructPathWithSessionInfo(REDIRECT_TO_MY_COURSES_LIST, session);
    }

    @GetMapping("mylist")
    public String showMyCourses(@RequestParam Long studentID, @RequestParam String userrole, @RequestParam String session, Model model) {
        if(!sessionService.isUserLoggedIn(session))
            return REDIRECT_TO_HOME;

        model.addAttribute("courses", courseService.findByStudentId(studentID));
        model.addAttribute("userSessionInfo", sessionService.getUserSessionInfo(session));
        return VIEW_STUDENT_COURSES;
    }


    @GetMapping("delete/{id}")
    public String deletecourse(@PathVariable("id") long id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        courseRepository.delete(course);
        model.addAttribute("courses", courseRepository.findAll());
        return REDIRECT_TO_ADMIN_COURSES_LIST;
    }
}

