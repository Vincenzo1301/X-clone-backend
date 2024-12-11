# X Clone Backend

## Purpose of the Service

The backend of the X Clone application provides a robust API to handle key functionalities like user data management,
tweet creation, and interactions. The service facilitates seamless operations by managing user accounts, posts, and
relationships between users and their posts, ensuring a smooth experience in the X Clone ecosystem.

## Used Technologies

<div align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" height="41" alt="Spring logo" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" height="41" alt="Java logo" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-plain-wordmark.svg" height="41" alt="PostgreSQL logo" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-plain-wordmark.svg" height="41" alt="Docker logo" />
</div>

### Prerequisites

To build and run this project, ensure the following tools are installed on your machine:

- **OpenJDK 21**: Required for running Java applications.
- **Maven**: Dependency management and build tool for Java projects.
- **Docker**: To run a local PostgreSQL instance for the application.

These technologies combine to offer a modern and scalable backend solution for the **X Clone** project.

## Running the Application Locally

To run the application on your local machine:

- Ensure Docker is running.
- Start a Docker container with PostgreSQL by executing:
  ```bash
  docker run --name xclone-backend-db -e TZ=Europe/Berlin -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1234 -e POSTGRES_DB=xclone-backend-db -p 5432:5432 -d postgres
    ```
- Launch the application by executing the main method in the org.xclone.Application class in your IDE or run it with
  Maven using:
  ```bash
  mvn spring-boot:run
  ```

## License

This project is licensed under the MIT License - see the LICENSE file for details.