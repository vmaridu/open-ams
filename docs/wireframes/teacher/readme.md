
# Login

 - To access any OpenAMS services , a JWT (JSON Web Token) should be passed as Authorization Header.

 - JWT can be be obtained by calling /auth endpoint using Basic Authentication. The username and password are combined with a single colon. (:)
    then Base64 encoded and  then passed in Authorization headed as shown in curl log.

 - /auth will respond with JWT token as Response Header as shown in curl log.

   ~~~ sh
   curl -vk -X GET https://ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443/auth  -H 'Authorization: Basic <Base64Encoded(userName:password)>'

   > GET /auth HTTP/1.1
   > Host: ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443
   > User-Agent: curl/7.47.0
   > Accept: */*
   > Authorization: Basic <Base64Encoded(userName:password)>

   < HTTP/1.1 200
   < X-XSS-Protection: 1; mode=block
   < Strict-Transport-Security: max-age=31536000 ; includeSubDomains
   < JWT : <JWT>
   < Content-Length:
   ~~~




---
# Consuming API
  - Once the JWT is obtained , A 'Bearer ' prefixed JWT should be passed as Authorization header for rest of API Calls.
  - filter Query Parameter value should be URL encoded. (Ex : (['user.userName'=='bioins']) -> filter=(%7B'user.userName'%3D%3D'bioins'%7D)')




---
# Staff Dashboard

![Staff Dashboard](./1_dashboard.png "Staff Dashboard")


### Get Profile Information  

  - Get Staff Profile Information by **GET /api/staff?filter=(['user.userName'=='$userName'])**

  ~~~ sh
  curl -vk -X GET --header 'Accept: application/json' --header 'Authorization: Bearer <JWT>' 'https://ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443/api/staff?filter=(%7B'user.userName'%3D%3D'bioins'%7D)'

  > GET /api/staff?filter=(%7B'user.userName'%3D%3D'bioins'%7D) HTTP/1.1
  > Host: ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443
  > User-Agent: curl/7.47.0
  > Accept: */*
  > authorization: Bearer <JWT>

  < HTTP/1.1 200
  < Strict-Transport-Security: max-age=31536000 ; includeSubDomains
  < Content-Type: application/json;charset=UTF-8
  < Transfer-Encoding: chunked
  < Date: Mon, 14 Aug 2017 02:36:26 GMT

      {
        "page": 0,
        "limit": 10,
        "total": 1,
        "size": 1,
        "totalPages": 1,
        "prvious": null,
        "next": null,
        "content": [
          {
            "id": "4a0ba7b9-d6f3-463d-86bd-5ce2b9de31a2",
            "prefix": "Mr",
            "suffix": null,
            "gender": "FEMALE",
            "eyeColor": null,
            "hairColor": null,
            "race": null,
            "height": null,
            "weight": null,
            "pictureUri": null,
            "dob": null,
            "joiningDtt": null,
            "modifiedDtt": null,
            "ssn": null,
            "user": {
              "userName": "bioins",
              "email": "bioins@openams.com",
              "status": "ACTIVE",
              "accountExpireDtt": null,
              "credentialsExpireDtt": null,
              "lastAccessDtt": "2015-04-10-16-27-25",
              "roles": null
            },
            "contact": {
              "id": "4dbb060c-847b-4bf1-a427-a14258cf8cea",
              "addressLine1": "Test Avenue",
              "addressLine2": "Test Line 2",
              "apartment": "1",
              "city": "TestCity",
              "country": "Test Country",
              "homePhone": "000-000-0000",
              "name": "Mac Appleseed",
              "notes": "test notes",
              "phone": "000-000-0000",
              "state": "TestState",
              "street": "Test Street",
              "zip": 0,
              "email": "test_email@test.com"
            },
            "emrContact": {
              "id": "4dbb060c-847b-4bf1-a427-a14258cf8cea",
              "addressLine1": "Test Avenue",
              "addressLine2": "Test Line 2",
              "apartment": "1",
              "city": "TestCity",
              "country": "Te* Connection #0 to host ec2-52-53-48-48.us-west-1.compute.amazonaws.com left intact st Country",
              "homePhone": "000-000-0000",
              "name": "Mac Appleseed",
              "notes": "test notes",
              "phone": "000-000-0000",
              "state": "TestState",
              "street": "Test Street",
              "zip": 0,
              "email": "test_email@test.com"
            },
            "altId": "BIO_STF_1",
            "desc": null,
            "designation": "Teacher",
            "fname": "Mac",
            "lname": "Appleseed",
            "mname": null
          }
        ]
      }
  ~~~


