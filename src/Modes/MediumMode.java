package Modes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.invoke.VolatileCallSite;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.w3c.dom.html.HTMLLabelElement;

import sun.awt.TracedEventQueue;
import Modes.EasyMode.NewTask;

import com.Game.main.GameOverScreen;
import com.Game.main.GameWindow;
import com.Game.main.MyConnection;
import com.Game.main.RegisterForm;
import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.tracing.dtrace.ProviderAttributes;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.awt.event.MouseMotionAdapter;

public class MediumMode extends JFrame {

	// DragingDrop In java !! for the mode
	/**
	 * Default SerialVersion Number
	 */
	private static final long serialVersionUID = -7457628310765945938L;
	public JFrame MediumFrame;

	private String uname;
	private String fname;
	public JLabel Life_1, Life_2, Life_3, Life_4, Life_5;
	public JLabel lblTime;
	private JLabel CountDown;
	private JLabel Number1;
	private JLabel Number2;
	private JLabel Number3;
	private ArrayList<JLabel> history = new ArrayList<JLabel>();
	private JLabel Operator;
	private JLabel Operator2;
	private JLabel ScoreValue;
	private JLabel answerNum;
	private JLabel saveQuestion;
	private JButton[] Options;
	private Double[] answers;
	private String[] arr ;
	private Popup popup;
	@SuppressWarnings("unused")
	private boolean answered = false;
	private boolean firstRun = false;
	public boolean modeDone = false;
	public int ModeNum;
	private int op;
	private int op2;
	private char _operator = '+'; // By Default
	private char _operator2 = '-'; // By Default
	private String tmp2 = "";
	private String tmp3 = "" ;

	// private int LNumber1 = 0 , LNumber2 = 0;

	private double Result1 = 0;
	private double Result2 = 0;
	private int lifeCounter = 6;
	private int medModeScore = 0;
	public int globalScore;
	private int StartTime = 20; // Count Down for Each Level
	private int OrginalStarting;
	private int maxLimit = 8; // Desired No. It can By Any Number.
	private SecureRandom randomClass;
	private Timer timer;
	private JLabel label_Level_Num;
	private JLabel lblLevel;
	private int level = 0;
	private int i;
	private int n1, n2, n3;
	private int answerNumber;
	private String updatedScore;
	private String number1Value;
	private String number2Value;
	private String number3Value;
	private int MedModeLvl = 0;
	private int xMouse1;
	private int yMouse1;
	private int xMouse2;
	private int yMouse2;
	private int xMouse3;
	private int yMouse3;
	private int xMouse4;
	private int yMouse4;
	private int xMouse5;
	private int yMouse5;
	private int xMouse6;
	private int yMouse6;
	private int xMouse7;
	private int yMouse7;
	private int xMouse8;
	private int yMouse8;
	private int xMouse9;
	private int yMouse9;
	private int xMouse0;
	private int yMouse0;
	private JLabel num1;
	private JLabel num2;
	private JLabel num3;
	private JLabel num4;
	private JLabel num5;
	private JLabel num6;
	private JLabel num7;
	private JLabel num8;
	private JLabel num9;
	private JLabel num0;
	private PrintWriter outPutStream;
	private String updatedhealth;

