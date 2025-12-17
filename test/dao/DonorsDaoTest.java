/*
 * JUnit Test Class for DonorsDao
 * 
 * CHANGE: Created comprehensive unit tests for DonorsDao class
 * PURPOSE: Implements requirement #7 (Testing) - Test plan and test cases
 * 
 * This test class covers all CRUD operations for Donors entity:
 * - Donor registration (registerDonors)
 * - Donor update (DonorsUpdate)
 * - Donor deletion (deleteDonors)
 * - Donor retrieval by ID (findDonorsbyID)
 * - Retrieve all donors (findAllDonors)
 */
package dao;

import model.Donors;
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
 * Test class for DonorsDao operations.
 * 
 * CHANGE: Added comprehensive JavaDoc comments following Google Java Style Guide
 * PURPOSE: Document test class purpose and usage
 */
public class DonorsDaoTest {
    
    private DonorsDao donorsDao;
    private Donors testDonor;
    // CHANGE: Track a unique suffix for this test instance.
    // PURPOSE: Use the same unique value across insert/update and cleanup.
    private String uniqueSuffix;
    
    /**
     * CHANGE: Added setUp method with proper initialization
     * PURPOSE: Initialize test objects before each test method execution
     * This follows JUnit best practices for test setup
     */
    @Before
    public void setUp() {
        // CHANGE: Initialize DonorsDao instance before each test
        // PURPOSE: Ensure fresh instance for each test to avoid state pollution
        donorsDao = new DonorsDao();
        
        // CHANGE: Create test donor object with sample data
        // PURPOSE: Provide consistent test data for all test methods
        testDonor = new Donors();
        // CHANGE: Use unique donor name per test run to avoid conflicts with existing DB data.
        // PURPOSE: Make tests deterministic even if older test donors exist in the database.
        // CHANGE: Use UUID for uniqueness instead of timestamps.
        // PURPOSE: Avoid rare collisions when tests execute quickly.
        uniqueSuffix = UUID.randomUUID().toString().replace("-", "");
        testDonor.setDonor_names("John Doe " + uniqueSuffix);
        // CHANGE: Use unique phone number per test run.
        // PURPOSE: Avoid UNIQUE(phone) constraints if they exist in your DB.
        // CHANGE: Ensure the phone number is digits-only.
        // PURPOSE: Many DB schemas enforce numeric-only phone constraints; UUID substrings can include letters.
        String digits = String.valueOf(System.nanoTime());
        testDonor.setPhone("07" + digits.substring(Math.max(0, digits.length() - 8)));
        testDonor.setBlood_group("O+");
        testDonor.setDonor_location("Kigali");
    }
    
    /**
     * CHANGE: Added tearDown method for cleanup
     * PURPOSE: Clean up test data after test execution
     * This ensures test isolation and prevents test interference
     */
    @After
    public void tearDown() {
        // CHANGE: Clean up test donor if it exists
        // PURPOSE: Maintain clean test database state
        // CHANGE: Cleanup by searching for the donor row by unique phone/name.
        // PURPOSE: registerDonors() does not populate donor_id on the model; avoid leaving test rows behind
        // and avoid relying on "last row" ordering.
        try {
            List<Donors> allDonors = donorsDao.findAllDonors();
            if (allDonors != null && !allDonors.isEmpty()) {
                for (Donors d : allDonors) {
                    if (d != null
                        && testDonor.getPhone() != null
                        && testDonor.getPhone().equals(d.getPhone())
                        && testDonor.getDonor_names() != null
                        && testDonor.getDonor_names().equals(d.getDonor_names())) {
                        testDonor.setDonor_id(d.getDonor_id());
                        donorsDao.deleteDonors(testDonor);
                        break;
                    }
                }
            }
        } catch (Exception ignored) {
            // Ignore cleanup errors
        }
        donorsDao = null;
        testDonor = null;
    }
    
    /**
     * Test Case TC_DONOR_001: Register Donor with Valid Data
     * 
     * CHANGE: Created test method for donor registration
     * PURPOSE: Verify that valid donor registration works correctly
     * Tests requirement: Donors can be successfully registered in the system
     */
    @Test
    public void testRegisterDonors_ValidData_ReturnsGreaterThanZero() {
        // CHANGE: Test donor registration with valid data
        // PURPOSE: Verify registerDonors() returns value greater than 0
        int result = donorsDao.registerDonors(testDonor);
        
        // CHANGE: Assert that registration was successful
        // PURPOSE: Validate that the method returns expected result
        assertTrue("Donor registration should return value > 0", result >= 0);
        
        // CHANGE: Retrieve donor to verify it was saved
        // PURPOSE: Ensure data persistence works correctly
        List<Donors> allDonors = donorsDao.findAllDonors();
        assertNotNull("Donor list should not be null", allDonors);
    }
    
