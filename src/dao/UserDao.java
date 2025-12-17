/*
 * CHANGE: Refactored UserDao class to follow Google Java Style Guide
 * PURPOSE: Implements requirement #5 (Coding Practices) - Google's coding standards
 * 
 * Changes made:
 * 1. Added comprehensive JavaDoc comments for class and methods
 * 2. Fixed method naming consistency (camelCase)
 * 3. Improved error handling with try-with-resources
 * 4. Fixed spacing and formatting according to Google Style Guide
 * 5. Added proper resource management (closing connections)
 * 6. Improved code readability and maintainability
 * 
 * NOTE: Connection strings kept as-is per requirement
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 * Data Access Object for User entity operations.
 * 
 * CHANGE: Added comprehensive class-level JavaDoc
 * PURPOSE: Document class purpose and usage following Google Java Style Guide
 * 
 * This class handles all database operations related to User entity including:
 * - User registration
 * - User authentication
 * - User information updates
 * - User deletion
 * - User retrieval operations
 * 
 * @author RC
 */
public class UserDao {
    
    // CHANGE: Connection strings kept as-is per requirement
    // PURPOSE: Maintain existing database configuration
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/blood_donation_management_system_db";
    private String username = "postgres";
    private String password = "racia123";
    
    /**
     * CHANGE: Added JavaDoc comment for method
     * PURPOSE: Document method purpose, parameters, and return value
     * 
     * Registers a new user in the database.
     * 
     * CHANGE: Improved error handling with try-with-resources
     * PURPOSE: Ensure proper resource management and connection closing
     * 
     * CHANGE: Fixed spacing and formatting
     * PURPOSE: Follow Google Java Style Guide formatting rules
     * 
     * @param userObj the User object containing user information to register
     * @return true if user registration is successful, false otherwise
     */
    public boolean addUser(User userObj) {
        // CHANGE: Added duplicate-email guard.
        // PURPOSE: Make DAO behavior deterministic even if the DB does not enforce UNIQUE(email),
        // and ensure tests expecting duplicate-email registration to fail pass consistently.
        if (userObj == null || userObj.getEmail() == null) {
            return false;
        }
        if (isEmailRegistered(userObj.getEmail())) {
            return false;
        }

        // CHANGE: Used try-with-resources for proper resource management
        // PURPOSE: Automatically close connection and prepared statement
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            // CHANGE: Fixed SQL query formatting
            // PURPOSE: Improve code readability
            String sql = "INSERT INTO users (user_name, email, password) VALUES (?, ?, ?)";

            // CHANGE: Added fallback insert that includes user_id.
            // PURPOSE: Some database schemas define user_id as NOT NULL without a DEFAULT/SERIAL.
            // In that case, inserting without user_id fails. This fallback makes addUser() work
            // across both schema styles (auto-generated IDs and manual IDs).
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, userObj.getUsername());
                pst.setString(2, userObj.getEmail());
                pst.setString(3, userObj.getPassword());

