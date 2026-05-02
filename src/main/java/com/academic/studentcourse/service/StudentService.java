package com.academic.studentcourse.service;

import com.academic.studentcourse.entity.Course;
import com.academic.studentcourse.entity.Student;
import com.academic.studentcourse.repository.CourseRepository;
import com.academic.studentcourse.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Student save(Student student, List<Long> courseIds) {
        attachCourses(student, courseIds);
        return studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAllStudentsWithCourses();
    }

    @Transactional(readOnly = true)
    public List<Student> findStudentsWithCoursesInnerJoin() {
        return studentRepository.findStudentsWithCourses();
    }

    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return studentRepository.findDetailedById(id).orElse(null);
    }

    @Transactional
    public Student update(Student student, List<Long> courseIds) {
        Student existing = studentRepository.findDetailedById(student.getId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + student.getId()));
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.getCourses().clear();
        attachCourses(existing, courseIds);
        return studentRepository.save(existing);
    }

    private void attachCourses(Student student, List<Long> courseIds) {
        if (courseIds == null || courseIds.isEmpty()) {
            return;
        }
        Set<Long> unique = new HashSet<>(courseIds);
        List<Course> resolved = new ArrayList<>();
        for (Long courseId : unique) {
            courseRepository.findById(courseId).ifPresent(resolved::add);
        }
        student.getCourses().addAll(resolved);
    }
}
