import { apiConfig } from './api-config';
import { getJWT, removeJWT, callAuth, handleGet, handlePost, handlePut, handleDelete } from './utils';
import { setProfile, getProfile, isValidUser, logout } from './auth';

let staffProfileTemplate = require("../templates/staff-profile.hbs");
let courseScheduleOptionsTemplate = require("../templates/course-schedule-options.hbs");
let courseScheduleAttendanceTemplate = require("../templates/course-schedule-attendance.hbs");
let attendanceAggregateReportTemplate = require("../templates/attendance-aggregate-report.hbs");
let attendanceDetailedReportTemplate = require("../templates/attendance-detailed-report.hbs");


//loads conent by id
function navigateById(navId) {
  if (navId == "attendance") {
    $("#content").load("views/staff/attendance.html");
    loadAttendanceCourseSchedules();
  } else if (navId == "reports") {
    $("#content").load("views/staff/reports.html");
    loadReportCourseSchedules();
  } else if (navId == "profile") {
    $("#content").load("views/staff/profile.html");
    loadProfile();
  } else if (navId == "logout") {
    logout();
  } else {
    $("#content").html("");
  }
}


// Updates dynamic content based on the fragment identifier.
function navigate() {
  var navId = location.hash.substr(1);
  navigateById(navId);
}


function handleCourseSchedulesByInstructorId(instructorId, handler) {
  var courseSchedulesByUserNameUrl = apiConfig.currunt.api.baseUrl
    + "/api/course-schedules?filter="
    + encodeURIComponent("( {'staff.id' == '" + instructorId + "'} )");
  handleGet(courseSchedulesByUserNameUrl, handler);
}


function handleStudnetEnrollmentsByCourseScheduleId(courseScheduleId, handler) {
  var studnetEnrollmentsByCourseScheduleIdUrl = apiConfig.currunt.api.baseUrl
    + "/api/course-schedules/" + courseScheduleId + "/enrollment_report";
  handleGet(studnetEnrollmentsByCourseScheduleIdUrl, handler);
}

function handleReportByCourseScheduleId(courseScheduleId, fromDt, toDt, handler) {
  // fromDtt=2015-07-17-10-00-00 & toDtt=2015-07-19-10-00-00
  //TODO: Inlude Date Filter
  var reportByCourseScheduleIdUrl = apiConfig.currunt.api.baseUrl
    + "/api/course-schedules/" + courseScheduleId + "/attendance_report?expand=true";
  handleGet(reportByCourseScheduleIdUrl, handler);
}

function handleSubmitAttendance(body, handler) {
  var submitAttendanceUrl = apiConfig.currunt.api.baseUrl + "/api/attendances/bulk";
  handlePost(submitAttendanceUrl, body, handler);
}


function populateTodayInAttendanceDt() {
  var now = new Date();
  var day = ("0" + now.getDate()).slice(-2);
  var month = ("0" + (now.getMonth() + 1)).slice(-2);
  var today = now.getFullYear() + "-" + (month) + "-" + (day);
  $('#attendance-dt').val(today);
}



function loadStudentsByCourseScheduleId(courseScheduleId) {
  handleStudnetEnrollmentsByCourseScheduleId(courseScheduleId, function (responseCode, responseBody) {
    if (responseCode == 200) {
      var studentEnrollmentsHtml = courseScheduleAttendanceTemplate({ "studentEnrollments": responseBody });
      $('#student-enrollments-div').html(studentEnrollmentsHtml);
      //bind click events
      $("#reset-attendance-btn").click(handleAttendaceResetClick);
      $("#submit-attendance-btn").click(handleAttendaceSubmitClick);
    } else {
      //TODO:Handle this case
    }
  });
}

function getDetailedAttendanceForHandleBarContext(status) {
  var detailedAttendance = {};
  if (status == "PRESENT") {
    detailedAttendance.status = "P";
    detailedAttendance.btClass = "bg-success";
  } else if (status == "ABSENT") {
    detailedAttendance.status = "A";
    detailedAttendance.btClass = "bg-danger";
  } else if (status == "ON_LEAVE") {
    detailedAttendance.status = "L";
    detailedAttendance.btClass = "bg-warning";
  } else if (status == "OTHER") {
    detailedAttendance.status = "O";
    detailedAttendance.btClass = "bg-info";
  } else {
    detailedAttendance.status = "";
    detailedAttendance.btClass = "";
  }
  return detailedAttendance;
}


