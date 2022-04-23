package com.example.crudweb.service.serviceimpl;

import java.util.List;

import com.example.crudweb.model.Student;
import com.example.crudweb.repository.StudentRepository;
import com.example.crudweb.service.StudentService;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent(Student student) {
        // TODO Auto-generated method stub
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        // TODO Auto-generated method stub
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByID(long id) {
        // TODO Auto-generated method stub
        return studentRepository.getById(id);
    }

    @Override
    public void updateStudent(Student student) {
        // TODO Auto-generated method stub
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        // TODO Auto-generated method stub
        studentRepository.deleteById(id);
    }
    
}
