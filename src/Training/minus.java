package Training;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Modes.trainingMode;

public class minus extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private static String uname;
	private static String fname;
	private JButton[] AnswerButton;
	private SecureRandom randomClass;
	private int maxLimit = 150;
	private JLabel Number1;
	private JLabel Number2;
	private JLabel Operator;
	private int Result;
	private float[] answers;
	private int answer;
	private JLabel title;
	private JLabel Back;


	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					minus frame = new minus(uname,fname);
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
	public minus(String username, String firstname) {
		uname = username;
		fname = firstname;
		randomClass = new SecureRandom();
		initialize();
		generateRandom();
	}
	public void generateRandom(){
		
		int n1 = randomClass.nextInt(maxLimit )+1; // +1 to avoid zero.
		int n2 = randomClass.nextInt(maxLimit)+1;
		
		int temp;
		if(n1<n2){
			temp=n1;
			n1=n2;
			n2=temp;
		}
		
		Number1.setText(""+(int)+n1); 
        Number2.setText(""+(int)+n2);
        
		/*
		 *  Generate The Random Operators .( * , / , + , -) 
		 */
		int op = randomClass.nextInt(4); 
		char _operator = '+'; // By Default
		op = 1;
		switch (op) {
			case 0 :_operator = '+';
			Result = (int) Math.ceil(n1 + n2);
			break;
			case 1 :_operator = '-';
			Result = (int) Math.ceil(n1 - n2);
			break;
			case 2 :_operator = 'X';
			Result = (int) Math.ceil(n1 * n2);
			break; 
			case 3 :_operator = '/';
			Result = (int) Math.ceil(n1/n2);
			break; 
		}
		Operator.setText(""+_operator);  
		
		int index = randomClass.nextInt(4);  // Picks random box from 4
		 answers[index] = (int)Result; // puts the answer in random box

		 for(int i=0;i < 3; i++){
			 
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

		 for(int i=0; i<4; i++) { 
				AnswerButton[i].setText(String.valueOf((int)answers[i])); // .split("\\.")[0]
				}
		
	}
	public void initialize(){
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		int ybound = 320;
		AnswerButton = new JButton[4];
		answers = new float[4];
		int i;
		for(i=0; i < 4; i++){
			
			AnswerButton[i] = new JButton();
			AnswerButton[i].setOpaque(false); // Makes the Jbutton Transparent
			AnswerButton[i].setHideActionText(true);
			AnswerButton[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			AnswerButton[i].setBackground(new Color(0, 0, 205));
			AnswerButton[i].setForeground(SystemColor.BLACK);
			AnswerButton[i].setFont(new Font("Trajan Pro", Font.PLAIN, 55));
			AnswerButton[i].setBounds(370, ybound, 670, 60);
			ybound+=70;
			contentPane.add(AnswerButton[i]);
			
			final int innerAnswerIndex = new Integer(i);
			AnswerButton[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					answer = Integer.parseInt(AnswerButton[innerAnswerIndex].getText());
					if(answer == Result){
						int choose = JOptionPane.showConfirmDialog(null,
		                         "Correct, "
		                         + "Press Yes to Continue No To Exit",
		                         "Alert", JOptionPane.YES_NO_OPTION,
		                         JOptionPane.INFORMATION_MESSAGE);
						 if (choose == JOptionPane.YES_OPTION){
							 answers = new float[4];
							 generateRandom();
						 }else{ // if user don't want to Contnue ...
							 trainingMode tm = new trainingMode(uname, fname);
							 tm.main();
							 minus.this.dispose();
						 }
						   	
		           	}else{ // if he answer wronge 
		           		JOptionPane.showMessageDialog(null,"Wronge!");
		           		
		           	}
				} 
			});
			
		}	
	
		Number1 = new JLabel();
		Number1.setForeground(new Color(0, 204, 102));
		Number1.setFont(new Font("Ravie", Font.PLAIN, 49));
		Number1.setHorizontalAlignment(SwingConstants.CENTER);
		Number1.setBounds(535, 176, 120, 70);
		contentPane.add(Number1);
		
		Operator = new JLabel("");
		Operator.setHorizontalAlignment(SwingConstants.CENTER);
		Operator.setForeground(new Color(0, 204, 102));
		Operator.setFont(new Font("Ravie", Font.PLAIN, 49));
		Operator.setBounds(654, 176, 100, 70);
		contentPane.add(Operator);
		
		Number2 = new JLabel();
		Number2.setHorizontalAlignment(SwingConstants.CENTER);
		Number2.setForeground(new Color(0, 204, 102));
		Number2.setFont(new Font("Ravie", Font.PLAIN, 49));
		Number2.setBounds(764, 176, 120, 70);
		contentPane.add(Number2);
		
		title = new JLabel("Subtraction");
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Trajan Pro", Font.PLAIN, 70));
		title.setBounds(457, 30, 503, 88);
		contentPane.add(title);
		
		Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				trainingMode tm = new trainingMode(uname, fname);
				tm.main();
				minus.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Back.setIcon(new ImageIcon("images/Back_Clicked_Button.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Back.setIcon(new ImageIcon("images/Back_Button.png"));
			}
		});
		Back.setHorizontalAlignment(SwingConstants.CENTER);
		Back.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Back_Button.png"));
		Back.setBounds(10, 619, 100, 100);
		contentPane.add(Back);
		
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\trainingModeBg.jpg"));
		background.setBounds(0, 0, 1366, 768);
		contentPane.add(background);
	}

}
