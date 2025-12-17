/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import con.ProjConnection;
import dao.BloodRequestsDao;
import java.sql.*;
import dao.DonationsDao;
import dao.DonorsDao;
import dao.SitesDao;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.BloodRequest;
import model.Donations;
import model.Donors;
import model.Sites;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author RC
 */
public class Dashboard extends javax.swing.JFrame {
    DefaultTableModel tableModel=new DefaultTableModel();
    DefaultTableModel Model=new DefaultTableModel();
    DefaultTableModel donorModel=new DefaultTableModel();
    DefaultTableModel siteModel=new DefaultTableModel();
    DefaultTableModel requestModel= new DefaultTableModel();
    /**
     * Creates new form Dashboard
     */
    CardLayout cardLayout;
    public Dashboard() {
        initComponents();
        loadNextDonationId();
        loadNextDonorId();
        loadNextSiteId();
        loadNextRequestId();
        addRowsInTable();
        addTableColumn();
        addColumnreq();
        addColumndona();
        addColumndo();
        
        
//        allowOnlyLetters(blood_groupTxt);

        allowOnlyNumbers(quantity_mlTxt);
        allowOnlyNumbersdo(d_idTxt);
        allowOnlyNumbersdona(s_idTxt);
        
    //for donors
        
        allowOnlyNumbers(d_phoneNumberTxt);
        allowOnlyNumbersid(d_idTxt);
        allowOnlyLetters(d_namesTxt);
        allowOnlyLet(d_locationTxt);
        
        // for site
        
        addTableColumnsite();
        //addColumnsite();
        allowOnlyNumbersm(phoneNumberTxt);
        allowOnlyLetters(s_namesTxt);
        allowOnlyLetters(doctorsNamesTxt);
        allowOnlyLetsi(s_locationTxt);
        
        cardLayout = (CardLayout)(pnlCards.getLayout());
    }
    
