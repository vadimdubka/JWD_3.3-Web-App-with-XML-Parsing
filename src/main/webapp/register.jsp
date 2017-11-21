<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div align="center">
    <p>Registration form:</p>
    <form action="controller" method="post">
        <input type="hidden" name="command_type" value="register"/>
        Email<br/>
        <input type="email" name="email" value="" required/><br/>
        Password<br/>
        <input type="password" name="password" value="" required/><br/>
        Repeat Password<br/>
        <input type="password" name="password_again" value="" required/><br/>
        First Name<br/>
        <input type="text" name="fname" value=""/><br/>
        Middle Name<br/>
        <input type="text" name="mname" value=""/><br/>
        Last Name<br/>
        <input type="text" name="lname" value=""/><br/>
        ${errorMessage}<br/>
        <input type="submit" value="Register"/><br/>
    </form>
</div>
<a href="index.jsp">Go back to index page</a>
</body>
</html>
