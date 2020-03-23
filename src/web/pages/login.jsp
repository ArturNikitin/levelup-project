<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 23.03.2020
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Hello, sir.</h1>
<p>This is the login page</p>

<form method="post" action = "login"  enctype="application/x-www-form-urlencoded">
    <p>
        <label>
            Login:
            <input type="text" name="username" value="${param['login']}">
        </label>
    </p>
    <p>
        <label>
            Password:
            <input type="password" name="password">
        </label>
    </p>
    <p>
        <input type="submit" value="Login">
    </p>
</form>

<c:choose>
    <c:when test="${empty sessionScope['notVerifiedName']}">
    </c:when>
    <c:otherwise>
        <p>
            <a href="http://t1.gstatic.com/images?q=tbn:ANd9GcTmePi51QnjKYleQ1pFPZUbuC8k2bHQ1aMDtRKF2bSxQOf1isneynPokha_QNgpoqGp3IliCfX5LhJFclfPG6k">Did you forget your password?</a>
        </p>
    </c:otherwise>
</c:choose>

<%--
<p>For now just press the button below to return to the main page</p>
<form action="/mp", method="get", enctype="application/x-www-form-urlencoded">
    <button type="submit">return</button>
</form>--%>
</body>
</html>
