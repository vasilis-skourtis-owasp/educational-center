package org.owasp.workshop.candies.administration.controllers.ui;

import org.owasp.workshop.candies.administration.model.Student;
import org.owasp.workshop.candies.administration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.owasp.workshop.candies.administration.directories.URIs.*;

@Controller
@RequestMapping("/students/")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("signup")
    public String showSignUpForm(Student student) {
        return ADD_STUDENT;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return VIEW_STUDENTS;
    }

    @PostMapping("add")
    public String addStudent(Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return ADD_STUDENT;
        }

        studentRepository.save(student);
        return REDIRECT_TO_STUDENTS_LIST;
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return EDIT_STUDENT;
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id, Student student, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return EDIT_STUDENT;
        }

        studentRepository.save(student);
        model.addAttribute("students", studentRepository.findAll());
        return REDIRECT_TO_STUDENTS_LIST;
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        studentRepository.delete(student);
        model.addAttribute("students", studentRepository.findAll());
        return REDIRECT_TO_STUDENTS_LIST;
    }
}

