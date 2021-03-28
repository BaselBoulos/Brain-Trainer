package com.Game.main;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

import dialogs.FirstQuizDialog;

import javax.swing.ImageIcon;

import sun.awt.image.ImageAccessException;

public class NewLoginPage extends JFrame{
	
	/**
	 * SerialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	public JTextField UserField;
	private JPasswordField PasswordField;
	protected Object rgf;
	public String Uname;
	public String Fname;
	public int modeLevel = 0;
	public boolean modeDone = false;
	private String pass;
	private int easyModeScore = 0;
	private int globalScore = 0;
	private int firstTimeInGame = 0;

	/*
	 * Check GlobalScore Value For the UserName
	 * Show The Max score the player reach it ...
	 * Function to show last score sine exit. 
	 */
	
	public int CheckFirstLog(String username){
		PreparedStatement ps;
	    ResultSet rs;
	    String query = "SELECT FirstTimeIngame FROM `the_app_users` WHERE `u_uname` = ?";
	    
	    try {
	        ps = MyConnection.getConnection().prepareStatement(query);
	        ps.setString(1, Uname);
	        
	        rs = ps.executeQuery();
	        
	        if(rs.next())
	        {
	        	firstTimeInGame = rs.getInt("FirstTimeIngame"); 
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
	    }
	 return firstTimeInGame;
	}
	
	
	public int CheckScore(String username){
		PreparedStatement ps;
	    ResultSet rs;
	    String query = "SELECT u_GlobalScore FROM `the_app_users` WHERE `u_uname` = ?";
	    
	    try {
	        ps = MyConnection.getConnection().prepareStatement(query);
	        ps.setString(1, Uname);
	        
	        rs = ps.executeQuery();
	        
	        if(rs.next())
	        {
	        	globalScore = rs.getInt("u_GlobalScore"); 
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
	    }
	 return easyModeScore;
	}

	/* *
	 * function to check if the username already exist in DB table
	 */
    public boolean checkUsername(String username)
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM `the_app_users` WHERE `u_uname` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                checkUser = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
         return checkUser;
    }
    
    /* *
	 * function to get firstname from DB
	 */
    public String GetFirstName(String username)
    {
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT u_fname FROM `the_app_users` WHERE `u_uname` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
            	Fname = rs.getString("u_fname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
         return Fname;
    }
	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewLoginPage window = new NewLoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewLoginPage() {
		
		initialize();
		if(globalScore > 100 ) { modeDone = true; }
		frame.setLocationRelativeTo(null); // Center form in the screen :) 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true); // Hide the toolbar 
		frame.setBounds(100, 100, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/mathit.png"));
		
		JPanel BodyPanel = new JPanel();
		BodyPanel.setBackground(new Color(0, 51, 51));
		BodyPanel.setBounds(0, 0, 1366, 768);
		frame.getContentPane().add(BodyPanel);
		BodyPanel.setLayout(null);
		
		JLabel Logo = new JLabel("");
		Logo.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\GameLogo.png"));
		Logo.setBounds(604, 24, 200, 200);
		BodyPanel.add(Logo);
		
		JLabel lblUserName = new JLabel("Username :");
		lblUserName.setForeground(SystemColor.activeCaptionBorder);
		lblUserName.setFont(new Font("Trajan Pro", Font.BOLD, 40));
		lblUserName.setBounds(346, 272, 266, 56);
		BodyPanel.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(SystemColor.activeCaptionBorder);
		lblPassword.setFont(new Font("Trajan Pro", Font.BOLD, 40));
		lblPassword.setBounds(346, 350, 266, 44);
		BodyPanel.add(lblPassword);
		
		UserField = new JTextField();
		UserField.setForeground(SystemColor.menu);
		UserField.setBackground(Color.DARK_GRAY);
		UserField.setFont(new Font("Trajan Pro", Font.PLAIN, 40));
		UserField.setBounds(622, 272, 397, 50);
		BodyPanel.add(UserField);
		UserField.setColumns(10);
		
		PasswordField = new JPasswordField();
		PasswordField.setForeground(SystemColor.menu);
		PasswordField.setBackground(Color.DARK_GRAY);
		PasswordField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		PasswordField.setBounds(622, 350, 397, 50);
		BodyPanel.add(PasswordField);
		
		JLabel lblRegister = new JLabel("Click Here to create a new Account  ");
		lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RegisterForm rgf = new RegisterForm();
				rgf.main();
				frame.dispose();	
			}
		});
		lblRegister.setForeground(SystemColor.menu);
		lblRegister.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		lblRegister.setBounds(478, 531, 440, 32);
		BodyPanel.add(lblRegister);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblLogin.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\LoginClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblLogin.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Login.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				PreparedStatement ps;
				ResultSet rs;
				
				Uname = UserField.getText();
				pass = String.valueOf(PasswordField.getPassword());
				GetFirstName(Uname);
				CheckScore(Uname);
				if(globalScore > 100 ) { modeDone = true; }
				String query = "SELECT * FROM `the_app_users` WHERE `u_uname` =? AND `u_pass` =?";
				
				try {
					
					ps = MyConnection.getConnection().prepareStatement(query);
					ps.setString(1, Uname);
		            ps.setString(2, pass);
		            
					rs = ps.executeQuery();

					if(rs.next())
					{
						
						//GameWindow gm = new GameWindow(Uname,Fname,modeDone);
						MainMenu mm = new MainMenu(Uname,Fname);
						frame.setVisible(false);
						frame.dispose();
						mm.main(); // Call Main Menu Window Frame
					}
					else
					{
						 JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, e1);
				
				}
			}
			
		});
		lblLogin.setForeground(Color.RED);
		lblLogin.setFont(new Font("Trajan Pro", Font.PLAIN, 40));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Login.png"));
		lblLogin.setBounds(413, 432, 245, 80);
		BodyPanel.add(lblLogin);
		
		JLabel lblCancel = new JLabel("cancel");
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FirstQuizDialog ExitApp = new FirstQuizDialog(3, "test","test");
				ExitApp.main();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCancel.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\CancelClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCancel.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Cancel.png"));
				
			}
		});
		lblCancel.setForeground(Color.BLUE);
		lblCancel.setFont(new Font("Trajan Pro", Font.PLAIN, 40));
		lblCancel.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Cancel.png"));
		lblCancel.setBounds(726, 433, 259, 81);
		BodyPanel.add(lblCancel);
		
		JLabel lblClear = new JLabel("");
		lblClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserField.setText("");
				PasswordField.setText("");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblClear.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\ClearClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblClear.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Clear.png"));
			}
		});
		lblClear.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Clear.png"));
		lblClear.setForeground(Color.GRAY);
		lblClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblClear.setBounds(1029, 306, 80, 56);
		BodyPanel.add(lblClear);
		
		JLabel Exit = new JLabel("");
		Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FirstQuizDialog ExitApp = new FirstQuizDialog(3,"test","test");
				ExitApp.main();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Exit.setIcon(new ImageIcon("images/ExitClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Exit.setIcon(new ImageIcon("images/Exit.png"));
			}
		});
		Exit.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Exit.png"));
		Exit.setHorizontalAlignment(SwingConstants.CENTER);
		Exit.setBounds(1276, 11, 80, 80);
		BodyPanel.add(Exit);
	}
}
