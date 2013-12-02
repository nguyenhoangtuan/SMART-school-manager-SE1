/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author anh
 */
public class Invoice implements Serializable{

    private String id;
    private Date startDate;
    private Date endDate;
    private ArrayList<ClassInvoiceDetail> classList = new ArrayList<>();
    private double totalFee;
    private boolean paid = false;
    private Date paidDate=null;
    private int paidMethod;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    

    public Invoice(String id) {
        this.id = id;
        paid = false;

        
    }
    
    public void init(){
        generateTotalFee();
        generateStartEndDate();
    }

    public void generateTotalFee() {
        totalFee = 0;
        for (ClassInvoiceDetail cd : classList) {
            totalFee += cd.getFee();
        }

    }

    private void generateStartEndDate() {
        Date d1 = null, d2 =null;
        for (ClassInvoiceDetail cd : classList) {
            if (d1 == null) {
                d1 = cd.getStarDate();
            } else if (d1.compareTo(cd.getStarDate()) < 0) {
                d1 = cd.getStarDate();
            }
            
            if(d2==null){
                d2 = cd.getEndDate();
            }else if(d2.compareTo(cd.getEndDate())>0){
                d2 = cd.getEndDate();
            }
            
        }
        
        this.startDate = d1;
        this.endDate = d2;
        
        
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }
    

    public void addClass(Classes c) {
        classList.add(new ClassInvoiceDetail(c.getId(), c.getName(), c.getStartDate(), c.getEndDate(), c.getFee()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<ClassInvoiceDetail> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<ClassInvoiceDetail> classList) {
        this.classList = classList;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public int getPaidMethod() {
        return paidMethod;
    }

    public void setPaidMethod(int paidMethod) {
        this.paidMethod = paidMethod;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + '}';
    }
    
    
}