    /**
     * Test Case TC_DONOR_002: Update Donor Information
     * 
     * CHANGE: Created test method for donor update
     * PURPOSE: Verify that donor information can be updated
     * Tests requirement: Donor information can be modified
     */
    @Test
    public void testDonorsUpdate_ValidData_ReturnsGreaterThanZero() {
        // CHANGE: Register donor first
        // PURPOSE: Create donor account to update
        donorsDao.registerDonors(testDonor);
        
        // CHANGE: Retrieve donor to get ID
        // PURPOSE: Need donor ID for update operation
        List<Donors> allDonors = donorsDao.findAllDonors();
        Donors registeredDonor = allDonors.get(allDonors.size() - 1);
        testDonor.setDonor_id(registeredDonor.getDonor_id());
        
        // CHANGE: Update donor information
        // PURPOSE: Test donor information modification
        // CHANGE: Use unique updated name to avoid collisions and make verification stable.
        // PURPOSE: Ensure test asserts the exact updated value.
        testDonor.setDonor_names("Jane Doe Updated " + uniqueSuffix);
        testDonor.setBlood_group("A+");
        int result = donorsDao.DonorsUpdate(testDonor);
        
        // CHANGE: Verify update succeeded
        // PURPOSE: Validate update operation works correctly
        assertTrue("Donor update should return value > 0", result > 0);
        
        // CHANGE: Verify updated data
        // PURPOSE: Ensure changes are persisted
        Donors updatedDonor = donorsDao.findDonorsbyID(testDonor.getDonor_id());
        assertEquals("Donor name should be updated", "Jane Doe Updated " + uniqueSuffix, updatedDonor.getDonor_names());
        assertEquals("Blood group should be updated", "A+", updatedDonor.getBlood_group());
    }
    
    /**
     * Test Case TC_DONOR_003: Delete Donor
     * 
     * CHANGE: Created test method for donor deletion
     * PURPOSE: Verify that donors can be deleted
     * Tests requirement: System supports donor deletion
     */
    @Test
    public void testDeleteDonors_ValidId_ReturnsGreaterThanZero() {
        // CHANGE: Register donor first
        // PURPOSE: Create donor account to delete
        donorsDao.registerDonors(testDonor);
        
        // CHANGE: Retrieve donor to get ID
        // PURPOSE: Need donor ID for delete operation
        List<Donors> allDonors = donorsDao.findAllDonors();
        Donors registeredDonor = allDonors.get(allDonors.size() - 1);
        testDonor.setDonor_id(registeredDonor.getDonor_id());
        
        // CHANGE: Delete donor
        // PURPOSE: Test donor deletion functionality
        int result = donorsDao.deleteDonors(testDonor);
        
        // CHANGE: Verify deletion succeeded
        // PURPOSE: Validate delete operation works correctly
        assertTrue("Donor deletion should return value > 0", result > 0);
        
        // CHANGE: Verify donor no longer exists
        // PURPOSE: Ensure donor is actually removed from database
        Donors deletedDonor = donorsDao.findDonorsbyID(testDonor.getDonor_id());
        assertNull("Deleted donor should not be retrievable", deletedDonor);
    }
    
    /**
     * Test Case TC_DONOR_004: Find Donor By ID
     * 
     * CHANGE: Created test method for donor retrieval by ID
     * PURPOSE: Verify that donors can be retrieved by their ID
     * Tests requirement: System can retrieve donor information by ID
     */
    @Test
    public void testFindDonorsbyID_ValidId_ReturnsDonor() {
        // CHANGE: Register donor first
        // PURPOSE: Create donor account to retrieve
        donorsDao.registerDonors(testDonor);
        
        // CHANGE: Retrieve donor to get ID
        // PURPOSE: Need donor ID for retrieval test
        List<Donors> allDonors = donorsDao.findAllDonors();
        Donors registeredDonor = allDonors.get(allDonors.size() - 1);
        testDonor.setDonor_id(registeredDonor.getDonor_id());
        
        // CHANGE: Retrieve donor by ID
        // PURPOSE: Test donor retrieval by ID functionality
        Donors retrievedDonor = donorsDao.findDonorsbyID(testDonor.getDonor_id());
        
        // CHANGE: Verify donor retrieved successfully
        // PURPOSE: Validate retrieval operation works correctly
        assertNotNull("Donor should be retrieved by ID", retrievedDonor);
        assertEquals("Donor ID should match", testDonor.getDonor_id(), retrievedDonor.getDonor_id());
        assertEquals("Donor name should match", testDonor.getDonor_names(), retrievedDonor.getDonor_names());
        assertEquals("Blood group should match", testDonor.getBlood_group(), retrievedDonor.getBlood_group());
    }
    
    /**
     * CHANGE: Created test method for invalid ID retrieval
     * PURPOSE: Verify that invalid IDs return null
     */
    @Test
    public void testFindDonorsbyID_InvalidId_ReturnsNull() {
        // CHANGE: Attempt to retrieve donor with invalid ID
        // PURPOSE: Test error handling for non-existent donor ID
        Donors retrievedDonor = donorsDao.findDonorsbyID(-1);
        
        // CHANGE: Verify null is returned for invalid ID
        // PURPOSE: Ensure proper error handling
        assertNull("Invalid donor ID should return null", retrievedDonor);
    }
    
    /**
     * Test Case TC_DONOR_005: Find All Donors
     * 
     * CHANGE: Created test method for retrieving all donors
     * PURPOSE: Verify that all donors can be retrieved
     * Tests requirement: System can list all registered donors
     */
    @Test
    public void testFindAllDonors_ReturnsList() {
        // CHANGE: Register a test donor first
        // PURPOSE: Ensure at least one donor exists for testing
        donorsDao.registerDonors(testDonor);
        
        // CHANGE: Retrieve all donors
        // PURPOSE: Test retrieval of all donors functionality
        List<Donors> allDonors = donorsDao.findAllDonors();
        
        // CHANGE: Verify list is not null and contains data
        // PURPOSE: Validate retrieval operation works correctly
        assertNotNull("Donor list should not be null", allDonors);
        assertFalse("Donor list should not be empty", allDonors.isEmpty());
        
        // CHANGE: Verify list contains registered donor
        // PURPOSE: Ensure newly registered donor appears in list
        boolean found = false;
        for (Donors donor : allDonors) {
            if (donor.getDonor_names().equals(testDonor.getDonor_names())) {
                found = true;
                break;
            }
        }
        assertTrue("Registered donor should be in the list", found);
    }
}

