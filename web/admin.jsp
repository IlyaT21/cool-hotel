<%-- 
    Document   : admin
    Created on : Jun 17, 2023, 5:50:13 PM
    Author     : Ilija
--%>

<%@page import="beans.RoomType"%>
<%@page import="beans.Hotel"%>
<%@page import="java.util.List"%>
<%@page import="beans.User"%>
<%@page import="database.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resources/css/main.css">
        <title>Dashboard</title>
    </head>
    <body>
        <main class="dashboard">
            <%@ include file="partials/header.jsp" %>
            <div class="add-data-hodler">
                <form action="AddUser" method="post">
                    <h3>Dodaj korisnika</h3>
                    <label>Korisnicko ime:</label>
                    <input type="text" name="username" value="" required>
                    <label>Sifra:</label>
                    <input type="text" name="password" value="" required>
                    <label>Uloga:</label>
                    <select name="role">
                        <option value="1">korisnik</option>
                        <option value="2">menadzer</option>
                    </select>
                    <button type="submit">Dodaj korisnika</button>
                </form>
                <form action="AddHotel" method="post">
                    <h3>Dodaj hotel</h3>
                    <label>Ime hotela:</label>
                    <input type="hidden" name="user_id" value="<%= session.getAttribute("user_id")%>">
                    <input type="text" name="naziv" value="" required>
                    <button type="submit">Dodaj hotel</button>
                </form>
                <form action="AddRoomType" method="post">
                    <h3>Dodaj vrstu sobe</h3>
                    <input type="text" name="type" value="" required>
                    <button type="submit">Dodaj vrstu</button>
                </form>
            </div>
            <div class="content-holder">
                <%
                    DBConnection dbConnection = new DBConnection();
                    List<User> userList = dbConnection.getAllUsers();
                    List<Hotel> hotelList = dbConnection.getAllHotels();
                    List<RoomType> roomTypeList = dbConnection.getAllRoomTypes();
                %>
                <div class="user-holder">
                    <h2>Korisnici</h2>
                    <% for (User user : userList) {%>
                    <form action="EditUser" method="post">
                        <p>Id: <%= user.getId()%></p>
                        <label>Korisnicko ime:</label>
                        <input type="hidden" name="id" value="<%= user.getId()%>">
                        <input type="text" name="username" value="<%= user.getUsername()%>">
                        <label>Sifra:</label>
                        <input type="text" name="password" value="<%= user.getPassword()%>">
                        <label>Poeni:</label>
                        <input type="number" name="points" value="<%= user.getPoints()%>">
                        <label>Uloga:</label>
                        <%
                            String role = user.getRole();

                            if (role.equals("1")) {
                        %>
                        <p>Korisnik</p>
                        <%
                        } else if (role.equals("2")) {
                        %>
                        <p>Menadzer</p>
                        <%
                        } else if (role.equals("3")) {
                        %>
                        <p>Admin</p>
                        <%
                            }
                        %>
                        <button type="submit">izmeni korisnika</button>
                    </form>
                    <%
                        if (!role.equals("3")) {
                    %>
                    <form action="DeleteUser" method="post">
                        <input type="hidden" name="id" value="<%= user.getId()%>">
                        <button type="submit">Obrisi korisnika</button>
                    </form>
                    <%
                        }
                    %>
                    <div class="border-bottom"></div>
                    <% }%>
                </div>
                <div class="user-holder">
                    <h2>Hoteli</h2>
                    <% for (Hotel hotel : hotelList) {%>
                    <form action="EditHotel" method="post">
                        <p>Id: <%= hotel.getId()%></p>
                        <label>Naziv:</label>
                        <input type="hidden" name="id" value="<%= hotel.getId()%>">
                        <input type="text" name="naziv" value="<%= hotel.getNaziv()%>">
                        <label>Menadzer: <%= dbConnection.getUserUsername(hotel.getUser_id())%></label>
                        <select name="user_id">
                            <option name="user_id" disabled selected="selected" value="<%= hotel.getUser_id()%>"><%= dbConnection.getUserUsername(hotel.getUser_id())%></option>
                            <%
                                for (User user : userList) {
                                    if (user.getRole().equals("2") || user.getRole().equals("3")){%>
                            <option name="user_id" value="<%= user.getId()%>"><%= user.getUsername()%></option>
                            <%     }
                                }%>
                        </select>
                        <button type="submit">izmeni hotel</button>
                    </form>
                    <form action="DeleteHotel" method="post">
                        <input type="hidden" name="id" value="<%= hotel.getId()%>">
                        <button type="submit">Obrisi hotel</button>
                    </form>
                        <a href="hotel.jsp?hotelId=<%= hotel.getId() %>">Pogledaj sobe</a>
                    <div class="border-bottom"></div>
                    <% }%>
                </div>
                <div class="user-holder">
                    <h2>Vrste soba</h2>
                    <% for (RoomType roomType : roomTypeList) {%>
                    <form action="EditRoomType" method="post">
                        <p>Id: <%= roomType.getId()%></p>
                        <label>Tip:</label>
                        <input type="hidden" name="id" value="<%= roomType.getId()%>">
                        <input type="text" name="type" value="<%= roomType.getType()%>">
                        <button type="submit">izmeni tip sobe</button>
                    </form>
                    <form action="DeleteRoomType" method="post">
                        <input type="hidden" name="id" value="<%= roomType.getId()%>">
                        <button type="submit">Obrisi tip sobe</button>
                    </form>
                    <div class="border-bottom"></div>
                    <%}%>
                </div>
                <%
                    dbConnection.closeConnection();
                %>
            </div>
            <%@ include file="partials/footer.jsp" %>
        </main>
    </body>
</html>
