package puzzlepack;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.Font;
public class MAINMENU extends JFrame implements ActionListener{
	private JTextField TxtMenubar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MAINMENU();					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MAINMENU() {
		initialize();
		this.setVisible(true);
	}


	private void initialize() {
		
		this.setBounds(100, 100, 450, 300);

		this.setTitle("Puzzle Main menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);		
		panel.setLayout(new GridLayout(2, 2, 6, 6));
		getContentPane().add(panel);
	
		JButton btnface = new JButton("");
		btnface.setIcon(new ImageIcon(new ImageIcon(MAINMENU.class.getResource("Pics/funnyface.jpg")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT))); // Set icon on Jbutton
        btnface.setActionCommand("src/puzzlepack/Pics/funnyface.jpg");
		btnface.addActionListener(this);
		panel.add(btnface);
		
		JButton btnhorse = new JButton("");		
		btnhorse.setIcon(new ImageIcon(new ImageIcon(MAINMENU.class.getResource("Pics/horse.jpg")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		btnhorse.setActionCommand("src/puzzlepack/Pics/horse.jpg");
		btnhorse.addActionListener(this);
		panel.add(btnhorse);
		
		JButton btnkids = new JButton("");		
		btnkids.setIcon(new ImageIcon(new ImageIcon(MAINMENU.class.getResource("Pics/happykids.jpg")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		btnkids.setActionCommand("src/puzzlepack/Pics/happykids.jpg");
		btnkids.addActionListener(this);
		panel.add(btnkids);
		
		JButton btnstick = new JButton("");
		btnstick.setHorizontalAlignment(SwingConstants.CENTER);
		btnstick.setIcon(new ImageIcon(new ImageIcon(MAINMENU.class.getResource("Pics/stick.jpg")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		btnstick.setActionCommand("src/puzzlepack/pics/stick.jpg");
		btnstick.addActionListener(this);
		panel.add(btnstick);
		
		JButton btnmickey = new JButton("");
		btnmickey.setHorizontalAlignment(SwingConstants.CENTER);
		btnmickey.setIcon(new ImageIcon(new ImageIcon(MAINMENU.class.getResource("Pics/mickey.jpg")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		btnmickey.setActionCommand("src/puzzlepack/pics/mickey.jpg");
		btnmickey.addActionListener(this);
		panel.add(btnmickey);
		
		JButton btntubies = new JButton("");
		btntubies.setHorizontalAlignment(SwingConstants.CENTER);
		btntubies.setIcon(new ImageIcon(new ImageIcon(MAINMENU.class.getResource("Pics/teletub.jpg")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		btntubies.setActionCommand("src/puzzlepack/pics/teletub.jpg");
		btntubies.addActionListener(this);
		panel.add(btntubies);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		TxtMenubar = new JTextField();
		TxtMenubar.setFont(new Font("Algerian", Font.ITALIC, 15));
		TxtMenubar.setText("Choose a picture to start!");
		TxtMenubar.setEditable(false);
		TxtMenubar.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(TxtMenubar);
		};
		
	@Override
	public void actionPerformed(ActionEvent e) {
		new PUZZLEMAIN(e.getActionCommand());
		this.setVisible(false);
	}

}