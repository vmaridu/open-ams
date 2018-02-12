import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../styles/login.css";
import "../styles/staff.css";

import "popper.js";
import "bootstrap";

import { initPageLoadEvents as initLoginPageLoadEvents } from './login';
import { initPageLoadEvents as initStaffPageLoadEvents } from './staff';


$(document).ready(function () {
    //load diffent js based on the page 
    let locationSegments = location.pathname.split("/");
    let fileNmae = locationSegments[locationSegments.length - 1];

    if (fileNmae == "login.html") {
        initLoginPageLoadEvents();
    } else if (fileNmae == "admin.html") {
        //initAdminPageLoadEvents();
    } else if (fileNmae == "staff.html") {
        initStaffPageLoadEvents();
    } else if (fileNmae == "student.html") {
        //initStudentPageLoadEvents();
    } else {

    }
});