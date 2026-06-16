# NextGen Alumni Association Platform - Backend 🎓

The NextGen Alumni Association Platform is a role-based backend application designed to strengthen connections between students, alumni, and administrators. Built with Spring Boot, Spring Security, JWT Authentication, MySQL, and Cloudinary, the platform provides secure APIs for mentorship management, internship opportunities, alumni networking, announcements, notifications, event management, and user engagement.

> ⚠️ This repository contains the backend API for the platform. The frontend application is maintained separately and will be linked once publicly available.

---

## 🚀 Overview

The platform enables educational institutions to build and maintain an active alumni ecosystem by facilitating:

* Alumni Networking
* Mentorship Programs
* Internship Opportunities
* Event Management
* Announcements & Notifications
* Alumni Discovery & Search
* Role-Based Dashboards
* Secure Authentication & Authorization

The system supports three distinct user roles:

* **Student**
* **Alumni**
* **Admin**

Each role has dedicated permissions and access to features relevant to their responsibilities.

---

## ✨ Features

### Authentication & Security

* User Registration & Login
* JWT-Based Authentication
* Spring Security Integration
* Role-Based Access Control (RBAC)
* Protected API Endpoints
* Secure Password Encryption

### Student Features

* Student Profile Management
* Search and Filter Alumni Profiles
* Request Mentorship from Alumni
* Apply for Internship Opportunities
* Receive Notifications and Announcements

### Alumni Features

* Alumni Profile Management
* Profile Image Upload via Cloudinary
* Manage Mentorship Requests
* Post Internship Opportunities
* Review Student Applications

### Admin Features

* Manage Users and Platform Activity
* Publish Announcements
* Oversee Mentorship Programs
* Monitor Internship Opportunities
* Dashboard Analytics

### Platform Features

* Alumni Search & Filtering
* Mentorship Request Workflow
* Internship Posting & Application Management
* Event Creation & Management
* Notification System
* Dashboard Analytics
* Cloudinary Media Storage Integration
* Scheduled Background Tasks

---

## 🛠️ Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT Authentication
* Maven

### Database

* MySQL

### Cloud Storage

* Cloudinary

### API

* RESTful APIs

---

## 🏗️ Architecture

The application follows a layered architecture to ensure maintainability, scalability, and clean separation of concerns.

```text
src
├── main
│   ├── java
│   │   └── com.amit.alumniManagement
│   │       ├── config
│   │       │   ├── CloudinaryConfig.java
│   │       │   └── SecurityConfig.java
│   │       │
│   │       ├── controller
│   │       │   ├── AuthController.java
│   │       │   ├── AlumniController.java
│   │       │   ├── StudentProfileController.java
│   │       │   ├── InternshipController.java
│   │       │   ├── MentorshipRequestController.java
│   │       │   ├── NotificationController.java
│   │       │   └── EventController.java
│   │       │
│   │       ├── dto
│   │       ├── entity
│   │       ├── repository
│   │       ├── scheduler
│   │       ├── service
│   │       └── AlumniManagementApplication.java
│   │
│   └── resources
│       ├── application.properties
│       └── application.properties.example
│
└── test
```

### Key Modules

#### Authentication & Authorization

Handles JWT-based authentication, user login, registration, and role-based access control using Spring Security.

#### Alumni Management

Supports alumni profile creation, profile image management, and alumni discovery through search and filtering.

#### Student Management

Provides student profile management and interaction with mentorship and internship modules.

#### Mentorship System

Allows students to request mentorship and alumni to manage mentorship requests.

#### Internship Management

Supports internship posting, application submission, and opportunity management.

#### Event Management

Enables creation and management of alumni events and activities.

#### Notification System

Delivers platform-wide notifications and announcements.

#### Cloudinary Integration

Handles profile image uploads and cloud-based media storage.

#### Scheduler Services

Automates event-related background tasks and scheduled operations.

---

## 👥 User Roles & Workflows

### Student Workflow

1. Register and log in.
2. Complete student profile.
3. Search and filter alumni profiles.
4. Send mentorship requests.
5. Apply for internship opportunities.
6. Receive announcements and notifications.

### Alumni Workflow

1. Register and log in.
2. Maintain alumni profile.
3. Upload profile image.
4. Review mentorship requests.
5. Accept or reject mentorship requests.
6. Post internship opportunities.
7. Review internship applications.

### Admin Workflow

1. Manage users.
2. Publish announcements.
3. Monitor mentorship activities.
4. Oversee internship opportunities.
5. Manage platform operations.
6. Access dashboard analytics.

---

## 📡 Core Modules

### Authentication Module

* User Registration
* User Login
* JWT Token Generation
* JWT Validation
* Role-Based Authorization

### Alumni Directory Module

* Alumni Profile Management
* Search Alumni
* Filter Alumni by Criteria
* Profile Image Upload

### Mentorship Module

* Mentorship Requests
* Request Approval/Rejection
* Mentorship Tracking

### Internship Module

* Internship Posting
* Internship Applications
* Application Management

### Event Module

* Event Creation
* Event Management
* Scheduled Event Processing

### Notification Module

* User Notifications
* Platform Announcements

---

## 🗄️ Database Design

The platform uses MySQL as its primary database and leverages Spring Data JPA and Hibernate for ORM functionality.

### Core Entities

* User
* StudentProfile
* AlumniProfile
* Internship
* MentorshipRequest
* Event
* Notification
* Role
* Status

---

## ⚙️ Environment Variables

Configure the following properties before running the application:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

jwt.secret=

cloudinary.cloud-name=
cloudinary.api-key=
cloudinary.api-secret=
```

---

## ▶️ Running Locally

### Clone Repository

```bash
git clone https://github.com/Amit1374/nextgen-alumni-backend.git
```

### Navigate to Project

```bash
cd nextgen-alumni-backend
```

### Configure Database

Update the database and Cloudinary credentials in your configuration.

### Run Application

```bash
mvn spring-boot:run
```

Application will start on:

```text
http://localhost:8080
```

---

## 🔒 Security Features

* JWT Authentication
* Spring Security Authorization
* Role-Based Access Control (RBAC)
* Password Encryption
* Protected REST APIs
* Secure Access Management
* Stateless Authentication

---

## 🚀 Future Enhancements

* Real-Time Notifications
* Email Notifications
* Alumni Networking Feed
* Resume Sharing
* Event Registration System
* Docker Containerization
* Cloud Deployment
* OAuth2 Authentication (Google/GitHub)
* ### Cloud Services

- Cloudinary (Configured for future media management)

---

## 👨‍💻 Author

**Amit Vishwakarma**

* GitHub: https://github.com/Amit1374
* LinkedIn: https://www.linkedin.com/in/amit-vishwakarma-7154b8331
* Portfolio: https://amit-vishwakarma.vercel.app/
* Email: [amitvishwakarma0137@gmail.com](mailto:amitvishwakarma0137@gmail.com)

---

## ⭐ Support

If you found this project useful, consider:

* Starring the repository
* Sharing it with fellow developers
* Reporting issues and bugs
* Suggesting new features and improvements

Your feedback and contributions are always appreciated.

---

⭐ If you like this project, don't forget to star the repository!
