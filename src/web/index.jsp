<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>marketplace</title>
    <%--Google font--%>
    <link href="https://fonts.googleapis.com/css?family=Gotu&display=swap" rel="stylesheet">
    <style>
        h1 {
            font-family: 'Gotu', sans-serif;
            background-color:  deepskyblue;
        }
        .default {
            text-align: left;
            width: 500px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>

<c:choose>
    <c:when test="${empty sessionScope['verifiedUserName']}">
        <div class="default">
            <h1>Welcome to the Clothing market place!</h1>
            <p>Sorry, we are working on the content</p>
            <p>You can log in or sign up if you don't have an account yet</p>
            <form action="login" method="get" enctype="application/x-www-form-urlencoded">
                <input class="btn btn-primary" type="submit" value="Log in">
            </form>
            <br>
            <form action="reg" method="get" enctype="application/x-www-form-urlencoded">
                <input class="btn btn-primary" type="submit" value="Sign in">
            </form>
        </div>
    </c:when>
    <c:otherwise>
        <div class="default">
            <h2>Hello, ${sessionScope['verifiedUserName']}.</h2>
            <hr>
            <p>What do you want to do? Maybe update your profile?</p>
            <form action="profile" method="get" enctype="application/x-www-form-urlencoded">
<%--                <button type="submit">Profile</button>--%>
                <input class="btn btn-primary" type="submit" value="Profile">
            </form>
            <br>
            <form action="marketplace" method="post">
                <input class="btn btn-primary" type="submit" value="Logout">
<%--                <button type="submit">Logout</button>--%>
            </form>
        </div>
    </c:otherwise>
</c:choose>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>

