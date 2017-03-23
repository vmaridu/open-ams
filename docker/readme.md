# Docker Orchestrator



## Running REST API from Docker

 - Install Docker and Make sure Docker client is running


 - Get docker image to your local index ; you can do either (a) or (b) to achive it

      a) Pull image from my public docker hub repository by running
      ~~~ sh
          docker pull phanimaridu/openams-rest-api
      ~~~

      b) Build image by your self by running
      ~~~ sh
         sh build_rest_api.sh
      ~~~


 - Verify images by using
    ~~~ sh
      docker images

      # you should see an entry like this
      # REPOSITORY                     TAG                 IMAGE ID            CREATED             SIZE
      # phanimaridu/openams-rest-api   0.0.1-SNAPSHOT      545cd225773c        9 minutes ago       238.4 MB
    ~~~


 - Run Docker Image with arguemnts
    ~~~ sh

    # -p 8443:8443 ; -p HOST:GUEST; Maps Guest's PORT to Host's PORT and Exposes Host's port number
    # -v "/home/docker/logs:/logs" ; -v HOST_DIR:GUEST_DIR ; Makes HOST_DIR as Shared directory in Guest
    # -e are environment variables for Spring Boot
    # user.timezone=GMT; Default GMT Time zone
    # spring.profiles.active=demo ; Spring Active Profile
    # spring.datasource.password=simim; H2 Database password

    docker run -d -p 8443:8443 -v "/home/docker/logs:/logs" -e "user.timezone=GMT" -e "spring.profiles.active=demo" -e "spring.datasource.password=simsim" -e "logging.file=/logs/app.log" -t phanimaridu/openams-rest-api:0.0.1-SNAPSHOT
    ~~~


 - Browse API Documentation @ https://DOCKER_HOST:8443/swagger-ui.html ; DEMO Credentials ** admin/password ** for querying API

 - Browse In Memory H2 Database @ https://DOCKER_HOST:8443/h2-console ; DEMO Credentials ** admin/simsim **




## Running WEB UI from Docker





## What build.sh does ?

 - Builds rest-api

 - Makes Java Docker Image out of rest-api

 - Builds phonegap , web-ui and preapres final ui build with native mobile apps

 - Makes nginx Docker image out of UI Build

> If you wish to use already built docker images , use my Docker Hub images
  -  rest-api (phanimaridu/openams-rest-api:0.0.1)
  - web-ui (phanimaridu/openams-web-ui:0.0.1)



## Server Setup

  - Run rest-api docker image by
  ~~~ sh
  docker run -p 8443:8443 -v <HOST_LOG_DIR>:<DOCKER_LOG_DIR> -t  phanimaridu/openams-rest-api:0.0.1 <SPRING_BOOT_INIT_WITH_VM_ARGS>
  ~~~

  - Run web-ui docker image by
  ~~~ sh
  # API URL is https://HOST:8443/
  docker run -p 443:443 -v <HOST_LOG_DIR>:<DOCKER_LOG_DIR> -e API_URL=<REST_API_URL> -t  phanimaridu/openams-web-ui:0.0.1
  ~~~

  - Browse Open AMS UI from https://HOST/
