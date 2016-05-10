package pong;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Control extends JPanel implements ActionListener, KeyListener{

	//create playerPanels and the ball
	Ball ball = new Ball();
	
    Paddle playerOne = new Paddle(5,25,250,10,50);
    Paddle playerTwo = new Paddle(495,465,250,10,50);
    
    
    protected boolean showTitleScreen = true;
	protected boolean optionsScreen = false;
    protected boolean playing = false;
    protected boolean gameOver = false;
    
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean wPressed = false;
    private boolean sPressed = false;
    
    //more keys for the free mode
    protected boolean freeMode = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean aPressed = false;
    private boolean dPressed = false;

    protected int playerOneScore = 0;
    protected int playerTwoScore = 0;
    
    public Control(){
    	
        //listen to key presses in this panel
        setFocusable(true);
        addKeyListener(this);

        //call step() -> game speed
        Timer timer = new Timer(1000/80, this);
        timer.start();
    }
    
    //ActionEvent
    public void actionPerformed(ActionEvent e){
        step();
    }
    
    //main logic method (player and ball movements)
    public void step(){

        if(playing){        	
        	int playerOneSpeed = playerOne.getSpeed();
        	int playerTwoSpeed = playerTwo.getSpeed();
        	int frameHeight = getHeight();
        	
            //checks if and which key is pressed
            if (wPressed) {
            	if (playerOne.getY()-playerOneSpeed > 0) {    
                    playerOne.setY(playerOne.getY()-playerOneSpeed);
                }
                }
            if (sPressed) {
            	if (playerOne.getY()+ playerOneSpeed + playerOne.getSizeH() <  frameHeight) {
                    playerOne.setY(playerOne.getY()+ playerOneSpeed);
                }
                }
            if (upPressed) {
            	if (playerTwo.getY()-playerTwoSpeed > 0) {    
                    playerTwo.setY(playerTwo.getY()-playerTwoSpeed);
                }
            }
            if (downPressed) {
            	if (playerTwo.getY()+ playerTwoSpeed + playerTwo.getSizeH() <  frameHeight) {
                    playerTwo.setY(playerTwo.getY()+ playerTwoSpeed);
                }
            }
            
            //add left right keys for free mode
            if(freeMode){
            if (aPressed) {
            	if (playerOne.getX()-playerOneSpeed > 5) {    
                    playerOne.setX(playerOne.getX()-playerOneSpeed);
                }
                }
            if (dPressed) {
            	if (playerOne.getX()+ playerOneSpeed + playerOne.getSizeW() <  250) {
                    playerOne.setX(playerOne.getX()+ playerOneSpeed);
                }
                }
            if (leftPressed) {
            	if (playerTwo.getX()-playerTwoSpeed >250) {    
                    playerTwo.setX(playerTwo.getX()-playerTwoSpeed);
                }
            }
            if (rightPressed) {
            	if (playerTwo.getX()+ playerTwoSpeed + playerTwo.getSizeW() < 495) {
                    playerTwo.setX(playerTwo.getX()+ playerTwoSpeed);
                }
            }
            }
            //the method for the ball movements
            ballLogic();
             
            }
        //tell this JPanel to repaint itself
        repaint();
    } 
    
    //outsourced ball logic
    private void ballLogic(){
    	
    	//ball movements
        int nextBallLeft = ball.getBallX() + ball.getDeltaX();
        int nextBallRight = ball.getBallX() + ball.getDiameter() + ball.getDeltaX();
        
        int nextBallTop = ball.getBallY() + ball.getDeltaY();
        int nextBallBottom = ball.getBallY() + ball.getDiameter() + ball.getDeltaY();
        
        //position of the players/paddles
        final int playerOneLine = playerOne.getScoreLine();
        int playerOneTop = playerOne.getY();
        int playerOneBottom = playerOne.getY()+ playerOne.getSizeH();

        final int playerTwoLine = playerTwo.getScoreLine();
        int playerTwoTop = playerTwo.getY();
        int playerTwoBottom = playerTwo.getY() + playerTwo.getSizeH();
        
        //ball bounces off top and bottom of screen
        if (nextBallTop < 0 || nextBallBottom > getHeight()) {
            ball.setDeltaY(ball.getDeltaY() * -1);
        }

        //checks if the ball goes off the left side
        if (nextBallLeft < playerOneLine) { 
            //hit the paddle or not ?
            if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {

                playerTwoScore ++;

                if (playerTwoScore == 3) {
                    playing = false;
                    gameOver = true;
                }
                //reset the ball (service)
                ball.setDeltaX(-3);
                ball.setBallX(250);
                ball.setBallY(250);
            }
            
            //ball hits the paddle -> change the direction
            else if(playerOne.getX()<ball.getBallX()){
            	ball.setDeltaX(Math.abs(ball.getDeltaX()));
            }
        }
        //for the free mode -> if the ball hits the paddle
        else if(nextBallLeft < playerOne.getX()+playerOne.getSizeW()){
        	if (!(nextBallTop > playerOneBottom || nextBallBottom < playerOneTop)) {
        		if(ball.getBallX()>playerOne.getX()){
        	    ball.setDeltaX(Math.abs(ball.getDeltaX()));
        		}
        	}
        }
            //checks if the ball goes off the right side
            if (nextBallRight > playerTwoLine) {
            	//hit the paddle or not ?
                if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {

                    playerOneScore ++;
                    
                    if (playerOneScore == 3) {
                        playing = false;
                        gameOver = true;
                    }
                    //ball reset (service)
                    ball.setDeltaX(3);
                    ball.setBallX(250);
                    ball.setBallY(250);
                }
                //ball hits the paddle -> change the direction
                else if(playerTwo.getX()>ball.getBallX()){
                    ball.setDeltaX(Math.abs(ball.getDeltaX()) * -1);
                }
            }
            //for the free mode -> if the ball hits the paddle
            else if(nextBallRight > playerTwo.getX()){
            	if (!(nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop)) {
            		if(ball.getBallX()<playerTwo.getX()){
            	    ball.setDeltaX(Math.abs(ball.getDeltaX()) * -1);
            		}
            	}
            }
            
        //ball movement
        ball.setBallX(ball.getBallX() + ball.getDeltaX());
        ball.setBallY(ball.getBallY() + ball.getDeltaY());
    }
    
    
    //key event listener
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (showTitleScreen) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                showTitleScreen = false;
                playing = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_O) {
                showTitleScreen = false;
                optionsScreen = true;
            }
        }
        //listen to the playing keys
        else if(playing){
        	     	
        	switch(e.getKeyCode()) {
            case KeyEvent.VK_UP: upPressed = true; break;
            case KeyEvent.VK_DOWN: downPressed = true; break;
            case KeyEvent.VK_LEFT: leftPressed = true; break;
            case KeyEvent.VK_RIGHT: rightPressed = true; break;
            case KeyEvent.VK_W: wPressed = true; break;
            case KeyEvent.VK_S: sPressed = true; break;
            case KeyEvent.VK_A: aPressed = true; break;
            case KeyEvent.VK_D: dPressed = true; break;
            //exit the game and reset the stats
            case KeyEvent.VK_ESCAPE: showTitleScreen = true; playing=false; reset(); break;
            }
        	
        }
        //exception for option screen
        else if (optionsScreen) {
            if (e.getKeyCode() == KeyEvent.VK_F) {
            	if(freeMode){
            	freeMode = false;
            	}
            	else{freeMode = true;}
            	
                showTitleScreen = true;
                optionsScreen = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                showTitleScreen = true;
            }
        }
        //exception for game over screen
        else if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            	reset();
                gameOver = false;
                showTitleScreen = true;
            }
        }
    }

    //key releases
    public void keyReleased(KeyEvent e) {
        if (playing) {
        	switch(e.getKeyCode()) {
            case KeyEvent.VK_UP: upPressed = false; break;
            case KeyEvent.VK_DOWN: downPressed = false; break;
            case KeyEvent.VK_LEFT: leftPressed = false; break;
            case KeyEvent.VK_RIGHT: rightPressed = false; break;
            case KeyEvent.VK_W: wPressed = false; break;
            case KeyEvent.VK_S: sPressed = false; break;
            case KeyEvent.VK_A: aPressed = false; break;
            case KeyEvent.VK_D: dPressed = false; break;
            }
            
        }
    }
    //recreate game for a new session
    public void reset(){
 
    this.ball = new Ball();
    this.playerOne = new Paddle(5,25,250,10,50);
    this.playerTwo = new Paddle(495,465,250,10,50);
    
    playerOneScore = 0;
    playerTwoScore = 0;
    
    upPressed = false;
    downPressed = false;
    leftPressed = false;
    rightPressed = false;
    
    wPressed = false;
    sPressed = false;
    aPressed = false;
    dPressed = false;
    }
}