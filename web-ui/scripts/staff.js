(function($) {

  //logout user if JWT is invalid
  (function(){
    if(!isValidUser()){
      logout();
    }
  })();


  //loads content pages at page load
  (function(){
      var navId = location.hash.substr(1);
      navigateById(navId);
  })();



  //loads conent by id
  function navigateById(navId){
    if(navId == "attendance"){
        $("#content").load("views/staff/attendance.html");
        loadAttendanceCourseSchedules();
    }else if(navId == "reports"){
        $("#content").load("views/staff/reports.html");
        loadReportCourseSchedules();
    }else if(navId == "profile"){
        $("#content").load("views/staff/profile.html");
        loadProfile();
    }else if(navId == "logout"){
        logout();
    }else{
        $("#content").html("");
    }
  }


  // Updates dynamic content based on the fragment identifier.
  function navigate(){
    var navId = location.hash.substr(1);
    navigateById(navId);
  }



  function handleStaffById(userName, handler){
    var staffByIdUrl = apiConfig.currunt.api.baseUrl + "/api/staff?filter=" + encodeURIComponent("( {'user.userName' == '" +  userName + "'} )");
    handleGet(staffByIdUrl, handler);
  }


  function handleCourseSchedulesByInstructorId(instructorId, handler){
    var courseSchedulesByUserNameUrl = apiConfig.currunt.api.baseUrl
                                            + "/api/course-schedules?filter="
                                            + encodeURIComponent("( {'staff.id' == '" +  instructorId + "'} )");
     handleGet(courseSchedulesByUserNameUrl, handler);
  }


  function handleStudnetEnrollmentsByCourseScheduleId(courseScheduleId, handler){
    var studnetEnrollmentsByCourseScheduleId = apiConfig.currunt.api.baseUrl
                                            + "/api/course-schedules/" + courseScheduleId + "/enrollment_report";
     handleGet(studnetEnrollmentsByCourseScheduleId, handler);
  }

  function handleReportByCourseScheduleId(courseScheduleId, fromDt, toDt, handler){
    // fromDtt=2015-07-17-10-00-00 & toDtt=2015-07-19-10-00-00
    //TODO: Inlude Date Filter
    var studnetEnrollmentsByCourseScheduleId = apiConfig.currunt.api.baseUrl
                                            + "/api/course-schedules/" + courseScheduleId + "/attendance_report?expand=true";
     handleGet(studnetEnrollmentsByCourseScheduleId, handler);
  }


  function populateTodayInAttendanceDt(){
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear() + "-" + (month) + "-" + (day);
    $('#attendance-dt').val(today);
  }



   function loadStudentsByCourseScheduleId(courseScheduleId){
        handleStudnetEnrollmentsByCourseScheduleId(courseScheduleId, function(responseCode, responseBody){
          if(responseCode == 200){
            var rawTemplate = document.getElementById("student-enrollments-ht").innerHTML;
            var compiledTemplate = Handlebars.compile(rawTemplate);
            var studentEnrollmentsHtml = compiledTemplate({"studentEnrollments" : responseBody});
            $('#student-enrollments-div').html(studentEnrollmentsHtml);
          }else{
             //TODO:Handle this case
          }
        });
   }

  function getDetailedAttendanceForHandleBarContext(status){
    var detailedAttendance = {};
    if(status == "PRESENT"){
       detailedAttendance.status = "P";
       detailedAttendance.btClass = "bg-success";
    }else if(status == "ABSENT"){
      detailedAttendance.status = "A";
      detailedAttendance.btClass = "bg-danger";
    }else if(status == "ON_LEAVE"){
      detailedAttendance.status = "L";
      detailedAttendance.btClass = "bg-warning";
    }else if(status == "OTHER"){
      detailedAttendance.status = "O";
      detailedAttendance.btClass = "bg-info";
    }else{
      detailedAttendance.status = "";
      detailedAttendance.btClass = "";
    }
    return detailedAttendance;
  }


   function loadReportsByCourseScheduleId(courseScheduleId, fromDt, toDt){
      handleReportByCourseScheduleId(courseScheduleId, fromDt, toDt, function(responseCode, responseBody){
        if(responseCode == 200){
           if(responseBody.totalClasses > 0){

                var reportContext = {
                  "stundets" : [],
                  "takenDateToAttendances" : []
                };

               for(var key in responseBody.attendancesSummary){
                  var studentEnrollmentId = key;
                  var studentEnrollmentInfo = responseBody.attendancesSummary[key].split(";");
                  var studnet = {
                    "id" : studentEnrollmentInfo[4],
                    "name" : studentEnrollmentInfo[6] + " " + studentEnrollmentInfo[7],
                    "rollNumber" : studentEnrollmentInfo[5],
                    "studentEnrollmentId" : studentEnrollmentId
                  };
                  reportContext.stundets.push(studnet);
                  reportContext.stundets = _.sortBy(reportContext.stundets, ["studentEnrollmentId"]);
               }

               responseBody.attendances.forEach(function(sourceAttendance){
                    var takenDateToAttendance = {
                      "takenDate" : sourceAttendance.takenDtt.substr(0,10),
                      "attendances" : []
                    }
                    sourceAttendance.attendances.forEach(function(sourceDetaiedAttendance){
                      takenDateToAttendance.attendances.push(getDetailedAttendanceForHandleBarContext(sourceDetaiedAttendance.status));
                    });
                    reportContext.takenDateToAttendances.push(takenDateToAttendance);
               });

                var reportsRawTemplate = document.getElementById("reports-ht").innerHTML;
                var reportsCompiledTemplate = Handlebars.compile(reportsRawTemplate);
                var reportsHtml = reportsCompiledTemplate(reportContext);
                $('#reports-div').html(reportsHtml);

                //load reports to progress bar
                var classAvgRawTemplate = document.getElementById("class-average-ht").innerHTML;
                var classAvgCompiledTemplate = Handlebars.compile(classAvgRawTemplate);
                var classAvgHtml = classAvgCompiledTemplate(responseBody);
                $('#class-average-div').html(classAvgHtml);
           }else{
             $('#reports-div').html("NO RECORDS FOUND");
             $('#class-average-div').html("");
           }
        }else {
          $('#reports-div').html("NO RECORDS FOUND");
          $('#class-average-div').html("");
        }
      });
   }



  function loadAttendanceCourseSchedules(){
      var userName = getCurruntUserData().sub;

      handleStaffById(userName, function(responseCode, responseBody){
         if(responseCode == 200){
             var staffId = responseBody.content[0].id;
             handleCourseSchedulesByInstructorId(staffId, function(responseCode, responseBody){
                if(responseBody.size > 0){
                    var rawTemplate = document.getElementById("course-schedule-options-ht").innerHTML;
                    var compiledTemplate = Handlebars.compile(rawTemplate);
                    var courseSchedulesHtml = compiledTemplate(responseBody);
                    $('#course-schedule-slct').html(courseSchedulesHtml);

                    // bind listener to secect coirse drop down
                    $('#course-schedule-slct').change( function() {
                       $(this).find(":selected").each(function () {
                          loadStudentsByCourseScheduleId($(this).attr('id'));
                       });
                    });

                    //populate currunt date in attendance date picker
                    populateTodayInAttendanceDt();
                }else{
                  //TODO:Handle this case
                }
             });
         }
         //TODO:Handle error case
      });
  }


  function loadReportCourseSchedules(){
      var userName = getCurruntUserData().sub;

      handleStaffById(userName, function(responseCode, responseBody){
         if(responseCode == 200){
             var staffId = responseBody.content[0].id;
             handleCourseSchedulesByInstructorId(staffId, function(responseCode, responseBody){
                if(responseBody.size > 0){
                    var rawTemplate = document.getElementById("course-schedule-options-ht").innerHTML;
                    var compiledTemplate = Handlebars.compile(rawTemplate);
                    var courseSchedulesHtml = compiledTemplate(responseBody);
                    $('#course-schedule-slct').html(courseSchedulesHtml);

                    // bind listener to filter button
                    $('#report-filter-btn').click( function() {
                        var courseScheduleId = $('#course-schedule-slct').find(":selected").attr('id');
                        var fromDt = $('#from-dt').val();
                        var toDt = $('#to-dt').val();
                        loadReportsByCourseScheduleId(courseScheduleId,fromDt,toDt);
                    });
                }else{
                  //TODO:Handle this case
                }
             });
         }
         //TODO:Handle error case
      });
  }


  function loadProfile(){
    var userName = getCurruntUserData().sub;

    handleStaffById(userName, function(responseCode, responseBody){
       if(responseCode == 200){
           var staff = responseBody.content[0];
           handleCourseSchedulesByInstructorId(staff.id, function(responseCode, responseBody){
              if(responseBody.size > 0){
                  var rawTemplate = document.getElementById("profile-ht").innerHTML;
                  var compiledTemplate = Handlebars.compile(rawTemplate);
                  var profileContext = {
                    "name" : staff.fname + " " + staff.lname ,
                    "email" : staff.user.email ,
                    "roles" : staff.user.roles ,
                    "courseSchedules" : responseBody.content
                  };
                  var profileHtml = compiledTemplate(profileContext);
                  $('#profile-body-div').html(profileHtml);
              }else{
                //TODO:Handle this case
              }
           });
       }
       //TODO:Handle error case
    });
  }


  // Navigate whenever the fragment identifier value changes.
  $(window).bind('hashchange', navigate);


})(jQuery);
