<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="role" value="${sessionScope.role}"/>
<c:set var="admin" value="admin"/>
<c:set var="client" value="client"/>
<c:set value="alert" var="alert"/>
<c:set var="command" value="${command}"/>
<c:set var="user_id" value="${user_id}"/>
<c:set var="discountClient" value="${discount}"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/body.css" type="text/css"/>
    <link rel="stylesheet" href="css/animation.css" type="text/css"/>
    <link rel="stylesheet" href="css/responsiveDesign.css" type="text/css"/>
    <link rel="stylesheet" href="css/header.css" type="text/css"/>
    <link rel="stylesheet" href="css/footer.css" type="text/css"/>
    <link rel="stylesheet" href="css/form.css" type="text/css"/>
    <link rel="stylesheet" href="css/myButtons.css" type="text/css"/>
    <link rel="stylesheet" href="css/" type="text/css"/>
    <link rel="stylesheet" href="css/spaceBetween.css" type="text/css"/>
    <link rel="stylesheet" href="css/desktop.css" type="text/css"/>
    <link rel="stylesheet" href="css/table.css" type="text/css"/>

    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <link rel="stylesheet" href="/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="/css/myStyle.css" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8">

    <title>update track</title>

    <fmt:setLocale scope="session" value="${sessionScope.userLocal}"/>
    <fmt:setBundle basename="local.design" scope="session" var="loc"/>

    <fmt:message bundle="${loc}" key="local.text.button.submit" var="submit"/>
    <fmt:message bundle="${loc}" key="local.text.enter_data" var="enter_data"/>
    <fmt:message bundle="${loc}" key="local.text.discount" var="discount"/>
</head>
<body>


<div class="header">
    <h1>Audio-track-order</h1>
</div>

<div class="myContainer">
    <%@include file="../jsp/components/navbar.jsp"%>
    <%@include file="../jsp/components/admin-menu.jsp"%>

</div>

<div class="desktop">
    <div class="form">
        <form  action="/audiotrack"  method="post" >
            <h1 style="color: #131010">${enter_data}</h1>
            <input type="hidden" name="command" value="update_user_discount"/>
            <input type="hidden" name="user_id" value="${user_id}"/>
            <label style="color: #131010">${discount}</label>
            <input  style="color: #131010" type="text"  name="discount" value="${discountClient}" />

            <button type="submit" >${submit}</button>
        </form>


    </div>
</div>

</body>
</html>

