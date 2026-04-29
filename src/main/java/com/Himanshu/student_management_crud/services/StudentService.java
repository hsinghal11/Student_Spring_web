package com.Himanshu.student_management_crud.services;

import com.Himanshu.student_management_crud.dto.StudentRequest;
import com.Himanshu.student_management_crud.entity.Student;
import com.Himanshu.student_management_crud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo){
        this.repo = repo;
    }


    public Student createStudent(StudentRequest req){

        Student s = new Student();

        s.setName(req.getName());
        s.setEmail(req.getEmail());
        s.setCourse(req.getCourse());

        Integer id = repo.save(s);

        s.setId(id); // important

        return s;
    }


    public List<Student> getAllStudents(){
        return repo.findAll();
    }


    public Student getStudent(Integer id){
        return repo.findById(id);
    }


    public Student updateStudent(
            Integer id,
            StudentRequest req){

        Student s = new Student();
        s.setName(req.getName());
        s.setEmail(req.getEmail());
        s.setCourse(req.getCourse());

        repo.update(id,s);

        return repo.findById(id);
    }


    public void deleteStudent(Integer id){
        repo.deleteById(id);
    }

}