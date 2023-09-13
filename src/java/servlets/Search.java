/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.Room;
import database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ilija
 */
public class Search extends HttpServlet {

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
            out.println("<title>Servlet Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Search at " + request.getContextPath() + "</h1>");
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
        // Retrieve search parameters from the form
        int hotelId = Integer.parseInt(request.getParameter("hotel_id"));
        int floor = Integer.parseInt(request.getParameter("floor"));
        int toilet = Integer.parseInt(request.getParameter("toilet"));
        int balcon = Integer.parseInt(request.getParameter("balcon"));

        // Perform the search based on the parameters
        List<Room> searchResults = performSearch(hotelId, floor, toilet, balcon);

        // Store the search results in a request attribute
        request.setAttribute("searchResults", searchResults);
        
        request.setAttribute("hotel_id", hotelId);

        // Forward the request to a JSP to display the search results
        RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
        dispatcher.forward(request, response);
    }

    private List<Room> performSearch(int hotelId, int floor, int toilet, int balcon) {
        List<Room> searchResults = new ArrayList<>();

        try {
            DBConnection dbConnection = new DBConnection();
            Connection conn = dbConnection.getConnection();

            // Prepare the SQL statement
            String sql = "SELECT * FROM room WHERE hotel_id = ?";

            // Add additional conditions for optional search parameters
            if (floor > 0) {
                sql += " AND floor = ?";
            }
            if (toilet > 0) {
                sql += " AND toilet = ?";
            }
            if (balcon > 0) {
                sql += " AND balcon = ?";
            }

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, hotelId);

            // Set parameters for optional search conditions
            int parameterIndex = 2; // Start from the second parameter index
            if (floor > 0) {
                statement.setInt(parameterIndex++, floor);
            }
            if (toilet > 0) {
                statement.setInt(parameterIndex++, toilet);
            }
            if (balcon > 0) {
                statement.setInt(parameterIndex++, balcon);
            }

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();

            // Retrieve and populate search results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                floor = resultSet.getInt("floor");
                toilet = resultSet.getInt("toilet");
                balcon = resultSet.getInt("balcon");
                int price = resultSet.getInt("price");
                int roomHotelId = resultSet.getInt("hotel_id");
                int roomTypeId = resultSet.getInt("room_type_id");

                // Create a Room object and add it to the searchResults list
                Room room = new Room(id, floor, toilet, balcon, price, roomHotelId, roomTypeId);
                searchResults.add(room);
            }

            // Close the database resources
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
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
