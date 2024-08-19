# Socal Assistance Fund API Version 1

## Project Overview

Ministry of Labour and Social Protection (MoGLSD) is in the process
of establishing a Social Assistance Fund. The Social Protection Fund will consolidate
monetary resources from state and non-state actors with the aim of disbursing it to the
needy and vulnerable persons on a timely and predictable manner. The applicants will be
identified on demand basis and assessed based on determination of their vulnerability and
income status. An applicant can apply for one or more social assistance programmes. Once
eligibility has been determined, then the applicants will be informed by email or through
SMS alerts.

## Requirements

Before running this project, ensure you have the following installed on your machine:

- Install JDK 17
- Maven
- MySQL
- Apache Server (The API will run an embed Apache Server)

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/jmwantisi/SocialAssistanceFundApiV1
cd SocialAssistanceFundApiV1
```

### 2. Configure Database while in the project directory
```bash
cd src/main/resources/
find file name: application.properties and edit to your needs
E.g:
spring.datasource.url=jdbc:mysql://localhost:3306/social_assistance_fund
spring.datasource.username=<username>
spring.datasource.password=<password>
```
### 2. Build Project while in the directory

```bash
mvn clean install
```

### 3. Run API while in the directory

```bash
mvn spring-boot:run
```

### 4. You can access API endpoints in the broswer now

- Feature 1: <host_domain>/swagger-ui/index.html#
