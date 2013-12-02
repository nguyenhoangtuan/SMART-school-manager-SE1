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
public class Account implements Serializable {

    private String id;
    private String password;
    private String userName;
    private String email;
    private String phoneNumber;
    private boolean active;//true = active; false = deactive
    private int role;
    private String pic;
    //photo

//    public Account(String id, String password, String userName, String email, String phoneNumber, boolean active, int role, String pic) {
//        this.id = id;
//        this.password = password;
//        this.userName = userName;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
//        this.active = active;
//        this.role = role;
//        this.pic = pic;
//    }
    public Account(String id, String password, String userName, String email, String phoneNumber, int role, String pic) {
        this.id = id;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.pic = pic;
        active = true;
    }
//  return "Account," + id + "," + password + "," + userName + ","
//                + email + "," + phoneNumber + "," + active + "," + role + "," + pic;
    public Account(String code) {
        String[] tmp = code.split(",");

        this.id = tmp[1];
        this.password = tmp[2];
        this.userName = tmp[3];
        this.email = tmp[4];
        this.phoneNumber = tmp[5];
        this.role = Integer.parseInt(tmp[7]);
        this.pic = tmp[8];
        active = Boolean.parseBoolean(tmp[6]);
    }

    //manager can do anything staff do
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String exportSCV() {
        return "Account," + id + "," + password + "," + userName + ","
                + email + "," + phoneNumber + "," + active + "," + role + "," + pic;
    }
}
