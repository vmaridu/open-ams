# OpenAMS Rest Web Service Module


### How to run ?

 - Install JDK 8 (Gradle Installation NOT REQUIRED ; WRAPPER INCLUDED)

 - Build sources using **./gradlew clean build**

 - Run application from JAR using
  ~~~ sh
  java -jar -Duser.timezone=GMT -Dspring.profiles.active=demo -Dspring.datasource.password=simsim build/libs/openams-rest-api-0.0.1-SNAPSHOT.jar
  ~~~

 - (OR) Run application using Gradle
   ~~~ sh
   ./gradlew bootRun -Duser.timezone=GMT -Dspring.profiles.active=demo -Dspring.datasource.password=simsim
   ~~~

 - Browse API Documentation @ https://localhost:8443/swagger-ui.html ; DEMO Credentials ** admin/password ** for querying API

 - Browse In Memory H2 Database @ https://localhost:8443/h2-console ; DEMO Credentials ** admin/simsim **



### Notes

 - Spring Profile **demo** , Starts a In Memory H2 database, Exports test data and Exposes API with Self Signed SSL Certificates
 - Use dev-mysql profile (OR) Spring boot Custom Configs for active development (configurations are in **/src/main/resources /config**)
 - Set GMT as server's default timezone by VM_ARG **-Duser.timezone=GMT**
 - Find SQL scripts in **/src/main/resources /sql**
 - Initial project generated by [Spring Initializer](https://start.spring.io)