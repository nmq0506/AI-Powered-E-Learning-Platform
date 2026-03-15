package com.ou.ailearning.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String videoUrl;

    private String documentUrl;

    private Integer lessonOrder;

    // Lesson thuộc Course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
