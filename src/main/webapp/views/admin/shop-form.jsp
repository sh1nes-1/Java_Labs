<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Магазин</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
    <div class="m-5">
        <c:if test="${add}">
            <h1>Додати магазин</h1>
        </c:if>
        <c:if test="${!add}">
            <h1>Редагувати магазин</h1>
        </c:if>
        <form method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label>Назва:</label>
                <input name="name" type="text" placeholder="Введіть назву магазину" value="${shop.name}" class="form-control">
            </div>
            <div class="form-group">
                <label>Виберіть логотип магазину:</label><br>
                <input name="logo" type="file" accept=".jpg, .jpeg, .png">
            </div>

            <input type="submit" class="btn btn-primary" value="Зберегти">
        </form>
    </div>
</body>
</html>
