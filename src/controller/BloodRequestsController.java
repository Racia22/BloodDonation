/*
 * CHANGE: Created BloodRequestsController class for MVC pattern implementation
 * PURPOSE: Implements requirement #6 (Software Design Pattern) - MVC Pattern
 * 
 * This controller acts as the intermediary between View and DAO layers:
 * - Handles business logic for blood request operations
 * - Coordinates between BloodRequestsDao and View components
 * - Provides a clean separation of concerns
 * 
 * MVC Architecture:
 * - Model: BloodRequest class (data structure)
 * - View: Dashboard.java (UI components)
 * - Controller: BloodRequestsController.java (business logic and coordination)
 */
package controller;

import dao.BloodRequestsDao;
import model.BloodRequest;
import java.util.List;

/**
 * Controller class for BloodRequest entity operations following MVC pattern.
 * 
 * CHANGE: Created controller layer to separate business logic from view and data access
 * PURPOSE: Implement MVC design pattern as required
 * 
 * This controller handles:
 * - Blood request registration business logic
 * - Blood request information updates
 * - Blood request deletion operations
 * - Blood request retrieval operations
 * 
 * CHANGE: Added comprehensive JavaDoc comments
 * PURPOSE: Document class purpose and usage following Google Java Style Guide
 * 
 * @author RC
 */
public class BloodRequestsController {
    
    // CHANGE: Added BloodRequestsDao instance as private field
    // PURPOSE: Encapsulate data access layer dependency
    private BloodRequestsDao bloodRequestsDao;
    
    /**
     * CHANGE: Added constructor to initialize BloodRequestsDao
     * PURPOSE: Follow dependency injection pattern for better testability
     */
    public BloodRequestsController() {
        // CHANGE: Initialize BloodRequestsDao instance
        // PURPOSE: Create data access object for blood request operations
        this.bloodRequestsDao = new BloodRequestsDao();
    }
    
    /**
     * CHANGE: Created controller method for blood request registration
     * PURPOSE: Separate business logic from view layer
     * 
     * Registers a new blood request in the system.
     * 
     * CHANGE: Added validation logic before database operation
     * PURPOSE: Implement business rules at controller level
     * 
     * @param bloodRequest the BloodRequest object containing request information
     * @return number of rows affected (> 0 if successful)
     */
    public int registerBloodRequest(BloodRequest bloodRequest) {
        // CHANGE: Added input validation
        // PURPOSE: Validate blood request data before database operation
        if (bloodRequest == null) {
            return 0;
        }
        
        // CHANGE: Validate required fields
        // PURPOSE: Ensure all required blood request information is present
        if (bloodRequest.getHospital_name() == null || bloodRequest.getHospital_name().trim().isEmpty()) {
            return 0;
        }
        
        if (bloodRequest.getBlood_group() == null || bloodRequest.getBlood_group().trim().isEmpty()) {
            return 0;
        }
        
        if (bloodRequest.getQuantity_ml() <= 0) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return bloodRequestsDao.registerrequest(bloodRequest);
    }
    
    /**
     * CHANGE: Created controller method for blood request update
     * PURPOSE: Separate business logic from view layer
     * 
     * Updates blood request information in the system.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate blood request data before database operation
     * 
     * @param bloodRequest the BloodRequest object containing updated information
     * @return number of rows affected (> 0 if successful)
     */
    public int updateBloodRequest(BloodRequest bloodRequest) {
        // CHANGE: Added input validation
        // PURPOSE: Validate blood request object before database operation
        if (bloodRequest == null) {
            return 0;
        }
        
        // CHANGE: Validate request ID exists
        // PURPOSE: Ensure blood request exists before update
        if (bloodRequest.getRequest_id() <= 0) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return bloodRequestsDao.updaterequest(bloodRequest);
    }
    
    /**
     * CHANGE: Created controller method for blood request deletion
     * PURPOSE: Separate business logic from view layer
     * 
     * Deletes a blood request from the system.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate blood request object before database operation
     * 
     * @param bloodRequest the BloodRequest object to delete
     * @return number of rows affected (> 0 if successful)
     */
    public int deleteBloodRequest(BloodRequest bloodRequest) {
        // CHANGE: Added input validation
        // PURPOSE: Validate blood request object before database operation
        if (bloodRequest == null) {
            return 0;
        }
        
        // CHANGE: Validate request ID exists
        // PURPOSE: Ensure blood request exists before deletion
        if (bloodRequest.getRequest_id() <= 0) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return bloodRequestsDao.deleterequest(bloodRequest);
    }
    
    /**
     * CHANGE: Created controller method for blood request retrieval by ID
     * PURPOSE: Separate business logic from view layer
     * 
     * Retrieves a blood request from the system by request ID.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate request ID before database operation
     * 
     * @param requestId the ID of the blood request to retrieve
     * @return BloodRequest object if found, null otherwise
     */
    public BloodRequest getBloodRequestById(int requestId) {
        // CHANGE: Added input validation
        // PURPOSE: Validate request ID before database operation
        if (requestId <= 0) {
            return null;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return bloodRequestsDao.findrequestById(requestId);
    }
    
    /**
     * CHANGE: Created controller method for retrieving all blood requests
     * PURPOSE: Separate business logic from view layer
     * 
     * Retrieves all blood requests from the system.
     * 
     * @return List of BloodRequest objects, or null if error occurs
     */
    public List<BloodRequest> getAllBloodRequests() {
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return bloodRequestsDao.findAllBloodRequest();
    }
}

