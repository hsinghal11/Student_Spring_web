package com.Himanshu.student_management_crud.Controllers;

import com.Himanshu.student_management_crud.dto.StudentRequest;
import com.Himanshu.student_management_crud.entity.Student;
import com.Himanshu.student_management_crud.services.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }


    // CREATE
    @PostMapping
    public ResponseEntity<Student> createStudent(
            @RequestBody StudentRequest request){

        Student student = service.createStudent(request);

        return new ResponseEntity<>(
                student,
                HttpStatus.CREATED
        );
    }


    // READ ALL
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){

        return ResponseEntity.ok(
                service.getAllStudents()
        );
    }


    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable Integer id){

        return ResponseEntity.ok(
                service.getStudent(id)
        );
    }


    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Integer id,
            @RequestBody StudentRequest req){

        return ResponseEntity.ok(
                service.updateStudent(id,req)
        );
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Integer id){

        service.deleteStudent(id);

        return ResponseEntity.ok(
                "Student deleted successfully"
        );
    }

}