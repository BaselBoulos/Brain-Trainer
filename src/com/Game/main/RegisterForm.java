package com.Game.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;

import dialogs.FirstQuizDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

import java.awt.Cursor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterForm {
	
	private JFrame rgf;
	private JTextField textField_LName;
	private JPasswordField passwordField;
	public JTextField textField_FName;
	private JPasswordField passwordField_Apply;
	private JTextField textField_UserName;
	public JDateChooser dateChooser;
	public JTextArea textArea;
	public String fName;
	public JLabel lblFirstName;
	
	
	
	public boolean checkUsername(String username)
    {
		PreparedStatement ps;
		ResultSet rs;
		boolean checkUser = false;
		
		String query = "SELECT * FROM `the_app_users` WHERE `u_uname` =?";
		try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, username);
             rs = ps.executeQuery();
             
             if(rs.next())
             {
            	 	checkUser = true;
             }
             
		}catch(SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		return checkUser;
    }
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public  void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm window = new RegisterForm();
					window.rgf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public RegisterForm() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		rgf = new JFrame();
		rgf.setUndecorated(true); // Hide the toolbar 
		rgf.setBounds(0, 0, 1366, 768);
		rgf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		rgf.getContentPane().setLayout(null);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBounds(0, 0, 1366, 45);
		rgf.getContentPane().add(titlePanel);
		
		JLabel TitleLbl = new JLabel("Register");
		TitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLbl.setForeground(Color.BLUE);
		TitleLbl.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		TitleLbl.setBounds(589, 12, 160, 20);
		titlePanel.add(TitleLbl);
		
		JLabel Lbl_Exit = new JLabel("X");
		Lbl_Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FirstQuizDialog failFrame = new FirstQuizDialog(3,"test","test");
				failFrame.main();
            }

			@Override // Some Style When Mouse Enter in the Label Change color of the label to Red
			public void mouseEntered(MouseEvent arg0) {
				Lbl_Exit.setForeground(Color.RED);
			}
	
			@Override // Getting back the Defult Color (Blue)
			public void mouseExited(MouseEvent arg0) {
				Lbl_Exit.setForeground(Color.BLUE);
			}
		});
		Lbl_Exit.setHorizontalAlignment(SwingConstants.CENTER);
		Lbl_Exit.setForeground(Color.BLUE);
		Lbl_Exit.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		Lbl_Exit.setBounds(1338, 11, 22, 22);
		titlePanel.add(Lbl_Exit);
		
		JLabel Lbl_Minmize = new JLabel("-");
		Lbl_Minmize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rgf.setState(JFrame.ICONIFIED);
			}
			@Override // Some Style When Mouse Enter in the Label Change color of the label to Red
			public void mouseEntered(MouseEvent arg0) {
				Lbl_Minmize.setForeground(Color.RED);
			}
			
			@Override // Getting back the Defult Color (Blue)
			public void mouseExited(MouseEvent arg0) {
				Lbl_Minmize.setForeground(Color.BLUE);
			}
		});
		Lbl_Minmize.setHorizontalAlignment(SwingConstants.CENTER);
		Lbl_Minmize.setForeground(Color.BLUE);
		Lbl_Minmize.setFont(new Font("Trajan Pro", Font.BOLD, 24));
		Lbl_Minmize.setBounds(1312, 11, 22, 22);
		titlePanel.add(Lbl_Minmize);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 51, 51));
		panel.setBounds(0, 44, 1366, 768);
		rgf.getContentPane().add(panel);
		
		JLabel lblLastName = new JLabel("Last Name :");
		lblLastName.setForeground(SystemColor.activeCaptionBorder);
		lblLastName.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblLastName.setBounds(494, 90, 130, 30);
		panel.add(lblLastName);
		
		JLabel label_Password = new JLabel("Password :");
		label_Password.setForeground(SystemColor.activeCaptionBorder);
		label_Password.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		label_Password.setBounds(494, 190, 115, 30);
		panel.add(label_Password);
		
		textField_LName = new JTextField();
		textField_LName.setForeground(SystemColor.menu);
		textField_LName.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		textField_LName.setColumns(10);
		textField_LName.setBackground(Color.DARK_GRAY);
		textField_LName.setBounds(634, 95, 186, 20);
		panel.add(textField_LName);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(SystemColor.menu);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passwordField.setBackground(Color.DARK_GRAY);
		passwordField.setBounds(634, 195, 186, 20);
		panel.add(passwordField);
		
		JButton RegisterBtn = new JButton("Register");
		RegisterBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fName = textField_FName.getText();
				String lName = textField_LName.getText();
				String Uname = textField_UserName.getText();
				String pass = String.valueOf(passwordField.getPassword());
				String re_pass = String.valueOf(passwordField_Apply.getPassword());
				String bdate = null;
				String address = textArea.getText(); 
					if(fName.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Add A First Name");
					}
					else if(lName.equals(""))
			        {
			            JOptionPane.showMessageDialog(null, "Add A Last Name");
			        }
					else if(Uname.equals(""))
			        {
			            JOptionPane.showMessageDialog(null, "Add A Username");
			        }
					else if(pass.equals(""))
			        {
			            JOptionPane.showMessageDialog(null, "Add A Password");
			        }
			        else if(!pass.equals(re_pass))
			        {
			            JOptionPane.showMessageDialog(null, "Retype The Password Again");
			        }
			        else if(checkUsername(Uname))
			        {
			            JOptionPane.showMessageDialog(null, "This Username Already Exist");
			        }
			        
			        else {
			        	if(dateChooser.getDate() != null)
				        {
				        	SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
							bdate = dataformat.format(dateChooser.getDate());
			        }
			        	
				PreparedStatement ps;
				String query = "INSERT INTO `the_app_users`(`u_fname`, `u_lname`, `u_uname`, `u_pass`, `u_bdate`, `u_address` ,`FirstTimeIngame`,`u_GlobalScore`,`u_easyModeScore`,`u_medModeScore`,`u_hardModeScore` ) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				try {
					ps = MyConnection.getConnection().prepareStatement(query);
					ps.setString(1, fName);
			        ps.setString(2, lName);
			        ps.setString(3, Uname);
			        ps.setString(4, pass);
			        
			        
			        
			        if(bdate != null)
		            {
			        	ps.setString(5, bdate);
		            }else{
		                ps.setNull(5, 0);
		            }
		            	ps.setString(6, address);
		            	//FirstTimeIngame
		            	ps.setString(7, "1");
		            	//GlobalScore
		            	ps.setString(8, "0");
		            	//EasyModeScore
		            	ps.setString(9, "0");
		            	//MediumModeScore
		            	ps.setString(10, "0");
		            	//HardModeScore
		            	ps.setString(11, "0");
		            	
			        if(ps.executeUpdate() > 0)
			        {
			        	JOptionPane.showMessageDialog(null, "New User Added");
			        }
				}catch (SQLException ex){
					Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
				}
			        }		
			}
		});
		
		RegisterBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		RegisterBtn.setHideActionText(true);
		RegisterBtn.setForeground(SystemColor.menu);
		RegisterBtn.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		RegisterBtn.setBackground(new Color(0, 0, 205));
		RegisterBtn.setBounds(706, 525, 112, 38);
		panel.add(RegisterBtn);
		
		JButton CancelBtn = new JButton("Cancel");
		CancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int choose = JOptionPane.showConfirmDialog(null,
                        "Do you really want to Cancel the Registration ?",
                        "Confirm Cancel", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (choose == JOptionPane.YES_OPTION) {
                    //System.exit(0);
                	new NewLoginPage();
                	NewLoginPage.main(null);
                	rgf.dispose();
                } else {
                    System.out.println("do nothing");
                }
			}
		});
		CancelBtn.setForeground(SystemColor.menu);
		CancelBtn.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		CancelBtn.setBackground(Color.RED);
		CancelBtn.setBounds(567, 526, 105, 38);
		panel.add(CancelBtn);
		
		JLabel lblClickHereTo = new JLabel("Click Here to Login");
		lblClickHereTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new NewLoginPage();
				NewLoginPage.main(null);
				rgf.dispose();
			}
		});
		lblClickHereTo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClickHereTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickHereTo.setForeground(SystemColor.menu);
		lblClickHereTo.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		lblClickHereTo.setBounds(561, 583, 258, 32);
		panel.add(lblClickHereTo);
		
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setForeground(SystemColor.activeCaptionBorder);
		lblFirstName.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblFirstName.setBounds(494, 40, 129, 30);
		panel.add(lblFirstName);
		
		textField_FName = new JTextField();
		textField_FName.setForeground(SystemColor.menu);
		textField_FName.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		textField_FName.setColumns(10);
		textField_FName.setBackground(Color.DARK_GRAY);
		textField_FName.setBounds(633, 45, 186, 20);
		panel.add(textField_FName);
		
		JLabel lblRetypePass = new JLabel("Retype Pass:");
		lblRetypePass.setForeground(SystemColor.activeCaptionBorder);
		lblRetypePass.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblRetypePass.setBounds(494, 240, 129, 30);
		panel.add(lblRetypePass);
		
		passwordField_Apply = new JPasswordField();
		passwordField_Apply.setForeground(SystemColor.menu);
		passwordField_Apply.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passwordField_Apply.setBackground(Color.DARK_GRAY);
		passwordField_Apply.setBounds(634, 245, 186, 20);
		panel.add(passwordField_Apply);
		
		JLabel lblBirthday = new JLabel("BirthData :");
		lblBirthday.setForeground(SystemColor.activeCaptionBorder);
		lblBirthday.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblBirthday.setBounds(495, 290, 130, 30);
		panel.add(lblBirthday);
		
		dateChooser = new JDateChooser();
		dateChooser.setBackground(Color.DARK_GRAY);
		dateChooser.setBounds(634, 295, 186, 20);
		panel.add(dateChooser);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setForeground(SystemColor.activeCaptionBorder);
		lblAddress.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblAddress.setBounds(494, 340, 130, 30);
		panel.add(lblAddress);
		
		textArea = new JTextArea();
		textArea.setForeground(SystemColor.menu);
		textArea.setFont(new Font("Mongolian Baiti", Font.PLAIN, 13));
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setBounds(634, 345, 258, 169);
		panel.add(textArea);
		
		JLabel lblUserName = new JLabel("User Name :");
		lblUserName.setForeground(SystemColor.activeCaptionBorder);
		lblUserName.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblUserName.setBounds(494, 140, 130, 30);
		panel.add(lblUserName);
		
		textField_UserName = new JTextField();
		textField_UserName.setForeground(SystemColor.menu);
		textField_UserName.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		textField_UserName.setColumns(10);
		textField_UserName.setBackground(Color.DARK_GRAY);
		textField_UserName.setBounds(634, 145, 186, 20);
		panel.add(textField_UserName);
	}
	}


