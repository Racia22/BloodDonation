/*
 * CHANGE: Created UserController class for MVC pattern implementation
 * PURPOSE: Implements requirement #6 (Software Design Pattern) - MVC Pattern
 * 
 * This controller acts as the intermediary between View and DAO layers:
 * - Handles business logic for user operations
 * - Coordinates between UserDao and View components
 * - Provides a clean separation of concerns
 * 
 * MVC Architecture:
 * - Model: User class (data structure)
 * - View: Login.java, Registration.java (UI components)
 * - Controller: UserController.java (business logic and coordination)
 */
package controller;

import dao.UserDao;
import model.User;

/**
 * Controller class for User entity operations following MVC pattern.
 * 
 * CHANGE: Created controller layer to separate business logic from view and data access
 * PURPOSE: Implement MVC design pattern as required
 * 
 * This controller handles:
 * - User registration business logic
 * - User authentication business logic
 * - User information updates
 * - User deletion operations
 * - User retrieval operations
 * 
 * CHANGE: Added comprehensive JavaDoc comments
 * PURPOSE: Document class purpose and usage following Google Java Style Guide
 * 
 * @author RC
 */
public class UserController {
    
    // CHANGE: Added UserDao instance as private field
    // PURPOSE: Encapsulate data access layer dependency
    private UserDao userDao;
    
    /**
     * CHANGE: Added constructor to initialize UserDao
     * PURPOSE: Follow dependency injection pattern for better testability
     */
    public UserController() {
        // CHANGE: Initialize UserDao instance
        // PURPOSE: Create data access object for user operations
        this.userDao = new UserDao();
    }
    
    /**
     * CHANGE: Created controller method for user registration
     * PURPOSE: Separate business logic from view layer
     * 
     * Registers a new user in the system.
     * 
     * CHANGE: Added validation logic before database operation
     * PURPOSE: Implement business rules at controller level
     * 
     * @param user the User object containing user information
     * @return true if registration is successful, false otherwise
     */
    public boolean registerUser(User user) {
        // CHANGE: Added input validation
        // PURPOSE: Validate user data before database operation
        if (user == null) {
            return false;
        }
        
        // CHANGE: Check if email is already registered
        // PURPOSE: Implement business rule - prevent duplicate emails
        if (userDao.isEmailRegistered(user.getEmail())) {
            return false;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return userDao.addUser(user);
    }
    
    /**
     * CHANGE: Created controller method for user authentication
     * PURPOSE: Separate business logic from view layer
     * 
     * Authenticates a user with provided credentials.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate credentials before database operation
     * 
     * @param userName the username for authentication
     * @param password the password for authentication
     * @return User object if authentication succeeds, null otherwise
     */
    public User authenticateUser(String userName, String password) {
        // CHANGE: Added input validation
        // PURPOSE: Validate input parameters before processing
        if (userName == null || userName.trim().isEmpty()) {
            return null;
        }
        
        if (password == null || password.trim().isEmpty()) {
            return null;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return userDao.authenticateUser(userName.trim(), password.trim());
    }
    
    /**
     * CHANGE: Created controller method for email registration check
     * PURPOSE: Separate business logic from view layer
     * 
     * Checks if an email address is already registered.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate email before database operation
     * 
     * @param email the email address to check
     * @return true if email is registered, false otherwise
     */
    public boolean isEmailRegistered(String email) {
        // CHANGE: Added input validation
        // PURPOSE: Validate email parameter before processing
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return userDao.isEmailRegistered(email.trim());
    }
    
    /**
     * CHANGE: Created controller method for user update
     * PURPOSE: Separate business logic from view layer
     * 
     * Updates user information in the system.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate user data before database operation
     * 
     * @param user the User object containing updated information
     * @return true if update is successful, false otherwise
     */
    public boolean updateUser(User user) {
        // CHANGE: Added input validation
        // PURPOSE: Validate user object before database operation
        if (user == null) {
            return false;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return userDao.updateUser(user);
    }
    
    /**
     * CHANGE: Created controller method for user deletion
     * PURPOSE: Separate business logic from view layer
     * 
     * Deletes a user from the system.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate user ID before database operation
     * 
     * @param userId the ID of the user to delete
     * @return true if deletion is successful, false otherwise
     */
    public boolean deleteUser(int userId) {
        // CHANGE: Added input validation
        // PURPOSE: Validate user ID before database operation
        if (userId <= 0) {
            return false;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return userDao.deleteUser(userId);
    }
    
    /**
     * CHANGE: Created controller method for user retrieval by ID
     * PURPOSE: Separate business logic from view layer
     * 
     * Retrieves a user from the system by user ID.
     * 
     * CHANGE: Added input validation
     * PURPOSE: Validate user ID before database operation
     * 
     * @param userId the ID of the user to retrieve
     * @return User object if found, null otherwise
     */
    public User getUserById(int userId) {
        // CHANGE: Added input validation
        // PURPOSE: Validate user ID before database operation
        if (userId <= 0) {
            return null;
        }
        
        // CHANGE: Delegate to DAO layer for database operation
        // PURPOSE: Maintain separation of concerns
        return userDao.getUserById(userId);
    }
}

