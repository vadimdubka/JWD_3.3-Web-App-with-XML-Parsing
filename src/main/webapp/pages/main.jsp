<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Welcome</title></head>
<body>
<h3>Welcome</h3>
<hr/>
Hello, ${sessionScope.role.role}!<br>
<a href="index.jsp">Go back to index page</a>
<hr/>
<a href="controller?command_type=logout">Logout</a>
</body>
</html>
<%--TODO В реальном примере следует передавать команду в скрытом поле и только по методу POST.
--%>