package com.devda.institution.controller;

import com.devda.institution.model.entity.StudentEntity;
import com.devda.institution.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/all")
    private ResponseEntity<List<StudentEntity>> getAll() {
        List<StudentEntity> studentList = service.getAllStudent();
        return studentList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(studentList);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<StudentEntity> getById(@PathVariable("id") Long id) {
        Optional<StudentEntity> student = service.getById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/grade/{grade}")
    private ResponseEntity<List<StudentEntity>> getByCurrentGrade(@PathVariable("grade") int grade) {
        List<StudentEntity> studentList = service.getByCurrentGrade(grade);
        return studentList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(studentList);
    }

    @GetMapping(value = "/age/{startAge}/{endAge}")
    private ResponseEntity<List<StudentEntity>> getByAgeBetween(@PathVariable("startAge") int startAge,@PathVariable("endAge") int endAge) {
        List<StudentEntity> studentList = service.getByAgeBetween(startAge,endAge);
        return studentList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(studentList);
    }

    @PostMapping
    private ResponseEntity<StudentEntity> create(@RequestBody StudentEntity student) {
        try {
            return new ResponseEntity<>(service.upsert(student), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/all")
    private ResponseEntity<List<StudentEntity>> createAll(@RequestBody List<StudentEntity> listStudent) {
        try {
            return new ResponseEntity<>(service.createAll(listStudent), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<StudentEntity> update(@PathVariable("id") Long id, @RequestBody StudentEntity student) {
        Optional<StudentEntity> studentOptional = service.getById(id);
        student.setIdStudent(id);
        return studentOptional.isPresent() ?
                new ResponseEntity<>(service.upsert(student), HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        Boolean exist = service.existId(id);
        if (exist) {
            service.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
