/*
 * JUnit Test Class for UserDao
 * 
 * CHANGE: Created comprehensive unit tests for UserDao class
 * PURPOSE: Implements requirement #7 (Testing) - Test plan and test cases
 * 
 * This test class covers all CRUD operations for User entity:
 * - User registration (addUser)
 * - User authentication (authenticateUser)
 * - Email registration check (isEmailRegistered)
 * - User update (updateUser)
 * - User deletion (deleteUser)
 * - User retrieval by ID (getUserById)
 */
package dao;

import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import java.util.UUID;

/**
 * Test class for UserDao operations.
 * 
 * CHANGE: Added comprehensive JavaDoc comments following Google Java Style Guide
 * PURPOSE: Document test class purpose and usage
 */
public class UserDaoTest {
    
    private UserDao userDao;
    private User testUser;
    // CHANGE: Track the username that should be used for cleanup.
    // PURPOSE: Some tests update username; cleanup must use the latest value.
    private String currentUsername;
    
    /**
     * CHANGE: Added setUp method with proper initialization
     * PURPOSE: Initialize test objects before each test method execution
     * This follows JUnit best practices for test setup
     */
    @Before
    public void setUp() {
        // CHANGE: Initialize UserDao instance before each test
        // PURPOSE: Ensure fresh instance for each test to avoid state pollution
        userDao = new UserDao();
        
        // CHANGE: Create test user object with sample data
        // PURPOSE: Provide consistent test data for all test methods
        testUser = new User();
        // CHANGE: Use unique username/email per test run to avoid conflicts with existing DB data.
        // PURPOSE: Make tests deterministic even if the database already contains older test rows.
        // CHANGE: Use UUID instead of currentTimeMillis for uniqueness.
        // PURPOSE: Prevent occasional collisions when multiple tests start within the same millisecond.
        String unique = UUID.randomUUID().toString().replace("-", "");
        testUser.setUsername("testuser_" + unique);
        testUser.setEmail("testuser_" + unique + "@example.com");
        testUser.setPassword("testpass123");
        currentUsername = testUser.getUsername();
    }
    
    /**
     * CHANGE: Added tearDown method for cleanup
     * PURPOSE: Clean up test data after test execution
     * This ensures test isolation and prevents test interference
     */
    @After
    public void tearDown() {
        // CHANGE: Clean up test user if it exists
        // PURPOSE: Maintain clean test database state
        // CHANGE: Cleanup by email (not only by user_id).
        // PURPOSE: addUser() does not populate testUser.user_id; this avoids leaving test rows behind.
        try {
            // Best-effort cleanup: find the user by authenticating and delete by ID if found.
            User created = userDao.authenticateUser(currentUsername, testUser.getPassword());
            if (created != null) {
                userDao.deleteUser(created.getUser_id());
            }
        } catch (Exception ignored) {
            // Ignore cleanup errors
        }
        userDao = null;
        testUser = null;
    }
    
    /**
     * Test Case TC_USER_001: User Registration with Valid Data
     * 
     * CHANGE: Created test method for user registration
     * PURPOSE: Verify that valid user registration works correctly
     * Tests requirement: User can be successfully registered in the system
     */
    @Test
    public void testAddUser_ValidData_ReturnsTrue() {
        // CHANGE: Test user registration with valid data
        // PURPOSE: Verify addUser() returns true for valid input
        boolean result = userDao.addUser(testUser);
        
        // CHANGE: Assert that registration was successful
        // PURPOSE: Validate that the method returns expected result
        assertTrue("User registration should succeed with valid data", result);
    }
    
    /**
     * Test Case TC_USER_002: User Registration with Duplicate Email
     * 
     * CHANGE: Created test method for duplicate email handling
     * PURPOSE: Verify that duplicate email registration is prevented
     * Tests requirement: System should prevent duplicate email registration
     */
    @Test
    public void testAddUser_DuplicateEmail_ReturnsFalse() {
        // CHANGE: First register a user
        // PURPOSE: Create initial user for duplicate test
        assertTrue("Initial user registration must succeed for duplicate-email test", userDao.addUser(testUser));
        
        // CHANGE: Attempt to register another user with same email
        // PURPOSE: Test duplicate email detection
        User duplicateUser = new User();
        duplicateUser.setUsername("differentuser");
        duplicateUser.setEmail(testUser.getEmail()); // Same email
        duplicateUser.setPassword("password123");
        
        // CHANGE: Verify duplicate registration fails
        // PURPOSE: Ensure database constraint enforcement
        boolean result = userDao.addUser(duplicateUser);
        assertFalse("Duplicate email registration should fail", result);
    }
    
