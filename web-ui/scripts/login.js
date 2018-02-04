(function($) {


  function getUserData(jwt){
     var base64UserData = jwt.split(".")[1];
     return JSON.parse(atob(base64UserData));
  }


  function displayErrorMessage(){
    $("#error-alrt").text("Invalid Username & Password !!!");
  }


  function authHandler(responseCode, responseBody){
      if(responseCode == 200 ){
          var jwt = responseBody;

          $("#error-alrt").text("");
          //store token to local storage
          localStorage.setItem("jwt", jwt);

          //parse user data
          var userData = getUserData(jwt);
          var role = userData.roles[0];

          //rediect screen to role specific page

          if(role == "ADMIN"){
               window.location.replace("./admin.html");
          }else if(role == "STAFF"){
               window.location.replace("./staff.html");
          }else if(role == "STUDENT"){
                $("#error-alrt").text("./STUDENT PAGE NOT AVAILABLE");
          }else{
                $("#error-alrt").text("INAVLID ROLE");
          }

      }else{
           displayErrorMessage();
      }
  }

  //call request API /auth
  //if credentials valid then store JWT, redirect to role-specfic dashboard
  //else populate error message
  $("#login-btn").click(function(){
      var userName = $("#user-name").val();
      var password = $("#password").val();
      callAuth(userName, password, authHandler);
  });

})(jQuery);
