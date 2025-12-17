/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RC
 */
public class Donors {
    
    private int donor_id;
    private String donor_names;
    private String phone;
    private String blood_group;
    private String donor_location;
    
    public Donors(){
        
    }

    public Donors(int donor_id, String donor_names, String phone, String blood_group, String donor_location) {
        this.donor_id = donor_id;
        this.donor_names = donor_names;
        this.phone = phone;
        this.blood_group = blood_group;
        this.donor_location = donor_location;
    }

    public int getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(int donor_id) {
        this.donor_id = donor_id;
    }

    public String getDonor_names() {
        return donor_names;
    }

    public void setDonor_names(String donor_names) {
        this.donor_names = donor_names;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getDonor_location() {
        return donor_location;
    }

    public void setDonor_location(String donor_location) {
        this.donor_location = donor_location;
    }
    
    
}
