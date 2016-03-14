package Metro;

import java.util.LinkedList;


public class Train {
	
	public static final int maxWagon = 5;
	public String name;
	public String number;
	public int size;
	public boolean ready;
	Driver driver;
	public LinkedList<Wagon> wagon;
	public Train(String name, String number) {
		super();
		this.name = name;
		this.number = number;
		this.size = 0;
		wagon = new LinkedList<>();
		ready = false;
	}
	public boolean firstMain(){
		return ((wagon.size() > 0)&&(wagon.getFirst().main));
	}
	public boolean lastMain(){
		return ((wagon.size() > 0) && (wagon.getLast().main));
	}	
	public void addWagon(Wagon w){
		if(this.size<maxWagon){
			if(w.main & !firstMain())
				wagon.addFirst(w);
			else {
				if(w.main & !lastMain())
					wagon.addLast(w);
				else{
					if(!firstMain() || !lastMain())
						wagon.add(w);
					else 
						wagon.add(1,w);
				} 
			}
			renumberWagons();
			this.size = wagon.size();
			ready = firstMain() & lastMain() & (size == maxWagon);
		}
		else 
			System.out.println("Not ready");
	}
	public void renumberWagons() {
		for (Wagon wgn : wagon) {
			wgn.setWagonNumber(this.number + "" + wagon.indexOf(wgn));
		}
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public int passCount(){
		int c=0;
		for (Wagon w : wagon) 
			c +=w.pass.size();
			return c;
	}
	@Override
	public String toString() {
		return "Train [name=" + name + ", ready=" + ready + ", wagon=" + wagon
				+ "]\n";
	}
	
}

