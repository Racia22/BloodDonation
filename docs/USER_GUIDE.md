## User guide

### Screens in the application
- **Registration (`view.Registration`)**
  - Create a new account (username, email, password)
  - Basic validation is performed (email format, password length)
- **Login (`view.Login`)**
  - Login with username + password
  - On success, opens **Dashboard**
- **Dashboard (`view.Dashboard`)**
  - Main screen with navigation (uses `CardLayout`)
  - Allows managing donors, sites, donations, and blood requests

### Typical usage flow

#### 1) Create an account
- Open the app
- Click **Register**
- Enter username, email, and password
- Click **Register** to create the account

#### 2) Login
- Enter username and password
- Click **Login**
- If credentials match a database row in `users`, you are logged in and the Dashboard opens

#### 3) Manage donors
From the Dashboard:
- **Register Donor**: enter donor details and save
- **Update Donor**: search donor by ID, edit fields, update
- **Delete Donor**: search donor by ID and delete
- **Display All Donors**: loads all donors into the table

Donor fields:
- **Names**
- **Blood group** (combo options: A+, A-, B+, B-, AB+, AB-, O+, O-)
- **Phone number**
- **Province / location**

#### 4) Manage sites
From the Dashboard:
- **Register Site**
- **Update Site**
- **Delete Site**
- **Display All Sites**

Site fields:
- **Site name**
- **Phone number**
- **Doctor name**
- **Location**

#### 5) Manage donations
From the Dashboard:
- **Register Donation**
- **Update Donation**
- **Delete Donation**
- **Display All Donations**

Donation fields:
- **Donor ID**
- **Site ID**
- **Blood group**
- **Quantity (ml)**

#### 6) Manage blood requests
From the Dashboard:
- **Register Request**
- **Update Request**
- **Delete Request**
- **Display All Requests**

Request fields:
- **Hospital name**
- **Blood group**
- **Quantity (ml)**
- **Request date** (timestamp)
- **Status** (e.g., Pending/Fulfilled)

### Notes
- Most search operations are **by numeric ID**.
- Many screens include buttons for **SEARCH / UPDATE / DELETE / DISPLAY ALL**.
- The application relies on the PostgreSQL database being available.


