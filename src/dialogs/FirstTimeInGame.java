package dialogs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class FirstTimeInGame extends JFrame {

	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstTimeInGame frame = new FirstTimeInGame();
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
	public FirstTimeInGame() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel BackToMainMenu = new JLabel("");
		BackToMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FirstTimeInGame.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				BackToMainMenu.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\HomeClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				BackToMainMenu.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Home.png"));
			}
		});
		BackToMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		BackToMainMenu.setFont(new Font("Trajan Pro", Font.PLAIN, 25));
		BackToMainMenu.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\Home.png"));
		BackToMainMenu.setBounds(360, 416, 100, 76);
		mainPanel.add(BackToMainMenu);
		
		JLabel message = new JLabel("<html>Don't Forget To See How to Play!<br/>"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				+ "&nbsp&nbsp&nbsp "
				+ "Hope You Will Enjoy . </html>");
		message.setForeground(new Color(75, 0, 130));
		message.setFont(new Font("Trajan Pro", Font.PLAIN, 22));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBounds(137, 503, 547, 53);
		mainPanel.add(message);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\WelcomePageBackground.jpg"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 800, 600);
		mainPanel.add(lblNewLabel);
	}

}
