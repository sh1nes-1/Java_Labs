<%--
  Created by IntelliJ IDEA.
  User: Sh1ne
  Date: 19.11.2019
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Магазин</title>
    <link rel="stylesheet" href="css/shop.css">
</head>
<body>
    <img width="150" src="${shop.imageUrl}">
    <h1>Каталоги магазину ${shop.name}</h1>
    <ul>
        <c:forEach items="${catalogs}" var="catalog">
            <li><a href="./catalog?id=${catalog.id}" class="catalog_name">${catalog.name}</a></li>
        </c:forEach>
    </ul>
</body>
</html>
