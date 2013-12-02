/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author anh
 */
public class Teacher extends People{

    private int startYear;
    private ArrayList<String> skillArray;
    private ArrayList<Double> feeSkill;
   
    
    public Teacher(String id,String fName, String lName, String phoneNo, String address,
            String email,boolean gender,String photo,ArrayList<String> skill,int startyear, ArrayList<Double> feeskill){
        super(id,fName,lName,phoneNo,address,email,gender,photo);
                this.skillArray = skill;
                this.startYear = startyear;
                this.feeSkill = feeskill;
    }
    
//    "Teacher,"+super.exportSVCpeople() + startYear  + "," +
//                skillArray.size() + "," +tmpSkillarray +"," + feeSkill.size() + "," + tmpSkillfeeString;
    
    
//    return  id + "," + firstName + "," + lastName + ","
//                + phoneNumber + "," + address + "," + email + "," + gender + "," + active + "," + photo ;
    
     public Teacher(String[] tmp){
        super(tmp[1],tmp[2],tmp[3],tmp[4],tmp[5],tmp[6],Boolean.parseBoolean(tmp[7]),tmp[9]);
                this.setActive(Boolean.parseBoolean(tmp[8]));
                this.startYear = Integer.parseInt(tmp[10]);
                int sizeSkill = Integer.parseInt(tmp[11]);
                String[] tmpskill = tmp[12].split("-");
                ArrayList<String> arraySkill = new ArrayList<String>();
                for(int i = 0; i < sizeSkill ; i++) {
                    arraySkill.add(tmpskill[i]);
                }
                
                int sizeFee = Integer.parseInt(tmp[13]);
                String[] tmpFee = tmp[14].split("-");
                ArrayList<Double> arrayFee = new ArrayList<Double>();
                for(int i = 0; i < sizeFee ; i++) {
                    arrayFee.add(Double.parseDouble(tmpFee[i]));
                }
                
                this.skillArray = arraySkill; 
                this.feeSkill = arrayFee;
    }
     
     

    public ArrayList<Double> getFeeSkill() {
        return feeSkill;
    }

    public void setFeeSkill(ArrayList<Double> feeSkill) {
        this.feeSkill = feeSkill;
    }
    
    

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

  
    public ArrayList<String> getSkillArray() {
        return skillArray;
    }

    public void setSkillArray(ArrayList<String> skillArray) {
        this.skillArray = skillArray;
    }

    
    public String exportSCV() {
        String tmpSkillarray= "";
        for (String str :skillArray) {
            if (tmpSkillarray.equals("")) {
                tmpSkillarray = str;
            } else {
                tmpSkillarray = tmpSkillarray + "-"+str;
            }
        }
        
        String tmpSkillfeeString = "";
        for (Double str :feeSkill) {
            if (tmpSkillfeeString.equals("")) {
                tmpSkillfeeString = str+"";
            } else {
                tmpSkillfeeString = tmpSkillfeeString + "-"+str;
            }
        }
        return "Teacher,"+super.exportSVCpeople() + ","+startYear + "," +
                skillArray.size() + "," +tmpSkillarray +"," + feeSkill.size() + "," + tmpSkillfeeString;
    }

    
    
}
