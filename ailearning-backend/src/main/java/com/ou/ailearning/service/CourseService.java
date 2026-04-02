package com.ou.ailearning.service;

import com.ou.ailearning.dto.request.CourseRequest;
import com.ou.ailearning.dto.response.CourseResponse;
import com.ou.ailearning.entity.Category;
import com.ou.ailearning.entity.Course;
import com.ou.ailearning.entity.enums.CourseStatus;
import com.ou.ailearning.exeption.ResourceNotFoundException;
import com.ou.ailearning.repository.CategoryRepository;
import com.ou.ailearning.repository.CourseRepository;
import com.ou.ailearning.service.mapper.CourseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository,
                         CategoryRepository categoryRepository,
                         CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.courseMapper = courseMapper;
    }

    @Transactional
    public CourseResponse createCourse(CourseRequest request) {

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", request.categoryId()));

        Course course = Course.builder()
                .title(request.title())
                .description(request.description())
                .instructorId(request.instructorId())
                .category(category)
                .status(CourseStatus.PENDING) // chờ admin duyệt
                .active(true)
                .build();

        Course saved = courseRepository.save(course);

        return courseMapper.toResponse(saved);
    }
    // ✅ UPDATE
    @Transactional
    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));

        if (request.title() != null) {
            course.setTitle(request.title());
        }

        if (request.description() != null) {
            course.setDescription(request.description());
        }

        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", request.categoryId()));
            course.setCategory(category);
        }

        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));

        course.setActive(false);
        courseRepository.save(course);

    }

    @Transactional(readOnly = true)
    public CourseResponse getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));

        return courseMapper.toResponse(course);
    }
    @Transactional
    public void approveCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));

        course.setStatus(CourseStatus.APPROVED);
        courseRepository.save(course);
    }
    @Transactional
    public void rejectCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));

        course.setStatus(CourseStatus.REJECTED);
        courseRepository.save(course);
    }
    @Transactional(readOnly = true)
    public List<CourseResponse> getPendingCourses() {
        return courseRepository
                .findByStatusAndActiveTrue(CourseStatus.PENDING)
                .stream()
                .map(courseMapper::toResponse)
                .toList();
    }
}