#!/bin/bash

# TODO:Error Handling

#Init variables
REST_API_JAR_PATH=../rest-api/build/libs/openams-rest-api-0.0.1-SNAPSHOT.jar
IMAGE_NAME=phanimaridu/openams-rest-api:0.0.1-SNAPSHOT

# clean and build rest api spring boot project
echo 'Initiating rest-api gradle build ...'
gradle -b ../rest-api/build.gradle clean build 
echo 'Completed rest-api gradle build.'

# copy built jar to docker directroy
cp $REST_API_JAR_PATH ./rest-api/app.jar
echo 'Copied Spring Boot App Jar to Docker directory'

# build docker image
echo 'Initiating rest-api docker build ...'
docker build -t $IMAGE_NAME rest-api
echo 'Completed rest-api docker build.'

# delete app.jar
rm ./rest-api/app.jar
echo 'Deleted Spring Boot App Jar from Docker directory'
