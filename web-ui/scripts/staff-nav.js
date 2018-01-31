(function($) {

  // Navigate whenever the fragment identifier value changes.
  $(window).bind('hashchange', navigate);


  // Updates dynamic content based on the fragment identifier.
  function navigate(){
     var navId = location.hash.substr(1);
     if(navId == "take-attendance"){
          // validate slecected course
          $("#content").load("views/staff/attendance.html");
     }else if(navId == "view-reports"){
          // validate slecected course
         $("#content").load("views/staff/reports.html");
     }else{
          $("#content").html("");
     }
  }

})(jQuery);
