package com.Game.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class OldLoginPage extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UserField;
	private JTextField PasswordField;

	
	/**
	 * Launch the application.
	 */
	public  void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OldLoginPage frame = new OldLoginPage();
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
	public OldLoginPage() {
		this.setTitle("Brain Trainer");
		setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Najbe\\Desktop\\Brain Trainer FInal Project\\images\\mathit.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		UserField = new JTextField();
		UserField.setBounds(303, 108, 187, 36);
		contentPane.add(UserField);
		UserField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("SANS_SERIF", Font.BOLD, 25));
		lblUsername.setBorder(null);
		lblUsername.setBounds(145, 93, 187, 62);
		contentPane.add(lblUsername);
		
		JButton LoginBtn = new JButton("Login");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/braintrainer", "root", "");
					System.out.println("connected");
					Statement stmt =con.createStatement();
					String sql = "Select * FROM login where username='"+UserField.getText()+"'and password= '"+PasswordField.getText().toString()+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()){
						JOptionPane.showMessageDialog(null, "WELCOME "+UserField.getText()+"");
						GameWindow gm = new GameWindow();
						dispose(); // Kill the Cur' JFrame
						gm.main(); // Call Main Window Frame
					}
					else	
						JOptionPane.showMessageDialog(null, "Incorrect Username or Password ,If You are a new user please click register.!");
					con.close();
				} catch(Exception e ){System.out.print(e);}
			}
		});
		LoginBtn.setBounds(213, 254, 119, 51);
		contentPane.add(LoginBtn);
		
		JLabel Passwordlbl = new JLabel("Password:");
		Passwordlbl.setHorizontalAlignment(SwingConstants.CENTER);
		Passwordlbl.setFont(new Font("SANS_SERIF", Font.BOLD, 25));
		Passwordlbl.setBounds(145, 166, 187, 51);
		contentPane.add(Passwordlbl);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(303, 175, 187, 36);
		contentPane.add(PasswordField);
		PasswordField.setColumns(10);
		
		
		
		//Register
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					//יצירת מופע של המנוע
					Class.forName("com.mysql.jdbc.Driver");
					//פתיחת החיבור לבסיס הנתונים
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/braintrainer", "root","" );
					//יצירת אובייקט לשמירת תוצאות שאילתה
					java.sql.Statement stmt = con.createStatement();
					//הרצת השאילתה והכנסת תוצאות שלה לאובייקט
					String acc = UserField.getText().toString();
					String pass = PasswordField.getText().toString();
					JOptionPane.showMessageDialog(null, "Connect Done!");
					if(PasswordField.getText().toString() != null)
					{
						String sql = "insert into login values('"+acc+"','"+pass+"')";
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "register Successfully...");
					}
					else
						JOptionPane.showMessageDialog(null, "register faild...");
						con.close();
				} catch (Exception e2) {
					System.out.print(e2);
				}
			}
		});		
		btnRegister.setBounds(385, 254, 119, 51);
		contentPane.add(btnRegister);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("images/Background.jpg"));
		lblBackground.setBounds(0, 0, 768, 576);
		this.getContentPane().add(lblBackground);
		
		
	}
}