function loadReportsByCourseScheduleId(courseScheduleId, fromDt, toDt) {
  handleReportByCourseScheduleId(courseScheduleId, fromDt, toDt, function (responseCode, responseBody) {
    if (responseCode == 200) {
      if (responseBody.totalClasses > 0) {

        var reportContext = {
          "stundets": [],
          "takenDateToAttendances": []
        };

        for (var key in responseBody.attendancesSummary) {
          var studentEnrollmentId = key;
          var studentEnrollmentInfo = responseBody.attendancesSummary[key].split(";");
          var studnet = {
            "id": studentEnrollmentInfo[4],
            "name": studentEnrollmentInfo[6] + " " + studentEnrollmentInfo[7],
            "rollNumber": studentEnrollmentInfo[5],
            "studentEnrollmentId": studentEnrollmentId
          };
          reportContext.stundets.push(studnet);
          reportContext.stundets = _.sortBy(reportContext.stundets, ["studentEnrollmentId"]);
        }

        responseBody.attendances.forEach(function (sourceAttendance) {
          var takenDateToAttendance = {
            "takenDate": sourceAttendance.takenDtt.substr(0, 10),
            "attendances": []
          }
          sourceAttendance.attendances.forEach(function (sourceDetaiedAttendance) {
            takenDateToAttendance.attendances.push(getDetailedAttendanceForHandleBarContext(sourceDetaiedAttendance.status));
          });
          reportContext.takenDateToAttendances.push(takenDateToAttendance);
        });

        var reportsHtml = attendanceDetailedReportTemplate(reportContext);
        $('#reports-div').html(reportsHtml);

        //load reports to progress bar
        var classAvgHtml = attendanceAggregateReportTemplate(responseBody);
        $('#class-average-div').html(classAvgHtml);
      } else {
        $('#reports-div').html("NO RECORDS FOUND");
        $('#class-average-div').html("");
      }
    } else {
      $('#reports-div').html("NO RECORDS FOUND");
      $('#class-average-div').html("");
    }
  });
}



function loadAttendanceCourseSchedules() {
  handleCourseSchedulesByInstructorId(getProfile().id, function (responseCode, responseBody) {
    if (responseBody.size > 0) {
      var courseSchedulesHtml = courseScheduleOptionsTemplate(responseBody);
      $('#course-schedule-slct').html(courseSchedulesHtml);

      // bind listener to secect coirse drop down
      $('#course-schedule-slct').change(function () {
        $(this).find(":selected").each(function () {
          loadStudentsByCourseScheduleId($(this).attr('id'));
        });
      });

      //populate currunt date in attendance date picker
      populateTodayInAttendanceDt();
    } else {
      //TODO:Handle this case
    }
  });
}


function loadReportCourseSchedules() {
  handleCourseSchedulesByInstructorId(getProfile().id, function (responseCode, responseBody) {
    if (responseBody.size > 0) {
      var courseSchedulesHtml = courseScheduleOptionsTemplate(responseBody);
      $('#course-schedule-slct').html(courseSchedulesHtml);

      // bind listener to filter button
      $('#report-filter-btn').click(function () {
        var courseScheduleId = $('#course-schedule-slct').find(":selected").attr('id');
        var fromDt = $('#from-dt').val();
        var toDt = $('#to-dt').val();
        loadReportsByCourseScheduleId(courseScheduleId, fromDt, toDt);
      });
    } else {
      //TODO:Handle this case
    }
  });
}


function loadProfile() {
  var profile = getProfile();

  handleCourseSchedulesByInstructorId(profile.id, function (responseCode, responseBody) {
    if (responseBody.size > 0) {
      var profileContext = {
        "name": profile.name,
        "email": profile.email,
        "roles": profile.roles,
        "courseSchedules": responseBody.content
      };
      $('#profile-body-div').html(staffProfileTemplate(profileContext));
    } else {
      //TODO:Handle this case
    }
  });
}


function handleAttendaceResetClick() {
  $("#student-enrollments-tbody input[type=radio]").prop("checked", false);
  $("#student-enrollments-tbody input[type=input]").val("");
}


function handleAttendaceSubmitClick() {
  var attendancesObject = { "attendances": [] }
  var attendanceRows = $("#student-enrollments-tbody tr");
  attendanceRows.each(function (index, attendanceRow) {

    //reset previous errors
    $(attendanceRow).removeClass("table-danger");

    var enrollmentId = $(this).attr("id");
    var statusInfo = $("#" + enrollmentId + " input[type=radio]:checked").attr("id");
    if (statusInfo != null) {
      var attendanceObject = {};
      var studentInfoTokens = statusInfo.split(";");
      attendanceObject.enrollmentId = studentInfoTokens[0];
      attendanceObject.status = studentInfoTokens[1];
      attendanceObject.comment = $("#" + enrollmentId + " input[type=input]").val();
      attendancesObject.attendances.push(attendanceObject);
    } else {
      //highlight error rows
      $(attendanceRow).addClass("table-danger");
    }
  });

  if (attendanceRows.length == attendancesObject.attendances.length) {
    attendancesObject.takenByStaffId = getProfile().id;
    attendancesObject.courseScheduleId = $('#course-schedule-slct').find(":selected").attr('id');
    attendancesObject.takenDtt = $('#attendance-dt').val() + "-00-00-00";
    handleSubmitAttendance(JSON.stringify(attendancesObject), function (responseCode, responseBody) {
      if (responseCode == 204) {
        $("#alrt").css("color", "green").text("Attendance submitted successfully");
      } else {
        $("#alrt").css("color", "red").text("Error while submiting attendance");
      }
    });
  } else {
    $("#alrt").css("color", "red").text("Correct highlighted rows");
  }
}


function initPageLoadEvents() {

  //logout user if JWT is invalid
  if (!isValidUser()) { logout(); }

  // Navigate whenever the fragment identifier value changes.
  $(window).bind('hashchange', navigate);

  //loads content pages at page load
  var navId = location.hash.substr(1);
  navigateById(navId);
}


export { initPageLoadEvents }
