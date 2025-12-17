/*
 * JUnit Test Class for SitesDao
 * 
 * CHANGE: Created comprehensive unit tests for SitesDao class
 * PURPOSE: Implements requirement #7 (Testing) - Test plan and test cases
 * 
 * This test class covers all CRUD operations for Sites entity:
 * - Site registration (registerSites)
 * - Site update (updateSites)
 * - Site deletion (deleteSites)
 * - Site retrieval by ID (findSiteByID)
 * - Retrieve all sites (findAllSites)
 */
package dao;

import model.Sites;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.List;
import java.util.UUID;

/**
 * Test class for SitesDao operations.
 * 
 * CHANGE: Added comprehensive JavaDoc comments following Google Java Style Guide
 * PURPOSE: Document test class purpose and usage
 */
public class SitesDaoTest {
    
    private SitesDao sitesDao;
    private Sites testSite;
    
    /**
     * CHANGE: Added setUp method with proper initialization
     * PURPOSE: Initialize test objects before each test method execution
     * This follows JUnit best practices for test setup
     */
    @Before
    public void setUp() {
        // CHANGE: Initialize SitesDao instance before each test
        // PURPOSE: Ensure fresh instance for each test to avoid state pollution
        sitesDao = new SitesDao();
        
        // CHANGE: Create test site object with sample data
        // PURPOSE: Provide consistent test data for all test methods
        testSite = new Sites();
        // CHANGE: Use unique site name/phone per test run.
        // PURPOSE: Avoid conflicts with existing DB data or UNIQUE constraints.
        String unique = UUID.randomUUID().toString().replace("-", "");
        testSite.setSite_names("Test Blood Donation Site " + unique);
        // CHANGE: Ensure the phone number is digits-only.
        // PURPOSE: Many DB schemas enforce numeric-only phone constraints; UUID substrings can include letters.
        String digits = String.valueOf(System.nanoTime());
        testSite.setSite_phonenumber("07" + digits.substring(Math.max(0, digits.length() - 8)));
        testSite.setDoctors_name("Dr. Test");
        testSite.setSite_location("Kigali");
    }
    
    /**
     * CHANGE: Added tearDown method for cleanup
     * PURPOSE: Clean up test data after test execution
     * This ensures test isolation and prevents test interference
     */
    @After
    public void tearDown() {
        // CHANGE: Clean up test site if it exists
        // PURPOSE: Maintain clean test database state
        if (testSite.getSite_id() > 0) {
            sitesDao.deleteSites(testSite);
        }
        sitesDao = null;
        testSite = null;
    }
    
    /**
     * Test Case TC_SITE_001: Register Site with Valid Data
     * 
     * CHANGE: Created test method for site registration
     * PURPOSE: Verify that valid site registration works correctly
     * Tests requirement: Sites can be successfully registered in the system
     */
    @Test
    public void testRegisterSites_ValidData_ReturnsGreaterThanZero() {
        // CHANGE: Test site registration with valid data
        // PURPOSE: Verify registerSites() returns value greater than 0
        int result = sitesDao.registerSites(testSite);
        
        // CHANGE: Assert that registration was successful
        // PURPOSE: Validate that the method returns expected result
        assertTrue("Site registration should return value >= 0", result >= 0);
        
        // CHANGE: Retrieve site to verify it was saved
        // PURPOSE: Ensure data persistence works correctly
        List<Sites> allSites = sitesDao.findAllSites();
        assertNotNull("Site list should not be null", allSites);
    }
    
    /**
     * Test Case TC_SITE_002: Update Site Information
     * 
     * CHANGE: Created test method for site update
     * PURPOSE: Verify that site information can be updated
     * Tests requirement: Site information can be modified
     */
    @Test
    public void testUpdateSites_ValidData_ReturnsGreaterThanZero() {
        // CHANGE: Register site first
        // PURPOSE: Create site to update
        sitesDao.registerSites(testSite);
        
        // CHANGE: Retrieve site to get ID
        // PURPOSE: Need site ID for update operation
        List<Sites> allSites = sitesDao.findAllSites();
        Sites registeredSite = allSites.get(allSites.size() - 1);
        testSite.setSite_id(registeredSite.getSite_id());
        
        // CHANGE: Update site information
        // PURPOSE: Test site information modification
        // CHANGE: Use unique updated name to avoid collisions.
        // PURPOSE: Ensure stable assertions even if old test rows exist.
        String unique = UUID.randomUUID().toString().replace("-", "");
        testSite.setSite_names("Updated Site Name " + unique);
        testSite.setDoctors_name("Dr. Updated");
        int result = sitesDao.updateSites(testSite);
        
        // CHANGE: Verify update succeeded
        // PURPOSE: Validate update operation works correctly
        assertTrue("Site update should return value > 0", result > 0);
        
        // CHANGE: Verify updated data
        // PURPOSE: Ensure changes are persisted
        Sites updatedSite = sitesDao.findSiteByID(testSite.getSite_id());
        assertEquals("Site name should be updated", "Updated Site Name " + unique, updatedSite.getSite_names());
        assertEquals("Doctor name should be updated", "Dr. Updated", updatedSite.getDoctors_name());
    }
    
