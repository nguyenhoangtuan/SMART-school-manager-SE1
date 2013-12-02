/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author anh
 */
public class Student extends People{
    
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private int relationship;


    public Student(String contactName, String contactPhone, String contactEmail, String id, String fName, String lName, String phoneNo, String address, String email, boolean gender, String photo,int relationship) {
        super(id, fName, lName, phoneNo, address, email, gender, photo);
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.relationship = relationship;
       
    }
    
//      return "Student," + super.exportSVCpeople()+"," + contactName + "," + contactPhone + "," 
//              + contactEmail + "," + relationship;
    

    
    public Student(String[] tmp) {
        super(tmp[1],tmp[2],tmp[3],tmp[4],tmp[5],tmp[6],Boolean.parseBoolean(tmp[7]),tmp[9]);
        this.setActive(Boolean.parseBoolean(tmp[8]));
        
        
        this.contactName = tmp[10];
        this.contactPhone = tmp[11];
        this.contactEmail = tmp[12];
        this.relationship = Integer.parseInt(tmp[13]);
       
    }


    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }



    public int getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
       this.relationship = relationship;
    }

    
    public String exportSCV() {
        return "Student," + super.exportSVCpeople()+"," + contactName + "," + contactPhone + "," + contactEmail + "," + relationship;
    }

    
  
    
}
