package CrossyRoad;

import java.awt.*; 
import javax.swing.*;

import CrossyRoad.Menu;

import java.util.*;
import java.awt.event.*;

/* File Name: GameOver
 * Name: Chittesh.C
 * Date: January 9th, 2022 
 * Description: Displays the game over screen 
				Creates a game-over display when a collision occurs between the obstacles and the character from the MainPanel class
				Creates a button to allow the player to restart the game
 */

public class GameOver extends JFrame implements ActionListener {
	
	//Restart button image 
	ImageIcon restart = new ImageIcon("restart.png"); //Obtaining the restart button image
	Image img1 = restart.getImage();
	Image newimg1 = img1.getScaledInstance(150, 120, java.awt.Image.SCALE_SMOOTH); //Scaling
	ImageIcon restart1 = new ImageIcon (newimg1);
	
	//Game over image
	ImageIcon background = new ImageIcon ("gameover.png"); //Obtaining the game over image
	Image img2 = background.getImage();
	Image newimg2 = img2.getScaledInstance(800, 800, java.awt.Image.SCALE_SMOOTH); //Scaling
	ImageIcon background1 = new ImageIcon (newimg2);
	
	//Creating game over frame
	JFrame frame = new JFrame("Crossy Road");
	
	//Creating title image 
	JLabel bg = new JLabel();
	
	//Creating restart button
	JButton restartButton = new JButton(restart1);
	
	public GameOver() {
        setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Setting title image 
		bg.setBounds(-85, 0, 900, 760); //Boundary
		bg.setIcon(background1); //Setting the title image to the game over image
		frame.setLayout(null);
		
		//Setting restart button 
		restartButton.setBounds(230, 550, 150, 80); //Boundary 
		restartButton.setBorder(BorderFactory.createEmptyBorder()); //Border 
		restartButton.setContentAreaFilled(false);
		restartButton.addActionListener(this);
		
		//Adding the restart button and title image onto the game over frame 
		frame.add(restartButton);
		frame.add(bg);
		
		//Setting the size of the game over frame 
		frame.setSize(655, 800);
		//Set the game over frame visible 
		frame.setVisible(true);
	}
	

    /*
     * Executes an action when the buttons being displayed on the game over frame are pressed
     * Pre: ActionEvent
     * Post: Closes the current frame, and runs the game 
     */
	public void actionPerformed(ActionEvent event){
		//If the user pressed the restart button ... 
		if(event.getSource() == restartButton){ 
			//Close the game over frame 
			frame.dispose();
			//Start the game 
			Game.main();
		}
	}
	
	public static void main() {
		GameOver over = new GameOver(); 
	}
}
