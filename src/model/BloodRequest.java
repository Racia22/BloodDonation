/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author RC
 */
public class BloodRequest {
    
   private int request_id;
   private String hospital_name;
   private String blood_group;
   private int quantity_ml;
   private Timestamp request_date;
   private String status;
   
   public BloodRequest(){
       
   }

    public BloodRequest(int request_id, String hospital_name, String blood_group, int quantity_ml, Timestamp request_date, String status) {
        this.request_id = request_id;
        this.hospital_name = hospital_name;
        this.blood_group = blood_group;
        this.quantity_ml = quantity_ml;
        this.request_date = request_date;
        this.status = status;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
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

    public Timestamp getRequest_date() {
        return request_date;
    }

    public void setRequest_date(Timestamp request_date) {
        this.request_date = request_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
   
   
   
}
