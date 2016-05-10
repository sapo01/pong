package pong;

public class Paddle {
	//fields for the paddle
	private int scoreLine;
	private int paddleX;
    private int paddleY;
    private int pSizeW;
    private int pSizeH;
    
    //changeable paddle speed
    private int paddleSpeed = 6;
    
    public Paddle(int scoreLine, int paddleX, int paddleY, int pSizeW, int pSizeH) {
    	this.scoreLine = scoreLine;
		this.paddleX = paddleX;
		this.paddleY = paddleY;
		this.pSizeW = pSizeW;
		this.pSizeH = pSizeH;
	}    
    
    //getters and setters
	public int getX() {
		return paddleX;
	}
	public void setX(int paddle) {
		this.paddleX = paddle;
	}
	public int getY() {
		return paddleY;
	}
	public void setY(int paddelY) {
		this.paddleY = paddelY;
	}
	public int getSizeW() {
		return pSizeW;
	}
	public void setSizeW(int pSizeW) {
		this.pSizeW = pSizeW;
	}
	public int getSizeH() {
		return pSizeH;
	}
	public void setSizeH(int pSizeH) {
		this.pSizeH = pSizeH;
	}

	public int getSpeed() {
		return paddleSpeed;
	}

	public void setSpeed(int paddleSpeed) {
		this.paddleSpeed = paddleSpeed;
	}

	public int getScoreLine() {
		return scoreLine;
	}
	
}