    /**
     * Test Case TC_USER_003: User Authentication with Valid Credentials
     * 
     * CHANGE: Created test method for valid authentication
     * PURPOSE: Verify that valid credentials authenticate successfully
     * Tests requirement: Users can login with correct credentials
     */
    @Test
    public void testAuthenticateUser_ValidCredentials_ReturnsUser() {
        // CHANGE: Register user first
        // PURPOSE: Create user account for authentication test
        assertTrue("User registration must succeed before authentication test", userDao.addUser(testUser));
        
        // CHANGE: Attempt authentication with valid credentials
        // PURPOSE: Test successful login scenario
        User authenticatedUser = userDao.authenticateUser(
            testUser.getUsername(), 
            testUser.getPassword()
        );
        
        // CHANGE: Verify authentication succeeded
        // PURPOSE: Validate that correct credentials return user object
        assertNotNull("Authentication should succeed with valid credentials", authenticatedUser);
        assertEquals("Username should match", testUser.getUsername(), authenticatedUser.getUsername());
        assertEquals("Email should match", testUser.getEmail(), authenticatedUser.getEmail());
    }
    
    /**
     * Test Case TC_USER_004: User Authentication with Invalid Credentials
     * 
     * CHANGE: Created test method for invalid authentication
     * PURPOSE: Verify that invalid credentials are rejected
     * Tests requirement: System should reject incorrect login attempts
     */
    @Test
    public void testAuthenticateUser_InvalidCredentials_ReturnsNull() {
        // CHANGE: Attempt authentication with invalid credentials
        // PURPOSE: Test failed login scenario
        User authenticatedUser = userDao.authenticateUser("nonexistent", "wrongpassword");
        
        // CHANGE: Verify authentication failed
        // PURPOSE: Ensure invalid credentials return null
        assertNull("Authentication should fail with invalid credentials", authenticatedUser);
    }
    
    /**
     * Test Case TC_USER_005: Check Email Registration Status
     * 
     * CHANGE: Created test method for email registration check
     * PURPOSE: Verify email existence checking works correctly
     * Tests requirement: System can check if email is already registered
     */
    @Test
    public void testIsEmailRegistered_ExistingEmail_ReturnsTrue() {
        // CHANGE: Register user first
        // PURPOSE: Create user with known email for testing
        assertTrue("User registration must succeed before email-registered test", userDao.addUser(testUser));
        
        // CHANGE: Check if email is registered
        // PURPOSE: Test email existence verification
        boolean isRegistered = userDao.isEmailRegistered(testUser.getEmail());
        
        // CHANGE: Verify email is found
        // PURPOSE: Validate email check functionality
        assertTrue("Existing email should return true", isRegistered);
    }
    
    /**
     * CHANGE: Created test method for non-existent email check
     * PURPOSE: Verify that unregistered emails return false
     */
    @Test
    public void testIsEmailRegistered_NonExistentEmail_ReturnsFalse() {
        // CHANGE: Check non-existent email
        // PURPOSE: Test email check for unregistered email
        boolean isRegistered = userDao.isEmailRegistered("nonexistent@example.com");
        
        // CHANGE: Verify email is not found
        // PURPOSE: Ensure unregistered emails return false
        assertFalse("Non-existent email should return false", isRegistered);
    }
    
