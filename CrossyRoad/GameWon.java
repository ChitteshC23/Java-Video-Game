package CrossyRoad;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

import CrossyRoad.Menu;

public class GameWon extends JFrame implements ActionListener{
	//Menu button image
	ImageIcon menu = new ImageIcon("menu.png"); //Obtaining the menu button image 
	Image img1 = menu.getImage();
	Image newimg1 = img1.getScaledInstance(150, 120, java.awt.Image.SCALE_SMOOTH); //Scaling
	ImageIcon menu1 = new ImageIcon(newimg1);
	
	//Victory image
	ImageIcon background = new ImageIcon ("gamewon.png"); //Obtaining the victory image
	Image img2 = background.getImage();
	Image newimg2 = img2.getScaledInstance(665, 910, java.awt.Image.SCALE_SMOOTH); //Scaling
	ImageIcon background1 = new ImageIcon (newimg2);
	
	//Creating game won frame
	JFrame frame = new JFrame("Crossy Road");
	
	//Creating title image 
	JLabel bg = new JLabel();
	
	//Creating menu button 
	JButton menuButton = new JButton(menu1);
	
	public GameWon() {
        setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Setting the title image 
		bg.setBounds(-10, 0, 655, 760); //Boundary 
		bg.setIcon(background1); //Setting the title image to the victory image
		frame.setLayout(null);
		
		//Setting the menu button 
		menuButton.setBounds(240, 50, 150, 80); //Boundary
		menuButton.setBorder(BorderFactory.createEmptyBorder()); //Border
		menuButton.setContentAreaFilled(false);
		menuButton.addActionListener(this); //Adding actionListener
		
		//Adding the menu button and title image onto the game over frame 
		frame.add(menuButton);
		frame.add(bg);
		
		//Setting the size of the game won frame 
		frame.setSize(655, 800);
		//Set the game won frame visible 
		frame.setVisible(true);
	}
	
    /*
     * Executes a action when the buttons being displayed on the game won frame are pressed
     * Pre: ActionEvent 
     * Post: Closes the current frame, re-runs the entire game from the menu
     */
	public void actionPerformed(ActionEvent event){
		//If the user pressed the menu button ... 
		if(event.getSource() == menuButton){ 
			//Close the game won frame 
			frame.dispose();
			//Start the game again from the menu
			Menu.main(null);
		}
	}
	
	public static void main() {
		GameWon win = new GameWon(); 
	}
}