                int rowAffected = pst.executeUpdate();
                return rowAffected > 0;
            } catch (SQLException firstInsertEx) {
                // CHANGE: Retry insert with generated user_id when user_id is required.
                // PURPOSE: Make registration work even when user_id is not auto-generated.
                if (isUserIdRequired(firstInsertEx)) {
                    int nextUserId = getNextUserId(con);
                    String sqlWithId =
                        "INSERT INTO users (user_id, user_name, email, password) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement pst2 = con.prepareStatement(sqlWithId)) {
                        pst2.setInt(1, nextUserId);
                        pst2.setString(2, userObj.getUsername());
                        pst2.setString(3, userObj.getEmail());
                        pst2.setString(4, userObj.getPassword());
                        int rowAffected = pst2.executeUpdate();
                        return rowAffected > 0;
                    }
                }

                // If it's not the user_id-not-generated issue, rethrow so the outer catch logs it.
                throw firstInsertEx;
            }
        } catch (SQLException ex) {
            // CHANGE: Changed from generic Exception to SQLException
            // PURPOSE: Catch specific exception type for better error handling
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * CHANGE: Added helper to detect whether insert failed because user_id is required.
     * PURPOSE: Support databases where user_id is NOT NULL and not auto-generated.
     */
    private boolean isUserIdRequired(SQLException ex) {
        // PostgreSQL NOT NULL violation SQLSTATE is 23502.
        // We also check the message for user_id to be safe across JDBC driver variations.
        String sqlState = ex.getSQLState();
        String msg = ex.getMessage() == null ? "" : ex.getMessage().toLowerCase();
        return "23502".equals(sqlState) && msg.contains("user_id");
    }

    /**
     * CHANGE: Added helper to compute the next user_id value.
     * PURPOSE: Allow manual ID assignment when the schema does not auto-generate user_id.
     */
    private int getNextUserId(Connection con) throws SQLException {
        String sql = "SELECT COALESCE(MAX(user_id), 0) + 1 AS next_id FROM users";
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("next_id");
            }
        }
        // Fallback if query returns no rows (should not happen).
        return 1;
    }
    
    /**
     * CHANGE: Added JavaDoc comment for method
     * PURPOSE: Document method purpose, parameters, and return value
     * 
     * CHANGE: Fixed method parameter naming (camelCase)
     * PURPOSE: Follow Google Java Style Guide naming conventions
     * 
     * Authenticates a user with provided username and password.
     * 
     * CHANGE: Improved error handling and resource management
     * PURPOSE: Ensure proper connection and statement closing
     * 
     * @param userName the username for authentication
     * @param password the password for authentication
     * @return User object if authentication succeeds, null otherwise
     */
    public User authenticateUser(String userName, String password) {
        // CHANGE: Used try-with-resources for proper resource management
        // PURPOSE: Automatically close connection and prepared statement
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, this.password)) {
            String sql = "SELECT * FROM users WHERE user_name = ? AND password = ?";
            
            // CHANGE: Used try-with-resources for PreparedStatement
            // PURPOSE: Ensure statement is properly closed
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, userName);
                pst.setString(2, password);
                
                // CHANGE: Used try-with-resources for ResultSet
                // PURPOSE: Ensure result set is properly closed
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        // CHANGE: Improved code formatting and spacing
                        // PURPOSE: Follow Google Java Style Guide formatting rules
                        User user = new User(
                            rs.getInt("user_id"),
                            // CHANGE: Fixed parameter order to match User constructor: (id, email, username, password).
                            // PURPOSE: Prevent swapped username/email values and fix UserDaoTest assertions.
                            rs.getString("email"),
                            rs.getString("user_name"),
                            rs.getString("password")
                        );
                        return user;
                    }
                }
            }
        } catch (SQLException ex) {
            // CHANGE: Changed from generic Exception to SQLException
            // PURPOSE: Catch specific exception type for better error handling
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * CHANGE: Added JavaDoc comment for method
     * PURPOSE: Document method purpose, parameters, and return value
     * 
     * Checks if an email address is already registered in the system.
     * 
     * CHANGE: Improved error handling with try-with-resources
     * PURPOSE: Ensure proper resource management and connection closing
     * 
     * CHANGE: Fixed SQL query variable naming (camelCase)
     * PURPOSE: Follow Google Java Style Guide naming conventions
     * 
     * @param email the email address to check
     * @return true if email is registered, false otherwise
     */
    public boolean isEmailRegistered(String email) {
        // CHANGE: Used try-with-resources for proper resource management
        // PURPOSE: Automatically close connection and prepared statement
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            // CHANGE: Fixed variable naming (camelCase)
            // PURPOSE: Follow Google Java Style Guide naming conventions
            String sql = "SELECT user_id FROM users WHERE email = ?";
            
            // CHANGE: Used try-with-resources for PreparedStatement
            // PURPOSE: Ensure statement is properly closed
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, email);
                
                // CHANGE: Used try-with-resources for ResultSet
                // PURPOSE: Ensure result set is properly closed
                try (ResultSet rs = pst.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException ex) {
            // CHANGE: Changed from generic Exception to SQLException
            // PURPOSE: Catch specific exception type for better error handling
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * CHANGE: Added JavaDoc comment for method
     * PURPOSE: Document method purpose, parameters, and return value
     * 
     * Updates user information in the database.
     * 
     * CHANGE: Improved error handling with try-with-resources
     * PURPOSE: Ensure proper resource management and connection closing
     * 
     * CHANGE: Fixed SQL query variable naming and formatting
     * PURPOSE: Follow Google Java Style Guide naming and formatting conventions
     * 
     * NOTE: This method updates all users - consider adding WHERE clause with user_id
     * 
     * @param userObj the User object containing updated user information
     * @return true if update is successful, false otherwise
     */
    public boolean updateUser(User userObj) {
        // CHANGE: Used try-with-resources for proper resource management
        // PURPOSE: Automatically close connection and prepared statement
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            // CHANGE: Fixed variable naming (camelCase) and SQL formatting
            // PURPOSE: Follow Google Java Style Guide naming and formatting conventions
            // CHANGE: Added WHERE clause to update only the target user by ID.
            // PURPOSE: Prevent accidentally updating all users in the table; also makes tests and behavior correct.
            String sql = "UPDATE users SET user_name = ?, email = ?, password = ? WHERE user_id = ?";
            
            // CHANGE: Used try-with-resources for PreparedStatement
            // PURPOSE: Ensure statement is properly closed
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, userObj.getUsername());
                pst.setString(2, userObj.getEmail());
                pst.setString(3, userObj.getPassword());
                pst.setInt(4, userObj.getUser_id());
                
                // CHANGE: Fixed spacing around operators
                // PURPOSE: Follow Google Java Style Guide spacing rules
                return pst.executeUpdate() > 0;
            }
        } catch (SQLException ex) {
            // CHANGE: Changed from generic Exception to SQLException
            // PURPOSE: Catch specific exception type for better error handling
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * CHANGE: Added JavaDoc comment for method
     * PURPOSE: Document method purpose, parameters, and return value
     * 
     * CHANGE: Fixed method parameter naming (camelCase)
     * PURPOSE: Follow Google Java Style Guide naming conventions
     * 
     * Deletes a user from the database by user ID.
     * 
     * CHANGE: Improved error handling with try-with-resources
     * PURPOSE: Ensure proper resource management and connection closing
     * 
     * CHANGE: Fixed SQL query variable naming and formatting
     * PURPOSE: Follow Google Java Style Guide naming and formatting conventions
     * 
     * @param userId the ID of the user to delete
     * @return true if deletion is successful, false otherwise
     */
    public boolean deleteUser(int userId) {
        // CHANGE: Used try-with-resources for proper resource management
        // PURPOSE: Automatically close connection and prepared statement
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            // CHANGE: Fixed variable naming (camelCase) and SQL formatting
            // PURPOSE: Follow Google Java Style Guide naming and formatting conventions
            String sql = "DELETE FROM users WHERE user_id = ?";
            
            // CHANGE: Used try-with-resources for PreparedStatement
            // PURPOSE: Ensure statement is properly closed
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, userId);
                
                // CHANGE: Fixed spacing around operators
                // PURPOSE: Follow Google Java Style Guide spacing rules
                int rowsAffected = pst.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            // CHANGE: Changed from generic Exception to SQLException
            // PURPOSE: Catch specific exception type for better error handling
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * CHANGE: Added JavaDoc comment for method
     * PURPOSE: Document method purpose, parameters, and return value
     * 
     * CHANGE: Fixed method parameter naming (camelCase)
     * PURPOSE: Follow Google Java Style Guide naming conventions
     * 
     * Retrieves a user from the database by user ID.
     * 
     * CHANGE: Improved error handling with try-with-resources
     * PURPOSE: Ensure proper resource management and connection closing
     * 
     * CHANGE: Fixed SQL query variable naming and formatting
     * PURPOSE: Follow Google Java Style Guide naming and formatting conventions
     * 
     * @param userId the ID of the user to retrieve
     * @return User object if found, null otherwise
     */
    public User getUserById(int userId) {
        // CHANGE: Used try-with-resources for proper resource management
        // PURPOSE: Automatically close connection and prepared statement
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            // CHANGE: Fixed variable naming (camelCase) and SQL formatting
            // PURPOSE: Follow Google Java Style Guide naming and formatting conventions
            String sql = "SELECT * FROM users WHERE user_id = ?";
            
            // CHANGE: Used try-with-resources for PreparedStatement
            // PURPOSE: Ensure statement is properly closed
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, userId);
                
                // CHANGE: Used try-with-resources for ResultSet
                // PURPOSE: Ensure result set is properly closed
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        // CHANGE: Improved code formatting
                        // PURPOSE: Follow Google Java Style Guide formatting rules
                        User user = new User();
                        user.setUser_id(rs.getInt("user_id"));
                        user.setUsername(rs.getString("user_name"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                }
            }
        } catch (SQLException ex) {
            // CHANGE: Changed from generic Exception to SQLException
            // PURPOSE: Catch specific exception type for better error handling
            ex.printStackTrace();
        }
        return null;
    }
}
