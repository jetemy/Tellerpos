/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author hp
 */
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.*;
import javax.swing.*;

public class MenuBar extends JPanel{
    
    private JMenuBar bar;
    
    public MenuBar(){
     //CREATE MENU BAR AND MENUS
         bar=new JMenuBar();
        bar.setBackground(Color.decode("#87CEEB"));
         bar.setBounds(0, 0,1000,30);
         
        JMenu file = new JMenu("File");
         file.setMnemonic('F');
         file.setFont(new Font("Arial",Font.BOLD,18));
         file.setForeground(Color.BLACK);
         
        JMenu edit = new JMenu("Edit");
         edit.setMnemonic('E');
         edit.setFont(new Font("Arial",Font.BOLD,18));
         edit.setForeground(Color.BLACK);
         
        file=new JMenu("File");
         file.setMnemonic('F');
         file.setFont(new Font("Arial",Font.BOLD,18));
         file.setForeground(Color.WHITE);
         
         edit=new JMenu("Edit");
         edit.setMnemonic('E');
         edit.setFont(new Font("Arial",Font.BOLD,18));
         edit.setForeground(Color.WHITE);
         
        JMenu sales = new JMenu("Sales");
         sales.setMnemonic('s');
         sales.setFont(new Font("Arial",Font.BOLD,18));
         sales.setForeground(Color.WHITE);
         
        JMenu stocks = new JMenu("Stock");
        stocks.setMnemonic('t');
        stocks.setFont(new Font("Arial",Font.BOLD,18));
        stocks.setForeground(Color.WHITE);
        
        JMenu customers = new JMenu("Customers");
        customers.setMnemonic('c');
        customers.setFont(new Font("Arial",Font.BOLD,18));
         customers.setForeground(Color.WHITE);
        
        JMenu reports = new JMenu("Reports");
        reports.setMnemonic('R');
        reports.setFont(new Font("Arial",Font.BOLD,18));
        reports.setForeground(Color.WHITE);
        
        JMenu suppliers = new JMenu("Suppliers");
        suppliers.setMnemonic('p');
        suppliers.setFont(new Font("Arial",Font.BOLD,18));
        suppliers.setForeground(Color.WHITE);
        
        
        JMenu help = new JMenu("Help");
        help.setMnemonic('H');
        help.setFont(new Font("Arial",Font.BOLD,18));
        help.setForeground(Color.WHITE);
        
                  
        bar.add(file);
        bar.add(edit);
        bar.add(sales);
        bar.add(stocks);
        bar.add(customers);
        bar.add(suppliers);
        bar.add(reports);
        bar.add(help);
        bar.add(Box.createVerticalStrut(30));
        
        
    }//END OF CONSTRUCTOR MENUBAR

  
}//END OF CLASS MENUBAR
