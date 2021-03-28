package Levels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dialogs.FirstQuizDialog;
import dialogs.QuizComplete;

import javax.swing.JButton;

import com.Game.main.MainMenu;



import com.Game.main.MyConnection;
import com.Game.main.RegisterForm;

import java.awt.SystemColor;
import java.io.FileNotFoundException;
import java.io.PrintWriter;



public class FirstQuiz extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel FirstPanel;
	
	private int[] questionTypes ;
	private int addType, subType, multiType, divType;
	private double[] questionResult;
	private String[] questionResultStr;
	private double[][]  questionNumber;
	private SecureRandom randomClass;
	private int q1rndOperator = 0;
	private int q2rndOperator = 0;
	private char q1_operator;
	private char q2_operator;
	private char q4_operator;
	private int maxLimit = 10; 
	private int StartTime = 15;
	private int OrginalStarting;
	private Timer timer;
	private JLabel QuestionLabel ,QuestionNumber;
	private JLabel quizTimer;
	private JLabel bonusQuestion;
	private JLabel TestScore;
	private JButton[] AnswerButton;
	public FirstQuizQuestions[] questions;
	private double[] answers;
	private int curQuestion = 1;
	private String username;
	private String firstname; 
	private int score = 0;
	private PrintWriter outPutStream;
	private String[] question;
	
 
	/*
	 * Update DB The User Passed The Exam
	 */
	public void UpdateQuizStat(String Username){
		PreparedStatement ps;
		String query = "UPDATE the_app_users SET FirstQuizDone = ? , QuizScore = ? WHERE u_uname = ?";
		try {
			ps = MyConnection.getConnection().prepareStatement(query);
			ps.setString(1, "1");
			ps.setString(2, ""+score);
			ps.setString(3, "" +Username);
			ps.executeUpdate();
			
		}catch (SQLException ex){
			Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/*
	  * Types Of the Questions :
	  * 1 = +
	  * 2 = -
	  * 3 = x 
	  * 4 = /
	  * 5 = + - 
	  * 6 = + *
	  * 7 = + /
	  * 8 = - *
	  * 9 = - /
	  * 10 = * /
	  * 11 = + - x /
	  */
		 
	
	public void updateSkills(String Username , int type){
		PreparedStatement ps = null;
		String add 		= "UPDATE the_app_users SET addition = ? WHERE u_uname = ?"; // 1
		String sub 		= "UPDATE the_app_users SET subtraction = ? WHERE u_uname = ?"; // 2
		String multi	= "UPDATE the_app_users SET multiplication = ? WHERE u_uname = ?"; // 3
		String div 		= "UPDATE the_app_users SET division = ? WHERE u_uname = ?"; // 4
		String addsub 	= "UPDATE the_app_users SET addition = ? ,subtraction = ? WHERE u_uname = ?"; // 5
		String addmulti = "UPDATE the_app_users SET addition = ? ,multiplication = ? WHERE u_uname = ?"; // 6
		String adddiv 	= "UPDATE the_app_users SET addition = ? ,division = ? WHERE u_uname = ?"; // 7
		String submulti = "UPDATE the_app_users SET subtraction = ? ,multiplication = ? WHERE u_uname = ?"; // 8
		String subdiv 	= "UPDATE the_app_users SET subtraction = ? ,division = ? WHERE u_uname = ?"; // 9
		String multidiv = "UPDATE the_app_users SET multiplication = ? ,  WHERE u_uname = ?"; // 10
		String allOperators = "UPDATE the_app_users SET addition = ?, subtraction = ?, multiplication = ?, division = ?  WHERE u_uname = ?"; // 11
		
		try {
			switch(type){
				case 1:
					ps = MyConnection.getConnection().prepareStatement(add);
					addType+=2;
					ps.setInt(1, +addType);
					ps.setString(2, "" +Username);
					ps.executeUpdate();
					break;
				case 2:
					ps = MyConnection.getConnection().prepareStatement(sub);
					subType+=2;
					ps.setInt(1, +subType);
					ps.setString(2, "" +Username);
					ps.executeUpdate();
					break;
				case 3:
					ps = MyConnection.getConnection().prepareStatement(multi);
					multiType+=2;
					ps.setInt(1, +multiType);
					ps.setString(2, "" +Username);
					ps.executeUpdate();
					break;
				case 4:
					ps = MyConnection.getConnection().prepareStatement(div);
					divType+=2;
					ps.setInt(1, +divType);
					ps.setString(2, "" +Username);
					ps.executeUpdate();
					break;
				case 5:
					ps = MyConnection.getConnection().prepareStatement(addsub);
					addType+=2;
					subType+=2;
					ps.setInt(1, +addType);
					ps.setInt(2, +subType);
					ps.setString(3, "" +Username);
					ps.executeUpdate();
					break;
				case 6:
					ps = MyConnection.getConnection().prepareStatement(addmulti);
					addType+=2;
					multiType+=2;
					ps.setInt(1, +addType);
					ps.setInt(2, +multiType);
					ps.setString(3, "" +Username);
					ps.executeUpdate();
					break;
				case 7:
					ps = MyConnection.getConnection().prepareStatement(adddiv);
					addType+=2;
					divType+=2;
					ps.setInt(1, +addType);
					ps.setInt(2, +divType);
					ps.setString(3, "" +Username);
					ps.executeUpdate();
					break;
				case 8:
					ps = MyConnection.getConnection().prepareStatement(submulti);
					subType+=2;
					multiType+=2;
					ps.setInt(1, +subType);
					ps.setInt(2, +multiType);
					ps.setString(3, "" +Username);
					ps.executeUpdate();
					break;
				case 9:
					ps = MyConnection.getConnection().prepareStatement(subdiv);
					subType+=2;
					divType+=2;
					ps.setInt(1, +subType);
					ps.setInt(2, +divType);
					ps.setString(3, "" +Username);
					ps.executeUpdate();
					break;
				case 10:
					ps = MyConnection.getConnection().prepareStatement(multidiv);
					multiType+=2;
					divType+=2;
					ps.setInt(1, +multiType);
					ps.setInt(2, +divType);
					ps.setString(3, "" +Username);
					ps.executeUpdate();
					break;
				case 11:
					ps = MyConnection.getConnection().prepareStatement(allOperators);
					addType+=2;
					subType+=2;
					multiType+=2;
					divType+=2;
					ps.setInt(1, +addType);
					ps.setInt(2, +subType);
					ps.setInt(3, +multiType);
					ps.setInt(4, +divType);
					ps.setString(5, "" +Username);
					ps.executeUpdate();
					break;
			}
			
		}catch (SQLException ex){
			Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	class NewTask extends TimerTask{
		

		@Override
		public void run() {
				
			if(StartTime>=0){
				quizTimer.setText(StartTime+"");
				StartTime--;
			}
			else{
				timer.cancel();
				StartTime = OrginalStarting;
				curQuestion++;
				questionsMaker();
				timer = new Timer();
				timer.schedule(new NewTask(), 0,1000);
				}
			}
		}
	public void startTimer(){
		timer = new Timer();
		timer.schedule(new NewTask(),0,1000);
	}
	private boolean isFrameVisible(){
		 return true;
	}
	
	/**
	 * Launch the application.
	 */
	public void main() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstQuiz frame = new FirstQuiz(username , firstname);
					frame.setVisible(true);
					frame.isFrameVisible();
					frame.startTimer();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void questionsMaker(){
		
		questionResult = new double[12];
		questionTypes = new int[12];
		questionResultStr = new String[12];
		questionNumber = new double[12][12];
		
		q1rndOperator = 0;
		q2rndOperator = 1;
		
		questionNumber[1][1] = (double)(randomClass.nextInt(maxLimit));
		questionNumber[1][2] = (double)(randomClass.nextInt(maxLimit+90));
		questionNumber[2][1] = (double)(randomClass.nextInt(200-100)+100);
		questionNumber[2][2] = (double)(randomClass.nextInt(200-100)+100);
		questionNumber[3][1] = (double)(randomClass.nextInt(500-100)+100);
		questionNumber[5][1] = (double)(randomClass.nextInt(9)+1);
		questionNumber[6][1] = (double)(randomClass.nextInt(9)+1);
		questionNumber[7][1] = (double)(randomClass.nextInt(9-1)+1);
		questionNumber[7][2] = (double)(randomClass.nextInt(9-1)+1);
		questionNumber[7][3] = (double)(randomClass.nextInt(9-1)+1);
		questionNumber[7][4] = (double)(1);
		questionNumber[8][1] = (double)(randomClass.nextInt(100-60)+60);
		questionNumber[8][2] = (double)(randomClass.nextInt(20-10)+10);
		questionNumber[9][1] = (double)(randomClass.nextInt(10-1)+1);
		questionNumber[9][2] = (double)(randomClass.nextInt(10-2)+2);
		questionNumber[9][3] = (double)(randomClass.nextInt(10-3)+3);
		questionNumber[10][1]= (double)(randomClass.nextInt(6000-5000)+5000);
		questionNumber[10][2]= (double)(randomClass.nextInt(3000-2000)+2000);
		questionNumber[11][1]= (double)(randomClass.nextInt(10-1)+1);
		
		//Question 1
		//Random Operator For Question 1
		switch (q1rndOperator) {
			case 0 :
				questionTypes[1] = 1;
				q1_operator = '+';
				questionResult[1] = (int) Math.ceil(questionNumber[1][1] + questionNumber[1][2]);
				break;
			}
		//Question 2
		//Random Operator for Question 2
		switch (q2rndOperator){
			case 1 :
				questionTypes[2] = 2;
				q2_operator = '-';
				questionResult[2] = (int) Math.ceil(questionNumber[2][1] - questionNumber[2][2]);
				break;
		}
		//Question 3
		questionTypes[3] = 4;
		questionResult[3] = (int)(questionNumber[3][1] * (50.0f /100.0f));
		
		//Question 4
		int rnd = randomClass.nextInt(5);
		questionTypes[4] = 3;
		switch(rnd){
			case 0:
				questionNumber[4][1] = 11;
				q4_operator = 'X';
				questionResult[4] = (int) Math.ceil(questionNumber[4][1] * questionNumber[4][1]);
				break;
			case 1:
				questionNumber[4][1] = 22;
				q4_operator = 'X';
				questionResult[4] = (int) Math.ceil(questionNumber[4][1] * questionNumber[4][1]);
				break;
			case 2:
				questionNumber[4][1] = 33;
				q4_operator = 'X';
				questionResult[4] = (int) Math.ceil(questionNumber[4][1] * questionNumber[4][1]);
				break;
			case 3:
				questionNumber[4][1] = 44;
				q4_operator = 'X';
				questionResult[4] = (int) Math.ceil(questionNumber[4][1] * questionNumber[4][1]);
				break;
			case 4:
				questionNumber[4][1] = 55;
				q4_operator = 'X';
				questionResult[4] = (int) Math.ceil(questionNumber[4][1] * questionNumber[4][1]);
				break;
		}
		
		//Question 5
		//3 + 3 * 3 - 3 + 3 = ?
		questionTypes[5] = 11;
		questionResult[5] = (int) Math.ceil(questionNumber[5][1] * questionNumber[5][1]);
		questionResult[5] += questionNumber[5][1];
		questionResult[5] -= questionNumber[5][1];
		questionResult[5] += questionNumber[5][1];
		
		//Question 6
		questionTypes[6] = 4;
		questionResult[6] = (int) Math.ceil(questionNumber[6][1] / 2.0);
		questionResult[6] += questionNumber[6][1];
		
		//Question 7
		questionTypes[7] = 11;
		questionResult[7]  = (int)Math.ceil(questionNumber[7][2] + questionNumber[7][3]);
		questionResult[7] /= questionNumber[7][4];
		questionResult[7] *= questionNumber[7][1];
		
		//Question 8
		questionTypes[8] = 1;
		questionResult[8] = (int) Math.ceil(questionNumber[8][1] + questionNumber[8][2]);
		
		//Question 9
		questionTypes[9] = 3;
		questionResult[9] = (int) Math.ceil(questionNumber[9][1] * questionNumber[9][2]);
		questionResult[9] *= questionNumber[9][3];
		
		//Question 10
		questionTypes[10] = 2;
		questionResult[10] = (int) Math.ceil(questionNumber[10][1] - questionNumber[10][2]);
		
		//Question 11
		questionTypes[11] = 11;
		questionResult[11] = (int) Math.ceil(questionNumber[11][1] / questionNumber[11][1]);
		questionResult[11] += (int)Math.ceil(questionNumber[11][1] * questionNumber[11][1]);
		questionResult[11] += (int)Math.ceil(questionNumber[11][1]);
		questionResult[11] -= (int)Math.ceil(questionNumber[11][1]);
		
		questionResultStr[1] = String.valueOf((int)questionResult[1]);//Result of Question 1
		questionResultStr[2] = String.valueOf((int)questionResult[2]);//Result of Question 2
		questionResultStr[3] = String.valueOf((int)questionResult[3]);//Result of Question 3
		questionResultStr[4] = String.valueOf((int)questionResult[4]);//Result if Question 4
		questionResultStr[5] = String.valueOf((int)questionResult[5]);//Result if Question 5
		questionResultStr[6] = String.valueOf((int)questionResult[6]);//Result if Question 6
		questionResultStr[7] = String.valueOf((int)questionResult[7]);//Result if Question 7
		questionResultStr[8] = String.valueOf((int)questionResult[8]);//Result if Question 8
		questionResultStr[9] = String.valueOf((int)questionResult[9]);//Result if Question 9
		questionResultStr[10]= String.valueOf((int)questionResult[10]);//Result if Question 10
		questionResultStr[11]= String.valueOf((int)questionResult[11]);//Result if Question 11
		
		question = new String[12];
		question[1] = ""+(int)questionNumber[1][1]+" "+q1_operator+" "+(int)questionNumber[1][2]+" = ?";
		question[2] = ""+(int)questionNumber[2][1]+" "+q2_operator+" "+(int)questionNumber[2][2]+" = ?";
		question[3] = "What is 50% of "+(int)questionNumber[3][1]+" = ?";
		question[4] = ""+(int)questionNumber[4][1]+" "+q4_operator+" "+(int)questionNumber[4][1]+" = ?";
		question[5] = ""+(int)questionNumber[5][1]+" + "+(int)questionNumber[5][1]+" X "+(int)questionNumber[5][1]+" - "+(int)questionNumber[5][1]+" + "+(int)questionNumber[5][1]+" = ?";
		question[6] = "What is half of "+(int)questionNumber[6][1]+" + "+(int)questionNumber[6][1]+" = ?";
		question[7] = ""+(int)questionNumber[7][1]+"X ( ("+(int)questionNumber[7][2]+" + "+(int)questionNumber[7][3]+") / 1 ) = ?";
		question[8] = "What is The Missing Number : ? - "+(int)questionNumber[8][1]+" = "+(int)questionNumber[8][2];
		question[9] = ""+(int)questionNumber[9][1]+" X "+(int)questionNumber[9][2]+" X "+(int)questionNumber[9][3]+" = ?";
		question[10]= ""+(int)questionNumber[10][1]+" - "+(int)questionNumber[10][2]+" = ?";
		question[11]= ""+(int)questionNumber[11][1]+" + "+(int)questionNumber[11][1]+" / "+ (int)questionNumber[11][1]+" + "+(int)questionNumber[11][1]+" X "+(int)questionNumber[11][1]+" - "+(int)questionNumber[11][1]+" = ?";
		
		
		questions = new FirstQuizQuestions[12];
		questions[1] =new FirstQuizQuestions(question[1], questionResultStr[1] , questionTypes[1]); // Question 1
		questions[2] =new FirstQuizQuestions(question[2], questionResultStr[2] , questionTypes[2]); // Question 2
		questions[3] =new FirstQuizQuestions(question[3], questionResultStr[3] , questionTypes[3]); // Question 3
		questions[4] =new FirstQuizQuestions(question[4], questionResultStr[4] , questionTypes[4]); // Question 4
		questions[5] =new FirstQuizQuestions(question[5], questionResultStr[5] , questionTypes[5]); // Question 5
		questions[6] =new FirstQuizQuestions(question[6], questionResultStr[6] , questionTypes[6]); // Question 6
		questions[7] =new FirstQuizQuestions(question[7], questionResultStr[7] , questionTypes[7]); // Question 7
		questions[8] =new FirstQuizQuestions(question[8], questionResultStr[8] , questionTypes[8]); // Question 8
		questions[9] =new FirstQuizQuestions(question[9], questionResultStr[9] , questionTypes[9]); // Question 9
		questions[10]=new FirstQuizQuestions(question[10], questionResultStr[10] , questionTypes[10]); // Question 10
		questions[11]=new FirstQuizQuestions(question[11], questionResultStr[11] , questionTypes[11]); // Question 11
		
		
		/*
		 * Writing All the Exam to Txt FIle :
		 */
		String fileName = "Exam.txt";
		try {
			outPutStream = new PrintWriter("C://Users//Najbe//Desktop//"+fileName);
			for(int i = 1 ; i < questions.length ; i++){
				outPutStream.println(question[i]);
			}
			outPutStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		/*
		 * =============================================================
		 */
		if(curQuestion <= questions.length){
			RandomAnswersMaker();
			takeTest();
		}else
			FirstQuiz.this.dispose();
		
	}

	public void RandomAnswersMaker(){
		if(curQuestion <= questions.length){
			
			 int i ;
			 int AnswerIndex = 0;
			 answers = new double[4];
			 AnswerIndex = randomClass.nextInt(4);
			 AnswerButton[AnswerIndex].setText(questions[curQuestion].answer);
			 for(i=0;i < 3; i++){
				 
				 int MaxRandom = (int) Double.parseDouble(questions[curQuestion].answer); 
				 int MinRandom = MaxRandom -10;
				 int RandomNums = (randomClass.nextInt(MaxRandom - MinRandom)+MinRandom);  // Random answer between 0-Result+10
				boolean inserted = false;
				do{
					AnswerIndex = randomClass.nextInt(4);
					if(answers[AnswerIndex]== 0)	{   				
						answers[AnswerIndex]=RandomNums; 
						if(RandomNums==MaxRandom) // Avoid getting results in more than 1 answer box.
							//AnswerButton[]
						answers[AnswerIndex]=randomClass.nextInt(4);
						inserted = true;
						}
			   }while(!inserted);
			
			}
			 for(i=0; i<4; i++) {
					AnswerButton[i].setText(String.valueOf((int)answers[i]));
					if(answers[i] == 0 )
						AnswerButton[i].setText(questions[curQuestion].answer);
					}
		}else { return; }
	}
	
	public void takeTest(){
		TestScore.setText("Score: "+score+"/100");
		if(curQuestion <= questions.length){
			QuestionLabel.setText(questions[curQuestion].prompt);
			QuestionNumber.setText(String.valueOf((int)curQuestion));
			if(curQuestion == 11) bonusQuestion.setVisible(true);
		}
	}	
	

	/**
	 * Create the frame.
	 */
	
	public FirstQuiz(String Username , String Firstname ) {
		username = Username;
		firstname = Firstname;
		OrginalStarting = StartTime;
		randomClass = new SecureRandom();
		FirstQuiz.this.setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		FirstPanel = new JPanel();
		FirstPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(FirstPanel);
		FirstPanel.setLayout(null);
		
		JPanel QuestionNumPanel = new JPanel();
		QuestionNumPanel.setBackground(Color.DARK_GRAY);
		QuestionNumPanel.setBounds(259, 91, 137, 74);
		FirstPanel.add(QuestionNumPanel);
		QuestionNumPanel.setLayout(null);
		
		int ybound = 310;
		AnswerButton = new JButton[4];
		for(int i=0; i < 4; i++){
			
			AnswerButton[i] = new JButton();
			AnswerButton[i].setOpaque(false); // Makes the Jbutton Transparent
			//AnswerButton[i].setContentAreaFilled(false);
			//AnswerButton[i].setBorderPainted(false);
			AnswerButton[i].setHideActionText(true);
			AnswerButton[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			AnswerButton[i].setBackground(new Color(0, 0, 205));
			AnswerButton[i].setForeground(SystemColor.BLACK);
			AnswerButton[i].setFont(new Font("Trajan Pro", Font.PLAIN, 55));
			AnswerButton[i].setBounds(320, ybound, 670, 60);
			ybound+=70;
			FirstPanel.add(AnswerButton[i]);
			final int innerAnswerIndex = new Integer(i);
			AnswerButton[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(AnswerButton[innerAnswerIndex].getText().equals(questions[curQuestion].answer)){
						if(curQuestion <= questions.length){
							updateSkills(username, questions[curQuestion].type);
							score+=10;
							curQuestion++;
							timer.cancel();
							timer = new Timer();
		                	timer.schedule(new NewTask(), 0,1000);
		                	StartTime = OrginalStarting;
		                	if(curQuestion != 12){
		                		RandomAnswersMaker();
		                		takeTest();
		                	}
		                	// Quiz Finished
		                	else{
		                		if(score >= 50)
		                			UpdateQuizStat(username); // Update The Quiz Stats (Done?)
		                		FirstQuiz.this.dispose();		       
								MainMenu qz = new MainMenu(username, firstname);
								qz.main();
								QuizComplete qc = new QuizComplete(score);
		                		qc.main();
		                	}
						}
					}
					/*
					 * If the User Answer Wronge
					 */
					else{ 
						curQuestion++;
						timer.cancel();
						timer = new Timer();
						timer.schedule(new NewTask(), 0,1000);
	                	StartTime = OrginalStarting;
	                	if(curQuestion != 12){
	                		RandomAnswersMaker();
	                		takeTest();
	                	}else{ // after Complete the quiz
	                		if(score >= 50)
		                		UpdateQuizStat(username); // Update The Quiz Stats (Done?)s
	                		FirstQuiz.this.dispose();		       
							MainMenu qz = new MainMenu(username, firstname);
							qz.main();
							QuizComplete qc = new QuizComplete(score);
	                		qc.main();
	                	}
	                	
					}
				}
			});		
		}
		QuestionNumber = new JLabel("0");
		QuestionNumber.setForeground(Color.WHITE);
		QuestionNumber.setFont(new Font("Tekton Pro", Font.BOLD, 40));
		QuestionNumber.setHorizontalAlignment(SwingConstants.CENTER);
		QuestionNumber.setBounds(10, 9, 117, 58);
		QuestionNumPanel.add(QuestionNumber);
		
		JPanel QuestionPanel = new JPanel();
		QuestionPanel.setBackground(Color.DARK_GRAY);
		QuestionPanel.setBounds(406, 91, 722, 74);
		FirstPanel.add(QuestionPanel);
		QuestionPanel.setLayout(null);
		
		QuestionLabel = new JLabel("");
		QuestionLabel.setForeground(Color.WHITE);
		QuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		QuestionLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 40));
		QuestionLabel.setBounds(10, 16, 702, 52);
		QuestionPanel.add(QuestionLabel);
		
		JLabel Exit = new JLabel("");
		Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FirstQuizDialog failFrame = new FirstQuizDialog(1 , username,firstname);
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
		Exit.setIcon(new ImageIcon("images/Exit.png"));
		Exit.setBounds(1300, 0, 80, 80);
		FirstPanel.add(Exit);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				QuestionNumber.setText(questions[curQuestion].answer);
			}
		});
		label.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\9.png"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(36, 135, 149, 125);
		FirstPanel.add(label);
		
		quizTimer = new JLabel(""+StartTime);
		quizTimer.setForeground(Color.RED);
		quizTimer.setFont(new Font("Trajan Pro", Font.PLAIN, 40));
		quizTimer.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\QuizTimer.png"));
		quizTimer.setHorizontalAlignment(SwingConstants.CENTER);
		quizTimer.setBounds(62, 407, 155, 88);
		FirstPanel.add(quizTimer);
		
		bonusQuestion = new JLabel("<html>&nbsp&nbsp&nbsp&nbsp&nbsp Bonus<br/> &nbsp&nbsp Question</html>");
		bonusQuestion.setForeground(Color.RED);
		bonusQuestion.setVisible(false);
		
		TestScore = new JLabel("");
		TestScore.setForeground(Color.RED);
		TestScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TestScore.setBounds(62, 271, 149, 38);
		FirstPanel.add(TestScore);
		bonusQuestion.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		bonusQuestion.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\BonusQuestion.png"));
		bonusQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		bonusQuestion.setBounds(1122, 77, 234, 88);
		FirstPanel.add(bonusQuestion);
				
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\FirstQuizBackground.jpg"));
		Background.setBounds(0, 0, 1366, 768);
		FirstPanel.add(Background);
		
		questionsMaker();
	}
}