/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author hp
 *
 */
import java.awt.*;
import javax.swing.*;
import inventory.StockMngt;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import money.SalesMngt;
import money.Report;
import inventory.ItemList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import entities.UserRegistration;
import entities.SignUp;
import functionality.Function;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JToolBar;
import javax.imageio.ImageIO;
import functionality.Discounting;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.LocalTime.now;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static system.UserLogIn.Nametxt;

public class TellerPos extends JFrame {

    private SalesMngt sls;
    private StockMngt stk;
    private Report Rpts;
    private final JMenuBar bar;
    private final JToolBar toolbar;
    private UserRegistration us;
    private SignUp signup;
    private Function func;
    private final JDesktopPane theDesktop;
    private Discounting disc;
    private ItemList list;
    public JMenu file, edit, sales, stocks, customers, reports, suppliers, help;
    public JMenuItem RcvStc, Stckmvnt, onhand,stockreport,prices,ItemList,NewItemList,
            Company,Admin,importdb,backup,Users,User_Reg,slsbyDate,slsbyRep,slsbyCat,
        slsbyItem,slssummary,suppCat,suppRegn,suppBill,suppInfo,suppRec,suppComp,suppnew,
        suppRet,editEdit,finStmts,banking ;

    public TellerPos() throws SQLException {
        // CREATE AN INSTANCES OF USERREGISTRATION,SIGNUP,DISCOUNTING,ITEMLIST AND FUNCTION CLASSES
        us = new UserRegistration();
       
       
        signup = new SignUp();
        disc = new Discounting();
        list = new ItemList();
        func = new Function();
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            frame.setIconImage(ImageIO.read(new File("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\favicon.PNG")));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "image icon not found", "icon", 1);
        }

