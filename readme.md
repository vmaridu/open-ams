# Open Source Academic Management System

## Refer API Documentation [HERE](https://ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443/swagger-ui.html)

#### What is OpenAMS ?
 OpenAMS is a Open Source Project which provides various services to maintain an Academic Institute.
 > Ex :
  - Maintaining Student Records
  - Taking Attendance
  - Uploading Grades
  - Mailing Student Progress Reports to Parents etc...

  Web Services are developed using Java 8 and Spring Boot. UI developed using Bootstrap and AngularJS.
  Native Mobile Apps developed using PhoneGap

#### Goals of OpenAMS
  - Providing Academic Institutes with FREE and EASILY Maintainable Software.
  - Share my knowledge of building great softwares.

#### What you can learn here ?
  - Java 8 in Real time
  - Server SSL Configuration
  - Using Proper Git Branching Model
  - Designing
      - ER Model from Real World Entities
      - Domain Model Classes & Using them in Hibernate (Ex : Using ENUMS as Status Messages instead of Strings)
      - Proper REST API (Richardson Maturity Model Level - 2 Standard, Pagination in Rest APIs)
  - Documenting REST APIS (JSONDOCS,Swagger)
  - Usage of Spring AOP, Data JPA, Boot, Security, Actuator
  - Data Auditing using Hibernate ENVARS.
  - Exception Handling with Spring Web MVC
  - Externalizing Configurations.
  - Using In Memory Databases (Ex : H2)
  - Writing Integration Test Cases
  - Writing Unit Test cases with Mocking (Power Mock)
  - Gradle
  - Docker
  - and Ofcourse writing GitHub Markups :)

#### Technologies Used
 - Java 8
 - Gradle
 - Docker
 - Spring Development Tool Suite 3.8.3 (IDE)
 - Spring 4
   - Spring Boot
   - Spring Boot CLI
   - Spring Data JPA
   - Hibernate
   - Spring Test
   - Spring Security
   - Spring Web MVC (Restful Webservices)
 - MySQL 5.6
 - H2 In Memory Database
 - MySQL Workbench
 - GIT
 - Bootstrap
 - Angular JS
 - Pencil 2.* (Design and Wire-frames)
 - ArgoUML
 - Swagger


#### Downloads

 - [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 - [Spring STS 3.8.3](https://spring.io/tools)
 - [MySQL 5.6](http://dev.mysql.com/downloads/)
 - [MySQL Workbench](http://dev.mysql.com/downloads/)
 - [Pencil 2.*](http://evoluspencil.googlecode.com/files/Pencil-2.0.5.win32.installer.exe)
 - [ArgoUML](http://sourceforge.net/projects/argouml.mirror/)
 - [AngularJS Plugin](http://marketplace.eclipse.org/content/angularjs-eclipse)


#### Repository Directory Structure
 - doc        : Contains Wireframes, Functional Documents
 - rest-api   : Rest Web Service Application Sources
 - web-ui     : Web GUI Sources
 - phonegap   : Multi Mobile Platform Native Apps
 - docker     : Docker images for easy deployment



### Notes
  -  Build and Installation instructions included inside module specific directories.



###  Quick Setup

#### Software Requirments
  - JDK 8
  - Docker version 1.11.1 (Only needed for Docker Orchestrator)

#### Download & Extract
  - Download the [sources](https://github.com/phanimaridu/open-ams/archive/dev.zip)
  - Unzip the archive

#### Start Rest Web Services
  - Go to 'rest-api' Directory
  - Run **./gradlew bootRun -Duser.timezone=GMT -Dspring.profiles.active=demo -Dspring.datasource.password=simsim**
  - For more info , Refer [rest-api module readme](rest-api/readme.md)
