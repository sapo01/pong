package pong;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ball {
	//default values
	private int ballX = 250;
    private int ballY = 250;
    private int diameter = 30;
    private int ballDeltaX = -2;
    private int ballDeltaY = 3;
    private Image img = new ImageIcon("./src/img/ball.png").getImage();
    
    //constructor for default values
    public Ball (){}
    
    public Ball (int ballX, int ballY, int dia, int deltaX, int deltaY){
    	this.ballX = ballX;
    	this.ballY = ballY;
    	this.diameter = dia;
    	this.ballDeltaX = deltaX;
    	this.ballDeltaY = deltaY;
    }
    
    //getters and setters
    public int getBallX() {
		return ballX;
	}


	public void setBallX(int ballX) {
		this.ballX = ballX;
	}
	
	public int getBallY() {
		return ballY;
	}


	public void setBallY(int ballY) {
		this.ballY = ballY;
	}
	
	public int getDiameter() {
		return diameter;
	}


	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	
	public int getDeltaX() {
		return ballDeltaX;
	}


	public void setDeltaX(int ballDeltaX) {
		this.ballDeltaX = ballDeltaX;
	}

	public int getDeltaY() {
		return ballDeltaY;
	}


	public void setDeltaY(int ballDeltaY) {
		this.ballDeltaY = ballDeltaY;
	}

	public Image getImg() {
		return img;
	}
    
    
    
}
