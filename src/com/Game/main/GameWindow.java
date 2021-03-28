package com.Game.main;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager;

import Levels.EasyModeLevels;
import Modes.EasyMode;
import Modes.MediumMode;
import Modes.HardMode;
import Modes.trainingMode;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingConstants;

public class GameWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmBrainTrainer;
	private JLabel labelUserName;
	//private String Username;
	private Popup popup;
	public String uname;
	public String Fname;
	private int easyModeLvl = 0;
	private int easyModeScore = 0;
	private int globalScore = 0;
	public boolean modeDone = false;
	private JButton btnEasyMode;
	private JButton btnMedMode;
	
	/*
	 * Check GlobalScore Value For the UserName
	 * Show The Max score the player reach it ...
	 * Function to show last score sine exit. 
	 */
	
	public int CheckScore(String Username){
		PreparedStatement ps;
	    ResultSet rs;
	    String query = "SELECT u_GlobalScore FROM `the_app_users` WHERE `u_uname` = ?";
	    
	    try {
	        ps = MyConnection.getConnection().prepareStatement(query);
	        ps.setString(1, uname);
	        
	        rs = ps.executeQuery();
	        
	        if(rs.next())
	        {
	        	globalScore = rs.getInt("u_GlobalScore"); 
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
	    }
	 return globalScore;
	}
	
/**
 * @param Username
 * @return easyModeLvl
 */
	public int getEzModeLvl(String Username){
		PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT u_easyModeLvl FROM `the_app_users` WHERE `u_uname` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, uname);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
            	easyModeLvl = rs.getInt("u_easyModeLvl");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
         return easyModeLvl;
    
	}

	/**
	 * @return EzMode Lvl to access the medium mode
	 *  in the start page 
	 */
	public int getEzModeScore(String Username){
		PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT u_easyModeScore FROM `the_app_users` WHERE `u_uname` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, uname);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
            	easyModeScore = rs.getInt("u_easyModeScore");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
         return easyModeScore;
    
	}

	/**
	 * Launch the application.
	 */
	public void main() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow(uname , Fname, modeDone);
					window.frmBrainTrainer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * Create the application.
	 * @param uname 
	 */
	public GameWindow(String uname,String Fname, boolean modeDone) {
		this.uname = uname;
		this.Fname = Fname;
		this.modeDone = modeDone;

		initialize();
		frmBrainTrainer.setLocationRelativeTo(null); // Center form in the screen :) 
		
		CheckScore(uname);
		getEzModeScore(uname);
		if(globalScore >= 100){
			btnMedMode.setEnabled(true);
		}
		if(globalScore >= 100) { btnEasyMode.setEnabled(false);} 
		else{btnEasyMode.setEnabled(true);}
		
	}
	//public GameWindow(){}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBrainTrainer = new JFrame();
		frmBrainTrainer.setLocationRelativeTo(null); // Center form in the screen :)
		frmBrainTrainer.setUndecorated(true); // Hide the Tool Bar( - [] x )
		frmBrainTrainer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmBrainTrainer.setResizable(false);
		frmBrainTrainer.setType(Type.POPUP);
		frmBrainTrainer.setIconImage(Toolkit.getDefaultToolkit().getImage("images/mathit.png"));
		frmBrainTrainer.setTitle("Brain Trainer");
		frmBrainTrainer.setBounds(100, 100, 1366, 768);
		frmBrainTrainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBrainTrainer.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 1366, 45);
		frmBrainTrainer.getContentPane().add(panel);
		
		JLabel lblBrainTrainer = new JLabel("Brain Trainer");
		lblBrainTrainer.setHorizontalAlignment(SwingConstants.CENTER);
		lblBrainTrainer.setForeground(Color.BLUE);
		lblBrainTrainer.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		lblBrainTrainer.setBounds(10, 11, 153, 20);
		panel.add(lblBrainTrainer);
		
		JLabel label_Exit_Menu = new JLabel("X");
		label_Exit_Menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_Exit_Menu.addMouseListener(new MouseAdapter() {
			@Override //Exit Button
			public void mouseClicked(MouseEvent e){
		                int choose = JOptionPane.showConfirmDialog(null,
		                        "Do you really want to exit the application ?",
		                        "Confirm Close", JOptionPane.YES_NO_OPTION,
		                        JOptionPane.INFORMATION_MESSAGE);
		                if (choose == JOptionPane.YES_OPTION) {
		                    System.exit(0);   
		                }
		            }
	
			@Override // Some Style When Mouse Enter in the Label Change color of the label to Red
			public void mouseEntered(MouseEvent arg0) {
				label_Exit_Menu.setForeground(Color.RED);
			}
			
			@Override // Getting back the Defult Color (Blue)
			public void mouseExited(MouseEvent arg0) {
				label_Exit_Menu.setForeground(Color.BLUE);
			}
		});
		label_Exit_Menu.setHorizontalAlignment(SwingConstants.CENTER);
		label_Exit_Menu.setForeground(Color.BLUE);
		label_Exit_Menu.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		label_Exit_Menu.setBounds(1340, 10, 22, 22);
		panel.add(label_Exit_Menu);
		
		JLabel label_Minimize_menu = new JLabel("-");
		label_Minimize_menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_Minimize_menu.addMouseListener(new MouseAdapter() {
			@Override // Minimize Button 
			public void mouseClicked(MouseEvent arg0) {
				frmBrainTrainer.setState(JFrame.ICONIFIED);
			}
			@Override // Some Style When Mouse Enter in the Label Change color of the label to Red
			public void mouseEntered(MouseEvent arg0) {
				label_Minimize_menu.setForeground(Color.RED);
			}
			
			@Override // Getting back the Defult Color (Blue)
			public void mouseExited(MouseEvent arg0) {
				label_Minimize_menu.setForeground(Color.BLUE);
			}
		});
		label_Minimize_menu.setHorizontalAlignment(SwingConstants.CENTER);
		label_Minimize_menu.setForeground(Color.BLUE);
		label_Minimize_menu.setFont(new Font("Trajan Pro", Font.BOLD, 24));
		label_Minimize_menu.setBounds(1315, 10, 22, 22);
		panel.add(label_Minimize_menu);
		
		JLabel lblWelcome = new JLabel("Welcome ");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(Color.BLUE);
		lblWelcome.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		lblWelcome.setBounds(614, 11, 99, 20);
		panel.add(lblWelcome);
		

		labelUserName = new JLabel(": "+Fname);
		labelUserName.setHorizontalAlignment(SwingConstants.LEFT);
		labelUserName.setForeground(Color.BLUE);
		labelUserName.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		labelUserName.setBounds(714, 11, 99, 20);
		panel.add(labelUserName);
		
		
		
		
		JPanel ModesPanel = new JPanel();
		ModesPanel.setForeground(UIManager.getColor("CheckBox.darkShadow"));
		//ModesPanel.setBounds(284, 82, 200, 240);
		ModesPanel.setBounds(589, 82, 231, 311);
		frmBrainTrainer.getContentPane().add(ModesPanel);
		ModesPanel.setBackground(new Color(0, 0, 0, 0));
		ModesPanel.setLayout(null);
		
		btnEasyMode = new JButton("Easy");
		btnEasyMode.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEasyMode.setHideActionText(true);
		btnEasyMode.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnEasyMode.setBackground(new Color(0, 0, 205));
		btnEasyMode.setForeground(SystemColor.menu);
		btnEasyMode.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		/**
		 * When Easy Mode Button Clicked 
		 */
		btnEasyMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					EasyModeLevels ezmodelvls = new EasyModeLevels(uname);
					ezmodelvls.main();
					//frmBrainTrainer.setVisible(false);
					frmBrainTrainer.dispose();
				
				/*
				getEzModeLvl(uname);
				EasyMode ez = new EasyMode(uname,Fname);
				frmBrainTrainer.setVisible(false);
				ez.EasyNewScreen();
				*/
			}
		});
		btnEasyMode.setBounds(60, 38, 106, 33);
		ModesPanel.add(btnEasyMode);
		
		btnMedMode = new JButton("Medium");
		btnMedMode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!btnMedMode.isEnabled()){
				  if (popup != null) {
	                    popup.hide();
	                }
	                JLabel text = new JLabel("You Need To Finish The Easy Mode First :) ");// + e.getPoint());
	                popup = PopupFactory.getSharedInstance().getPopup(e.getComponent(), text, e.getXOnScreen(), e.getYOnScreen());
	                popup.show();
	            }
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(popup!= null)
				popup.hide();
			}
	        });
		/**
		 * When Medium Mode Button Clicked 
		 */
		btnMedMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					MediumMode med = new MediumMode(uname,Fname);
				frmBrainTrainer.setVisible(false);
				med.MediumNewScreen();
			}
		});
		btnMedMode.setBackground(new Color(128, 0, 128));
		btnMedMode.setForeground(SystemColor.menu);
		btnMedMode.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		btnMedMode.setEnabled(false);
		btnMedMode.setBounds(60, 92, 106, 36);
		ModesPanel.add(btnMedMode);
		
		JButton btnHardMode = new JButton("Hard");
		btnHardMode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!btnHardMode.isEnabled()){
				if (popup != null) {
                    popup.hide();
                }
                JLabel text = new JLabel("You Need To Finish The Medium Mode ..");// + e.getPoint());
                popup = PopupFactory.getSharedInstance().getPopup(e.getComponent(), text, e.getXOnScreen(), e.getYOnScreen());
                popup.show();
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				popup.hide();
			}
		});
		/**
		 * When Hard Mode Button Clicked 
		 */
		btnHardMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HardMode hard = new HardMode();
				frmBrainTrainer.setVisible(false);
				hard.HardNewScreen();
			}
		});
		btnHardMode.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHardMode.setHideActionText(true);
		btnHardMode.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnHardMode.setBackground(new Color(255, 0, 0));
		btnHardMode.setForeground(SystemColor.menu);
		btnHardMode.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		btnHardMode.setEnabled(false);
		btnHardMode.setBounds(60, 152, 106, 33);
		ModesPanel.add(btnHardMode);
		
		JButton btnTraining = new JButton("Training Mode");
		btnTraining.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				frmBrainTrainer.dispose();
				trainingMode tm = new trainingMode(uname,Fname);
				tm.main();
			}
		});
		btnTraining.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTraining.setHideActionText(true);
		btnTraining.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnTraining.setBackground(new Color(0, 128, 0));
		btnTraining.setForeground(SystemColor.menu);
		btnTraining.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		btnTraining.setBounds(22, 210, 180, 33);
		ModesPanel.add(btnTraining);
		
		JButton btnFavouriteQuestions = new JButton("favourite Questions");
		btnFavouriteQuestions.setHorizontalTextPosition(SwingConstants.CENTER);
		btnFavouriteQuestions.setHideActionText(true);
		btnFavouriteQuestions.setForeground(SystemColor.menu);
		btnFavouriteQuestions.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		btnFavouriteQuestions.setBackground(new Color(0, 206, 209));
		btnFavouriteQuestions.setBounds(0, 267, 231, 33);
		ModesPanel.add(btnFavouriteQuestions);
		
		JLabel backToMainMenu = new JLabel("");
		backToMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MainMenu mm = new MainMenu(uname, Fname);
				mm.main();
				frmBrainTrainer.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				backToMainMenu.setIcon(new ImageIcon("images/backToMainMenuClicked.png"));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backToMainMenu.setIcon(new ImageIcon("images/backToMainMenu.png"));
				
			}
		});
		backToMainMenu.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\backToMainMenu.png"));
		backToMainMenu.setBounds(641, 423, 132, 100);
		frmBrainTrainer.getContentPane().add(backToMainMenu);
		backToMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("images/Background.jpg"));
		lblBackground.setBounds(0, 0, 1366, 768);
		frmBrainTrainer.getContentPane().add(lblBackground);
		
		
		

	}
}
