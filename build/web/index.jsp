<%-- 
    Document   : index
    Created on : Mar 18, 2023, 6:40:22 PM
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
        <main>
            <%@ include file="partials/header.jsp" %>
            <section class="hero-section">
                <div class="content-holder">
                    <div class="text">
                        <h1>
                            Cool Hotel
                        </h1>
                        <p>
                            Najbolja mesta za odmor!
                        </p>
                    </div>
                </div>
            </section>
            <section class="featured-section">
                <div class="content-holder">
                    <h2>Posetite neke od naših najpopularnijih hotela</h2>
                    <div class="hotels-holder">
                        <div class="hotel-card">
                            <img class="hotel-img" src="${pageContext.request.contextPath}/Resources/images/hotel-test.jpg" alt="Hotel">
                            <div class="hotel-info">
                                <h4>Hotel Termag</h4>
                                <a>Detaljnije</a>
                            </div>
                        </div>
                         <div class="hotel-card">
                            <img class="hotel-img" src="${pageContext.request.contextPath}/Resources/images/hotel-test.jpg" alt="Hotel">
                            <div class="hotel-info">
                                <h4>Hotel Termag</h4>
                                <a>Detaljnije</a>
                            </div>
                        </div>
                        <div class="hotel-card">
                            <img class="hotel-img" src="${pageContext.request.contextPath}/Resources/images/hotel-test.jpg" alt="Hotel">
                            <div class="hotel-info">
                                <h4>Hotel Termag</h4>
                                <a>Detaljnije</a>
                            </div>
                        </div>
                        <div class="hotel-card">
                            <img class="hotel-img" src="${pageContext.request.contextPath}/Resources/images/hotel-test.jpg" alt="Hotel">
                            <div class="hotel-info">
                                <h4>Hotel Termag</h4>
                                <a>Detaljnije</a>
                            </div>
                        </div>
                        <div class="hotel-card">
                            <img class="hotel-img" src="${pageContext.request.contextPath}/Resources/images/hotel-test.jpg" alt="Hotel">
                            <div class="hotel-info">
                                <h4>Hotel Termag</h4>
                                <a>Detaljnije</a>
                            </div>
                        </div>
                        <div class="hotel-card">
                            <img class="hotel-img" src="${pageContext.request.contextPath}/Resources/images/hotel-test.jpg" alt="Hotel">
                            <div class="hotel-info">
                                <h4>Hotel Termag</h4>
                                <a>Detaljnije</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <%@ include file="partials/footer.jsp" %>
        </main>
    </body>
</html>
