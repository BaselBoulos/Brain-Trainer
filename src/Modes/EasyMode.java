package Modes;


import javax.swing.*;

import java.awt.event.*;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingConstants;

import com.Game.main.GameOverScreen;
import com.Game.main.GameWindow;
import com.Game.main.MyConnection;
import com.Game.main.RegisterForm;

import dialogs.EasyModeDone;

import javax.swing.border.EmptyBorder;

public class EasyMode extends JFrame {

/**
 * Default Serial Version Number.
 */
private static final long serialVersionUID = 1L;

private static final String[] LevelNumbers = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14"};

public String uname;
public String Fname;
private JFrame EasyFrame;
public JLabel Life_1,Life_2,Life_3;
public JLabel lblTime;
private JLabel CountDown;
private JLabel Number1;
private JLabel Number2;
private JLabel Operator;
private JLabel ScoreValue;
private JButton[] Options;
private float[] answers;
@SuppressWarnings("unused")
private boolean answered = false;
public boolean modeDone = false;
public int ModeNum;
private float Result = 0;
private int lifeCounter = 0;
private int EasyModeScore = 0;
public int globalScore;
private int StartTime = 10; 
private int OrginalStarting;
private int maxLimit = 10; 
private SecureRandom randomClass; 
private Timer timer;
private JLabel label_Level_Num;
private JLabel lblLevel;
private int level = 1;
private int i=0;
private String updatedScore;
private String updatedhealth;
private int easyModeLvl = 0;


	public class NewTask extends TimerTask{
		@Override
		public void run() {
			
			if(StartTime>=0){
				CountDown.setText(StartTime+"");
				StartTime--;
			}
			else{ 
				if(lifeCounter != 3){
				lifeCounter++;	
				timer.cancel();
				answers = new float[4];
				StartTime = OrginalStarting;
				timer = new Timer();
				timer.schedule(new NewTask(), 0,1000);
				generateRandom();
				}
			}
		}
	}
	
	/*
	 * Truncate Modes_Levels Table After Game Over 
	 */
	public void TruncateModeLevels(){
		PreparedStatement ps;
		 String query = " TRUNCATE `Modes_Levels`";
		 try {
		        ps = MyConnection.getConnection().prepareStatement(query);
		        ps.executeUpdate();
		       
		    } catch (SQLException ex) {
		        Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
		    }
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
        	level = easyModeLvl;
        }
    } catch (SQLException ex) {
        Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
    }
     return easyModeLvl;

}

/**
 * @param Username
 */
public void setEzModeLvl(String Username,int modeLevel)
{
	PreparedStatement ps;
	String query = "UPDATE `the_app_users` SET `u_easyModeLvl` = ? WHERE `u_uname` = ?";
	try {
		
		ps = MyConnection.getConnection().prepareStatement(query);
		ps.setString(1, "" + modeLevel);
		ps.setString(2, "" + Username);
		ps.executeUpdate();
		
	}catch (SQLException ex){
		Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
	}
}

/**
 * @param Username
 * @return EasyModeScore
 */
public int resetScoreAfterMode(String Username){
	PreparedStatement ps;
//	ResultSet rs;
	String query = "UPDATE the_app_users SET u_GlobalScore = ? , u_easyModeScore = ?  WHERE u_uname = ?";
	try {
		ps = MyConnection.getConnection().prepareStatement(query);
		ps.setString(1, ""+EasyModeScore);
		ps.setString(2, "0");
		ps.setString(3, ""+uname);
		ps.executeUpdate();
		
	}catch (SQLException ex){
		Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
	}
	return EasyModeScore;
}


/*
 * Update Score Value For the UserName 
 */
