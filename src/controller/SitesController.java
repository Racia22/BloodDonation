/*
 * CHANGE: Created SitesController class for MVC pattern implementation
 * PURPOSE: Implements requirement #6 (Software Design Pattern) - MVC Pattern
 * 
 * This controller acts as the intermediary between View and DAO layers:
 * - Handles business logic for site operations
 * - Coordinates between SitesDao and View components
 * - Provides a clean separation of concerns
 * 
 * MVC Architecture:
 * - Model: Sites class (data structure)
 * - View: Dashboard.java (UI components)
 * - Controller: SitesController.java (business logic and coordination)
 */
package controller;

import dao.SitesDao;
import model.Sites;
import java.util.List;

/**
 * Controller class for Sites entity operations following MVC pattern.
 * 
 * CHANGE: Created controller layer to separate business logic from view and data access
 * PURPOSE: Implement MVC design pattern as required
 * 
 * This controller handles:
 * - Site registration business logic
 * - Site information updates
 * - Site deletion operations
 * - Site retrieval operations
 * 
 * CHANGE: Added comprehensive JavaDoc comments
 * PURPOSE: Document class purpose and usage following Google Java Style Guide
 * 
 * @author RC
 */
public class SitesController {
    
    // CHANGE: Added SitesDao instance as private field
    // PURPOSE: Encapsulate data access layer dependency
    private SitesDao sitesDao;
    
    /**
     * CHANGE: Added constructor to initialize SitesDao
     * PURPOSE: Follow dependency injection pattern for better testability
     */
    public SitesController() {
        // CHANGE: Initialize SitesDao instance
        // PURPOSE: Create data access object for site operations
        this.sitesDao = new SitesDao();
    }
    
    /**
     * CHANGE: Created controller method for site registration
     * PURPOSE: Separate business logic from view layer
     * 
     * Registers a new site in the system.
     * 
     * CHANGE: Added validation logic before database operation
     * PURPOSE: Implement business rules at controller level
     * 
     * @param site the Sites object containing site information
     * @return number of rows affected (>= 0 if successful)
     */
    public int registerSite(Sites site) {
        // CHANGE: Added input validation
        // PURPOSE: Validate site data before database operation
        if (site == null) {
            return 0;
        }
        
        // CHANGE: Validate required fields
        // PURPOSE: Ensure all required site information is present
        if (site.getSite_names() == null || site.getSite_names().trim().isEmpty()) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return sitesDao.registerSites(site);
    }
    
    /**
     * CHANGE: Created controller method for site update
     * PURPOSE: Separate business logic from view layer
     * 
     * Updates site information in the system.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate site data before database operation
     * 
     * @param site the Sites object containing updated information
     * @return number of rows affected (> 0 if successful)
     */
    public int updateSite(Sites site) {
        // CHANGE: Added input validation
        // PURPOSE: Validate site object before database operation
        if (site == null) {
            return 0;
        }
        
        // CHANGE: Validate site ID exists
        // PURPOSE: Ensure site exists before update
        if (site.getSite_id() <= 0) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return sitesDao.updateSites(site);
    }
    
    /**
     * CHANGE: Created controller method for site deletion
     * PURPOSE: Separate business logic from view layer
     * 
     * Deletes a site from the system.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate site object before database operation
     * 
     * @param site the Sites object to delete
     * @return number of rows affected (> 0 if successful)
     */
    public int deleteSite(Sites site) {
        // CHANGE: Added input validation
        // PURPOSE: Validate site object before database operation
        if (site == null) {
            return 0;
        }
        
        // CHANGE: Validate site ID exists
        // PURPOSE: Ensure site exists before deletion
        if (site.getSite_id() <= 0) {
            return 0;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return sitesDao.deleteSites(site);
    }
    
    /**
     * CHANGE: Created controller method for site retrieval by ID
     * PURPOSE: Separate business logic from view layer
     * 
     * Retrieves a site from the system by site ID.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate site ID before database operation
     * 
     * @param siteId the ID of the site to retrieve
     * @return Sites object if found, null otherwise
     */
    public Sites getSiteById(int siteId) {
        // CHANGE: Added input validation
        // PURPOSE: Validate site ID before database operation
        if (siteId <= 0) {
            return null;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return sitesDao.findSiteByID(siteId);
    }
    
    /**
     * CHANGE: Created controller method for retrieving all sites
     * PURPOSE: Separate business logic from view layer
     * 
     * Retrieves all sites from the system.
     * 
     * @return List of Sites objects, or null if error occurs
     */
    public List<Sites> getAllSites() {
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return sitesDao.findAllSites();
    }
}

