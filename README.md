# QuizApp 🎯

A simple and efficient **Quiz Application** built using **Spring Boot**.  
This project allows users to interact with quiz questions, designed to demonstrate the use of modern Java backend development practices.  
The app uses a PostgreSQL database for persistence and follows clean coding principles with the help of Lombok and Spring Data JPA.

---

## 🚀 Features

- Create and manage quiz questions.
- Fetch random questions for quiz attempts.
- Seamless API integration using Spring Web.
- Database integration with PostgreSQL.
- Simplified entity management with Spring Data JPA.
- Reduced boilerplate using Lombok annotations.

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok

---

## 💾 Database Design

- PostgreSQL is used as the relational database.
- Entities are mapped using Spring Data JPA.
- Environment-based configurations are handled in `application.properties` or `application.yml`.

---

## 💡 Prerequisites

- Java 17 or higher
- Maven or Gradle
- PostgreSQL installed and running

---

## ⚙️ Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/quizapp.git
   cd quizapp

2. **Configure the Database**

   Update your application.properties file with your PostgreSQL credentials:
   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update

3. **Build the Project**

   ```bash
   ./mvnw clean install
   
4. **Run the Application**

   ```bash
   ./mvnw spring-boot:run

5. **Access the app via:**

   ```bash
   http://localhost:8080/

## 📬 API Endpoints

| HTTP Method | Endpoint                           | Description                               |
|-------------|------------------------------------|-------------------------------------------|
| GET         | `/question/allQuestion`            | Fetch all quiz questions                  |
| POST        | `/question/addQuestion`            | Add a new quiz question                   |
| GET         | `/question/category/category_name` | Fetch random questions for category_name  |

---

## 🤝 Contributing

Contributions are always welcome!  
If you find any bugs, want to suggest improvements, or wish to add new features — feel free to fork the repository and submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

---

## 📜 License

Distributed under the [MIT License](https://opensource.org/licenses/MIT).
---


