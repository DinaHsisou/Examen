package com.example.exam.controller;



import com.example.exam.domain.model.Student;
import com.example.exam.domain.model.Teacher;
import com.example.exam.service.StudentService;
import com.example.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class RestStudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    public void StudentController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    public RestStudentController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> listStudent() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student,
                                              @RequestParam(value = "teacherIds", required = false) List<Long> teacherIds) {
        if (teacherIds != null) {
            List<Teacher> teachers = teacherService.getAllTeachers()
                    .stream()
                    .filter(teacher -> teacherIds.contains(teacher.getId()))
                    .collect(Collectors.toList());
            student.setTeachers(teachers);
        }

        studentService.addStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

