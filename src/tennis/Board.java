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
	    Player player1 = new Player(this);
	    //Player player2 = new Player(this);
	    Ball ball = new Ball(this);
	    Player2 player2 = new Player2(this);
	    Image bg = new ImageIcon("src/Resource/tennis-court.jpg").getImage();
	    Boolean playerServe = false;
	    
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
	        //Thread Machine = new Thread(computer);
	        //Machine.start();
	    }
	 
	    public void paint(Graphics g) {
	    	g.drawImage(bg, 0,0,1000,480, null);
	        player1.paint(g);
	        player2.paint(g);
	        ball.paint(g);
	        //computer.paint(g);
	    }	 
	    public Ball getBall() {
	        return ball;
	    }
	     
	    public Player getPlayer() {
	        return player1;
	    }
	    
	    //added by Lee
	    public Player2 getPlayer2()
	    {
	    	return player2;
	    }
	  
	 
	    public void actionPerformed(ActionEvent e) {
	        repaint();
	    }
	     
	    public class Konan extends KeyAdapter {
	        @Override
	        public void keyPressed(KeyEvent e){
	            player1.keyPressed(e);
	            player2.keyPressed(e);
	        }
	        @Override
	        public void keyReleased(KeyEvent e){
	            player1.keyReleased(e);
	            player2.keyReleased(e);
	        }
	        public void keyTyped(KeyEvent e){
	            ball.keyTyped(e);
	        }
	    }
	 
	}