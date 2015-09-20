package Citizen;

import java.awt.Color;
import java.awt.Graphics2D;

public class Child implements Citizen{

	private int id;
	private String name;
	private int age;
	private String ageGroup = "Child";
	private String job;
	private int houseId;
	private static int x = 640;
	private static int y = 360;
	private static int limit = 0;
	private static int[] dy = new int[4];
	
	public Child(int id, String name, int age, String job, int houseId){
		
		this.id = id;
		setName(name);
		setAge(age);
		setJob(job);
		setHouseId(houseId);
		int[] dy = new int[4];
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
		}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public String getAgeGroup() {
		return ageGroup;
	}
	
	public void setJob(String job) {
		this.job = job;
	}

	public String getJob() {
		return job;
	}

	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}


	public int getHouseId() {
		return houseId;
	}
	
	public static void drawChild(Graphics2D g){
		setCoordinates();
		g.setColor(Color.RED);
		g.fillRect(x, y, 20, 20);
		
	}
	
	public static void setCoordinates(){
		if(limit == 0){
			int dx = (int) (Math.random() * 4);
			if(dx == 0){x = x -1; dy[0]++;}
			if(dx == 1){x = x +1; dy[1]++;}
			if(dx == 2){y = y -1; dy[2]++;}
			if(dx == 3){y = y +1; dy[3]++;}
			System.out.println("0: "+dy[0]+" 1:"+dy[1]+" 2:"+dy[2]+" 3:"+dy[3]);
			//limit = 0;
		}
		//limit++;
		
	}

}
