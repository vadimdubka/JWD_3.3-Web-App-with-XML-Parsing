<%@ page contentType="text/html;charset=UTF-8" %>
<div class="user-block">
    <p>Welcome, ${role} ${user.email}!</p>
    <a href="${pageContext.request.contextPath}/controller?command_type=logout">Log out</a>
</div>