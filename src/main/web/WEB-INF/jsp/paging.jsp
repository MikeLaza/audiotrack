<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="currentPage" value="${currentPage}"/>
<c:set var="countPages" value="${countPages}"/>
<c:set var="command" value="${command}"/>


<div class="paging">
    <table>

        <tr>
            <c:if test="${currentPage != 1}">
                <td>

                    <form  action="/audiotrack" method="post" accept-charset="UTF-8" class="myButton" >
                        <input type="hidden" name="command" value="${command}"/>
                        <input type="hidden" name="page" value="${currentPage - 1}"/>

                        <button class="pag" type="submit" style="text-transform: uppercase">${prev}prev</button>
                    </form>


                </td>
            </c:if>
            <c:forEach begin="1" end="${countPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td id="current">${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td>

                            <form  action="/audiotrack" method="post" accept-charset="UTF-8" class="myButton" >
                                <input type="hidden" name="command" value="${command}"/>
                                <input type="hidden" name="page" value="${i}"/>

                                <button class="exitButton" type="submit" style="text-transform: uppercase">${i}</button>
                            </form>


                        </td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt countPages}">
                <td>
                    <form  action="/audiotrack" method="post" accept-charset="UTF-8" class="myButton" >
                        <input type="hidden" name="command" value="${command}"/>
                        <input type="hidden" name="page" value="${currentPage + 1}"/>

                        <button class="pag" type="submit" style="text-transform: uppercase">${next}next</button>
                    </form>


                </td>
            </c:if>
        </tr>
    </table>
</div>








