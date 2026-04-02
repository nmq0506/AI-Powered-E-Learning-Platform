package com.ou.ailearning.repository;

import com.ou.ailearning.entity.Course;
import com.ou.ailearning.entity.enums.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStatus(CourseStatus status);
    List<Course> findByStatusAndActiveTrue(CourseStatus status);
}