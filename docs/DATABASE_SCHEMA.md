## Database schema (PostgreSQL)

### Database name (as used in code)
The application connects to:
- **Database**: `blood_donation_management_system_db`
- **Host**: `localhost`
- **Port**: `5432`

The connection string and credentials are currently hardcoded in:
- `src/con/ProjConnection.java`
- DAO classes under `src/dao/`

### Tables used by the application
The DAOs show the application expects these tables:
- `users`
- `donors`
- `sites`
- `donations`
- `blood_requests`

### Expected columns (derived from DAO SQL)

#### `users`
- `user_id` (int, primary key)
- `user_name` (text/varchar)
- `email` (text/varchar)
- `password` (text/varchar)

Used by:
- `UserDao.addUser()` inserts (`user_name`, `email`, `password`)
- `UserDao.authenticateUser()` selects by (`user_name`, `password`)

#### `donors`
- `donor_id` (int, primary key)
- `donor_names` (text/varchar)
- `phone` (text/varchar)
- `blood_group` (text/varchar)
- `donor_location` (text/varchar)

Used by:
- `DonorsDao.registerDonors()`, `DonorsUpdate()`, `deleteDonors()`, `findDonorsbyID()`, `findAllDonors()`

#### `sites`
- `site_id` (int, primary key)
- `site_names` (text/varchar)
- `site_phonenumber` (text/varchar)
- `doctors_name` (text/varchar)
- `site_location` (text/varchar)

Used by:
- `SitesDao.registerSites()`, `updateSites()`, `deleteSites()`, `findSiteByID()`, `findAllSites()`

#### `donations`
- `donation_id` (int, primary key)
- `donor_id` (int, FK to `donors.donor_id`)
- `site_id` (int, FK to `sites.site_id`)
- `blood_group` (text/varchar)
- `quantity_ml` (int)
- `donation_date` (timestamp) — DAO reads it on select

Used by:
- `DonationsDao.registerDonations()`, `DonationsUpdate()`, `deleteDonations()`, `findDonationsbyID()`, `findAllDonations()`

#### `blood_requests`
- `request_id` (int, primary key)
- `hospital_name` (text/varchar)
- `blood_group` (text/varchar)
- `quantity_ml` (int)
- `request_date` (timestamp)
- `status` (text/varchar)

Used by:
- `BloodRequestsDao.registerrequest()`, `updaterequest()`, `deleterequest()`, `findrequestById()`, `findAllBloodRequest()`

### Example DDL (create tables)
This is a **recommended starter schema** matching the column names used in the code.

```sql
-- Create database (run once)
-- CREATE DATABASE blood_donation_management_system_db;

-- Users
CREATE TABLE IF NOT EXISTS users (
  user_id      SERIAL PRIMARY KEY,
  user_name    VARCHAR(100) NOT NULL,
  email        VARCHAR(150) NOT NULL UNIQUE,
  password     VARCHAR(255) NOT NULL
);

-- Donors
CREATE TABLE IF NOT EXISTS donors (
  donor_id        SERIAL PRIMARY KEY,
  donor_names     VARCHAR(150) NOT NULL,
  phone           VARCHAR(30) NOT NULL,
  blood_group     VARCHAR(5) NOT NULL,
  donor_location  VARCHAR(150) NOT NULL
);

-- Sites
CREATE TABLE IF NOT EXISTS sites (
  site_id          SERIAL PRIMARY KEY,
  site_names       VARCHAR(150) NOT NULL,
  site_phonenumber VARCHAR(30) NOT NULL,
  doctors_name     VARCHAR(150) NOT NULL,
  site_location    VARCHAR(150) NOT NULL
);

-- Donations
CREATE TABLE IF NOT EXISTS donations (
  donation_id   SERIAL PRIMARY KEY,
  donor_id      INT NOT NULL REFERENCES donors(donor_id),
  site_id       INT NOT NULL REFERENCES sites(site_id),
  blood_group   VARCHAR(5) NOT NULL,
  quantity_ml   INT NOT NULL CHECK (quantity_ml > 0),
  donation_date TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Blood requests
CREATE TABLE IF NOT EXISTS blood_requests (
  request_id    SERIAL PRIMARY KEY,
  hospital_name VARCHAR(200) NOT NULL,
  blood_group   VARCHAR(5) NOT NULL,
  quantity_ml   INT NOT NULL CHECK (quantity_ml > 0),
  request_date  TIMESTAMP NOT NULL DEFAULT NOW(),
  status        VARCHAR(50) NOT NULL DEFAULT 'Pending'
);
```

### Notes
- **Column names must match exactly** (the code uses them in `ResultSet.getString("...")`).
- The application currently uses **plain text passwords** in the database.


