package userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.print.DocFlavor.URL;
import javax.swing.JPanel;

import objectgame.Cactus;
import objectgame.Clouds;
import objectgame.EnemyManager;
import objectgame.Land;
import objectgame.MainCharacter;
import util.Resource;

public class GameScreen extends JPanel implements Runnable, KeyListener {
	private static final int GAME_START_STATE=0;
	private static final int GAME_PLAY_STATE=1;
	private static final int GAME_OVER_STATE=2;
	
	public static final float GRAVITY=0.1f;
	public static final float GROUNDY=110;
	private Thread thread;
	private MainCharacter mainCharacter;
	private Land land;
	private Clouds cloud;
	private EnemyManager enemyManager;
	
	private int gameState=GAME_START_STATE;
	private BufferedImage gameOverImage;
	private int score;
	private int highScore;
	
	
	@SuppressWarnings("removal")
	public GameScreen() {
		thread=new Thread(this);
		mainCharacter=new MainCharacter();
		mainCharacter.setX(50);
		mainCharacter.resetY();
		land=new Land(this);
		cloud=new Clouds();
		enemyManager=new EnemyManager(mainCharacter,this,land);
		gameOverImage=Resource.getResourceImage("data/gameover_text.png");
		highScore=0;
		score=0;
	}
	
	public void startGame() {
		thread.start();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				update();
				repaint();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void update() {
//		System.out.println("Update called");
		switch(gameState) {
		case GAME_PLAY_STATE:
			mainCharacter.update();
			land.update();
			cloud.update();	
			enemyManager.update();
			if(!mainCharacter.getAlive()) {
				gameState=GAME_OVER_STATE;
				
			}
			break;
		}
	}
	public void setScore(int score) {
		this.score=score;
	}
	
	public void setHighScore(int highScore) {
		this.highScore=Math.max(this.highScore, highScore);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());
//		System.out.println(score);
		switch(gameState) {
		case GAME_START_STATE:
			mainCharacter.draw(g);
			break;
		case GAME_PLAY_STATE:			
			cloud.draw(g);
			land.draw(g);
			mainCharacter.draw(g);
			enemyManager.draw(g);
			g.drawString("HI "+String.valueOf(highScore), 450, 20);
			g.drawString(String.valueOf(score), 500, 20);
			break;
		case GAME_OVER_STATE:
			cloud.draw(g);
			land.draw(g);
			mainCharacter.draw(g);
			enemyManager.draw(g);
			g.drawString("HI "+String.valueOf(highScore), 450, 20);
			g.drawString(String.valueOf(score), 500, 20);
			g.drawImage(gameOverImage,200,50,null);
			break;
		}
		
	}
	
	private void resetGame() {
		mainCharacter.resetY();
		enemyManager.reset();
		mainCharacter.setAlive(true);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(gameState==GAME_PLAY_STATE) {
			mainCharacter.jump();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if(gameState==GAME_START_STATE) {
				gameState=GAME_PLAY_STATE;
			}
			else if(gameState==GAME_OVER_STATE) {
				resetGame();
				gameState=GAME_PLAY_STATE;
			}
			
			break;
		}
	}

}
