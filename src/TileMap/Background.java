package TileMap;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import SimMain.SimPanel;

public class Background {

	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms){
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y){
		this.x = (x*moveScale) % SimPanel.WIDTH;
		this.y = (y*moveScale) % SimPanel.HEIGTH;
	}
	
	public void setVector(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update(){
		x += dx;
		y += dy;
		//System.out.println("x:"+x+" y:"+y);
	}
	
	public void draw(Graphics2D g){
		g.drawImage(image, (int)x, (int)y, null);
		if(x<0){
			g.drawImage(image, (int)x +SimPanel.WIDTH, (int)y, null);
		}
		if(x>0){
			g.drawImage(image, (int)x -SimPanel.WIDTH, (int)y, null);
		}
	}
}
