# DFD Level 2 - Clean Layout Guide

I've created **3 versions** of the DFD Level 2 diagram. Choose the one that works best for you:

## Version Comparison

### **Version 1: `5_DFD_Level2_DonationManagement.puml`**
- Uses directional arrows (right, left, down)
- Clear separation of flows
- Good for simple presentations

### **Version 2: `5_DFD_Level2_DonationManagement_V2.puml`**
- Uses numbered steps [1] through [15]
- Processes arranged in columns
- Data stores at bottom
- **RECOMMENDED for exams** - easiest to explain

### **Version 3: `5_DFD_Level2_DonationManagement_V3.puml`**
- Best visual layout
- Clear vertical and horizontal flows
- No arrow collisions
- **RECOMMENDED for presentations**

---

## How to Use

1. **Copy the code** from your chosen version
2. **Paste into PlantUML** (http://www.plantuml.com/plantuml/uml/)
3. **Generate diagram**
4. **Save as PNG** for your presentation

---

## Explanation Order (Use Version 2 or 3)

### **Flow 1: Register Donation** (Steps 1-7)
1. Donor provides donation details
2. Process 3.1 validates input
3. Process 3.2 checks donor exists (D2)
4. Process 3.2 checks site exists (D4)
5. Process 3.3 registers donation (D3)
6. Success message to donor

### **Flow 2: Update Donation** (Steps 8-12)
1. Staff provides update details
2. Process 3.4 validates donor (if changed)
3. Process 3.4 validates site (if changed)
4. Process 3.4 updates donation record
5. Confirmation to staff

### **Flow 3: View Donations** (Steps 13-15)
1. Staff requests view
2. Process 3.5 retrieves from D3
3. Display records to staff

### **Data Persistence** (Bottom)
- All data stores sync with PostgreSQL Database
- Read/Write operations shown clearly

---

## Tips for Presentation

1. **Start with Flow 1** (most common)
2. **Follow numbered steps** sequentially
3. **Point to each arrow** as you explain
4. **Highlight validation steps** (3.1, 3.2)
5. **Show data persistence** at the end

---

## Key Points to Emphasize

✅ **Input Validation** (Process 3.1)
- Checks all fields filled
- Validates quantity (250-500ml)

✅ **Foreign Key Validation** (Process 3.2)
- Ensures donor exists
- Ensures site exists

✅ **Data Integrity** (Process 3.3)
- Only registers if all validations pass

✅ **Database Persistence**
- All data stores sync with PostgreSQL

