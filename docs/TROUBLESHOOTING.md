## Troubleshooting

### Database connection fails
Symptoms:
- Login/Registration fails
- Dashboard tables do not load
- Exceptions in output related to `org.postgresql.Driver` or connection

Checks:
- **PostgreSQL is running** on `localhost:5432`
- **Database exists**: `blood_donation_management_system_db`
- **Credentials match the code**:
  - user: `postgres`
  - password: `racia123`
- **JDBC driver is on the classpath**
  - NetBeans references a local jar in `nbproject/project.properties`

### `org.postgresql.Driver` not found
Fix:
- Add the PostgreSQL JDBC jar to the project libraries (NetBeans) or to your Java classpath (VS Code/Cursor).

### JUnit errors like `The import org.junit cannot be resolved`
Cause:
- JUnit is missing from your IDE classpath (common in VS Code/Cursor)

Fix:
- Follow `CURSOR_VSCODE_SETUP.md` (recommended) or `JUNIT_SETUP_INSTRUCTIONS.md`

### Main screen is not the Login screen
Note:
- NetBeans main class is configured as `view.Registration` (see `nbproject/project.properties`)
- `view.Main` launches `view.Login`

Fix:
- In NetBeans: Project Properties → Run → set Main Class to `view.Main` if you want to start at Login

### Tests fail unexpectedly
Common causes:
- Database not running
- Tables/columns do not exist or names differ
- Constraints (FK/unique) reject test inserts

Fix:
- Ensure schema matches `docs/DATABASE_SCHEMA.md`


