function valid_sign_in() {
    var errorMessage=undefined;
    // var isValid=undefined;
    var countValidParameters=0;
    var login=document.getElementById("login_sign_in");
    var loginValue = login.value;
    var loginReg=/^[A-Za-z]\w{5,}$/;

    if(loginValue==""){
        errorMessage="Enter login";
        alert(errorMessage);
        login.style.border=" 2px solid red";
        // isValid=false;
    } else {
        if(loginReg.test(loginValue)){
            // isValid=true;
            countValidParameters++;
            login.style.border=" 0px";
        }else {
            login.style.border=" 2px solid red";
            errorMessage="Wrong login";
            alert(errorMessage);
            // isValid=false;
        }
    }

    var password=document.getElementById("password_sign_in");
    var passwordValue=password.value;
    var passwordReg=/(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])^[0-9a-zA-Z]{6,}$/;

    if(passwordValue==""){
        errorMessage="Enter password";
        alert(errorMessage);
        password.style.border=" 2px solid red";
        // isValid=false;
    } else {
        if(passwordReg.test(passwordValue)==true){
            // isValid=true;
            countValidParameters++
            password.style.border=" 0px";
        }else {
            password.style.border=" 2px solid red";
            errorMessage="Wrong password";
            alert(errorMessage);
            // isValid=false
        }
    }

    if (countValidParameters==2){
        return true;
    }else {
        return false;
    }


    // return isValid;
}
