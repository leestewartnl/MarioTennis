package tennis;
	 
	import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.*;
	 
	 
	public class Ball2 implements Runnable{
	    private Board field;
	    private int x;
	    private int y;
	    public int xVelocity = 5;
	    public int yVelocity = 1;
	    private int size = 20;
	    protected int player1Score = 0;
	    protected int player2Score = 0;
	    public boolean gameEnd = false;
	    private Image ball = new ImageIcon("src/Resource/ball.png").getImage();
	    boolean player1served = true;  // new code added
	     
	    //Constructor///////////////////////////////////////////////////
	    public Ball2(Board game) {
	        this.field = game;
	        x = field.getPlayer().getX() + field.getPlayer().getWidth();
	        y = field.getPlayer().getY();
	    }
	      ////////////////////////////////////////////////////////////////
	      //The Update Method////////////////////////////////////////////
	      public void update(){
	          hitWall();
	          if(player1Score > 8 || player2Score > 8)
	          {
	              gameEnd = true;
	              xVelocity = 0;
	              yVelocity = 0;
	          }else{
	              if (x < 0) {
	            	  
	            	  player2Score = player2Score + 1;
	            	  serve2();
	  	              
	            	  
	              }else if (x + size > Tennis.WINDOW_WIDTH - 6) {
	            	  
	            	  player1Score = player1Score + 1;
	            	  serve();
	            	  
	              }
	              if (y < 0) {
	                serve();
	              }else if (y + size > Tennis.WINDOW_HEIGHT - 28) {
	                  serve();
	              }
	              x = x + xVelocity;
	              y = y + yVelocity;
	        }
	      }
	      ///////////////////////////////////////////////////////////////
	       
	      //Serve Method/////////////////////////////////////////////////
	      private void serve(){
	          this.x = field.getPlayer().getX() + field.getPlayer().getWidth();
	          this.y = field.getPlayer().getY();
	          xVelocity = 5;
	          yVelocity = 0;
	          try {
	            Thread.sleep(300);
	          } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	          }
	      }
	      ///////////////////////////////////////////////////////////////
	      
	      private void serve2(){
	          this.x = field.getPlayer2().getX() + field.getPlayer2().getWidth();
	          this.y = field.getPlayer2().getY();
	          xVelocity = -5;
	          yVelocity = 0;
	          try {
	            Thread.sleep(300);
	          } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	          }
	      }
	      
	      //The PAINT Method/////////////////////////////////////////////
	      public void paint(Graphics g) {
	          g.setColor(Color.WHITE);
	          g.drawImage(ball, x,y, null);
	          g.drawString(toPlayer1(), 5, 15);
	          g.drawString(toPlayer2(), 324, 15);
	      }
	      ////////////////////////////////////////////////////////////////
	       
	      //Change XDirection/////////////////////////////////////////////
	      public synchronized void reverseDirection() {
	        if(this.x < Tennis.WINDOW_WIDTH/2)
	          xVelocity = 5;
	        else
	          xVelocity = -5;
	      }
	      /////////////////////////////////////////////////////////////////
	       
	      //Change YDirection//////////////////////////////////////////////
	      public synchronized void reverseDirectionY() {
	        yVelocity = - yVelocity;
	      }
	      /////////////////////////////////////////////////////////////////
	       
	      //HitWall Situation//////////////////////////////////////////////
	      public void hitWall() {
	        if (this.y < 71) {
	          reverseDirectionY();
	        }
	      }
	      /////////////////////////////////////////////////////////////////
	       
	      //getX///////////////////////////////////////////////////////////
	      public int getX() {
	        return x;
	      }
	      /////////////////////////////////////////////////////////////////
	       
	      //GetY///////////////////////////////////////////////////////////
	      public int getY() {
	        return y;
	      }
	      /////////////////////////////////////////////////////////////////
	       
	      //getPlayerScore()///////////////////////////////////////////////
	      public int getPlayerScore() {
	        return player1Score;
	      }
	      /////////////////////////////////////////////////////////////////
	       
	      //getComputerScore///////////////////////////////////////////////
	      public int getComputerScore() {
	        return player2Score;
	      }
	      /////////////////////////////////////////////////////////////////
	       
	      //String for Player//////////////////////////////////////////////
	      /*
	      public String toPlayer() {
	        String retVal = "";
	        retVal = "Player Score: " + playerScore;
	        return retVal;
	      }
	      */
	      public String toPlayer1() {
		        String retVal = "";
		        retVal = "Player1 Score: " + player1Score;
		        return retVal;
		      }
	      /////////////////////////////////////////////////////////////////
	      
	      //String for Computer////////////////////////////////////////////
	       public String toPlayer2() {
	        String retVal = "";
	        retVal = "Player2 Score: " + player2Score;
	        return retVal;
	      }
	      /*
	      public String toComputer() {
	        String retVal = "";
	        retVal = "Computer Score: " + computerScore;
	        return retVal;
	      }
	      */
	      //////////////////////////////////////////////////////////////////
	       
	      //The Run Method//////////////////////////////////////////////////
	      public void run(){
	          try {
	            while(true){
	                update();
	                Thread.sleep(5);
	            }
	          } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	      }
	      /////////////////////////////////////////////////////////////////
	       
	      //Key////////////////////////////////////////////////////////////
	      public void keyTyped(KeyEvent e){
	          if (e.getKeyCode() == KeyEvent.VK_W){
	              e.consume();
	          }
	      }
	}