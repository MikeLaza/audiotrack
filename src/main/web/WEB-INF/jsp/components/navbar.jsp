<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale scope="session" value="${sessionScope.userLocal}"/>
<fmt:setBundle basename="local.design" scope="session" var="loc"/>

<fmt:message bundle="${loc}" key="local.button.lang.ru" var="locale_ru"/>
<fmt:message bundle="${loc}" key="local.button.lang.en" var="locale_en"/>
<fmt:message bundle="${loc}" key="local.button.exit" var="exit"/>



<div class="col-2 col-m-2">

    <form action="/audiotrack" method="post" class="myButton">
        <input class="lang_en" type="hidden" name="command" value="change_local"/>
        <input class="lang_en" type="hidden" name="local" value="en_US"/>
        <input type="hidden" name="page" value="universal_page"/>
        <button type="submit" style="text-transform: uppercase">${locale_en}</button>
    </form>

    <form action="/audiotrack" method="post" class="myButton">
        <input class="lang_ru" type="hidden" name="command" value="change_local"/>
        <input class="lang_ru" type="hidden" name="local" value="ru_RU"/>
        <input type="hidden" name="page" value="universal_page"/>
        <input type="hidden" name="contextPath" value="pageContext.request.contextPath"/>
        <button type="submit" style="text-transform: uppercase">${locale_ru}</button>
    </form>

    <form  action="/audiotrack" method="post" accept-charset="UTF-8" class="myButton" >
        <input type="hidden" name="command" value="log_out"/>
        <button class="exitButton" type="submit" style="text-transform: uppercase">${exit}</button>
    </form>
</div>


    <div class="logo">
        <div id="animation">

        </div>
    </div>

