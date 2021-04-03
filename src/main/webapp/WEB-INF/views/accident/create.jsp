<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Create Accident</title>
</head>
<body>
<div class="container pt-3">
    <form action="<c:url value='/save'/>" method='POST'>
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Название</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="Название">
            </div>
        </div>
        <div class="form-group row">
            <label for="text" class="col-sm-2 col-form-label">Описание</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="text" name="text" placeholder="Описание">
            </div>
        </div>
        <div class="form-group row">
            <label for="address" class="col-sm-2 col-form-label">Адрес</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="address" name="address" placeholder="Адрес">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>