package com.devda.institution.service;

import com.devda.institution.model.dao.IStudentDAO;
import com.devda.institution.model.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private IStudentDAO dao;

    public StudentEntity upsert(StudentEntity student) {
        return dao.save(student);
    }

    public List<StudentEntity> createAll(List<StudentEntity> studentList) {
        return dao.saveAll(studentList);
    }

    public List<StudentEntity> getAllStudent() {
        return dao.findAll();
    }

    public Optional<StudentEntity> getById(Long idStudent) {
        return dao.findById(idStudent);
    }

    public List<StudentEntity> getByCurrentGrade(int currentGrade){
        return dao.findByCurrentGrade(currentGrade);
    }

    public List<StudentEntity> getByAgeBetween(int startAge, int endAge){
        return dao.findByAgeBetween(startAge, endAge);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    public Boolean existId(Long id){
        return dao.existsById(id);
    }
}
