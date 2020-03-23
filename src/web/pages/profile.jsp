<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 23.03.2020
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>

<p>Hello, ${sessionScope['verifiedUserName']}.</p>
<hr>
<form action="profile" method="post" enctype="application/x-www-form-urlencoded">
    <p>Add your address</p>
    <p>Country</p>
    <input type="text" name="country">
    <p>City</p>
    <input type="text" name="City">
    <p>Street</p>
    <input type="text" name="street">
    <p>Postcode</p>
    <input type="text" name="postcode">
</form>
<form action="profile" method="post" enctype="application/x-www-form-urlencoded">
    <button type="submit">Logout</button>
</form>
</body>
</html>
