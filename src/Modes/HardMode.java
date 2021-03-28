package Modes;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HardMode extends JFrame {

	/**
	 * Default Serial Version Number
	 */
	private static final long serialVersionUID = 1L;
	private JFrame HardFrame;

	/**
	 * Launch the application.
	 */
	public void HardNewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HardMode frame = new HardMode();
					frame.HardFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HardMode() {
		HardFrame = new JFrame();
		HardFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		HardFrame.setResizable(false);
		HardFrame.setType(Type.POPUP);
		HardFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Najbe\\Desktop\\Brain Trainer FInal Project\\images\\mathit.png"));
		HardFrame.setTitle("Brain Trainer");
		HardFrame.setBounds(100, 100, 768, 576);
		HardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HardFrame.getContentPane().setLayout(null);
	
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("C:\\Users\\Najbe\\Desktop\\Brain Trainer FInal Project\\images\\Background.jpg"));
		lblBackground.setBounds(0, 0, 768, 576);
		HardFrame.getContentPane().add(lblBackground);
	}

}
