package Metro;

import java.util.LinkedList;

public class Wagon {
	
	public static final int maxCapacity = 20;
	public String name;
	public boolean main;
	public int capacity;
	public String wagonNumber;
	LinkedList<Passengers> pass;
	public Wagon(String name, boolean main) {
		this.name = name;
		this.main = main;
		capacity = 0;
		pass=new LinkedList<>();
	}
	public boolean main(){
		return main;
	}
	public void setMain(boolean main){
		this.main=main;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWagonNumber() {
		return wagonNumber;
	}
	public void setWagonNumber(String wagonNumber) {
		this.wagonNumber = wagonNumber;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "Wagon [name=" + name + ", main=" + main + ", wagonNumber=" + wagonNumber + "]";
	}
	
	
}
