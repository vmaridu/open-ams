import { apiConfig } from './api-config';
import { getJWT, removeJWT, callAuth, handleGet, handlePost, handlePut, handleDelete } from './utils';
import { setProfile, getProfile, getUserData, isValidUser, logout } from './auth';


function displayErrorMessage() {
    $("#error-alrt").text("Invalid Username & Password !!!");
}


function authHandler(responseCode, responseBody) {
    //call request API /auth
    //if credentials valid then store JWT, redirect to role-specfic dashboard
    //else populate error message
    if (responseCode == 200) {
        var jwt = responseBody;

        $("#error-alrt").text("");
        //store token to local storage
        localStorage.setItem("jwt", jwt);
        setProfile();

        //parse user data
        var userData = getUserData(jwt);
        var role = userData.roles[0];

        //rediect screen to role specific page

        if (role == "ADMIN") {
            window.location.replace("./admin.html");
        } else if (role == "STAFF") {
            window.location.replace("./staff.html");
        } else if (role == "STUDENT") {
            $("#error-alrt").text("./STUDENT PAGE NOT AVAILABLE");
        } else {
            $("#error-alrt").text("INAVLID ROLE");
        }

    } else {
        displayErrorMessage();
    }
}


function initPageLoadEvents() {
    $("#login-btn").click(function () {
        var userName = $("#user-name").val();
        var password = $("#password").val();
        callAuth(userName, password, authHandler);
    });
}



export { initPageLoadEvents }

