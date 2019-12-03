<%--
  Created by IntelliJ IDEA.
  User: Sh1ne
  Date: 03.12.2019
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Інформація про товар</title>
</head>
<body>
    <h1>Смартфон ${item.smartPhone.name}</h1>
    <ul>
        <li>Ціна: ${item.price} ГРН</li>
        <li>В наявності: ${item.count} шт.</li>
        <li>Дата виходу: ${item.smartPhone.releaseDate}</li>
        <li>Оперативна пам'ять: ${item.smartPhone.ram} ГБ</li>
        <li>Діагональ екрану: ${item.smartPhone.diagonal}</li>
        <li>Колір: ${item.smartPhone.color}</li>
    </ul>
</body>
</html>