    /**
     * Test Case TC_USER_006: Update User Information
     * 
     * CHANGE: Created test method for user update
     * PURPOSE: Verify that user information can be updated
     * Tests requirement: Users can update their profile information
     */
    @Test
    public void testUpdateUser_ValidData_ReturnsTrue() {
        // CHANGE: Register user first
        // PURPOSE: Create user account to update
        assertTrue("User registration must succeed before update test", userDao.addUser(testUser));
        
        // CHANGE: Retrieve user to get ID
        // PURPOSE: Need user ID for update operation
        User retrievedUser = userDao.authenticateUser(
            testUser.getUsername(), 
            testUser.getPassword()
        );
        assertNotNull("User must be retrievable before update", retrievedUser);
        
        // CHANGE: Update user information
        // PURPOSE: Test user information modification
        // CHANGE: Use unique updated username/email to avoid collisions with existing DB data.
        // PURPOSE: Prevent UNIQUE(email) constraint failures when tests run multiple times.
        String unique = UUID.randomUUID().toString().replace("-", "");
        retrievedUser.setUsername("updateduser_" + unique);
        retrievedUser.setEmail("updated_" + unique + "@example.com");
        boolean result = userDao.updateUser(retrievedUser);
        // CHANGE: Update currentUsername for cleanup.
        // PURPOSE: Ensure tearDown deletes the updated user row.
        currentUsername = retrievedUser.getUsername();
        
        // CHANGE: Verify update succeeded
        // PURPOSE: Validate update operation works correctly
        assertTrue("User update should succeed", result);
    }
    
    /**
     * Test Case TC_USER_007: Delete User
     * 
     * CHANGE: Created test method for user deletion
     * PURPOSE: Verify that users can be deleted
     * Tests requirement: System supports user account deletion
     */
    @Test
    public void testDeleteUser_ValidId_ReturnsTrue() {
        // CHANGE: Register user first
        // PURPOSE: Create user account to delete
        assertTrue("User registration must succeed before delete test", userDao.addUser(testUser));
        
        // CHANGE: Retrieve user to get ID
        // PURPOSE: Need user ID for delete operation
        User retrievedUser = userDao.authenticateUser(
            testUser.getUsername(), 
            testUser.getPassword()
        );
        assertNotNull("User must be retrievable before delete", retrievedUser);
        
        // CHANGE: Delete user
        // PURPOSE: Test user deletion functionality
        boolean result = userDao.deleteUser(retrievedUser.getUser_id());
        
        // CHANGE: Verify deletion succeeded
        // PURPOSE: Validate delete operation works correctly
        assertTrue("User deletion should succeed", result);
        
        // CHANGE: Verify user no longer exists
        // PURPOSE: Ensure user is actually removed from database
        User deletedUser = userDao.getUserById(retrievedUser.getUser_id());
        assertNull("Deleted user should not be retrievable", deletedUser);
    }
    
    /**
     * Test Case TC_USER_008: Get User By ID
     * 
     * CHANGE: Created test method for user retrieval by ID
     * PURPOSE: Verify that users can be retrieved by their ID
     * Tests requirement: System can retrieve user information by ID
     */
    @Test
    public void testGetUserById_ValidId_ReturnsUser() {
        // CHANGE: Register user first
        // PURPOSE: Create user account to retrieve
        assertTrue("User registration must succeed before getUserById test", userDao.addUser(testUser));
        
        // CHANGE: Retrieve user by authentication to get ID
        // PURPOSE: Need user ID for retrieval test
        User authenticatedUser = userDao.authenticateUser(
            testUser.getUsername(), 
            testUser.getPassword()
        );
        assertNotNull("User must be retrievable before getUserById", authenticatedUser);
        
        // CHANGE: Retrieve user by ID
        // PURPOSE: Test user retrieval by ID functionality
        User retrievedUser = userDao.getUserById(authenticatedUser.getUser_id());
        
        // CHANGE: Verify user retrieved successfully
        // PURPOSE: Validate retrieval operation works correctly
        assertNotNull("User should be retrieved by ID", retrievedUser);
        assertEquals("User ID should match", authenticatedUser.getUser_id(), retrievedUser.getUser_id());
        assertEquals("Username should match", authenticatedUser.getUsername(), retrievedUser.getUsername());
        assertEquals("Email should match", authenticatedUser.getEmail(), retrievedUser.getEmail());
    }
    
    /**
     * CHANGE: Created test method for invalid ID retrieval
     * PURPOSE: Verify that invalid IDs return null
     */
    @Test
    public void testGetUserById_InvalidId_ReturnsNull() {
        // CHANGE: Attempt to retrieve user with invalid ID
        // PURPOSE: Test error handling for non-existent user ID
        User retrievedUser = userDao.getUserById(-1);
        
        // CHANGE: Verify null is returned for invalid ID
        // PURPOSE: Ensure proper error handling
        assertNull("Invalid user ID should return null", retrievedUser);
    }
}

