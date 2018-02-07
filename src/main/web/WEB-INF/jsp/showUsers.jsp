<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="role" value="${sessionScope.role}"/>
<c:set var="admin" value="admin"/>
<c:set var="client" value="client"/>
<c:set value="alert" var="alert"/>
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

    <title>show users</title>

    <fmt:setLocale scope="session" value="${sessionScope.userLocal}"/>
    <fmt:setBundle basename="local.design" scope="session" var="loc"/>

    <fmt:message bundle="${loc}" key="local.text.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.text.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.text.locale" var="locale"/>
    <fmt:message bundle="${loc}" key="local.text.balance" var="balance"/>
    <fmt:message bundle="${loc}" key="local.text.discount" var="discount"/>
    <fmt:message bundle="${loc}" key="local.button.update_user_discount" var="update_user_discount"/>
    <fmt:message bundle="${loc}" key="local.text.table_users" var="table_users"/>
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

        <h2 style="text-transform: uppercase">
            ${table_users}
        </h2>

    <table class="mainTable">

        <tr>
            <th style="text-transform: uppercase">${login}</th>
            <th style="text-transform: uppercase">${email}</th>
            <th style="text-transform: uppercase">${locale}</th>
            <th style="text-transform: uppercase">${balance}</th>
            <th style="text-transform: uppercase">${discount}</th>
            <th style="text-transform: uppercase"></th>

        </tr>

        <c:forEach var="user" items="${requestScope.userList}">
        <tr>

                <td>${user.loginUser}</td>
                <td> ${user.email}</td>
                <td> ${user.locale}</td>
                <td> ${user.balance}</td>
                <td> ${user.discount} %</td>

            <td>
                 <form action="/audiotrack" method="post" class="myButton">
                         <input class="lang_ru" type="hidden" name="command" value="before_update_user_discount"/>
                        <input type="hidden" name="user_id" value="${user.id}"/>
                        <input type="hidden" name="discount" value="${user.discount}"/>
                         <button class="menu" type="submit" style="text-transform: uppercase">${update_user_discount}</button>
                 </form>

            </td>
         </tr>

        </c:forEach>
        </table>

    </div>
    <%@include file="../jsp/paging.jsp"%>

</body>
    </html>
