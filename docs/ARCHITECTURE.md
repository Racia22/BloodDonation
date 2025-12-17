## Architecture

### High-level architecture
This project follows a layered architecture:

- **View layer (`src/view/`)**: Swing screens (UI)
- **Controller layer (`src/controller/`)**: business logic + validation (MVC controller)
- **DAO layer (`src/dao/`)**: JDBC database access
- **Model layer (`src/model/`)**: POJOs representing data rows
- **Database**: PostgreSQL (`blood_donation_management_system_db`)

### Design pattern used: MVC
The project implements **MVC (Model-View-Controller)**:

- **Model**: `User`, `Donors`, `Sites`, `Donations`, `BloodRequest`
- **View**: `Login`, `Registration`, `Dashboard`
- **Controller**: `UserController`, `DonorsController`, `SitesController`, `DonationsController`, `BloodRequestsController`

For a deeper explanation of the MVC implementation see: `MVC_PATTERN_DOCUMENTATION.md`.

### Data access pattern: DAO
Database operations are isolated in DAO classes:

- `UserDao`
- `DonorsDao`
- `SitesDao`
- `DonationsDao`
- `BloodRequestsDao`

Each DAO uses JDBC to execute SQL queries and map results into model objects.

### Main UI flow
- App starts (either `view.Main` or NetBeans main class `view.Registration`)
- User registers or logs in
- On successful login, `Dashboard` opens and provides navigation through different management screens (implemented via `CardLayout`)

### Dependencies
Configured in NetBeans project properties (`nbproject/project.properties`):
- PostgreSQL JDBC driver (example path in your machine)
- `KGradientPanel (1).jar` (UI component library)
- `libs.junit_4.classpath` (JUnit 4 in NetBeans)


