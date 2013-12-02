/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author anh
 */


public class PaySlipDetail{

    private String classId;
    private int noLesson;
    private double teachingHour;
    private double hourlyPayRate;
    private double fee;
    
        
    
    public PaySlipDetail() {
        
    }

    public PaySlipDetail(String classId, int noLesson, double teachingHour, double hourlyPayRate, double fee) {
        this.classId = classId;
        this.noLesson = noLesson;
        this.teachingHour = teachingHour;
        this.hourlyPayRate = hourlyPayRate;
        this.fee = fee;
    }
    
    

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getNoLesson() {
        return noLesson;
    }

    public void setNoLesson(int noLesson) {
        this.noLesson = noLesson;
    }

    public double getTeachingHour() {
        return teachingHour;
    }

    public void setTeachingHour(double teachingHour) {
        this.teachingHour = teachingHour;
    }

    public double getHourlyPayRate() {
        return hourlyPayRate;
    }

    public void setHourlyPayRate(double hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
    

}
