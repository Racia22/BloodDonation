/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Donors;
import java.sql.*;
import java.util.*;
/**
 *
 * @author RC
 */
public class DonorsDao {
    
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/blood_donation_management_system_db";
    private String username = "postgres";
    private String password = "racia123"; 
    
    public int registerDonors(Donors donorsobj){
        try{
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        String sql = "insert into donors(donor_names, phone, blood_group, donor_location) values (?,?,?,?) ";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, donorsobj.getDonor_names());
        pst.setString(2, donorsobj.getPhone());
        pst.setString(3, donorsobj.getBlood_group());
        pst.setString(4, donorsobj.getDonor_location());
        
        int rowsAffected = pst.executeUpdate();
        con.close();
    }catch(Exception ex){
        ex.printStackTrace();
    }
        return 0;
    }
    
    public int DonorsUpdate (Donors donorsobj){
         // Step 1: surround with try and catch
        try {
            //Step 1: Establish the connection
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            //Step 2: Create/Prepare the statement
            String sql="UPDATE Donors SET donor_names = ? , blood_group = ? , phone = ? , donor_location = ?  WHERE donor_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            // SETTING MODEL
            pst.setString(1, donorsobj.getDonor_names());
            pst.setString(2, donorsobj.getBlood_group());
            pst.setString(3, donorsobj.getPhone());
            pst.setString(4, donorsobj.getDonor_location());
            pst.setInt(5, donorsobj.getDonor_id());
            int rowsAffected = pst.executeUpdate();
            con.close();
            return rowsAffected;
            
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public int deleteDonors(Donors donorsObj){
       try{
           Connection con= DriverManager.getConnection(jdbcUrl, username, password);
           String sql="DELETE FROM donors where donor_id= ?";
           PreparedStatement pst=con.prepareStatement(sql);
           pst.setInt(1, donorsObj.getDonor_id());
           int rowsDeleted= pst.executeUpdate();
           con.close();
           return rowsDeleted;
       }catch(Exception ex){
           ex.printStackTrace();
       }
       return 0;
   }
    
    
    public Donors findDonorsbyID(int donorId){
        // CHANGE: Return null when no donor is found instead of returning an empty Donors object.
        // PURPOSE: Correct "find by ID" behavior and ensure JUnit tests expecting null for invalid IDs pass.
        Donors donors = null;
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql="SELECT * FROM donors WHERE donor_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, donorId);
            ResultSet rs = pst.executeQuery();

            // CHANGE: Only create the model if the result set has a row.
            // PURPOSE: Avoid returning a non-null object when no DB record exists.
            if(rs.next()){
                donors = new Donors();
                donors.setDonor_id(rs.getInt("donor_id"));
                donors.setDonor_names(rs.getString("donor_names"));
                donors.setBlood_group(rs.getString("blood_group"));
                donors.setPhone(rs.getString("phone"));
                donors.setDonor_location(rs.getString("donor_location"));
            }
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return donors;
    }
    public List<Donors> findAllDonors(){
         try{
             Connection con = DriverManager.getConnection(jdbcUrl, username, password);
             String sql="Select*from donors";
             PreparedStatement pst=con.prepareStatement(sql);
             ResultSet rs=pst.executeQuery();
             List<Donors> Donorlist=new ArrayList<>();
             while(rs.next()){
                Donors donorsobj=new Donors();
                donorsobj.setDonor_id(rs.getInt("donor_id"));
                donorsobj.setDonor_names(rs.getString("donor_names"));
                donorsobj.setPhone(rs.getString("phone"));
                donorsobj.setBlood_group(rs.getString("blood_group"));
                donorsobj.setDonor_location(rs.getString("donor_location"));
                Donorlist.add(donorsobj);
             }
             con.close();
             return Donorlist;
         }catch(Exception ex){
             ex.printStackTrace();
         }
        return null;
    }
}
    

