/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

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
public class EditRoom extends HttpServlet {

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
            out.println("<title>Servlet EditRoom</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditRoom at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        // Retrieve the form data
        int id = Integer.parseInt(request.getParameter("id"));
        int floor = Integer.parseInt(request.getParameter("floor"));
        int toilet = Integer.parseInt(request.getParameter("toilet"));
        int balcon = Integer.parseInt(request.getParameter("balcon"));
        int price = Integer.parseInt(request.getParameter("price"));
        int hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
        int room_type_id = Integer.parseInt(request.getParameter("room_type_id"));

        // Perform the logic to update the user's information in the database
        boolean updateSuccessful = updateRoomInDatabase(id, floor, toilet, balcon, price, hotel_id, room_type_id);

        if (updateSuccessful) {
            // Redirect the user to a success page or display a success message
            String message = "Uspesno izmenjena soba";
            response.getWriter().println("<script>alert('" + message + "'); window.history.back();</script>");
        } else {
            // Redirect the user to an error page or display an error message
            String errorMessage = "An error occurred while deleting the user.";
            response.getWriter().println("<script>alert('" + errorMessage + "'); window.history.back();</script>");
        }
    }

    private boolean updateRoomInDatabase(int id, int floor, int toilet, int balcon, int price, int hotel_id, int room_type_id) {
        // Initialize the database connection
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        try {
            // Prepare the SQL statement
            String sql = "UPDATE room SET floor=?, toilet=?, balcon=?, price=?, hotel_id=?, room_type_id=? WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, floor);
            statement.setInt(2, toilet);
            statement.setInt(3, balcon);
            statement.setInt(4, price);
            statement.setInt(5, hotel_id);
            statement.setInt(6, room_type_id);
            statement.setInt(7, id);

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();

            // Close the database resources
            statement.close();
            dbConnection.closeConnection();

            // Check if the update was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
