<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 26.03.2020
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sell product</title>
</head>
<body>

<h2>Hello ${sessionScope['verifiedUserName']}</h2>
<p>Let's add you product to our shop, shall we?</p>
<p>Please fill in all position and then press submit button</p>
<p>Cheers!</p>

<form action="addProduct" method="post" enctype="application/x-www-form-urlencoded">
    <p>
        Product name:
        <input type="text" name="productName">
    </p>
    <p>
        Price:
        <input type="text" name="price">
    </p>
    <p>

    </p>

</form>
</body>
</html>
