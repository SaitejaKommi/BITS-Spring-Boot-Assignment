package com.academic.studentcourse.repository;

import com.academic.studentcourse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Custom JPQL with INNER JOIN to load students together with their courses.
     */
    @Query("SELECT DISTINCT s FROM Student s INNER JOIN FETCH s.courses")
    List<Student> findStudentsWithCourses();

    @Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses")
    List<Student> findAllStudentsWithCourses();

    @Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.id = :id")
    Optional<Student> findDetailedById(@Param("id") Long id);
}
