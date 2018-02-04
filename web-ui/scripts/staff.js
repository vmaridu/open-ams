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
        loadCourseSchedules();
    }else if(navId == "reports"){
        $("#content").load("views/staff/reports.html");
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
    var getCourseSchedulesByUserNameUrl = apiConfig.currunt.api.baseUrl
                                            + "/api/course-schedules?filter="
                                            + encodeURIComponent("( {'staff.id' == '" +  instructorId + "'} )");
     handleGet(getCourseSchedulesByUserNameUrl, handler);
  }


  function populateTodayInAttendanceDt(){
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear() + "-" + (month) + "-" + (day);
    $('#attendance-dt').val(today);
  }



   function loadStudentsByCourseScheduleId(courseScheduleId){
        console.log(courseScheduleId);
   }



  function loadCourseSchedules(){
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
