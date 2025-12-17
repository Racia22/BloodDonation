/*
 * JUnit Test Class for BloodRequestsDao
 * 
 * CHANGE: Created comprehensive unit tests for BloodRequestsDao class
 * PURPOSE: Implements requirement #7 (Testing) - Test plan and test cases
 * 
 * This test class covers all CRUD operations for BloodRequest entity:
 * - Blood request registration (registerrequest)
 * - Blood request update (updaterequest)
 * - Blood request deletion (deleterequest)
 * - Blood request retrieval by ID (findrequestById)
 * - Retrieve all blood requests (findAllBloodRequest)
 */
package dao;

import model.BloodRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Test class for BloodRequestsDao operations.
 * 
 * CHANGE: Added comprehensive JavaDoc comments following Google Java Style Guide
 * PURPOSE: Document test class purpose and usage
 */
public class BloodRequestsDaoTest {
    
    private BloodRequestsDao bloodRequestsDao;
    private BloodRequest testBloodRequest;
    
    /**
     * CHANGE: Added setUp method with proper initialization
     * PURPOSE: Initialize test objects before each test method execution
     * This follows JUnit best practices for test setup
     */
    @Before
    public void setUp() {
        // CHANGE: Initialize BloodRequestsDao instance before each test
        // PURPOSE: Ensure fresh instance for each test to avoid state pollution
        bloodRequestsDao = new BloodRequestsDao();
        
        // CHANGE: Create test blood request object with sample data
        // PURPOSE: Provide consistent test data for all test methods
        testBloodRequest = new BloodRequest();
        testBloodRequest.setHospital_name("Test Hospital");
        testBloodRequest.setBlood_group("O+");
        testBloodRequest.setQuantity_ml(1000);
        testBloodRequest.setRequest_date(new Timestamp(new Date().getTime()));
        testBloodRequest.setStatus("Pending");
    }
    
    /**
     * CHANGE: Added tearDown method for cleanup
     * PURPOSE: Clean up test data after test execution
     * This ensures test isolation and prevents test interference
     */
    @After
    public void tearDown() {
        // CHANGE: Clean up test blood request if it exists
        // PURPOSE: Maintain clean test database state
        if (testBloodRequest.getRequest_id() > 0) {
            bloodRequestsDao.deleterequest(testBloodRequest);
        }
        bloodRequestsDao = null;
        testBloodRequest = null;
    }
    
    /**
     * Test Case TC_REQUEST_001: Register Blood Request with Valid Data
     * 
     * CHANGE: Created test method for blood request registration
     * PURPOSE: Verify that valid blood request registration works correctly
     * Tests requirement: Blood requests can be successfully registered in the system
     */
    @Test
    public void testRegisterrequest_ValidData_ReturnsGreaterThanZero() {
        // CHANGE: Test blood request registration with valid data
        // PURPOSE: Verify registerrequest() returns value greater than 0
        int result = bloodRequestsDao.registerrequest(testBloodRequest);
        
        // CHANGE: Assert that registration was successful
        // PURPOSE: Validate that the method returns expected result
        assertTrue("Blood request registration should return value > 0", result > 0);
        
        // CHANGE: Retrieve blood request to verify it was saved
        // PURPOSE: Ensure data persistence works correctly
        List<BloodRequest> allRequests = bloodRequestsDao.findAllBloodRequest();
        assertNotNull("Blood request list should not be null", allRequests);
    }
    
    /**
     * Test Case TC_REQUEST_002: Update Blood Request
     * 
     * CHANGE: Created test method for blood request update
     * PURPOSE: Verify that blood request information can be updated
     * Tests requirement: Blood request information can be modified
     */
    @Test
    public void testUpdaterequest_ValidData_ReturnsGreaterThanZero() {
        // CHANGE: Register blood request first
        // PURPOSE: Create blood request to update
        bloodRequestsDao.registerrequest(testBloodRequest);
        
        // CHANGE: Retrieve blood request to get ID
        // PURPOSE: Need request ID for update operation
        List<BloodRequest> allRequests = bloodRequestsDao.findAllBloodRequest();
        BloodRequest registeredRequest = allRequests.get(allRequests.size() - 1);
        testBloodRequest.setRequest_id(registeredRequest.getRequest_id());
        
        // CHANGE: Update blood request information
        // PURPOSE: Test blood request information modification
        testBloodRequest.setHospital_name("Updated Hospital");
        testBloodRequest.setStatus("Fulfilled");
        testBloodRequest.setQuantity_ml(1500);
        int result = bloodRequestsDao.updaterequest(testBloodRequest);
        
        // CHANGE: Verify update succeeded
        // PURPOSE: Validate update operation works correctly
        assertTrue("Blood request update should return value > 0", result > 0);
        
        // CHANGE: Verify updated data
        // PURPOSE: Ensure changes are persisted
        BloodRequest updatedRequest = bloodRequestsDao.findrequestById(testBloodRequest.getRequest_id());
        assertEquals("Hospital name should be updated", "Updated Hospital", updatedRequest.getHospital_name());
        assertEquals("Status should be updated", "Fulfilled", updatedRequest.getStatus());
        assertEquals("Quantity should be updated", 1500, updatedRequest.getQuantity_ml());
    }
    
