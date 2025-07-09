<%-- 
    Document   : login
    Created on : 09 Jul 2025, 09:53:11
    Author     : Cosmo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="RegisterServlet" method="post">
        Username: <input type="text" name="username"/><br/>
        Password: <input type="password" name="password"/><br/>
        Email: <input type="text" name="email"/><br/>
        <input type="submit" value="Register"/>
    </form>
    </body>
</html>
