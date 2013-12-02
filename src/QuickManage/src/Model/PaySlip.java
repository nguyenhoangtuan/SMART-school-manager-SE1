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
public class PaySlip implements Serializable{
    
    private String id;
    private ArrayList<PaySlipDetail> pList = new ArrayList<>();
    private double totalSalary =0;

    public PaySlip(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public ArrayList<PaySlipDetail> getpList() {
        return pList;
    }

    public void setpList(ArrayList<PaySlipDetail> pList) {
        this.pList = pList;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
    
    public void init(){
        generateFee();
    }
    
    private void generateFee(){
        totalSalary = 0;
        for(PaySlipDetail ps:pList){
            totalSalary += ps.getFee();
        }
    }
    
}
