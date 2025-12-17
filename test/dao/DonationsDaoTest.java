/*
 * JUnit Test Class for DonationsDao
 * 
 * CHANGE: Created comprehensive unit tests for DonationsDao class
 * PURPOSE: Implements requirement #7 (Testing) - Test plan and test cases
 * 
 * This test class covers all CRUD operations for Donations entity:
 * - Donation registration (registerDonations)
 * - Donation update (DonationsUpdate)
 * - Donation deletion (deleteDonations)
 * - Donation retrieval by ID (findDonationsbyID)
 * - Retrieve all donations (findAllDonations)
 */
package dao;

import model.Donations;
import model.Donors;
import model.Sites;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.UUID;

/**
 * Test class for DonationsDao operations.
 * 
 * CHANGE: Added comprehensive JavaDoc comments following Google Java Style Guide
 * PURPOSE: Document test class purpose and usage
 */
public class DonationsDaoTest {
    
    private DonationsDao donationsDao;
    private DonorsDao donorsDao;
    private SitesDao sitesDao;
    private Donations testDonation;
    private Donors testDonor;
    private Sites testSite;
    
    /**
     * CHANGE: Added setUp method with proper initialization
     * PURPOSE: Initialize test objects before each test method execution
     * This follows JUnit best practices for test setup
     */
    @Before
    public void setUp() {
        // CHANGE: Initialize DAO instances before each test
        // PURPOSE: Ensure fresh instances for each test to avoid state pollution
        donationsDao = new DonationsDao();
        donorsDao = new DonorsDao();
        sitesDao = new SitesDao();
        
        // CHANGE: Create test donor for donation tests
        // PURPOSE: Donations require valid donor_id
        testDonor = new Donors();
        // CHANGE: Use unique donor name/phone per test run.
        // PURPOSE: Avoid conflicts with existing DB data or UNIQUE constraints.
        String unique = UUID.randomUUID().toString().replace("-", "");
        testDonor.setDonor_names("Test Donor " + unique);
        // CHANGE: Ensure the phone number is digits-only.
        // PURPOSE: Many DB schemas enforce numeric-only phone constraints; UUID substrings can include letters.
        String donorDigits = String.valueOf(System.nanoTime());
        testDonor.setPhone("07" + donorDigits.substring(Math.max(0, donorDigits.length() - 8)));
        testDonor.setBlood_group("O+");
        testDonor.setDonor_location("Kigali");
        donorsDao.registerDonors(testDonor);
        
        // CHANGE: Retrieve donor ID after registration
        // PURPOSE: Need donor ID for donation creation
        List<Donors> allDonors = donorsDao.findAllDonors();
        testDonor = allDonors.get(allDonors.size() - 1);
        
        // CHANGE: Create test site for donation tests
        // PURPOSE: Donations require valid site_id
        testSite = new Sites();
        // CHANGE: Use unique site name/phone per test run.
        // PURPOSE: Avoid conflicts with existing DB data or UNIQUE constraints.
        testSite.setSite_names("Test Site " + unique);
        // CHANGE: Ensure the phone number is digits-only.
        // PURPOSE: Many DB schemas enforce numeric-only phone constraints; UUID substrings can include letters.
        String siteDigits = String.valueOf(System.nanoTime());
        testSite.setSite_phonenumber("07" + siteDigits.substring(Math.max(0, siteDigits.length() - 8)));
        testSite.setDoctors_name("Dr. Test");
        testSite.setSite_location("Kigali");
        sitesDao.registerSites(testSite);
        
        // CHANGE: Retrieve site ID after registration
        // PURPOSE: Need site ID for donation creation
        List<Sites> allSites = sitesDao.findAllSites();
        testSite = allSites.get(allSites.size() - 1);
        
        // CHANGE: Create test donation object with sample data
        // PURPOSE: Provide consistent test data for all test methods
        testDonation = new Donations();
        testDonation.setDonor_id(testDonor.getDonor_id());
        testDonation.setSite_id(testSite.getSite_id());
        testDonation.setBlood_group("O+");
        // CHANGE: Use a quantity value that is compatible with the database CHECK constraint on donations.quantity_ml.
        // PURPOSE: Prevent DB constraint violations during tests (e.g., 750 can violate donations_quantity_ml_check).
        testDonation.setQuantity_ml(450);
    }
    
