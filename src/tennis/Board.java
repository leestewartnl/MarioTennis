package tennis;
	 
	import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
	 
	public class Board extends JPanel implements ActionListener{
		
		// moved this from Player
	    Image p1NoHit = new ImageIcon("src/Resource/player1.png").getImage();
	    Image p1Hit = new ImageIcon("src/Resource/player1hit.png").getImage();
	    
	    // moved this from Player2
	    Image p2NoHit = new ImageIcon("src/Resource/player2.png").getImage();
	    Image p2Hit = new ImageIcon("src/Resource/player2hit.png").getImage();
		boolean play1 = true;
	    Player player1 = new Player(this, p1NoHit, p1Hit, 0, play1);
	    boolean play2 = false;	// why can't you change play1 to false. very weird.
	    Player player2 = new Player(this, p2NoHit, p2Hit, Tennis.WINDOW_WIDTH-150, play2);
	    //Player player2 = new Player(this);
	    Ball3 ball = new Ball3(this, player1, player2);
	    
	    Image bg = new ImageIcon("src/Resource/tennis-court.jpg").getImage();

	    boolean playerServe = false;
	    
	    
	    
	    Thread tennisBall;
	    Thread Human1;
	    Thread Human2;
	     
	    public Board(){
	        Timer time = new Timer(50, this);
	        time.start();
	        this.addKeyListener(new Konan());
	        this.setFocusable(true);
 
	    }
	     
	    public void update(){
	        tennisBall = new Thread(ball);
	        tennisBall.start();
	        Human1 = new Thread(player1);
	        Human1.start();
	        Human2 = new Thread(player2);
	        Human2.start();
	    }
	 
	    public void paint(Graphics g) {
	    	g.drawImage(bg, 0,0,1000,480, null);
	        player1.paint(g);
	        player2.paint(g);
	        ball.paint(g);
	        //computer.paint(g);
	    }	 
	    public Ball3 getBall() {
	        return ball;
	    }
	    
	    //moved this to Player class where it should have been
	    /*
	    public Player getPlayer() {
	        return Player.getPlayer(); //player1;
	    	//return this;
	    }
	    */
	    
	    //added by Lee
	    /*
	    public Player2 getPlayer2()
	    {
	    	return player2;
	    }
	  */
	 
	    public void actionPerformed(ActionEvent e) {
	        repaint();
	    }
	     
	    public class Konan extends KeyAdapter {
	        @Override
	        public void keyPressed(KeyEvent e){
	            player1.keyPressed(e);
	            player2.keyPressed(e);
	            ball.keyPressed(e);
	        }
	        @Override
	        public void keyReleased(KeyEvent e){
	            player1.keyReleased(e);
	            player2.keyReleased(e);
	        }
	        
	    }
	 
	}