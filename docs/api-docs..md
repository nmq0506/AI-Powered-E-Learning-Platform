# API Design – AI-Powered E-Learning Platform

## 1. Overview

This document defines RESTful APIs for the AI-Powered E-Learning Platform.

**Base URL**

```
http://localhost:8080/api/v1
```

**Authentication**

* JWT Bearer Token

```
Authorization: Bearer <token>
```

**Common Response Format**

```json
{
  "timestamp": "2026-03-20T10:00:00Z",
  "status": 200,
  "message": "Success",
  "data": {}
}
```

---

# 2. Authentication APIs

## 2.1 Register

**POST** `/auth/register`

Request:

```json
{
  "fullName": "John Doe",
  "username": "johnd",
  "password": "123456"
}
```

Response:

```json
{
  "timestamp": "...",
  "status": 201,
  "message": "User registered successfully",
  "data": {
    "id": 1,
    "email": "john@example.com"
  }
}
```

---

## 2.2 Login

**POST** `/auth/login`

Request:

```json
{
  "username": "johnd",
  "password": "123456"
}
```

Response:

```json
{
  "timestamp": "...",
  "status": 200,
  "message": "Login successful",
  "data": {
    "token": "jwt_token_here"
  }
}
```

---

# 3. Course APIs

## 3.1 Get All Courses

**GET** `/courses`

Response:

```json
{
  "status": 200,
  "data": [
    {
      "id": 1,
      "title": "Java Basics",
      "description": "Learn Java",
      "instructorName": "John Doe"
    }
  ]
}
```

---

## 3.2 Create Course (Instructor)

**POST** `/courses`

Request:

```json
{
  "title": "Spring Boot",
  "description": "Learn Spring Boot",
  "instructorName": 1
}
```

Response:

```json
{
  "status": 201,
  "message": "Course created",
  "data": {
    "id": 10,
    "title": "Spring Boot"
  }
}
```

---

## 3.3 Get Course Detail

**GET** `/courses/{id}`

Response:

```json
{
  "status": 200,
  "data": {
    "id": 1,
    "title": "Java Basics",
    "description": "Learn Java",
    "lessons": []
  }
}
```

---

# 4. Lesson APIs

## 4.1 Get Lessons by Course

**GET** `/courses/{courseId}/lessons`

Response:

```json
{
  "status": 200,
  "data": [
    {
      "id": 1,
      "title": "Introduction",
      "videoUrl": "..."
    }
  ]
}
```

---

## 4.2 Create Lesson

**POST** `/lessons`

Request:

```json
{
  "courseId": 1,
  "title": "Intro",
  "videoUrl": "url"
}
```

Response:

```json
{
  "status": 201,
  "message": "Lesson created",
  "data": {
    "id": 5
  }
}
```

---

# 5. Enrollment APIs

## 5.1 Enroll Course

**POST** `/enrollments`

Request:

```json
{
  "courseId": 1
}
```

Response:

```json
{
  "status": 201,
  "message": "Enrolled successfully"
}
```

---

## 5.2 Get My Courses

**GET** `/enrollments/my-courses`

Response:

```json
{
  "status": 200,
  "data": [
    {
      "courseId": 1,
      "progress": 60
    }
  ]
}
```

---

# 6. Quiz APIs

## 6.1 Generate Quiz (AI)

**POST** `/quizzes/generate`

Request:

```json
{
  "courseId": 1,
  "lessonContent": "..."
}
```

Response:

```json
{
  "status": 200,
  "data": {
    "quizId": 1,
    "questions": []
  }
}
```

---

## 6.2 Submit Quiz

**POST** `/quizzes/{quizId}/submit`

Request:

```json
{
  "answers": [
    {
      "questionId": 1,
      "selectedOptionId": 2
    }
  ]
}
```

Response:

```json
{
  "status": 200,
  "data": {
    "score": 8.0
  }
}
```

---

# 7. Assignment APIs

## 7.1 Submit Assignment

**POST** `/assignments/{id}/submit`

Request:

```json
{
  "content": "My answer"
}
```

Response:

```json
{
  "status": 200,
  "data": {
    "score": 8.5,
    "feedback": "Good job"
  }
}
```

---

# 8. AI Tutor APIs

## 8.1 Chat with AI

**POST** `/ai/chat`

Request:

```json
{
  "message": "Explain this lesson",
  "courseId": 1
}
```

Response:

```json
{
  "status": 200,
  "data": {
    "reply": "AI explanation here"
  }
}
```

---

# 9. Progress APIs

## 9.1 Get Progress

**GET** `/progress/{courseId}`

Response:

```json
{
  "status": 200,
  "data": {
    "completion": 75
  }
}
```

---

# 10. Certificate APIs

## 10.1 Get Certificate

**GET** `/certificates/{courseId}`

Response:

```json
{
  "status": 200,
  "data": {
    "url": "certificate_link"
  }
}
```

---

# 11. Admin APIs

## 11.1 Get Users

**GET** `/admin/users`

Response:

```json
{
  "status": 200,
  "data": []
}
```

---

## 11.2 Approve Course

**PUT** `/admin/courses/{id}/approve`

Response:

```json
{
  "status": 200,
  "message": "Course approved"
}
```

---

## 11.3 Manage Categories

**GET** `/admin/categories`

Response:

```json
{
  "status": 200,
  "data": []
}
```

**POST** `/admin/categories`

Response:

```json
{
  "status": 201,
  "message": "Category created"
}
```

**PUT** `/admin/categories/{id}`

Response:

```json
{
  "status": 200,
  "message": "Category updated"
}
```

**DELETE** `/admin/categories/{id}`

Response:

```json
{
  "status": 200,
  "message": "Category deleted"
}
```

---

# 12. HTTP Status Codes

* 200 OK
* 201 Created
* 400 Bad Request
* 401 Unauthorized
* 403 Forbidden
* 404 Not Found
* 500 Internal Server Error

---

**End of API Document**
