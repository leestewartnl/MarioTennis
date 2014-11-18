package tennis;
import javax.swing.JFrame;
	 
	 
	public class Tennis extends JFrame{
	    final static int WINDOW_WIDTH = 1000;
	    final static int WINDOW_HEIGHT = 480;
		
	    static boolean stop = false;
	    public Tennis() {
	        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	        setResizable(false);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        Board game = new Board();
	        add(game);
	        game.update();
	        setVisible(true);
	    }
	 
	    public static void main(String[] args) {
	      new Tennis();
	    }
	     
	}