package tennis;
	 
	import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
	 
	
	public class Player implements Runnable{
	      private Board field;
	      private int y = Tennis.WINDOW_HEIGHT / 2 - 100;
	      private int yVelocity = 0;
	      private int X;
	      private int xVelocity = 0;
	      private int width = 40;	//changed from 50  226
	      private int height = 180;  //changed from 50
	      //Image noHit = new ImageIcon("src/Resource/player1.png").getImage();
	      Image noHit;
	      //Image yesHit = new ImageIcon("src/Resource/player1hit.png").getImage();
	      Image yesHit;
	      public boolean hit = false;
	      //Image player;
	      //added by Lee
	      boolean player1;
	       
	      public Player (Board game, Image noHit, Image yesHit, int x, boolean play) {
	            this.field = game;
	            this.noHit = noHit;
	            this.yesHit = yesHit;
	            this.X = x;
	            this.player1 = play;
	        }
	       
	      public void update(){
	          checkCollision();
	          y = y + yVelocity;
	          X = X + xVelocity;
	      }
	       
	      public void paint(Graphics g){
	          if(hit == false){
	              g.drawImage(noHit, X, y, field);
	          }else{
	              g.drawImage(yesHit, X, y, field);
	               
	          }
	      }
	       
	      public void setYVelocity(int speed) {
	        yVelocity = speed;
	      }
	       
	      public void setXVelocity(int speed) {
	        xVelocity = speed;
	      }
	       
	      public void setX(int m){
	        this.X = m;
	      }
	       
	      public int getX() {
	        return X;
	      }
	       
	      public int getY() {
	        return y;
	      }
	      
	      //added by Lee
	      public void setY(int m){
	    	  this.y = m;
	      }
	 
	      public int getWidth() {
	        return width;
	      }
	       
	      public int getHeight() {
	        return height;
	      }
	      
	      public void keyPressed(KeyEvent e){
	    	  if (player1){
	    		  if (e.getKeyCode() == KeyEvent.VK_S) {
		        	  this.setYVelocity(-1);
		        	  if(field.getBall().retserve() && field.getBall().servetime())
	 	            		field.getBall().setYVelocity(-1);
		                if (this.getY() < 0) {
		                	this.setY(0);
		                    this.setYVelocity(0);
		                    if(field.getBall().retserve() && field.getBall().servetime()){
		                    	field.getBall().setY(0);
		                    	field.getBall().setYVelocity(0);
		                    }
		                }
		                e.consume();
		          }else if (e.getKeyCode() == KeyEvent.VK_X) {
		        	  	this.setYVelocity(1);
		        	  	if(field.getBall().retserve() && field.getBall().servetime())
	 	            		field.getBall().setYVelocity(1);
		                if (this.getY() > Tennis.WINDOW_HEIGHT - 200) {  // was 28  this.getY() + getHeight()) > Tennis.WINDOW_HEIGHT - 15
		                    this.setY(Tennis.WINDOW_HEIGHT - 200);
		                	this.setYVelocity(0);
		                    if(field.getBall().retserve() && field.getBall().servetime()){
		 	            		field.getBall().setY(Tennis.WINDOW_HEIGHT - 200);
		                    	field.getBall().setYVelocity(0);
		                    }
		                    //e.consume();	// moved inside brackets
		                }
		                e.consume();
		           }else if (e.getKeyCode() == KeyEvent.VK_D) {
		        	   this.setXVelocity(1);
		        	   if(field.getBall().retserve() && field.getBall().servetime())
	 	            		field.getBall().setXVelocity(1);
		                if (this.getX()+ 40  > 350) {
		                    this.setX(324-40);
		                    this.setXVelocity(0);
		                    if(field.getBall().retserve() && field.getBall().servetime()){
		                    	field.getBall().setX(310);
		 	            		field.getBall().setXVelocity(0);
		 	            		}
		                    //e.consume();	// moved inside brackets
		                }
		                e.consume();   
		           }else if (e.getKeyCode() == KeyEvent.VK_A) {
		        	   this.setXVelocity(-1);
		        	   if(field.getBall().retserve() && field.getBall().servetime())
	 	            		field.getBall().setXVelocity(-1);
		                if (this.getX() < 0) {
		                    this.setX(0);
		                    this.setXVelocity(0);
		                    if(field.getBall().retserve() && field.getBall().servetime()){
		                    	field.getBall().setX(0);
		 	            		field.getBall().setXVelocity(0);
		 	            		}
		                    //e.consume();	// moved inside brackets
		                }
		                e.consume();
		            }
	    	  }
	    	  else{
	    		  if (e.getKeyCode() == KeyEvent.VK_UP) {  
		                this.setYVelocity(-1);
		                if(!(field.getBall().retserve()) && field.getBall().servetime())
	 	            		field.getBall().setYVelocity(-1);
		                if (this.getY() < 0) {
		                	this.setY(0);
		                	this.setYVelocity(0);
		                	if(!(field.getBall().retserve()) && field.getBall().servetime()){
		                    	field.getBall().setY(0);
		                    	field.getBall().setYVelocity(0);
		                    }
		                }
		                    e.consume();
		                
		          }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {  
		        	  this.setYVelocity(1);
		        	  if(!(field.getBall().retserve()) && field.getBall().servetime())
	 	            		field.getBall().setYVelocity(1);
		                if (this.getY() > Tennis.WINDOW_HEIGHT - 200) {   // was 28  (this.getY() + getHeight()) > Tennis.WINDOW_HEIGHT - 15
		                	this.setY(Tennis.WINDOW_HEIGHT - 200);
		                	this.setYVelocity(0);
		                	if(!(field.getBall().retserve()) && field.getBall().servetime()){
		                    	field.getBall().setY(Tennis.WINDOW_HEIGHT - 200);
		                    	field.getBall().setYVelocity(0);
		                    }
		                }
		                e.consume();
		           }else if (e.getKeyCode() == KeyEvent.VK_LEFT) { //was vk_a
		        	   this.setXVelocity(-1);
		        	   if(!(field.getBall().retserve()) && field.getBall().servetime())
	 	            		field.getBall().setXVelocity(-1);
		                if (getX()- 40  < 550) {
		                	this.setX(550+40);
		                	this.setXVelocity(0);
		                	if(!(field.getBall().retserve()) && field.getBall().servetime()){
		                    	field.getBall().setX(550+40);
		                    	field.getBall().setXVelocity(0);
		                    }
		                }
		                
		                e.consume();
		           }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //was vk_d
		        	   this.setXVelocity(1);
		        	   if(!(field.getBall().retserve()) && field.getBall().servetime())
	 	            		field.getBall().setXVelocity(1);
		                if (this.getX()  > Tennis.WINDOW_WIDTH-150) {
		                	this.setX(Tennis.WINDOW_WIDTH-150);
		                	this.setXVelocity(0);
		                	if(!(field.getBall().retserve()) && field.getBall().servetime()){
		                    	field.getBall().setX(Tennis.WINDOW_WIDTH-100);
		                    	field.getBall().setXVelocity(0);
		                    }
		                }
		                e.consume();
		            }
	    	  }  
	      }
	 
	      public void keyReleased(KeyEvent e) {
	            int keyCode = e.getKeyCode();
	            if(player1){
	            	 if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_X) {
	 	            	this.setYVelocity(0);
	 	            	if(field.getBall().retserve() && field.getBall().servetime())
	 	            		field.getBall().setYVelocity(0);
	 	            }
	 	            if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D) {
	 	            	this.setXVelocity(0);
	 	            	if(field.getBall().retserve() && field.getBall().servetime())
	 	            		field.getBall().setXVelocity(0);
	 	            }
	            }
	            else{
	            	if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
		            	this.setYVelocity(0);
		            	if(!field.getBall().retserve() && field.getBall().servetime())
	 	            		field.getBall().setYVelocity(0);
		            }
		            if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
		            	this.setXVelocity(0);
		            	if(!field.getBall().retserve() && field.getBall().servetime())
	 	            		field.getBall().setXVelocity(0);
		            }
	            }
	        }
	        
	       
	      private synchronized void checkCollision(){
	            if ((this.X - width ) < field.getBall().getX() && (this.X + width)  > field.getBall().getX()) {
	              if (this.y < field.getBall().getY()  && (this.y + height) > field.getBall().getY()) {
	                  hit = true;
	                  field.getBall().reverseDirection();
	                  try {
	                        Thread.sleep(30);
	                  } catch (InterruptedException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                  }
	              }
	            }else{
	                hit = false;
	            }
	      }
	       
	      public void run(){
	          try {
	            while(true){
	                update();
	                Thread.sleep(5);
	            }
	          } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	      }
	      
	      // added by Lee. getPlayer should not be in Board class. 
	      public Player getPlayer(){
	    	  return this;
	      }
	}
	
	