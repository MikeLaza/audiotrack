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
    <link rel="stylesheet" href="../../css/info.css" type="text/css"/>

    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8">

    <title>show client tracks</title>

    <fmt:setLocale scope="session" value="${sessionScope.userLocal}"/>
    <fmt:setBundle basename="local.design" scope="session" var="loc"/>

    <fmt:message bundle="${loc}" key="local.text.album_id" var="album_id"/>
    <fmt:message bundle="${loc}" key="local.text.name_track" var="name_track"/>
    <fmt:message bundle="${loc}" key="local.text.duration" var="duration"/>
    <fmt:message bundle="${loc}" key="local.text.price" var="price"/>
    <fmt:message bundle="${loc}" key="local.text.name_album" var="name_album"/>


</head>
<body>
<div class="header">
    <h1>Audio-track-order</h1>
</div>

<div class="myContainer">
    <%@include file="../jsp/components/navbar.jsp"%>
    <%@include file="../jsp/components/user-menu.jsp"%>

</div>

<div class="desktop">








    <c:forEach var="track" items="${requestScope.trackList}">

        <div  class="col-6 myContainer">

            <div class="picture col-6" >
                <figure>

                    <img  style="border-radius: 25px" src="${pageContext.request.contextPath}/images/albums/${track.imageNumber}.jpg"/>



                </figure>
            </div>

            <div class="info col-6"  >

                <h3 style="text-transform: uppercase">
                        ${price}  ${track.price}$
                </h3>

                <table>
                    <tr>
                        <td >
                                ${name_album}
                        </td>
                        <td>
                                ${track.nameAlbum}
                        </td>
                    </tr>

                    <tr>
                        <td >
                                ${name_track}
                        </td>
                        <td>
                                ${track.nameTrack}
                        </td>
                    </tr>

                    <tr>
                        <td >
                                ${duration}
                        </td>
                        <td>
                                ${track.duration}
                        </td>
                    </tr>

                </table>

            </div>


        </div>

    </c:forEach>

    <%@include file="../jsp/paging.jsp"%>
</div>


</body>
</html>
