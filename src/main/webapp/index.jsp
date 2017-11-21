<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Index</title></head>
<body>
<section class="left-bar">
    <c:choose>
        <c:when test="${sessionScope.user.role != null && 'PLAYER'.equals(sessionScope.user.role.toString())}">
            <%@include file="user.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="login.jsp" %>
            <a href="register.jsp">Go to Registration!</a><br>
        </c:otherwise>
    </c:choose>
</section>

<h2>Task 3.1 Simple Web App</h2>
<form action="controller" method="get">
    Name: <input type="text" name="name" value=""/><br/>
    Surname: <input type="text" name="surname" value=""/><br/>
    <input type="hidden" name="command_type" value="show_users_by_criteria">
    <input type="submit" value="Show users by criteria"/><br/>
</form>

<form action="controller" method="get">
    <input type="hidden" name="command_type" value="show_all_users">
    <input type="submit" value="Show all users">
</form>

<div align="center">
    <h2>Task 3.3 XML Parser</h2>
    <form action="controller" method="get">
        <input type="hidden" name="command_type" value="xml_parse"/>
        <input type="hidden" name="document_path" value="data/candies.xml"/>
        <input type="hidden" name="schema_path" value="data/candies.xsd"/>
        <input type="hidden" name="page_number" value="1"/>
        <button type="submit" name="parser_type" value="sax">SAX</button>
        <br/><br/>
        <button type="submit" name="parser_type" value="stax">StAX</button>
        <br><br>
        <button type="submit" name="parser_type" value="dom">DOM</button>
    </form>
</div>
</body>
</html>

