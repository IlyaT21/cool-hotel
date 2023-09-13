/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Ilija
 */
import beans.Hotel;
import beans.Reservation;
import beans.Room;
import beans.RoomType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import beans.User;

public class DBConnection {

    private Connection conn = null;
    private String url = "jdbc:mysql://localhost:3306/cool_hotel";
    private String user = "root";
    private String password = "";

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace(System.out);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the JDBC driver");
            e.printStackTrace(System.out);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("Failed to close the connection");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM user";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String points = resultSet.getString("points");
                // Retrieve other user details if needed

                User user = new User(id, username, password, role, points);
                userList.add(user);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hotelList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM hotel";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String naziv = resultSet.getString("naziv");
                int user_id = resultSet.getInt("user_id");
                // Retrieve other user details if needed

                Hotel hotel = new Hotel(id, naziv, user_id);
                hotelList.add(hotel);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotelList;
    }

    public Hotel getHotelById(int hotelId) {
        Hotel hotel = null;

        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM hotel WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, hotelId);

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the hotel if a row is returned
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String naziv = resultSet.getString("naziv");
                int user_id = resultSet.getInt("user_id");
                // Retrieve other hotel details if needed

                hotel = new Hotel(id, naziv, user_id);
            }

            // Close the database resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotel;
    }

    public Hotel getHotelByUserId(int hotelId) {
        Hotel hotel = null;

        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM hotel WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, hotelId);

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the hotel if a row is returned
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String naziv = resultSet.getString("naziv");
                int user_id = resultSet.getInt("user_id");
                // Retrieve other hotel details if needed

                hotel = new Hotel(id, naziv, user_id);
            }

            // Close the database resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotel;
    }

    public List<Room> getAllRooms(int hotelId) {
        List<Room> roomList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM room WHERE hotel_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int floor = resultSet.getInt("floor");
                int toilet = resultSet.getInt("toilet");
                int balcon = resultSet.getInt("balcon");
                int price = resultSet.getInt("price");
                int roomTypeId = resultSet.getInt("room_type_id");
                // Retrieve other room details if needed

                Room room = new Room(id, floor, toilet, balcon, price, hotelId, roomTypeId);
                roomList.add(room);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomList;
    }

    public boolean isRoomReserved(int roomId) {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Prepare the SQL statement
            String sql = "SELECT COUNT(*) FROM reservations WHERE room_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, roomId);

            // Execute the SQL statement and retrieve the result
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // If count is greater than 0, the room is reserved
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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

        return false; // An error occurred or room is not reserved
    }

    public Room getRoomById(int roomId) {
        Room room = null;

        try {
            String sql = "SELECT * FROM room WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int floor = resultSet.getInt("floor");
                int toilet = resultSet.getInt("toilet");
                int balcon = resultSet.getInt("balcon");
                int price = resultSet.getInt("price");
                int hotel_id = resultSet.getInt("hotel_id");
                int room_type_id = resultSet.getInt("room_type_id");

                room = new Room(id, floor, toilet, balcon, price, hotel_id, room_type_id);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return room;
    }

    public List<RoomType> getAllRoomTypes() {
        List<RoomType> roomTypeList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM room_type";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                // Retrieve other user details if needed

                RoomType roomType = new RoomType(id, type);
                roomTypeList.add(roomType);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomTypeList;
    }

    public String getRoomTypeTypeById(int roomTypeId) {
        String type = null;
        try {
            String sql = "SELECT type FROM room_type WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, roomTypeId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                type = resultSet.getString("type");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    public String getUserUsername(int userId) {
        String username = null;
        try {
            // Prepare the SQL statement
            String sql = "SELECT username FROM user WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the username if a row is returned
            if (resultSet.next()) {
                username = resultSet.getString("username");
            }

            // Close the database resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    public List<Reservation> getUserReservations(int userId) {
        List<Reservation> reservations = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reservations WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double total = resultSet.getDouble("total");
                String status = resultSet.getString("status");
                int room_id = resultSet.getInt("room_id");

                Reservation reservation = new Reservation(id, total, status, userId, room_id);
                reservations.add(reservation);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
}
