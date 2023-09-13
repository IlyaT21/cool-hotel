<%-- 
    Document   : success
    Created on : Jun 15, 2023, 10:11:58 PM
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
            <h2>Uspesna registracija</h2>
            <p>Dobrodosao, <%= session.getAttribute("username") %></p>
            <%@ include file="partials/footer.jsp" %>
        </main>
    </body>
</html>
