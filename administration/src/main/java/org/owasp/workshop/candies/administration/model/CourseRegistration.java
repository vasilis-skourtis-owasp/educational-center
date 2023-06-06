package org.owasp.workshop.candies.administration.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course_registrations")
public class CourseRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_fk", insertable = true, updatable = true)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_fk", insertable = true, updatable = true)
    private Course course;

    @Column(name = "registration_date")
    private Date registrationDate;

    public CourseRegistration() {}

    public CourseRegistration(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.registrationDate=new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}