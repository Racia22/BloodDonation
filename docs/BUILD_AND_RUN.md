## Build and run

### Build system
This is a **NetBeans/Ant** Java project:
- Build script: `build.xml`
- NetBeans config: `nbproject/`

### Main class (important)
NetBeans project configuration currently sets:
- **`main.class=view.Registration`** (see `nbproject/project.properties`)

However, the repository also includes:
- `view.Main` which launches `view.Login`

### Run with NetBeans (recommended)
1. Open `BloodDonationManagementSystembest/` in NetBeans
2. Ensure PostgreSQL is running and schema exists (see `docs/DATABASE_SCHEMA.md`)
3. Ensure the PostgreSQL JDBC driver is available (NetBeans references a jar in `nbproject/project.properties`)
4. Click **Run**

### Build JAR (NetBeans)
1. Right-click project → **Clean and Build**
2. Output JAR is configured as:
   - `dist/BloodDonationManagementSystemBest.jar`

### Run with Ant (optional)
From the project root (`BloodDonationManagementSystembest/`):
- `ant clean`
- `ant jar`
- `ant run`

Note: Ant targets and classpath are managed by NetBeans via `nbproject/build-impl.xml`.


