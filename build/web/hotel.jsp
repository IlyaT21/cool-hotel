<%-- 
    Document   : hotel
    Created on : Jun 25, 2023, 3:24:47 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <%
            DBConnection dbConnection = new DBConnection();
            String hotelIdStr = request.getParameter("hotelId");
            int hotelId = Integer.parseInt(hotelIdStr);
            Hotel hotel = dbConnection.getHotelById(hotelId);
            List<Hotel> hotelList = dbConnection.getAllHotels();
            List<RoomType> roomTypeList = dbConnection.getAllRoomTypes();
            List<Room> roomList = dbConnection.getAllRooms(hotel.getId());
        %>
        <main class="dashboard">
            <%@ include file="partials/header.jsp" %>
            <h1><%= hotel.getNaziv()%></h1>
            <%
                String role = (String) session.getAttribute("role");
                if (!"1".equals(role)) {%>
            <div class="room-holder">
                <form action="AddRoom" method="post">
                    <h3>Dodaj sobu</h3>
                    <label>Sprat:</label>
                    <input type="number" name="floor" value="" required>
                    <label>Toalet:</label>
                    <input type="number" name="toilet" value="" required>
                    <label>Balkon:</label>
                    <input type="number" name="balcon" value="" required>
                    <label>Cena:</label>
                    <input type="number" name="price" value="" required>
                    <label>Tip sobe:</label>
                    <select name="room_type_id" required>
                        <option value="">Izaberi</option>
                        <%
                            for (RoomType roomType : roomTypeList) {%>
                        <option value="<%= roomType.getId()%>"><%= roomType.getType()%></option>
                        <%}%>
                    </select>
                    <input type="hidden" name="hotel_id" value="<%= hotel.getId()%>">
                    <button type="submit">Dodaj sobu</button>
                </form>
                </>
                <div class="user-holder">
                    <h2>Sobe</h2>
                    <% for (Room room : roomList) {%>
                    <form action="EditRoom" method="post">
                        <p>Id: <%= room.getId()%></p>
                        <input type="hidden" name="id" value="<%= room.getId()%>">
                        <label>Sprat sobe:</label>
                        <input type="number" name="floor" value="<%= room.getFloor()%>">
                        <label>Broj kupatila:</label>
                        <input type="number" name="toilet" value="<%= room.getToilet()%>">
                        <label>Broj balkona:</label>
                        <input type="number" name="balcon" value="<%= room.getBalcon()%>">
                        <label>Cena nocenja:</label>
                        <input type="number" name="price" value="<%= room.getPrice()%>">
                        <input type="hidden" name="hotel_id" value="<%= hotel.getId()%>">
                        <label>Tip sobe:</label>
                        <select name="room_type_id" required>
                            <option value="<%= room.getRoomTypeId()%>"><%= dbConnection.getRoomTypeTypeById(room.getRoomTypeId())%></option>
                            <%
                                for (RoomType roomType : roomTypeList) {%>
                            <option value="<%= roomType.getId()%>"><%= roomType.getType()%></option>
                            <%}%>
                        </select>
                        <button type="submit">izmeni sobu</button>
                    </form>
                    <form action="DeleteRoom" method="post">
                        <input type="hidden" name="id" value="<%= room.getId()%>">
                        <button type="submit">Obrisi sobu</button>
                    </form>
                    <div class="border-bottom"></div>
                    <% }%>
                </div>
                <%} else {%>
                <div class="search-holder">
                    <h2>Pretrazi</h2>
                    <form action="Search" method="post">
                        <input type="hidden" name="hotel_id" value="<%=hotelId%>">
                        <label>Sprat sobe:</label>
                        <input type="number" name="floor" value="0">
                        <label>Broj kupatila:</label>
                        <input type="number" name="toilet" value="0">
                        <label>Broj balkona:</label>
                        <input type="number" name="balcon" value="0">
                        <button type="submit">Pretrazi</button>
                    </form>
                </div>
                <div class="user-holder">
                    <h2>Sobe</h2>
                    <% for (Room room : roomList) {
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
                    <% }
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