	/*
	 * Update Score Value For the UserName 
	 */
	public String getHealth(String Username){
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

/*
 * Update u_health field in DB
 */
	public void setHealth(String username){
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
	}
	
	/*
	 * Check Score Value For the UserName
	 */
	public int CheckGlobalScore(String Username) {
		PreparedStatement ps;
		ResultSet rs;
		String query = "SELECT u_GlobalScore FROM `the_app_users` WHERE `u_uname` = ?";

		try {
			ps = MyConnection.getConnection().prepareStatement(query);
			ps.setString(1, uname);

			rs = ps.executeQuery();

			if (rs.next()) {
				globalScore = rs.getInt("u_GlobalScore");
			}
		} catch (SQLException ex) {
			Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return globalScore;
	}

	/**
	 * @param Username
	 * @return easyModeLvl
	 */
	public int getMedModeLvl(String Username) {
		PreparedStatement ps;
		ResultSet rs;
		String query = "SELECT u_medModeLvl FROM `the_app_users` WHERE `u_uname` =?";

		try {
			ps = MyConnection.getConnection().prepareStatement(query);
			ps.setString(1, uname);
			rs = ps.executeQuery();

			if (rs.next()) {
				MedModeLvl = rs.getInt("u_medModeLvl");
				level = MedModeLvl;
			}
		} catch (SQLException ex) {
			Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return MedModeLvl;

	}

	/**
	 * @param Username
	 */
	public void setMedModeLvl(String Username, int modeLevel) {
		PreparedStatement ps;
		String query = "UPDATE `the_app_users` SET `u_MedModeLvl` = ? WHERE `u_uname` = ?";
		try {

			ps = MyConnection.getConnection().prepareStatement(query);
			ps.setString(1, "" + modeLevel);
			ps.setString(2, "" + Username);
			ps.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

	/*
	 * Update Score Value For the UserName
	 */

	public String getUpdatedScore(String Username) {
		PreparedStatement ps;
		ResultSet rs;
		String query = "SELECT u_medModeScore FROM `the_app_users` WHERE `u_uname` = ?";

		try {
			ps = MyConnection.getConnection().prepareStatement(query);
			ps.setString(1, uname);

			rs = ps.executeQuery();

			if (rs.next()) {
				medModeScore = rs.getInt("u_medModeScore");
				updatedScore = String.valueOf(medModeScore);
			}
		} catch (SQLException ex) {
			Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return updatedScore;
	}
	
	public void setUpdatedScore(String userName){
		 PreparedStatement ps;
		 String query = "UPDATE `the_app_users` SET `u_medModeScore` = ? WHERE `u_uname` = ?";
		 try {
			 ps = MyConnection.getConnection().prepareStatement(query);
			 ps.setString(1, ""+medModeScore);
			 ps.setString(2, ""+uname);
			 ps.executeUpdate();
		
		 }catch (SQLException ex){
			 Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
	
	/**
	 * Saving Custom Question in DB
	 * @param username
	 */
	public void SaveQuestion(String username){
		
		PreparedStatement ps;
    	String query = "INSERT INTO `saved_questions`(`Question`, `UserName`) VALUES (?,?)";
    	try {
			ps = MyConnection.getConnection().prepareStatement(query);
			ps.setString(1, "? "+_operator+" ? "+_operator2+" ? = "+answerNumber);
			ps.setString(2, ""+username);
			 if(ps.executeUpdate() > 0)
		        {
		        	JOptionPane.showMessageDialog(null, "Question Saved !");
		        	//System.out.println("1 question saved!");
		        }
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	class NewTask extends TimerTask {
		@Override
		public void run() {

			if (StartTime >= 0) {
				CountDown.setText(StartTime + "");
				StartTime--;
				if(Number1.getText() != "" && Number2.getText() != "" && Number3.getText() != "")
					checkAnswer();
				
			} else {
				if (lifeCounter != 1) {
					lifeCounter--;
					setHealth(uname);
					Number1.setText("");
					Number2.setText("");
					Number3.setText("");
					timer.cancel();
					StartTime = OrginalStarting;
					startTimer();
					generateRandom();
				}
			}

		}
	}

	/*
	 * Func' To start the timer
	 */
	public void startTimer() {
		timer = new Timer();
		timer.schedule(new NewTask(), 0, 1000);
	}

	/**
	 * Launch the application.
	 */
	public void MediumNewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MediumMode frame = new MediumMode(uname, fname);
					frame.MediumFrame.setVisible(true);
					frame.startTimer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public MediumMode(String Username, String Fname) {
		uname = Username;
		fname = Fname;
		ModeNum = 2;
		randomClass = new SecureRandom();
		OrginalStarting = StartTime;
		initialize();
		generateRandom();
		firstRun = false;
		
		}

	/* *
	 * Generate Random Numbers .
	 */

	public void Connector(){
		/* Test */
		if(Number1.getText() != ""){
			int num1 = Integer.parseInt(Number1.getText());
			//System.out.println("num 2: "+ num1); //1
			}
		/*End of Test */
	}
	
	
	void generateRandom() {
		
		getMedModeLvl(uname);
		getUpdatedScore(uname);
		label_Level_Num.setText(""+level);
		ScoreValue.setText(""+updatedScore);
		checkAnswer();
		getHealth(uname);
		
		if(lifeCounter == 2) {
		     Life_3.setIcon(new ImageIcon("images/FailHeart.png"));
		     Life_2.setIcon(new ImageIcon("images/FailHeart.png"));
	    }else if (lifeCounter == 4) {
	    	Life_5.setIcon(new ImageIcon("images/FailHeart.png"));
		    Life_4.setIcon(new ImageIcon("images/FailHeart.png"));
		}else if (lifeCounter == 3) {
			Life_5.setIcon(new ImageIcon("images/FailHeart.png"));
			Life_4.setIcon(new ImageIcon("images/FailHeart.png"));
		    Life_3.setIcon(new ImageIcon("images/FailHeart.png"));
		}
		
		
		  switch(lifeCounter){
			  
			  case 5 : 
				  Life_5.setIcon(new ImageIcon("images/FailHeart.png"));
				  break; 
			  case 4 :
				  Life_4.setIcon(new ImageIcon("images/FailHeart.png"));
				  break;
			  case 3 : 
				  Life_3.setIcon(new ImageIcon("images/FailHeart.png"));
				  break; 
			  case 2 :
				  Life_2.setIcon(new ImageIcon("images/FailHeart.png"));
				  break;
			  case 1 :
				  Life_1.setIcon(new ImageIcon("images/FailHeart.png"));
				  //timer.cancel(); 
				  MediumFrame.dispose(); 
				  GameOverScreen Gm = new GameOverScreen(uname,fname,medModeScore,ModeNum);
				  Gm.GameOver();
				  break;
		  }
	
		op = randomClass.nextInt(4);
		op2 = randomClass.nextInt(4);
		
		switch (op) {
			case 0 :
				_operator = '+';
				break;
			case 1 :
				_operator = '-';
				break;
			case 2 :
				_operator = 'X';
				break;
			case 3 :
				_operator = '/';
				break;
		}
		switch (op2) {
			case 0 :
				_operator2 = '+';
				break;
			case 1 :
				_operator2 = '-'; 
				break;
			case 2 :
				_operator2 = 'X';
				break;
			case 3 :
				_operator2 = '/';
				break;
		}

		Operator.setText("" + _operator);
		Operator2.setText("" + _operator2);

		int rnd = randomClass.nextInt(10) + 1;
		answerNum.setText("" + rnd);
		answerNumber = Integer.parseInt(answerNum.getText());
		//System.out.println(answerNumber);
		
		solve(_operator,_operator2,answerNumber);
		
	}
	
	/**
	 * Initialise the contents of the frame.
	 */
	
	private void initialize() {
		
		firstRun = true;
		MediumFrame = new JFrame();
		MediumFrame.setLocationRelativeTo(null); // Center form in the screen :)
		MediumFrame.setUndecorated(true); // Hide the toolbar
		MediumFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		MediumFrame.setResizable(false);
		MediumFrame.setType(Type.POPUP);
		MediumFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/mathit.png"));
		MediumFrame.setTitle("Brain Trainer");
		MediumFrame.setBounds(0, 0, 1366, 768);
		MediumFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MediumFrame.getContentPane().setLayout(null);

		Options = new JButton[11];
		answers = new Double[11];

		/*
		 * Adding Numbers buttons like a calculator (Old)
		 * 
		 * 1 2 3 
		 * 4 5 6 
		 * 7 8 9 
		 * 	 0
		 */

		/*
		 * int xbound = 280; int ybound = 200; int num = 1;
		 * 
		 * for (int i = 0; i <= 2; i++) { for (int j = 0; j < 3; j++) {
		 * 
		 * Options[i] = new JButton(); Options[i].setText(""+num);
		 * Options[i].setHideActionText(true);
		 * Options[i].setCursor(Cursor.getPredefinedCursor
		 * (Cursor.DEFAULT_CURSOR)); Options[i].setBackground(new Color(0, 0,
		 * 205)); Options[i].setForeground(SystemColor.menu);
		 * Options[i].setFont(new Font("Trajan Pro", Font.PLAIN, 30));
		 * Options[i].setBounds(xbound, ybound, 70, 70); // 235, 351, 70, 70
		 * 
		 * xbound+=80; MediumFrame.getContentPane().add(Options[i]); num++; }
		 * xbound = 280; ybound += 80; }
		 */

		lblTime = new JLabel();
		lblTime.setBounds(10, 56, 48, 45);
		MediumFrame.getContentPane().add(lblTime);
		lblTime.setIcon(new ImageIcon("images/timer.png"));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(Color.RED);
		lblTime.setFont(new Font("Trajan Pro", Font.PLAIN, 18));

		CountDown = new JLabel("10");
		CountDown.setBounds(60, 56, 50, 45);
		MediumFrame.getContentPane().add(CountDown);
		CountDown.setHorizontalAlignment(SwingConstants.CENTER);
		CountDown.setForeground(Color.RED);
		CountDown.setFont(new Font("Verdana", Font.PLAIN, 20));

		ScoreValue = new JLabel();
		ScoreValue.setBounds(1290, 53, 60, 25);
		MediumFrame.getContentPane().add(ScoreValue);
		ScoreValue.setText(getUpdatedScore(uname));
		ScoreValue.setHorizontalAlignment(SwingConstants.CENTER);
		ScoreValue.setForeground(Color.RED);
		ScoreValue.setFont(new Font("Trajan Pro", Font.PLAIN, 24));

		JLabel lblScore = new JLabel("Score :");
		lblScore.setBounds(1222, 56, 78, 20);
		MediumFrame.getContentPane().add(lblScore);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.RED);
		lblScore.setFont(new Font("Trajan Pro", Font.PLAIN, 18));

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 1377, 45);
		MediumFrame.getContentPane().add(panel);

		lblLevel = new JLabel("Level :");
		lblLevel.setHorizontalAlignment(SwingConstants.LEFT);
		lblLevel.setForeground(Color.BLUE);
		lblLevel.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		lblLevel.setBounds(10, 11, 65, 20);
		panel.add(lblLevel);
		
		JLabel BackButton = new JLabel("");
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BackButton.setIcon(new ImageIcon("images/Back_Clicked_Button.png"));
				
				int choose = JOptionPane.showConfirmDialog(null,
						"Do you really want to Return to Main Menu ?",
						"Confirm Close", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (choose == JOptionPane.YES_OPTION) {
					timer.cancel();
					MediumFrame.dispose();
					GameWindow gm = new GameWindow(uname, fname, modeDone);
					gm.main();
				}else {
					/*
					int currTime = Integer.parseInt(CountDown.getText());
					startTimer();
					StartTime = currTime;
					*/
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BackButton.setIcon(new ImageIcon("images/Back_Clicked_Button.png"));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BackButton.setIcon(new ImageIcon("images/Back_Button.png"));
			}
		});
		BackButton.setIcon(new ImageIcon("images/Back_Button.png"));
		BackButton.setBounds(950, 479, 80, 80);
		MediumFrame.getContentPane().add(BackButton);

		JLabel label_exit = new JLabel("X");
		label_exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_exit.addMouseListener(new MouseAdapter() {

			@Override
			// Exit Button
			public void mouseClicked(MouseEvent e) {
				int choose = JOptionPane.showConfirmDialog(null,
						"Do you really want to Return to Main Menu ?",
						"Confirm Close", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (choose == JOptionPane.YES_OPTION) {
					MediumFrame.dispose();
					timer.cancel();
					GameWindow gm = new GameWindow(uname, fname, modeDone);
					gm.main();
				} else {
					/*
					int currTime = Integer.parseInt(CountDown.getText());
					startTimer();
					StartTime = currTime;
					*/
				}
			}

			@Override
			// Some Style When Mouse Enter in the Label Change Label color to Red
			public void mouseEntered(MouseEvent arg0) {
				label_exit.setForeground(Color.RED);
			}

			@Override
			// Getting back the Defult Color (Blue)
			public void mouseExited(MouseEvent arg0) {
				label_exit.setForeground(Color.BLUE);
			}
		});
		label_exit.setHorizontalAlignment(SwingConstants.CENTER);
		label_exit.setForeground(Color.BLUE);
		label_exit.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		label_exit.setBounds(1336, 11, 22, 22);
		panel.add(label_exit);

		JLabel label_minmize = new JLabel("-");
		label_minmize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_minmize.addMouseListener(new MouseAdapter() {
			@Override
			// Minimize Button
			public void mouseClicked(MouseEvent arg0) {
				MediumFrame.setState(JFrame.ICONIFIED);
			}
			@Override
			// Some Style When Mouse Enter in the Label Change Label color to Red
			public void mouseEntered(MouseEvent arg0) {
				label_minmize.setForeground(Color.RED);
			}

			@Override
			// Getting back the Defult Color (Blue)
			public void mouseExited(MouseEvent arg0) {
				label_minmize.setForeground(Color.BLUE);
			}
		});
		label_minmize.setHorizontalAlignment(SwingConstants.CENTER);
		label_minmize.setForeground(Color.BLUE);
		label_minmize.setFont(new Font("Trajan Pro", Font.BOLD, 24));
		label_minmize.setBounds(1304, 11, 22, 22);
		panel.add(label_minmize);

		label_Level_Num = new JLabel();
		label_Level_Num.setText(String.valueOf(getMedModeLvl(uname)));
		label_Level_Num.setHorizontalAlignment(SwingConstants.CENTER);
		label_Level_Num.setForeground(Color.BLUE);
		label_Level_Num.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		label_Level_Num.setBounds(68, 10, 45, 25);
		panel.add(label_Level_Num);

		Life_1 = new JLabel();
		Life_1.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Heart.png"));
		Life_1.setHorizontalAlignment(SwingConstants.CENTER);
		Life_1.setForeground(Color.BLUE);
		Life_1.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		Life_1.setBounds(545, -1, 48, 45);
		panel.add(Life_1);

		Life_2 = new JLabel();
		Life_2.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Heart.png"));
		Life_2.setHorizontalAlignment(SwingConstants.CENTER);
		Life_2.setForeground(Color.BLUE);
		Life_2.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		Life_2.setBounds(595, -1, 48, 45);
		panel.add(Life_2);

		Life_3 = new JLabel();
		Life_3.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Heart.png"));
		Life_3.setHorizontalAlignment(SwingConstants.CENTER);
		Life_3.setForeground(Color.BLUE);
		Life_3.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		Life_3.setBounds(645, -1, 48, 45);
		panel.add(Life_3);
		
		Life_4 = new JLabel();
		Life_4.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Heart.png"));
		Life_4.setHorizontalAlignment(SwingConstants.CENTER);
		Life_4.setForeground(Color.BLUE);
		Life_4.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		Life_4.setBounds(695, -1, 48, 45);
		panel.add(Life_4);
		
		Life_5 = new JLabel();
		Life_5.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Heart.png"));
		Life_5.setHorizontalAlignment(SwingConstants.CENTER);
		Life_5.setForeground(Color.BLUE);
		Life_5.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		Life_5.setBounds(745, -1, 48, 45);
		panel.add(Life_5);

		Number1 = new JLabel("");
		Number1.setHorizontalTextPosition(SwingConstants.LEADING);
		Number1.setTransferHandler(new TransferHandler("text"));
		//Number1.setTransferHandler(new TransferHandler.TransferSupport(arg1, "text"));
		Number1.setForeground(Color.ORANGE);
		Number1.setHorizontalAlignment(SwingConstants.CENTER);
		Number1.setFont(new Font("Trajan Pro", Font.PLAIN, 60));
		Number1.setBounds(342, 87, 116, 100);
		MediumFrame.getContentPane().add(Number1);

		Number2 = new JLabel("");
		Number2.setTransferHandler(new TransferHandler("text"));
		Number2.setForeground(Color.ORANGE);
		Number2.setHorizontalAlignment(SwingConstants.CENTER);
		Number2.setFont(new Font("Trajan Pro", Font.PLAIN, 60));
		Number2.setBounds(535, 96, 116, 91);
		MediumFrame.getContentPane().add(Number2);

		Number3 = new JLabel("");
		Number3.setTransferHandler(new TransferHandler("text"));
		Number3.setHorizontalAlignment(SwingConstants.CENTER);
		Number3.setForeground(Color.ORANGE);
		Number3.setFont(new Font("Trajan Pro", Font.PLAIN, 60));
		Number3.setBounds(711, 96, 116, 91);
		MediumFrame.getContentPane().add(Number3);

		Operator = new JLabel("+");
		Operator.setForeground(new Color(0, 0, 139));
		Operator.setHorizontalAlignment(SwingConstants.CENTER);
		Operator.setFont(new Font("Onyx", Font.BOLD, 60));
		Operator.setBounds(476, 102, 46, 72);
		MediumFrame.getContentPane().add(Operator);

		Operator2 = new JLabel("+");
		Operator2.setHorizontalAlignment(SwingConstants.CENTER);
		Operator2.setForeground(new Color(0, 0, 139));
		Operator2.setFont(new Font("Onyx", Font.BOLD, 60));
		Operator2.setBounds(661, 102, 46, 72);
		MediumFrame.getContentPane().add(Operator2);

		JLabel equalsLbl = new JLabel("=");
		equalsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		equalsLbl.setForeground(new Color(0, 0, 139));
		equalsLbl.setFont(new Font("Onyx", Font.BOLD, 60));
		equalsLbl.setBounds(832, 102, 46, 72);
		MediumFrame.getContentPane().add(equalsLbl);

		answerNum = new JLabel();
		answerNum.setHorizontalAlignment(SwingConstants.CENTER);
		answerNum.setForeground(Color.ORANGE);
		answerNum.setFont(new Font("Trajan Pro", Font.PLAIN, 60));
		answerNum.setBounds(888, 96, 146, 91);
		MediumFrame.getContentPane().add(answerNum);

		JLabel undoLbl = new JLabel();
		undoLbl.setText("Undo");
		undoLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (popup != null) {
					popup.hide();
				}
				JLabel text = new JLabel("Undo");// + e.getPoint());
				popup = PopupFactory.getSharedInstance().getPopup(
						e.getComponent(), text, e.getXOnScreen(),
						e.getYOnScreen());
				popup.show();
				undoLbl.setIcon(new ImageIcon("images/UndoClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (popup != null) {
					popup.hide();
				}
				undoLbl.setIcon(new ImageIcon("images/Undo.png"));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				undoLbl.setIcon(new ImageIcon("images/UndoClicked.png"));
				if (Number3.getText() == null) {
					if (Number2.getText() == null) {
						if (Number1.getText() == null) {
							JOptionPane
									.showMessageDialog(null,
											"Attention you didn't Get any Numbers Yet !");

						} else {
							Number1.setText(null);
						}
					} else {
						Number2.setText(null);
					}
				} else {

					Number3.setText(null);
				}
			}
		});
		undoLbl.setIcon(new ImageIcon("images/Undo.png"));
		undoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		undoLbl.setForeground(Color.GREEN);
		undoLbl.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		undoLbl.setBounds(235, 304, 130, 70);
		MediumFrame.getContentPane().add(undoLbl);

		JLabel lblClear = new JLabel();
		lblClear.setText("Clear");
		lblClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (popup != null) {
					popup.hide();
				}
				JLabel text = new JLabel("Clear");// + e.getPoint());
				popup = PopupFactory.getSharedInstance().getPopup(
						e.getComponent(), text, e.getXOnScreen(),
						e.getYOnScreen());
				popup.show();
				lblClear.setIcon(new ImageIcon("images/BroomClicked.png"));

			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (popup != null) {
					popup.hide();
				}
				lblClear.setIcon(new ImageIcon("images/Broom.png"));

			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblClear.setIcon(new ImageIcon("images/BroomClicked.png"));
				Number1.setText("");
				Number1.setIcon(null);
				Number2.setText("");
				Number2.setIcon(null);
				Number3.setText("");
				Number3.setIcon(null);

			}
		});
		lblClear.setIcon(new ImageIcon("images/Broom.png"));
		lblClear.setHorizontalAlignment(SwingConstants.CENTER);
		lblClear.setForeground(Color.GREEN);
		lblClear.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		lblClear.setBounds(888, 281, 130, 70);
		MediumFrame.getContentPane().add(lblClear);

		num1 = new JLabel("1");
		num1.setFont(num1.getFont().deriveFont(0f));
		num1.setForeground(Color.DARK_GRAY);
		num1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg1) {

				xMouse1 = arg1.getX();
				yMouse1 = arg1.getY();

			}
			@Override
			public void mouseReleased(MouseEvent arg1) {
				JComponent jc = (JComponent) arg1.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, arg1, TransferHandler.COPY);
				num1.setBounds(500, 210, 70, 70); // 
			}
		});

