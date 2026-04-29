package com.Himanshu.student_management_crud.repository;

import com.Himanshu.student_management_crud.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE
    public Integer save(Student student){

        String sql =
                "INSERT INTO students(name,email,course) " +
                        "VALUES(?,?,?) RETURNING id";

        Integer generatedId =
                jdbcTemplate.queryForObject(
                        sql,
                        Integer.class,
                        student.getName(),
                        student.getEmail(),
                        student.getCourse()
                );

        return generatedId;
    }

    // READ ALL
    public List<Student> findAll() {

        String sql = "SELECT * FROM students";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Student(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("course")
                        )
        );
    }

    // READ BY ID
    public Student findById(Integer id) {

        String sql =
                "SELECT * FROM students WHERE id=?";

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) ->
                        new Student(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("course")
                        ),
                id
        );
    }


    // UPDATE
    public int update(Integer id, Student student) {

        String sql =
                "UPDATE students SET name=?,email=?,course=? WHERE id=?";

        return jdbcTemplate.update(
                sql,
                student.getName(),
                student.getEmail(),
                student.getCourse(),
                id
        );
    }

    // DELETE
    public int deleteById(Integer id) {

        String sql =
                "DELETE FROM students WHERE id=?";

        return jdbcTemplate.update(sql, id);
    }

}