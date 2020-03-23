<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 21.03.2020
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
  <head>
      <link href="https://fonts.googleapis.com/css?family=Gotu&display=swap" rel="stylesheet">
      <title>marketplace</title>
      <style>
          h1 {font-family: 'Gotu', sans-serif;}
          .default {padding-left: 35%;}
      </style>
  </head>
  <body>
    <c:choose>
      <c:when test="${empty sessionScope['verifiedUserName']}">
          <div class="default">
          <h1>Welcome to the Clothing market place!</h1>
          <p>Sorry, we are working on the content</p>
          <p>Press 'Login' button to login or 'Register' button to register on our website</p>
          <form action="login" method="get" enctype="application/x-www-form-urlencoded">
              <button type="submit">Login</button>
          </form>
          <form action="registration" method="get" enctype="application/x-www-form-urlencoded">
              <button type="submit">Register</button>
          </form>
          </div>
      </c:when>
      <c:otherwise>
          <div class="default">
          <h2>Hello, ${sessionScope['verifiedUserName']}.</h2>
          <hr>
          <p>What do you want to do? Maybe update your profile?</p>
          <form action="mp" method="post" enctype="application/x-www-form-urlencoded">
              <button type="submit">Profile</button>
          </form>
          </div>
      </c:otherwise>
    </c:choose>

  </body>
</html>
