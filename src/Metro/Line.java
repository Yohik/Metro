package Metro;

import java.util.LinkedList;

public class Line {
	String name;
	LinkedList<Train> lineTrain;
	LinkedList<Station> lineStation;
	public Line(String name) {
		this.name = name;
		this.lineStation = new LinkedList<Station>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LinkedList<Train> getLineTrain() {
		return lineTrain;
	}
	public void setLineTrain(LinkedList<Train> lineTrain) {
		this.lineTrain = lineTrain;
	}
	public LinkedList<Station> getLineStation() {
		return lineStation;
	}
	public void setLineStation(LinkedList<Station> lineStation) {
		this.lineStation = lineStation;
	}
	@Override
	public String toString() {
		return "Line [name=" + name + ", lineTrain=" + lineTrain + " lineStation=" + lineStation + "]\n";
	}
	
}