### Get Course Schedules Information by Staff ID

  - Get Course Schedules Information by **GET /api/course-schedules?filter=(['staff.id'=='$staffId'])**

    ~~~ sh
    curl -vk -X GET --header 'Accept: application/json' --header 'Authorization: Bearer <JWT>' 'https://ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443/api/staff?filter=(%7B'user.userName'%3D%3D'bioins'%7D)'

    > GET /api/staff?filter=(%7B'user.userName'%3D%3D'bioins'%7D) HTTP/1.1
    > Host: ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443
    > User-Agent: curl/7.47.0
    > Accept: */*
    > authorization: Bearer <JWT>

    < HTTP/1.1 200
    < Strict-Transport-Security: max-age=31536000 ; includeSubDomains
    < Content-Type: application/json;charset=UTF-8
    < Transfer-Encoding: chunked
    < Date: Mon, 14 Aug 2017 02:36:26 GMT

    {
        "page": 0,
        "limit": 10,
        "total": 2,
        "size": 2,
        "totalPages": 1,
        "prvious": null,
        "next": null,
        "content": [
            {
                "id": "47d41bdf-e433-4ee8-8d0b-0c0b4f529c69",
                "name": "K10 BIO EVEN",
                "desc": "K10 Biology Evening Class",
                "status": "ACTIVE",
                "location": "ROOM 102",
                "startDt": "2017-03-20",
                "endDt": "2018-02-12",
                "startT": "16:00:00",
                "endT": "18:00:00",
                "term": "2017",
                "instructorId": "4a0ba7b9-d6f3-463d-86bd-5ce2b9de31a2",
                "courseId": "91a17ac6-29ae-49aa-86c3-680bf77e2130",
                "modifiedDtt": "2017-03-20-10-00-00"
            },
            {
                "id": "7f249c50-9ec1-44df-84d2-c117c196516b",
                "name": "K10 BIO MORN",
                "desc": "K10 Biology Morning Class",
                "status": "ACTIVE",
                "location": "ROOM 102",
                "startDt": "2017-03-20",
                "endDt": "2018-02-12",
                "startT": "10:00:00",
                "endT": "12:00:00",
                "term": "2017",
                "instructorId": "4a0ba7b9-d6f3-463d-86bd-5ce2b9de31a2",
                "courseId": "91a17ac6-29ae-49aa-86c3-680bf77e2130",
                "modifiedDtt": "2017-03-20-09-00-00"
            }
        ]
    }
   ~~~




---
# Take Attendance

![Take Attendance](./2_take_attendance.png "Take Attendance")


### Get Course Schedule's Student Enrollments

  - Get Course Schedule's Student Enrollments Report by **GET /api/course-schedules/$courseScheduleId/enrollment_report**

   ~~~ sh
   curl -vk -X GET \
 https://ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443/api/course-schedules/7f249c50-9ec1-44df-84d2-c117c196516b/enrollment_report \
 -H 'authorization: Bearer <JWT>'

   > GET /api/course-schedules/7f249c50-9ec1-44df-84d2-c117c196516b/enrollment_report HTTP/1.1
   > Host: ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443
   > User-Agent: curl/7.47.0
   > Accept: */*
   > authorization: Bearer <JWT>

   < HTTP/1.1 200
   < Strict-Transport-Security: max-age=31536000 ; includeSubDomains
   < X-Application-Context: application:demo:8443
   < Content-Type: application/json;charset=UTF-8
   < Transfer-Encoding: chunked

    [
      {
          "enrollmentId": "636f9a8e-91b2-492b-bc91-03cd81b24fc4",
          "studentId": "abba7c7a-2795-4fe6-a263-002d9e727776",
          "rollNumber": "17_K10_A_007",
          "fname": "Jamal",
          "lname": "Guzman",
          "mname": null
      },
      {
          "enrollmentId": "d23ac922-0cf6-4d8f-b431-cfaf3f2e1389",
          "studentId": "ee2271dc-4579-4cab-83df-ebbf23a8b9d7",
          "rollNumber": "17_K10_A_006",
          "fname": "Ivor",
          "lname": "Dejesus",
          "mname": null
      },
      {
          "enrollmentId": "da9630ea-b246-434f-9433-6e02d17ec146",
          "studentId": "653e0ed8-d74f-464e-8502-cb51a9aa81f0",
          "rollNumber": "17_K10_A_004",
          "fname": "Evan",
          "lname": "Patrick",
          "mname": null
      },
      {
          "enrollmentId": "f33e49be-ef65-422f-a705-ef58a4dfef6a",
          "studentId": "8b13d8d7-08b5-496e-8285-df20df73bf45",
          "rollNumber": "17_K10_A_005",
          "fname": "Gannon",
          "lname": "Pierce",
          "mname": null
      }
    ]
   ~~~



