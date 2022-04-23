package com.example.crudweb.service;

import java.util.List;

import com.example.crudweb.model.Student;

public interface StudentService {
    List<Student> getAllStudent();
    void saveStudent(Student student);
    Student getStudentByID(long id);
    void updateStudent(Student student);
    void deleteStudent(long id);
}
