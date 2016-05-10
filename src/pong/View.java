package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class View extends Control {
		
	//paint the game screen -> repainted by the step() method
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.WHITE);
        
        //draw main screen at start
        if (showTitleScreen) {

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Pong", 165, 100);
            
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Start: Press Enter", 175, 400);
            g.drawString("Options: Press O", 175, 350);
            if(freeMode){
            	g.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
            	g.drawString("free mode enabled", 175, 420);
            }
        }
        
        else if (playing) {

            /* if asked for that feature: 
             * draw "goal lines" on each side -> removed for better design
             * 
               g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
               g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
            */

            //draw the scores
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
            g.drawString(String.valueOf(playerOneScore), 100, 100);
            g.drawString(String.valueOf(playerTwoScore), 400, 100);

            /*draw the ball
            *if you want to play with a normal ball:
            *
              g.fillOval(ballX, ballY, diameter, diameter);
            */
            
            //draw super mario ball
            g.drawImage(ball.getImg(), ball.getBallX(), ball.getBallY(), null);
            
            //draw the paddles
            g.fillRect(playerOne.getX(), playerOne.getY(), playerOne.getSizeW(), playerOne.getSizeH());
            g.fillRect(playerTwo.getX(), playerTwo.getY(), playerTwo.getSizeW(), playerTwo.getSizeH());
            
            //draw the info
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
            g.drawString("ESC for Exit", 1, 10);
        }
        else if (optionsScreen) {
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
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
    
}
