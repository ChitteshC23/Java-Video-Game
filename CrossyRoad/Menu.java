package CrossyRoad;

import java.awt. *; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Menu extends JFrame implements ActionListener {
	
	//Start button image 
	ImageIcon start = new ImageIcon("start.png"); //Obtaining the start button image
	Image img1 = start.getImage();
	Image newimg1 = img1.getScaledInstance(150, 90, java.awt.Image.SCALE_SMOOTH); //Scaling
	ImageIcon start1 = new ImageIcon (newimg1);
	
	//Rules button image 
	ImageIcon rules = new ImageIcon("rule.png"); //Obtaining the rules button image
	Image img2 = rules.getImage();
	Image newimg2 = img2.getScaledInstance(150, 90, java.awt.Image.SCALE_SMOOTH); //Scaling
	ImageIcon rules1 = new ImageIcon(newimg2);
	
	//Background image
	ImageIcon back = new ImageIcon ("title.png"); //Obtaining the background image
	Image img3 = back.getImage();
	Image newimg3 = img3.getScaledInstance(1200, 800, java.awt.Image.SCALE_SMOOTH); //Scaling
	ImageIcon back1 = new ImageIcon (newimg3);
	
	//Rules image 
	ImageIcon rulesbackground = new ImageIcon ("rulesbg.png"); //Obtaining the rules image
	Image img4 = rulesbackground.getImage();
	Image newimg4 = img4.getScaledInstance(850, 900, java.awt.Image.SCALE_SMOOTH); //Scaling
	ImageIcon rulesbg1 = new ImageIcon (newimg4);
	
	//Creating menu frame 
	JFrame frame = new JFrame("Crossy Road");
	
	//Creating title image
	JLabel bg = new JLabel();
	
	//Creating a start button
	JButton startButton = new JButton(start1);
	
	//Creating a rules button
	JButton rulesButton = new JButton(rules1);
	JLabel rbg = new JLabel(rulesbg1);
	
	public Menu() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Setting start button
		startButton.setBounds(425, 50, 150, 90); //Boundary
		startButton.setBorder(BorderFactory.createEmptyBorder()); //Border
		startButton.setContentAreaFilled(false);
		startButton.addActionListener(this); //Adding actionListner
		
		//Setting rules button
		rulesButton.setBounds(50, 55, 150, 90); //Boundary
		rulesButton.setBorder(BorderFactory.createEmptyBorder()); //Border
		rulesButton.setContentAreaFilled(false);
		rulesButton.addActionListener(this); //Adding actionListener
		
		//Setting the rules 
		rbg.setBounds(-10, -10, 655, 800); //Boundary
		rbg.setVisible(false);
		
		//Setting the title image 
		bg.setBounds(-270, 0, 1200, 800); //Boundary
		bg.setIcon(back1); //Setting the title image to the background image
		frame.setLayout(null);
		
		//Adding the buttons and title image to the menu frame 
		frame.add(startButton);
		frame.add(rulesButton);
		frame.add(bg);
		frame.add(rbg);
		
		//Setting the size of the menu frame 
		frame.setSize(655, 800);
		//Set the menu frame visible 
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Menu gui = new Menu(); 
	}
	
    /*
     * Executes a action when the buttons being displayed on the menu frame are pressed
     * Pre: ActionEvent 
     * Post: If the user presses "Start", close the current frame, start/run the game
             If the user presses rules, close the current frame, show the rules/objective of the game
     */
	public void actionPerformed(ActionEvent event){
		//If the start button is pressed ...
		if(event.getSource() == startButton){ 
			//Remove the menu frame 
			frame.dispose();
			//Start the game 
			Game.main(); 
		}
		
		//If the rules button is pressed ...
		if(event.getSource() == rulesButton) {
			//Set the title image invisible
			bg.setVisible(false);
			//Set the rules button invisible
			rulesButton.setVisible(false);
			//Re-arrange the start button 
			startButton.setBounds(235, 575, 150, 90);
			//Set the rules image visible 
			rbg.setVisible(true);
		}
	}
	
}
