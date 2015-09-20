package GameState;

import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	public int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int SIMGAMESTATE = 1;
	
	public GameStateManager() {
		
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new SimGameState(this));
	}
	
	public void setState(int state){
		currentState = state;
		gameStates.get(currentState).init();
		System.out.println(currentState);
	}
	
	public void update() {
		gameStates.get(currentState).update();		
	}
	
	public void draw(java.awt.Graphics2D g){
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k){
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k){
		gameStates.get(currentState).keyReleased(k);
	}
}