function valid_sign_up() {
    var errorMessage=undefined;
    var isValid=undefined;
    var countValidParameters=0;

    var login=document.getElementById("login");
    var loginValue = login.value;
    var loginReg=/^[A-Za-z]\w{5,}$/;

    if(loginValue==""){
        errorMessage="Enter login";
        alert(errorMessage);
        login.style.border=" 2px solid red";
        // isValid=false;
    } else {
        if(loginReg.test(loginValue)==true){
            // isValid=true;
            countValidParameters++;
            login.style.border=" 0px";
        }else {
            login.style.border=" 2px solid red";
            errorMessage="Wrong login"
            alert(errorMessage);
            // isValid=false;
        }
    }

    var password1=document.getElementById("password");
    var passwordValue1=password.value;
    var passwordReg=/(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])^[0-9a-zA-Z]{6,}$/;

    if(passwordValue1==""){
        errorMessage="Enter password";
        alert(errorMessage);
        password1.style.border=" 2px solid red";
        // isValid=false;
    } else {
        if(passwordReg.test(passwordValue1)==true){
            // isValid=true;
            countValidParameters++;
            password1.style.border=" 0px";
        }else {
            password1.style.border=" 2px solid red";
            errorMessage="Wrong password"
            alert(errorMessage);
            // isValid=false
        }
    }



    var password2=document.getElementById("repassword");
    var passwordValue2=password2.value;

    if(passwordValue2=="") {
        errorMessage = "Enter repit password";
        alert(errorMessage);
        password2.style.border = " 2px solid red";
        // isValid = false;
    } else {
        if(passwordValue1!==passwordValue2){
            errorMessage = "Wrong repit password";
            alert(errorMessage);
            password2.style.border = " 2px solid red";
            // isValid = false;
        } else {
            // isValid=true;
            countValidParameters++;
            password2.style.border=" 0px";
        }
    }


    var email=document.getElementById("email");
    var emailValue = email.value;
    var emailReg=/^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$/;

    if(emailValue==""){
        errorMessage="Enter email";
        alert(errorMessage);
        email.style.border=" 2px solid red";
        // isValid=false;
    } else {
        if(emailReg.test(emailValue)==true){
            countValidParameters++;
            // isValid=true;
            email.style.border=" 0px";
        }else {
            email.style.border=" 2px solid red";
            errorMessage="Wrong email"
            alert(errorMessage);
            // isValid=false;
        }
    }
    debugger
    if(countValidParameters==4){
        return true;
    }else {
        return false;
    }


    // return isValid;
}


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

