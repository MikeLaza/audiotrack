<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="role" value="${sessionScope.role}"/>
<c:set var="admin" value="admin"/>
<c:set var="client" value="client"/>
<c:set value="alert" var="alert"/>
<c:set var="command" value="${command}"/>

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
    <link rel="stylesheet" href="/css/spaceBetween.css" type="text/css"/>
    <link rel="stylesheet" href="/css/desktop.css" type="text/css"/>



    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <fmt:setLocale scope="session" value="${sessionScope.userLocal}"/>
    <c:choose>
        <c:when test="${role eq(admin)}">
            <title>admin</title>
        </c:when>
        <c:when test="${role eq(client)}">
            <title>client</title>
        </c:when>

    </c:choose>

</head>
<body>

<div>

    <div class="header">
        <h1>Audio-track-order</h1>
    </div>

    <div class="myContainer" >

        <%@include file="../components/navbar.jsp"%>
        <c:choose>
            <c:when test="${role eq(client)}">
                <%@include file="../components/user-menu.jsp"%>
            </c:when>

            <c:when test="${role eq(admin)}">
                <%@include file="../components/admin-menu.jsp"%>
            </c:when>
            <c:otherwise>
                ERROR
            </c:otherwise>
        </c:choose>


    </div>


</div>

</body>
</html>
