## Project overview

### Project name
**Blood Donation Management System**

### Problem statement
Hospitals and blood banks need a simple way to **register donors**, record **donations**, manage **donation sites**, and track **blood requests**. Manual record-keeping can be slow and error-prone. This system provides a desktop UI to manage these records with a PostgreSQL database.

### Main features
- **Authentication**
  - **Register** a user account
  - **Login** with username + password
- **Donors**
  - Register donor details (name, phone, blood group, location/province)
  - Update / delete / search by ID
  - Display all donors
- **Sites**
  - Register donation site (name, phone, doctor name, location)
  - Update / delete / search by ID
  - Display all sites
- **Donations**
  - Register donation (donor ID, site ID, blood group, quantity in ml)
  - Update / delete / search by ID
  - Display all donations
- **Blood requests**
  - Register request (hospital name, blood group, quantity, request date, status)
  - Update / delete / search by ID
  - Display all blood requests

### Users of the system
- **System operator / admin user** using the desktop application to manage the records

### Key packages
- **`model`**: data objects (POJOs)
- **`dao`**: database operations (JDBC)
- **`view`**: Swing UI screens
- **`controller`**: controller layer (MVC pattern)


