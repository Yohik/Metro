package Metro;

import java.util.Random;

public class Driver {
	String name;
	int exp;
	Train curTrain;
	public Driver(String name, int exp) {
		super();
		this.name = name;
		this.exp = exp;
	}
	public void changeExp(int km){
		Random rd=new Random();
		this.exp += rd.nextInt(km);
	}
	
	public void driving(){
		changeExp(100);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public Train getCurTrain() {
		return curTrain;
	}
	public void setCurTrain(Train curTrain) {
		this.curTrain = curTrain;
		curTrain.setDriver(this);
	}
	@Override
	public String toString() {
		return "Driver [name=" + name + ", exp=" + exp + ", curTrain=" + curTrain + "]";
	}
	
}
