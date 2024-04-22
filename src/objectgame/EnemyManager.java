package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import userinterface.GameScreen;
import util.Resource;

public class EnemyManager {
	private List<Enemy> enemies;
	private Random random;
	private MainCharacter mainCharacter;
	private BufferedImage imageCactus1,imageCactus2;
	private GameScreen gameScreen;
	private Land land;
	private int score=0;
	
	public EnemyManager(MainCharacter mainCharacter, GameScreen gameScreen, Land land) {
		this.gameScreen=gameScreen;
		this.mainCharacter=mainCharacter;
		this.land=land;
		enemies=new ArrayList<Enemy>();
		random=new Random();
		imageCactus1=Resource.getResourceImage("data/cactus1.png");
		imageCactus2=Resource.getResourceImage("data/cactus2.png");
		Cactus cactus=getRandomCactus();
		enemies.add(cactus);		
	}
	
	public void update() {
		for(Enemy e: enemies) {
			e.update();
		}
		if(enemies.get(0).isOutOfScreen()) {
			enemies.remove(0);
			enemies.add(getRandomCactus());
			
		}
		if(enemies.get(0).getBound().intersects(mainCharacter.getBound())) {
			mainCharacter.setAlive(false);
			gameScreen.setHighScore(score);
		}
		if(mainCharacter.getAlive()) {
			score=land.getPosX();
			gameScreen.setScore(score);
		}
	}
	
	public void draw(Graphics g) {
		for(Enemy e:enemies) {
			e.draw(g);
		}
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(getRandomCactus());
		land.setPosX(0);
	}
	
	private Cactus getRandomCactus() {
		int i=random.nextInt(2);
		switch(i) {
		case 1:
			return new Cactus(65,imageCactus1,mainCharacter);
		default:
			return new Cactus(75,imageCactus2,mainCharacter);
		}
	}
	
}