    /**
     * CHANGE: Added tearDown method for cleanup
     * PURPOSE: Clean up test data after test execution
     * This ensures test isolation and prevents test interference
     */
    @After
    public void tearDown() {
        // CHANGE: Clean up test donation if it exists
        // PURPOSE: Maintain clean test database state
        if (testDonation.getDonation_id() > 0) {
            donationsDao.deleteDonations(testDonation);
        }
        
        // CHANGE: Clean up test donor and site
        // PURPOSE: Remove all test data
        if (testDonor.getDonor_id() > 0) {
            donorsDao.deleteDonors(testDonor);
        }
        if (testSite.getSite_id() > 0) {
            sitesDao.deleteSites(testSite);
        }
        
        donationsDao = null;
        donorsDao = null;
        sitesDao = null;
        testDonation = null;
        testDonor = null;
        testSite = null;
    }
    
    /**
     * Test Case TC_DONATION_001: Register Donation with Valid Data
     * 
     * CHANGE: Created test method for donation registration
     * PURPOSE: Verify that valid donation registration works correctly
     * Tests requirement: Donations can be successfully registered in the system
     */
    @Test
    public void testRegisterDonations_ValidData_ReturnsGreaterThanZero() {
        // CHANGE: Test donation registration with valid data
        // PURPOSE: Verify registerDonations() returns value greater than 0
        int result = donationsDao.registerDonations(testDonation);
        
        // CHANGE: Assert that registration was successful
        // PURPOSE: Validate that the method returns expected result
        assertTrue("Donation registration should return value > 0", result > 0);
        
        // CHANGE: Retrieve donation to verify it was saved
        // PURPOSE: Ensure data persistence works correctly
        List<Donations> allDonations = donationsDao.findAllDonations();
        assertNotNull("Donation list should not be null", allDonations);
    }
    
    /**
     * Test Case TC_DONATION_003: Update Donation
     * 
     * CHANGE: Created test method for donation update
     * PURPOSE: Verify that donation information can be updated
     * Tests requirement: Donation information can be modified
     */
    @Test
    public void testDonationsUpdate_ValidData_ReturnsGreaterThanZero() {
        // CHANGE: Register donation first
        // PURPOSE: Create donation to update
        donationsDao.registerDonations(testDonation);
        
        // CHANGE: Retrieve donation to get ID
        // PURPOSE: Need donation ID for update operation
        List<Donations> allDonations = donationsDao.findAllDonations();
        Donations registeredDonation = allDonations.get(allDonations.size() - 1);
        testDonation.setDonation_id(registeredDonation.getDonation_id());
        
        // CHANGE: Update donation information
        // PURPOSE: Test donation information modification
        // CHANGE: Update to another safe value (within common donation constraints) instead of 750.
        // PURPOSE: Keep the update test meaningful while avoiding DB CHECK constraint violations.
        testDonation.setQuantity_ml(500);
        testDonation.setBlood_group("A+");
        int result = donationsDao.DonationsUpdate(testDonation);
        
        // CHANGE: Verify update succeeded
        // PURPOSE: Validate update operation works correctly
        assertTrue("Donation update should return value > 0", result > 0);
        
        // CHANGE: Verify updated data
        // PURPOSE: Ensure changes are persisted
        Donations updatedDonation = donationsDao.findDonationsbyID(testDonation.getDonation_id());
        assertEquals("Quantity should be updated", 500, updatedDonation.getQuantity_ml());
        assertEquals("Blood group should be updated", "A+", updatedDonation.getBlood_group());
    }
    
