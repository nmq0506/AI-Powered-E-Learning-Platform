package com.ou.ailearning.controller;

import com.ou.ailearning.dto.request.CourseRequest;
import com.ou.ailearning.dto.response.CourseResponse;
import com.ou.ailearning.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public CourseResponse createCourse(@RequestBody @Valid CourseRequest request) {
        return courseService.createCourse(request);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public CourseResponse updateCourse(
            @PathVariable Long id,
            @RequestBody CourseRequest request
    ) {
        return courseService.updateCourse(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "Khoa hoc duoc xoa thanh cong";
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public CourseResponse getCourse(@PathVariable Long id) {
        return courseService.getById(id);
    }
    @GetMapping("/admin/pending")
    public List<CourseResponse> getPendingCourses() {
        return courseService.getPendingCourses();
    }

    @PatchMapping("/admin/{id}/approve")
    public void approveCourse(@PathVariable Long id) {

        courseService.approveCourse(id);
    }

    @PatchMapping("/admin/{id}/reject")
    public void rejectCourse(@PathVariable Long id) {
        courseService.rejectCourse(id);
    }

}
