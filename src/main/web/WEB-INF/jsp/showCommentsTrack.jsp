<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="role" value="${sessionScope.role}"/>
<c:set var="admin" value="admin"/>
<c:set var="client" value="client"/>
<c:set value="alert" var="alert"/>
<c:set value="true" var="true"/>
<c:set var="command" value="${command}"/>

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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8">

    <title>show comments track</title>

    <fmt:setLocale scope="session" value="${sessionScope.userLocal}"/>
    <fmt:setBundle basename="local.design" scope="session" var="loc"/>

    <fmt:message bundle="${loc}" key="local.text.user_id" var="user_id"/>
    <fmt:message bundle="${loc}" key="local.text.user" var="userLoginStr"/>

    <fmt:message bundle="${loc}" key="local.text.comment_t" var="comment_t"/>


</head>
<body>
<div class="header">
    <h1>Audio-track-order</h1>
</div>

<div class="myContainer">
    <%@include file="../jsp/components/navbar.jsp"%>

    <c:choose>
        <c:when test="${role eq(client)}">
            <%@include file="../jsp/components/user-menu.jsp"%>
        </c:when>

        <c:when test="${role eq(admin)}">
            <%@include file="../jsp/components/admin-menu.jsp"%>
        </c:when>
        <c:otherwise>
            ERROR
        </c:otherwise>
    </c:choose>

</div>

<div class="desktop">
    <table>
        <thead>
        <tr>
            <th style="text-transform: uppercase">${userLoginStr}</th>
            <th style="text-transform: uppercase">${comment_t}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="comment" items="${requestScope.commentsList}">
        <tr>
            <td>${comment.userLogin}</td>
            <td>${comment.text}</td>
        </tr>
        </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>
