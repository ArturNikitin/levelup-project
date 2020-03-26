
<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="form" type="webjava.ProfileForm" scope="request"/>

<!doctype html>
<html lang="en">
<head>
    <title>${sessionScope['verifiedUserName']}</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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
<div id="content">
    <h2>Hello, ${sessionScope['verifiedUserName']}!</h2>
    <p>You can update your address here</p>
    <p>Please fill in all text boxes</p>
    <hr>
    <form action="profile" method="post" enctype="application/x-www-form-urlencoded">
        <div class="form-group">
            <label>Country</label>
            <input type="text" name="country" class="form-control" value="${form.address.country}">
        </div>
        <div class="form-group">
            <label>City</label>
            <input type="text" name="city" class="form-control" value="${form.address.city}">
        </div>
        <div class="form-group">
            <label>Street</label>
            <input type="text" name="street" class="form-control" value="${form.address.street}">
        </div>
        <div class="form-group">
            <label>Postcode</label>
            <input type="text" name="postcode" class="form-control" value="${form.address.postCode}">
        </div>
        <P>
            <button type="submit" class="btn btn-primary">Update</button>
        </p>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>

<%--<html>
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
</html>--%>
