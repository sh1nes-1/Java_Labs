<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sh1ne
  Date: 19.11.2019
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>SmartShops - Головна</title>
    <link rel="stylesheet" href="css/index.css">
  </head>
  <body>
    <div class="shops">
      <h1>Список магазинів</h1>
      <ul>
        <c:forEach items="${shops}" var="shop">
          <li>
            <div class="shop">
              <a href="shop?id=${shop.id}" class="shop_img"><img src="${shop.imageUrl}"></a>
              <a href="shop?id=${shop.id}" class="shop_name">${shop.name}</a>
            </div>
          </li>
        </c:forEach>
      </div>
    </div>
  </body>
</html>
