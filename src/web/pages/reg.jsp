<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <title>Sign up</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <%--CustomCSS--%>

    <style>
        #content{
            text-align: left;
            width: 500px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>

</head>
<body>
<%--@elvariable id="form" type="webjava.RegistrationForm"--%>
<div id="content">
    <h2 id="heading">Registration</h2>
    <form:form modelAttribute="form" action="reg" method="post" enctype="application/x-www-form-urlencoded">
        <div class="form-group">
            <label>Login</label>
            <form:input type="text" path="login" class="form-control"/>
            <form:errors path="login" cssStyle="color: red" />
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <form:input type="text" path="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"/>
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            <form:errors path="email" cssStyle="color: red" />
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <form:input type="password" path="passwrod" class="form-control" id="exampleInputPassword1"/>
            <form:errors path="passwrod" cssStyle="color: red" />
        </div>
        <button type="submit" class="btn btn-primary">Sing in</button>
    </form:form>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>