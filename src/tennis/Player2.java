package tennis;
	 
	import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
	 
	
	public class Player2 implements Runnable{
	      private Board field;
	      private int y = Tennis.WINDOW_HEIGHT / 2;
	      private int yVelocity = 0;
	      //private int X = Tennis.WINDOW_WIDTH - 400;
	      private int X = Tennis.WINDOW_WIDTH-150;
	      private int xVelocity = 0;
	      private int width = 50;
	      private int height = 50;
	      Image noHit = new ImageIcon("src/Resource/player2.png").getImage();
	      Image yesHit = new ImageIcon("src/Resource/player2hit.png").getImage();
	      public boolean hit = false;
	      Image player;
	       
	      public Player2 (Board game) {
	            this.field = game;
	        }
	       
	      public void update(){
	          checkCollision();
	          y = y + yVelocity;
	          X = X + xVelocity;
	      }
	       
	      public void paint(Graphics g){
	          if(hit == false){
	              g.drawImage(noHit, X, y, null);
	          }else{
	              g.drawImage(yesHit, X, y, null);
	               
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
	 
	      public int getWidth() {
	        return width;
	      }
	       
	      public int getHeight() {
	        return height;
	      }
	       
	      public void keyPressed(KeyEvent e){
	          if (e.getKeyCode() == KeyEvent.VK_S) {
	                setYVelocity(-1);
	                if (getY() < 30) {
	                    setYVelocity(0);
	                    e.consume();
	                }
	          }else if (e.getKeyCode() == KeyEvent.VK_X) {
	                setYVelocity(1);
	                if (getY() + getHeight() > Tennis.WINDOW_HEIGHT - 28) {
	                    setYVelocity(0);
	                }
	                e.consume();
	           }else if (e.getKeyCode() == KeyEvent.VK_A) {
	                setXVelocity(-1);
	               
	                
	                if (getX()- 40  < 550) {
	                    setX(550+40);
	                    setXVelocity(0);
	                }
	                
	                e.consume();
	           }else if (e.getKeyCode() == KeyEvent.VK_D) {
	                setXVelocity(1);
	                if (getX()  > Tennis.WINDOW_WIDTH-150) {
	                    setX(Tennis.WINDOW_WIDTH-150);
	                    setXVelocity(0);
	                }
	                e.consume();
	            }
	      }
	 
	      public void keyReleased(KeyEvent e) {
	            int keyCode = e.getKeyCode();
	            if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_X) {
	                setYVelocity(0);
	            }
	            if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A) {
	                setXVelocity(0);
	            }
	        }
	       
	      private synchronized void checkCollision(){
	            if (this.X < field.getBall().getX()  && (this.X + width)  > field.getBall().getX()) {
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
	}