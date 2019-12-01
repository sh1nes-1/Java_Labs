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
  </head>
  <body>
    <h1>Список магазинів</h1>
    <div>
      <div class="card-deck" style="flex-flow: row wrap;">
        <c:forEach items="${shops}" var="shop">
          <div class="card" style="display: flex">
            <img src="" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">${shop.name}</h5>
              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
              <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </body>
</html>
