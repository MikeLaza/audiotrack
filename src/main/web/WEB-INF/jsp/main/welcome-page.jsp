<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="alert" var="alert"/>
<c:set var="command" value="${command}"/>
<c:set var="error_info" value="${error_info}"/>
<!DOCTYPE html>
<html>
<head>

        <link rel="stylesheet" href="/css/body.css" type="text/css"/>
        <link rel="stylesheet" href="/css/animation.css" type="text/css"/>
        <link rel="stylesheet" href="/css/responsiveDesign.css" type="text/css"/>
        <link rel="stylesheet" href="/css/header.css" type="text/css"/>
        <link rel="stylesheet" href="/css/footer.css" type="text/css"/>
        <link rel="stylesheet" href="/css/form.css" type="text/css"/>
        <link rel="stylesheet" href="/css/myButtons.css" type="text/css"/>
        <link rel="stylesheet" href="css/" type="text/css"/>


        <link rel="stylesheet" href="css/spaceBetween.css" type="text/css"/>

        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">



    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8">
    <title>Audio-track-order</title>

    <fmt:setLocale scope="session" value="${sessionScope.welcomeLocal}"/>
    <fmt:setLocale scope="request" value="${welcomeLocal}"/>
    <fmt:setBundle basename="local.design" scope="session" var="loc"/>


    <fmt:message bundle="${loc}" key="local.text.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="local.text.signIn" var="text_sign_in"/>
    <fmt:message bundle="${loc}" key="local.text.sign_in_on_website" var="sign_in_on_website"/>
    <fmt:message bundle="${loc}" key="local.text.signUp" var="text_sign_up"/>
    <fmt:message bundle="${loc}" key="local.text.sign_up_on_website" var="sign_up_on_website"/>

    <fmt:message bundle="${loc}" key="local.text.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.text.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.text.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.text.password2" var="password2"/>

    <fmt:message bundle="${loc}" key="local.button.lang.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.button.lang.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.text.aboutUs" var="about_us"/>
    <fmt:message bundle="${loc}" key="local.text.beyondSound" var="beyond_sound"/>

</head>

<body>

<div class="logo">
    <div id="animation">

    </div>
</div>


<div class="header">
    <h1>Audio-track-order</h1>
</div>

<div class="myContainer">

    <div class="col-2 col-m-2 item">
        <form action="/audiotrack" method="post" class="myButton">
            <input class="lang_en" type="hidden" name="command" value="change_local"/>
            <input class="lang_en" type="hidden" name="local" value="en_US"/>
            <input type="hidden" name="page" value="welcome_page"/>
            <button type="submit" style="text-transform: uppercase">${en_button}</button>
        </form>

        <form action="/audiotrack" method="post" class="myButton">
            <input class="lang_ru" type="hidden" name="command" value="change_local"/>
            <input class="lang_ru" type="hidden" name="local" value="ru_RU"/>
            <input type="hidden" name="page" value="welcome_page"/>
            <button type="submit" style="text-transform: uppercase" >${ru_button}</button>
        </form>
    </div>

    <div class="col-m-4 col-4 sign_in item">
        <div class="form">
            <form class=" sign_in_form" action="/audiotrack"  method="post" >
                <h1>${text_sign_in}</h1>
                <input type="hidden" name="command" value="sign_in"/>
                <input type="text" name="login" placeholder="${login}" id="login_sing_in" title="Латинские буквы, цифры, знак подчеркивания, первый символ – латинская буква, кол-во символов не менее 5"/>
                <input type="password" name="password" placeholder="${password}" id="password_sing_in"  title="Не менее 6 символов, не менее одной буквы в каждом регистре и не менее одной цифры"/>
                <button type="submit" >${sign_in_on_website}</button>
            </form>
        </div>
        <p class="error">
            ${error_info}
        </p>
    </div>

    <div class="col-m-4 col-4 sign_up item">
        <div class="form">
            <form class=" sign_up_form" action="/audiotrack" onsubmit="return valid_sign_up();" method="post" >
                <h1>${registration}</h1>
                <input type="hidden" name="command" value="sign_up"/>
                <input type="text" name="login" placeholder="${login}" id="login" title="Латинские буквы, цифры, знак подчеркивания, первый символ – латинская буква, кол-во символов не менее 5"/>
                <input type="password" name="password" placeholder="${password}" id="password"  title="Не менее 6 символов, не менее одной буквы в каждом регистре и не менее одной цифры"/>
                <input type="password" name="password2" placeholder="${password2}" id="repassword"  title="Не менее 6 символов, не менее одной буквы в каждом регистре и не менее одной цифры"/>
                <input type="text"  name="email" placeholder="${email}" id="email"/>
                <button type="submit" >${sign_up_on_website}</button>
            </form>
        </div>
    </div>


</div>


<div class="footer">
    <p>${beyond_sound}</p>
</div>

<script src="/js/validation_sign_up.js" defer> </script>

</body>

</html>