    /**
     * Test Case TC_DONATION_004: Delete Donation
     * 
     * CHANGE: Created test method for donation deletion
     * PURPOSE: Verify that donations can be deleted
     * Tests requirement: System supports donation deletion
     */
    @Test
    public void testDeleteDonations_ValidId_ReturnsGreaterThanZero() {
        // CHANGE: Register donation first
        // PURPOSE: Create donation to delete
        donationsDao.registerDonations(testDonation);
        
        // CHANGE: Retrieve donation to get ID
        // PURPOSE: Need donation ID for delete operation
        List<Donations> allDonations = donationsDao.findAllDonations();
        Donations registeredDonation = allDonations.get(allDonations.size() - 1);
        testDonation.setDonation_id(registeredDonation.getDonation_id());
        
        // CHANGE: Delete donation
        // PURPOSE: Test donation deletion functionality
        int result = donationsDao.deleteDonations(testDonation);
        
        // CHANGE: Verify deletion succeeded
        // PURPOSE: Validate delete operation works correctly
        assertTrue("Donation deletion should return value > 0", result > 0);
        
        // CHANGE: Verify donation no longer exists
        // PURPOSE: Ensure donation is actually removed from database
        Donations deletedDonation = donationsDao.findDonationsbyID(testDonation.getDonation_id());
        assertNull("Deleted donation should not be retrievable", deletedDonation);
    }
    
    /**
     * Test Case TC_DONATION_005: Find Donation By ID
     * 
     * CHANGE: Created test method for donation retrieval by ID
     * PURPOSE: Verify that donations can be retrieved by their ID
     * Tests requirement: System can retrieve donation information by ID
     */
    @Test
    public void testFindDonationsbyID_ValidId_ReturnsDonation() {
        // CHANGE: Register donation first
        // PURPOSE: Create donation to retrieve
        donationsDao.registerDonations(testDonation);
        
        // CHANGE: Retrieve donation to get ID
        // PURPOSE: Need donation ID for retrieval test
        List<Donations> allDonations = donationsDao.findAllDonations();
        Donations registeredDonation = allDonations.get(allDonations.size() - 1);
        testDonation.setDonation_id(registeredDonation.getDonation_id());
        
        // CHANGE: Retrieve donation by ID
        // PURPOSE: Test donation retrieval by ID functionality
        Donations retrievedDonation = donationsDao.findDonationsbyID(testDonation.getDonation_id());
        
        // CHANGE: Verify donation retrieved successfully
        // PURPOSE: Validate retrieval operation works correctly
        assertNotNull("Donation should be retrieved by ID", retrievedDonation);
        assertEquals("Donation ID should match", testDonation.getDonation_id(), retrievedDonation.getDonation_id());
        assertEquals("Donor ID should match", testDonation.getDonor_id(), retrievedDonation.getDonor_id());
        assertEquals("Site ID should match", testDonation.getSite_id(), retrievedDonation.getSite_id());
        assertEquals("Blood group should match", testDonation.getBlood_group(), retrievedDonation.getBlood_group());
    }
    
    /**
     * CHANGE: Created test method for invalid ID retrieval
     * PURPOSE: Verify that invalid IDs return null
     */
    @Test
    public void testFindDonationsbyID_InvalidId_ReturnsNull() {
        // CHANGE: Attempt to retrieve donation with invalid ID
        // PURPOSE: Test error handling for non-existent donation ID
        Donations retrievedDonation = donationsDao.findDonationsbyID(-1);
        
        // CHANGE: Verify null is returned for invalid ID
        // PURPOSE: Ensure proper error handling
        assertNull("Invalid donation ID should return null", retrievedDonation);
    }
    
    /**
     * Test Case TC_DONATION_006: Find All Donations
     * 
     * CHANGE: Created test method for retrieving all donations
     * PURPOSE: Verify that all donations can be retrieved
     * Tests requirement: System can list all registered donations
     */
    @Test
    public void testFindAllDonations_ReturnsList() {
        // CHANGE: Register a test donation first
        // PURPOSE: Ensure at least one donation exists for testing
        donationsDao.registerDonations(testDonation);
        
        // CHANGE: Retrieve all donations
        // PURPOSE: Test retrieval of all donations functionality
        List<Donations> allDonations = donationsDao.findAllDonations();
        
        // CHANGE: Verify list is not null
        // PURPOSE: Validate retrieval operation works correctly
        assertNotNull("Donation list should not be null", allDonations);
    }
}

