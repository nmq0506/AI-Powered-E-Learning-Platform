# AI-Powered-E-Learning-Platform
## 1. Thông Tin Nhóm
- Nhóm: 4
- Thành viên: Nguyễn Minh Quân, Phạm Đỗ Nhật Tân
- GVHD: Võ Việt Khoa
## 2. Thông Tin Đề Tài
- Tên đề tài: AI-Powered-E-Learning-Platform
- Mô tả ngắn: Xây dựng LMS cho phép Student xem video, chat với AI Tutor, làm quiz AI-generated, nộp bài
tập và nhận AI feedback, xem tiến độ và nhận certificate. Instructor tạo khóa học, tạo quiz với AI assist,
chấm bài với AI hỗ trợ.
- Đối tượng sử dụng:
  +Student (End User) : Xem video, chat AI Tutor, làm quiz, nộp bài, nhận cer tif icate
  +Instructor (Business User) : Tạo khóa học, tạo quiz với AI, chấm bài với AI
  +Admin: Duyệt khóa học, quản lý users, cấu hình AI

## 3. Tính Năng Chính (MVP)
STUDENT (6 tính năng):
├── Xem video bài giảng
├── Chat với AI Tutor
├── Làm quiz (AI-generated)
├── Nộp bài tập và nhận AI feedback
├── Xem tiến độ học tập
└── Nhận certificate khi hoàn thành
INSTRUCTOR (6 tính năng):
├── Tạo khóa học (video, tài liệu)
├── Tạo quiz với AI assist
├── Xem danh sách học viên
├── Chấm bài tập (với AI hỗ trợ)
├── Gửi thông báo cho học viên
└── Xem analytics khóa học
ADMIN (6 tính năng):
├── Quản lý users (CRUD)
├── Duyệt khóa học mới
├── Quản lý categories
├── Cấu hình AI model
├── Báo cáo hệ thống
└── Quản lý thông báo 
## 4. Công Nghệ Sử Dụng
- Backend: Spring Boot
- Database: WorkbenchSQL
- Frontend: ReactJS
- AI (nếu có): OpenAI, ChatGPT
## 5. Phân Công Công Việc
| Thành viên | Công việc | Timeline |
|------------|-----------|----------|
| Nguyễn Minh Quân | Backend - Admin module | Week 1-3 |
| Phạm Đỗ Nhật Tân | Backend - Instructor module | Week 1-3 |
## 6. Timeline
## Tuần 1: 
Chốt đề tài bài tập lớn + phân công công việc từng thành viên
Tuần Giai đoạn Công việc chính Deliverables
2 Phân tích
Phân tích yêu cầu, use cases,
wireframes
docs/requirements.md
3 Thiết kế Database design, API design, k iến
trúc
docs/database-design.md,
docs/api-docs.md
4-5
Backend
Core
Setup project, entities, repositories,
basic APIs
Backend chạy được
6-7
Backend +
Auth
Authentication, Authorization,
business log ic
APIs hoàn chỉnh
8 Frontend UI components, tích hợp API Frontend cơ bản
9 Hoàn thiện
Testing, bug f ixes, AI integration
(nếu có)
Sản phẩm hoàn chỉnh
10 Bảo vệ
Documentation, video demo,
presentation
Nộp bài + Bảo vệ
