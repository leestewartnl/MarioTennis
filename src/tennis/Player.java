package tennis;
	 
	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.event.KeyEvent;
	import javax.swing.ImageIcon;
	 
	
	public class Player implements Runnable{
	      private Board field;
	      private int y = Tennis.WINDOW_HEIGHT / 2;
	      private int yVelocity = 0;
	      private int X = 0;
	      private int xVelocity = 0;
	      private int width = 50;
	      private int height = 50;
	      Image noHit = new ImageIcon("src/Resource/player1.png").getImage();
	      Image yesHit = new ImageIcon("src/Resource/player1hit.png").getImage();
	      public boolean hit = false;
	      Image player;
	       
	      public Player (Board game) {
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
	          if (e.getKeyCode() == KeyEvent.VK_UP) {
	                setYVelocity(-1);
	                if (getY() < 30) {
	                    setYVelocity(0);
	                    e.consume();
	                }
	          }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	                setYVelocity(1);
	                if (getY() + getHeight() > Tennis.WINDOW_HEIGHT - 28) {
	                    setYVelocity(0);
	                }
	                e.consume();
	           }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	                setXVelocity(1);
	                if (getX()+ 40  > 324) {
	                    setX(324-40);
	                    setXVelocity(0);
	                }
	                e.consume();
	           }else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	                setXVelocity(-1);
	                if (getX()  < 0) {
	                    setX(0);
	                    setXVelocity(0);
	                }
	                e.consume();
	            }
	      }
	 
	      public void keyReleased(KeyEvent e) {
	            int keyCode = e.getKeyCode();
	            if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
	                setYVelocity(0);
	            }
	            if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_LEFT) {
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