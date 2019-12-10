<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список магазинів</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="m-5">
    <h1>Список магазинів</h1>
    <a href="./add-shop"><i class="fa fa-plus" aria-hidden="true"></i> Додати магазин</a>
    <hr>
    <ul>
        <c:forEach items="${shops}" var="shop">
            <li><a href="./edit-shop?id=${shop.id}">${shop.name}</a></li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
