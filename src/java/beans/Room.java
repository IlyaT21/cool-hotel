/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author Ilija
 */
public class Room {
    private int id;
    protected int floor;
    protected int toilet;
    protected int balcon;
    protected int price;
    protected int hotel_id;
    protected int room_type_id;

    public Room() {
        
    }

    public Room(int id, int floor, int toilet, int balcon, int price, int hotel_id, int room_type_id) {
        this.id = id;
        this.floor = floor;
        this.toilet = toilet;
        this.balcon = balcon;
        this.price = price;
        this.hotel_id = hotel_id;
        this.room_type_id = room_type_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getToilet() {
        return toilet;
    }

    public void setToilet(int toilet) {
        this.toilet = toilet;
    }

    public int getBalcon() {
        return balcon;
    }

    public void setBalcon(int balcon) {
        this.balcon = balcon;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHotelId() {
        return hotel_id;
    }

    public void setHotelId(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getRoomTypeId() {
        return room_type_id;
    }

    public void setRoomTypeId(int room_type_id) {
        this.room_type_id = room_type_id;
    }
}