### Get Attendance Summary

  - Get Course Schedule's Attendance Report by **GET /api/course-schedules/$courseScheduleId/attendance_report?expand=false**

   ~~~ sh
   curl -vk -X GET \
 https://ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443/api/course-schedules/7f249c50-9ec1-44df-84d2-c117c196516b/attendance_report?expand=false \
 -H 'authorization: Bearer <JWT>'

   > GET /api/course-schedules/7f249c50-9ec1-44df-84d2-c117c196516b/attendance_report HTTP/1.1
   > Host: ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443
   > User-Agent: curl/7.47.0
   > Accept: */*
   > authorization: Bearer <JWT>

   < HTTP/1.1 200
   < Strict-Transport-Security: max-age=31536000 ; includeSubDomains
   < X-Application-Context: application:demo:8443
   < Content-Type: application/json;charset=UTF-8
   < Transfer-Encoding: chunked

    {
      "totalClasses": 6,
      "classAverage": 91.666664,
      "attendancesSummary": {
          "da9630ea-b246-434f-9433-6e02d17ec146": "5;1;0;0",
          "d23ac922-0cf6-4d8f-b431-cfaf3f2e1389": "6;0;0;0",
          "636f9a8e-91b2-492b-bc91-03cd81b24fc4": "5;0;1;0",
          "f33e49be-ef65-422f-a705-ef58a4dfef6a": "6;0;0;0"
      },
      "attendances": null
    }
   ~~~




---
# Review Attendance

![Review Attendance](./3_review_attendance.png "Review Attendance")




---
## Submit Attendance

  - Submit Attendance to entire Class Bulk Submit by **POST /api/attendances/bulk**

  ~~~ sh
  curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' --header 'Authorization: Bearer <JWT>' -d '{ \
     "attendances": [ \
       { \
         "enrollmentId": "da9630ea-b246-434f-9433-6e02d17ec146", \
         "comment": "Absent with out leave", \
         "status": "ABSENT" \
       }, \
       { \
         "enrollmentId": "d23ac922-0cf6-4d8f-b431-cfaf3f2e1389", \
         "comment": null, \
         "status": "PRESENT" \
       }, \
       { \
         "enrollmentId": "636f9a8e-91b2-492b-bc91-03cd81b24fc4", \
         "comment": "Sick leave", \
         "status": "ON_LEAVE" \
       }, \
       { \
         "enrollmentId": "f33e49be-ef65-422f-a705-ef58a4dfef6a", \
         "comment": null, \
         "status": "PRESENT" \
       } \
     ], \
     "comment": "2 P 1 A 1 L", \
     "courseScheduleId": "7f249c50-9ec1-44df-84d2-c117c196516b", \
     "takenByStaffId": "4a0ba7b9-d6f3-463d-86bd-5ce2b9de31a2", \
     "takenDtt": "2015-08-11-10-00-00" \
   } \
   ' 'https://ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443/api/attendances/bulk'
  ~~~




---
# View Attendance Reports

![View Reports](./4_view_reports.png "View Reports")


  - Get Attendance Report by **GET /api/course-schedules/$courseScheduleId/attendance_report?fromDtt=$fromDateTime&toDtt=$toDateTime&expand=true**

   ~~~ sh
    curl -vk -X GET --header 'Accept: application/json' --header 'Authorization: Bearer <JWT>' 'https://ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443/api/course-schedules/$courseScheduleId/attendance_report?fromDtt=$fromDtt&toDtt=toDtt&expand=true'

    > GET /api/course-schedules/7f249c50-9ec1-44df-84d2-c117c196516b/attendance_report?fromDtt=2015-07-17-10-00-00&toDtt=2015-07-19-10-00-00&expand=true HTTP/1.1
    > Host: ec2-52-53-48-48.us-west-1.compute.amazonaws.com:8443
    > User-Agent: curl/7.43.0
    > Accept: application/json
    > Authorization: Bearer <JWT>

    < HTTP/1.1 200
    < Strict-Transport-Security: max-age=31536000 ; includeSubDomains
    < X-Application-Context: application:demo:8443
    < Content-Type: application/json;charset=UTF-8

    {
       "totalClasses": 6,
       "classAverage": 91.666664,
       "attendancesSummary": {
           "da9630ea-b246-434f-9433-6e02d17ec146": "5;1;0;0",
           "d23ac922-0cf6-4d8f-b431-cfaf3f2e1389": "6;0;0;0",
           "636f9a8e-91b2-492b-bc91-03cd81b24fc4": "5;0;1;0",
           "f33e49be-ef65-422f-a705-ef58a4dfef6a": "6;0;0;0"
       },
       "attendances": [
           {
               "takenDtt": "2015-07-17-10-00-00",
               "comment": null,
               "courseScheduleId": null,
               "takenByStaffId": "6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c",
               "attendances": [
                   {
                       "enrollmentId": "636f9a8e-91b2-492b-bc91-03cd81b24fc4",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-17-10-00-00"
                   },
                   {
                       "enrollmentId": "d23ac922-0cf6-4d8f-b431-cfaf3f2e1389",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-17-10-00-00"
                   },
                   {
                       "enrollmentId": "da9630ea-b246-434f-9433-6e02d17ec146",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-17-10-00-00"
                   },
                   {
                       "enrollmentId": "f33e49be-ef65-422f-a705-ef58a4dfef6a",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-17-10-00-00"
                   }
               ]
           },
           {
               "takenDtt": "2015-07-18-10-00-00",
               "comment": null,
               "courseScheduleId": null,
               "takenByStaffId": "6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c",
               "attendances": [
                   {
                       "enrollmentId": "636f9a8e-91b2-492b-bc91-03cd81b24fc4",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-18-10-00-00"
                   },
                   {
                       "enrollmentId": "d23ac922-0cf6-4d8f-b431-cfaf3f2e1389",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-18-10-00-00"
                   },
                   {
                       "enrollmentId": "da9630ea-b246-434f-9433-6e02d17ec146",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-18-10-00-00"
                   },
                   {
                       "enrollmentId": "f33e49be-ef65-422f-a705-ef58a4dfef6a",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-18-10-00-00"
                   }
               ]
           },
           {
               "takenDtt": "2015-07-19-10-00-00",
               "comment": null,
               "courseScheduleId": null,
               "takenByStaffId": "6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c",
               "attendances": [
                   {
                       "enrollmentId": "636f9a8e-91b2-492b-bc91-03cd81b24fc4",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-19-10-00-00"
                   },
                   {
                       "enrollmentId": "d23ac922-0cf6-4d8f-b431-cfaf3f2e1389",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-19-10-00-00"
                   },
                   {
                       "enrollmentId": "da9630ea-b246-434f-9433-6e02d17ec146",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-19-10-00-00"
                   },
                   {
                       "enrollmentId": "f33e49be-ef65-422f-a705-ef58a4dfef6a",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-19-10-00-00"
                   }
               ]
           },
           {
               "takenDtt": "2015-07-20-10-00-00",
               "comment": null,
               "courseScheduleId": null,
               "takenByStaffId": "6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c",
               "attendances": [
                   {
                       "enrollmentId": "636f9a8e-91b2-492b-bc91-03cd81b24fc4",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-20-10-00-00"
                   },
                   {
                       "enrollmentId": "d23ac922-0cf6-4d8f-b431-cfaf3f2e1389",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-20-10-00-00"
                   },
                   {
                       "enrollmentId": "da9630ea-b246-434f-9433-6e02d17ec146",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-20-10-00-00"
                   },
                   {
                       "enrollmentId": "f33e49be-ef65-422f-a705-ef58a4dfef6a",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-20-10-00-00"
                   }
               ]
           },
           {
               "takenDtt": "2015-07-21-10-00-00",
               "comment": null,
               "courseScheduleId": null,
               "takenByStaffId": "6c44a361-b9b3-4037-8b6d-a5c4f4c13b4c",
               "attendances": [
                   {
                       "enrollmentId": "636f9a8e-91b2-492b-bc91-03cd81b24fc4",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-21-10-00-00"
                   },
                   {
                       "enrollmentId": "d23ac922-0cf6-4d8f-b431-cfaf3f2e1389",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-21-10-00-00"
                   },
                   {
                       "enrollmentId": "da9630ea-b246-434f-9433-6e02d17ec146",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-21-10-00-00"
                   },
                   {
                       "enrollmentId": "f33e49be-ef65-422f-a705-ef58a4dfef6a",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-07-21-10-00-00"
                   }
               ]
           },
           {
               "takenDtt": "2015-08-11-10-00-00",
               "comment": "2 P 1 A 1 L",
               "courseScheduleId": null,
               "takenByStaffId": "4a0ba7b9-d6f3-463d-86bd-5ce2b9de31a2",
               "attendances": [
                   {
                       "enrollmentId": "da9630ea-b246-434f-9433-6e02d17ec146",
                       "status": "ABSENT",
                       "comment": "Absent with out leave",
                       "takenDtt": "2015-08-11-10-00-00"
                   },
                   {
                       "enrollmentId": "d23ac922-0cf6-4d8f-b431-cfaf3f2e1389",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-08-11-10-00-00"
                   },
                   {
                       "enrollmentId": "636f9a8e-91b2-492b-bc91-03cd81b24fc4",
                       "status": "ON_LEAVE",
                       "comment": "Sick leave",
                       "takenDtt": "2015-08-11-10-00-00"
                   },
                   {
                       "enrollmentId": "f33e49be-ef65-422f-a705-ef58a4dfef6a",
                       "status": "PRESENT",
                       "comment": null,
                       "takenDtt": "2015-08-11-10-00-00"
                   }
               ]
           }
       ]
    }
   ~~~