//==============================================================================    
        String Today = LocalDate.now().toString();// CREATE STRING VARRIABLE STORING THE DATE VALUE
        LocalTime time = now().truncatedTo(ChronoUnit.SECONDS);//CREATE STRING VARRIABLE STORING THE TIME VALUE

        // CREATE TEXFIELDS TO SET AND GET THE DATE AND TIME VALUES
        JTextField TD = new JTextField(Today);
        JTextField tm = new JTextField(Time.valueOf(time).toString());
        JTextField ID = new JTextField(disc.UserExist());
        JTextField OutActivity = new JTextField("LogOut");

        //======================================================================
        theDesktop = new JDesktopPane();
        add(theDesktop);
        //======================================================================
        //CREATE MENU BAR AND ITEMS
        bar = new JMenuBar();
        bar.setBackground(Color.BLACK);

        toolbar = new JToolBar();
        toolbar.setBackground(Color.GRAY);

        //======================================================================  
        //CREATE MENU ITEMS        
        file = new JMenu("File");
        file = new JMenu("File");
        file.setMnemonic('F');
        file.setFont(new Font("Arial", Font.PLAIN, 15));
        file.setForeground(Color.BLACK);

        edit = new JMenu("Edit");
        edit = new JMenu("Edit");
        edit.setMnemonic('E');
        edit.setFont(new Font("Arial", Font.PLAIN, 15));
        edit.setForeground(Color.BLACK);

        sales = new JMenu("Sales");
        sales.setMnemonic('s');
        sales.setFont(new Font("Arial", Font.PLAIN, 15));
        sales.setForeground(Color.BLACK);

        stocks = new JMenu("Stock");
        stocks.setMnemonic('t');
        stocks.setFont(new Font("Arial", Font.PLAIN, 15));
        stocks.setForeground(Color.BLACK);

        customers = new JMenu("Customers");
        customers.setMnemonic('c');
        customers.setFont(new Font("Arial", Font.PLAIN, 15));
        customers.setForeground(Color.BLACK);

        reports = new JMenu("Reports");
        reports.setEnabled(true);
        reports.setMnemonic('R');
        reports.setFont(new Font("Arial", Font.PLAIN, 15));
        reports.setForeground(Color.BLACK);

        suppliers = new JMenu("Suppliers");
        suppliers.setMnemonic('p');
        suppliers.setFont(new Font("Arial", Font.PLAIN, 15));
        suppliers.setForeground(Color.BLACK);

        help = new JMenu("Help");
        help.setMnemonic('H');
        help.setFont(new Font("Arial", Font.PLAIN, 15));
        help.setForeground(Color.BLACK);

        //========================================================================
        //CREATE STOCK MENU ITEMS
        ImageIcon Itemlist, lists;
        lists = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\list.png");
        Itemlist = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\newListItem.png");

        RcvStc = new JMenuItem("Recieve Stock");
        RcvStc.addActionListener((ActionEvent e) -> {
            if (e.getSource() == RcvStc) {
                stk.setVisible(true);
                sls.setVisible(false);
                SwingUtilities.invokeLater(() -> {
                    try {
                        func.ComboCategory();
                        func.ComboOthers();
                        func.ComboItems2();
                    } catch (SQLException ex) {
                        Logger.getLogger(TellerPos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

            }
        });
        Stckmvnt = new JMenuItem("Stock Movement");
        onhand = new JMenuItem("Stock on Hand");
        stockreport = new JMenuItem("Stock Reports");
        prices = new JMenuItem("Price Management");
        ItemList = new JMenuItem("Item List", lists);
        NewItemList = new JMenuItem("New List Item ", Itemlist);
        NewItemList.addActionListener((ActionEvent e) -> {
            if (e.getSource() == NewItemList) {
                list.dialog.setVisible(true);

            }
        });

        //========================================================================
        //CREATE FILE MENU ITEMS
        //CREATE FILE MENU ITEMS IMAGE ICONS
        //==================================
        ImageIcon exitIcon, admin, printer, userreg, user, home, impot, export;
        exitIcon = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\exit.png");
        admin = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\sysadmin.png");
        printer = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\fileprint.png");
        userreg = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\userreg.png");
        user = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\user.png");
        home = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\gohome.png");
        impot = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\import.png");
        export = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\export.png");

        //==================================================
        Company = new JMenuItem("Company SetUp", home);
        Admin = new JMenuItem("Admin", admin);
        Admin.addActionListener((ActionEvent e) -> {
            
           
                disc.Adminhelp();
           
            
             });
        final JMenuItem Print = new JMenuItem("Print", printer);
        Print.setMnemonic('P');
        Print.addActionListener((ActionEvent e) -> {
            if (e.getSource() == Print) {
                disc.dispStock();
                disc.validatextFields();
                disc.dispStock();

            }
        });

        User_Reg = new JMenuItem("User Registration", userreg);
        User_Reg.setMnemonic('U');
        User_Reg.addActionListener((ActionEvent e) -> {
            us.dialog.setVisible(true);
        });
        Users = new JMenuItem("User SignUp", user);
        Users.setMnemonic('U');
        Users.addActionListener((ActionEvent e) -> {
            signup.dialog.setVisible(true);
        });

        JMenuItem exit = new JMenuItem("Exit", exitIcon);
        exit.setMnemonic('X');
        exit.addActionListener((ActionEvent event) -> {
            disc.logOutAudit( Integer.parseInt(ID.getText()),Nametxt.getText(),
                    OutActivity.getText(), Time.valueOf(tm.getText()), Date.valueOf(TD.getText()));

            System.exit(0);
        });
        importdb = new JMenuItem("Import Files and Settings", impot);
        importdb.addActionListener((ActionEvent event) -> {
            disc.Restoredbfromsql("DataBaseBackup.sql");
        });

        backup = new JMenuItem("Back Up Files and Settings", export);
        backup.addActionListener((ActionEvent event) -> {
            disc.Backupdbtosql();
        });

        //==========================================================================     
        //CREATE SALES MENU ITEMS
        //==========================================================================     
        ImageIcon img = null;

        final JMenuItem New_Sale = new JMenuItem("New_Sale", img);
        New_Sale.setMnemonic('N');
        New_Sale.addActionListener((ActionEvent e) -> {
            if (e.getSource() == New_Sale) {
                stk.setVisible(false);
                sls.setVisible(true);

            }
        });

        slsbyDate = new JMenuItem("Report By Date");
        slsbyRep = new JMenuItem("Report By Rep");
        slsbyCat = new JMenuItem("Report By Category");
        slsbyItem = new JMenuItem("Report By Item");
        slssummary = new JMenuItem("Sales Summary");

        //==========================================================================
        //CREATE SUPPLIER MENU ITEMS
        //==========================================================================    
        suppCat = new JMenuItem("Supplier By Item Category");
        suppRegn = new JMenuItem("Supplier By Region");
        suppBill = new JMenuItem("Supplier By Bill");
        suppInfo = new JMenuItem("Supplier Name");
        suppRec = new JMenuItem("Supplier Record");
        suppComp = new JMenuItem("Supplier Price Compare");
        suppnew = new JMenuItem("New Supplier");
        suppRet = new JMenuItem("Supplier Returns");

        //==========================================================================
        //CREATE REPORTS MENU ITEMS
        //==========================================================================     
        //CREATE REPORT MENU ITEMS IMAGE ICONS
        //======================================== 
        ImageIcon charts, financial;
        charts = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\charts.png");
        financial = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\cash.png");
        //============================================

        JMenuItem systReports = new JMenuItem("All Reports");
        systReports.addActionListener((ActionEvent ae) -> {
            try {
                Rpts = new Report();
            } catch (SQLException ex) {
                Logger.getLogger(TellerPos.class.getName()).log(Level.SEVERE, null, ex);
            }
            Rpts.setVisible(true);

            this.setVisible(false);

        });
        finStmts = new JMenuItem("Finacial Statements");
        JMenuItem graphs = new JMenuItem("Graphs and Charts", charts);
        JMenuItem stockrpts = new JMenuItem("Stock Reports");
        JMenuItem salesrpts = new JMenuItem("Sales Reports");
        JMenuItem purchases = new JMenuItem("Purchases Reports");
        banking = new JMenuItem("Banking", financial);
        JMenuItem TransSumm = new JMenuItem("Summary");
        JMenuItem Returns = new JMenuItem("Returns");

        //=======================================================================     
        //CREATE EDIT MENU ITEMS
        //CREATE EDIT MENU ITEM ICON IMAGES
        ImageIcon reload, undo, search, cut, fedit, copy;
        reload = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\reload.png");
        undo = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\redo.png");
        search = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\search22.png");
        cut = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\editcut.png");
        fedit = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\edit.png");
        copy = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\copy.png");
        //===================================================

        JMenuItem editundo = new JMenuItem("Undo", undo);
        JMenuItem editref = new JMenuItem("Refresh", reload);
        editref.addActionListener((ActionEvent e) -> {
            if (e.getSource() == editref) {
                disc.dispStock();// REFRESH STOSK TABLE ITEMS

            }
        });

        JMenuItem editcopy = new JMenuItem("Copy", copy);
        JMenuItem editcut = new JMenuItem("Cut", cut);
        JMenuItem editfind = new JMenuItem("Search", search);
        editEdit = new JMenuItem("Edit", fedit);

        //======================================================================    
        //CREATE CUSTOMER MENU ITEMS
        //CREATE EDIT MENU ITEM ICON IMAGES
        //=======================================
        ImageIcon cust;
        cust = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller POS"
                + "\\src\\images\\customer.png");
        //===============================================

        JMenuItem CustCat = new JMenuItem("Customer By Item Category         >");
        JMenuItem CustRegn = new JMenuItem("Customer By Region");
        JMenuItem CustBill = new JMenuItem("Customer By Bill");
        JMenuItem CustInfo = new JMenuItem("Customer Contact Info");
        JMenuItem CustRec = new JMenuItem("Customer Record");
        JMenuItem Custnew = new JMenuItem("New Customer", cust);
        JMenuItem CustRet = new JMenuItem("Customer Returns");

        //=======================================================================
        //CREATE HELP MENU ITEMS
        JMenuItem reg = new JMenuItem("Register");
        JMenuItem License = new JMenuItem("License");
        JMenuItem guide = new JMenuItem("Help");
        JMenuItem contact = new JMenuItem("Contact Us");
        JMenuItem about = new JMenuItem("About Teller POS");
        JMenuItem update = new JMenuItem("Updates");

        //=======================================================================
        //ADD EDIT MENU ITEMS
        edit.add(editref);
        edit.add(editundo);
        edit.addSeparator();
        edit.add(editcopy);
        edit.add(editcut);
        edit.add(editEdit);
        edit.addSeparator();
        edit.add(editfind);
        edit.add(Box.createHorizontalStrut(150));

        //=======================================================================     
        // ADD ITEMS TO CUSTOMER MENU
        customers.add(Custnew);
        customers.addSeparator();
        customers.add(CustInfo);
        customers.add(CustRegn);
        customers.add(CustCat);
        customers.addSeparator();
        customers.add(CustRec);
        customers.add(CustBill);
        customers.addSeparator();
        customers.add(CustRet);
        customers.add(Box.createHorizontalStrut(150));
        //======================================================================

        //ADD ITEMS TO SUPPLIER MENU
        suppliers.add(suppnew);
        suppliers.addSeparator();
        suppliers.add(suppInfo);
        suppliers.add(suppRegn);
        suppliers.addSeparator();
        suppliers.add(suppCat);
        suppliers.add(suppRec);
        suppliers.add(suppComp);
        suppliers.addSeparator();
        suppliers.add(suppBill);
        suppliers.add(suppRet);
        suppliers.add(Box.createHorizontalStrut(150));

        //=======================================================================          
        //ADD ITEMS TO REPORTS MENU
        reports.add(systReports);
        reports.addSeparator();
        reports.add(stockrpts);
        reports.add(salesrpts);
        reports.add(purchases);
        reports.add(Returns);
        reports.addSeparator();
        reports.add(finStmts);
        reports.add(banking);
        reports.addSeparator();
        reports.add(graphs);
        reports.add(TransSumm);
        reports.add(Box.createHorizontalStrut(150));

        //===================================================================
        //ADD ITEMS TO SALES MENU
        sales.add(New_Sale);
        sales.addSeparator();
        sales.add(slsbyDate);
        sales.add(slsbyRep);
        sales.add(slsbyCat);
        sales.add(slsbyItem);
        sales.addSeparator();
        sales.add(slssummary);
        sales.add(Box.createHorizontalStrut(150));

        //===================================================================
        //ADD ITEMS TO FILE MENU
        file.add(Admin);
        file.add(Company);
        file.add(User_Reg);
        file.add(Users);
        file.addSeparator();
        file.add(Print);
        file.addSeparator();
        file.add(backup);
        file.add(importdb);
        file.addSeparator();
        file.add(exit);
        file.add(Box.createHorizontalStrut(150));

        //===================================================================
        //ADD ITEMS TO STOCK MENU
        stocks.add(NewItemList);
        stocks.add(ItemList);
        stocks.add(RcvStc);
        stocks.addSeparator();
        stocks.add(Stckmvnt);
        stocks.add(onhand);
        stocks.addSeparator();
        stocks.add(stockreport);
        stocks.add(prices);

        stocks.add(Box.createHorizontalStrut(150));

        //=====================================================================
        // ADD HELP MENU ITEMS
        help.add(about);
        help.add(contact);
        help.addSeparator();
        help.add(reg);
        help.add(License);
        help.addSeparator();
        help.add(update);
        help.add(guide);
        help.add(Box.createHorizontalStrut(150));

        //=================================================================
        //ADD ITEMS TO THE MENU BAR
        bar.add(file);
        bar.add(edit);
        bar.add(sales);
        bar.add(stocks);
        bar.add(customers);
        bar.add(suppliers);
        bar.add(reports);
        bar.add(help);
        bar.add(Box.createVerticalStrut(20));

        toolbar.add(Box.createVerticalStrut(30));
        sls = new SalesMngt();
        stk = new StockMngt();
        //us=new Users();

        add(sls);
        add(stk);
        setJMenuBar(bar);
        add(toolbar);
        setVisible(false);
        setSize(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
                java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height
        );
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        this.addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event) {
            disc.logOutAudit(Integer.parseInt(ID.getText()),Nametxt.getText(), 
            OutActivity.getText(), Time.valueOf(tm.getText()), Date.valueOf(TD.getText()));

                System.exit(0);
            }
        }
        );

    }// END OF CONSTRUCTOR TELLERPOS

}// END OF CLASS TELLERPOS
