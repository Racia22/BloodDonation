/*
 * CHANGE: Created DonorsController class for MVC pattern implementation
 * PURPOSE: Implements requirement #6 (Software Design Pattern) - MVC Pattern
 * 
 * This controller acts as the intermediary between View and DAO layers:
 * - Handles business logic for donor operations
 * - Coordinates between DonorsDao and View components
 * - Provides a clean separation of concerns
 * 
 * MVC Architecture:
 * - Model: Donors class (data structure)
 * - View: Dashboard.java (UI components)
 * - Controller: DonorsController.java (business logic and coordination)
 */
package controller;

import dao.DonorsDao;
import model.Donors;
import java.util.List;

/**
 * Controller class for Donors entity operations following MVC pattern.
 * 
 * CHANGE: Created controller layer to separate business logic from view and data access
 * PURPOSE: Implement MVC design pattern as required
 * 
 * This controller handles:
 * - Donor registration business logic
 * - Donor information updates
 * - Donor deletion operations
 * - Donor retrieval operations
 * 
 * CHANGE: Added comprehensive JavaDoc comments
 * PURPOSE: Document class purpose and usage following Google Java Style Guide
 * 
 * @author RC
 */
public class DonorsController {
    
    // CHANGE: Added DonorsDao instance as private field
    // PURPOSE: Encapsulate data access layer dependency
    private DonorsDao donorsDao;
    
    /**
     * CHANGE: Added constructor to initialize DonorsDao
     * PURPOSE: Follow dependency injection pattern for better testability
     */
    public DonorsController() {
        // CHANGE: Initialize DonorsDao instance
        // PURPOSE: Create data access object for donor operations
        this.donorsDao = new DonorsDao();
    }
    
    /**
     * CHANGE: Created controller method for donor registration
     * PURPOSE: Separate business logic from view layer
     * 
     * Registers a new donor in the system.
     * 
     * CHANGE: Added validation logic before database operation
     * PURPOSE: Implement business rules at controller level
     * 
     * @param donor the Donors object containing donor information
     * @return number of rows affected (>= 0 if successful)
     */
    public int registerDonor(Donors donor) {
        // CHANGE: Added input validation
        // PURPOSE: Validate donor data before database operation
        if (donor == null) {
            return 0;
        }
        
        // CHANGE: Validate required fields
        // PURPOSE: Ensure all required donor information is present
        if (donor.getDonor_names() == null || donor.getDonor_names().trim().isEmpty()) {
            return 0;
        }
        
        if (donor.getBlood_group() == null || donor.getBlood_group().trim().isEmpty()) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return donorsDao.registerDonors(donor);
    }
    
    /**
     * CHANGE: Created controller method for donor update
     * PURPOSE: Separate business logic from view layer
     * 
     * Updates donor information in the system.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate donor data before database operation
     * 
     * @param donor the Donors object containing updated information
     * @return number of rows affected (> 0 if successful)
     */
    public int updateDonor(Donors donor) {
        // CHANGE: Added input validation
        // PURPOSE: Validate donor object before database operation
        if (donor == null) {
            return 0;
        }
        
        // CHANGE: Validate donor ID exists
        // PURPOSE: Ensure donor exists before update
        if (donor.getDonor_id() <= 0) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return donorsDao.DonorsUpdate(donor);
    }
    
    /**
     * CHANGE: Created controller method for donor deletion
     * PURPOSE: Separate business logic from view layer
     * 
     * Deletes a donor from the system.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate donor object before database operation
     * 
     * @param donor the Donors object to delete
     * @return number of rows affected (> 0 if successful)
     */
    public int deleteDonor(Donors donor) {
        // CHANGE: Added input validation
        // PURPOSE: Validate donor object before database operation
        if (donor == null) {
            return 0;
        }
        
        // CHANGE: Validate donor ID exists
        // PURPOSE: Ensure donor exists before deletion
        if (donor.getDonor_id() <= 0) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return donorsDao.deleteDonors(donor);
    }
    
    /**
     * CHANGE: Created controller method for donor retrieval by ID
     * PURPOSE: Separate business logic from view layer
     * 
     * Retrieves a donor from the system by donor ID.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate donor ID before database operation
     * 
     * @param donorId the ID of the donor to retrieve
     * @return Donors object if found, null otherwise
     */
    public Donors getDonorById(int donorId) {
        // CHANGE: Added input validation
        // PURPOSE: Validate donor ID before database operation
        if (donorId <= 0) {
            return null;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return donorsDao.findDonorsbyID(donorId);
    }
    
    /**
     * CHANGE: Created controller method for retrieving all donors
     * PURPOSE: Separate business logic from view layer
     * 
     * Retrieves all donors from the system.
     * 
     * @return List of Donors objects, or null if error occurs
     */
    public List<Donors> getAllDonors() {
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return donorsDao.findAllDonors();
    }
}

