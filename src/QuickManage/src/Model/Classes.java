/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author anh
 */
public class Classes implements Serializable, Ultility {

    private String id;
    private String name;//room, time, teacher, textbook, fees startday, endday,method(1-1||1-*)
    private String textbook;
    private double fee;
    private Date startDate;
    private Date endDate;
    private int hour;
    //4 for room 1-1; 3 room 1-*; 1-dance room/ no class same room same time
    //piano,violin,guitar (max 1 student)
    //organ,ballet,hiphop, painting,singing (max 20 student)
    private int classTypeid;
//    private Model model = Model.getModel();

    public Classes(String id, String name, String textbook, Date startDate, Date endDate, int classType, int hour) {
        this.id = id; // C000123
        this.name = name;
//        this.skill = skill;
        this.textbook = textbook;
        this.startDate = startDate;
        this.endDate = endDate;
//        convertMethod();
        this.classTypeid = classType;
        this.hour = hour;
    }

   

    public int getClassTypeid() {
        return classTypeid;
    }

    public void setClassTypeid(int classTypeid) {
        this.classTypeid = classTypeid;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    
    public double getFee() {
        return fee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
