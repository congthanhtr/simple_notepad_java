package com.example.crudweb.controller;

import com.example.crudweb.model.Student;
import com.example.crudweb.service.StudentService;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor @AllArgsConstructor
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String getAllStudent(Model model) {
        model.addAttribute("students", studentService.getAllStudent());
        return "index";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student  = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }

    // process after submit create student form
    @PostMapping("/")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/students/{id}")
    public String getStudent(@PathVariable("id") long id, Model model) {
        Student student = studentService.getStudentByID(id);
        model.addAttribute("student", student);
        return "get_student";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }

    @GetMapping("/students/edit/{id}")
    public String createUpdateStudentForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("student", studentService.getStudentByID(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@ModelAttribute("student") Student student, @PathVariable("id")long id) {
        Student existingStudent = studentService.getStudentByID(id);

        existingStudent.setEmail(student.getEmail());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());

        studentService.saveStudent(existingStudent);
        return "redirect:/";
    }

    @GetMapping("/students/import")
    public String getImportStudentForm(Model model) {
        model.addAttribute("file", new File(""));
        return "import_student";
    }

    @PostMapping("/students/import")
    public String importStudentForm(@ModelAttribute("file") File file) {
        String filePath = file.getAbsolutePath();
        File file1 = new File(filePath);
        studentService.importStudentFromFile(file1);
        return "redirect:/";
    }
}
