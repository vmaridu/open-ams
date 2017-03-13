# Docker Orchestrator


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
