<%-- 
    Document   : search
    Created on : Sep 3, 2023, 9:07:59 PM
    Author     : Ilija
--%>

<%@page import="beans.Room"%>
<%@page import="java.util.List"%>
<%@page import="beans.RoomType"%>
<%@page import="database.DBConnection"%>
<%@page import="beans.Hotel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resources/css/main.css">
        <title>Search</title>
    </head>
    <body>
        <%
            DBConnection dbConnection = new DBConnection();
            int hotelId = (int) request.getAttribute("hotel_id");
            Hotel hotel = dbConnection.getHotelById(hotelId);
            List<RoomType> roomTypeList = dbConnection.getAllRoomTypes();
            List<Room> searchResults = (List<Room>) request.getAttribute("searchResults");
        %>
        <main class="dashboard">
            <%@ include file="partials/header.jsp" %>
            <h1><%= hotel.getNaziv()%> - Rezultati pretrage</h1>
            <%
                String role = (String) session.getAttribute("role");
            %>
            <div class="room-holder">
                <div class="user-holder">
                    <h2>Sobe</h2>
                    <% if (searchResults != null && !searchResults.isEmpty()) { %>
                    <% for (Room room : searchResults) {
                            if (!dbConnection.isRoomReserved(room.getId())) {%>
                    <p>Vrsta sobe - <%= dbConnection.getRoomTypeTypeById(room.getRoomTypeId())%></p>
                    <p>Sprat sobe - <%= room.getFloor()%></p>
                    <p>Broj toaleta - <%= room.getToilet()%></p>
                    <p>Broj balkona - <%= room.getBalcon()%></p>
                    <p>Cena nocenja - <%= room.getPrice()%>$</p>
                    <form action="Reserve" method="post">
                        <input type="hidden" name="room_id" value="<%= room.getId()%>">
                        <input type="hidden" name="price" value="<%= room.getPrice()%>">
                        <input type="hidden" name="user_id" value="<%= session.getAttribute("user_id")%>">
                        <input type="number" name="nights" placeholder="Unesite broj nocenja" required>
                        <button type="submit">Rezervisi</button>
                    </form>
                    <div class="border-bottom"></div>
                    <%}
                        }%>
                </div>
                <%}%>
                <%@ include file="partials/footer.jsp" %>
        </main>
        <%
            dbConnection.closeConnection();
        %>
    </body>
</html>
