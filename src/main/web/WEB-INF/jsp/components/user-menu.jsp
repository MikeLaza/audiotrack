<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="clientBalance" value="${balance}"/>
<fmt:setLocale scope="session" value="${sessionScope.userLocal}"/>
<fmt:setBundle basename="local.design" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.text.balance" var="balance"/>

<fmt:message bundle="${loc}" key="local.button.show_all_tracks" var="show_all_tracks"/>
<fmt:message bundle="${loc}" key="local.button.show_bought_tracks" var="show_bought_tracks"/>




<div class="account" class="col-8 col-m-8 ">
    <p style="text-transform: uppercase">
        ${balance}: ${clientBalance} $
    </p>
</div>



<div class="col-4 col-m-4 ">
    <form action="/audiotrack" method="post" class="myButton">
        <input class="lang_en" type="hidden" name="command" value="show_tracks"/>
        <button class="menu" type="submit" style="text-transform: uppercase">${show_all_tracks}</button>
    </form>


    <form action="/audiotrack" method="post" class="myButton">
        <input class="lang_ru" type="hidden" name="command" value="show_client_tracks"/>
        <button class="menu" type="submit" style="text-transform: uppercase">${show_bought_tracks}</button>
    </form>

</div>


