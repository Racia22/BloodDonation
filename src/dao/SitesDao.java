/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Sites;
import java.sql.*;
import java.util.*;


/**
 *
 * @author RC
 */
public class SitesDao {
    
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/blood_donation_management_system_db";
    private String username = "postgres";
    private String password = "racia123"; 
    
    public int registerSites(Sites sitesobj){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "insert into sites(site_names, site_phonenumber, doctors_name, site_location) values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, sitesobj.getSite_names());
            pst.setString(2, sitesobj.getSite_phonenumber());
            pst.setString(3, sitesobj.getDoctors_name());
            pst.setString(4, sitesobj.getSite_location());
            
            int rowsAffected = pst.executeUpdate();
            con.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public int updateSites(Sites sitesobj){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "update sites set site_names= ?, site_phonenumber= ?, doctors_name= ?,site_location= ?  where site_id= ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, sitesobj.getSite_names());
            pst.setString(2, sitesobj.getSite_phonenumber());
            pst.setString(3, sitesobj.getDoctors_name());
            pst.setString(4, sitesobj.getSite_location());
            pst.setInt(5, sitesobj.getSite_id());
            
            int rowsAffected = pst.executeUpdate();
            
            con.close();
            return rowsAffected;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public int deleteSites(Sites sitesobj){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "delete from sites where site_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, sitesobj.getSite_id());
            
            int rowsDeleted = pst.executeUpdate();
            con.close();
            return rowsDeleted;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public Sites findSiteByID(int siteId){
        Sites thesites = null;
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "select * from sites where site_id= ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, siteId);
            
            ResultSet rs = pst.executeQuery();
            thesites = new Sites();
            if(rs.next()){
                thesites.setSite_id(rs.getInt("site_id"));
                thesites.setSite_names(rs.getString("site_names"));
                thesites.setSite_phonenumber(rs.getString("site_phonenumber"));
                thesites.setDoctors_name(rs.getString("doctors_name"));
                thesites.setSite_location(rs.getString("site_location"));
                
                return thesites;
            }
            
            con.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Sites> findAllSites(){
         try{
             Connection con = DriverManager.getConnection(jdbcUrl, username, password);
             String sql="Select*from Sites";
             PreparedStatement pst=con.prepareStatement(sql);
             ResultSet rs=pst.executeQuery();
             List<Sites> siteslist=new ArrayList<>();
             while(rs.next()){
                Sites sitesobj=new Sites();
                sitesobj.setSite_id(rs.getInt("site_id"));
                sitesobj.setSite_names(rs.getString("site_names"));
                sitesobj.setSite_phonenumber(rs.getString("site_phonenumber"));
                sitesobj.setDoctors_name(rs.getString("doctors_name"));
                sitesobj.setSite_location(rs.getString("site_location"));
                siteslist.add(sitesobj);
             }
             con.close();
             return siteslist;
         }catch(Exception ex){
             ex.printStackTrace();
         }
        return null;
    }
}
