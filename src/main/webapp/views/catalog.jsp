<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Каталог ${catalog.name}</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <style>tr { cursor:pointer; }</style>
    <script>
        $(document).ready(function($) {
            $(".clickable-row").click(function() {
                window.document.location = $(this).data("href");
            });
        });
    </script>
</head>
<body style="text-align: center">
    <img width="150" src="./image?name=${shop.imageUrl}">
    <h1>${catalog.name} магазину ${shop.name}</h1>

    <table style="margin-left:auto; margin-right:auto;" class="table table-bordered table-condensed table-striped table-hover col-lg-6">
        <tr>
            <th>Назва</th>
            <th>Колір</th>
            <th>Оперативна пам'ять</th>
            <th>Діагональ екрану</th>
            <th>Дата виходу</th>
        </tr>
        <c:forEach items="${catalog.catalogItems}" var="catalogItem">
            <tr class="clickable-row" data-href="./catalogitem?sm_id=${catalogItem.smartPhone.id}&cat_id=${catalog.id}">
                <td>${catalogItem.smartPhone.name}</td>
                <td>${catalogItem.smartPhone.color}</td>
                <td>${catalogItem.smartPhone.ram}</td>
                <td>${catalogItem.smartPhone.diagonal}</td>
                <td>${catalogItem.smartPhone.releaseDate}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
