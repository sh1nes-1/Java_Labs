<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sh1ne
  Date: 10.12.2019
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Смартфон</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
    <div class="m-5">
        <c:if test="${add}">
            <h1>Додати смартфон</h1>
        </c:if>
        <c:if test="${!add}">
            <h1>Редагувати смартфон</h1>
        </c:if>

        <hr>

        <form method="POST">
            <div class="form-group">
                <label>Назва:</label>
                <input name="name" type="text" placeholder="Введіть назву смартфона" value="${smartPhone.name}" class="form-control">
            </div>
            <div class="form-group">
                <label>Стартова ціна:</label>
                <input name="price" type="text" placeholder="Введіть стартову ціну смартфона" value="${smartPhone.price}" class="form-control">
            </div>
            <div class="form-group">
                <label>Дата виходу:</label>
                <input name="releaseDate" type="date" placeholder="Введіть дату виходу смартфона" value="${smartPhone.releaseDate}" class="form-control">
            </div>
            <div class="form-group">
                <label>Об'єм оперативної памяті:</label>
                <input name="ram" type="number" placeholder="Введіть об'єм оперативної пам'яті смартфона в МБ" value="${smartPhone.ram}" class="form-control">
            </div>
            <div class="form-group">
                <label>Діагональ:</label>
                <input name="diagonal" type="number" step="0.01" value="${smartPhone.diagonal}" placeholder="Введіть діагональ смартфона в дюймах" class="form-control">
            </div>
            <div class="form-group">
                <label>Колір:</label>
                <select name="color" size="1" class="form-control">
                    <c:forEach items="${colors}" var="color">
                        <option value="${color}" <c:if test="${smartPhone.color == color}">selected</c:if>>${color}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" class="btn btn-primary" value="Зберегти">
        </form>
    </div>
</body>
</html>
