package userinterface;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	public static final int SCREEN_WIDTH=600;
	private GameScreen gameScreen;
	
	public GameWindow() {
		super("Java T-Rex game"); // sets title of frame
//		setTitle("TRex");
		setSize(SCREEN_WIDTH,175);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400,200);
		setResizable(false);
		
		
		gameScreen=new GameScreen();
		add(gameScreen);
		addKeyListener(gameScreen);
	}

	public void startGame() {
		gameScreen.startGame();
	}
	public static void main(String[] args) {
		GameWindow gw=new GameWindow();
		gw.setVisible(true);
		gw.startGame();
	}

}
