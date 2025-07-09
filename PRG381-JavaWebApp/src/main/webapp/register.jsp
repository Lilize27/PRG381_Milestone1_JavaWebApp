<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>

    <h2>Register</h2>

    <form action="register" method="post">
        Student Number: <input type="text" name="student_number"><br/>
        Name: <input type="text" name="name"><br/>
        Surname: <input type="text" name="surname"><br/>
        Email: <input type="text" name="email"><br/>
        Phone: <input type="text" name="phone"><br/>
        Password: <input type="password" name="password"><br/>
        <input type="submit" value="Register">
    </form>

    <%-- Show error or success message --%>
    <p style="color:red;">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </p>
    <p style="color:green;">
        <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
    </p>

</body>
</html>
