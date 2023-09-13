<%-- 
    Document   : ponuda
    Created on : Jun 27, 2023, 12:37:37 AM
    Author     : Ilija
--%>

<%@page import="java.util.List"%>
<%@page import="beans.Hotel"%>
<%@page import="database.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resources/css/main.css">
        <title>JSP Page</title>
    </head>
    <body>
        <main class="dashboard">
            <%@ include file="partials/header.jsp" %>
            <div class="user-holder">
                <%
                    DBConnection dbConnection = new DBConnection();
                    List<Hotel> hotelList = dbConnection.getAllHotels();
                %>
                <h1>Hoteli</h1>
                <% for (Hotel hotel : hotelList) {%>
                <p><%= hotel.getNaziv()%></p>
                <a href="hotel.jsp?hotelId=<%= hotel.getId()%>">Pogledaj sobe</a>
                <div class="border-bottom"></div>
                <% }%>
                <%
                    dbConnection.closeConnection();
                %>
            </div>
            <%@ include file="partials/footer.jsp" %>
        </main>
    </body>
</html>
