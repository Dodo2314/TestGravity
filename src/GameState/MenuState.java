package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

public class MenuState extends GameState{
	
	private Background bg;
	private String[] options = {"Start", "Help","Quit"};
	private int currentChoice = 0;
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public MenuState(GameStateManager gsm){
		
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/WoodBack.jpg", 1);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 80);
			font = new Font("Arial", Font.PLAIN, 30);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public void init() {
		
	}

	public void update() {
		bg.update();
	}

	public void draw(Graphics2D g) {
		
		//draw bg
		bg.draw(g);
		
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("City Sim", 500, 140);
		
		//Draw menu options
		g.setFont(font);
		for(int i = 0; i< options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.GREEN);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 610, 280 + i * 30);
		}
		
	}

	public void select(){
		if(currentChoice == 0){
			gsm.setState(gsm.SIMGAMESTATE);
			
			//Start
		}
		if(currentChoice == 1){
			//help
		}
		if(currentChoice == 2){
			//exit
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER){
			select();
		}
		if (k == KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN){
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
	}
	
	public void keyReleased(int k) {
		
	}
}