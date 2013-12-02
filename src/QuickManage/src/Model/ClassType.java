/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Lyn
 */
public class ClassType implements Serializable {

    private int id;
    private String name; // piano , guildtar , balet
    private int type; // Individual == 0 ,, dual == 1. ,, group  == 2
    private int lessonWeek;
    private float feeLesson45;
    private float feeLesson60;
//    private int timeOfLesson ; // 45 == 0 ; 60 == 1 ; both = 2;
    private String info;

    public ClassType(int id, String name, int type, int lessonWeek, float feeLesson45,
            float feeLession60, String info) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.lessonWeek = lessonWeek;
        this.feeLesson45 = feeLesson45;
        this.feeLesson60 = feeLession60;
        this.info = info;

    }
//      code ==   
//    "ClassType,"+ id + "," + name + "," + type + "," + lessonWeek + "," +
//                feeLesson45 + "," + feeLesson60 + "," + info;

    public ClassType(String code) {
        String[] tmp = code.split(",");


        this.id = Integer.parseInt(tmp[1]);
        this.name = tmp[2];
        this.type = Integer.parseInt(tmp[3]);
        this.lessonWeek = Integer.parseInt(tmp[4]);
        this.feeLesson45 = Float.parseFloat(tmp[5]);
        this.feeLesson60 = Float.parseFloat(tmp[6]);
        this.info = tmp[7];

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getLessonWeek() {
        return lessonWeek;
    }

    public void setLessonWeek(int lessonWeek) {
        this.lessonWeek = lessonWeek;
    }

    public float getFeeLesson45() {
        return feeLesson45;
    }

    public void setFeeLesson45(float feeLesson45) {
        this.feeLesson45 = feeLesson45;
    }

    public float getFeeLesson60() {
        return feeLesson60;
    }

    public void setFeeLesson60(float feeLesson60) {
        this.feeLesson60 = feeLesson60;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        String text = "";
        if (type == 0) {
            text = "Individual";
        } else if (type == 1) {
            text = "Dual";
        } else {
            text = "Group";
        }
        return name + " " + text;
    }

    public String exportSCV() {
        return "ClassType," + id + "," + name + "," + type + "," + lessonWeek + ","
                + feeLesson45 + "," + feeLesson60 + "," + info;
    }
}
