package objectgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Animation;
import util.Resource;

import static userinterface.GameScreen.GRAVITY;
import static userinterface.GameScreen.GROUNDY;
public class MainCharacter {

	private float x=0,y=0;
	private float speedY=0;
	private Animation characterRun;
	private Rectangle rect;
	private boolean isAlive=true;
	
	public MainCharacter() {
		characterRun=new Animation(200);
		characterRun.addFrame(Resource.getResourceImage("data/main-character1.png"));
		characterRun.addFrame(Resource.getResourceImage("data/main-character2.png"));
		resetY();
		rect=new Rectangle();
	}
	public void update() {
		characterRun.update();
		if(y>=GROUNDY-characterRun.getFrame().getHeight()) {
			y=GROUNDY-characterRun.getFrame().getHeight();
			speedY=0;
		}
		else {
			speedY+=GRAVITY;
			y+=speedY;
		}
		rect.x=(int)x;
		rect.y=(int)y;
		rect.width=characterRun.getFrame().getWidth();
		rect.height=characterRun.getFrame().getHeight();
	}
	
	public Rectangle getBound() {
		return rect;
	}
	public void draw(Graphics g) {
		g.setColor(Color.black);
		// coordinates x,y,width, height of rect
//		g.drawRect((int)x, (int)y, characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());
		g.drawImage(characterRun.getFrame(), (int)x, (int)y, null);
	}
	
	public void jump() {
		speedY=-4;
		y+=speedY;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x=x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y=y;
	}
	public void resetY() {
		this.y=GROUNDY-characterRun.getFrame().getHeight();
	}
	public float getSpeedY() {
		return speedY;
	}
	public void setSpeedY(float speedY) {
		this.speedY=speedY;
	}
	
	public void setAlive(boolean alive) {
		isAlive=alive;
	}
	public boolean getAlive() {
		return isAlive;
	}
}
