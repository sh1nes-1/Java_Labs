<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sh1ne
  Date: 10.12.2019
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список смартфонів</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
    <div class="m-5">
        <h1>Список смартфонів</h1>
        <a href="./add-smartphone"><i class="fa fa-plus" aria-hidden="true"></i> Додати смартфон</a>
        <hr>
        <ul>
            <c:forEach items="${smartPhones}" var="smartPhone">
                <li><a href="./edit-smartphone?id=${smartPhone.id}">${smartPhone}</a></li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