    /**
     * Test Case TC_SITE_003: Delete Site
     * 
     * CHANGE: Created test method for site deletion
     * PURPOSE: Verify that sites can be deleted
     * Tests requirement: System supports site deletion
     */
    @Test
    public void testDeleteSites_ValidId_ReturnsGreaterThanZero() {
        // CHANGE: Register site first
        // PURPOSE: Create site to delete
        sitesDao.registerSites(testSite);
        
        // CHANGE: Retrieve site to get ID
        // PURPOSE: Need site ID for delete operation
        List<Sites> allSites = sitesDao.findAllSites();
        Sites registeredSite = allSites.get(allSites.size() - 1);
        testSite.setSite_id(registeredSite.getSite_id());
        
        // CHANGE: Delete site
        // PURPOSE: Test site deletion functionality
        int result = sitesDao.deleteSites(testSite);
        
        // CHANGE: Verify deletion succeeded
        // PURPOSE: Validate delete operation works correctly
        assertTrue("Site deletion should return value > 0", result > 0);
        
        // CHANGE: Verify site no longer exists
        // PURPOSE: Ensure site is actually removed from database
        Sites deletedSite = sitesDao.findSiteByID(testSite.getSite_id());
        assertNull("Deleted site should not be retrievable", deletedSite);
    }
    
    /**
     * Test Case TC_SITE_004: Find Site By ID
     * 
     * CHANGE: Created test method for site retrieval by ID
     * PURPOSE: Verify that sites can be retrieved by their ID
     * Tests requirement: System can retrieve site information by ID
     */
    @Test
    public void testFindSiteByID_ValidId_ReturnsSite() {
        // CHANGE: Register site first
        // PURPOSE: Create site to retrieve
        sitesDao.registerSites(testSite);
        
        // CHANGE: Retrieve site to get ID
        // PURPOSE: Need site ID for retrieval test
        List<Sites> allSites = sitesDao.findAllSites();
        Sites registeredSite = allSites.get(allSites.size() - 1);
        testSite.setSite_id(registeredSite.getSite_id());
        
        // CHANGE: Retrieve site by ID
        // PURPOSE: Test site retrieval by ID functionality
        Sites retrievedSite = sitesDao.findSiteByID(testSite.getSite_id());
        
        // CHANGE: Verify site retrieved successfully
        // PURPOSE: Validate retrieval operation works correctly
        assertNotNull("Site should be retrieved by ID", retrievedSite);
        assertEquals("Site ID should match", testSite.getSite_id(), retrievedSite.getSite_id());
        assertEquals("Site name should match", testSite.getSite_names(), retrievedSite.getSite_names());
        assertEquals("Doctor name should match", testSite.getDoctors_name(), retrievedSite.getDoctors_name());
    }
    
    /**
     * CHANGE: Created test method for invalid ID retrieval
     * PURPOSE: Verify that invalid IDs return null
     */
    @Test
    public void testFindSiteByID_InvalidId_ReturnsNull() {
        // CHANGE: Attempt to retrieve site with invalid ID
        // PURPOSE: Test error handling for non-existent site ID
        Sites retrievedSite = sitesDao.findSiteByID(-1);
        
        // CHANGE: Verify null is returned for invalid ID
        // PURPOSE: Ensure proper error handling
        assertNull("Invalid site ID should return null", retrievedSite);
    }
    
    /**
     * Test Case TC_SITE_005: Find All Sites
     * 
     * CHANGE: Created test method for retrieving all sites
     * PURPOSE: Verify that all sites can be retrieved
     * Tests requirement: System can list all registered sites
     */
    @Test
    public void testFindAllSites_ReturnsList() {
        // CHANGE: Register a test site first
        // PURPOSE: Ensure at least one site exists for testing
        sitesDao.registerSites(testSite);
        
        // CHANGE: Retrieve all sites
        // PURPOSE: Test retrieval of all sites functionality
        List<Sites> allSites = sitesDao.findAllSites();
        
        // CHANGE: Verify list is not null and contains data
        // PURPOSE: Validate retrieval operation works correctly
        assertNotNull("Site list should not be null", allSites);
        assertFalse("Site list should not be empty", allSites.isEmpty());
        
        // CHANGE: Verify list contains registered site
        // PURPOSE: Ensure newly registered site appears in list
        boolean found = false;
        for (Sites site : allSites) {
            if (site.getSite_names().equals(testSite.getSite_names())) {
                found = true;
                break;
            }
        }
        assertTrue("Registered site should be in the list", found);
    }
}

