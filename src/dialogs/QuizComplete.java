package dialogs;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class QuizComplete extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel gratzJPanel;
	private JLabel BodyLabel;
	private JLabel FailMessage;
	private JLabel LogoLabel;
	private int score;


	/**
	 * Launch the application.
	 */
	public  void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					QuizComplete frame = new QuizComplete(score);
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
	public QuizComplete(int score) {
		this.score = score;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		gratzJPanel = new JPanel();
		gratzJPanel.setBackground(Color.LIGHT_GRAY);
		gratzJPanel.setLayout(null);
		setContentPane(gratzJPanel);
		
		
		JLabel Oklbl = new JLabel("");
		Oklbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				QuizComplete.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OKClicked.png"));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OK.png"));
				
			}
		});
		Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OK.png"));
		Oklbl.setHorizontalAlignment(SwingConstants.CENTER);
		Oklbl.setBounds(210, 409, 80, 80);
		gratzJPanel.add(Oklbl);
		
		LogoLabel = new JLabel("congratulations");
		LogoLabel.setForeground(Color.WHITE);
		LogoLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 15));
		LogoLabel.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\quizResultLogo.png"));
		LogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LogoLabel.setBounds(113, 11, 254, 116);
		gratzJPanel.add(LogoLabel);
		
		FailMessage = new JLabel("");
		if(score <= 50) { 
			FailMessage.setText("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ " You Fail In This Exam ...<br/>"
					+ " &nbsp&nbsp&nbsp&nbsp"
					+ " You Should Pass It To Enter <br/>"
					+ " &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ " Math Game Modes<br/>"
					+ " We Have Saved The Exam Questions <br/>"
					+ " &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ " In Desktop <br/>"
					+ " You Can Try And Solve This Exam <br/>"
					+ " &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ " For Training .</html>");
			LogoLabel.setText("");
			LogoLabel.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\quizResultLogo_Fail.png"));;
		}else{
			FailMessage.setForeground(Color.GREEN);
			FailMessage.setText("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				+ " You Pass In This Exam ...<br/>"
				+ " You Can Access The Math Game Modes <br/></html>");
		}
		FailMessage.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		FailMessage.setForeground(Color.RED);
		FailMessage.setHorizontalAlignment(SwingConstants.CENTER);
		FailMessage.setBounds(10, 138, 479, 207);
		gratzJPanel.add(FailMessage);
		
		BodyLabel = new JLabel("");
		BodyLabel.setText("Score : "+score+"/100");
		BodyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BodyLabel.setForeground(Color.ORANGE);
		BodyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		BodyLabel.setBounds(70, 356, 365, 42);
		gratzJPanel.add(BodyLabel);
		
		JLabel Background = new JLabel("");
		Background.setHorizontalAlignment(SwingConstants.CENTER);
		Background.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\instructionBackground.jpg"));
		Background.setBounds(0, 0, 500, 500);
		gratzJPanel.add(Background);
	}
}
