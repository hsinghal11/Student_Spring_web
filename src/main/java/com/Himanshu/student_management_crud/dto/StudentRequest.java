package com.Himanshu.student_management_crud.dto;

import lombok.Data;

@Data
public class StudentRequest {

    private String name;
    private String email;
    private String course;

}