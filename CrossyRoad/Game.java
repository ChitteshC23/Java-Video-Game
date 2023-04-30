package CrossyRoad;

import java.awt.*;
import javax.swing.*;

/* File Name: Game
 * Name: Chittesh.C
 * Date: January 5th, 2022
 * Description: Sets up the frame of the game
				Combines the different classes together
 */

@SuppressWarnings("serial")
public class Game extends JFrame {
	
	public Game() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creating components in the MainPanel onto the game screen
        MainPanel chicken = new MainPanel();
        //Adding the components to the game screen 
        add(chicken, BorderLayout.CENTER);
        
        setVisible(true);   
        
    }
    
	public static void main() {
		//Creating and setting the game frame 
		JFrame frame = new Game();
		frame.setTitle("Crossy Road");
	    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    frame.setSize(655,800);
	    frame.setResizable(false);
	    frame.setVisible (true);
	}
	


}
