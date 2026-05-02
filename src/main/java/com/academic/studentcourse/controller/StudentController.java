package com.academic.studentcourse.controller;

import com.academic.studentcourse.entity.Student;
import com.academic.studentcourse.service.CourseService;
import com.academic.studentcourse.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.findAll());
        return "add-student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student,
                              @RequestParam(value = "courseIds", required = false) List<Long> courseIds,
                              Model model) {
        try {
            studentService.save(student, courseIds);
            return "redirect:/students";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Could not save student: " + ex.getMessage());
            model.addAttribute("student", student);
            model.addAttribute("courses", courseService.findAll());
            return "add-student";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id);
        if (student == null) {
            model.addAttribute("errorMessage", "Student not found.");
            return "error";
        }
        model.addAttribute("student", student);
        model.addAttribute("courses", courseService.findAll());
        return "update-student";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student,
                                @RequestParam(value = "courseIds", required = false) List<Long> courseIds,
                                Model model) {
        try {
            studentService.update(student, courseIds);
            return "redirect:/students";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Could not update student: " + ex.getMessage());
            Student refreshed = studentService.findById(student.getId());
            model.addAttribute("student", refreshed != null ? refreshed : student);
            model.addAttribute("courses", courseService.findAll());
            return "update-student";
        }
    }
}
