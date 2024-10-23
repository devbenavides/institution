package com.devda.institution.model.dao;

import com.devda.institution.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IStudentDAO extends JpaRepository<StudentEntity,Long> {
    List<StudentEntity> findByCurrentGrade(int currentGrade);

    @Query("FROM StudentEntity s WHERE s.age BETWEEN :startAge AND :endAge")
    List<StudentEntity> findByAgeBetween(
            @Param("startAge") int startAge,
            @Param("endAge") int endAge
    );
}
