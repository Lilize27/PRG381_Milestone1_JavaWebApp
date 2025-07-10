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
        <form action="LoginServlet" method="post">
        Username: <input type="text" name="student_number" required/><br/>
        Password: <input type="password" name="password" required/><br/>
        <input type="submit" value="Login"/>
    </form>
         <%-- show error message if login fails --%>
        <p style="color:red">
            <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
        </p> 
    </body>
</html>
