<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="configuration.Configuration" %>
<%@page import="dao.ParkDAO" %>
<%@page import="entity.Park" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Car" %>
<%@ page import="dao.CarDAO" %>
<%
    ParkDAO parkDAO = new ParkDAO(Configuration.getDataSource());

    String tag = request.getParameter("RegistrationTag");
    String parkId = request.getParameter("Park");
    if(tag!=null&&parkId!=null){
        CarDAO carDAO = new CarDAO(Configuration.getDataSource());
        Car car = new Car(tag,Long.parseLong(parkId));
        carDAO.create(car);
        response.sendRedirect("index.jsp");
    }

    List<Park> parks = parkDAO.getAll();
    pageContext.setAttribute("parks", parks);

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/add.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <title>Add car</title>
</head>
<body>


<nav class="navbar navbar-inverse" style="margin-bottom: 0px">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Cars</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="index.jsp">Table </a></li>
            <li class="active"><a href="add.jsp">Add Car</a></li>
        </ul>
    </div>

</nav>
<form class="col-md-2 col-md-offset-5" id="form">
    <div class="form-group">
        <label>Registration tag</label>
        <input type="text" class="form-control" name="RegistrationTag">
    </div>
    <div class="form-group">
        <label>Choose park</label>
        <select class="form-control" name="Park">
            <c:forEach items="${parks}" var="park">
                <option value="${park.id}">${park.name}</option>
            </c:forEach>
        </select>

    </div>
    <div class="form-group">
            <input class="btn btn-default" type="submit" value="Add" href="">
    </div>
</form>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
