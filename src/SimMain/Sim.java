package SimMain;

import javax.swing.JFrame;

public class Sim {
	
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Dragon Tale");
		window.setContentPane(new SimPanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocation(240, 120);
		window.pack();
		window.setVisible(true);
		
	}
}