public String UpdateHealth(String Username){
	PreparedStatement ps;
    ResultSet rs;
	 String query = "SELECT u_Health FROM `the_app_users` WHERE `u_uname` = ?";
	 try {
	        ps = MyConnection.getConnection().prepareStatement(query);
	        ps.setString(1, uname);
	        rs = ps.executeQuery();
	        
	        if(rs.next())
	        {
	        	lifeCounter = rs.getInt("u_Health");
	        	updatedhealth = String.valueOf(lifeCounter); 
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
	    }
	 return updatedhealth;
	
}


public String UpdateScore(String Username){
	PreparedStatement ps;
    ResultSet rs;
    String query = "SELECT u_easyModeScore FROM `the_app_users` WHERE `u_uname` = ?";
    
    try {
        ps = MyConnection.getConnection().prepareStatement(query);
        ps.setString(1, uname);
        rs = ps.executeQuery();
        
        if(rs.next())
        {
        	EasyModeScore = rs.getInt("u_easyModeScore");
        	updatedScore = String.valueOf(EasyModeScore); 
        }
    } catch (SQLException ex) {
        Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
    }
 return updatedScore;
}

/*
 * Launch the application. 
 */
public void startTimer(){
	timer = new Timer();
	timer.schedule(new NewTask(),0,1000);
}
public void EasyNewScreen() {
 EventQueue.invokeLater(new Runnable() {
  public void run() {
   try {
	   
	   EasyMode frame = new EasyMode(uname,Fname);
	   frame.startTimer();
	   frame.EasyFrame.setVisible(true);
	   
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
 });
} 

/* 
 * Create the frame. 
 */
public EasyMode(String uname,String Fname) {
 this.uname = uname;
 this.Fname = Fname;
 ModeNum = 1;
 UpdateScore(uname);
 UpdateHealth(uname);
 //updateMode(level, ModeNum, StartTime, uname); //Update 
 getEzModeLvl(uname);
 initialize();
 EasyFrame.setLocationRelativeTo(null);
 randomClass = new SecureRandom();
 generateRandom();
 OrginalStarting = StartTime; 
}
/* * 
 * Generate Random Numbers . 
 * */
	void generateRandom() {

	 	if (EasyModeScore >= 100 && EasyModeScore <= 130) { modeDone = true; }
          if(lifeCounter==2) {
	
	     Life_3.setIcon(new ImageIcon("images/FailHeart.png"));
	     Life_2.setIcon(new ImageIcon("images/FailHeart.png"));
    }
		switch(lifeCounter){
			case 1 :
				Life_3.setIcon(new ImageIcon("images/FailHeart.png"));
			break;
			case 2 :
				Life_2.setIcon(new ImageIcon("images/FailHeart.png"));
			break;
			case 3 : 
				Life_1.setIcon(new ImageIcon("images/FailHeart.png")); 
				timer.cancel();
				EasyFrame.dispose();
				TruncateModeLevels(); // delete all levels logs in DB . (delete Table)
				GameOverScreen Gm = new GameOverScreen(uname,Fname,EasyModeScore,ModeNum);
				Gm.GameOver();
				break;	
			
		}
		
		int n1 = randomClass.nextInt(maxLimit)+1; // +1 to avoid zero.
		int n2 = randomClass.nextInt(maxLimit)+1;
		
		int temp;
		if(n1<n2){
			temp=n1;
			n1=n2;
			n2=temp;
		}
		label_Level_Num.setText(LevelNumbers[getEzModeLvl(uname)]);
		/*
		 *  Setting this to Labels. /\ Show Only 1 number (not 1.0)
		 */
	    	Number1.setText(""+(int)+n1); 
	        Number2.setText(""+(int)+n2);
		/*
		 *  Generate The Random Operators .( * , / , + , -) 
		 */
		int op = randomClass.nextInt(4); 
		char _operator = '+'; // By Default

		switch (op) {
			case 0 :_operator = '+';
			Result = (float) Math.ceil(n1 + n2);
			break;
			case 1 :_operator = '-';
			Result = (float) Math.ceil(n1 - n2);
			break;
			case 2 :_operator = 'X';
			Result = (float) Math.ceil(n1 * n2);
			break; 
			case 3 :_operator = '/';
			Result = (float) Math.ceil(n1/n2);
			break; 
		}
		Operator.setText(""+_operator);  
	
  
		 int index = randomClass.nextInt(4);  // Picks random box from 4
		 answers[index] = (int)Result; // puts the answer in random box

		 
		 
		 for(i=0;i < 3; i++){
			 
			 int tst = (int)Result; // right answer in tst
			 int tmp = randomClass.nextInt(tst+10)+1;  // Random answer between 0-Result+10
			boolean inserted = false;
			do{
				index = randomClass.nextInt(4); // random box
				if(answers[index]== 0)	{   				
					answers[index]=tmp; 
					if(tmp==Result) // Avoid getting results in more than 1 answer box.
					answers[index]=randomClass.nextInt(4);
					inserted = true;
					}
						
		   }while(!inserted);
		
		}

		 for(i=0; i<4; i++) { 
				Options[i].setText(String.valueOf(answers[i])); // .split("\\.")[0]
				}
	}
	/* *
	 * Initialize the contents of the frame.
	 * */
		private void initialize() {
			EasyFrame = new JFrame();
			EasyFrame.setLocationRelativeTo(null); // Center form in the screen :)
			EasyFrame.setUndecorated(true); // Hides the toolbar 
			EasyFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
			EasyFrame.setType(Type.POPUP);
			EasyFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/mathit.png"));
			EasyFrame.setTitle("Brain Trainer");
			EasyFrame.setBounds(100, 100, 1366, 768);
			EasyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			EasyFrame.getContentPane().setLayout(null);
			Options = new JButton[4];
			answers = new float[4];
			
			int xbound = 1150;
			for(i=0; i < 4; i++){
  
				Options[i] = new JButton();
				Options[i].setHideActionText(true);
				Options[i].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				Options[i].setBackground(new Color(0, 0, 205));
				Options[i].setForeground(SystemColor.menu);
				Options[i].setFont(new Font("Trajan Pro", Font.PLAIN, 30));
				Options[i].setBounds(xbound, 450, 110, 100);
				xbound-=350;
				EasyFrame.getContentPane().add(Options[i]);
	  
				Options[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						String val = evt.getActionCommand();
						answered = true;
						if(val.equalsIgnoreCase(String.valueOf(Result)))
						{
							
							timer.cancel();
							int choose = JOptionPane.showConfirmDialog(null,
			                         "Correct, "
			                         + "Press Yes to Continue No To Exit",
			                         "Alert", JOptionPane.YES_NO_OPTION,
			                         JOptionPane.INFORMATION_MESSAGE);
							  if (choose == JOptionPane.YES_OPTION){
			                	 EasyModeScore += 10;
			                	 
			                	 if(EasyModeScore == 100)
			                		 modeDone = true;
			                	 if(EasyModeScore <= 100){
								
			                		 PreparedStatement ps;
			                		 String query = "UPDATE `the_app_users` SET `u_easyModeScore` = ? WHERE `u_uname` = ?";
			                		 try {
			                			 ps = MyConnection.getConnection().prepareStatement(query);
			                			 ps.setString(1, ""+EasyModeScore);
			                			 ps.setString(2, ""+uname);
			                			 ps.executeUpdate();
									
			                		 }catch (SQLException ex){
			                			 Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
			                		 }
			 							ScoreValue.setText(String.valueOf(UpdateScore(uname)));
			                	 }
			                	 else
			                	 {//when we got score 100
			                		 ScoreValue.setText(String.valueOf(UpdateScore(uname)));
			                		 EasyFrame.dispose();
			                		 resetScoreAfterMode(uname);
			                		 GameWindow gm = new GameWindow(uname, Fname,modeDone);
			                		 gm.main();
			                		 EasyModeDone ezdDone = new EasyModeDone(Fname);
			                		 ezdDone.main();
			                	 }
			                	 if(level < 11)
			                	 {
			                		 updateMode(level, ModeNum, Integer.parseInt(CountDown.getText()),uname);
			                		 level++;
			                		 setEzModeLvl(uname, level);
			                		 getEzModeLvl(uname);
			                		 System.out.println("After correct answer :" + level);
			                		
			                	 }
			 				
			                	 answers = new float[4];
			                	 StartTime=OrginalStarting;
			                	 generateRandom();
			                	 timer = new Timer();
			                	 timer.schedule(new NewTask(), 0,1000);
			                 	}
			                 
			                 /*
			                  * If the user Doesn't want to continue to the next level
			                  */
			                 
			                 else{
			                	 timer.cancel();
			                	 EasyFrame.dispose();
			                     GameWindow gm = new GameWindow(uname,Fname,modeDone);
			                     gm.main();
			                 }
			                 /* -----------------------------------------------------------*/
			 				}
						/*
						 * If the answer is wrong
						 */
			 				else
			 				{
			 					
			 					timer.cancel();
			 					JOptionPane.showMessageDialog(null,"You're Wrong!");
			 					lifeCounter++;
			 					//Update health field in DB
			 					 PreparedStatement ps;
		                		 String query = "UPDATE `the_app_users` SET `u_Health` = ? WHERE `u_uname` = ?";
		                		 try {
		                			 ps = MyConnection.getConnection().prepareStatement(query);
		                			 ps.setString(1, ""+lifeCounter);
		                			 ps.setString(2, ""+uname);
		                			 ps.executeUpdate();
								
		                		 }catch (SQLException ex){
		                			 Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
		                		 }
		                		
			 					answers = new float[4];
			 					StartTime=OrginalStarting;
			 					timer = new Timer();
			 					timer.schedule(new NewTask(), 0,1000);
			 					generateRandom();
			 				}
						
		 }
	 });
  }
 		
 			lblTime = new JLabel();
 			lblTime.setBounds(10, 56, 48, 45);
 			EasyFrame.getContentPane().add(lblTime);
 			lblTime.setIcon(new ImageIcon("images/timer.png"));
 			lblTime.setHorizontalAlignment(SwingConstants.CENTER);
 			lblTime.setForeground(Color.RED);
 			lblTime.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
 		
 			CountDown = new JLabel("10");
 			CountDown.setBounds(60, 56, 50, 45);
 			EasyFrame.getContentPane().add(CountDown);
 			CountDown.setHorizontalAlignment(SwingConstants.CENTER);
 			CountDown.setForeground(Color.RED);
 			CountDown.setFont(new Font("Verdana", Font.PLAIN, 20));
 	
	 	
	 		ScoreValue = new JLabel();
	 		ScoreValue.setBounds(1298, 53, 60, 25);
	 		EasyFrame.getContentPane().add(ScoreValue);
	 		ScoreValue.setText(UpdateScore(uname));
	 		ScoreValue.setHorizontalAlignment(SwingConstants.CENTER);
	 		ScoreValue.setForeground(Color.RED);
	 		ScoreValue.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
	 	
	 		JLabel lblScore = new JLabel("Score :");
	 		lblScore.setBounds(1230, 56, 78, 20);
	 		EasyFrame.getContentPane().add(lblScore);
	 		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
	 		lblScore.setForeground(Color.RED);
	 		lblScore.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
	 
		 	JPanel panel = new JPanel();
		 	panel.setLayout(null);
		 	panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		 	panel.setBackground(Color.GRAY);
		 	panel.setBounds(0, 0, 1366, 45);
		 	EasyFrame.getContentPane().add(panel);
		 
		 	lblLevel = new JLabel("Level :");
		 	lblLevel.setHorizontalAlignment(SwingConstants.LEFT);
		 	lblLevel.setForeground(Color.BLUE);
		 	lblLevel.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		 	lblLevel.setBounds(10, 11, 65, 20);
		 	panel.add(lblLevel);
		 
		 	JLabel label_exit = new JLabel("X");
		 	label_exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 	label_exit.addMouseListener(new MouseAdapter() {
		  

	 		@Override //Exit Button
	 		public void mouseClicked(MouseEvent e){
	 				int choose = JOptionPane.showConfirmDialog(null,
	                         "Do you really want to Return to Main Menu ?",
	                         "Confirm Close", JOptionPane.YES_NO_OPTION,
	                         JOptionPane.INFORMATION_MESSAGE);
	                 if (choose == JOptionPane.YES_OPTION) {
	                	timer.cancel();
	                	EasyFrame.dispose();
	                    GameWindow gm = new GameWindow(uname,Fname,modeDone);
	                    gm.main();
	                 } else 
	                 	{
	                	 timer.cancel();
	                 	}
	             }
	
	 		@Override // Some Style When Mouse Enter in the Label Change color of the label to Red
	 		public void mouseEntered(MouseEvent arg0) {
	 			label_exit.setForeground(Color.RED);
	 		}
  
	 		@Override // Getting back the Defult Color (Blue)
	 		public void mouseExited(MouseEvent arg0) {
	 			label_exit.setForeground(Color.BLUE);
	 		}
	 	});
		 	label_exit.setHorizontalAlignment(SwingConstants.CENTER);
		 	label_exit.setForeground(Color.BLUE);
		 	label_exit.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		 	label_exit.setBounds(1340, 10, 22, 22);
		 	panel.add(label_exit);
		 
		 	JLabel label_minmize = new JLabel("-");
		 	label_minmize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 	label_minmize.addMouseListener(new MouseAdapter() {
		 		@Override // Minimize Button 
		 		public void mouseClicked(MouseEvent arg0) {
		 			EasyFrame.setState(JFrame.ICONIFIED);
		 		}
		 		@Override // Some Style When Mouse Enter in the Label Change color of the label to Red
		 		public void mouseEntered(MouseEvent arg0) {
		 			label_minmize.setForeground(Color.RED);
		 		}
		  
		 		@Override // Getting back the Defult Color (Blue)
		 		public void mouseExited(MouseEvent arg0) {
		 			label_minmize.setForeground(Color.BLUE);
		 		}
		 	});
		 	label_minmize.setHorizontalAlignment(SwingConstants.CENTER);
		 	label_minmize.setForeground(Color.BLUE);
		 	label_minmize.setFont(new Font("Trajan Pro", Font.BOLD, 24));
		 	label_minmize.setBounds(1315, 10, 22, 22);
		 	panel.add(label_minmize);
		
		 	label_Level_Num = new JLabel();
		 	label_Level_Num.setText(String.valueOf(getEzModeLvl(uname)));
		 	label_Level_Num.setHorizontalAlignment(SwingConstants.CENTER);
		 	label_Level_Num.setForeground(Color.BLUE);
		 	label_Level_Num.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		 	label_Level_Num.setBounds(68, 10, 45, 25);
		 	panel.add(label_Level_Num);
		 	
		 	Life_1 = new JLabel();
		 	Life_1.setIcon(new ImageIcon("images/Heart.png"));
		 	Life_1.setHorizontalAlignment(SwingConstants.CENTER);
		 	Life_1.setForeground(Color.BLUE);
		 	Life_1.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		 	Life_1.setBounds(614, 0, 48, 45);
		 	panel.add(Life_1);
		 	
		 	Life_2 = new JLabel();
		 	Life_2.setIcon(new ImageIcon("images/Heart.png"));
		 	Life_2.setHorizontalAlignment(SwingConstants.CENTER);
		 	Life_2.setForeground(Color.BLUE);
		 	Life_2.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		 	Life_2.setBounds(664, 0, 48, 45);
		 	panel.add(Life_2);
		 	
		 	Life_3 = new JLabel();
		 	Life_3.setIcon(new ImageIcon("images/Heart.png"));
		 	Life_3.setHorizontalAlignment(SwingConstants.CENTER);
		 	Life_3.setForeground(Color.BLUE);
		 	Life_3.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		 	Life_3.setBounds(714, 0, 48, 45);
		 	panel.add(Life_3);
		
		 	Number1 = new JLabel("2");
		 	Number1.setForeground(Color.MAGENTA);
		 	Number1.setHorizontalAlignment(SwingConstants.CENTER);
		 	Number1.setFont(new Font("Trajan Pro", Font.PLAIN, 60));
		 	Number1.setBounds(533, 56, 125, 91);
		 	EasyFrame.getContentPane().add(Number1);
		 
		 	Number2 = new JLabel("9");
		 	Number2.setForeground(Color.MAGENTA);
		 	Number2.setHorizontalAlignment(SwingConstants.CENTER);
		 	Number2.setFont(new Font("Trajan Pro", Font.PLAIN, 60));
		 	Number2.setBounds(745, 56, 116, 91);
		 	EasyFrame.getContentPane().add(Number2);
		 
		 	Operator = new JLabel("+");
		 	Operator.setForeground(new Color(0, 0, 139));
		 	Operator.setHorizontalAlignment(SwingConstants.CENTER);
		 	Operator.setFont(new Font("Onyx", Font.BOLD, 60));
		 	Operator.setBounds(678, 62, 46, 72);
		 	EasyFrame.getContentPane().add(Operator);
		 
		 	JLabel lblBackground = new JLabel("");
		 	lblBackground.setIcon(new ImageIcon("images/Background.jpg"));
		 	lblBackground.setBounds(0, 29, 1366, 768);
		 	EasyFrame.getContentPane().add(lblBackground);
			}	
		}