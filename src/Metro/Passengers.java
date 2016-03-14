package Metro;

public class Passengers {
	String name;
	private int id;
	private static int idCounter = 0;
	public Passengers(String name) {
		this.name = name;
		this.id=idCounter++;
	}
	public Passengers(){
		this.id=idCounter++;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Passengers [name=" + name + id+ "]";
	}
	
}
