package pong;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * @author Janick Ruegger
 * @author Sandro Portner
 * @version 1.0
 * 
 */

public class Main{

    public static void main(String[] args) {

        JFrame frame = new JFrame("Pong");
        try {
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("./src/img/background.jpg")))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("./src/img/icon.png").getImage());
        frame.setLayout(new BorderLayout());
        
        View view = new View();
        
        view.setOpaque(false);
        
        frame.add(view, BorderLayout.CENTER);
        
      
        
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}