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
public class AddRoom extends HttpServlet {

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
            out.println("<title>Servlet AddRoom</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRoom at " + request.getContextPath() + "</h1>");
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
        String floor = request.getParameter("floor");
        String toilet = request.getParameter("toilet");
        String balcon = request.getParameter("balcon");
        String price = request.getParameter("price");
        String room_type_id = request.getParameter("room_type_id");
        String hotel_id = request.getParameter("hotel_id");

        // Perform the logic to add the user to the database
        boolean addSuccessful = addRoomToDatabase(floor, toilet, balcon, price, room_type_id, hotel_id);

        if (addSuccessful) {
            // Redirect the user to a success page or display a success message
            String message = "Uspesno dodata soba";
            response.getWriter().println("<script>alert('" + message + "'); window.history.back();</script>");
        } else {
            // Redirect the user to an error page or display an error message
            String errorMessage = "An error occurred while adding the user.";
            response.getWriter().println("<script>alert('" + errorMessage + "'); window.history.back();</script>");
        };
    }

    private boolean addRoomToDatabase(String floor, String toilet, String balcon, String price, String room_type_id, String hotel_id) {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        PreparedStatement statement = null;

        try {
            // Prepare the SQL statement
            String sql = "INSERT INTO room (floor, toilet, balcon, price, hotel_id, room_type_id) VALUES (?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, floor);
            statement.setString(2, toilet);
            statement.setString(3, balcon);
            statement.setString(4, price);
            statement.setString(5, hotel_id);
            statement.setString(6, room_type_id);

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();

            // Check if the user was successfully added (at least one row affected)
            if (rowsAffected > 0) {
                return true; // User addition was successful
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

        return false; // User addition failed
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
