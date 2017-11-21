<%@ page contentType="text/html;charset=UTF-8" %>
<form action="controller" method="post">
    <input type="hidden" name="command_type" value="login"/>
    Email<br/>
    <input type="email" name="email" value="" required/><br/>
    Password<br/>
    <input type="password" name="password" value="" required/><br/>
    <input type="submit" value="Log in"/>
</form>
