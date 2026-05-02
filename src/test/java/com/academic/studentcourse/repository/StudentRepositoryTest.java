package com.academic.studentcourse.repository;

import com.academic.studentcourse.entity.Course;
import com.academic.studentcourse.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void findStudentsWithCourses_usesInnerJoinAndReturnsEnrolledStudents() {
        Course c1 = new Course();
        c1.setTitle("Test Course");
        c1.setDuration("4 weeks");
        entityManager.persist(c1);

        Student s1 = new Student();
        s1.setName("Repo Student");
        s1.setEmail("repo@test.edu");
        s1.getCourses().add(c1);
        entityManager.persist(s1);

        Student s2 = new Student();
        s2.setName("Lonely Student");
        s2.setEmail("lonely@test.edu");
        entityManager.persist(s2);

        entityManager.flush();
        entityManager.clear();

        List<Student> result = studentRepository.findStudentsWithCourses();

        assertThat(result).extracting(Student::getName).contains("Repo Student");
        assertThat(result).extracting(Student::getName).doesNotContain("Lonely Student");
        Student loaded = result.stream()
                .filter(s -> "Repo Student".equals(s.getName()))
                .findFirst()
                .orElseThrow();
        assertThat(loaded.getCourses()).hasSize(1);
        assertThat(loaded.getCourses().get(0).getTitle()).isEqualTo("Test Course");
    }
}
