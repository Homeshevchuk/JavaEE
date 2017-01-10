<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.CarService" %>
<%@ page import="dao.DriverService" %>
<%@ page import="dao.ParkService" %>
<%@ page import="dao.UserService" %>
<%@ page import="entity.Car" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Driver" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Context ctx = new InitialContext();
    UserService userService = (UserService) ctx.lookup("java:comp/env/userService");
    CarService carService = (CarService) ctx.lookup("java:comp/env/carService");
    ParkService parkService = (ParkService) ctx.lookup("java:comp/env/parkService");
    DriverService driverService = (DriverService) ctx.lookup("java:comp/env/driverService");
    List<Driver> drivers = driverService.getAll();
    List<Car> carList = null;
    String tag = request.getParameter("filterTag");
    if (tag != null) {
        carList = carService.filterByTag(tag);
    } else {
        carList = carService.getAll();
    }
    pageContext.setAttribute("cars", carList);

%>
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
    <title>Autopark</title>
</head>
<body>


<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Cars</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Table </a></li>
                <li><a href="add.jsp">Add Car</a></li>

            </ul>
            <form class="navbar-form navbar-left">
            </form>
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search" name="filterTag">
                    <input class="btn btn-default" type="submit" value="Filter">
                </div>


            </form>
        </div>
    </div>
</nav>


<table class="table table-hover table-bordered">
    <thead>
    <tr>
        <th>Id</th>
        <th>Registration tag</th>
        <th>Park</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td>${car.id}</td>
            <td>${car.registrationTag}</td>
            <td>${car.park.name}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
