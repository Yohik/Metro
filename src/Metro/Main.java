package Metro;

public class Main {

	public static void main(String[] args) {
		Metro metro = new Metro();
		metro.createWagons();
		metro.createTrains();
		System.out.println("--------------------");
		metro.createLines();
		metro.createLineTrains();
		metro.createLineStations();
		System.out.println(metro.metro);
		System.out.println("--------------------");
		metro.passengersInOut();
		
		
		
	}

}
