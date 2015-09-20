package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Citizen.Child;
import SimMain.SimPanel;
import TileMap.Background;

public class SimGameState extends GameState{
	
	private Background bg;
	private int x = 50;
	private int y = 600;
	private int rectSizeX = 20;
	private int rectSizeY = 20;
	private boolean inJump = false;
	private boolean jumpDown = false;
	private boolean crouch = false;
	private boolean jumpFatigue = false;
	private int inJumpCount = 0;
	private int jumpFatigueCount = 0;
	private boolean[] keys = new boolean[4];
	
	private int bx0 = 0;
	private int by0 = 700;
	private int bxs0 = 80;
	private int bys0 = 720-by0;
	
	private int bx1 = 200;
	private int by1 = 670;
	private int bxs1 = 80;
	private int bys1 = 720-by1;
	
	private int bx2 = 500;
	private int by2 = 650;
	private int bxs2 = 40;
	private int bys2 = 720-by2;
	
	private int bx3 = 900;
	private int by3 = 450;
	private int bxs3 = 5;
	private int bys3 = 720-by3;
	
	private boolean onBlock = false;

	public SimGameState(GameStateManager gsm){
		
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/GameBack2.png", 1);
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void init() {
		System.out.println("SIMGAMESTATE");
	}

	public void update() {
		bg.update();
		
		keys();
		isInJump();
		collisionDetection();
		momentum();
		//jumpFatigue();
		//citizenMove(1,0);
		//System.out.println(x);
		
	}

	public void draw(Graphics2D g) {
		
		//draw bg
		bg.draw(g);
		g.setColor(Color.GREEN);
		g.fillRect(x, y, rectSizeX, rectSizeY);
		g.setColor(Color.GRAY);
		g.fillRect(bx0, by0, bxs0, bys0);
		g.setColor(Color.BLACK);
		g.fillRect(bx1, by1, bxs1, bys1);
		g.setColor(Color.BLUE);
		g.fillRect(bx2, by2, bxs2, bys2);
		g.setColor(Color.MAGENTA);
		g.fillRect(bx3, by3, bxs3, bys3);
		win(g);
	}
	
	public void collisionDetection(){
		obstacleRect1(bx0, by0, bxs0, bys0);
		obstacleRect1(bx1, by1, bxs1, bys1);
		obstacleRect1(bx2, by2, bxs2, bys2);
		obstacleRect1(bx3, by3, bxs3, bys3);
		
	}
	
	public void momentum()
	{
		if(y < SimPanel.HEIGTH - rectSizeY && inJump == false){
			y = y + 4;
		}
		if(y > SimPanel.HEIGTH - rectSizeY - 1){
			y = SimPanel.HEIGTH - rectSizeY;
		}
	}
	
	public void obstacleRect1(int bx, int by, int bxs, int bys)
	{
		//System.out.println("bx: "+bx+" by: "+by+" x: "+x+" y: "+y+" bxs: "+bxs+" bys: "+bys+" rectSizeX: "+rectSizeX);
		if(x > bx - rectSizeX && x < bx && y > by){
			//Kollision Rechts
			x = bx - rectSizeX;
		}
		if(x < bx + bxs && x > bx + 1 && y > by){
			//Kollision Links
			x = bx + bxs;
		}
		if(x > bx - rectSizeX && x < bx + bxs  && y > by - rectSizeY ){
			//Kollision oben
			y = SimPanel.HEIGTH - (bys+rectSizeY);
		}
	}
	
	public void isInJump(){
		if(inJumpCount < 25 && inJump == true){
			y = y - 4;
			inJumpCount++;
			//System.out.println("up: "+y);
		}
		else if(inJumpCount >= 25 && inJumpCount < 50 && inJump == true){
			y = y + 4;
			inJumpCount++;
			//System.out.println("down: "+y);
		}
		else if(inJumpCount >= 50 && inJump == true){
			inJumpCount = 0;
			inJump = false;
		}
	}
	
	public void jumpFatigue(){
		if(jumpFatigue == true){
			jumpFatigueCount++;
		}
		if(jumpFatigueCount == 60){
			jumpFatigue = false;
			jumpFatigueCount = 0;
		}
	}
	
	public void win(Graphics2D g){
		if(y >= SimPanel.HEIGTH - rectSizeY){
			x = 50;
			y = 684;
		}
		if(x >= 880){
			g.drawString("You win", 640, 360);
			y = 650;
		}
	}
	
	public void addCitizenChild(){
		Child ch1 = new Child(1, "Rob", 10, "Student", 100);
	}
	
	public void citizenMove(int dx, int dy) {
		x += dx;
		if(x > SimPanel.WIDTH - rectSizeX){
			x = SimPanel.WIDTH - rectSizeX;
		}
		else if(x < 0){
			x = 0;
		}
		y += dy;
		if(y > SimPanel.HEIGTH - rectSizeY){
			y = SimPanel.HEIGTH - rectSizeY;
			//System.out.println("up: "+y);
		}
		else if(y < 0){
			y = 0;
			
		}
		//System.out.println("Citizen 1 green x: "+x+" y: "+y);
		
	}

	public void keys()
	{
		int pixMove = 5;
		
		if(keys[0] == true){
			
			if(inJump == false){
				rectSizeY = 20;
				inJump = true;
				jumpFatigue = true;
			}
		}
		if(keys[1] == true){
			citizenMove(-pixMove,0);
		}
		if(keys[2] == true){
			rectSizeY = 10;
			citizenMove(0,10);
			crouch = true;
		}
		if(keys[2] == false && crouch == true){
			rectSizeY = 20;
			citizenMove(0, 10);
			crouch = false;
		}
		if(keys[3] == true){
			citizenMove(pixMove,0);
		}
	}
	
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_W || k == KeyEvent.VK_SPACE)
		{
			keys[0] = true;
		}
		
		if(k == KeyEvent.VK_A )
		{
			keys[1] = true;
		}
		if(k == KeyEvent.VK_S )
		{
			keys[2] = true;
		}
		if(k == KeyEvent.VK_D )
		{
			keys[3] = true;
		}
		if(k == KeyEvent.VK_ENTER )
		{
			gsm.setState(gsm.MENUSTATE);
		}
		
	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_W || k == KeyEvent.VK_SPACE)
		{
			keys[0] = false;
		}
		
		if(k == KeyEvent.VK_A )
		{
			keys[1] = false;
		}
		if(k == KeyEvent.VK_S )
		{
			keys[2] = false;
		}
		if(k == KeyEvent.VK_D )
		{
			keys[3] = false;
		}
		
	}

}
