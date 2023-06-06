package org.owasp.workshop.candies.administration.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course_exams")
public class CourseExam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_fk", insertable = true, updatable = true)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_fk", insertable = true, updatable = true)
    private Course course;

    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "grade")
    private short grade;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    public CourseExam() {}

    public CourseExam(Student student, Course course) {
        this.student = student;
        this.course = course;
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

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public short getGrade() {
        return grade;
    }

    public void setGrade(short grade) {
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}