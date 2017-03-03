## Open AMS Orchestrator

- This will act as gathering all projects and making a single docker build for deployment

- buildOrchstratorDocker Gradle task will do these actions

  - Builds openams-rest
  - Builds openams-web
  - Builds openams-* apps
  
  - Create Docker Base with JRE 8 and NGNX
  - Copy Spring Boot JAR
  - Copy openams-web hostable web content to NGNX DIR -> web-ui
  - Add redirect rule to NGNX for Routing NGNX-SERVER/rest -> OPENAMS-REST-SERVER
  - Add Spring Boot Run and NGNX Run to Docker Startup 
  - Build Docker Image 
