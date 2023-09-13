<%-- 
    Document   : rezervacije
    Created on : Aug 24, 2023, 9:52:08 PM
    Author     : Ilija
--%>

<%@page import="beans.RoomType"%>
<%@page import="beans.Room"%>
<%@page import="java.util.List"%>
<%@page import="beans.Reservation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    // Get the user_id from the session
    int user_id = (int) session.getAttribute("user_id");

    // Get user reservations from the database
    DBConnection dbConnection = new DBConnection();
    List<Reservation> reservations = dbConnection.getUserReservations(user_id);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resources/css/main.css">
        <title>JSP Page</title>
    </head>
    <body>
        <main class="form-default">
            <%@ include file="partials/header.jsp" %>
            <h2>Vase rezervacije:</h2>
            <table>
                <tr>
                    <th>Cena</th>
                    <th>Status</th>
                    <th>Sprat</th>
                    <th>Blakona</th>
                    <th>Toalet</th>
                    <th>Tip sobe</th>
                </tr>
                <% for (Reservation reservation : reservations) {
                Room room = dbConnection.getRoomById(reservation.getRoom_id());
                String roomType = dbConnection.getRoomTypeTypeById(reservation.getRoom_id());
                %>
                <tr>
                    <td><%= reservation.getTotal()%>€</td>
                    <td><%= reservation.getStatus()%></td>
                    <td><%= room.getFloor()%></td>
                    <td><%= room.getBalcon()%></td>
                    <td><%= room.getToilet()%></td>
                    <td><%= roomType%></td>
                    <td><a href="DeleteReservation?reservationId=<%= reservation.getId() %>">Otkazi</a></td>
                </tr>
                <% }%>
            </table>
            <%@ include file="partials/footer.jsp" %>
        </main>
    </body>
</html>
