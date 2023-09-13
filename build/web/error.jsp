<%-- 
    Document   : error
    Created on : Jun 15, 2023, 10:34:35 PM
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
            <h2>Greska!</h2>
            <p>Proverite unete podatke i pokusajte ponovo</p>
            <p>${errorMessage}</p>
            <%@ include file="partials/footer.jsp" %>
        </main>
    </body>
</html>
