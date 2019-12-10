<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Головна</title>
    <link rel="stylesheet" href="css/index.css">
  </head>
  <body>
    <div class="shops">
      <h1>Список магазинів</h1>
      <ul>
        <c:forEach items="${shops}" var="shop">
          <li>
            <div class="shop">
              <a href="shop?id=${shop.id}" class="shop_img"><img src="./image?name=${shop.imageUrl}"></a>
              <a href="shop?id=${shop.id}" class="shop_name">${shop.name}</a>
            </div>
          </li>
        </c:forEach>
      </div>
    </div>
  </body>
</html>
