import { apiConfig } from './api-config';
import { getJWT, removeJWT, callAuth, handleGet, handlePost, handlePut, handleDelete } from './utils';


function clearProfile() {
  localStorage.removeItem("userName");
  localStorage.removeItem("roles");
  localStorage.removeItem("id");
  localStorage.removeItem("name");
  localStorage.removeItem("email");
}

function getUserData(jwt) {
  if (jwt == null) { return null; }
  var base64UserData = jwt.split(".")[1];
  return JSON.parse(atob(base64UserData));
}

function getCurruntUserData() {
  return getUserData(getJWT());
}


function setProfile() {
  var userName = getCurruntUserData().sub;
  var roles = getCurruntUserData().roles;
  handleStaffById(userName, function (responseCode, responseBody) {
    if (responseCode == 200) {
      localStorage.setItem("userName", userName);
      localStorage.setItem("roles", roles);
      localStorage.setItem("id", responseBody.content[0].id);
      localStorage.setItem("name", responseBody.content[0].fname + " " + responseBody.content[0].lname);
      localStorage.setItem("email", responseBody.content[0].user.email);
    }else{
      logout();
    }
  });
}

function getProfile() {
  return {
    "userName": localStorage.getItem("userName"),
    "roles": localStorage.getItem("roles"),
    "id": localStorage.getItem("id"),
    "name": localStorage.getItem("name"),
    "email": localStorage.getItem("email")
  };
}


function handleStaffById(userName, handler) {
  var staffByIdUrl = apiConfig.currunt.api.baseUrl + "/api/staff?filter=" + encodeURIComponent("( {'user.userName' == '" + userName + "'} )");
  handleGet(staffByIdUrl, handler);
}

function handleStudentById(userName, handler) {
  var studentByIdUrl = apiConfig.currunt.api.baseUrl + "/api/students?filter=" + encodeURIComponent("( {'user.userName' == '" + userName + "'} )");
  handleGet(studentByIdUrl, handler);
}


function isValidUser() {
  var userData = getCurruntUserData();
  var sysEpoch = (new Date).getTime() / 1000;
  var profile = getProfile();
  return (userData != null) && (userData.exp > sysEpoch) &&
         (profile.id != null) && (profile.name != null) && 
         (profile.roles != null) && (profile.userName != null);
}


function logout() {
  removeJWT();
  clearProfile();
  window.location.replace("./login.html");
}


export { setProfile, getProfile, getUserData, isValidUser, logout }

