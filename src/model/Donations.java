/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.util.Calendar.DATE;
import java.sql.Timestamp;

/**
 *
 * @author RC
 */
public class Donations {
    private int donation_id;
    private int donor_id;
    private int site_id;
    private String blood_group;
    private int quantity_ml;
    private Timestamp donation_date;
    
    public Donations(int donation_id, int donor_id, int site_id, String blood_group, int quantity_ml, Timestamp donation_date ){
        this.donation_id = donation_id;
        this.donor_id = donor_id;
        this.site_id = site_id;
        this.blood_group = blood_group;
        this.quantity_ml = quantity_ml;
        this.donation_date = donation_date;
    }
    
    public Donations(){
        
    }

    public int getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(int donation_id) {
        this.donation_id = donation_id;
    }

    public int getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(int donor_id) {
        this.donor_id = donor_id;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public int getQuantity_ml() {
        return quantity_ml;
    }

    public void setQuantity_ml(int quantity_ml) {
        this.quantity_ml = quantity_ml;
    }

    public Timestamp getDonation_date() {
        return donation_date;
    }

    public void setDonation_date(Timestamp donation_date) {
        this.donation_date = donation_date;
    }
    
    
    
}
