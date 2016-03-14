package Metro;

import java.util.concurrent.LinkedBlockingQueue;

public class Station {
	final static int maxPass = 500;
	String name;
	LinkedBlockingQueue<Passengers> pass;
	public Station(String name) {
		super();
		this.name = name;
		this.pass = new LinkedBlockingQueue<Passengers>(maxPass);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LinkedBlockingQueue<Passengers> getPass() {
		return pass;
	}
	public void setPass(LinkedBlockingQueue<Passengers> pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "Station [name=" + name + ", pass=" + pass + "]";
	}
	
}
