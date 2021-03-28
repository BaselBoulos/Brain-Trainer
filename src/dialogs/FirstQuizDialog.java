package dialogs;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.Game.main.GameWindow;

public class FirstQuizDialog extends JFrame{ //extends JPanel {

	private static final long serialVersionUID = 1L;
	private int DialogType;
	private String userName;
	private String fName;
	private JLabel iconLbl , messageLbl , Oklbl;
	private JLabel NoLbl;
	private JPanel StandardDialog;
	
	
	
	
	/**
	 * Create the panel.
	 */
	public void main(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstQuizDialog failFrame = new FirstQuizDialog(DialogType, userName, fName);
					failFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public FirstQuizDialog(int type , String username , String firstName) {
		getContentPane().setBackground(Color.DARK_GRAY);
		DialogType = type;
		userName = username;
		fName = firstName;
		
		setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		setUndecorated(true);
		setBounds(100, 100, 700, 200);
		setLocationRelativeTo(null);
		
		StandardDialog = new JPanel();
		StandardDialog.setEnabled(false);
		StandardDialog.setBackground(new Color(128, 128, 128));
		StandardDialog.setBounds(0, 0, 700, 200);
		getContentPane().add(StandardDialog);
		StandardDialog.setLayout(null);
		
		
		iconLbl = new JLabel("");
		iconLbl.setBounds(10, 36, 80, 80);
		StandardDialog.add(iconLbl);
		iconLbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\FailedTask.png"));
		iconLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		Oklbl = new JLabel("");
		Oklbl.setBounds(294, 109, 80, 80);
		StandardDialog.add(Oklbl);
		Oklbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OKClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OK.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
               	FirstQuizDialog.this.dispose();

			}
			
		});
		Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OK.png"));
		Oklbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		messageLbl = new JLabel("");
		messageLbl.setBounds(100, 35, 590, 80);
		StandardDialog.add(messageLbl);
		messageLbl.setForeground(Color.WHITE);
		messageLbl.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 44));
		messageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		NoLbl = new JLabel("");
		NoLbl.setBounds(412, 109, 80, 80);
		StandardDialog.add(NoLbl);
		NoLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				NoLbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\NoClicked.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				NoLbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\No.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
               	FirstQuizDialog.this.dispose();
              

			}
		});
		NoLbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\No.png"));
		NoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		/*
		 * List of Dialogs
		 */
		switch (type) {
			case 1 :
				messageLbl.setText("Do you want to exit the exam ?");
				Oklbl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OKClicked.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OK.png"));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						System.exit(0);
					}
					
				});
				iconLbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\quit.png"));
				break;
			case 2:
				messageLbl.setText("You Have Complete The Exam!");
				iconLbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\CompletedTask.png"));
				break;
			case 3:
				messageLbl.setText("Do you want to exit the Game ?");
				Oklbl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OKClicked.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OK.png"));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						System.exit(0);
					}	
				});
				iconLbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\quit.png"));
				break;
			case 4:
				messageLbl.setText("Really want to switch user ?");
				Oklbl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OKClicked.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OK.png"));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						
					}	
				});
				iconLbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\UserIconDialog.png"));
				break;
			case 5:
				messageLbl.setText("Back To Main Menu ?");
				Oklbl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OKClicked.png"));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						Oklbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\OK.png"));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						GameWindow gw = new GameWindow(username, fName ,false);
						gw.main();
					}	
				});
				iconLbl.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Final Project\\Brain Trainer FInal Project\\images\\quit.png"));
				break;
				
		}
		

	}
	

}
