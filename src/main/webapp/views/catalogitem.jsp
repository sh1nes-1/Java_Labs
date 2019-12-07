<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Інформація про товар</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
    <div class="card mt-3 p-3 mx-auto" style="width: 600px;">
        <div class="card-body">
            <h1>Смартфон ${item.smartPhone.name}</h1>
            <ul>
                <li>Ціна: ${item.price} ГРН</li>
                <li>В наявності: ${item.count} шт.</li>
                <li>Дата виходу: ${item.smartPhone.releaseDate}</li>
                <li>Оперативна пам'ять: ${item.smartPhone.ram} МБ</li>
                <li>Діагональ екрану: ${item.smartPhone.diagonal}</li>
                <li>Колір: ${item.smartPhone.color}</li>
            </ul>
        </div>
    </div>
</body>
</html>
