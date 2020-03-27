<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="database.utilities.ClothingSize" %>
<%@page import="database.utilities.ClothingType" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>New product</title>

    <%--Custom CSS--%>
    <style>
        #main{
            width: 500px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<div id="main">
    <h2>Hello ${sessionScope['verifiedUserName']}</h2>
    <p>Let's add you product to our shop, shall we?</p>
    <p>Please fill in all position and then press submit button</p>

    <div>
        <form action="create" method="post" enctype="application/x-www-form-urlencoded">
            <div class="form-group">
                <label for="formGroupExampleInput">Name of the product</label>
                <input type="text" name="productName" class="form-control" id="formGroupExampleInput" placeholder="T-shirt Palace">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">$</span>
                    <span class="input-group-text">0.00</span>
                </div>
                <input type="text" name="price" class="form-control" aria-label="Dollar amount (with dot and two decimal places)" placeholder="12.57">
            </div>
            <div class="form-group">
                <label for="exampleFormControlSelect1">Type of the clothing</label>
                <select name="type" class="form-control" id="exampleFormControlSelect2">
                    <c:forEach items="<%=ClothingType.values()%>" var="type">
                        <option>${type.name()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleFormControlSelect1">Size</label>
                <select name="size" class="form-control" id="exampleFormControlSelect1">
                    <c:forEach items="<%=ClothingSize.values()%>" var="size">
                        <option>${size.name()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                </div>
                <div class="custom-file">
                    <input type="file" name="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
                    <label class="custom-file-label" for="inputGroupFile01">Choose photo</label>
                </div>
            </div>
            <div class="form-group">
                <label for="exampleFormControlTextarea1">Description</label>
                <textarea name="description" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
            </div>
            <P>
                <button type="submit" class="btn btn-primary">Publish product</button>
            </p>
        </form>
    </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
