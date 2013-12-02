/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author anh
 */
public class Room  implements Serializable{

    public Room(String id, String name, int type) {
        this.name = name;
        this.type = type; //1= 1 - 1 or 2= 1 - many
        this.id = id;
    }
    
     public Room(String id, String name, String roomType) {
        this.name = name;
//        this.type = type; //1= 1 - 1 or 2= 1 - many
        this.id = id;
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
     
     
    private String roomType;
    private String name;
    private int type;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " " + id;
    }
}
