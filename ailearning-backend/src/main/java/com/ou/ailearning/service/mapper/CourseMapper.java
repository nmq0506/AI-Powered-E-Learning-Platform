package com.ou.ailearning.service.mapper;

import com.ou.ailearning.dto.response.CourseResponse;
import com.ou.ailearning.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getCategory().getName(),
                course.getStatus(),
                course.getActive(),
                course.getCreatedAt(),
                course.getCategory().getId(),
                course.getInstructorId()
        );
    }
}
