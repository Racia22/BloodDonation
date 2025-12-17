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
public class Sites {
    private int site_id;
    private String site_names;
    private String site_phonenumber;
    private String doctors_name;
    private String site_location;
    
    public Sites(){

    }

    public Sites(int site_id, String site_names, String site_phonenumber, String doctors_name, String site_location) {
        this.site_id = site_id;
        this.site_names = site_names;
        this.site_phonenumber = site_phonenumber;
        this.doctors_name = doctors_name;
        this.site_location = site_location;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public String getSite_names() {
        return site_names;
    }

    public void setSite_names(String site_names) {
        this.site_names = site_names;
    }

    public String getSite_phonenumber() {
        return site_phonenumber;
    }

    public void setSite_phonenumber(String site_phonenumber) {
        this.site_phonenumber = site_phonenumber;
    }

    public String getDoctors_name() {
        return doctors_name;
    }

    public void setDoctors_name(String doctors_name) {
        this.doctors_name = doctors_name;
    }

    public String getSite_location() {
        return site_location;
    }

    public void setSite_location(String site_location) {
        this.site_location = site_location;
    }

    
    
}

