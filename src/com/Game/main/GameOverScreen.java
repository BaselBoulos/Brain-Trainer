package com.Game.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class GameOverScreen extends JFrame{
	
	/**
	 * SerialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private JLabel playerScore;
	protected Object rgf;
	public String Uname;
	public String Fname;
	public boolean modeDone = false;
	public int Score;
	public int ModeNum;


	/**
	 * @param username
	 * @return
	 * Reset Level and Score for the Selected Mode
	 */
	public void resetEasyMode(String Username)
	{
		PreparedStatement ps;
		String query = "UPDATE `the_app_users` SET `u_easyModeLvl` = ? , u_easyModeScore = ?, u_Health = ? WHERE `u_uname` = ?";
		try {
			
			ps = MyConnection.getConnection().prepareStatement(query);
			ps.setString(1, "1");
			ps.setString(2, "0");
			ps.setString(3, "0");
			ps.setString(4, "" + Username);
			ps.executeUpdate();
			
		}catch (SQLException ex){
			Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		 
	}
	
	/**
	 * @param username
	 * @return
	 * Reset Level and Score for the Selected Mode
	 */
	public void resetMediumMode(String Username)
	{
		PreparedStatement ps;
		String query = "UPDATE `the_app_users` SET `u_medModeLvl` = ? , u_medModeScore = ?, u_Health = ? WHERE `u_uname` = ?";
		try {
			
			ps = MyConnection.getConnection().prepareStatement(query);
			ps.setString(1, "1");
			ps.setString(2, "0");
			ps.setString(3, "6");
			ps.setString(4, "" + Username);
			ps.executeUpdate();
			
		}catch (SQLException ex){
			Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		 
	}
	/* *
	 * function to check if the username already exist in database table
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
	 * function to check if the username already exist in database table
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
	public void GameOver() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOverScreen window = new GameOverScreen(Uname,Fname,Score,ModeNum);
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
	public GameOverScreen() {}
	
	public GameOverScreen(String Username , String Firstname,int score ,int ModeNum) {
		this.Uname = Username;
		this.Fname = Firstname;
		this.Score = score;
		this.ModeNum = ModeNum;
		System.out.println(Uname+Fname+Score);
		initialize();
		frame.setLocationRelativeTo(null); // Center form in the screen :) 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("after init "+Score);
		frame = new JFrame();
		frame.setUndecorated(true); // Hide the toolbar 
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel TitlePanel = new JPanel();
		TitlePanel.setBackground(Color.ORANGE);
		TitlePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		TitlePanel.setBounds(0, 0, 450, 45);
		frame.getContentPane().add(TitlePanel);
		TitlePanel.setLayout(null);
		
		JLabel lblLoginTitle = new JLabel("Brain Trainer");
		lblLoginTitle.setForeground(Color.BLUE);
		lblLoginTitle.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		lblLoginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginTitle.setBounds(145, 11, 160, 20);
		TitlePanel.add(lblLoginTitle);
		
		JPanel BodyPanel = new JPanel();
		BodyPanel.setBackground(new Color(0, 51, 51));
		BodyPanel.setBounds(0, 45, 450, 255);
		frame.getContentPane().add(BodyPanel);
		BodyPanel.setLayout(null);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setForeground(Color.ORANGE);
		lblGameOver.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblGameOver.setBounds(161, 37, 115, 30);
		BodyPanel.add(lblGameOver);
		
		JButton btnLogin = new JButton("Exit");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int choose = JOptionPane.showConfirmDialog(null,
	                        "Do you really want to exit the application ?",
	                        "Confirm Close", JOptionPane.YES_NO_OPTION,
	                        JOptionPane.INFORMATION_MESSAGE);
	                if (choose == JOptionPane.YES_OPTION)  { 
	                	
	                	switch(ModeNum){
	   					 case 1: //reset level and score in easy mode;
	   						 resetEasyMode(Uname);
	   					 case 2:
	   						 resetMediumMode(Uname);
	   						 //reset Level and score for med mode;
	   					 case 3:
	   						 //reset Level and score for hard mode;
	   				 }
	                	System.exit(0); 
	                	
	                }
			}
		});
			
		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.setHideActionText(true);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnLogin.setBackground(new Color(0, 0, 205));
		btnLogin.setForeground(SystemColor.menu);
		btnLogin.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		btnLogin.setBounds(248, 163, 100, 38);
		BodyPanel.add(btnLogin);
		
		JButton btnCancel = new JButton("Restart");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 frame.dispose();
				 switch(ModeNum){
					 case 1: //reset level and score in easy mode;
						 resetEasyMode(Uname);
					 case 2:
						 //reset Level and score for med mode;
						 resetMediumMode(Uname);
					 case 3:
						 //reset Level and score for hard mode;
				 }
                 GameWindow gm = new GameWindow(Uname,Fname,modeDone);
                 gm.main();
			}
		});
		
		btnCancel.setBackground(new Color(255, 0, 0));
		btnCancel.setForeground(SystemColor.menu);
		btnCancel.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		btnCancel.setBounds(82, 163, 110, 38);
		BodyPanel.add(btnCancel);
		
		JLabel lblScore = new JLabel("Your Score :");
		lblScore.setForeground(Color.RED);
		lblScore.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblScore.setBounds(161, 78, 160, 30);
		BodyPanel.add(lblScore);
		
		playerScore = new JLabel(""+Score);
		playerScore.setForeground(Color.RED);
		playerScore.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		playerScore.setBounds(300, 78, 40, 30);
		BodyPanel.add(playerScore);
	}
}