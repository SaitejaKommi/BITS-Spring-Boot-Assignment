package com.academic.studentcourse.service;

import com.academic.studentcourse.entity.Course;
import com.academic.studentcourse.entity.Student;
import com.academic.studentcourse.repository.CourseRepository;
import com.academic.studentcourse.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void save_persistsStudentWithResolvedCourses() {
        Course course = new Course();
        course.setId(5L);
        course.setTitle("Spring");
        course.setDuration("6 weeks");

        when(courseRepository.findById(5L)).thenReturn(Optional.of(course));

        Student input = new Student();
        input.setName("Test User");
        input.setEmail("test@univ.edu");

        studentService.save(input, List.of(5L));

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(captor.capture());
        Student saved = captor.getValue();
        assertThat(saved.getName()).isEqualTo("Test User");
        assertThat(saved.getCourses()).hasSize(1);
        assertThat(saved.getCourses().get(0).getId()).isEqualTo(5L);
    }

    @Test
    void findAll_delegatesToRepository() {
        when(studentRepository.findAllStudentsWithCourses()).thenReturn(List.of());
        assertThat(studentService.findAll()).isEmpty();
        verify(studentRepository).findAllStudentsWithCourses();
    }

    @Test
    void update_changesFieldsAndCourses() {
        Course oldCourse = new Course();
        oldCourse.setId(1L);
        Student existing = new Student();
        existing.setId(10L);
        existing.setName("Old");
        existing.setEmail("old@x.edu");
        existing.getCourses().add(oldCourse);

        Course newCourse = new Course();
        newCourse.setId(2L);
        newCourse.setTitle("New");
        newCourse.setDuration("1 week");

        when(studentRepository.findDetailedById(10L)).thenReturn(Optional.of(existing));
        when(courseRepository.findById(2L)).thenReturn(Optional.of(newCourse));

        Student update = new Student();
        update.setId(10L);
        update.setName("New Name");
        update.setEmail("new@x.edu");

        studentService.update(update, List.of(2L));

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(captor.capture());
        Student out = captor.getValue();
        assertThat(out.getName()).isEqualTo("New Name");
        assertThat(out.getEmail()).isEqualTo("new@x.edu");
        assertThat(out.getCourses()).hasSize(1);
        assertThat(out.getCourses().get(0).getId()).isEqualTo(2L);
    }
}
