package com.example.studentmanagement.service;

import java.util.List;

import com.example.studentmanagement.model.Student;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentByID(long id);
    Student updateStudent(Student student);
    void deleteStudent(long id);
}
