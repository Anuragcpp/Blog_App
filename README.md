# 📝 Spring Boot Blog Backend  
### Secure Blog API with JWT Auth, MySQL, DTO Mapping & Clean Architecture

A production-grade backend built using **Spring Boot**, **Spring Security (JWT)**, **MySQL**, **JPA/Hibernate**, and **DTO-based architecture**.  
Supports authentication, posts, categories, tags, UUID-based filtering, and draft management.

---

## 🚀 Features

### 🔐 Authentication & Security
- User Registration & Login  
- Password hashing (BCrypt)  
- Stateless JWT Authentication  
- Custom JWT filter  
- Protected routes using Spring Security  
- Get authenticated user using `@AuthenticationPrincipal` or `@RequestAttribute`  

### 📝 Blog Features
- Create, Update, Delete Posts  
- Draft vs Published posts  
- Get user-specific drafts  
- Category & Tag filtering  
- Get Post by ID  
- UUID conversion from MySQL BINARY(16) hex  

### 🛡 Validation & Error Handling
- Jakarta Validation (`@NotBlank`, `@Size`, etc.)  
- Global Exception Handling (`@ControllerAdvice`)  
- Proper error structure  
- Custom `IllegalArgumentException` & `AuthenticationException` handlers  

### 🧱 Clean Architecture
- Controller → Service → Repository pattern  
- DTO-based request/response  
- Entity isolation  
- Mappers for entity → DTO  
- Modular package structure  

---

## 🛠 Tech Stack

| Layer        | Technology |
|--------------|------------|
| Language     | Java 17 |
| Framework    | Spring Boot |
| Security     | Spring Security + JWT |
| Database     | MySQL (UUID BINARY(16)) |
| ORM          | JPA / Hibernate |
| Build Tool   | Maven |
| Validation   | Jakarta Bean Validation |
| Mapping      | Mappers / MapStruct-friendly |

---

## 📁 Project Structure

com.blog.Blog_app
│
├── config/
│ └── SecurityConfig.java
│
├── controller/
│ ├── AuthController.java
│ ├── CategoryController.java
│ ├── ErrorController.java
│ ├── PostController.java
│ └── TagController.java
│
├── domain/
│ ├── dto/
│ └── entities/
│
├── mapper/
│ ├── CategoryMapper.java
│ ├── PostMapper.java
│ └── TagMapper.java
│
├── repository/
│ ├── CategoryRepository.java
│ ├── TagRepository.java
│ ├── PostRepository.java
│ └── UserRepository.java
│
├── security/
│ └── userSecurity/
│ ├── BlogUserDetails.java
│ ├── BlogUserDetailsService.java
│ └── JwtAuthenticationFilter.java
│
├── service/
│ ├── impl/
│ └── interfaces...
│
└── utils/
├── HexToUUIDConverter.java
└── others...


--- End points ----

### 🔐 Authentication

| Method | Endpoint               | Description            |
|--------|-------------------------|------------------------|
| POST   | /api/v1/auth/login     | Login & receive JWT   |
| POST   | /api/v1/auth/register  | Register new user     |


### 🗂 Categories

| Method | Endpoint                        | Description              |
|--------|----------------------------------|--------------------------|
| GET    | /api/v1/categories               | List all categories      |
| POST   | /api/v1/categories               | Create new category      |
| DELETE | /api/v1/categories/{category_id} | Delete category by UUID  |


### 🏷 Tags

| Method | Endpoint                   | Description              |
|--------|-----------------------------|--------------------------|
| GET    | /api/v1/tags               | Fetch all tags          |
| POST   | /api/v1/tags               | Create multiple tags     |
| DELETE | /api/v1/tags/{tag_id}      | Delete tag by UUID       |


### 📝 Posts

| Method | Endpoint                    | Description                    |
|--------|------------------------------|--------------------------------|
| POST   | /api/v1/posts                | Create a post                 |
| GET    | /api/v1/posts                | Get published posts           |
| GET    | /api/v1/posts/{postId}       | Get post by ID               |
| GET    | /api/v1/posts/drafts         | Get logged-in user's drafts  |
| PUT    | /api/v1/posts/{postId}       | Update a post                |
| DELETE | /api/v1/posts/{postId}       | Delete a post                |


Filtering Posts
Supported filters:

GET /api/v1/posts?categoryId=<uuid>&tagId=<uuid>
Both parameters optional.

## 🔧 Example Requests
✔ Register User
{
  "name": "John Doe",
  "email": "john@gmail.com",
  "password": "password123"
}


## ✔ Login
{
  "email": "test@gmail.com",
  "password": "password123"
}

## ✔ Create Post
{
  "title": "TestPost",
  "content": "This is a test content",
  "categoryId": "ef0f716d-3e39-4767-9cbb-1822059bf089",
  "tagsId": ["9b8aba09-d4b8-4ef2-9ec2-40a458343867"],
  "status": "DRAFT"
}

## 🧪 Using Postman

Add headers:

Authorization: Bearer <jwt_token>
Content-Type: application/json


Then call any protected API (like /posts).

## 🛢 Database Notes

- UUIDs are stored as BINARY(16) for performance
- MySQL shows them as hex: 0xEF0F716D3E3947679CBB1822059BF089
- HexToUUIDConverter converts them to Java UUID seamlessly

## 💡 Future Enhancements

- Add Admin Role + RBAC  
- Comments System  
- File Upload (Images for posts)  
- Pagination & Sorting  
- Search by title/content  
- Refresh Tokens  
- Soft delete 


## setup_and_installation_guide: Follow the steps below to set up and run the project locally.

  ---

  📥 1. Clone the Repository

      https://github.com/Anuragcpp/Blog_App.git

  ---

  🗄 2. Setup MySQL Database

      # Create database
      CREATE DATABASE blog_app;

      # Optional charset (recommended)
      ALTER DATABASE blog_app CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

  ---

  🔑 3. Configure Application Properties

      # File location:
      src/main/resources/application.properties

      # Add the following:

      spring.datasource.url: jdbc:mysql://localhost:3306/blog_app
      spring.datasource.username: root
      spring.datasource.password: YOUR_MYSQL_PASSWORD

      spring.jpa.hibernate.ddl-auto: update
      spring.jpa.show-sql: true

      # Hibernate UUID optimization
      spring.jpa.properties.hibernate.jdbc.batch_size: 30

      # JWT Secret (must be >= 32 characters)
      jwt.secret: your-256-bit-secret-key-here-make-it-at-least-32-bytes-long

      # Generate a key at https://www.allkeysgenerator.com

  ---

  📦 4. Install Dependencies

      # Using Maven Wrapper
      ./mvnw clean install

      # Or using Maven
      mvn clean install

  ---

  ▶️ 5. Run the Application

      # Option A — Maven Wrapper
      ./mvnw spring-boot:run

      # Option B — Maven
      mvn spring-boot:run

      # Option C — IntelliJ IDEA
      1. Open project
      2. Let Maven resolve dependencies
      3. Run src/main/java/com/blog/Blog_app/BlogAppApplication.java

  ---


  🎉 Ready!
      The Spring Boot Blog Backend is now running locally. Enjoy building and extending the application! 🚀

----------------------------------------------------------------------------------------------------------------

⭐ Support

If you found this project helpful, please ⭐ star the repository!

