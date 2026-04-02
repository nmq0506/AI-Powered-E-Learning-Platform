package com.ou.ailearning.repository;

import com.ou.ailearning.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CourseRepository extends JpaRepository<Course, Long> {

}
