package CrossyRoad;

import java.awt.*; 
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

/* File Name: MainPanel
 * Name: Chittesh.C
 * Date: January 5th, 2022
 * Description: Most important/main function of the game
 				Imports and creates the different sprites and the background involved in the game 
 				Allows the obstacle sprite to move left or right
 				Allows the player to control the character sprite via arrow keys
 				Defines the directional sprite of the character with each direction the character faces being a different sprite
 				Detects whether or not a collision has occurred between the obstacle and the character or between the character and the top of the frame
 */

public class MainPanel extends JPanel implements  Runnable, KeyListener {
	//Images for the background, and different position of the chicken and obstacles
	Image road; //Background

	Image chickenSprite; //Default image of the chicken sprite
	Image chickenForward, chickenBackward, chickenLeft, chickenRight; //Different image position's of the chicken sprite
	
	Image car1Right; //Grey car sprite
	Image car2Left;	//Truck sprite
	Image car3Right; //Garbage truck sprite
	Image car4Right; //Ambulance car sprite
	Image car5Right; //Sports car sprite
	Image car6Left; //Tractor sprite
	Image car7Left;	//Bus sprite
	Image car8Left;	//Fire truck sprite
	Image car9Right; //Pickup truck sprite 

	Thread runner;
    
	//Speed of the chicken sprite 
    int speed = 70;
    //Velocity of the chicken sprite
    int v = 5;
    
    //Chicken's x and y position 
    int chickenX = 100, chickenY = 700;
    //Chicken's width and height for collision rectangle 
    int chickenWidth = 1;
    int chickenHeight = 1; 
    
    //Obstacle's x and y position, & width and height for collision rectangle 
    //Grey car
    int car1X = 10, car1Y = 550;
    int car1Width = 50, car1Height = 30;
    
    //White truck 
    int car2X = 600, car2Y = 450;
    int car2Width = 90, car2Height = 35;

    //Garbage truck
    int car3X = 200, car3Y = 520;
    int car3Width = 90, car3Height = 55;
    
    //Ambulance car 
    int car4X = 200, car4Y = 180;
    int car4Width = 50, car4Height = 35;
    
    //Sports car
    int car5X = 500, car5Y = 180;
    int car5Width = 50, car5Height = 35;
    
    //Tractor 
    int car6X = 900, car6Y = 420;
    int car6Width = 90, car6Height = 55;
    
    //Bus
    int car7X = 200, car7Y = 90;
    int car7Width = 10, car7Height = 55;
    
    //Fire truck
    int car8X = 500, car8Y = 85;
    int car8Width = 90, car8Height = 55;
    
    //Pickup truck 
    int car9X = 0, car9Y = 175;
    int car9Width = 60, car9Height = 25;

    public MainPanel () {
        super();
        addKeyListener(this);
        
        //Calling roadBackground to obtain the background image of the game 
        roadBackground();
        
        //Calling chickenSprite to obtain images for the different directions (forward, backward, left, right) of the chicken 
        mainSprite();
        
        //Calling obstacleSprite to obtain images for the different directions (left, or right) for the obstacles 
        obstacleSprite();
        
        runner = new Thread(this);
        runner.start();
    }
    
