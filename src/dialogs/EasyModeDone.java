package dialogs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

public class EasyModeDone extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static String fname;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EasyModeDone frame = new EasyModeDone(fname);
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
	public EasyModeDone(String firstName) {
		fname = firstName;
		//uname = "Najeeb";
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 700);
		setLocationRelativeTo(null); // Center form in the screen :)
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel champion = new JLabel("");
		champion.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\modeDoneChampion.png"));
		champion.setBounds(200, 30, 100, 100);
		contentPane.add(champion);
		
		JLabel message = new JLabel("");
		message.setForeground(Color.RED);
		message.setVerticalAlignment(SwingConstants.TOP);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		message.setBounds(10, 158, 480, 157);
		message.setText("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp congratulations ! <br/> "
				+ "You have Done Math Easy Mode .<br/>"
				+ "&nbsp Let's Try And Complete the<br/>"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				+ " Math Medium Mode .<br/><br/>"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				+ " Well Done<br/></html>");
		contentPane.add(message);
		
		JLabel Medal = new JLabel("");
		Medal.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\modeDoneMedal.png"));
		Medal.setHorizontalAlignment(SwingConstants.CENTER);
		Medal.setBounds(200, 400, 100, 100);
		contentPane.add(Medal);
		
		JLabel ok = new JLabel("");
		ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EasyModeDone.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				ok.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OKClicked.png"));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ok.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OK.png"));
				
			}
		});
		
		JLabel playerIcon = new JLabel("");
		playerIcon.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\player.png"));
		playerIcon.setVerticalAlignment(SwingConstants.TOP);
		playerIcon.setHorizontalAlignment(SwingConstants.CENTER);
		playerIcon.setBounds(168, 326, 50, 50);
		contentPane.add(playerIcon);
		
		JLabel firstNameLbl = new JLabel(" "+fname);
		firstNameLbl.setHorizontalAlignment(SwingConstants.LEFT);
		firstNameLbl.setForeground(Color.BLUE);
		firstNameLbl.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		firstNameLbl.setBounds(228, 326, 119, 50);
		contentPane.add(firstNameLbl);
		ok.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OK.png"));
		ok.setHorizontalAlignment(SwingConstants.CENTER);
		ok.setBounds(200, 556, 100, 100);
		contentPane.add(ok);
		
		
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\easyModeDoneBackground.jpg"));
		Background.setBounds(0, 0, 500, 700);
		contentPane.add(Background);
	}
}
