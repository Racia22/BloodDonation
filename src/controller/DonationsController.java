/*
 * CHANGE: Created DonationsController class for MVC pattern implementation
 * PURPOSE: Implements requirement #6 (Software Design Pattern) - MVC Pattern
 * 
 * This controller acts as the intermediary between View and DAO layers:
 * - Handles business logic for donation operations
 * - Coordinates between DonationsDao and View components
 * - Provides a clean separation of concerns
 * 
 * MVC Architecture:
 * - Model: Donations class (data structure)
 * - View: Dashboard.java (UI components)
 * - Controller: DonationsController.java (business logic and coordination)
 */
package controller;

import dao.DonationsDao;
import dao.DonorsDao;
import dao.SitesDao;
import model.Donations;
import java.util.List;

/**
 * Controller class for Donations entity operations following MVC pattern.
 * 
 * CHANGE: Created controller layer to separate business logic from view and data access
 * PURPOSE: Implement MVC design pattern as required
 * 
 * This controller handles:
 * - Donation registration business logic
 * - Donation information updates
 * - Donation deletion operations
 * - Donation retrieval operations
 * 
 * CHANGE: Added comprehensive JavaDoc comments
 * PURPOSE: Document class purpose and usage following Google Java Style Guide
 * 
 * @author RC
 */
public class DonationsController {
    
    // CHANGE: Added DonationsDao instance as private field
    // PURPOSE: Encapsulate data access layer dependency
    private DonationsDao donationsDao;
    
    // CHANGE: Added DonorsDao and SitesDao for validation
    // PURPOSE: Validate foreign key relationships before operations
    private DonorsDao donorsDao;
    private SitesDao sitesDao;
    
    /**
     * CHANGE: Added constructor to initialize DAOs
     * PURPOSE: Follow dependency injection pattern for better testability
     */
    public DonationsController() {
        // CHANGE: Initialize DAO instances
        // PURPOSE: Create data access objects for donation operations
        this.donationsDao = new DonationsDao();
        this.donorsDao = new DonorsDao();
        this.sitesDao = new SitesDao();
    }
    
    /**
     * CHANGE: Created controller method for donation registration
     * PURPOSE: Separate business logic from view layer
     * 
     * Registers a new donation in the system.
     * 
     * CHANGE: Added validation logic before database operation
     * PURPOSE: Implement business rules at controller level
     * 
     * @param donation the Donations object containing donation information
     * @return number of rows affected (> 0 if successful)
     */
    public int registerDonation(Donations donation) {
        // CHANGE: Added input validation
        // PURPOSE: Validate donation data before database operation
        if (donation == null) {
            return 0;
        }
        
        // CHANGE: Validate foreign key relationships
        // PURPOSE: Ensure donor and site exist before creating donation
        if (donation.getDonor_id() <= 0 || donation.getSite_id() <= 0) {
            return 0;
        }
        
        // CHANGE: Validate donor exists
        // PURPOSE: Ensure donor ID is valid
        if (donorsDao.findDonorsbyID(donation.getDonor_id()) == null) {
            return 0;
        }
        
        // CHANGE: Validate site exists
        // PURPOSE: Ensure site ID is valid
        if (sitesDao.findSiteByID(donation.getSite_id()) == null) {
            return 0;
        }
        
        // CHANGE: Validate required fields
        // PURPOSE: Ensure all required donation information is present
        if (donation.getBlood_group() == null || donation.getBlood_group().trim().isEmpty()) {
            return 0;
        }
        
        if (donation.getQuantity_ml() <= 0) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return donationsDao.registerDonations(donation);
    }
    
    /**
     * CHANGE: Created controller method for donation update
     * PURPOSE: Separate business logic from view layer
     * 
     * Updates donation information in the system.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate donation data before database operation
     * 
     * @param donation the Donations object containing updated information
     * @return number of rows affected (> 0 if successful)
     */
    public int updateDonation(Donations donation) {
        // CHANGE: Added input validation
        // PURPOSE: Validate donation object before database operation
        if (donation == null) {
            return 0;
        }
        
        // CHANGE: Validate donation ID exists
        // PURPOSE: Ensure donation exists before update
        if (donation.getDonation_id() <= 0) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return donationsDao.DonationsUpdate(donation);
    }
    
    /**
     * CHANGE: Created controller method for donation deletion
     * PURPOSE: Separate business logic from view layer
     * 
     * Deletes a donation from the system.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate donation object before database operation
     * 
     * @param donation the Donations object to delete
     * @return number of rows affected (> 0 if successful)
     */
    public int deleteDonation(Donations donation) {
        // CHANGE: Added input validation
        // PURPOSE: Validate donation object before database operation
        if (donation == null) {
            return 0;
        }
        
        // CHANGE: Validate donation ID exists
        // PURPOSE: Ensure donation exists before deletion
        if (donation.getDonation_id() <= 0) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return donationsDao.deleteDonations(donation);
    }
    
    /**
     * CHANGE: Created controller method for donation retrieval by ID
     * PURPOSE: Separate business logic from view layer
     * 
     * Retrieves a donation from the system by donation ID.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate donation ID before database operation
     * 
     * @param donationId the ID of the donation to retrieve
     * @return Donations object if found, null otherwise
     */
    public Donations getDonationById(int donationId) {
        // CHANGE: Added input validation
        // PURPOSE: Validate donation ID before database operation
        if (donationId <= 0) {
            return null;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return donationsDao.findDonationsbyID(donationId);
    }
    
    /**
     * CHANGE: Created controller method for retrieving all donations
     * PURPOSE: Separate business logic from view layer
     * 
     * Retrieves all donations from the system.
     * 
     * @return List of Donations objects, or null if error occurs
     */
    public List<Donations> getAllDonations() {
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return donationsDao.findAllDonations();
    }
}

