## Testing guide (JUnit)

### What exists in this project
- **Testing plan**: `TESTING_PLAN.md`
- **JUnit tests**: `test/`
  - `test/dao/UserDaoTest.java`
  - `test/dao/DonorsDaoTest.java`
  - `test/dao/DonationsDaoTest.java`
  - `test/dao/SitesDaoTest.java`
  - `test/dao/BloodRequestsDaoTest.java`
  - `test/con/ProjConnectionTest.java`

### Important note about these tests
These tests are **integration-style tests** because:
- the DAOs connect to a real database using JDBC
- tests will fail if PostgreSQL is not running or schema is missing

### Running tests in NetBeans
NetBeans project properties already reference:
- `${libs.junit_4.classpath}`

Steps:
1. Open the project in NetBeans
2. Ensure PostgreSQL is running and schema exists
3. Right-click the project → **Test**
4. Or right-click a test file → **Test File**

### Running tests in Cursor / VS Code
Cursor/VS Code needs JUnit jars on the Java extension classpath.

- Recommended: follow `CURSOR_VSCODE_SETUP.md`
- General guide: `JUNIT_SETUP_INSTRUCTIONS.md`

### Testing checklist
- **DB running**: PostgreSQL service is up
- **DB exists**: `blood_donation_management_system_db`
- **Schema exists**: required tables/columns exist (see `docs/DATABASE_SCHEMA.md`)
- **Credentials match**: code uses a hardcoded username/password


