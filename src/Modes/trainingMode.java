package Modes;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import Training.div;
import Training.multi;

import com.Game.main.GameWindow;

import dialogs.FirstQuizDialog;

public class trainingMode extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	public String uname;
	public String fname;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trainingMode frame = new trainingMode(uname, fname);
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
	public trainingMode(String userName, String firstName) {
		uname = userName;
		fname = firstName;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		setLocationRelativeTo(null); // Center form in the screen :)
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel plus = new JLabel("");
		plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Training.plus p = new Training.plus(uname,firstName);
				p.main();
				trainingMode.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				plus.setIcon(new ImageIcon("images/trainingplusClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				plus.setIcon(new ImageIcon("images/trainingplus.png"));
			}
		});
		plus.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\trainingplus.png"));
		plus.setBounds(300, 162, 130, 130);
		mainPanel.add(plus);
		
		JLabel minus = new JLabel("");
		minus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Training.minus m = new Training.minus(userName, firstName);
				m.main();
				trainingMode.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				minus.setIcon(new ImageIcon("images/trainingminusClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				minus.setIcon(new ImageIcon("images/trainingminus.png"));
			}
		});
		minus.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\trainingminus.png"));
		minus.setBounds(633, 160, 130, 130);
		mainPanel.add(minus);
		
		JLabel multi = new JLabel("");
		multi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Training.multi m = new multi(userName, firstName);
				m.main();
				trainingMode.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				multi.setIcon(new ImageIcon("images/trainingmultiClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				multi.setIcon(new ImageIcon("images/trainingmulti.png"));
			}
		});
		multi.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\trainingmulti.png"));
		multi.setBounds(963, 160, 130, 130);
		mainPanel.add(multi);
		
		JLabel div = new JLabel("");
		div.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Training.div d = new div(userName, firstName);
				d.main();
				trainingMode.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				div.setIcon(new ImageIcon("images/trainingdivClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				div.setIcon(new ImageIcon("images/trainingdiv.png"));
			}
		});
		div.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\trainingdiv.png"));
		div.setBounds(622, 433, 130, 130);
		mainPanel.add(div);
		
		JLabel exam = new JLabel("");
		exam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exam.setIcon(new ImageIcon("images/trainingExamClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exam.setIcon(new ImageIcon("images/trainingExam.png"));
			}
		});
		exam.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\trainingExam.png"));
		exam.setBounds(298, 433, 130, 130);
		mainPanel.add(exam);
		
		JLabel makeQuestion = new JLabel("");
		makeQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				makeQuestion.setIcon(new ImageIcon("images/trainingMakequestionClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				makeQuestion.setIcon(new ImageIcon("images/trainingMakequestion.png"));
			}
		});
		makeQuestion.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\trainingMakequestion.png"));
		makeQuestion.setBounds(963, 433, 130, 130);
		mainPanel.add(makeQuestion);
		
		
		JLabel Exit = new JLabel("");
		Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				FirstQuizDialog failFrame = new FirstQuizDialog(5 , uname , fname);
				failFrame.main();
				trainingMode.this.dispose();
				
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
		mainPanel.add(Exit);
		
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\trainingModeBg.jpg"));
		background.setBounds(0, 0, 1366, 768);
		mainPanel.add(background);
	}
}
