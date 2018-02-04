
  function getJWT(){
     return localStorage.getItem("jwt");
  }

  function removeJWT(){
     localStorage.removeItem("jwt");
  }

  function getUserData(jwt){
    if(jwt == null) {
      return null;
    }
     var base64UserData = jwt.split(".")[1];
     return JSON.parse(atob(base64UserData));
  }


  function getCurruntUserData(){
    return getUserData(getJWT());
  }


  function isValidUser(){
     var userData = getCurruntUserData();
     var sysEpoch = (new Date).getTime()/1000;
     return (userData != null) && (userData.exp > sysEpoch);
  }


  function logout(){
    removeJWT();
    window.location.replace("./login.html");
  }


  function callAuth(userName, password, authHandler){
        $.ajax({
            type: 'GET',
            url: apiConfig.currunt.api.baseUrl + '/auth' ,
            aysnc: true,
            cache: false,
            headers: {
               'Authorization': 'Basic ' + btoa(userName + ':' + password)
            },
            error: function(jqXHR, textStatus, error){
                var responseCode = jqXHR.status;
                var responseBody = jqXHR.responseJSON;
                authHandler(responseCode, responseBody);
            },
            success: function (data, textStatus, jqXHR) {
                //obtain error from server
                var responseCode = jqXHR.status;
                var jwt = jqXHR.getResponseHeader('Authorization');
                authHandler(responseCode, jwt);
            }
        });
   }
