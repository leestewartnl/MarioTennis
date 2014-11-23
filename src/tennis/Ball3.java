package tennis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Ball3 implements Runnable {
	
	private Board field;
    private int x;
    private int y;
    public int xVelocity = 0;
    public int yVelocity = 0;   //changed from 1 to 0
    private int size = 20;
    protected int player1Score = 0;
    protected int player2Score = 0;
    protected int previousScore1 = 0;
    protected int previousScore2 = 0;
    protected int matchScore1 = 0;
    protected int matchScore2 = 0;
    public boolean gameEnd = false;
    public boolean matchEnd = false;
    public int challenge = 0;//0: no chalenge; 1: challenge a; 2:challenge b;
    private Image ball = new ImageIcon("src/Resource/ball.png").getImage();
    protected boolean player1served = true;  // new code added
    boolean serve = true;
    boolean f1Pressed;
    
    // added by Lee
    Player player1;
    Player player2;
     
    //Constructor///////////////////////////////////////////////////
    public Ball3(Board game, Player player1, Player player2) {
        this.field = game;
        //x = field.getPlayer().getX() + field.getPlayer().getWidth();
        //y = field.getPlayer().getY();
        // modified because I moved getPlayer to Player class
        if(player1served){
  		  player1.setX(0);
  		  player1.setY(Tennis.WINDOW_HEIGHT / 2 - 100);
	    	  this.x = player1.getX() + player1.getWidth();
	          this.y = player1.getY();
  	  }
  	  else{
  		  player2.setX(Tennis.WINDOW_WIDTH-150);
      	  player2.setY(Tennis.WINDOW_HEIGHT / 2 - 100);
	    	  this.x = player2.getX();// + player2.getWidth();
	          this.y = player2.getY();
  	  }
        // had to implement players because I removed getPlayer from Board class
        this.player1 = player1;
        this.player2 = player2;
    }
      ////////////////////////////////////////////////////////////////
    
    public boolean retserve(){
    	return player1served;
    }
    
    public boolean servetime(){
    	return serve;
    }
    
    public void setYVelocity(int speed) {
        yVelocity = speed;
      }
       
      public void setXVelocity(int speed) {
        xVelocity = speed;
      }
       
      public void setX(int m){
        this.x = m;
      }
      
      //added by Lee
      public void setY(int m){
    	  this.y = m;
      }
    
    public void score1Plus()
    {
    	switch (player1Score){
    	case 0:{
    		previousScore1 = 0;
    		challenge = 1;
    		player1Score = 15;
    		break;
    	}
    	case 15:{
    		challenge = 1;
    		previousScore1 = 15;
    		player1Score = 30;
    		break;
    	}
    	case 30:{
    		challenge = 1;
    		previousScore1 = 30;
    		player1Score = 40;
    		break;
    	}
    	case 40:{
    		challenge = 1;
    		previousScore1 = 40;
    		if(player2Score < 40){
    			player1Score = 0;
    			player2Score = 0;
    			matchScore1++;
    			matchEnd = true;
    			break;
    		}
    		else{	
	    		player1Score = 45;
	    		break;
    		}
    	}
    	case 45:{
    		challenge = 0;
    		previousScore1 = previousScore2 = 0;
    		player1Score = 0;
    		player2Score = 0;
			matchScore1++;
			matchEnd = true;
    		break;
    	}
    	default:break;
    	}
    }
    
    public void score2Plus()
    {
    	switch (player2Score){
    	case 0:{
    		challenge = 2;
    		previousScore2 = 0;
    		player2Score = 15;
    		break;
    	}
    	case 15:{
    		challenge = 2;
    		previousScore2 = 15;
    		player2Score = 30;
    		break;
    	}
    	case 30:{
    		challenge = 2;
    		previousScore2 = 30;
    		player2Score = 40;
    		break;
    	}
    	case 40:{
    		challenge = 2;
    		previousScore2 = 40;
    		if(player1Score < 40){
    			player1Score = 0;
    			player2Score = 0;
    			matchScore2++;
    			matchEnd = true;
    			break;
    		}
    		else{	
	    		player2Score = 45;
	    		break;
    		}
    	}
    	case 45:{
    		challenge = 0;
    		previousScore1 = previousScore2 = 0;
    		player1Score = 0;
    		player2Score = 0;
			matchScore2++;
			matchEnd = true;
    		break;
    	}
    	default:break;
    	}
    }
    
    public void scoreUndo(){
    	if(challenge == 1)	player1Score = previousScore1;
    	if(challenge == 2)	player2Score = previousScore2;
    }
    
    public void serveReverse(){
    	player1served = player1served?false:true;
    }
    
      //The Update Method////////////////////////////////////////////
      public void update(){
          //hitWall();
          /*if(matchEnd){
        	  xVelocity = 0;
        	  yVelocity = 0;
        	  JOptionPane.showConfirmDialog(null, "Current Set End, change serve players", "Match End", JOptionPane.OK_CANCEL_OPTION);
        	  serveReverse();
        	  matchEnd = false;
          }*/
          
          /*if(matchScore1 > 7 || matchScore2 > 7)
          {
              gameEnd = true;//sean
              xVelocity = 0;
              yVelocity = 0;
          }*/
          //else {
        	  if (x < 0 && !serve) 
              {
        		  score2Plus();
        		  reset();
        		  //serve = true;
              }
        	  else if((this.x + size) > (Tennis.WINDOW_WIDTH - 6)&&!serve){
        		  score1Plus();
        		  reset();
        		  //serve = true;
        	  }
              x = x + xVelocity;
              y = y + yVelocity;
          }
      
      ///////////////////////////////////////////////////////////////
      
      //reset method/////////////////////////////////////////////////////////////
      public void reset()
      {
    	  if(matchEnd){
        	  JOptionPane.showConfirmDialog(null, "Current Set End, change serve players", "Match End", JOptionPane.OK_CANCEL_OPTION);
        	  serveReverse();
        	  matchEnd = false;
          }
    	  this.setXVelocity(0);
    	  this.setYVelocity(0);
    	  serve = true;
    	  if(player1served){
    		  player1.setX(0);
    		  player1.setY(Tennis.WINDOW_HEIGHT / 2 - 100);
	    	  this.x = player1.getX() + player1.getWidth();
	          this.y = player1.getY();
    	  }
    	  else{
    		  player2.setX(Tennis.WINDOW_WIDTH-150);
        	  player2.setY(Tennis.WINDOW_HEIGHT / 2 - 100);
	    	  this.x = player2.getX();// + player2.getWidth();
	          this.y = player2.getY();
    	  }
      }
       
      //Serve Method/////////////////////////////////////////////////
      private void serve()
      {
    	  serve = false;
    	  if (player1served)
    	  {
    		  //this.x = player1.getPlayer().getX() + player1.getPlayer().getWidth();
              //this.y = player1.getPlayer().getY()+50;//sean
              xVelocity = 3;
              yVelocity = 0;
              
              try {
                Thread.sleep(300);
              } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
              
    	  }
    	  else
    	  {
    		  //this.x = player2.getPlayer().getX() - player2.getPlayer().getWidth();
              //this.y = player2.getPlayer().getY()+50;
              xVelocity = -3;
              yVelocity = 0;
              try {
                Thread.sleep(300);
              } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
    	  }
      }
      ///////////////////////////////////////////////////////////////
      /* added by Lee
      private void serve2(){
          this.x = field.getPlayer2().getX() + field.getPlayer2().getWidth();
          this.y = field.getPlayer2().getY();
          xVelocity = -3;
          yVelocity = 0;
          try {
            Thread.sleep(300);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
      }
      */
      //The PAINT Method/////////////////////////////////////////////
      public void paint(Graphics g) {
          g.setColor(Color.WHITE);
          g.drawImage(ball, this.x,this.y, null);
          g.drawString(toMatchplayer1(), 15, 460);
          g.drawString(toPlayer1(), 15, 25);
          
          g.drawString(toMatchplayer2(), 35, 460);
          g.drawString(toPlayer2(), 850, 25);
      }
      ////////////////////////////////////////////////////////////////
       
      //Change XDirection/////////////////////////////////////////////
      public synchronized void reverseDirection() {
        if(this.x < Tennis.WINDOW_WIDTH/2)
          xVelocity = 3;
        else
          xVelocity = -3;
      }
      /////////////////////////////////////////////////////////////////
       
      //Change YDirection//////////////////////////////////////////////
      public synchronized void reverseDirectionY() {
        yVelocity = - yVelocity;
      }
      /////////////////////////////////////////////////////////////////
       
      //HitWall Situation//////////////////////////////////////////////
      public void hitWall() {
        if (this.y < 15) {
          reverseDirectionY();
        }
        
        else if (this.y > 210){
        	reverseDirectionY(); // else clause added by Lee
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
       
      /////////////////////////////////////////////////////////////////
      
      public String toMatchplayer1() {
	        String retVal = "";
	        retVal = ""+matchScore1+" :";
	        return retVal;
	      }
      
/////////////////////////////////////////////////////////////////
      
      public String toMatchplayer2() {
	        String retVal = "";
	        retVal = ""+matchScore2;
	        return retVal;
	      }
      
/////////////////////////////////////////////////////////////////
      
      public String toPlayer1() {
	        //String retVal = "";
	        if (player1Score == 45)
	        	return "Player1 Score: " + "A";
	        return "Player1 Score: " + player1Score;
	        //return retVal;
	      }
      /////////////////////////////////////////////////////////////////
      
      //String for Player2////////////////////////////////////////////
       public String toPlayer2() {
        //String retVal = "";
    	   if (player2Score == 45)
	        	return "Player2 Score: " + "A";
	        return "Player2 Score: " + player2Score;
        //return retVal;
      }
      
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
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_Q && serve && player1served){
			serve();
  	  	}
		
		if (e.getKeyCode() == KeyEvent.VK_P && serve && !player1served){
			serve();
  	  	}
		
		if (e.getKeyCode() == KeyEvent.VK_F1 || e.getKeyCode() == KeyEvent.VK_F12){
			if (serve){
				if(JOptionPane.showConfirmDialog(null, "Do you challenge this point?", "Challenge", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
					scoreUndo();
				}
				/*else if((this.x + size) > (Tennis.WINDOW_WIDTH - 6)){
					if(JOptionPane.showConfirmDialog(null, "Do you challenge this point?", "Challenge", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
						scoreUndo();
					}	
				}*/
			}
			
              //e.consume();//sean
		}
	
}
