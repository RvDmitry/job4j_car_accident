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
    <style type="text/css">
        th, td {
            text-align: center;
        }
    </style>
    <title>Accident</title>
</head>
<body>
<div class="container pt-3">
    <a href="<c:url value='/create'/>">Добавить инцидент</a>
</div>
<div class="container pt-3">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" style="width: 10%">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Text</th>
            <th scope="col">Address</th>
            <th scope="col">Edit accident</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${accidents}" var="accident">
            <tr>
                <td style="vertical-align: middle">
                    <c:out value="${accident.id}"/>
                </td>
                <td style="vertical-align: middle">
                    <c:out value="${accident.name}"/>
                </td>
                <td style="vertical-align: middle">
                    <c:out value="${accident.text}"/>
                </td>
                <td style="vertical-align: middle">
                    <c:out value="${accident.address}"/>
                </td>
                <td>
                    <form action="<c:url value="/edit"/>">
                        <input type="hidden" name="id" value="${accident.id}">
                        <input type="hidden" name="name" value="${accident.name}">
                        <input type="hidden" name="text" value="${accident.text}">
                        <input type="hidden" name="address" value="${accident.address}">
                        <button type="submit" class="btn btn-secondary">Edit</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>