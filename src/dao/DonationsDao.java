/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import model.Donations;

/**
 *
 * @author RC
 */
public class DonationsDao {
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/blood_donation_management_system_db";
    private String username = "postgres";
    private String password = "racia123"; 
    
    public int registerDonations(Donations donationsobj){
        // CHANGE: Added validation at DAO level as a safety check.
        // PURPOSE: Double-check quantity range even if UI validation is bypassed, preventing database errors.
        if (donationsobj == null) {
            JOptionPane.showMessageDialog(null, "Donation object is null.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        
        int quantity = donationsobj.getQuantity_ml();
        // CHANGE: Validate quantity range (250-500 ml) before database operation.
        // PURPOSE: Prevent constraint violations and provide immediate feedback.
        if (quantity < 250 || quantity > 500) {
            JOptionPane.showMessageDialog(null, 
                "Invalid donation quantity: " + quantity + " ml.\nQuantity must be between 250 and 500 ml.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        
        // CHANGE: Implemented try-with-resources for automatic resource management.
        // PURPOSE: Ensure Connection and PreparedStatement are properly closed, preventing resource leaks.
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO donations (donor_id, site_id, blood_group, quantity_ml) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, donationsobj.getDonor_id());
                pst.setInt(2, donationsobj.getSite_id());
                pst.setString(3, donationsobj.getBlood_group());
                pst.setInt(4, donationsobj.getQuantity_ml());
                
                int rowsAffected = pst.executeUpdate();
                return rowsAffected;
            }
        } catch (SQLException ex) {
            // CHANGE: Improved error handling to detect constraint violations and show user-friendly messages.
            // PURPOSE: Provide clear feedback when database constraints are violated (e.g., quantity out of range).
            if (ex.getMessage() != null && ex.getMessage().contains("donations_quantity_ml_check")) {
                JOptionPane.showMessageDialog(null, 
                    "Invalid donation quantity. Quantity must be between 250 and 500 ml.\n\nYou entered: " + quantity + " ml", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Database error: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        return 0;
    }
    
    public int DonationsUpdate(Donations donationsObj){
        // CHANGE: Implemented try-with-resources for automatic resource management.
        // PURPOSE: Ensure all database resources (Connection, PreparedStatement, ResultSet) are properly closed.
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            //validate Donation_id
            String checkSql = "SELECT COUNT (*) From donations WHERE donation_id = ?";
            try (PreparedStatement checkpst = con.prepareStatement(checkSql)) {
                checkpst.setInt(1, donationsObj.getDonation_id());
                try (ResultSet rs = checkpst.executeQuery()) {
                    rs.next();
                    if (rs.getInt(1) == 0){
                        JOptionPane.showMessageDialog(null, "No donations found with ID: " + donationsObj.getDonation_id());
                        return 0;
                    }
                }
            }
            
            //validate Donor_id
            String checkDonorsql = "select count (*) from donors where donor_id = ?";
            try (PreparedStatement checkDonorpst = con.prepareStatement(checkDonorsql)) {
                checkDonorpst.setInt(1, donationsObj.getDonor_id());
                try (ResultSet donorrs = checkDonorpst.executeQuery()) {
                    donorrs.next();
                    if (donorrs.getInt(1) == 0){
                        JOptionPane.showMessageDialog(null, "Invalid Donor ID: " + donationsObj.getDonor_id());
                        return 0;
                    }
                }
            }
            
            //validate site_id
            String checkSiteSql = "SELECT COUNT(*) FROM sites WHERE site_id = ?";
            try (PreparedStatement checkSitePst = con.prepareStatement(checkSiteSql)) {
                checkSitePst.setInt(1, donationsObj.getSite_id());
                try (ResultSet siteRs = checkSitePst.executeQuery()) {
                    siteRs.next();
                    if (siteRs.getInt(1) == 0) {
                        JOptionPane.showMessageDialog(null, "Invalid Site ID: " + donationsObj.getSite_id());
                        return 0;
                    }
                }
            }
            
            //perform update
            String sql = "update donations set donor_id= ?, site_id= ?, quantity_ml= ?, blood_group= ? where donation_id= ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, donationsObj.getDonor_id());
                pst.setInt(2, donationsObj.getSite_id());
                pst.setInt(3, donationsObj.getQuantity_ml());
                pst.setString(4, donationsObj.getBlood_group());
                pst.setInt(5, donationsObj.getDonation_id());
                int rowsAffected = pst.executeUpdate();
                return rowsAffected;
            }
        } catch (SQLException ex) {
            // CHANGE: Improved error handling to detect constraint violations and show user-friendly messages.
            // PURPOSE: Provide clear feedback when database constraints are violated (e.g., quantity out of range).
            if (ex.getMessage() != null && ex.getMessage().contains("donations_quantity_ml_check")) {
                JOptionPane.showMessageDialog(null, 
                    "Invalid donation quantity. Quantity must be between 250 and 500 ml.", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            }
            return 0;
        }
    }
    
    public int deleteDonations(Donations donationsObj){
        // CHANGE: Implemented try-with-resources and parameterized query instead of string concatenation.
        // PURPOSE: Ensure proper resource management and prevent SQL injection vulnerabilities.
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "delete from donations where donation_id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, donationsObj.getDonation_id());
                int rowsDeleted = pst.executeUpdate();
                return rowsDeleted;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public Donations findDonationsbyID(int donationId){
        // CHANGE: Return null when no donation is found instead of returning an empty Donations object.
        // PURPOSE: Correct "find by ID" behavior and ensure JUnit tests expecting null for invalid IDs pass.
        Donations donations = null;
        // CHANGE: Implemented try-with-resources for automatic resource management.
        // PURPOSE: Ensure Connection, PreparedStatement, and ResultSet are properly closed.
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
           String sql = "select * from donations where donation_id = ?";
           try (PreparedStatement pst = con.prepareStatement(sql)) {
               pst.setInt(1, donationId);
               try (ResultSet rs = pst.executeQuery()) {
                   // CHANGE: Only create the model if the result set has a row.
                   // PURPOSE: Avoid returning a non-null object when no DB record exists.
                   if(rs.next()){
                       donations = new Donations();
                       donations.setDonation_id(rs.getInt("donation_id"));
                       donations.setDonor_id(rs.getInt("donor_id"));
                       donations.setSite_id(rs.getInt("site_id"));
                       donations.setBlood_group(rs.getString("blood_group"));
                       donations.setQuantity_ml(rs.getInt("quantity_ml"));
                       donations.setDonation_date(rs.getTimestamp("donation_date"));
                   }
               }
           }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return donations;
    }
    
    public List<Donations> findAllDonations(){
        // CHANGE: Implemented try-with-resources for automatic resource management.
        // PURPOSE: Ensure Connection, PreparedStatement, and ResultSet are properly closed.
         try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
             String sql="Select * from donations";
             try (PreparedStatement pst=con.prepareStatement(sql)) {
                 try (ResultSet rs=pst.executeQuery()) {
                     List<Donations> donationslist=new ArrayList<>();
                     while(rs.next()){
                        Donations donationsobj=new Donations();
                        donationsobj.setDonation_id(rs.getInt("donation_id"));
                        donationsobj.setDonor_id(rs.getInt("donor_id"));
                        donationsobj.setSite_id(rs.getInt("site_id"));
                        donationsobj.setBlood_group(rs.getString("blood_group"));
                        donationsobj.setQuantity_ml(rs.getInt("quantity_ml"));
                        donationsobj.setDonation_date(rs.getTimestamp("donation_date"));
                        donationslist.add(donationsobj);
                     }
                     return donationslist;
                 }
             }
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
        return null;
         
     }
    
    
}