     private void allowOnlyNumbers(JTextField textField) {
        s_idTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isDigit(c)) {
                evt.consume();
                JOptionPane.showMessageDialog(null, "Enter only numbers");
            }
        }
    });
}
     
       private void allowOnlyNumbersdona(JTextField textField) {
        quantity_mlTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isDigit(c)) {
                evt.consume();
                JOptionPane.showMessageDialog(null, "Enter only numbers");
            }
        }
    });
}
       private void allowOnlyNumbersdo(JTextField textField) {
           d_idTxt.addKeyListener(new java.awt.event.KeyAdapter() {
              public void keyTyped(java.awt.event.KeyEvent evt) {
                 char c = evt.getKeyChar();
                 if (!Character.isDigit(c)) {
                 evt.consume();
                 JOptionPane.showMessageDialog(null, "Enter only numbers");
            }
        }
    });
}
           
           public void addTableColumn() {
        tableModel.addColumn("donations ID ");
        tableModel.addColumn("DONORS ID ");
        tableModel.addColumn("Sites ID ");
        tableModel.addColumn("Blood group");
        tableModel.addColumn("quantity_ml");
        tableModel.addColumn("donation_date");
         
        donationsTable.setModel(tableModel);
    }
    public void addRows(){
        tableModel.setRowCount(0);
        DonationsDao dao=new DonationsDao();
        List<Donations> donationslist=dao.findAllDonations();
        try{
            for(Donations donations: donationslist) {
                tableModel.addRow(new Object[]{
                    donations.getDonation_id(),
                    donations.getDonor_id(),
                    donations.getSite_id(),
                    donations.getBlood_group(),
                    donations.getQuantity_ml(),
                    donations.getDonation_date()
                 });
            }
            donationsTable.setModel(tableModel);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void addColumndona() {
        Model.addColumn("donations ID ");
        Model.addColumn("DONORS ID ");
        Model.addColumn("Sites ID ");
        Model.addColumn("Blood group ");
        Model.addColumn("quantity_ml");
        Model.addColumn("donation_date");
         
        
    }
    
    
    
    private void clearFieldsdona() {
    d_idTxt.setText("");
    s_idTxt.setText("");
    combo_txt.setSelectedItem("");
    quantity_mlTxt.setText("");
}
    
    private void cleardona() {
    donation_idtxt.setText("");
    donors_idTxt.setText("");
    site_idTxt.setText("");
    combo_txt.setSelectedIndex(-1);
    quantityTxt.setText("");
     }
    
    // methods for donors
    
    public void addColumndo() {
        donorModel.addColumn("donors ID ");
        donorModel.addColumn("Donors names ");
        donorModel.addColumn("Blood group");
        donorModel.addColumn("Phone number ");
        donorModel.addColumn("Location");
         
        Donors_table.setModel(donorModel);
    }
     public void addRowsInTable(){
         donorModel.setRowCount(0);
        DonorsDao dao=new DonorsDao(); 
        List<Donors> donorslist=dao.findAllDonors();
        try{
            for(Donors donors: donorslist) {
                donorModel.addRow(new Object[]{
                    donors.getDonor_id(),
                    donors.getDonor_names(),
                    donors.getBlood_group(),
                    donors.getPhone(),
                    donors.getDonor_location() 
                 });
            }
            Donors_table.setModel(donorModel);
        }catch(Exception ex){
            ex.printStackTrace();
        }
     }
     public void addColumnInTable() {
        donorModel.addColumn("Donors ID ");
        donorModel.addColumn("Donors names ");
        donorModel.addColumn("Blood group");
        donorModel.addColumn("Phone number ");
        donorModel.addColumn("Location");  
    }
     
      private void allowOnlyLetters(JTextField textField) {
    d_namesTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                evt.consume();
                JOptionPane.showMessageDialog(null, "Enter only letters");
            }
        }
    });


}
    private void allowOnlyLet(JTextField textField) {
    d_locationTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                evt.consume();
            }
        }
    });


}
    
        private void allowOnlyNumbersid(JTextField textField) {
    d_idTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isDigit(c)) {
                evt.consume();
                JOptionPane.showMessageDialog(null, "Enter only numbers");
           
            }
        }
    });
}
    private void allowOnlyNumbersdon(JTextField textField) {
    donor_phoneNumberTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isDigit(c)) {
                evt.consume();
                JOptionPane.showMessageDialog(null, "Enter only numbers");
            }
        }
    });
}
     private void clearFieldsdon() {
    d_idTxt.setText("");
    d_namesTxt.setText("");
    donor_phoneNumberTxt.setText("");
    d_locationTxt.setText("");
}
     private void clearField() {
        // DS_IDTXT.setText("");
     }
     
     
     private void initializeComboBox() {
    combo_txt.removeAllItems(); // Clear existing items
    String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
    for (String bloodGroup : bloodGroups) {
        combo_txt.addItem(bloodGroup);
    }
    combo_txt.setSelectedIndex(-1); // No item selected by default
}
     private void cleardon() {
    donor_namesTxt.setText("");
    combo_txt.setSelectedIndex(-1); // Deselect any item
    donor_phoneNumberTxt.setText("");
    donor_locationTxt.setText("");
     }
     
     // for site
     
     public void addTableColumnsite() {
        siteModel.addColumn("Sites ID ");
        siteModel.addColumn("Sites names ");
        siteModel.addColumn("phone number ");
        siteModel.addColumn("doctors names");
        siteModel.addColumn("location");
         
        sitesTable.setModel(siteModel);
    }
    public void addRowssite(){
        siteModel.setRowCount(0);
        SitesDao dao=new SitesDao();
        List<Sites> siteslist=dao.findAllSites();
        try{
            for(Sites sites: siteslist) {
                siteModel.addRow(new Object[]{
                    sites.getSite_id(),
                    sites.getSite_names(),
                    sites.getSite_phonenumber(),
                    sites.getDoctors_name(),
                    sites.getSite_location()
                 });
                
            }
            sitesTable.setModel(siteModel);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /*public void addColumnsite() {
        siteModel.addColumn("Sites ID ");
        siteModel.addColumn("Sites names ");
        siteModel.addColumn("phone number ");
        siteModel.addColumn("doctors names");
        siteModel.addColumn("location");
         
        //sitesTable.setModel(siteModel);
    }*/
     private void allowOnlyLetterssi(JTextField textField) {
    s_namesTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                evt.consume();
                JOptionPane.showMessageDialog(null, "Enter only letters");
            }
        }
    });


}
     private void allowOnlyLetter(JTextField textField) {
    doctorsNamesTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                evt.consume();
                JOptionPane.showMessageDialog(null, "Enter only letters");
            }
        }
    });


}
    private void allowOnlyLetsi(JTextField textField) {
    s_locationTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                evt.consume();
            }
        }
    });


}
    
        private void allowOnlyNumberssiteid(JTextField textField) {
    s_namesTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isDigit(c)) {
                evt.consume();
                JOptionPane.showMessageDialog(null, "Enter only letters");
            }
        }
    });
}
    private void allowOnlyNumbersm(JTextField textField) {
    phoneNumberTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            char c = evt.getKeyChar();
            if (!Character.isDigit(c)) {
                evt.consume();
                JOptionPane.showMessageDialog(null, "Enter only numbers");
            }
        }
    });
}
     private void clearFields() {
    s_namesTxt.setText("");
    phoneNumberTxt.setText("");
    s_locationTxt.setText("");
    doctorsNamesTxt.setText("");
}
     private void clearFieldsite() {
         s_idTxt.setText("");
     }
     /*private void clear() {
    site_id.setText("");
    sites_namesTxt.setText("");
    sites_phoneNumberTxt.setText("");
    sites_locationTxt.setText("");
    sites_doctorsNameTxt.setText("");
     }*/
     
     // for blood request
     
      public void addColumnreq() {
        requestModel.addColumn("Request ID ");
        requestModel.addColumn("Hospital name ");
        requestModel.addColumn("Blood group");
        requestModel.addColumn("Quantity Ml ");
        requestModel.addColumn("Request Date");
        requestModel.addColumn("Status");
         
        requestTable.setModel(requestModel);
    }
     public void addRowsInTablere(){
         requestModel.setRowCount(0);
        BloodRequestsDao dao=new BloodRequestsDao(); 
        List<BloodRequest> requestlist=dao.findAllBloodRequest();
        try{
            for(BloodRequest requests: requestlist) {
                requestModel.addRow(new Object[]{
                    requests.getRequest_id(),
                    requests.getHospital_name(),
                    requests.getBlood_group(), 
                    requests.getQuantity_ml(),
                    requests.getRequest_date(),
                    requests.getStatus()
                 });
            }
            requestTable.setModel(requestModel);
        }catch(Exception ex){
            ex.printStackTrace();
        }
     }
     public void addColumnInTablere() {
        requestModel.addColumn("Request ID ");
        requestModel.addColumn("Hospital names ");
        requestModel.addColumn("Blood group");
        requestModel.addColumn("Request Date");
        requestModel.addColumn("Quantity Ml");  
        requestModel.addColumn("Status");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        dashboard = new javax.swing.JLabel();
        donations = new javax.swing.JLabel();
        donors = new javax.swing.JLabel();
        bloodrequest = new javax.swing.JLabel();
        sites = new javax.swing.JLabel();
        pnlCards = new javax.swing.JPanel();
        dash = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        donats = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        donationsTable = new javax.swing.JTable();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Add2 = new javax.swing.JButton();
        DisplayAll = new javax.swing.JButton();
        searchdonid = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        don = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Donors_table = new javax.swing.JTable();
        Add3 = new javax.swing.JButton();
        Update1 = new javax.swing.JButton();
        Deleteredi = new javax.swing.JButton();
        DisplayAll1 = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        search1 = new javax.swing.JButton();
        searchdonid1 = new javax.swing.JTextField();
        bloodre = new javax.swing.JPanel();
        Add5 = new javax.swing.JButton();
        Update3 = new javax.swing.JButton();
        Delete2 = new javax.swing.JButton();
        DisplayAll3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        searchdonid2 = new javax.swing.JTextField();
        search2 = new javax.swing.JButton();
        sit = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        sitesTable = new javax.swing.JTable();
        Add4 = new javax.swing.JButton();
        Update2 = new javax.swing.JButton();
        Delete1 = new javax.swing.JButton();
        DisplayAll2 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        searchdonid3 = new javax.swing.JTextField();
        search3 = new javax.swing.JButton();
        donationsregister = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        d_idTxt = new javax.swing.JTextField();
        s_idTxt = new javax.swing.JTextField();
        quantity_mlTxt = new javax.swing.JTextField();
        combo_txt = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Register = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        donationsDelete = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        d_idTxt1 = new javax.swing.JTextField();
        s_idTxt1 = new javax.swing.JTextField();
        quantity_mlTxt1 = new javax.swing.JTextField();
        combo_txt1 = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        Deletebtn = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        do_id = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        donationsUpdate = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        donors_idTxt = new javax.swing.JTextField();
        site_idTxt = new javax.swing.JTextField();
        quantityTxt = new javax.swing.JTextField();
        combo_txt2 = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        Updatebtn = new javax.swing.JButton();
        donation_idtxt = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        donorsReg = new javax.swing.JPanel();
        d_names = new javax.swing.JLabel();
        d_namesTxt = new javax.swing.JTextField();
        blood_group = new javax.swing.JLabel();
        combo_txt3 = new javax.swing.JComboBox<>();
        phone_number = new javax.swing.JLabel();
        d_phoneNumberTxt = new javax.swing.JTextField();
        d_location = new javax.swing.JLabel();
        d_locationTxt = new javax.swing.JTextField();
        registrationbtn = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel84 = new javax.swing.JLabel();
        donorsupd = new javax.swing.JPanel();
        d_names1 = new javax.swing.JLabel();
        donor_namesTxt = new javax.swing.JTextField();
        blood_group1 = new javax.swing.JLabel();
        combo_txt4 = new javax.swing.JComboBox<>();
        phone_number1 = new javax.swing.JLabel();
        donor_phoneNumberTxt = new javax.swing.JTextField();
        d_location1 = new javax.swing.JLabel();
        donor_locationTxt = new javax.swing.JTextField();
        Updatebtn1 = new javax.swing.JButton();
        d_id = new javax.swing.JLabel();
        donors_id = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel86 = new javax.swing.JLabel();
        donorsDel = new javax.swing.JPanel();
        deletebtn = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        donnors_id = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        donors_namesTxt = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        phone_NumberTxt = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        donars_locationTxt = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        siteReg = new javax.swing.JPanel();
        s_id = new javax.swing.JLabel();
        s_names = new javax.swing.JLabel();
        s_namesTxt = new javax.swing.JTextField();
        s_phoneNumberTxt = new javax.swing.JLabel();
        phoneNumberTxt = new javax.swing.JTextField();
        locationTxt = new javax.swing.JLabel();
        s_locationTxt = new javax.swing.JTextField();
        doctors_nameTxt = new javax.swing.JLabel();
        doctorsNamesTxt = new javax.swing.JTextField();
        registrationButton = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel91 = new javax.swing.JLabel();
        siteUpd = new javax.swing.JPanel();
        s_id1 = new javax.swing.JLabel();
        site_id = new javax.swing.JTextField();
        s_names1 = new javax.swing.JLabel();
        sites_namesTxt = new javax.swing.JTextField();
        s_phoneNumberTxt1 = new javax.swing.JLabel();
        sites_phoneNumberTxt = new javax.swing.JTextField();
        locationTxt1 = new javax.swing.JLabel();
        sites_locationTxt = new javax.swing.JTextField();
        doctors_nameTxt1 = new javax.swing.JLabel();
        sites_doctorsNameTxt = new javax.swing.JTextField();
        updatebtn = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel93 = new javax.swing.JLabel();
        sitedelete = new javax.swing.JPanel();
        s_id2 = new javax.swing.JLabel();
        sitee_id = new javax.swing.JTextField();
        s_names2 = new javax.swing.JLabel();
        s_namesTxt1 = new javax.swing.JTextField();
        s_phoneNumberTxt2 = new javax.swing.JLabel();
        phoneNumberTxt1 = new javax.swing.JTextField();
        locationTxt2 = new javax.swing.JLabel();
        s_locationTxt1 = new javax.swing.JTextField();
        doctors_nameTxt2 = new javax.swing.JLabel();
        doctorsNamesTxt1 = new javax.swing.JTextField();
        deletebtn1 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel95 = new javax.swing.JLabel();
        requestReg = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        hospname = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        quantml = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        stat = new javax.swing.JTextField();
        RegisterReq = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        ComboBoxre = new javax.swing.JComboBox<>();
        jLabel96 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        requestUpd = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        UpdateReq = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        hospname1 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        quantml1 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        stat1 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel76 = new javax.swing.JLabel();
        req_idtxt = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        requestdel = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        DeleteReq = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        hospname2 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        quantml2 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        stat2 = new javax.swing.JTextField();
        req_idtxt1 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel98 = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerSize(1);

        jPanel1.setBackground(new java.awt.Color(218, 61, 70));

        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Dashboard.png"))); // NOI18N
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dashboardMousePressed(evt);
            }
        });

        donations.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Donations.png"))); // NOI18N
        donations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                donationsMousePressed(evt);
            }
        });

        donors.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Donors.png"))); // NOI18N
        donors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                donorsMousePressed(evt);
            }
        });

        bloodrequest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Blood requests.png"))); // NOI18N
        bloodrequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bloodrequestMousePressed(evt);
            }
        });

        sites.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Sites.png"))); // NOI18N
        sites.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sitesMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sites, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(donations, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(donors, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bloodrequest, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(donations, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(donors, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(bloodrequest, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(sites, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        pnlCards.setLayout(new java.awt.CardLayout());

        dash.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(218, 61, 70));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dashboard");

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/logout-02-Stroke-Rounded (1) 1.png"))); // NOI18N
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logout)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Donations.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/gift-Stroke-Rounded 1.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/4.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(43, 43, 43))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(48, 48, 48))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Donors.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/4.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/user-Stroke-Rounded 1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(218, 61, 70));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Blood requests.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/5 +.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/give-pill-Stroke-Rounded 1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(237, 145, 145));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Available Sites.png"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/5 +.png"))); // NOI18N

        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/location-03-Stroke-Rounded 1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel61)
                .addGap(26, 26, 26))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel61)
                        .addGap(50, 50, 50))))
        );

        javax.swing.GroupLayout dashLayout = new javax.swing.GroupLayout(dash);
        dash.setLayout(dashLayout);
        dashLayout.setHorizontalGroup(
            dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dashLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        dashLayout.setVerticalGroup(
            dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174)
                .addGroup(dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(69, 69, 69)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        pnlCards.add(dash, "pnlCard1");

        donats.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/see-940x153.jpg"))); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(821, 469));

        donationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        donationsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                donationsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(donationsTable);

        Update.setBackground(new java.awt.Color(218, 61, 70));
        Update.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Update.setForeground(new java.awt.Color(255, 255, 255));
        Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Update details.png"))); // NOI18N
        Update.setText("UPDATE");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Delete.setBackground(new java.awt.Color(218, 61, 70));
        Delete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Delete.setForeground(new java.awt.Color(255, 255, 255));
        Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        Delete.setText("DELETE");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Add2.setBackground(new java.awt.Color(218, 61, 70));
        Add2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Add2.setForeground(new java.awt.Color(255, 255, 255));
        Add2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add new.png"))); // NOI18N
        Add2.setText("ADD");
        Add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add2ActionPerformed(evt);
            }
        });

        DisplayAll.setBackground(new java.awt.Color(218, 61, 70));
        DisplayAll.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DisplayAll.setForeground(new java.awt.Color(255, 255, 255));
        DisplayAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Details.png"))); // NOI18N
        DisplayAll.setText("DISPLAY ALL");
        DisplayAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayAllActionPerformed(evt);
            }
        });

        search.setBackground(new java.awt.Color(218, 61, 70));
        search.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText("SEARCH");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout donatsLayout = new javax.swing.GroupLayout(donats);
        donats.setLayout(donatsLayout);
        donatsLayout.setHorizontalGroup(
            donatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(donatsLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(searchdonid, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(donatsLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(Add2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(Update)
                .addGap(47, 47, 47)
                .addComponent(Delete)
                .addGap(40, 40, 40)
                .addComponent(DisplayAll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        donatsLayout.setVerticalGroup(
            donatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(donatsLayout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(donatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchdonid, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114)
                .addGroup(donatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Add2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DisplayAll, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCards.add(donats, "pnlCard2");

        don.setBackground(new java.awt.Color(255, 255, 255));

        Donors_table.setBorder(new javax.swing.border.MatteBorder(null));
        Donors_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Donors_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Donors_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Donors_table);

        Add3.setBackground(new java.awt.Color(218, 61, 70));
        Add3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Add3.setForeground(new java.awt.Color(255, 255, 255));
        Add3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add new.png"))); // NOI18N
        Add3.setText("ADD");
        Add3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add3ActionPerformed(evt);
            }
        });

        Update1.setBackground(new java.awt.Color(218, 61, 70));
        Update1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Update1.setForeground(new java.awt.Color(255, 255, 255));
        Update1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Update details.png"))); // NOI18N
        Update1.setText("UPDATE");
        Update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update1ActionPerformed(evt);
            }
        });

        Deleteredi.setBackground(new java.awt.Color(218, 61, 70));
        Deleteredi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Deleteredi.setForeground(new java.awt.Color(255, 255, 255));
        Deleteredi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        Deleteredi.setText("DELETE");
        Deleteredi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleterediActionPerformed(evt);
            }
        });

        DisplayAll1.setBackground(new java.awt.Color(218, 61, 70));
        DisplayAll1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DisplayAll1.setForeground(new java.awt.Color(255, 255, 255));
        DisplayAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Details.png"))); // NOI18N
        DisplayAll1.setText("DISPLAY ALL");
        DisplayAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayAll1ActionPerformed(evt);
            }
        });

        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/donordo-940x153.jpg"))); // NOI18N

        search1.setBackground(new java.awt.Color(218, 61, 70));
        search1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        search1.setForeground(new java.awt.Color(255, 255, 255));
        search1.setText("SEARCH");
        search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout donLayout = new javax.swing.GroupLayout(don);
        don.setLayout(donLayout);
        donLayout.setHorizontalGroup(
            donLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(donLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(searchdonid1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, donLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Add3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(Update1)
                .addGap(88, 88, 88)
                .addComponent(Deleteredi)
                .addGap(74, 74, 74)
                .addComponent(DisplayAll1)
                .addGap(62, 62, 62))
        );
        donLayout.setVerticalGroup(
            donLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, donLayout.createSequentialGroup()
                .addComponent(jLabel57)
                .addGap(49, 49, 49)
                .addGroup(donLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchdonid1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addGroup(donLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Add3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Update1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Deleteredi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DisplayAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(142, 142, 142)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCards.add(don, "pnlCard3");

        bloodre.setBackground(new java.awt.Color(255, 255, 255));

        Add5.setBackground(new java.awt.Color(218, 61, 70));
        Add5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Add5.setForeground(new java.awt.Color(255, 255, 255));
        Add5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add new.png"))); // NOI18N
        Add5.setText("ADD");
        Add5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add5ActionPerformed(evt);
            }
        });

        Update3.setBackground(new java.awt.Color(218, 61, 70));
        Update3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Update3.setForeground(new java.awt.Color(255, 255, 255));
        Update3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Update details.png"))); // NOI18N
        Update3.setText("UPDATE");
        Update3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update3ActionPerformed(evt);
            }
        });

        Delete2.setBackground(new java.awt.Color(218, 61, 70));
        Delete2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Delete2.setForeground(new java.awt.Color(255, 255, 255));
        Delete2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        Delete2.setText("DELETE");
        Delete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete2ActionPerformed(evt);
            }
        });

        DisplayAll3.setBackground(new java.awt.Color(218, 61, 70));
        DisplayAll3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DisplayAll3.setForeground(new java.awt.Color(255, 255, 255));
        DisplayAll3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Details.png"))); // NOI18N
        DisplayAll3.setText("DISPLAY ALL");
        DisplayAll3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayAll3ActionPerformed(evt);
            }
        });

        requestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        requestTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(requestTable);

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bloodreq-940x153.jpg"))); // NOI18N

        search2.setBackground(new java.awt.Color(218, 61, 70));
        search2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        search2.setForeground(new java.awt.Color(255, 255, 255));
        search2.setText("SEARCH");
        search2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bloodreLayout = new javax.swing.GroupLayout(bloodre);
        bloodre.setLayout(bloodreLayout);
        bloodreLayout.setHorizontalGroup(
            bloodreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bloodreLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(bloodreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bloodreLayout.createSequentialGroup()
                        .addComponent(Add5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(Update3)
                        .addGap(81, 81, 81)
                        .addComponent(Delete2)
                        .addGap(81, 81, 81)
                        .addComponent(DisplayAll3))
                    .addGroup(bloodreLayout.createSequentialGroup()
                        .addComponent(searchdonid2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(search2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bloodreLayout.setVerticalGroup(
            bloodreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bloodreLayout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(bloodreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchdonid2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(138, 138, 138)
                .addGroup(bloodreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Add5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Update3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DisplayAll3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCards.add(bloodre, "pnlCard4");

        sit.setBackground(new java.awt.Color(255, 255, 255));

        sitesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        sitesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sitesTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(sitesTable);

        Add4.setBackground(new java.awt.Color(218, 61, 70));
        Add4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Add4.setForeground(new java.awt.Color(255, 255, 255));
        Add4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add new.png"))); // NOI18N
        Add4.setText("ADD");
        Add4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add4ActionPerformed(evt);
            }
        });

        Update2.setBackground(new java.awt.Color(218, 61, 70));
        Update2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Update2.setForeground(new java.awt.Color(255, 255, 255));
        Update2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Update details.png"))); // NOI18N
        Update2.setText("UPDATE");
        Update2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update2ActionPerformed(evt);
            }
        });

        Delete1.setBackground(new java.awt.Color(218, 61, 70));
        Delete1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Delete1.setForeground(new java.awt.Color(255, 255, 255));
        Delete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        Delete1.setText("DELETE");
        Delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete1ActionPerformed(evt);
            }
        });

        DisplayAll2.setBackground(new java.awt.Color(218, 61, 70));
        DisplayAll2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DisplayAll2.setForeground(new java.awt.Color(255, 255, 255));
        DisplayAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Details.png"))); // NOI18N
        DisplayAll2.setText("DISPLAY ALL");
        DisplayAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayAll2ActionPerformed(evt);
            }
        });

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/siitee.png"))); // NOI18N

        search3.setBackground(new java.awt.Color(218, 61, 70));
        search3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        search3.setForeground(new java.awt.Color(255, 255, 255));
        search3.setText("SEARCH");
        search3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sitLayout = new javax.swing.GroupLayout(sit);
        sit.setLayout(sitLayout);
        sitLayout.setHorizontalGroup(
            sitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sitLayout.createSequentialGroup()
                .addGroup(sitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sitLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(searchdonid3, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(search3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sitLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(Add4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(Update2)
                        .addGap(84, 84, 84)
                        .addComponent(Delete1)
                        .addGap(78, 78, 78)
                        .addComponent(DisplayAll2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sitLayout.setVerticalGroup(
            sitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sitLayout.createSequentialGroup()
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(sitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchdonid3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addGroup(sitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Add4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Update2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DisplayAll2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCards.add(sit, "pnlCard5");

        donationsregister.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel12.setText("REGISTER NEW DONATION");
        donationsregister.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 37, 320, 59));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setText("Donation ID");
        donationsregister.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, -1, 31));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel14.setText("Donor ID");
        donationsregister.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, -1, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel15.setText("Site ID");
        donationsregister.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, -1, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel16.setText("Blood Group");
        donationsregister.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 410, -1, -1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setText("Quantity_Ml");
        donationsregister.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 510, -1, -1));

        d_idTxt.setBackground(new java.awt.Color(248, 232, 232));
        d_idTxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        d_idTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsregister.add(d_idTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, 236, 39));

        s_idTxt.setBackground(new java.awt.Color(248, 232, 232));
        s_idTxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        s_idTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsregister.add(s_idTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 236, 39));

        quantity_mlTxt.setBackground(new java.awt.Color(248, 232, 232));
        quantity_mlTxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quantity_mlTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsregister.add(quantity_mlTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 490, 236, 46));

        combo_txt.setBackground(new java.awt.Color(248, 232, 232));
        combo_txt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        combo_txt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        donationsregister.add(combo_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, 230, 42));
        donationsregister.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 109, 718, 10));
        donationsregister.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 680, 718, 10));

        Register.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Register.setText("Register");
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }
        });
        donationsregister.add(Register, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 720, 124, 52));

        // CHANGE: Set opaque to true so background color is visible.
        // PURPOSE: Ensure the label background is visible for better readability.
        jLabel25.setOpaque(true);
        jLabel25.setBackground(new java.awt.Color(255, 0, 51));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        // CHANGE: Set horizontal alignment to left so text displays properly.
        // PURPOSE: Ensure the donation ID text is visible and aligned correctly.
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        // CHANGE: Set initial text and ensure label is visible from the start.
        // PURPOSE: Display a default value immediately so the label is never empty.
        jLabel25.setText("Loading...");
        jLabel25.setVisible(true);
        jLabel25.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jLabel25ComponentShown(evt);
            }
        });
        // CHANGE: Increased width from 20 to 100 pixels to properly display donation ID numbers.
        // PURPOSE: The previous width of 20 pixels was too narrow to show multi-digit donation IDs.
        donationsregister.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 100, 40));

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel78.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel78MouseClicked(evt);
            }
        });
        donationsregister.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/donorback-940x860.jpg"))); // NOI18N
        donationsregister.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));
        // CHANGE: Bring jLabel25 to front after adding jLabel20 (background image) to ensure it's visible.
        // PURPOSE: jLabel20 covers the entire panel, so jLabel25 needs to be on top to display the donation ID.
        donationsregister.setComponentZOrder(jLabel25, 0);

        pnlCards.add(donationsregister, "pnlCard7");

        donationsDelete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel26.setText("DELETE DONATION");
        donationsDelete.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 260, 59));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel27.setText("Donation ID");
        donationsDelete.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, -1, 31));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel28.setText("Donor ID");
        donationsDelete.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, -1));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel29.setText("Site ID");
        donationsDelete.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, -1, -1));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel30.setText("Blood Group");
        donationsDelete.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, -1, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel31.setText("Quantity_Ml");
        donationsDelete.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 520, -1, -1));

        d_idTxt1.setBackground(new java.awt.Color(248, 232, 232));
        d_idTxt1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        d_idTxt1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsDelete.add(d_idTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 236, 39));

        s_idTxt1.setBackground(new java.awt.Color(248, 232, 232));
        s_idTxt1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        s_idTxt1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsDelete.add(s_idTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 236, 39));

        quantity_mlTxt1.setBackground(new java.awt.Color(248, 232, 232));
        quantity_mlTxt1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        quantity_mlTxt1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsDelete.add(quantity_mlTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 500, 236, 46));

        combo_txt1.setBackground(new java.awt.Color(248, 232, 232));
        combo_txt1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        combo_txt1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        donationsDelete.add(combo_txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, 230, 42));
        donationsDelete.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 109, 718, 10));
        donationsDelete.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 680, 718, 10));

        Deletebtn.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Deletebtn.setText("Delete");
        Deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletebtnActionPerformed(evt);
            }
        });
        donationsDelete.add(Deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 730, 124, 52));

        jLabel39.setBackground(new java.awt.Color(255, 0, 51));
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 51, 51));
        jLabel39.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jLabel39ComponentShown(evt);
            }
        });
        donationsDelete.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 20, 40));

        do_id.setBackground(new java.awt.Color(248, 232, 232));
        do_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        do_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsDelete.add(do_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 240, 50));

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel79.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel79MouseClicked(evt);
            }
        });
        donationsDelete.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/donorback-940x860.jpg"))); // NOI18N
        donationsDelete.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(donationsDelete, "pnlCard8");

        donationsUpdate.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel40.setText("UPDATE DONATION");
        donationsUpdate.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 240, 59));

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel41.setText("Donation ID");
        donationsUpdate.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, 31));

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel42.setText("Donor ID");
        donationsUpdate.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, -1, -1));

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel43.setText("Site ID");
        donationsUpdate.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, -1, -1));

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel44.setText("Blood Group");
        donationsUpdate.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 420, -1, -1));

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel45.setText("Quantity_Ml");
        donationsUpdate.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, -1, -1));

        donors_idTxt.setBackground(new java.awt.Color(248, 232, 232));
        donors_idTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        donors_idTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsUpdate.add(donors_idTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 250, 240, 39));

        site_idTxt.setBackground(new java.awt.Color(248, 232, 232));
        site_idTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        site_idTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsUpdate.add(site_idTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 240, 39));

        quantityTxt.setBackground(new java.awt.Color(248, 232, 232));
        quantityTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        quantityTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsUpdate.add(quantityTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, 236, 46));

        combo_txt2.setBackground(new java.awt.Color(248, 232, 232));
        combo_txt2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        combo_txt2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        donationsUpdate.add(combo_txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 400, 240, 42));
        donationsUpdate.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 109, 718, 10));
        donationsUpdate.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 680, 718, 10));

        Updatebtn.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Updatebtn.setText("Update");
        Updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatebtnActionPerformed(evt);
            }
        });
        donationsUpdate.add(Updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 730, 124, 52));

        donation_idtxt.setBackground(new java.awt.Color(248, 232, 232));
        donation_idtxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        donation_idtxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donationsUpdate.add(donation_idtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 250, 40));

        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel81.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel81MouseClicked(evt);
            }
        });
        donationsUpdate.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/donorback-940x860.jpg"))); // NOI18N
        donationsUpdate.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(donationsUpdate, "pnlCard9");

        donorsReg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        d_names.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        d_names.setText("Names");
        donorsReg.add(d_names, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, -1, -1));

        d_namesTxt.setBackground(new java.awt.Color(248, 232, 232));
        d_namesTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        d_namesTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        d_namesTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d_namesTxtActionPerformed(evt);
            }
        });
        donorsReg.add(d_namesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 345, 37));

        blood_group.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        blood_group.setText("Blood group");
        donorsReg.add(blood_group, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, -1, -1));

        combo_txt3.setBackground(new java.awt.Color(246, 217, 217));
        combo_txt3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        combo_txt3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        donorsReg.add(combo_txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 345, 42));

        phone_number.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        phone_number.setText("Phone Number");
        donorsReg.add(phone_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, -1, -1));

        d_phoneNumberTxt.setBackground(new java.awt.Color(246, 217, 217));
        d_phoneNumberTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        d_phoneNumberTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        d_phoneNumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d_phoneNumberTxtActionPerformed(evt);
            }
        });
        donorsReg.add(d_phoneNumberTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 440, 345, 43));

        d_location.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        d_location.setText("Province");
        donorsReg.add(d_location, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 560, -1, -1));

        d_locationTxt.setBackground(new java.awt.Color(246, 217, 217));
        d_locationTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        d_locationTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        d_locationTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d_locationTxtActionPerformed(evt);
            }
        });
        donorsReg.add(d_locationTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 550, 345, 42));

        registrationbtn.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        registrationbtn.setText("REGISTER");
        registrationbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationbtnActionPerformed(evt);
            }
        });
        donorsReg.add(registrationbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 720, -1, 52));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel32.setText("Donor's Registration");
        donorsReg.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 320, 60));

        jLabel71.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel71.setText("Donor ID");
        donorsReg.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, -1));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 0, 51));
        donorsReg.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 10, 20));

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel83MouseClicked(evt);
            }
        });
        donorsReg.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        donorsReg.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 109, 718, 10));
        donorsReg.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 670, 718, 20));

        jLabel84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/donorback-940x860.jpg"))); // NOI18N
        donorsReg.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(donorsReg, "pnlCard10");

        donorsupd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        d_names1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        d_names1.setText("Names");
        donorsupd.add(d_names1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, -1, -1));

        donor_namesTxt.setBackground(new java.awt.Color(246, 230, 230));
        donor_namesTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        donor_namesTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donor_namesTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donor_namesTxtActionPerformed(evt);
            }
        });
        donorsupd.add(donor_namesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 345, 37));

        blood_group1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        blood_group1.setText("Blood group");
        donorsupd.add(blood_group1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, -1, -1));

        combo_txt4.setBackground(new java.awt.Color(247, 224, 224));
        combo_txt4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        combo_txt4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        donorsupd.add(combo_txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, 340, 42));

        phone_number1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        phone_number1.setText("Phone Number");
        donorsupd.add(phone_number1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, -1, -1));

        donor_phoneNumberTxt.setBackground(new java.awt.Color(250, 236, 236));
        donor_phoneNumberTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        donor_phoneNumberTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donor_phoneNumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donor_phoneNumberTxtActionPerformed(evt);
            }
        });
        donorsupd.add(donor_phoneNumberTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 480, 340, 43));

        d_location1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        d_location1.setText("Province");
        donorsupd.add(d_location1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 580, -1, -1));

        donor_locationTxt.setBackground(new java.awt.Color(251, 234, 234));
        donor_locationTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        donor_locationTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donor_locationTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donor_locationTxtActionPerformed(evt);
            }
        });
        donorsupd.add(donor_locationTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 570, 340, 42));

        Updatebtn1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Updatebtn1.setText("Update");
        Updatebtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Updatebtn1ActionPerformed(evt);
            }
        });
        donorsupd.add(Updatebtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 730, -1, 52));

        d_id.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        d_id.setText("ID");
        donorsupd.add(d_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, -1));

        donors_id.setBackground(new java.awt.Color(251, 237, 237));
        donors_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        donors_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donorsupd.add(donors_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 349, 37));

        jLabel70.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel70.setText("Update Donor");
        donorsupd.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 230, 60));

        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel85.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel85MouseClicked(evt);
            }
        });
        donorsupd.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        donorsupd.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 109, 718, 10));
        donorsupd.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 670, 718, 20));

        jLabel86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/donorback-940x860.jpg"))); // NOI18N
        donorsupd.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(donorsupd, "pnlCard11");

        donorsDel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deletebtn.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });
        donorsDel.add(deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 720, 121, 51));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel34.setText("Donors ID");
        donorsDel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, -1, -1));

        donnors_id.setBackground(new java.awt.Color(247, 230, 230));
        donnors_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        donnors_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donnors_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donnors_idActionPerformed(evt);
            }
        });
        donorsDel.add(donnors_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 275, 42));

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel46.setText("Donors names");
        donorsDel.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, -1, -1));

        donors_namesTxt.setBackground(new java.awt.Color(247, 230, 230));
        donors_namesTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        donors_namesTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donorsDel.add(donors_namesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 275, 42));

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel48.setText("Blood group");
        donorsDel.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, -1, -1));

        jComboBox1.setBackground(new java.awt.Color(247, 230, 230));
        jComboBox1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        donorsDel.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 275, 50));

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel53.setText("phone number");
        donorsDel.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 450, -1, -1));

        phone_NumberTxt.setBackground(new java.awt.Color(247, 230, 230));
        phone_NumberTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        phone_NumberTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        phone_NumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phone_NumberTxtActionPerformed(evt);
            }
        });
        donorsDel.add(phone_NumberTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 438, 275, 50));

        jLabel54.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel54.setText("Province");
        donorsDel.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, -1, 24));

        donars_locationTxt.setBackground(new java.awt.Color(247, 230, 230));
        donars_locationTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        donars_locationTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        donars_locationTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donars_locationTxtActionPerformed(evt);
            }
        });
        donorsDel.add(donars_locationTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 520, 275, 50));

        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel87MouseClicked(evt);
            }
        });
        donorsDel.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        donorsDel.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 109, 718, 10));
        donorsDel.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 670, 718, 20));

        jLabel88.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel88.setText("Delete Donor");
        donorsDel.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 230, 60));

        jLabel89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/donorback-940x860.jpg"))); // NOI18N
        donorsDel.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(donorsDel, "pnlCard12");

        siteReg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s_id.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        s_id.setText("Site ID:");
        siteReg.add(s_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 224, 90, -1));

        s_names.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        s_names.setText("Site Name:");
        siteReg.add(s_names, new org.netbeans.lib.awtextra.AbsoluteConstraints(357, 286, -1, -1));

        s_namesTxt.setBackground(new java.awt.Color(248, 232, 232));
        s_namesTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        s_namesTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        s_namesTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_namesTxtActionPerformed(evt);
            }
        });
        siteReg.add(s_namesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(529, 279, 297, 38));

        s_phoneNumberTxt.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        s_phoneNumberTxt.setText("Phone Number:");
        siteReg.add(s_phoneNumberTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 351, -1, -1));

        phoneNumberTxt.setBackground(new java.awt.Color(248, 232, 232));
        phoneNumberTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        phoneNumberTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        phoneNumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberTxtActionPerformed(evt);
            }
        });
        siteReg.add(phoneNumberTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(529, 344, 297, 39));

        locationTxt.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        locationTxt.setText("Location:");
        siteReg.add(locationTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 419, -1, -1));

        s_locationTxt.setBackground(new java.awt.Color(248, 232, 232));
        s_locationTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        s_locationTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        siteReg.add(s_locationTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(529, 412, 297, 38));

        doctors_nameTxt.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        doctors_nameTxt.setText("Doctor's names:");
        siteReg.add(doctors_nameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 483, -1, -1));

        doctorsNamesTxt.setBackground(new java.awt.Color(248, 232, 232));
        doctorsNamesTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        doctorsNamesTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        siteReg.add(doctorsNamesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(529, 483, 297, 40));

        registrationButton.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        registrationButton.setText("Register");
        registrationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationButtonActionPerformed(evt);
            }
        });
        siteReg.add(registrationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 720, -1, 44));

        jLabel55.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel55.setText("Site's  Registration");
        siteReg.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 289, 45));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        siteReg.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 20, 20));

        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel90.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel90MouseClicked(evt);
            }
        });
        siteReg.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        siteReg.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 718, 10));
        siteReg.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 670, 718, 20));

        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/donorback-940x860.jpg"))); // NOI18N
        siteReg.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(siteReg, "pnlCard13");

        siteUpd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s_id1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        s_id1.setText("Site ID:");
        siteUpd.add(s_id1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 90, -1));

        site_id.setBackground(new java.awt.Color(248, 232, 232));
        site_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        site_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        site_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                site_idActionPerformed(evt);
            }
        });
        siteUpd.add(site_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 289, 44));

        s_names1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        s_names1.setText("Site  Name:");
        siteUpd.add(s_names1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, -1, -1));

        sites_namesTxt.setBackground(new java.awt.Color(248, 232, 232));
        sites_namesTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        sites_namesTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        sites_namesTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sites_namesTxtActionPerformed(evt);
            }
        });
        siteUpd.add(sites_namesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 289, 44));

        s_phoneNumberTxt1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        s_phoneNumberTxt1.setText("Phone Number:");
        siteUpd.add(s_phoneNumberTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, -1, -1));

        sites_phoneNumberTxt.setBackground(new java.awt.Color(248, 232, 232));
        sites_phoneNumberTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        sites_phoneNumberTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        sites_phoneNumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sites_phoneNumberTxtActionPerformed(evt);
            }
        });
        siteUpd.add(sites_phoneNumberTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 410, 289, 44));

        locationTxt1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        locationTxt1.setText("Location:");
        siteUpd.add(locationTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, -1, -1));

        sites_locationTxt.setBackground(new java.awt.Color(248, 232, 232));
        sites_locationTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        sites_locationTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        sites_locationTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sites_locationTxtActionPerformed(evt);
            }
        });
        siteUpd.add(sites_locationTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 289, 44));

        doctors_nameTxt1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        doctors_nameTxt1.setText("Doctor's names:");
        siteUpd.add(doctors_nameTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 570, -1, -1));

        sites_doctorsNameTxt.setBackground(new java.awt.Color(248, 232, 232));
        sites_doctorsNameTxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        sites_doctorsNameTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        siteUpd.add(sites_doctorsNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 570, 289, 37));

        updatebtn.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        updatebtn.setText("update");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });
        siteUpd.add(updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 720, 102, 57));

        jLabel56.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel56.setText("Update Site");
        siteUpd.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 190, 45));

        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel92.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel92MouseClicked(evt);
            }
        });
        siteUpd.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        siteUpd.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 718, 10));
        siteUpd.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 670, 718, 20));

        jLabel93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/donorback-940x860.jpg"))); // NOI18N
        siteUpd.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(siteUpd, "pnlCard14");

        sitedelete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s_id2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        s_id2.setText("Site ID:");
        sitedelete.add(s_id2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 90, -1));

        sitee_id.setBackground(new java.awt.Color(248, 232, 232));
        sitee_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        sitee_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        sitee_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sitee_idActionPerformed(evt);
            }
        });
        sitedelete.add(sitee_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 289, 44));

        s_names2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        s_names2.setText("Site Name:");
        sitedelete.add(s_names2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        s_namesTxt1.setBackground(new java.awt.Color(248, 232, 232));
        s_namesTxt1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        s_namesTxt1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        s_namesTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_namesTxt1ActionPerformed(evt);
            }
        });
        sitedelete.add(s_namesTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 289, 44));

        s_phoneNumberTxt2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        s_phoneNumberTxt2.setText("Phone Number:");
        sitedelete.add(s_phoneNumberTxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, -1, -1));

        phoneNumberTxt1.setBackground(new java.awt.Color(248, 232, 232));
        phoneNumberTxt1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        phoneNumberTxt1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        phoneNumberTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberTxt1ActionPerformed(evt);
            }
        });
        sitedelete.add(phoneNumberTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, 289, 44));

        locationTxt2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        locationTxt2.setText("Location:");
        sitedelete.add(locationTxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 490, -1, -1));

        s_locationTxt1.setBackground(new java.awt.Color(248, 232, 232));
        s_locationTxt1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        s_locationTxt1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        sitedelete.add(s_locationTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 480, 289, 44));

        doctors_nameTxt2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        doctors_nameTxt2.setText("Doctor's names:");
        sitedelete.add(doctors_nameTxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 570, -1, -1));

        doctorsNamesTxt1.setBackground(new java.awt.Color(248, 232, 232));
        doctorsNamesTxt1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        doctorsNamesTxt1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        sitedelete.add(doctorsNamesTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 550, 289, 44));

        deletebtn1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        deletebtn1.setText("Delete");
        deletebtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtn1ActionPerformed(evt);
            }
        });
        sitedelete.add(deletebtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 720, 130, 60));

        jLabel59.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel59.setText("Delete Site");
        sitedelete.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 190, 45));

        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel94.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel94MouseClicked(evt);
            }
        });
        sitedelete.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        sitedelete.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 718, 10));
        sitedelete.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 670, 718, 20));

        jLabel95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/donorback-940x860.jpg"))); // NOI18N
        sitedelete.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(sitedelete, "pnlCard15");

        requestReg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel35.setText(" Register Blood_Request ");
        requestReg.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel38.setText("Hospital Name");
        requestReg.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, -1, -1));

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel47.setText("Blood Group");
        requestReg.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, -1, -1));

        hospname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        hospname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        hospname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hospnameActionPerformed(evt);
            }
        });
        requestReg.add(hospname, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, 271, 42));

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel49.setText("Quantity Ml");
        requestReg.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, -1, -1));

        quantml.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        quantml.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        quantml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantmlActionPerformed(evt);
            }
        });
        requestReg.add(quantml, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 271, 42));

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel50.setText("Status");
        requestReg.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 570, -1, -1));

        stat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        stat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        requestReg.add(stat, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 560, 271, 42));

        RegisterReq.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        RegisterReq.setText("Register");
        RegisterReq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterReqActionPerformed(evt);
            }
        });
        requestReg.add(RegisterReq, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 740, 130, 50));

        jLabel73.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel73.setText("Request ID");
        requestReg.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, -1, -1));

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(223, 32, 32));
        requestReg.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 20, 20));

        ComboBoxre.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ComboBoxre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        requestReg.add(ComboBoxre, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 270, 50));

        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel96.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel96MouseClicked(evt);
            }
        });
        requestReg.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        requestReg.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 718, 10));
        requestReg.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 670, 718, 20));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/request-940x860.jpg"))); // NOI18N
        requestReg.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(requestReg, "pnlCard16");

        requestUpd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel36.setText("Update Request ");
        requestUpd.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        UpdateReq.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        UpdateReq.setText("Update");
        UpdateReq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateReqActionPerformed(evt);
            }
        });
        requestUpd.add(UpdateReq, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 720, 110, 60));

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel52.setText("Hospital Name");
        requestUpd.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, -1, -1));

        hospname1.setBackground(new java.awt.Color(247, 247, 247));
        hospname1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        hospname1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        hospname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hospname1ActionPerformed(evt);
            }
        });
        requestUpd.add(hospname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 271, 42));

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel60.setText("Blood Group");
        requestUpd.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, -1, -1));

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel62.setText("Quantity Ml");
        requestUpd.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, -1, -1));

        quantml1.setBackground(new java.awt.Color(247, 247, 247));
        quantml1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        quantml1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        quantml1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantml1ActionPerformed(evt);
            }
        });
        requestUpd.add(quantml1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, 271, 42));

        jLabel63.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel63.setText("Status");
        requestUpd.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 510, -1, -1));

        stat1.setBackground(new java.awt.Color(247, 247, 247));
        stat1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        stat1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        requestUpd.add(stat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 271, 42));

        jComboBox3.setBackground(new java.awt.Color(247, 247, 247));
        jComboBox3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        requestUpd.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 270, 50));

        jLabel76.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel76.setText("Request ID");
        requestUpd.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, -1, -1));

        req_idtxt.setBackground(new java.awt.Color(247, 247, 247));
        req_idtxt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        req_idtxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        requestUpd.add(req_idtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 270, 40));

        jLabel97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel97.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel97MouseClicked(evt);
            }
        });
        requestUpd.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        requestUpd.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 718, 10));
        requestUpd.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 670, 718, 20));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/request-940x860.jpg"))); // NOI18N
        requestUpd.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(requestUpd, "pnlCard17");

        requestdel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel37.setText("Delete Request");
        requestdel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 234, -1));

        DeleteReq.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        DeleteReq.setText("Delete");
        DeleteReq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteReqActionPerformed(evt);
            }
        });
        requestdel.add(DeleteReq, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 720, 105, 48));

        jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel64.setText("Hospital Name");
        requestdel.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, -1, -1));

        hospname2.setBackground(new java.awt.Color(247, 247, 247));
        hospname2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        hospname2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        hospname2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hospname2ActionPerformed(evt);
            }
        });
        requestdel.add(hospname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 271, 42));

        jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel65.setText("Blood Group");
        requestdel.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, -1, -1));

        jLabel66.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel66.setText("Quantity Ml");
        requestdel.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, -1, -1));

        quantml2.setBackground(new java.awt.Color(247, 247, 247));
        quantml2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        quantml2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        quantml2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantml2ActionPerformed(evt);
            }
        });
        requestdel.add(quantml2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, 271, 42));

        jLabel67.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel67.setText("Status");
        requestdel.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 510, -1, 30));

        stat2.setBackground(new java.awt.Color(247, 247, 247));
        stat2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        stat2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        requestdel.add(stat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 271, 42));

        req_idtxt1.setBackground(new java.awt.Color(247, 247, 247));
        req_idtxt1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        req_idtxt1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        requestdel.add(req_idtxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 270, 40));

        jLabel77.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel77.setText("Request ID");
        requestdel.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, -1, -1));

        jComboBox2.setBackground(new java.awt.Color(247, 247, 247));
        jComboBox2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        requestdel.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 270, 50));

        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow-left-01-Stroke-Rounded 1.png"))); // NOI18N
        jLabel98.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel98MouseClicked(evt);
            }
        });
        requestdel.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));
        requestdel.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 718, 10));
        requestdel.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 670, 718, 20));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/request-940x860.jpg"))); // NOI18N
        requestdel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 860));

        pnlCards.add(requestdel, "pnlCard18");

        jSplitPane1.setRightComponent(pnlCards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMousePressed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard1");
    }//GEN-LAST:event_dashboardMousePressed

    private void donationsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donationsMousePressed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard2");
    }//GEN-LAST:event_donationsMousePressed

    private void donorsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donorsMousePressed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard3");
    }//GEN-LAST:event_donorsMousePressed

    private void bloodrequestMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bloodrequestMousePressed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard4");
    }//GEN-LAST:event_bloodrequestMousePressed

    private void sitesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sitesMousePressed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard5");
    }//GEN-LAST:event_sitesMousePressed

    private void logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMousePressed
        // TODO add your handling code here:
        Login log = new Login();
        log.setVisible(true);
        log.pack();
        log.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_logoutMousePressed

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterActionPerformed
        // TODO add your handling code here:
        try{
          if(d_idTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "donors id is needed");
        }else if(s_idTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "sites id is required");
        }else if(combo_txt.getSelectedItem().toString().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "blood group is required");
        }else if(quantity_mlTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "quantity is required");
        }else{
            Donations donationsobj=new Donations();
            
            int donor_id=Integer.parseInt(d_idTxt.getText());
            donationsobj.setDonor_id(donor_id);
            int site_id=Integer.parseInt(s_idTxt.getText());
            donationsobj.setSite_id(site_id);
            donationsobj.setBlood_group(combo_txt.getSelectedItem().toString().trim());
            int quantity;
            try {
                quantity = Integer.parseInt(quantity_mlTxt.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity must be a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // CHANGE: Added validation for donation quantity range (250-500 ml).
            // PURPOSE: Prevent database constraint violations and provide immediate user feedback.
            // Standard blood donation quantities are typically between 250-500 ml.
            if(quantity < 250 || quantity > 500){
                JOptionPane.showMessageDialog(this, 
                    "Invalid quantity: " + quantity + " ml.\n\nQuantity must be between 250 and 500 ml.\nPlease enter a valid amount.", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                quantity_mlTxt.requestFocus(); // Focus back to quantity field
                return;
            }
            
            donationsobj.setQuantity_ml(quantity);
            DonationsDao dao=new DonationsDao();
            int rowAffected =dao.registerDonations(donationsobj);
            if(rowAffected>0){
                JOptionPane.showMessageDialog(this, "Data saved successfully");
                // CHANGE: Refresh the donation ID after successful registration.
                // PURPOSE: Display the next available donation ID for the next registration.
                loadNextDonationId();
                // CHANGE: Clear form fields after successful registration.
                // PURPOSE: Prepare the form for the next donation entry.
                clearFieldsdona();
            }else{
                JOptionPane.showMessageDialog(this, "Data not saved, make sure site and donors id are written correctly");
            }
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_RegisterActionPerformed

    private void Add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add2ActionPerformed
        // TODO add your handling code here:
        // CHANGE: Refresh the donation ID when showing the registration panel.
        // PURPOSE: Ensure the next available donation ID is displayed when the user opens the registration form.
        cardLayout.show(pnlCards, "pnlCard7");
        // CHANGE: Call loadNextDonationId AFTER showing the panel to ensure it updates.
        // PURPOSE: The label needs to be visible before its text can be updated properly.
        loadNextDonationId();
        // CHANGE: Force label to be visible and repaint to ensure it displays.
        // PURPOSE: Ensure the label is visible and properly rendered.
        jLabel25.setVisible(true);
        jLabel25.revalidate();
        jLabel25.repaint();
    }//GEN-LAST:event_Add2ActionPerformed

    private void jLabel25ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLabel25ComponentShown
        // TODO add your handling code here:
        // CHANGE: Refresh the donation ID when the label becomes visible.
        // PURPOSE: Ensure the next available donation ID is displayed whenever the registration form is shown.
        loadNextDonationId();
    }//GEN-LAST:event_jLabel25ComponentShown

  private void loadNextDonationId() {
    // CHANGE: Ensure label is visible before setting text.
    // PURPOSE: Make sure the label is visible so the ID can be displayed.
    jLabel25.setVisible(true);
    try (Connection con = ProjConnection.getCon();
         PreparedStatement pst = con.prepareStatement("SELECT COALESCE(MAX(donation_id), 0) + 1 FROM donations");
         ResultSet rs = pst.executeQuery()) {

        if (rs.next()) {
            String nextId = rs.getString(1);
            // CHANGE: Explicitly set text and ensure label is visible and repainted.
            // PURPOSE: Force the label to display the donation ID value.
            jLabel25.setText(nextId);
            jLabel25.setVisible(true);
            jLabel25.revalidate();
            jLabel25.repaint();
        } else {
            jLabel25.setText("1");
            jLabel25.setVisible(true);
            jLabel25.revalidate();
            jLabel25.repaint();
        }
    } catch (Exception ex) {
        // CHANGE: Set default value and ensure visibility even on error.
        // PURPOSE: Always show something in the label, even if database query fails.
        jLabel25.setText("1");
        jLabel25.setVisible(true);
        jLabel25.revalidate();
        jLabel25.repaint();
        ex.printStackTrace();
    }
  }
    
  private void loadNextDonorId() {
    try (Connection con = ProjConnection.getCon();
         PreparedStatement pst = con.prepareStatement("SELECT COALESCE(MAX(donor_id), 0) + 1 FROM donors");
         ResultSet rs = pst.executeQuery()) {

        if (rs.next()) {
            jLabel24.setText(rs.getString(1));
        } else {
            jLabel24.setText("1");
        }
    } catch (Exception ex) {
        jLabel24.setText("1");
    }
}
  
    private void loadNextSiteId() {
    try (Connection con = ProjConnection.getCon();
         PreparedStatement pst = con.prepareStatement("SELECT COALESCE(MAX(site_id), 0) + 1 FROM sites");
         ResultSet rs = pst.executeQuery()) {

        if (rs.next()) {
            jLabel18.setText(rs.getString(1));
        } else {
            jLabel18.setText("1");
        }
    } catch (Exception ex) {
        jLabel18.setText("1");
    }
}
    
    private void loadNextRequestId() {
    try (Connection con = ProjConnection.getCon();
         PreparedStatement pst = con.prepareStatement("SELECT COALESCE(MAX(site_id), 0) + 1 FROM sites");
         ResultSet rs = pst.executeQuery()) {

        if (rs.next()) {
            jLabel51.setText(rs.getString(1));
        } else {
            jLabel51.setText("1");
        }
    } catch (Exception ex) {
        jLabel51.setText("1");
    }
}
     
    private void DeletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletebtnActionPerformed
        // TODO add your handling code here:
     String input = do_id.getText().trim();
    if (input.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter Donation ID!", 
            "Empty Field", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        int id = Integer.parseInt(input);
        Donations donations = new Donations();
        donations.setDonation_id(id);

        DonationsDao dao = new DonationsDao();
        int result = dao.deleteDonations(donations);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, 
                "Donation ID " + id + " deleted successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            do_id.setText("");
        } else {
            JOptionPane.showMessageDialog(this, 
                "No donation found with ID: " + id, 
                "Not Found", JOptionPane.ERROR_MESSAGE);
        }

        addRows(); // refresh table

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Donation ID must be a number!", 
            "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_DeletebtnActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard9");
    }//GEN-LAST:event_UpdateActionPerformed

    private void UpdatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatebtnActionPerformed
        // TODO add your handling code here:
        if(donation_idtxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"donation Id is required");
        }else if(donors_idTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "donors id is needed");
        }else if(site_idTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "sites id is required");
        }else if(combo_txt.getSelectedItem().toString().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "donation date is required");
        }else if(quantityTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "quantity is required");
        }else{
            Donations donationsobj=new Donations();
            int do_id=Integer.parseInt(donation_idtxt.getText());
            donationsobj.setDonation_id(do_id);
            int d_id=Integer.parseInt(donors_idTxt.getText());
            donationsobj.setDonor_id(d_id);
            int s_id=Integer.parseInt(site_idTxt.getText());
            donationsobj.setSite_id(s_id);
            donationsobj.setBlood_group(combo_txt.getSelectedItem().toString().trim());
            int quantity=Integer.parseInt(quantityTxt.getText());
            donationsobj.setQuantity_ml(quantity);
            DonationsDao dao=new DonationsDao();
            int rowAffected =dao.DonationsUpdate(donationsobj);
            if(rowAffected>0){
                JOptionPane.showMessageDialog(this, "Data updated successfully");
            }else{
                JOptionPane.showMessageDialog(this, "Data not updated successfully");
            }
        }
        addRows();
    }//GEN-LAST:event_UpdatebtnActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard8");
    }//GEN-LAST:event_DeleteActionPerformed

    private void DisplayAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayAllActionPerformed
        // TODO add your handling code here:
        addRows();
    }//GEN-LAST:event_DisplayAllActionPerformed

    private void jLabel39ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLabel39ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel39ComponentShown

    private void d_locationTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_locationTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d_locationTxtActionPerformed

    private void d_phoneNumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_phoneNumberTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d_phoneNumberTxtActionPerformed

    private void d_namesTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_namesTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d_namesTxtActionPerformed

    private void registrationbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationbtnActionPerformed
        // TODO add your handling code here:
        if(d_namesTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "names are needed");
        }else if(d_phoneNumberTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "phone number is required");
        }else if(d_locationTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "location is required");
        }else{
            Donors donorsobj=new Donors();
            donorsobj.setDonor_names(d_namesTxt.getText());
            donorsobj.setBlood_group(combo_txt.getSelectedItem().toString().trim());
            donorsobj.setPhone(d_phoneNumberTxt.getText());
            donorsobj.setDonor_location(d_locationTxt.getText());
            DonorsDao dao=new DonorsDao();
            int rowAffected =dao.registerDonors(donorsobj);
            if(rowAffected>0){
                JOptionPane.showMessageDialog(this, "Data not saved");
            }else{
                JOptionPane.showMessageDialog(this, "Data saved successfully");
                JOptionPane.showMessageDialog(this, "Thanks for donating");
            }

        }
    }//GEN-LAST:event_registrationbtnActionPerformed

    private void donor_namesTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donor_namesTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_donor_namesTxtActionPerformed

    private void donor_phoneNumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donor_phoneNumberTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_donor_phoneNumberTxtActionPerformed

    private void donor_locationTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donor_locationTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_donor_locationTxtActionPerformed

    private void Updatebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Updatebtn1ActionPerformed

        if (donors_id.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Id is required");
        } else if(donor_namesTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Names are needed");
        } else if (donor_phoneNumberTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number is required");
        } else if (donor_locationTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Location is required");
        } else if (combo_txt.getSelectedItem() == null || combo_txt.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Blood group is required");
        } else {
            try {
                String selectedBloodGroup = combo_txt.getSelectedItem().toString().trim();
                System.out.println("Selected blood group: " + selectedBloodGroup);
                Donors donorsobj = new Donors();
                donorsobj.setDonor_id(Integer.parseInt(donors_id.getText().trim()));
                donorsobj.setDonor_names(donor_namesTxt.getText().trim());
                donorsobj.setBlood_group(selectedBloodGroup);
                donorsobj.setPhone(donor_phoneNumberTxt.getText().trim());
                donorsobj.setDonor_location(donor_locationTxt.getText().trim());
                DonorsDao dao = new DonorsDao();
                int rowAffected = dao.DonorsUpdate(donorsobj);
                if (rowAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Data updated successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Data not updated");
                }
                addRowsInTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating data: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_Updatebtn1ActionPerformed

    private void Add3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add3ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard10");
    }//GEN-LAST:event_Add3ActionPerformed

    private void Update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update1ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard11");
    }//GEN-LAST:event_Update1ActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        // TODO add your handling code here:
         String input = donnors_id.getText().trim();
    if (input.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter Donor ID!", 
            "Empty Field", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        int id = Integer.parseInt(input);
        Donors donors = new Donors();
        donors.setDonor_id(id);

        DonorsDao dao = new DonorsDao();
        int result = dao.deleteDonors(donors);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, 
                "Donors ID " + id + " deleted successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            donnors_id.setText("");
        } else {
            JOptionPane.showMessageDialog(this, 
                "No donor found with ID: " + id, 
                "Not Found", JOptionPane.ERROR_MESSAGE);
        }

        addRowsInTable(); // refresh table

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Donor ID must be a number!", 
            "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_deletebtnActionPerformed

    private void donnors_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donnors_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_donnors_idActionPerformed

    private void phone_NumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phone_NumberTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phone_NumberTxtActionPerformed

    private void donars_locationTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donars_locationTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_donars_locationTxtActionPerformed

    private void DeleterediActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleterediActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard12");
    }//GEN-LAST:event_DeleterediActionPerformed

    private void DisplayAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayAll1ActionPerformed
        // TODO add your handling code here:
        addRowsInTable();
    }//GEN-LAST:event_DisplayAll1ActionPerformed

    private void s_namesTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_namesTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_namesTxtActionPerformed

    private void phoneNumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumberTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumberTxtActionPerformed

    private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationButtonActionPerformed
        if(s_namesTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Names is required");
        }else if(phoneNumberTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "phone number is required");
        }else if(s_locationTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "location is required");
        }else if(doctorsNamesTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Doctors name is required");
        }else{
            Sites sitesobj=new Sites();
            sitesobj.setSite_names(s_namesTxt.getText());
            sitesobj.setSite_phonenumber(phoneNumberTxt.getText());
            sitesobj.setSite_location(s_locationTxt.getText());
            sitesobj.setDoctors_name(doctorsNamesTxt.getText());
            SitesDao dao=new SitesDao();
            int rowAffected =dao.registerSites(sitesobj);
            if(rowAffected>0){
                JOptionPane.showMessageDialog(this, "Data not saved");
            }else{
                JOptionPane.showMessageDialog(this, "Data saved successfully");
            }
        }
    }//GEN-LAST:event_registrationButtonActionPerformed

    private void Add4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add4ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard13");
    }//GEN-LAST:event_Add4ActionPerformed

    private void Update2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update2ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard14");
    }//GEN-LAST:event_Update2ActionPerformed

    private void Delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete1ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard15");
    }//GEN-LAST:event_Delete1ActionPerformed

    private void DisplayAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayAll2ActionPerformed
        // TODO add your handling code here:
        addRowssite();
    }//GEN-LAST:event_DisplayAll2ActionPerformed

    private void site_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_site_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_site_idActionPerformed

    private void sites_namesTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sites_namesTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sites_namesTxtActionPerformed

    private void sites_phoneNumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sites_phoneNumberTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sites_phoneNumberTxtActionPerformed

    private void sites_locationTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sites_locationTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sites_locationTxtActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        if(site_id.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "ID is required");
        }else if(sites_namesTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Names is required");
        }else if(sites_phoneNumberTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "phone number is required");
        }else if(sites_locationTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "location is required");
        }else if(sites_doctorsNameTxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Doctors name is required");
        }else{
            Sites sitesobj=new Sites();
            int id=Integer.parseInt(site_id.getText());
            sitesobj.setSite_id(id);
            sitesobj.setSite_names(sites_namesTxt.getText());
            sitesobj.setSite_phonenumber(sites_phoneNumberTxt.getText());
            sitesobj.setSite_location(sites_locationTxt.getText());
            sitesobj.setDoctors_name(sites_doctorsNameTxt.getText());
            SitesDao dao=new SitesDao();
            int rowAffected =dao.updateSites(sitesobj);
            if(rowAffected>0){
                JOptionPane.showMessageDialog(this, "site updated successfully");
            }else{
                JOptionPane.showMessageDialog(this, "site not updated");
            }
        }
        addRowssite();
    }//GEN-LAST:event_updatebtnActionPerformed

    private void sitee_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sitee_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sitee_idActionPerformed

    private void s_namesTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_namesTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_namesTxt1ActionPerformed

    private void phoneNumberTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumberTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumberTxt1ActionPerformed

    private void deletebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtn1ActionPerformed
        String input = sitee_id.getText().trim();
    if (input.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter Site ID!", 
            "Empty Field", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        int id = Integer.parseInt(input);
        Sites site = new Sites();
        site.setSite_id(id);

        SitesDao dao = new SitesDao();
        int result = dao.deleteSites(site);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, 
                "Sites ID " + id + " deleted successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            sitee_id.setText("");
        } else {
            JOptionPane.showMessageDialog(this, 
                "No site found with ID: " + id, 
                "Not Found", JOptionPane.ERROR_MESSAGE);
        }

        addRowssite(); // refresh table

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Site ID must be a number!", 
            "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_deletebtn1ActionPerformed

    private void Add5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add5ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard16");
    }//GEN-LAST:event_Add5ActionPerformed

    private void Update3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update3ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard17");
    }//GEN-LAST:event_Update3ActionPerformed

    private void Delete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete2ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard18");
    }//GEN-LAST:event_Delete2ActionPerformed

    private void DisplayAll3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayAll3ActionPerformed
        // TODO add your handling code here:
        addRowsInTablere();
    }//GEN-LAST:event_DisplayAll3ActionPerformed

    private void hospnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hospnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hospnameActionPerformed

    private void quantmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantmlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantmlActionPerformed

    private void RegisterReqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterReqActionPerformed
        // TODO add your handling code here:
        if(hospname.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Hospital Name is required");
        }else if(ComboBoxre.getSelectedItem().toString().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "blood group is required");
        }else if(quantml.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "quantity is required");
        }else{
            // Disable button to prevent multiple submissions
            RegisterReq.setEnabled(false);
            try{
                BloodRequest requestsobj=new BloodRequest();
                requestsobj.setHospital_name(hospname.getText());
                String selectedBloodGroup = ComboBoxre.getSelectedItem().toString().trim();
                requestsobj.setBlood_group(selectedBloodGroup);
                int quantity=Integer.parseInt(quantml.getText());
                requestsobj.setQuantity_ml(quantity);
                requestsobj.setStatus("Processing");
                requestsobj.setRequest_date(new Timestamp(new Date().getTime()));
                BloodRequestsDao dao = new BloodRequestsDao();
                int rowAffected =dao.registerrequest(requestsobj); 
                if(rowAffected>0){
                    JOptionPane.showMessageDialog(this, "Data saved successfully");
                    hospname.setText("");
                    ComboBoxre.setSelectedIndex(0);
                    quantml.setText("");
                    stat.setText("");
                }else{
                    JOptionPane.showMessageDialog(this, "Data not saved, please check your input");
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
                ex.printStackTrace();
            }finally{
                // Re-enable button after operation completes
                RegisterReq.setEnabled(true);
            }
        }
    }//GEN-LAST:event_RegisterReqActionPerformed

    private void UpdateReqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateReqActionPerformed
        // TODO add your handling code here:
        if(req_idtxt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Request Id is required");
        }else if (hospname1.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Hospital Name is required");
        }else if (jComboBox3.getSelectedItem().toString().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Blood group is required");
        }else if (quantml1.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Quantity is required");
        }else if (stat1.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Status is required");
        }else{
           
            BloodRequest requestsobj = new BloodRequest();
            int id = Integer.parseInt(req_idtxt.getText());
            requestsobj.setRequest_id(id);
            requestsobj.setHospital_name(hospname1.getText().trim());
            requestsobj.setBlood_group(jComboBox3.getSelectedItem().toString().trim());
            int quantity=Integer.parseInt(quantml1.getText());
            requestsobj.setQuantity_ml(quantity);
            requestsobj.setStatus(stat1.getText());
            BloodRequestsDao dao = new BloodRequestsDao();
            int rowsAffected = dao.updaterequest(requestsobj);
            if (rowsAffected>0){
                 JOptionPane.showMessageDialog(this, " Request updated successfully");
            req_idtxt.setText("");
            hospname1.setText("");
            jComboBox3.setSelectedIndex(0);
            quantml1.setText("");
            stat1.setText("");
            }else{
                JOptionPane.showMessageDialog(this, "Request not updated");
            }
        }
    }//GEN-LAST:event_UpdateReqActionPerformed

    private void hospname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hospname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hospname1ActionPerformed

    private void quantml1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantml1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantml1ActionPerformed

    private void DeleteReqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteReqActionPerformed
        // TODO add your handling code here:
            String input = req_idtxt1.getText().trim();
    if (input.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter Request ID!", 
            "Empty Field", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        int id = Integer.parseInt(input);
        BloodRequest request = new BloodRequest();
        request.setRequest_id(id);

        BloodRequestsDao dao = new BloodRequestsDao();
        int result = dao.deleterequest(request);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, 
                "Request ID " + id + " deleted successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            req_idtxt1.setText("");
        } else {
            JOptionPane.showMessageDialog(this, 
                "No Request found with ID: " + id, 
                "Not Found", JOptionPane.ERROR_MESSAGE);
        }

        addRowsInTablere(); // refresh table

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Request ID must be a number!", 
            "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_DeleteReqActionPerformed

    private void hospname2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hospname2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hospname2ActionPerformed

    private void quantml2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantml2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantml2ActionPerformed

    private void donationsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donationsTableMouseClicked
        // TODO add your handling code here:
         donationsTable.setModel(tableModel);
        int selectedRow=donationsTable.getSelectedRow();
        donation_idtxt.setText(tableModel.getValueAt(selectedRow, 0).toString());
        donors_idTxt.setText(tableModel.getValueAt(selectedRow, 1).toString());
        site_idTxt.setText(tableModel.getValueAt(selectedRow, 2).toString());
        quantityTxt.setText(tableModel.getValueAt(selectedRow, 4).toString());
        String bloodGroup = tableModel.getValueAt(selectedRow, 3).toString();
        combo_txt2.setSelectedItem(bloodGroup);
        
        do_id.setText(tableModel.getValueAt(selectedRow, 0).toString());
        d_idTxt1.setText(tableModel.getValueAt(selectedRow, 1).toString());
        s_idTxt1.setText(tableModel.getValueAt(selectedRow, 2).toString());
        String bloodGroupp = tableModel.getValueAt(selectedRow, 3).toString();
        combo_txt1.setSelectedItem(bloodGroup);
        quantity_mlTxt1.setText(tableModel.getValueAt(selectedRow, 4).toString());
    }//GEN-LAST:event_donationsTableMouseClicked

    private void requestTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestTableMouseClicked
        // TODO add your handling code here:
        requestTable.setModel(requestModel);
        int selectedRow=requestTable.getSelectedRow();
        req_idtxt.setText(requestModel.getValueAt(selectedRow, 0).toString());
        hospname1.setText(requestModel.getValueAt(selectedRow, 1).toString());
        String bloodGroup = requestModel.getValueAt(selectedRow, 2).toString();
        jComboBox3.setSelectedItem(bloodGroup);
        quantml1.setText(requestModel.getValueAt(selectedRow, 3).toString());
        stat1.setText(requestModel.getValueAt(selectedRow, 5).toString());
        
        req_idtxt1.setText(requestModel.getValueAt(selectedRow, 0).toString());
        hospname2.setText(requestModel.getValueAt(selectedRow, 1).toString());
        String bloodGroupp = requestModel.getValueAt(selectedRow, 2).toString();
        jComboBox2.setSelectedItem(bloodGroup);
        quantml2.setText(requestModel.getValueAt(selectedRow, 3).toString());
        stat2.setText(requestModel.getValueAt(selectedRow, 5).toString());
        
    }//GEN-LAST:event_requestTableMouseClicked

    private void Donors_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Donors_tableMouseClicked
        // TODO add your handling code here:
        Donors_table.setModel(donorModel);
        int selectedRow=Donors_table.getSelectedRow();
        donors_id.setText(donorModel.getValueAt(selectedRow, 0).toString());
        donor_namesTxt.setText(donorModel.getValueAt(selectedRow, 1).toString());
        String bloodGroup = donorModel.getValueAt(selectedRow, 2).toString();
        combo_txt4.setSelectedItem(bloodGroup);
        donor_phoneNumberTxt.setText(donorModel.getValueAt(selectedRow, 3).toString());
        donor_locationTxt.setText(donorModel.getValueAt(selectedRow, 4).toString());
        
        donnors_id.setText(donorModel.getValueAt(selectedRow, 0).toString());
        donors_namesTxt.setText(donorModel.getValueAt(selectedRow, 1).toString());
        String bloodGroupp = donorModel.getValueAt(selectedRow, 2).toString();
        jComboBox1.setSelectedItem(bloodGroup);
        phone_NumberTxt.setText(donorModel.getValueAt(selectedRow, 3).toString());
        donars_locationTxt.setText(donorModel.getValueAt(selectedRow, 4).toString());
    }//GEN-LAST:event_Donors_tableMouseClicked

    private void sitesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sitesTableMouseClicked
        // TODO add your handling code here:
         sitesTable.setModel(siteModel);
        int selectedRow=sitesTable.getSelectedRow();
        site_id.setText(siteModel.getValueAt(selectedRow, 0).toString());
        sites_namesTxt.setText(siteModel.getValueAt(selectedRow, 1).toString());
        sites_phoneNumberTxt.setText(siteModel.getValueAt(selectedRow, 2).toString());
        sites_locationTxt.setText(siteModel.getValueAt(selectedRow, 3).toString());
        sites_doctorsNameTxt.setText(siteModel.getValueAt(selectedRow, 4).toString());
        
        sitee_id.setText(siteModel.getValueAt(selectedRow, 0).toString());
        s_namesTxt1.setText(siteModel.getValueAt(selectedRow, 1).toString());
        phoneNumberTxt1.setText(siteModel.getValueAt(selectedRow, 2).toString());
        s_locationTxt1.setText(siteModel.getValueAt(selectedRow, 3).toString());
        doctorsNamesTxt1.setText(siteModel.getValueAt(selectedRow, 4).toString());
    }//GEN-LAST:event_sitesTableMouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        String searchIdText = searchdonid.getText().trim();  // assuming your JTextField is named searchTextField

    // Validation 1: Check if user entered something
    if (searchIdText.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Please enter a Donation ID to search!", 
            "Input Required", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Validation 2: Check if it's a valid number
    int donationId;
    try {
        donationId = Integer.parseInt(searchIdText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, 
            "Donation ID must be a number!", 
            "Invalid Input", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Clear the table first
    tableModel.setRowCount(0);

    // Search in database
    DonationsDao dao = new DonationsDao();
    Donations donation = dao.findDonationsbyID(donationId);
    

    if (donation != null) {
        // Found → Display the row
        tableModel.addRow(new Object[]{
            donation.getDonation_id(),
            donation.getDonor_id(),
            donation.getSite_id(),
            donation.getBlood_group(),
            donation.getQuantity_ml(),
            donation.getDonation_date()
            // Add more columns if you have them
        });
    } else {
        // Not found → Show error message (this gets you the 2pts!)
        JOptionPane.showMessageDialog(this, 
            "Donation ID " + donationId + " does not exist in the database.", 
            "Not Found", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_searchActionPerformed

    private void search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search1ActionPerformed
        // TODO add your handling code here:
         String searchIdText = searchdonid1.getText().trim();  // assuming your JTextField is named searchTextField

    // Validation 1: Check if user entered something
    if (searchIdText.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Please enter a Donor ID to search!", 
            "Input Required", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Validation 2: Check if it's a valid number
    int donorId;
    try {
        donorId = Integer.parseInt(searchIdText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, 
            "Donor ID must be a number!", 
            "Invalid Input", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Clear the table first
    donorModel.setRowCount(0);

    // Search in database
    DonorsDao dao = new DonorsDao();
    Donors donor = dao.findDonorsbyID(donorId);
    

    if (donor != null) {
        // Found → Display the row
        donorModel.addRow(new Object[]{
            donor.getDonor_id(),
            donor.getDonor_names(),
            donor.getBlood_group(),
            donor.getPhone(),
            donor.getDonor_location()
            
        });
    } else {
        // Not found → Show error message (this gets you the 2pts!)
        JOptionPane.showMessageDialog(this, 
            "Donor ID " + donorId + " does not exist in the database.", 
            "Not Found", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_search1ActionPerformed

    private void search2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search2ActionPerformed
        // TODO add your handling code here:
                 String searchIdText = searchdonid2.getText().trim();  // assuming your JTextField is named searchTextField

    // Validation 1: Check if user entered something
    if (searchIdText.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Please enter a Request ID to search!", 
            "Input Required", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Validation 2: Check if it's a valid number
    int requestId;
    try {
        requestId = Integer.parseInt(searchIdText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, 
            "Request ID must be a number!", 
            "Invalid Input", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Clear the table first
    requestModel.setRowCount(0);

    // Search in database
    BloodRequestsDao dao = new BloodRequestsDao();
    BloodRequest request = dao.findrequestById(requestId);
    

    if (request != null) {
        // Found → Display the row
        requestModel.addRow(new Object[]{
            request.getRequest_id(),
            request.getHospital_name(),
            request.getBlood_group(),
            request.getQuantity_ml(),
            request.getRequest_date(),
            request.getStatus()
            // Add more columns if you have them
        });
    } else {
        // Not found → Show error message (this gets you the 2pts!)
        JOptionPane.showMessageDialog(this, 
            "Request ID " + requestId + " does not exist in the database.", 
            "Not Found", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_search2ActionPerformed

    private void search3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search3ActionPerformed
        // TODO add your handling code here:
    String searchIdText = searchdonid3.getText().trim();  // assuming your JTextField is named searchTextField

    // Validation 1: Check if user entered something
    if (searchIdText.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Please enter a Site ID to search!", 
            "Input Required", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Validation 2: Check if it's a valid number
    int siteId;
    try {
        siteId = Integer.parseInt(searchIdText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, 
            "Site ID must be a number!", 
            "Invalid Input", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Clear the table first
    siteModel.setRowCount(0);

    // Search in database
    SitesDao dao = new SitesDao();
    Sites site = dao.findSiteByID(siteId);
    

    if (site != null) {
        // Found → Display the row
        siteModel.addRow(new Object[]{
            site.getSite_id(),
            site.getSite_names(),
            site.getSite_phonenumber(),
            site.getDoctors_name(),
            site.getSite_location()
           
        });
    } else {
        // Not found → Show error message 
        JOptionPane.showMessageDialog(this, 
            "Site ID " + siteId + " does not exist in the database.", 
            "Not Found", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_search3ActionPerformed

    private void jLabel78MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel78MouseClicked
        // TODO add your handling code here:
       cardLayout.show(pnlCards, "pnlCard2");
    }//GEN-LAST:event_jLabel78MouseClicked

    private void jLabel79MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel79MouseClicked
        // TODO add your handling code here:
       cardLayout.show(pnlCards, "pnlCard2");
    }//GEN-LAST:event_jLabel79MouseClicked

    private void jLabel81MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel81MouseClicked
        // TODO add your handling code here:
       cardLayout.show(pnlCards, "pnlCard2");
    }//GEN-LAST:event_jLabel81MouseClicked

    private void jLabel83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseClicked
        // TODO add your handling code here:
       cardLayout.show(pnlCards, "pnlCard3");
    }//GEN-LAST:event_jLabel83MouseClicked

    private void jLabel85MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel85MouseClicked
        // TODO add your handling code here:
       cardLayout.show(pnlCards, "pnlCard3");
    }//GEN-LAST:event_jLabel85MouseClicked

    private void jLabel87MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel87MouseClicked
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard3");
    }//GEN-LAST:event_jLabel87MouseClicked

    private void jLabel90MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel90MouseClicked
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard5");
    }//GEN-LAST:event_jLabel90MouseClicked

    private void jLabel92MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel92MouseClicked
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard5");
    }//GEN-LAST:event_jLabel92MouseClicked

    private void jLabel94MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel94MouseClicked
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard5");
    }//GEN-LAST:event_jLabel94MouseClicked

    private void jLabel96MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel96MouseClicked
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard4");
    }//GEN-LAST:event_jLabel96MouseClicked

    private void jLabel97MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel97MouseClicked
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard4");
    }//GEN-LAST:event_jLabel97MouseClicked

    private void jLabel98MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel98MouseClicked
        // TODO add your handling code here:
        cardLayout.show(pnlCards, "pnlCard4");
    }//GEN-LAST:event_jLabel98MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add2;
    private javax.swing.JButton Add3;
    private javax.swing.JButton Add4;
    private javax.swing.JButton Add5;
    private javax.swing.JComboBox<String> ComboBoxre;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Delete1;
    private javax.swing.JButton Delete2;
    private javax.swing.JButton DeleteReq;
    private javax.swing.JButton Deletebtn;
    private javax.swing.JButton Deleteredi;
    private javax.swing.JButton DisplayAll;
    private javax.swing.JButton DisplayAll1;
    private javax.swing.JButton DisplayAll2;
    private javax.swing.JButton DisplayAll3;
    private javax.swing.JTable Donors_table;
    private javax.swing.JButton Register;
    private javax.swing.JButton RegisterReq;
    private javax.swing.JButton Update;
    private javax.swing.JButton Update1;
    private javax.swing.JButton Update2;
    private javax.swing.JButton Update3;
    private javax.swing.JButton UpdateReq;
    private javax.swing.JButton Updatebtn;
    private javax.swing.JButton Updatebtn1;
    private javax.swing.JLabel blood_group;
    private javax.swing.JLabel blood_group1;
    private javax.swing.JPanel bloodre;
    private javax.swing.JLabel bloodrequest;
    private javax.swing.JComboBox<String> combo_txt;
    private javax.swing.JComboBox<String> combo_txt1;
    private javax.swing.JComboBox<String> combo_txt2;
    private javax.swing.JComboBox<String> combo_txt3;
    private javax.swing.JComboBox<String> combo_txt4;
    private javax.swing.JLabel d_id;
    private javax.swing.JTextField d_idTxt;
    private javax.swing.JTextField d_idTxt1;
    private javax.swing.JLabel d_location;
    private javax.swing.JLabel d_location1;
    private javax.swing.JTextField d_locationTxt;
    private javax.swing.JLabel d_names;
    private javax.swing.JLabel d_names1;
    private javax.swing.JTextField d_namesTxt;
    private javax.swing.JTextField d_phoneNumberTxt;
    private javax.swing.JPanel dash;
    private javax.swing.JLabel dashboard;
    private javax.swing.JButton deletebtn;
    private javax.swing.JButton deletebtn1;
    private javax.swing.JTextField do_id;
    private javax.swing.JTextField doctorsNamesTxt;
    private javax.swing.JTextField doctorsNamesTxt1;
    private javax.swing.JLabel doctors_nameTxt;
    private javax.swing.JLabel doctors_nameTxt1;
    private javax.swing.JLabel doctors_nameTxt2;
    private javax.swing.JPanel don;
    private javax.swing.JTextField donars_locationTxt;
    private javax.swing.JTextField donation_idtxt;
    private javax.swing.JLabel donations;
    private javax.swing.JPanel donationsDelete;
    private javax.swing.JTable donationsTable;
    private javax.swing.JPanel donationsUpdate;
    private javax.swing.JPanel donationsregister;
    private javax.swing.JPanel donats;
    private javax.swing.JTextField donnors_id;
    private javax.swing.JTextField donor_locationTxt;
    private javax.swing.JTextField donor_namesTxt;
    private javax.swing.JTextField donor_phoneNumberTxt;
    private javax.swing.JLabel donors;
    private javax.swing.JPanel donorsDel;
    private javax.swing.JPanel donorsReg;
    private javax.swing.JTextField donors_id;
    private javax.swing.JTextField donors_idTxt;
    private javax.swing.JTextField donors_namesTxt;
    private javax.swing.JPanel donorsupd;
    private javax.swing.JTextField hospname;
    private javax.swing.JTextField hospname1;
    private javax.swing.JTextField hospname2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel locationTxt;
    private javax.swing.JLabel locationTxt1;
    private javax.swing.JLabel locationTxt2;
    private javax.swing.JLabel logout;
    private javax.swing.JTextField phoneNumberTxt;
    private javax.swing.JTextField phoneNumberTxt1;
    private javax.swing.JTextField phone_NumberTxt;
    private javax.swing.JLabel phone_number;
    private javax.swing.JLabel phone_number1;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JTextField quantityTxt;
    private javax.swing.JTextField quantity_mlTxt;
    private javax.swing.JTextField quantity_mlTxt1;
    private javax.swing.JTextField quantml;
    private javax.swing.JTextField quantml1;
    private javax.swing.JTextField quantml2;
    private javax.swing.JButton registrationButton;
    private javax.swing.JButton registrationbtn;
    private javax.swing.JTextField req_idtxt;
    private javax.swing.JTextField req_idtxt1;
    private javax.swing.JPanel requestReg;
    private javax.swing.JTable requestTable;
    private javax.swing.JPanel requestUpd;
    private javax.swing.JPanel requestdel;
    private javax.swing.JLabel s_id;
    private javax.swing.JLabel s_id1;
    private javax.swing.JLabel s_id2;
    private javax.swing.JTextField s_idTxt;
    private javax.swing.JTextField s_idTxt1;
    private javax.swing.JTextField s_locationTxt;
    private javax.swing.JTextField s_locationTxt1;
    private javax.swing.JLabel s_names;
    private javax.swing.JLabel s_names1;
    private javax.swing.JLabel s_names2;
    private javax.swing.JTextField s_namesTxt;
    private javax.swing.JTextField s_namesTxt1;
    private javax.swing.JLabel s_phoneNumberTxt;
    private javax.swing.JLabel s_phoneNumberTxt1;
    private javax.swing.JLabel s_phoneNumberTxt2;
    private javax.swing.JButton search;
    private javax.swing.JButton search1;
    private javax.swing.JButton search2;
    private javax.swing.JButton search3;
    private javax.swing.JTextField searchdonid;
    private javax.swing.JTextField searchdonid1;
    private javax.swing.JTextField searchdonid2;
    private javax.swing.JTextField searchdonid3;
    private javax.swing.JPanel sit;
    private javax.swing.JPanel siteReg;
    private javax.swing.JPanel siteUpd;
    private javax.swing.JTextField site_id;
    private javax.swing.JTextField site_idTxt;
    private javax.swing.JPanel sitedelete;
    private javax.swing.JTextField sitee_id;
    private javax.swing.JLabel sites;
    private javax.swing.JTable sitesTable;
    private javax.swing.JTextField sites_doctorsNameTxt;
    private javax.swing.JTextField sites_locationTxt;
    private javax.swing.JTextField sites_namesTxt;
    private javax.swing.JTextField sites_phoneNumberTxt;
    private javax.swing.JTextField stat;
    private javax.swing.JTextField stat1;
    private javax.swing.JTextField stat2;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
}