    /*
     * Obtaining the background image of the game 
     * Post: road (background image of the game)
     */
	private void roadBackground() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        road = kit.getImage("roads.png");
	}
    
    /*
     * Obtaining the images for the different position of the chicken (forward, backward, left, & right)
     * Post: chickenForward, chickenBackward, chickenLeft, chickenRight (different positions of the chicken), chickenSprite (default position when the game starts)
     */
	private void mainSprite() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        
        chickenForward = kit.getImage("chickenforward.png"); //Forward position of the chicken 
        
        chickenBackward = kit.getImage("chickenback.png"); //Backward position of the chicken 
        
        chickenLeft = kit.getImage("chickenleft.png"); //Left position of the chicken 

        chickenRight = kit.getImage("chickenright.png"); //Right position of the chicken 

        //Setting the default position of the chicken to the forward position 
        chickenSprite = chickenForward; 
	}
	
    /*
     * Obtaining the images for the different positions of the obstacles (left or right)
     * Post: car1Right, car2Left, car3Right, car4Right, car5Right, car6Left, car7Left, car8Left (different position of the obstacle)
     */
    private void obstacleSprite() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        
        car1Right = kit.getImage("car1right.png");
        
        car2Left = kit.getImage("car2left.png");
        
        car3Right = kit.getImage("car3right.png"); 
        
        car4Right = kit.getImage("car4right.png");
        
        car5Right = kit.getImage("car5right.png");
        
        car6Left = kit.getImage("car6left.png");		
        
        car7Left = kit.getImage("car7left.png");
        
        car8Left = kit.getImage("car8left.png");
        
        car9Right = kit.getImage("car9right.png");
	}
    
    /*
     * Drawing the images such as the chicken sprite, obstacles, and the background on to the panel
     * Pre: Graphics
     * Post: Painting the sprites and background
     */
    public void paintComponent(Graphics comp) {
        Graphics2D comp2D = (Graphics2D) comp;
        comp2D.drawImage(road, 0, 0, this); //Painting the background
        comp2D.drawImage(chickenSprite,chickenX,chickenY,this); //Painting the chicken
        comp2D.drawImage(car1Right, car1X, car1Y, this); //Painting the grey car
        comp2D.drawImage(car2Left, car2X, car2Y, this); //Painting the white truck 
        comp2D.drawImage(car3Right, car3X, car3Y, this); //Painting the garbage truck
        comp2D.drawImage(car4Right, car4X, car4Y, this); //Painting the ambulance car
        comp2D.drawImage(car5Right, car5X, car5Y, this); //Painting the sports car
        comp2D.drawImage(car6Left, car6X, car6Y, this); //Painting the tractor
        comp2D.drawImage(car7Left, car7X, car7Y, this); //Painting the bus
        comp2D.drawImage(car8Left, car8X, car8Y, this); //Painting the fire truck
        comp2D.drawImage(car9Right, car9X, car9Y, this); //Painting the pickup truck
    }
    
    /*
     * The main components of the game
     * Involves the movement of the obstacles, and checks if the obstacles are out of bounds 
     * Involves collision detection when the chicken and obstacle collide, and when the chicken reaches the top
     * Post: Movement of the obstacle's & collision detection between the chicken and obstacles, and between the chicken and the top of the frame 
     */
    public void run() {
        Thread thisThread = Thread.currentThread();
	    while (runner == thisThread) {
	    	//Creating counters which will be used for each obstacle movement and speed
	    	int counter1 = 100;
        	int counter2 = 100;
        	int counter3 = 100;
        	int counter4 = 100;
        	//Incrementing counter's 
        	counter1 +=15; //First lane from the bottom
        	counter2 += 25; //Second lane from the bottom
        	counter3 += 60; //Third lane from the bottom
        	counter4 += 50; //Fourth lane from the bottom

        	//The speed and x movement of the obstacles (+ means movement to the right, - means movement to the left)
        	car1X += 15; //Incrementing the speed and the movement of the grey car by 15
        	car2X -= 25; //Decrementing the speed and the movement of the white truck by 25
        	car3X += 15; //Incrementing the speed and the movement of the garbage truck by 15
        	car4X += 60; //Incrementing the speed and the movement of the ambulance car by 30
        	car5X += 60; //Incrementing the speed and the movement of the sports car by 30
        	car6X -= 25; //Decrementing the speed and the movement of the tractor by 25
        	car7X -= 50; //Decrementing the speed and the movement of the bus by 50
        	car8X -= 50; //Decrementing the speed and the movement of the fire truck by 50
        	car9X += 60; //Incrementing the speed and the movement of the pickup truck by 30

        	//Checking if the obstacles are out of bounds on the frame and resetting their positions (counter is also reset)
        	//If the grey car reached the far right boundary ...
        	if (car1X == 650) {
        		counter1 = 100;
        		car1X = 0;
        	}
    		if(car1X > 750) {
    			car1X = -60;
    		}
    		
        	//If the white truck reached the far left boundary ...
        	if (car2X == -10) {
        		counter2 = 100;
        		car2X = 600;
        	}
    		if(car2X < -150) {
    			car2X = 600;
    		}
    		
        	//If the garbage truck reached the far right boundary ...
    		if (car3X == 650) {
        		counter1 = 100;
        		car3X = 0;
        	}
    		if(car3X > 750) {
    			car3X = -60;
    		}
    		
        	//If the ambulance car reached the far right boundary ...
        	if (car4X == 650) {
        		counter3 = 100;
        		car4X = 0;
        	}
    		if(car4X > 750) {
    			car4X = -20;
    		}
    		
        	//If the sports car reached the far right boundary ...
        	if (car5X == 650) {
        		counter3 = 100;
        		car5X = 0;
        	}
    		if(car5X > 750) {
    			car5X = -20;
    		}
    		
        	//If the tractor reached the far left boundary ...
        	if (car6X == -20) {
        		counter2 = 100;
        		car6X = 600;
        	}
    		if(car6X < -150) {
    			car6X = 600;
    		}
    		
        	//If the bus reached the far left boundary ...
        	if (car7X == -20) {
        		counter4 = 100;
        		car7X = 600;
        	}
    		if(car7X < -150) {
    			car7X = 600;
    		}
    		
        	//If the fire truck reached the far left boundary ...
        	if (car8X == -20) {
        		counter4 = 100;
        		car8X = 600;
        	}
    		if(car8X < -150) {
    			car8X = 600;
    		}
    		
        	//If the pickup truck reached the far right boundary ...
        	if (car9X == 650) {
        		counter3 = 100;
        		car9X = 0;
        	}
    		if(car9X > 750) {
    			car9X = -20;
    		}
    		
    		//Creating collision rectangles for the chicken & obstacles 
    	    Rectangle character = new Rectangle(chickenX, chickenY, chickenWidth, chickenHeight);
    	    Rectangle obstacle1 = new Rectangle(car1X, car1Y, car1Width, car1Height);
    	    Rectangle obstacle2 = new Rectangle(car2X, car2Y, car2Width, car2Height);
    	    Rectangle obstacle3 = new Rectangle(car3X, car3Y, car3Width, car3Height);
    	    Rectangle obstacle4 = new Rectangle(car4X, car4Y, car4Width, car4Height);
    	    Rectangle obstacle5 = new Rectangle(car5X, car5Y, car5Width, car5Height);
    	    Rectangle obstacle6 = new Rectangle(car6X, car6Y, car6Width, car6Height);
    	    Rectangle obstacle7 = new Rectangle(car7X, car7Y, car7Width, car7Height);
    	    Rectangle obstacle8 = new Rectangle(car8X, car8Y, car8Width, car8Height);
    	    Rectangle obstacle9 = new Rectangle(car9X, car9Y, car9Width, car9Height);

    	    
    		//Collision detection between the chicken and the different obstacles 
    	    //If the chicken's rectangle intersects with an obstacle's rectangle...  
    	    if (character.intersects(obstacle1) || character.intersects(obstacle2) || character.intersects(obstacle3) || character.intersects(obstacle4)|| character.intersects(obstacle5) 
    	    		|| character.intersects(obstacle6) ||character.intersects(obstacle7) || character.intersects(obstacle8) || character.intersects(obstacle9)) {
    	    	//Signal GameOver class 
    	    	GameOver end = new GameOver();
    	    	//Stop the game
    			break;
    		}
    	    
    	    //Collision rectangle for when the chicken reaches the top of the frame
    	    Rectangle won = new Rectangle (0, 50, 1000, 20);
    		
    	    //If the chicken reached the top of the frame ...
    		if (character.intersects(won)) {
    			//Signal GameWon class
    			GameWon winner = new GameWon();
    			//Stop the game
    			break;
    		}

	        repaint();
	        requestFocusInWindow();
	       
	       try { 
	    	   Thread.sleep(speed); 
	       }
	       catch (InterruptedException e) {
	    	   
	       } 
	    }
    }
    
    /*
     * Movement for the chicken sprite via arrow keys
     * Pre: KeyEvent
     * Post: Movement of the chicken, and change in position of the chicken
     */
	public void keyPressed (KeyEvent event) { 
    	//If the user pressed left arrow key ...
		if (event.getKeyCode() == KeyEvent.VK_LEFT) { 
    		//Set the default sprite of the chicken to the left position 
			chickenSprite = chickenLeft; 
    		chickenX -= v; 
    		//If the chicken reached the far left boundary of the frame... 
    		if(chickenX < 0)
    			//Set the chicken to come from the far right side of the frame
    			chickenX = 600; 
    	}
		
		//If the user pressed up arrow key ...
    	else if (event.getKeyCode() == KeyEvent.VK_UP) { 
    		//Set the default sprite of the chicken to the forward position 
    		chickenSprite = chickenForward; 
    		chickenY -= v; 
		} 
		
		//If the user pressed right arrow key ...
    	else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
    		//Set the default sprite of the chicken to the right position 
    		chickenSprite = chickenRight; 
    		chickenX += v; 
    		//If the chicken reached the far right boundary of the frame... 
    		if(chickenX > 600)
    			//Set the chicken to come from the left boundary of the frame
    			chickenX = 0; 
    	}
      
		//If the user pressed down arrow key ...
    	else if (event.getKeyCode() == KeyEvent.VK_DOWN) { 
    		//Set the default sprite of the chicken to the backward position 
    		chickenSprite = chickenBackward; 
    		chickenY += v; 
    		//If the user reached the bottom boundary frame...
    		if(chickenY > 700)
    			//Chicken is not allowed to come from the top side of the frame 
    			chickenY = 700; 
		 }
    }
	    
    public void keyReleased(KeyEvent event){  

    }

    public void keyTyped(KeyEvent event){  
    }
    
}
