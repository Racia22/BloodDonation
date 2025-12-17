/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.BloodRequest;
import java.sql.*;
import java.util.*;

/**
 *
 * @author RC
 */
public class BloodRequestsDao {
    
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/blood_donation_management_system_db";
    private String username = "postgres";
    private String password = "racia123"; 
    
    public int registerrequest(BloodRequest requestsobj){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "Insert into blood_requests (hospital_name, blood_group, quantity_ml, request_date, status) values (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, requestsobj.getHospital_name());
            pst.setString(2, requestsobj.getBlood_group());
            pst.setInt(3, requestsobj.getQuantity_ml());
            pst.setTimestamp(4, requestsobj.getRequest_date());
            pst.setString(5, requestsobj.getStatus());
            
            int rowsAffected = pst.executeUpdate();
            pst.close();
            con.close();
            return rowsAffected;
           
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public int updaterequest(BloodRequest requestsobj){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "Update blood_requests set hospital_name=?, blood_group= ?, quantity_ml = ?, status  =  ? where request_id= ?";
            PreparedStatement pst  = con.prepareStatement(sql);
            
            pst.setString(1, requestsobj.getHospital_name());
            pst.setString(2, requestsobj.getBlood_group());
            pst.setInt(3, requestsobj.getQuantity_ml());
            pst.setString(4, requestsobj.getStatus());
            pst.setInt(5, requestsobj.getRequest_id());
            
            int rowsAffected = pst.executeUpdate();
            con.close();
            
            return rowsAffected;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public int deleterequest(BloodRequest requestsobj){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql= "delete from blood_requests where request_id = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, requestsobj.getRequest_id());
            
            int rowsDeleted = pst.executeUpdate();
            con.close();
            return rowsDeleted;
            
        }catch(Exception ex){
            
        }
        return 0;
    }
    
    public BloodRequest findrequestById(int requestId){
        // CHANGE: Return null when no row is found instead of returning an empty BloodRequest object.
        // PURPOSE: Fix DAO contract so "find by ID" behaves correctly; this also fixes failing JUnit tests
        // (invalid ID and post-delete retrieval should return null).
        BloodRequest request = null;
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "select * from blood_requests where request_id= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, requestId);
            ResultSet rs = pst.executeQuery();
            
            // CHANGE: Only instantiate the model if a row exists.
            // PURPOSE: Prevent returning a non-null object when the query returns 0 rows.
            if (rs.next()){
                request = new BloodRequest();
                request.setRequest_id(rs.getInt("request_id"));
                request.setHospital_name(rs.getString("hospital_name"));
                request.setBlood_group(rs.getString("blood_group"));
                request.setQuantity_ml(rs.getInt("quantity_ml"));
                request.setRequest_date(rs.getTimestamp("request_date"));
                request.setStatus(rs.getString("status"));
            }
            con.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return request;
    }
    
 public List<BloodRequest> findAllBloodRequest() {
    List<BloodRequest> requestlist = new ArrayList<>();  // create list outside try
    
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
         PreparedStatement pst = con.prepareStatement("SELECT * FROM blood_requests");
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            BloodRequest request = new BloodRequest();
            request.setRequest_id(rs.getInt("request_id"));
            request.setHospital_name(rs.getString("hospital_name"));
            request.setBlood_group(rs.getString("blood_group"));
            request.setQuantity_ml(rs.getInt("quantity_ml"));
            request.setRequest_date(rs.getTimestamp("request_date"));
            request.setStatus(rs.getString("status"));
            
            requestlist.add(request);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        
    }
    
    return requestlist;   // ← NOW YOU RETURN THE ACTUAL LIST!
}
    
}
