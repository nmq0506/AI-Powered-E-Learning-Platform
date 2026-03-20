# Database Design – AI-Powered E-Learning Platform (LMS)

## 1. Overview

This document describes the Entity-Relationship Diagram for the AI-Powered E-Learning Platform.

The system supports:

* Students learning courses
* Instructors creating content
* AI-powered quiz & grading

---

## 2. Main Entities

### 2.1 User

* id (PK)
* name
* email
* password
* role (STUDENT, INSTRUCTOR, ADMIN)
* created_at

---

### 2.2 Course

* id (PK)
* title
* description
* instructor_id (FK → User)
* status
* created_at

---

### 2.3 Lesson

* id (PK)
* course_id (FK → Course)
* title
* video_url
* document_url
* lesson_order

---

### 2.4 Enrollment

* id (PK)
* student_id (FK → User)
* course_id (FK → Course)
* enrolled_at
* progress

---

### 2.5 Quiz

* id (PK)
* course_id (FK → Course)
* title
* is_ai_generated

---

### 2.6 Question

* id (PK)
* quiz_id (FK → Quiz)
* content
* type (MCQ, ESSAY)

---

### 2.7 AnswerOption

* id (PK)
* question_id (FK → Question)
* content
* is_correct

---

### 2.8 QuizAttempt

* id (PK)
* quiz_id (FK → Quiz)
* student_id (FK → User)
* score

---

### 2.9 StudentAnswer

* id (PK)
* attempt_id (FK → QuizAttempt)
* question_id (FK → Question)
* selected_option_id
* essay_answer
* ai_feedback

---

### 2.10 Assignment

* id (PK)
* course_id (FK → Course)
* title
* description
* due_date

---

### 2.11 Submission

* id (PK)
* assignment_id (FK → Assignment)
* student_id (FK → User)
* content
* score
* ai_feedback

---

### 2.12 Certificate

* id (PK)
* student_id (FK → User)
* course_id (FK → Course)
* issued_at

---

## 3. Relationships

### Core Relationships

* User (Instructor) 1 —— N Course
* Course 1 —— N Lesson
* User (Student) N —— N Course (via Enrollment)

### Quiz System

* Course 1 —— N Quiz
* Quiz 1 —— N Question
* Question 1 —— N AnswerOption
* Quiz 1 —— N QuizAttempt
* QuizAttempt 1 —— N StudentAnswer

### Assignment System

* Course 1 —— N Assignment
* Assignment 1 —— N Submission

### Certificate

* User 1 —— N Certificate
* Course 1 —— N Certificate

---

## 4. ERD Diagram (Text Form)

```
User
 ├──< Course (instructor)
 ├──< Enrollment >── Course
 ├──< QuizAttempt
 ├──< Submission
 └──< Certificate

Course
 ├──< Lesson
 ├──< Quiz
 ├──< Assignment
 ├──< Enrollment
 └──< Certificate

Quiz
 ├──< Question
 └──< QuizAttempt

Question
 └──< AnswerOption

QuizAttempt
 └──< StudentAnswer

Assignment
 └──< Submission
```

---

**End of Document**