    /**
     * Test Case TC_REQUEST_003: Delete Blood Request
     * 
     * CHANGE: Created test method for blood request deletion
     * PURPOSE: Verify that blood requests can be deleted
     * Tests requirement: System supports blood request deletion
     */
    @Test
    public void testDeleterequest_ValidId_ReturnsGreaterThanZero() {
        // CHANGE: Register blood request first
        // PURPOSE: Create blood request to delete
        bloodRequestsDao.registerrequest(testBloodRequest);
        
        // CHANGE: Retrieve blood request to get ID
        // PURPOSE: Need request ID for delete operation
        List<BloodRequest> allRequests = bloodRequestsDao.findAllBloodRequest();
        BloodRequest registeredRequest = allRequests.get(allRequests.size() - 1);
        testBloodRequest.setRequest_id(registeredRequest.getRequest_id());
        
        // CHANGE: Delete blood request
        // PURPOSE: Test blood request deletion functionality
        int result = bloodRequestsDao.deleterequest(testBloodRequest);
        
        // CHANGE: Verify deletion succeeded
        // PURPOSE: Validate delete operation works correctly
        assertTrue("Blood request deletion should return value > 0", result > 0);
        
        // CHANGE: Verify blood request no longer exists
        // PURPOSE: Ensure blood request is actually removed from database
        BloodRequest deletedRequest = bloodRequestsDao.findrequestById(testBloodRequest.getRequest_id());
        assertNull("Deleted blood request should not be retrievable", deletedRequest);
    }
    
    /**
     * Test Case TC_REQUEST_004: Find Blood Request By ID
     * 
     * CHANGE: Created test method for blood request retrieval by ID
     * PURPOSE: Verify that blood requests can be retrieved by their ID
     * Tests requirement: System can retrieve blood request information by ID
     */
    @Test
    public void testFindrequestById_ValidId_ReturnsBloodRequest() {
        // CHANGE: Register blood request first
        // PURPOSE: Create blood request to retrieve
        bloodRequestsDao.registerrequest(testBloodRequest);
        
        // CHANGE: Retrieve blood request to get ID
        // PURPOSE: Need request ID for retrieval test
        List<BloodRequest> allRequests = bloodRequestsDao.findAllBloodRequest();
        BloodRequest registeredRequest = allRequests.get(allRequests.size() - 1);
        testBloodRequest.setRequest_id(registeredRequest.getRequest_id());
        
        // CHANGE: Retrieve blood request by ID
        // PURPOSE: Test blood request retrieval by ID functionality
        BloodRequest retrievedRequest = bloodRequestsDao.findrequestById(testBloodRequest.getRequest_id());
        
        // CHANGE: Verify blood request retrieved successfully
        // PURPOSE: Validate retrieval operation works correctly
        assertNotNull("Blood request should be retrieved by ID", retrievedRequest);
        assertEquals("Request ID should match", testBloodRequest.getRequest_id(), retrievedRequest.getRequest_id());
        assertEquals("Hospital name should match", testBloodRequest.getHospital_name(), retrievedRequest.getHospital_name());
        assertEquals("Blood group should match", testBloodRequest.getBlood_group(), retrievedRequest.getBlood_group());
    }
    
    /**
     * CHANGE: Created test method for invalid ID retrieval
     * PURPOSE: Verify that invalid IDs return null
     */
    @Test
    public void testFindrequestById_InvalidId_ReturnsNull() {
        // CHANGE: Attempt to retrieve blood request with invalid ID
        // PURPOSE: Test error handling for non-existent request ID
        BloodRequest retrievedRequest = bloodRequestsDao.findrequestById(-1);
        
        // CHANGE: Verify null is returned for invalid ID
        // PURPOSE: Ensure proper error handling
        assertNull("Invalid blood request ID should return null", retrievedRequest);
    }
    
    /**
     * Test Case TC_REQUEST_005: Find All Blood Requests
     * 
     * CHANGE: Created test method for retrieving all blood requests
     * PURPOSE: Verify that all blood requests can be retrieved
     * Tests requirement: System can list all registered blood requests
     */
    @Test
    public void testFindAllBloodRequest_ReturnsList() {
        // CHANGE: Register a test blood request first
        // PURPOSE: Ensure at least one blood request exists for testing
        bloodRequestsDao.registerrequest(testBloodRequest);
        
        // CHANGE: Retrieve all blood requests
        // PURPOSE: Test retrieval of all blood requests functionality
        List<BloodRequest> allRequests = bloodRequestsDao.findAllBloodRequest();
        
        // CHANGE: Verify list is not null
        // PURPOSE: Validate retrieval operation works correctly
        assertNotNull("Blood request list should not be null", allRequests);
    }
}

