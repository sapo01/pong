package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

// we probably should refactor it, so that we have controller and model separated

@SuppressWarnings("serial")
public class Control extends JPanel implements ActionListener, KeyListener{

	//create playerPanels and the ball
	Ball ball = new Ball();
	
    Paddle playerOne = new Paddle(5,25,250,10,50);
    Paddle playerTwo = new Paddle(495,465,250,10,50);
    
    
	private boolean showTitleScreen = true;
	private boolean optionsScreen = false;
    private boolean playing = false;
    private boolean gameOver = false;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean wPressed = false;
    private boolean sPressed = false;
    
    //for free mode
    private boolean freeMode = true;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean aPressed = false;
    private boolean dPressed = false;

    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    
    public Control(){
    	
        //listen to key presses
        setFocusable(true);
        addKeyListener(this);

        //call step() -> speed of the game
        Timer timer = new Timer(1000/90, this);
        timer.start();
    }


    public void actionPerformed(ActionEvent e){
        step();
    }

    public void step(){

        if(playing){        	
        	int playerOneSpeed = playerOne.getSpeed();
        	int playerTwoSpeed = playerTwo.getSpeed();
        	int frameHeight = getHeight();
        	
            //PlayerKeyPressCheck
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
            
            //add left right for free mode
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
        	
          
            //BallMovements
            int nextBallLeft = ball.getBallX() + ball.getDeltaX();
            int nextBallRight = ball.getBallX() + ball.getDiameter() + ball.getDeltaX();
            
            int nextBallTop = ball.getBallY() + ball.getDeltaY();
            int nextBallBottom = ball.getBallY() + ball.getDiameter() + ball.getDeltaY();
                      

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

            //will the ball go off the left side?
            if (nextBallLeft < playerOneLine) { 
                //is it going to miss the paddle?
                if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {

                    playerTwoScore ++;

                    if (playerTwoScore == 3) {
                        playing = false;
                        gameOver = true;
                    }
                    ball.setDeltaX(-3);
                    ball.setBallX(250);
                    ball.setBallY(250);
                }
                else if(playerOne.getX()<ball.getBallX()){
                	ball.setDeltaX(ball.getDeltaX() * -1);
                }
            }else if(nextBallLeft < playerOne.getX()+playerOne.getSizeW()){
            	if (!(nextBallTop > playerOneBottom || nextBallBottom < playerOneTop)) {
            		if(ball.getBallX()>playerOne.getX()){
            	    ball.setDeltaX(ball.getDeltaX() * -1);
            		}
            	}
            }
                //will the ball go off the right side?
                if (nextBallRight > playerTwoLine) {
                    //is it going to miss the paddle?
                    if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {

                        playerOneScore ++;
                        
                        if (playerOneScore == 3) {
                            playing = false;
                            gameOver = true;
                        }
                        ball.setDeltaX(3);
                        ball.setBallX(250);
                        ball.setBallY(250);
                    }
                    else if(playerTwo.getX()>ball.getBallX()){
                        ball.setDeltaX(ball.getDeltaX() * -1);
                    }
                }else if(nextBallRight > playerTwo.getX()){
                	if (!(nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop)) {
                		if(ball.getBallX()<playerTwo.getX()){
                	    ball.setDeltaX(ball.getDeltaX() * -1);
                		}
                	}
                }

                //move the ball
                ball.setBallX(ball.getBallX() + ball.getDeltaX());
                ball.setBallY(ball.getBallY() + ball.getDeltaY());
            }
        //stuff has moved, tell this JPanel to repaint itself
        repaint();
    } 
    
	//paint the game screen
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.WHITE);

        if (showTitleScreen) {

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Pong", 165, 100);
            
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Start: Press Enter", 175, 400);
            g.drawString("Options: Press O", 175, 350);
            if(freeMode){
            	g.drawString("free mode enabled", 175, 420);
            }
        }
        else if (playing) {

            /*draw "goal lines" on each side
            g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
            g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());*/

            //draw the scores
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
            g.drawString(String.valueOf(playerOneScore), 100, 100);
            g.drawString(String.valueOf(playerTwoScore), 400, 100);

            //draw the ball
            //g.fillOval(ballX, ballY, diameter, diameter);
            g.drawImage(ball.getImg(), ball.getBallX(), ball.getBallY(), null);
            
            //draw the paddles
            g.fillRect(playerOne.getX(), playerOne.getY(), playerOne.getSizeW(), playerOne.getSizeH());
            g.fillRect(playerTwo.getX(), playerTwo.getY(), playerTwo.getSizeW(), playerTwo.getSizeH());
            
        }else if (optionsScreen) {
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
            g.drawString("Press F for Free Mode", 100, 100);
            g.drawString("Back to Startscreen: SPACE", 100, 125);
        }
        else if (gameOver) {

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
            g.drawString(String.valueOf(playerOneScore), 100, 100);
            g.drawString(String.valueOf(playerTwoScore), 400, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            if (playerOneScore > playerTwoScore) {
                g.drawString("Player 1 Wins!", 165, 200);
            }
            else {
                g.drawString("Player 2 Wins!", 165, 200);
            }

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Press space to restart.", 150, 400);
        }
    }
    
    
//    Controller
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
        else if(playing){
        	
        	/* We tried to use switch and got it working,
        	 * but we still keep the if-statements, just in case
        	 */ 
        	
        	
        	/*
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_W) {
                wPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) {
                sPressed = true;
            }else if (e.getKeyCode() == KeyEvent.VK_A) {
                aPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_D) {
                dPressed = true;
            }
            */
        	
        	
        	switch(e.getKeyCode()) {
            case KeyEvent.VK_UP: upPressed = true; break;
            case KeyEvent.VK_DOWN: downPressed = true; break;
            case KeyEvent.VK_LEFT: leftPressed = true; break;
            case KeyEvent.VK_RIGHT: rightPressed = true; break;
            case KeyEvent.VK_W: wPressed = true; break;
            case KeyEvent.VK_S: sPressed = true; break;
            case KeyEvent.VK_A: aPressed = true; break;
            case KeyEvent.VK_D: dPressed = true; break;
            }
        	
        }
        else if (optionsScreen) {
            if (e.getKeyCode() == KeyEvent.VK_F) {
            	if(freeMode){
            	freeMode = false;
            	}else{freeMode = true;}
            	
                showTitleScreen = true;
                optionsScreen = false;
            }else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                showTitleScreen = true;
            }
        }else if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameOver = false;
                showTitleScreen = true;
                playerOneScore = 0;
                playerTwoScore = 0;
                //recreate game
                this.ball = new Ball();
                this.playerOne = new Paddle(5,25,250,10,50);
                this.playerTwo = new Paddle(495,465,250,10,50);
                
            }
        }
    }


    public void keyReleased(KeyEvent e) {
        if (playing) {
        	
        	/* We tried to use switch and got it working,
        	 * but we still keep the if-statements, just in case
        	 */ 
        	
        	
        	/*
        	if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_W) {
                wPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) {
                sPressed = false;
            }else if (e.getKeyCode() == KeyEvent.VK_A) {
                aPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_D) {
                dPressed = false;
            }
        	*/
        	
        
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
}