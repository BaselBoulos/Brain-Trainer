package com.Game.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import Levels.EasyModeLevels;
import Levels.FirstQuiz;
import dialogs.FirstQuizDialog;
import dialogs.FirstTimeInGame;
import dialogs.Instruction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel mainMenuPanel;
	private static String UserFirstName;
	private String Username;
	private int firstTimeInGame = 0;
	private boolean FirstQuizDone = false;

	public int UpdateFirstLog(String Username){
		PreparedStatement ps;
		String query = "UPDATE the_app_users SET FirstTimeIngame = ? WHERE u_uname = ?";
		try {
			ps = MyConnection.getConnection().prepareStatement(query);
			ps.setString(1, ""+firstTimeInGame);
			ps.setString(2, "" +Username);
			ps.executeUpdate();
			
		}catch (SQLException ex){
			Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		return firstTimeInGame;
	}
	
	public int CheckFirstLog(String username){
		PreparedStatement ps;
	    ResultSet rs;
	    String query = "SELECT FirstTimeIngame FROM `the_app_users` WHERE `u_uname` = ?";
	    
	    try {
	        ps = MyConnection.getConnection().prepareStatement(query);
	        ps.setString(1, Username);
	        
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
	
	public boolean CheckQuizDone(String username){
		PreparedStatement ps;
	    ResultSet rs;
	    String query = "SELECT FirstQuizDone FROM `the_app_users` WHERE `u_uname` = ?";
	    try {
	        ps = MyConnection.getConnection().prepareStatement(query);
	        ps.setString(1, Username);
	        
	        rs = ps.executeQuery();
	        
	        if(rs.next())
	        {
	        	int temp;
	        	 temp = rs.getInt("FirstQuizDone"); 
	        	if(temp == 1) FirstQuizDone = true;
	        	else FirstQuizDone = false;
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
	    }
	 return FirstQuizDone;
	}
	
	
	/**
	 * Launch the application.
	 */
	public  void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu(Username,UserFirstName);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu(String Username, String FirstName) {
		this.Username = Username;
		UserFirstName = FirstName;
		CheckFirstLog(Username);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		setUndecorated(true);
		mainMenuPanel = new JPanel();
		setContentPane(mainMenuPanel);
		mainMenuPanel.setLayout(null);
		mainMenuPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
					if(firstTimeInGame == 1){
						firstTimeInGame = 0;
						UpdateFirstLog(Username);
						FirstTimeInGame ftig = new FirstTimeInGame();
						ftig.main();
					}
			}
		});
		
		JLabel Exit = new JLabel("");
		Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FirstQuizDialog failFrame = new FirstQuizDialog(3, Username, FirstName);
				failFrame.main();
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
		
		JLabel TitleLogo = new JLabel("");
		TitleLogo.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLogo.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\MainMenuLogo.jpg"));
		TitleLogo.setBounds(578, 11, 243, 146);
		mainMenuPanel.add(TitleLogo);
		
		JLabel PuzzleModeLogo = new JLabel("");
		PuzzleModeLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PuzzleModeLogo.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\PuzzleShape1.png"));
		PuzzleModeLogo.setHorizontalAlignment(SwingConstants.CENTER);
		PuzzleModeLogo.setBounds(190, 255, 200, 200);
		mainMenuPanel.add(PuzzleModeLogo);
		
		JLabel MathModeLogo = new JLabel("");
		MathModeLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckQuizDone(Username);
				//System.out.println(FirstQuizDone);
				if(!FirstQuizDone){
					FirstQuiz fq = new FirstQuiz(Username,FirstName);
					fq.main();
					MainMenu.this.setVisible(false);
					MainMenu.this.dispose();
				}else{
				GameWindow gw = new GameWindow(Username,FirstName,true);
				gw.main();
				MainMenu.this.dispose();
				}
			}
		});
		MathModeLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MathModeLogo.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\MathLogo.png"));
		MathModeLogo.setHorizontalAlignment(SwingConstants.CENTER);
		MathModeLogo.setBounds(585, 255, 200, 250);
		mainMenuPanel.add(MathModeLogo);
		
		JLabel MemoryModeLogo = new JLabel("");
		MemoryModeLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MemoryModeLogo.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\MemoryLogo.png"));
		MemoryModeLogo.setHorizontalAlignment(SwingConstants.CENTER);
		MemoryModeLogo.setBounds(990, 277, 200, 200);
		mainMenuPanel.add(MemoryModeLogo);
		Exit.setHorizontalAlignment(SwingConstants.CENTER);
		Exit.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Exit.png"));
		Exit.setBounds(1290, 0, 80, 80);
		mainMenuPanel.add(Exit);
		
		JLabel lblUsername = new JLabel("Welcome: "+UserFirstName);
		lblUsername.setForeground(Color.MAGENTA);
		lblUsername.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		lblUsername.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\userlogo.png"));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(10, 11, 297, 69);
		mainMenuPanel.add(lblUsername);
		
		JLabel lblSwitchUser = new JLabel("");
		lblSwitchUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSwitchUser.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\changeUserClicked.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FirstQuizDialog switchuser = new FirstQuizDialog(4 ,Username,FirstName);
				switchuser.main();
				MainMenu.this.dispose();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSwitchUser.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\changeUser.png"));
			}
		});
		lblSwitchUser.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\changeUser.png"));
		lblSwitchUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwitchUser.setBounds(10, 677, 80, 80);
		mainMenuPanel.add(lblSwitchUser);
		
		JLabel lblHowToPlay = new JLabel(" How To Play");
		lblHowToPlay.setForeground(new Color(255, 255, 0));
		lblHowToPlay.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		lblHowToPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Instruction howToPlay = new Instruction();
				howToPlay.main();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblHowToPlay.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\infoClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblHowToPlay.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\info.png"));
			}
		});
		lblHowToPlay.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\info.png"));
		lblHowToPlay.setHorizontalAlignment(SwingConstants.CENTER);
		lblHowToPlay.setBounds(542, 680, 243, 80);
		mainMenuPanel.add(lblHowToPlay);
		
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\MainMenuBackground.jpg"));
		Background.setBounds(0, 0, 1366, 768);
		mainMenuPanel.add(Background);
		
		
	}

}
