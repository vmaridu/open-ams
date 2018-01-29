(function($) {

  function getUserData(jwt){
    if(jwt == null) {
      return null;
    }
     var base64UserData = jwt.split(".")[1];
     return JSON.parse(atob(base64UserData));
  }

  function logout(){
    localStorage.removeItem("jwt");
    window.location.replace("login.html");
  }


  (function(){
    var jwt = localStorage.getItem("jwt");
    var userData = getUserData(jwt);
    if(userData == null){
        logout();
    }else{
      //TODO:Validate jwt
       $("#profile-welcome").text(userData.firstName + " " + userData.lastName);
    }
  })();


  $("#logout-lnk").click(function(){
      logout();
  });


})(jQuery);
