
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <style>
        #content{
            text-align: center;
            width: 500px;
            margin-left: auto;
            margin-right: auto;
        }
        h1 {
            margin: 0;
        }
    </style>
</head>
<body>
<div id="content">
    <h1>Hello, sir.</h1>
    <p>This is the login page</p>
    <div id="buttons">
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
                    <a href="reg">Did you forget your password?</a>
                </p>
            </c:otherwise>
        </c:choose>
    </div>

</div>
</body>
</html>
