<%-- 
    Document   : register
    Created on : Jun 15, 2023, 9:49:14 PM
    Author     : Ilija
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resources/css/main.css">
        <title>JSP Page</title>
    </head>
    <body>
        <main class="form-default">
            <%@ include file="partials/header.jsp" %>
            <h2>Registruj se</h2>
            <form action="Register" method="post">
                <label for="username">Username:</label>
                <input type="text" name="username" id="username" required><br><br>

                <label for="password">Password:</label>
                <input type="password" name="password" id="password" required><br><br>

                <input type="submit" value="Registruj se">
            </form>
            <%@ include file="partials/footer.jsp" %>
        </main>
    </body>
</html>
