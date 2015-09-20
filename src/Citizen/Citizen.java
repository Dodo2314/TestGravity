package Citizen;

public interface Citizen {
	
	public abstract int getId();
	public abstract void setName(String name);
	public abstract String getName();
	public abstract void setAge(int age);
	public abstract int getAge();
	public abstract String getAgeGroup();
	public abstract void setJob(String job);
	public abstract String getJob();
	public abstract void setHouseId(int houseId);
	public abstract int getHouseId();
}
