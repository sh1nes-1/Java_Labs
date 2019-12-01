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
</head>
<body>
    <h1>Каталог ${catalog.name}</h1>

    <ul>
        <c:forEach items="${catalog.catalogItems}" var="catalogItem">
            <li>${catalogItem.smartPhone.name}</li>
        </c:forEach>
    </ul>
</body>
</html>
