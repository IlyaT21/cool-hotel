<%@page import="beans.Hotel"%>
<%@page import="database.DBConnection"%>
<header>
    <a href="index.jsp">
        <img class="logo" src="${pageContext.request.contextPath}/Resources/images/cool-hotel-logo.png" alt="logo">
    </a>
    <nav>
        <% if ("3".equals(session.getAttribute("role"))) { %>
        <a href="admin.jsp">
            Dashboard
        </a>
        <% } %>
        <% if ("2".equals(session.getAttribute("role"))) { %>
        <%
            DBConnection dbConnectionHead = new DBConnection();
            int hotelIdDb = (Integer) session.getAttribute("user_id");
            Hotel hotelDb = dbConnectionHead.getHotelByUserId(hotelIdDb);
            dbConnectionHead.closeConnection();
        %>
        <a href="hotel.jsp?hotelId=<%= hotelDb.getId()%>">Izmeni hotel</a>
        <% }%>
        <%if (session.getAttribute("username") != null) {
                // User is logged in, display welcome message
                String username = (String) session.getAttribute("username");
        %>
        <a>Dobrodosli, <%= username%></a>
        <% }%>
        <% if ("1".equals(session.getAttribute("role"))) { %>
        <a href="ponuda.jsp">Ponuda</a>
        <a href="rezervacije.jsp">Moje Rezervacije</a>
        <% }%>
        <%if (session.getAttribute("username") != null) {%>
        <a href="Logout">Izlogujte se</a>
        <% } else {%>
        <a href="login.jsp">
            Uloguje se
        </a>
        <% }%>
        <%if (session.getAttribute("username") == null) {%>
        <a href="register.jsp">
            Registruj se
        </a>
        <% }%>
    </nav>
</header>
