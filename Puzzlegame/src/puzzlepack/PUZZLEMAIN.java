package puzzlepack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class PUZZLEMAIN extends JFrame {

		private JPanel panel;
		private BufferedImage source;
		private BufferedImage resized;    
		private Image image;
		private MyButton lastButton;
		private int width, height;    
		
		private List<MyButton> buttons;
		private List<Point> solution;

		private final int NUMBER_OF_BUTTONS = 12;
		private final int DESIRED_WIDTH = NUMBER_OF_BUTTONS*50; //  scale the image to have the desired width
		
		private String filename = null;

    public PUZZLEMAIN(String fn) {
    	this.filename = fn;
        initUI();
        this.setVisible(true);
    }
    

    private void initUI() {

        solution = new ArrayList<>(); // array list stores the correct order of buttons which forms the image. 
        
        /***  This only works for 12 buttons! ***/
        solution.add(new Point(0, 0)); // Each button is identified by one Point.
        solution.add(new Point(0, 1));
        solution.add(new Point(0, 2));
        solution.add(new Point(1, 0));
        
        solution.add(new Point(1, 1));
        solution.add(new Point(1, 2));
        solution.add(new Point(2, 0));
        solution.add(new Point(2, 1));
        
        solution.add(new Point(2, 2));
        solution.add(new Point(3, 0));
        solution.add(new Point(3, 1));
        solution.add(new Point(3, 2));

        buttons = new ArrayList<>();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        panel.setLayout(new GridLayout(4, 3, 0, 0));	/***  This only works for 12 buttons! ***/

        try {
            source = loadImage();
            int h = getNewHeight(source.getWidth(), source.getHeight());
            resized = resizeImage(source, DESIRED_WIDTH, h, BufferedImage.TYPE_INT_ARGB);
        } catch (IOException ex) {
            Logger.getLogger(PUZZLEMAIN.class.getName()).log(Level.SEVERE, null, ex);
        }

        width = resized.getWidth(null);
        height = resized.getHeight(null);

        getContentPane().add(panel, BorderLayout.CENTER);

        /***  This only works for 12 buttons! ***/
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
            	
                image = createImage(new FilteredImageSource(resized.getSource(),
                        new CropImageFilter(j * width / 3, i * height / 4, // cut a rectangular shape from the already resized image source.
                                (width / 3), height / 4)));
                
                MyButton button = new MyButton(image);
                button.putClientProperty("position", new Point(i, j));

                if (i == 3 && j == 2) {
                    lastButton = new MyButton();
                    lastButton.setBorderPainted(false);
                    lastButton.setContentAreaFilled(false);
                    lastButton.setLastButton();
                    lastButton.putClientProperty("position", new Point(i, j));
                } else {
                    buttons.add(button);
                }
            }
        }

        Collections.shuffle(buttons); // randomly reorder the elements of the buttons list
        buttons.add(lastButton); // the button with no image, is inserted at the end of the list,always goes at the end.

        for (int i = 0; i < NUMBER_OF_BUTTONS; i++) {
  // All  components from the buttons list are placed on the panel. We create  gray border around the buttons and add a click action listener.
            MyButton btn = buttons.get(i);
            panel.add(btn);
            btn.setBorder(BorderFactory.createLineBorder(Color.gray));
            btn.addActionListener(new ClickAction());
        }

        pack();
        setTitle("Puzzle");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private int getNewHeight(int w, int h) { 
    	// method calculates the height of the image based on the desired width,We scale the image using these values.
        double ratio = DESIRED_WIDTH / (double) w;
        int newHeight = (int) (h * ratio);
        return newHeight;
    }

    private BufferedImage loadImage() throws IOException {

        BufferedImage bimg = ImageIO.read(new File(filename));

        return bimg;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width,
            int height, int type) throws IOException {

        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    private class ClickAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            checkButton(e);
            checkSolution();
        }

        private void checkButton(ActionEvent e) {

            int lidx = 0;
            
            for (MyButton button : buttons) {
                if (button.isLastButton()) {
                    lidx = buttons.indexOf(button);
                }
            }

            JButton button = (JButton) e.getSource();
            int bidx = buttons.indexOf(button);

            if ((bidx - 1 == lidx) || (bidx + 1 == lidx)
                    || (bidx - 3 == lidx) || (bidx + 3 == lidx)) {
                Collections.swap(buttons, bidx, lidx);
                updateButtons();
            }
        }

        private void updateButtons() { // method maps the list to the panel's grid

            panel.removeAll(); // all components are removed 

            for (JComponent btn : buttons) { //loop to go through the buttons list to add the reordered buttons back to the panel's layout manager.
                panel.add(btn);
            }

            panel.validate(); // method implements the new layout.
        }
    }

    private void checkSolution() {
 // comparing the list of points of the correctly ordered buttons with the current list containing the order of buttons from the window
        List<Point> current = new ArrayList<>();

        for (JComponent btn : buttons) {
            current.add((Point) btn.getClientProperty("position"));
        }

        if (compareList(solution, current)) {
            JOptionPane.showMessageDialog(panel, "Finished", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static boolean compareList(List<Point> ls1, List<Point> ls2) {        
        return ls1.toString().contentEquals(ls2.toString());
    }
    
}

class MyButton extends JButton {

    private boolean isLastButton;

    public MyButton() {
        super();
        initUI();
    }

    public MyButton(Image image) {

        super(new ImageIcon(image));

        initUI();
    }

    private void initUI() {
        isLastButton = false;
        BorderFactory.createLineBorder(Color.gray);

        addMouseListener(new MouseAdapter() { // When we hover a mouse pointer over the button, the border changes to blue color.
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.blue));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.gray));
            }
            
        });
        
    }

    public void setLastButton() {        
        isLastButton = true;
    }

    public boolean isLastButton() { // button that does not have an image. Other buttons swap space with this one.
        return isLastButton;
    }
    
}