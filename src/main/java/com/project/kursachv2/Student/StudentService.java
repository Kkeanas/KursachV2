package com.project.kursachv2.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public boolean studentExitsById(long id) {
        return studentRepository.existsById(id);
    }

    public Student getStudentById(long id) {
        return studentRepository.getReferenceById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudentById(Student student, long id) {
        if (studentExitsById(id)) {
        student.setId(id);
        return studentRepository.save(student);
        } else {
            throw new DataIntegrityViolationException("Студента с id="+id+" не существует");
        }
    }

    public void deleteStudentById(long id) {
        if (studentExitsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Студента с id="+id+" не существует");
        }
    }

    public Student convertDTOtoStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStuFirstName(studentDTO.getStuFirstName());
        student.setStuSecondName(studentDTO.getStuSecondName());
        student.setStuSurName(studentDTO.getStuSurName());
        student.setStuAddress(studentDTO.getStuAddress());
        student.setStuPhone(studentDTO.getStuPhone());
        student.setStuEmail(studentDTO.getStuEmail());
        student.setStuComment(studentDTO.getStuComment());
        student.setStuCommentFromStudent(studentDTO.getStuCommentFromStudent());
        return student;
    }
}
