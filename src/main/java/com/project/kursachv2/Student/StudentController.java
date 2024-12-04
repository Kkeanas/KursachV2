package com.project.kursachv2.Student;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    // Получение студента по индетификатору
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id) {
        return studentService.getStudentById(id);
    }
    // Получение всех студентов
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    // Запись нового студента
    @PostMapping
    public Student addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentService.convertDTOtoStudent(studentDTO));
    }
    // Обновление студента по индетификатору
    @PutMapping("/{id}")
    public Student updateStudentById(@PathVariable long id, @Valid @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudentById(studentService.convertDTOtoStudent(studentDTO), id);
    }
    // Удаление студента по индетификатору
    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable long id) {
        studentService.deleteStudentById(id);
    }
}
