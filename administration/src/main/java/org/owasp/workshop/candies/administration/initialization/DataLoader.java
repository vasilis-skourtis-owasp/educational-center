package org.owasp.workshop.candies.administration.initialization;

import org.owasp.workshop.candies.administration.dtos.StudentUser;
import org.owasp.workshop.candies.administration.model.Course;
import org.owasp.workshop.candies.administration.model.User;
import org.owasp.workshop.candies.administration.model.UserRoleValue;
import org.owasp.workshop.candies.administration.services.CourseService;
import org.owasp.workshop.candies.administration.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private CourseService courseService;
    private UserService userService;

    @Autowired
    public DataLoader(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    public void run(ApplicationArguments args) {
        List<Course> courses = getInitialCourses();
        List<StudentUser> studentUsers = getInitialStudents();


        initAdminUsers();
        initCourses(courses);
        initStudentUsers(studentUsers);

//        initStudentRegistrations(courses, studentUsers);

    }

    private void initStudentRegistrations(List<Course> courses, List<StudentUser> studentUsers) {
        for(StudentUser studentUser : studentUsers) {
            courseService.registerToCourse(courses.get(0).getName(), studentUser.getEmail());
            courseService.registerToCourse(courses.get(1).getName(), studentUser.getEmail());
            courseService.registerToCourse(courses.get(2).getName(), studentUser.getEmail());
        }
    }


    private void initAdminUsers() {
        userService.createUser(new User("vskourtis", "s0ftw@r3Candies2023", "vskourtis@owasp.org", "Vasilis", "Skourtis", UserRoleValue.ADMIN_ROLE));
    }
    private void initCourses(List<Course> courses) {
        logger.info("Started initializing courses ...");
        courses.forEach(course -> courseService.save(course));
        logger.info("Completed successfully courses initialization!");
    }
    private void initStudentUsers(List<StudentUser> students) {
        logger.info("Started initializing students ...");
        students.forEach(student -> userService.registerStudentUser(student));
        logger.info("Completed successfully students initialization!");
    }




    private List<Course> getInitialCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Maths101", "Basic Mathematics"));
        courses.add(new Course("Maths201", "Advanced Mathematics"));
        courses.add(new Course("Physics101", "Basic Physics"));
        courses.add(new Course("Physics201", "Advanced Physics"));
        courses.add(new Course("InformationSecurity101", "Basic Information Security"));
        courses.add(new Course("ApplicationSecurity101", "Basic Application Security"));
        courses.add(new Course("ApplicationSecurity201", "Advanced Application Security"));
        return courses;
    }

    private List<StudentUser> getInitialStudents() {
        List<StudentUser> students = new ArrayList<>();
        students.add(new StudentUser("mfaraday", "password", "mfaraday@gmail.com", "Michael", "Faraday"));
        students.add(new StudentUser("inewton", "inewton", "inewton@owasp.com", "Isaac", "Newton"));
        students.add(new StudentUser("aeinstein", "lightBend1879", "aeinstein@owasp.com", "Albert", "Einstein"));
        students.add(new StudentUser("jmaxwell", "cambridge", "jmaxwell@physics.com", "James", "Maxwell"));
        students.add(new StudentUser("nbohr", "atomic", "nbohr@physics.com", "Niels", "Bohr"));
        return students;
    }



}
