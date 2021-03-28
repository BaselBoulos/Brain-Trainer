package Levels;

import java.awt.EventQueue;

import javax.management.loading.PrivateClassLoader;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sun.security.util.DisabledAlgorithmConstraints;
import Modes.EasyMode;

import com.Game.main.GameWindow;
import com.Game.main.MyConnection;
import com.Game.main.RegisterForm;
import com.sun.org.apache.xpath.internal.operations.And;

import dialogs.FirstQuizDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EasyModeLevels {

	private JFrame frame;
	private JLabel LevelsTitle = new JLabel("");
	private JLabel ModeLabel = new JLabel("");
	private JLabel[] ezLevel;
	private int levelDone = 1;
	private int solveTime;
	private JLabel[][] ezLevelStar;
	private int easyModeLvl = 0;
	public String uname;
	public String Fname;
	private boolean inserted = false;
	private boolean lvlDone = false;

	
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
		
		
		/*
		 * Adding mode level detail after all correct answer in the current mode(easy , hard , etc..)
		 */
		public void updateMode(int modeLvl, int modeNum, int solveTime, String userName){
			
	    	PreparedStatement ps1;
	    	String query1 = "INSERT INTO `Modes_Levels`(`level`, `Mode`, `solve_Time`, `username`) VALUES (?,?,?,?)";
	    	try {
				ps1 = MyConnection.getConnection().prepareStatement(query1);
				ps1.setString(1, ""+modeLvl);
				ps1.setString(2, ""+modeNum);
				ps1.setString(3, ""+solveTime);
				ps1.setString(4, userName);
				 if(ps1.executeUpdate() > 0)
			        {
			        	//JOptionPane.showMessageDialog(null, "Easy Mode Levels Table Updated");
			        	System.out.println(" Modes Levels Table Updated");
			        }
			} catch (Exception e) {
				// TODO: handle exception
				Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, e);
			}
	 
		}
		
		
		public void getlevelinfo(String Username , int levelDone){
			PreparedStatement ps;
	        ResultSet rs;
	        String query = "SELECT level, solve_Time FROM `Modes_Levels` WHERE `username` =? AND level = ?";
	        
	        try {
	            ps = MyConnection.getConnection().prepareStatement(query);
	            ps.setString(1, uname);
	            ps.setString(2, ""+levelDone);
	            rs = ps.executeQuery();
	            
	            if(rs.next())
	            {
	            	levelDone = rs.getInt("level");
	            	solveTime = rs.getInt("solve_Time");
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
	        } 
		}
	
		
	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EasyModeLevels window = new EasyModeLevels(uname);
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
	public EasyModeLevels(String userName) {
		uname = userName;
		//getlevelinfo(uname ,levelDone);
		getEzModeLvl(uname);
		GetFirstName(uname);
		initialize();
		if(easyModeLvl > 0)
		checkRate();
	}

	/**
	 * Check Player Answer Rate
	 */
	public void checkRate(){
		lvlDone = false;
		for(int i = 1 ; i < easyModeLvl  ; i ++){
			getlevelinfo(uname,i);
			if(solveTime <= 3){
				ezLevel[i].setIcon(new ImageIcon("images/num"+i+"Clicked.png"));
				ezLevelStar[i][1].setIcon(new ImageIcon("images/RatedStar.png"));
			}else if (solveTime <= 6 && solveTime > 4) {
				ezLevel[i].setIcon(new ImageIcon("images/num"+i+"Clicked.png"));
				ezLevelStar[i][1].setIcon(new ImageIcon("images/RatedStar.png"));
				ezLevelStar[i][2].setIcon(new ImageIcon("images/RatedStar.png"));
				
			}else if (solveTime <= 9 && solveTime > 6) {
				ezLevel[i].setIcon(new ImageIcon("images/num"+i+"Clicked.png"));
				//ezLevel[i].setEnabled(false);
				ezLevelStar[i][1].setIcon(new ImageIcon("images/RatedStar.png"));
				ezLevelStar[i][2].setIcon(new ImageIcon("images/RatedStar.png"));
				ezLevelStar[i][3].setIcon(new ImageIcon("images/RatedStar.png"));
			}	
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		LevelsTitle.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\levels logo2.png"));
		LevelsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		LevelsTitle.setBounds(580, 0, 200, 200);
		frame.getContentPane().add(LevelsTitle);
		
		JLabel Exit = new JLabel("");
		Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				FirstQuizDialog failFrame = new FirstQuizDialog(5 , uname,Fname);
				failFrame.main();
				frame.dispose();
				
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
		Exit.setIcon(new ImageIcon("images/Exit.png"));
		Exit.setBounds(1300, 0, 80, 80);
		frame.getContentPane().add(Exit);
		
		
		/*
		 * Drawing theS levels
		 */
		int xBound = 300;
		int yBound = 300;
		int i ;
		ezLevel = new JLabel[11];
		for(i = 1; i < ezLevel.length ; i++){
			ezLevel[i] = new JLabel();
			ezLevel[i].setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\num"+i+".png"));
			ezLevel[i].setBounds(xBound, yBound, 100, 100);
			frame.getContentPane().add(ezLevel[i]);
				xBound+=150;
			if(i == 6){
				xBound = 450;
				yBound = 450;
			}
			
			final int innerAnswerIndex = new Integer(i);
			ezLevel[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(easyModeLvl >= innerAnswerIndex){

						EasyMode ez = new EasyMode(uname,Fname);
						frame.setVisible(false);
						frame.dispose();
						ez.EasyNewScreen();
					}
					else{
						JOptionPane.showMessageDialog(null,"You Should Complete The Previous Levels at least with One Star");
					}
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					ezLevel[innerAnswerIndex].setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\num"+innerAnswerIndex+"Clicked.png"));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					if(innerAnswerIndex > easyModeLvl)
					ezLevel[innerAnswerIndex].setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\num"+innerAnswerIndex+".png"));
					else
						e.consume();
				} 
			});
			
		}
		
		
		int starXbound = 295;
		int starYbound = 380;
		ezLevelStar = new JLabel[ezLevel.length][5];
		for(int lvl = 1; lvl < ezLevel.length ; lvl++){
			for(int star = 1 ; star < 4 ; star++){
				ezLevelStar[lvl][star] = new JLabel("");		
				ezLevelStar[lvl][star].setIcon(new ImageIcon("images/star.png"));
				ezLevelStar[lvl][star].setBounds(starXbound , starYbound , 30, 30);
				frame.getContentPane().add(ezLevelStar[lvl][star]);
				starXbound+= 25;
			}
			if(lvl == 6){ // new line
				starXbound = 370;
				starYbound = 530;
			}
			starXbound +=75;
		}
		
		
		ModeLabel.setForeground(Color.RED);
		ModeLabel.setText("Mode : Easy");
		ModeLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 25));
		ModeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		ModeLabel.setBounds(10, 10, 200, 30);
		frame.getContentPane().add(ModeLabel);
		
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\simple_background.jpg"));
		Background.setBounds(0, 0, 1366, 768);
		frame.getContentPane().add(Background);
		frame.pack();
	}
}
