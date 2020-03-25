<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 23.03.2020
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        #content{
            text-align: center;
            width: 500px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<div id="content">
    <h2 id="heading">Registration</h2>
    <form action="reg" method="post" enctype="application/x-www-form-urlencoded">
        <p>
            Login
            <input type="text" name="user" value="${param['user']}">
        </p>
        <p>
            Email:
            <input type="test" name="email">
        </p>
        <p>
            Password:
            <input type="password" name="password" value="${param['password']}">
        </p>
        <p>
            <input type="submit" value="Login">
        </p>
    </form>
</div>
</body>
</html>
