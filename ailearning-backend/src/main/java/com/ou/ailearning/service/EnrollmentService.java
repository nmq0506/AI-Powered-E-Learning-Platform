package com.ou.ailearning.service;

import com.ou.ailearning.dto.request.EnrollmentRequest;
import com.ou.ailearning.entity.Course;
import com.ou.ailearning.entity.Enrollment;
import com.ou.ailearning.entity.User;
import com.ou.ailearning.exeption.DuplicateResourceException;
import com.ou.ailearning.exeption.ResourceNotFoundException;
import com.ou.ailearning.repository.CourseRepository;
import com.ou.ailearning.repository.EnrollmentRepository;
import com.ou.ailearning.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public void enroll(EnrollmentRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", request.courseId()));

        if (enrollmentRepository.existsByUserIdAndCourseId(user.getId(), request.courseId())) {
            throw new DuplicateResourceException("Bạn đã đăng ký khóa học này rồi!");
        }

        Enrollment enrollment = Enrollment.builder()
                .user(user)
                .course(course)
                .build();

        enrollmentRepository.save(enrollment);
    }
}
