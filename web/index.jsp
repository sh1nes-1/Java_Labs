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

      <%
        if (request.getAttribute("userName") != null) {
          out.println("<p>User '" + request.getAttribute("userName") + "' added!</p>");
        }
      %>      

    <div class="shop">
      ShopName - ${test}
    </div>
  </body>
</html>