		num1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg1) {

				int x = arg1.getXOnScreen();
				int y = arg1.getYOnScreen();
				num1.setLocation(x - xMouse1, y - yMouse1);

			}
		});
		num1.setTransferHandler(new TransferHandler("text"));
		num1.setHorizontalAlignment(SwingConstants.CENTER);
		num1.setBounds(500, 210, 70, 70);
		num1.setIcon(new ImageIcon("images/1.png"));
		MediumFrame.getContentPane().add(num1);

		num2 = new JLabel("2");
		num2.setFont(num2.getFont().deriveFont(0f));
		num2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg2) {

				xMouse2 = arg2.getX();
				yMouse2 = arg2.getY();

			}
			@Override
			public void mouseReleased(MouseEvent arg2) {
				JComponent jc = (JComponent) arg2.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, arg2, TransferHandler.COPY);
				num2.setBounds(600, 210, 70, 70);
			}
		});

		num2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				num2.setLocation(x - xMouse2, y - yMouse2);

			}
		});
		num2.setTransferHandler(new TransferHandler("text"));
		num2.setHorizontalAlignment(SwingConstants.CENTER);
		num2.setBounds(600, 210, 70, 70);
		num2.setIcon(new ImageIcon("images/2.png"));
		MediumFrame.getContentPane().add(num2);

		num3 = new JLabel("3");
		num3.setFont(num3.getFont().deriveFont(0f));
		num3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg3) {

				xMouse3 = arg3.getX();
				yMouse3 = arg3.getY();

			}
			@Override
			public void mouseReleased(MouseEvent arg3) {
				JComponent jc = (JComponent) arg3.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, arg3, TransferHandler.COPY);
				num3.setBounds(700, 210, 70, 70);
			}
		});

		num3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				num3.setLocation(x - xMouse3, y - yMouse3);

			}
		});
		num3.setTransferHandler(new TransferHandler("text"));
		num3.setHorizontalAlignment(SwingConstants.CENTER);
		num3.setBounds(700, 210, 70, 70);
		num3.setIcon(new ImageIcon("images/3.png"));
		MediumFrame.getContentPane().add(num3);

		num4 = new JLabel("4");
		num4.setFont(num4.getFont().deriveFont(0f));
		num4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				xMouse4 = arg0.getX();
				yMouse4 = arg0.getY();

			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JComponent jc = (JComponent) arg0.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				num4.setBounds(500, 310, 70, 70);
			}
		});

		num4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				num4.setLocation(x - xMouse4, y - yMouse4);

			}
		});
		num4.setTransferHandler(new TransferHandler("text"));
		num4.setHorizontalAlignment(SwingConstants.CENTER);
		num4.setBounds(500, 310, 70, 70);
		num4.setIcon(new ImageIcon("images/4.png"));
		MediumFrame.getContentPane().add(num4);

		num5 = new JLabel("5");
		num5.setFont(num5.getFont().deriveFont(0f));
		num5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				xMouse5 = arg0.getX();
				yMouse5 = arg0.getY();

			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JComponent jc = (JComponent) arg0.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				num5.setBounds(600, 310, 70, 70);
			}
		});

		num5.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				num5.setLocation(x - xMouse5, y - yMouse5);

			}
		});
		num5.setTransferHandler(new TransferHandler("text"));
		num5.setHorizontalAlignment(SwingConstants.CENTER);
		num5.setBounds(600, 310, 70, 70);
		num5.setIcon(new ImageIcon("images/5.png"));
		MediumFrame.getContentPane().add(num5);

		num6 = new JLabel("6");
		num6.setFont(num6.getFont().deriveFont(0f));
		num6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				xMouse6 = arg0.getX();
				yMouse6 = arg0.getY();

			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JComponent jc = (JComponent) arg0.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				num6.setBounds(700, 310, 70, 70);
			}
		});

		num6.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				num6.setLocation(x - xMouse6, y - yMouse6);

			}
		});
		num6.setTransferHandler(new TransferHandler("text"));
		num6.setHorizontalAlignment(SwingConstants.CENTER);
		num6.setBounds(700, 310, 70, 70);
		num6.setIcon(new ImageIcon("images/6.png"));
		MediumFrame.getContentPane().add(num6);

		num7 = new JLabel("7");
		num7.setFont(num7.getFont().deriveFont(0f));
		num7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				xMouse7 = arg0.getX();
				yMouse7 = arg0.getY();

			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JComponent jc = (JComponent) arg0.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				num7.setBounds(500, 410, 70, 70);
			}
		});

		num7.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				num7.setLocation(x - xMouse7, y - yMouse7);

			}
		});
		num7.setTransferHandler(new TransferHandler("text"));
		num7.setHorizontalAlignment(SwingConstants.CENTER);
		num7.setBounds(500, 410, 70, 70);
		num7.setIcon(new ImageIcon("images/7.png"));
		MediumFrame.getContentPane().add(num7);

		num8 = new JLabel("8");
		num8.setFont(num8.getFont().deriveFont(0f));
		num8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				xMouse8 = arg0.getX();
				yMouse8 = arg0.getY();

			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JComponent jc = (JComponent) arg0.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				num8.setBounds(600, 410, 70, 70);
			}
		});

		num8.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				num8.setLocation(x - xMouse8, y - yMouse8);

			}
		});
		num8.setTransferHandler(new TransferHandler("text"));
		num8.setHorizontalAlignment(SwingConstants.CENTER);
		num8.setBounds(600, 410, 70, 70);
		num8.setIcon(new ImageIcon("images/8.png"));
		MediumFrame.getContentPane().add(num8);

		num9 = new JLabel("9");
		num9.setFont(num9.getFont().deriveFont(0f));
		num9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				xMouse9 = arg0.getX();
				yMouse9 = arg0.getY();

			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JComponent jc = (JComponent) arg0.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				num9.setBounds(700, 410, 70, 70);
			}
		});

		num9.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				num9.setLocation(x - xMouse9, y - yMouse9);

			}
		});
		num9.setTransferHandler(new TransferHandler("text"));
		num9.setHorizontalAlignment(SwingConstants.CENTER);
		num9.setBounds(700, 410, 70, 70);
		num9.setIcon(new ImageIcon("images/9.png"));
		MediumFrame.getContentPane().add(num9);

		num0 = new JLabel("0");
		num0.setFont(num0.getFont().deriveFont(0f));
		num0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				xMouse0 = arg0.getX();
				yMouse0 = arg0.getY();

			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JComponent jc = (JComponent) arg0.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				num0.setBounds(600, 510, 70, 70);
			}
		});

		num0.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				num0.setLocation(x - xMouse0, y - yMouse0);

			}
		});
		num0.setTransferHandler(new TransferHandler("text"));
		num0.setHorizontalAlignment(SwingConstants.CENTER);
		num0.setBounds(600, 510, 70, 70);
		num0.setIcon(new ImageIcon("images/0.png"));
		MediumFrame.getContentPane().add(num0);
		
		JLabel SolveButton = new JLabel("Solve");
		SolveButton.setForeground(Color.WHITE);
		SolveButton.setFont(new Font("Trajan Pro", Font.PLAIN, 15));
		SolveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SolveButton.setIcon(new ImageIcon("images/Solve_Clicked_Icon.png"));
					medModeScore-=10; 
					JOptionPane.showMessageDialog(null, new JList<Object>(arr));
					
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				SolveButton.setIcon(new ImageIcon("images/Solve_Clicked_Icon.png"));
				if (popup != null) {
					popup.hide();
				}
				JLabel text = new JLabel("If you Click in this Button you will lose the Score from this Question ");// + e.getPoint());
				popup = PopupFactory.getSharedInstance().getPopup(
						arg0.getComponent(), text, arg0.getXOnScreen(),
						arg0.getYOnScreen());
				popup.show();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				SolveButton.setIcon(new ImageIcon("images/Solve_Icon.png"));
				if (popup != null) {
					popup.hide();
				}
			}
		});
		SolveButton.setIcon(new ImageIcon("images/Solve_Icon.png"));
		SolveButton.setHorizontalAlignment(SwingConstants.CENTER);
		SolveButton.setBounds(152, 102, 156, 72);
		MediumFrame.getContentPane().add(SolveButton);
		
		saveQuestion = new JLabel("Save This Question");
		saveQuestion.setForeground(UIManager.getColor("Tree.selectionBackground"));
		saveQuestion.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		saveQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SaveQuestion(uname);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				saveQuestion.setIcon(new ImageIcon("images/saveQuestionClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				saveQuestion.setIcon(new ImageIcon("images/saveQuestion.png"));
			}
		});
		saveQuestion.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\saveQuestion.png"));
		saveQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		saveQuestion.setBounds(1061, 94, 268, 80);
		MediumFrame.getContentPane().add(saveQuestion);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("images/Background.jpg"));
		lblBackground.setBounds(0, 29, 1366, 749);
		MediumFrame.getContentPane().add(lblBackground);
		// generateRandom();
		// checkAnswer();

	}

	public void checkAnswer() {
		
		//solve(_operator,_operator2,answerNumber);
		
		number1Value = Number1.getText();
		number2Value = Number2.getText();
		number3Value = Number3.getText();

			try {
				n1 = Integer.parseInt(number1Value);
				n2 = Integer.parseInt(number2Value);
				n3 = Integer.parseInt(number3Value);
			} catch (NumberFormatException e) {
				return;
			}
		
		int temp;
		if (n1 < n2 && op == 1) {
			JOptionPane.showMessageDialog(null,
					"Attention you Need a lower Number then the First one !");
			temp = n1;
			n1 = n2;
			Number1.setText("" + n1);
			n2 = temp;
			Number2.setText("" + n2);
		}

		int temp2;
		if (n2 < n3) {
			temp2 = n2;
			n2 = n3;
			n3 = temp2;
		}

		switch (op) {
			case 0 :
				Result1 = (double) Math.ceil(n1 + n2);
				// System.out.println("+1 = "+Result1);
				break;
			case 1 :
				Result1 = (double) Math.ceil(n1 - n2);
				// System.out.println("-1 = "+Result1);
				break;
			case 2 :
				Result1 = (double) Math.ceil(n1 * n2);
				// System.out.println("x1 = "+Result1);
				break;
			case 3 :
				Result1 = (double) Math.ceil(n1 / n2);
				// System.out.println("/ = "+Result1);
				break;
		}
		switch (op2) {
			case 0 :
				Result2 = (double) Math.ceil(Result1 + n3);
				// System.out.println("+2 = "+Result1);
				break;
			case 1 :
				Result2 = (double) Math.ceil(Result1 - n3);
				// System.out.println("-2 = "+Result1);
				break;
			case 2 :
				Result2 = (double) Math.ceil(Result1 * n3);
				// System.out.println("x2 = "+Result1);
				break;
			case 3 :
				Result2 = (double) Math.ceil(Result1 / n3);
				// System.out.println("/2 = "+Result1);
				break;
		}

		//System.out.println("result = " + Result2);
				if (answerNumber == Result2) {
					timer.cancel();
					JOptionPane.showMessageDialog(null, "Well done!");
					level++;
					medModeScore+= 10;
					setUpdatedScore(uname); // update the score in DB
					setMedModeLvl(uname,level); // update the level in DB
					StartTime = OrginalStarting;
					Number1.setText("");
					Number2.setText("");
					Number3.setText("");
					startTimer();
					generateRandom();
				} else {
					timer.cancel();
					JOptionPane.showMessageDialog(null, "Wrong!");
					Number1.setText("");
					Number2.setText("");
					Number3.setText("");
					lifeCounter--; // lose 1 health
					setHealth(uname);
					StartTime = OrginalStarting;
					startTimer();
					generateRandom();
				}	

		Number1.setText("");
		Number2.setText("");
		Number3.setText("");
		history.clear();
		
	}

	private void undo() {
		if (history.size() > 0) {
			JLabel label = history.get(0);
			label.setText("");
			history.remove(0);
		}
	}
	
	public void solve(char op, char op2, int Answer){
		
		arr = new String[10]; 
		for(int x = 1 ; x <= 9; x++){
			for(int y = 1 ; y <=9 ; y++){
				for(int z = 1 ; z <=9 ; z++){
					if(op == '+' && op2 == '+'){
						if(Answer ==(Math.ceil(x + y + z)))
							arr[x] = ""+x+"+"+y+"+"+z+"="+Answer;
						//	System.out.println(""+x+"+"+y+"+"+z+"="+Answer);;
					}else if(op == '-' && op2 == '-'){
						if(Answer ==(Math.ceil(x - y - z)))
							arr[x] = ""+x+"-"+y+"-"+z+"="+Answer;
							//System.out.println(""+x+"-"+y+"-"+z+"="+Answer);
					}else if(op == 'X' && op2 == 'X'){
						if(Answer ==(Math.ceil(x * y * z)))
							arr[x] = ""+x+"X"+y+"X"+z+"="+Answer;
							//System.out.println(""+x+"X"+y+"X"+z+"="+Answer);
					}else if(op == '/' && op2 == '/'){
						if(Answer ==(Math.ceil(x / y / z)))
							arr[x] = ""+x+"/"+y+"/"+z+"="+Answer;
							//System.out.println(""+x+"/"+y+"/"+z+"="+Answer);
					}else if(op == '+' && op2 == '-'){
						if(Answer ==(Math.ceil(x + y - z)))
							arr[x] = ""+x+"+"+y+"-"+z+"="+Answer;
							//System.out.println(""+x+"+"+y+"-"+z+"="+Answer);
					}else if(op == '+' && op2 == 'X'){
						if(Answer ==(Math.ceil(x + y * z)))
							arr[x] = ""+x+"+"+y+"X"+z+"="+Answer;
							//System.out.println(""+x+"+"+y+"X"+z+"="+Answer);
					}else if(op == '+' && op2 == '/'){
						if(Answer ==(Math.ceil(x + y / z)))
							arr[x] = ""+x+"+"+y+"/"+z+"="+Answer;
							//System.out.println(""+x+"+"+y+"/"+z+"="+Answer);
					}else if(op == '-' && op2 == '+'){
						if(Answer ==(Math.ceil(x - y + z)))
							arr[x] = ""+x+"-"+y+"+"+z+"="+Answer;
							//System.out.println(""+x+"-"+y+"+"+z+"="+Answer);
					}else if(op == '-' && op2 == 'X'){
						if(Answer ==(Math.ceil(x - y * z)))
							arr[x] = ""+x+"-"+y+"X"+z+"="+Answer;
							//System.out.println(""+x+"-"+y+"X"+z+"="+Answer);
					}else if(op == '-' && op2 == '/'){
						if(Answer ==(Math.ceil(x - y / z)))
							arr[x] = ""+x+"-"+y+"/"+z+"="+Answer;
							//System.out.println(""+x+"-"+y+"/"+z+"="+Answer);
					}else if(op == 'X' && op2 == '+'){
						if(Answer ==(Math.ceil(x * y + z)))
							arr[x] = ""+x+"X"+y+"+"+z+"="+Answer;
							//System.out.println(""+x+"X"+y+"+"+z+"="+Answer);
					}else if(op == 'X' && op2 == '-'){
						if(Answer ==(Math.ceil(x * y - z)))
							arr[x] = ""+x+"X"+y+"-"+z+"="+Answer;
							//System.out.println(""+x+"X"+y+"-"+z+"="+Answer);
					}else if(op == 'X' && op2 == '/'){
						if(Answer ==(Math.ceil(x * y / z)))
							arr[x] = ""+x+"X"+y+"/"+z+"="+Answer;
							//System.out.println(""+x+"X"+y+"/"+z+"="+Answer);
					}else if(op == '/' && op2 == '+'){
						if(Answer ==(Math.ceil(x / y + z)))
							arr[x] = ""+x+"/"+y+"+"+z+"="+Answer;
							//System.out.println(""+x+"/"+y+"+"+z+"="+Answer);
					}else if(op == '/' && op2 == '-'){
						if(Answer ==(Math.ceil(x / y - z)))
							arr[x] = ""+x+"/"+y+"-"+z+"="+Answer;
							//System.out.println(""+x+"/"+y+"-"+z+"="+Answer);
					}else if(op == '/' && op2 == 'X'){
						if(Answer ==(Math.ceil(x / y * z)))
							arr[x] = ""+x+"/"+y+"X"+z+"="+Answer;
							//System.out.println(""+x+"/"+y+"X"+z+"="+Answer);
					}
				}
			}
		}
	}
}
