package com.example.cruddemo.repository;

import com.example.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentRepo implements IStudentRepo{

    private EntityManager _entityManager;

    @Autowired
    public StudentRepo(EntityManager entityManager) {
        _entityManager = entityManager;
    }

    @Override
    @Transactional
    public void create(Student student) {
        _entityManager.persist(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(int id) {
        return _entityManager.find(Student.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = _entityManager.createQuery("FROM Student", Student.class);
        return theQuery.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findByLastName(String paramLastName) {
        TypedQuery<Student> theQuery = _entityManager.createQuery("FROM Student WHERE lastName=: paramData", Student.class);
        theQuery.setParameter("paramData", paramLastName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        _entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Student studentToDelete = _entityManager.find(Student.class, id);
        _entityManager.remove(studentToDelete);
    }

    @Override
    @Transactional
    public int deleteAll() {
        return _entityManager.createQuery("DELETE FROM Student")
                            .executeUpdate();
    }
}
