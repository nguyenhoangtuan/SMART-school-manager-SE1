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
public class ClassInvoiceDetail implements Serializable{

    private String classId;
    private String className;
    private Date starDate;
    private Date endDate;
    private double fee;

    public ClassInvoiceDetail(String classId, String className, Date starDate, Date endDate, double fee) {
        this.classId = classId;
        this.className = className;
        this.starDate = starDate;
        this.endDate = endDate;
        this.fee = fee;
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public Date getStarDate() {
        return starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getFee() {
        return fee;
    }
}
