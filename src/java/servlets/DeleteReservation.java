package servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ilija
 */
public class DeleteReservation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteReservation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteReservation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the reservation ID from the request parameter
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));

        // Delete the reservation from the database
        boolean deletionSuccessful = deleteReservationFromDatabase(reservationId);

        if (deletionSuccessful) {
            // Redirect to the page where reservations are displayed
            response.sendRedirect("rezervacije.jsp");
        } else {
            // Redirect to an error page or show an error message
            response.sendRedirect("error.jsp");
        }
    }

    private boolean deleteReservationFromDatabase(int reservationId) {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        PreparedStatement statement = null;

        try {
            // Prepare the SQL statement
            String sql = "DELETE FROM reservations WHERE id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, reservationId);

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();

            // Check if the reservation was successfully deleted (at least one row affected)
            if (rowsAffected > 0) {
                return true; // Reservation deletion was successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false; // Reservation deletion failed
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
