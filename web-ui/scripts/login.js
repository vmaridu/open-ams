(function($) {


  function getUserData(jwt){
     var base64UserData = jwt.split(".")[1];
     return JSON.parse(atob(base64UserData));
  }


  function callAuth(userName, password){
      $.ajax({
          aysnc: true,
          cache: false,
          headers: {
             'Authorization': 'Basic ' + btoa(userName + ':' + password)
          },
          error: function(jqXHR, textStatus, error){
              var responseCode = jqXHR.status;
              var responseBody = jqXHR.responseJSON;
              $("#error-alrt").text("Invalid Username & Password !!!");
          },
          success: function (data, textStatus, jqXHR) {
              //obtain error from server
              var jwt = jqXHR.getResponseHeader('Authorization');
              if(jwt != null){
                 $("#error-alrt").text("");
                 //store token to local storage
                 localStorage.setItem("jwt", jwt);

                 //parse user data
                 var userData = getUserData(jwt);
                 var role = userData.roles[0];

                 //rediect screen to role specific page

                 if(role == "ADMIN"){
                      window.location.replace("admin.html");
                 }else if(role == "STAFF"){
                      window.location.replace("staff.html");
                 }else if(role == "STUDENT"){
                       $("#error-alrt").text("STUDENT PAGE NOT AVAILABLE");
                 }else{
                       $("#error-alrt").text("INAVLID ROLE");
                 }
              }else{
                   $("#error-alrt").text("Invalid Username & Password !!!");
              }
              },
              type: 'GET',
              url: apiConfig.currunt.api.baseUrl + '/auth'
          });
   }


  //call request API /auth
  //if credentials valid then store JWT, redirect to role-specfic dashboard
  //else populate error message
  $("#login-btn").click(function(){
      var userName = $("#user-name").val();
      var password = $("#password").val();
      callAuth(userName, password);
  });


})(jQuery);
