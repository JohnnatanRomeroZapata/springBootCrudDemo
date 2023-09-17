package com.example.cruddemo.repository;

import com.example.cruddemo.entity.Student;

import java.util.List;

public interface IStudentRepo {

    void create(Student student);

    Student findById(int id);

    List<Student> findAll();

    List<Student> findByLastName(String param);

    void update(Student student);

    void delete(int id);

    int deleteAll();
}
