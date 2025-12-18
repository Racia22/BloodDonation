# PlantUML Diagrams for Blood Donation Management System

This directory contains PlantUML diagram files (.puml) that can be used to generate UML diagrams.

## How to Use

### Option 1: Online (PlantUML Server)
1. Go to http://www.plantuml.com/plantuml/uml/
2. Copy and paste the content of any `.puml` file
3. The diagram will be generated automatically
4. Right-click to save as PNG or SVG

### Option 2: VS Code Extension
1. Install "PlantUML" extension in VS Code
2. Open any `.puml` file
3. Press `Alt+D` (Windows) or `Cmd+D` (Mac) to preview
4. Right-click and select "Export Current Diagram"

### Option 3: Command Line
1. Install PlantUML: http://plantuml.com/starting
2. Install Graphviz: https://graphviz.org/download/
3. Run: `plantuml filename.puml`
4. Output will be generated as PNG/SVG

### Option 4: NetBeans Plugin
1. Install PlantUML plugin from NetBeans Plugin Portal
2. Open `.puml` file in NetBeans
3. Right-click → "Generate Diagram"

## Available Diagrams

### Sequence Diagrams
1. **1_Sequence_Login.puml** - User login authentication flow
2. **2_Sequence_RegisterDonation.puml** - Donation registration process

### Data Flow Diagrams (DFD)
3. **3_DFD_Level0_Context.puml** - Context diagram showing system boundaries
4. **4_DFD_Level1.puml** - Level 1 DFD showing main processes
5. **5_DFD_Level2_DonationManagement.puml** - Level 2 DFD for donation management

### Activity Diagrams
6. **6_Activity_Login.puml** - Login process activity flow
7. **7_Activity_RegisterDonation.puml** - Donation registration activity flow
8. **8_Activity_UserRegistration.puml** - User registration activity flow

## Diagram Descriptions

### Sequence Diagrams
- Show interaction between objects/components over time
- Useful for understanding system behavior and message flow
- Include actors, participants, and database interactions

### Data Flow Diagrams (DFD)
- Show how data flows through the system
- Level 0: Context diagram (system boundaries)
- Level 1: Main processes and data stores
- Level 2: Detailed breakdown of specific processes

### Activity Diagrams
- Show workflow and decision points
- Useful for understanding business processes
- Include start/end points, activities, and decision nodes

## Notes
- All diagrams are based on the actual codebase structure
- Diagrams follow MVC pattern implementation
- Database interactions are shown with PostgreSQL
- Error handling and validation flows are included

