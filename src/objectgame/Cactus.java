package objectgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Resource;

public class Cactus extends Enemy{
	
	private BufferedImage image;
	private int posX,posY;
	private Rectangle rect;
	private MainCharacter mainCharacter;
	private boolean isScoreGet=false;
	
	public Cactus(MainCharacter mainCharacter) {
		this.mainCharacter=mainCharacter;
		image=Resource.getResourceImage("data/cactus1.png");
		posX=200;
		posY=65;
		rect=new Rectangle();
	}
	public Cactus(int posY,BufferedImage image,MainCharacter mainCharacter) {
		this.mainCharacter=mainCharacter;
		this.posX=600;
		this.posY=posY;
		this.image=image;
		rect=new Rectangle();
	}
	
	public void update() {
		posX-=3;
		rect.x=posX;
		rect.y=posY;
		rect.width=image.getWidth();
		rect.height=image.getHeight();
	}
	
	public Rectangle getBound() {
		return rect;
	}
	
	@Override
	public void draw(Graphics g) {
//		g.drawRect(rect);
		g.drawImage(image, posX, posY, null);
	}
	
	@Override
	public boolean isOutOfScreen() {
		return (posX+image.getWidth())<0;
	}
	@Override
	public boolean isOver() {
		return (mainCharacter.getX()>posX);
	}
	@Override
	public boolean isScoreGot() {
		return isScoreGet;
	}
	@Override
	public void setScoreGot(boolean isScoreGet) {
		this.isScoreGet=isScoreGet;
		
	}
	
}
