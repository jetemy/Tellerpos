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
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.Timer;
import functionality.Discounting;
import java.awt.Image;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.LocalTime.now;
import java.time.temporal.ChronoUnit;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public final class UserLogIn extends JFrame {

    public static JCheckBox ViewPWDChkBx;
    public static JLabel Namelbl, Passwordlbl, Selectionlbl, lblmsg,X;
    public static JPasswordField PasswordField;
    public static JTextField Nametxt;
    public static JPanel login, MsgPanel;
    private Discounting disc;
    public static JButton BTNNEXT, lblimg, CANCELButton, logInButton;
    public static Timer timer1, timer;
    private JProgressBar progbar;
    private TellerPos tp;
    private Icon img;

    public UserLogIn() throws SQLException {

        disc = new Discounting();
        img = new ImageIcon("C:\\Users\\Oscar\\Documents\\NetBeansProjects\\Teller "
                + "POS\\src\\resources\\images\\logIn Img.JPG");
        //
        MsgPanel = new JPanel();
        MsgPanel.setLayout(null);

        timer = new Timer(100, (ActionEvent ae) -> {
            if (MsgPanel.getHeight() != 50) {
                MsgPanel.setBounds(0, 0, UserLogIn.this.getSize().width,
                        MsgPanel.getHeight() + 5);
                if (MsgPanel.getHeight() == 50) {
                    timer.stop();
                }

            }
        });
        timer1 = new Timer(100, (ActionEvent ae) -> {
            if (MsgPanel.getHeight() != 0) {
                MsgPanel.setBounds(0, 0, UserLogIn.this.getSize().width,
                        MsgPanel.getHeight() - 5);
                if (MsgPanel.getHeight() == 0) {
                    timer1.stop();
                }

            }

        });

        //create label objects
        lblmsg = new JLabel();
        lblimg = new JButton();
        lblimg.setIcon(img);
        lblimg.setBounds(10, 80, 150, 150);
        lblimg.setBorder(BorderFactory.createLineBorder(Color.cyan));

        Selectionlbl = new JLabel("  LOG IN");
        Selectionlbl.setFont(new Font("Arial Black", Font.BOLD, 30));
        Selectionlbl.setForeground(Color.decode("#00BFFF"));
        Selectionlbl.setBounds(290, 50, 180, 30);

        Namelbl = new JLabel("ENTER NAME");
        Namelbl.setBounds(180, 100, 180, 30);
        Namelbl.setForeground(Color.decode("#00FFFF"));

        Passwordlbl = new JLabel("ENTER PASSWORD");
        Passwordlbl.setBounds(180, 150, 180, 30);
        Passwordlbl.setForeground(Color.decode("#00FFFF"));

        //create password fields
        PasswordField = new JPasswordField();
        PasswordField.setEchoChar('*');
        PasswordField.setFont(new Font("", Font.PLAIN, 18));
        PasswordField.setBounds(300, 150, 150, 30);

        ViewPWDChkBx = new JCheckBox("VIEW PASSWORD");
        ViewPWDChkBx.setBounds(300, 195, 150, 10);
        ViewPWDChkBx.setFont(new Font("Arial", Font.PLAIN, 14));
        ViewPWDChkBx.setForeground(Color.MAGENTA);
        ViewPWDChkBx.setBackground(Color.decode("#87CEEB"));
        ViewPWDChkBx.addItemListener((ItemEvent ie) -> {
            if (ViewPWDChkBx.isSelected()) {
                PasswordField.setEchoChar((char) 0);

            } else {
                PasswordField.setEchoChar('*');
            }

        });

        //create text field objects
        Nametxt = new JTextField();
        Nametxt.setBounds(300, 100, 150, 30);
        Nametxt.setFont(new Font("", Font.PLAIN, 14));
        Nametxt.addActionListener((ActionEvent event) -> {
            SwingUtilities.invokeLater(() -> {
                try {
                    UserLogIn nu = new UserLogIn();
                    nu.setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(UserLogIn.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        });

        // create button objects
        logInButton = new JButton("LOG IN");
        logInButton.setBounds(270, 220, 80, 30);
        logInButton.addActionListener((ActionEvent e) -> {
            try {

                try {
                    disc.pswdchk();
                } catch (SQLException ex) {
                    Logger.getLogger(UserLogIn.class.getName()).log(Level.SEVERE, null, ex);
                }

                String Today = LocalDate.now().toString();
                LocalTime time = now().truncatedTo(ChronoUnit.MILLIS);
                JTextField TD = new JTextField(Today);
                JTextField tm = new JTextField(Time.valueOf(time).toString());
                JTextField ID = new JTextField(disc.UserExist());
                JTextField InActivity = new JTextField("LogIn");
                disc.loginOutAudit(Integer.parseInt(ID.getText()), Nametxt.getText(),
                        InActivity.getText(), Time.valueOf(tm.getText()),
                        Date.valueOf(TD.getText()));
                this.dispose();

            } catch (SQLException ex) {
                Logger.getLogger(UserLogIn.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        //======================================================================      
        CANCELButton = new JButton("CANCEL");
        CANCELButton.setBounds(380, 220, 80, 30);
        CANCELButton.addActionListener((ActionEvent event) -> {

            if (JOptionPane.showConfirmDialog(null, "Do you want to exit",
                    "EXIT", JOptionPane.OK_CANCEL_OPTION)
                    == JOptionPane.OK_OPTION) {
                System.exit(0);
            } else {
                Nametxt.setText("");
                PasswordField.setText("");
                System.gc();
            }

        });

        progbar = new JProgressBar();
        progbar.setIndeterminate(true);
        int max = 100;
        int min = 0;
        progbar.setForeground(Color.blue);
        progbar.setBackground(Color.WHITE);
        progbar.setBounds(0, 255, 490, 4);
        progbar.setMaximum(max);
        progbar.setMinimum(min);
        progbar.updateUI();
        JLabel prog = new JLabel(progbar.getString());
        prog.setBounds(5, 240, 100, 10);
        login = new JPanel();
        login.setBounds(0, 0, 500, 300);
        login.setBorder(BorderFactory.createBevelBorder(5));
        login.setBackground(Color.decode("#2F4F4F"));
        login.setLayout(null);

        MsgPanel.add(lblmsg);

        //login.add(prog);
        //login.add(progbar);
        login.add(Selectionlbl);
        login.add(Namelbl);
        login.add(Passwordlbl);
        login.add(Nametxt);
        login.add(PasswordField);
        login.add(logInButton);
        login.add(CANCELButton);
        login.add(lblimg);
        login.add(MsgPanel);
        login.add(ViewPWDChkBx);

        add(login);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(false);
        setResizable(false);
        disc.DBConnect();

    }//END OF CONSTRUCTOR LOGIN

    public static void main(String[] args) throws SQLException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserLogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              
                    try {
                        UserLogIn Log = new UserLogIn();
                   Log.setVisible(true); 
                   

                } catch (SQLException ex) {
                    Logger.getLogger(UserLogIn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

    private ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(X.getWidth(), X.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

}// END OF CLASS LOGIN
