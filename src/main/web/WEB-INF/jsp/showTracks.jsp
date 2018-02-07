<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="role" value="${sessionScope.role}"/>
<c:set var="admin" value="admin"/>
<c:set var="client" value="client"/>
<c:set value="alert" var="alert"/>
<c:set value="true" var="true"/>
<c:set var="command" value="${command}"/>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../../css/body.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/animation.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/responsiveDesign.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/header.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/footer.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/form.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/myButtons.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/" type="text/css"/>
    <link rel="stylesheet" href="../../css/spaceBetween.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/desktop.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/table.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/hoverAffect.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/info.css" type="text/css"/>

    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8">

    <title>show tracks</title>

    <fmt:setLocale scope="session" value="${sessionScope.userLocal}"/>
    <fmt:setBundle basename="local.design" scope="session" var="loc"/>

    <fmt:message bundle="${loc}" key="local.text.album_id" var="album_id"/>
    <fmt:message bundle="${loc}" key="local.text.name_track" var="name_track"/>
    <fmt:message bundle="${loc}" key="local.text.duration" var="duration"/>
    <fmt:message bundle="${loc}" key="local.text.price" var="price"/>

    <fmt:message bundle="${loc}" key="local.button.buy_track" var="buy_track"/>
    <fmt:message bundle="${loc}" key="local.button.comment_track" var="comment_track"/>
    <fmt:message bundle="${loc}" key="local.button.show_comments_track" var="show_comments_track"/>

    <fmt:message bundle="${loc}" key="local.button.update_track" var="update_track"/>
    <fmt:message bundle="${loc}" key="local.button.delete_track" var="delete_track"/>

    <fmt:message bundle="${loc}" key="local.text.are_you_sure" var="are_you_sure"/>
    <fmt:message bundle="${loc}" key="local.text.name_album" var="name_album"/>
    <fmt:message bundle="${loc}" key="local.text.track_list" var="track_list"/>


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
            ERROR 404 =)
        </c:otherwise>
    </c:choose>
</div>

<div class="desktop">

    <h2 style="text-transform: uppercase">
        ${track_list}
    </h2>

    <c:forEach var="track" items="${requestScope.trackList}">

                    <div  class="col-6 myContainer">

                        <div class="picture col-6" >
                            <figure>

                                <img  style="border-radius: 25px" src="${pageContext.request.contextPath}/images/albums/${track.imageNumber}.jpg"/>
                                <figcaption >

                                    <c:choose>
                                        <c:when test="${role eq(client)}">


                                            <form class="myButton" action="/audiotrack" method="post">
                                                <input type="hidden" name="track_id" value="${track.id}"/>
                                                <input type="hidden" name="command" value="make_order"/>
                                                <button class="menu" type="submit" style="text-transform: uppercase">${buy_track}</button>
                                            </form>

                                            <form class="myButton" action="/audiotrack" method="post">
                                                <input type="hidden" name="track_id" value="${track.id}"/>
                                                <input type="hidden" name="command" value="before_add_comment"/>
                                                <button class="menu" type="submit" style="text-transform: uppercase">${comment_track}</button>
                                            </form>


                                        </c:when>

                                        <c:when test="${role eq(admin)}">
                                            <form class="myButton" action="/audiotrack" method="post">
                                                <input type="hidden" name="track_id" value="${track.id}"/>
                                                <input type="hidden" name="album_id" value="${track.idAlbum}"/>

                                                <input type="hidden" name="name_album" value="${track.nameAlbum}"/>
                                                <input type="hidden" name="name_track" value="${track.nameTrack}"/>
                                                <input type="hidden" name="duration" value="${track.duration}"/>
                                                <input type="hidden" name="price" value="${track.price}"/>

                                                <input type="hidden" name="command" value="before_update_track"/>
                                                <button class="menu" type="submit" style="text-transform: uppercase">${update_track}</button>
                                            </form>

                                            <form class="myButton" action="/audiotrack" method="post">
                                                <input type="hidden" name="track_id" value="${track.id}"/>
                                                <input type="hidden" name="command" value="show_comments_track"/>
                                                <button class="menu" type="submit" style="text-transform: uppercase">${show_comments_track}</button>
                                            </form>


                                            <form class="myButton" action="/audiotrack" method="post" onclick="confirm('${are_you_sure}!')">
                                                <input type="hidden" name="track_id" value="${track.id}"/>
                                                <input type="hidden" name="command" value="delete_track"/>
                                                <button class="menu" type="submit" style="text-transform: uppercase">${delete_track} </button>
                                            </form>
                                        </c:when>
                                    </c:choose>

                                </figcaption>


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
