package org.owasp.workshop.candies.administration.controllers.ui;

import org.owasp.workshop.candies.administration.model.Course;
import org.owasp.workshop.candies.administration.repository.CourseExamRepository;
import org.owasp.workshop.candies.administration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.owasp.workshop.candies.administration.directories.URIs.*;

@Controller
@RequestMapping("/courses/exams/")
public class CourseExamController {

    private final CourseExamRepository courseExamRepository;

    @Autowired
    public CourseExamController(CourseExamRepository courseExamRepository) {
        this.courseExamRepository = courseExamRepository;
    }


}

