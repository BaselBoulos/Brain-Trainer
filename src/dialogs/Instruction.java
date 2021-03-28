package dialogs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Instruction extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Instruction;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instruction frame = new Instruction();
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
	public Instruction() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 700);
		setLocationRelativeTo(null);
		Instruction = new JPanel();
		Instruction.setBackground(Color.LIGHT_GRAY);
		Instruction.setLayout(null);
		setContentPane(Instruction);
		
		JLabel Oklbl = new JLabel("");
		Oklbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Instruction.this.dispose();
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
		Oklbl.setBounds(197, 610, 80, 80);
		Instruction.add(Oklbl);
		
		JLabel LogoLabel = new JLabel("");
		LogoLabel.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\HowToPlayLogo.png"));
		LogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LogoLabel.setBounds(150, 11, 183, 116);
		Instruction.add(LogoLabel);
		
		JLabel BodyLabel = new JLabel("");
		BodyLabel.setText("<html>- You Choose the Way That's You<br/>"
				+ "&nbsp Are Going Train Your Brain.<br/><br/>"
				+ "-In Math Mode You'r Going To Solve<br/>"
				+ "&nbsp a Quick Math Quiz To Discover<br/>"
				+ "&nbsp You'r Brain Skills In Math.<br/><br/>"
				+ "-In Puzzle Mode You'r Going to Drag<br/> "
				+ "&nbsp The Blocks To Constuct The Image.<br/><br/>"
				+ "-In Memory Mode You'r Need to Find<br/>"
				+ "&nbsp All The Same Pictures.<br/><br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ " HAVE FUN ! </html>");

		
		BodyLabel.setHorizontalAlignment(SwingConstants.LEFT);
		BodyLabel.setForeground(Color.BLACK);
		BodyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		BodyLabel.setBounds(50, 150, 365, 438);
		Instruction.add(BodyLabel);
		
		JLabel Background = new JLabel("");
		Background.setHorizontalAlignment(SwingConstants.CENTER);
		Background.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\instructionBackground.jpg"));
		Background.setBounds(0, 0, 500, 700);
		Instruction.add(Background);
	}
}
