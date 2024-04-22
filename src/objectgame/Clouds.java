package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import util.Resource;

public class Clouds {
	private List<Cloud> clouds;
	
	private BufferedImage cloudImage;
	
	public Clouds() {
		cloudImage=Resource.getResourceImage("data/cloud.png");
		clouds=new ArrayList<Cloud>();
		
		Cloud cloud1=new Cloud();
		cloud1.posX=100;
		cloud1.posY=50;
		clouds.add(cloud1);
		
		cloud1=new Cloud();
		cloud1.posX=200;
		cloud1.posY=30;
		clouds.add(cloud1);
		
		cloud1=new Cloud();
		cloud1.posX=300;
		cloud1.posY=80;
		clouds.add(cloud1);
		
		cloud1=new Cloud();
		cloud1.posX=450;
		cloud1.posY=50;
		clouds.add(cloud1);
		
		cloud1=new Cloud();
		cloud1.posX=600;
		cloud1.posY=60;
		clouds.add(cloud1);
	}
	public void update() {
		for(Cloud cloud:clouds) {
			cloud.posX--;
		}
		Cloud firstCloud=clouds.get(0);
		if(firstCloud.posX+cloudImage.getWidth()<0) {
			clouds.remove(firstCloud);
//			firstCloud.posX=clouds.get(clouds.size()-1).posX+cloudImage.getWidth();
			
			firstCloud.posX=600;
			clouds.add(firstCloud);
		}
	}
	public void draw(Graphics g) {
		for(Cloud cloud:clouds) {
			
			g.drawImage(cloudImage, (int)cloud.posX, (int)cloud.posY, null);
		}
	}
	
	private class Cloud{
		float posX;
		float posY;
	}
}
