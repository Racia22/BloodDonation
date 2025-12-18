# DFD Level 2 - Donation Management Explanation Guide

## Overview
This DFD Level 2 diagram breaks down Process 3.0 (Donation Management) into 5 sub-processes, showing how donations are validated, registered, updated, and viewed.

---

## Components

### **Processes (5 sub-processes):**
1. **3.1 Validate Donation Input** - Validates user input
2. **3.2 Validate Donor & Site** - Checks foreign key relationships
3. **3.3 Register Donation** - Creates new donation record
4. **3.4 Update Donation** - Modifies existing donation
5. **3.5 View Donations** - Retrieves and displays donations

### **Data Stores:**
- **D2: Donors** - Stores donor information
- **D3: Donations** - Stores donation records
- **D4: Sites** - Stores collection site information

### **External Entities:**
- **Donor** - Provides donation details
- **Staff/Admin** - Updates and views donations

---

## Flow Explanation (Step by Step)

### **FLOW 1: Register New Donation** (Most Common Flow)

**Step 1:** Donor provides donation details
- Input: donor_id, site_id, blood_group, quantity_ml

**Step 2:** Process 3.1 validates input
- Checks: All fields filled?
- Checks: Quantity between 250-500ml?
- Checks: Data types correct?

**Step 3:** If valid → sends to Process 3.2
- If invalid → returns error to Donor

**Step 4:** Process 3.2 validates donor exists
- Queries D2: Donors with donor_id
- Receives: Donor data or null

**Step 5:** Process 3.2 validates site exists
- Queries D4: Sites with site_id
- Receives: Site data or null

**Step 6:** If both valid → Process 3.3
- If either invalid → error returned

**Step 7:** Process 3.3 registers donation
- Inserts new record into D3: Donations
- Auto-generates donation_id
- Sets donation_date = current date

**Step 8:** Success message to Donor
- "Donation registered successfully"

---

### **FLOW 2: Update Donation** (Staff Operation)

**Step 9:** Staff provides update details
- Input: donation_id, updated fields

**Step 10:** Process 3.4 validates donor (if changed)
- Queries D2: Donors if donor_id updated

**Step 11:** Process 3.4 validates site (if changed)
- Queries D4: Sites if site_id updated

**Step 12:** Process 3.4 updates donation
- UPDATE D3: Donations SET ...
- WHERE donation_id = ?

**Step 13:** Confirmation to Staff
- "Donation updated successfully"

---

### **FLOW 3: View Donations** (Staff Operation)

**Step 14:** Staff requests to view donations
- Input: View all or filtered by criteria

**Step 15:** Process 3.5 retrieves records
- SELECT * FROM donations (with optional filters)
- Receives: List of donation records

**Step 16:** Display to Staff
- Formatted table/list view

---

## Data Persistence

All data stores (D2, D3, D4) sync with PostgreSQL Database:
- **Read:** Retrieve data from database
- **Write:** Save data to database

---

## Key Validation Rules

### Input Validation (Process 3.1):
- ✅ All fields must be filled
- ✅ Quantity must be 250-500ml
- ✅ Data types must be correct

### Foreign Key Validation (Process 3.2):
- ✅ Donor ID must exist in D2: Donors
- ✅ Site ID must exist in D4: Sites
- ✅ Both must exist before registration

### Registration Rules (Process 3.3):
- ✅ Auto-generate donation_id
- ✅ Set donation_date automatically
- ✅ Enforce quantity constraint (250-500ml)

---

## How to Explain This Diagram

1. **Start with the main purpose:** "This diagram shows how donations are managed in the system"

2. **Explain the 3 main flows:**
   - **Register** (Donor → Validate → Register)
   - **Update** (Staff → Validate → Update)
   - **View** (Staff → Retrieve → Display)

3. **Highlight validation steps:**
   - Input validation (fields, quantity)
   - Foreign key validation (donor, site exist)

4. **Show data flow:**
   - How data moves from external entities → processes → data stores → database

5. **Emphasize error handling:**
   - Validation errors returned to users
   - Invalid foreign keys prevent registration

---

## Exam Presentation Tips

1. **Follow the numbered steps** (1-16) when explaining
2. **Group related processes** (3.1-3.3 for registration flow)
3. **Highlight data stores** and their relationships
4. **Show database persistence** at the end
5. **Mention error handling** for each validation step

