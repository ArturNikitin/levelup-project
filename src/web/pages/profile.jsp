<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 23.03.2020
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="form" type="webjava.ProfileForm" scope="request"/>

<html>
<head>
    <title>${sessionScope['verifiedUserName']}</title>
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
    <p>Hello, ${sessionScope['verifiedUserName']}.</p>
    <hr>
    <form action="profile" method="post" enctype="application/x-www-form-urlencoded">
        <p>Add your address</p>
        <p>Country</p>
        <input type="text" name="country" value="${form.address.country}">
        <p>City</p>
        <input type="text" name="city" value="${form.address.city}">
        <p>Street</p>
        <input type="text" name="street" value="${form.address.street}">
        <p>Postcode</p>
        <input type="text" name="postcode" value="${form.address.postCode}">
        <P>
            <input type="submit" value="Submit">
        </p>
    </form>
</div>

</body>
</html>
