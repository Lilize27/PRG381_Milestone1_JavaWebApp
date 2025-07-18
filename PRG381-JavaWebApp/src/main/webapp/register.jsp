<%-- 
    Document   : register
    Created on : 09 Jul 2025, 09:41:06
    Author     : Cosmo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="style.css">
</head>
<body>
<header>
    <h1>Student Wellness System</h1>
        </header>
        <div class="container">
        <div class="card">
    <h2>Register</h2>


   <form action="register" method="post">
    Student Number: <input type="text" name="student_number" placeholder="Enter student number"><br/>
    Name: <input type="text" name="name" placeholder="Enter name"><br/>
    Surname: <input type="text" name="surname" placeholder="Enter surname"><br/>
    Email: <input type="text" name="email" placeholder="Enter email"><br/>
    Phone: <input type="text" name="phone" placeholder="Enter phone number"><br/>
    Password: <input type="password" name="password" placeholder="Enter password"><br/>
    <input type="submit" value="Register">
    <p>Already have an account? <a href="login.jsp">Back to Login</a></p>
   </form>

<p class="error"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>
<p class="success"><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></p>
        </div>
        </div>
</body>
</html>
