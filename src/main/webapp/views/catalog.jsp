<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sh1ne
  Date: 01.12.2019
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Каталог ${catalog.name}</title>
    <link rel="stylesheet" href="css/shop.css">
</head>
<body>
    <img width="150" src="${shop.imageUrl}">
    <h1>${catalog.name} магазину ${shop.name}</h1>

    <ul>
        <c:forEach items="${catalog.catalogItems}" var="catalogItem">
            <li><a href="./item?cat_id=${catalog.id}&sm_id=${catalogItem.smartPhone.id}">${catalogItem.smartPhone}</a></li>
        </c:forEach>
    </ul>
</body>
</html>
