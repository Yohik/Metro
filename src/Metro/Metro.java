package Metro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;


public class Metro {
	public final static int wagonInDepot = 100;
	LinkedList<Wagon> depot;
	LinkedList<Train> trains;
	Line redLine, blueLine, greenLine;
	BlockingQueue<Driver> driverQueue;
	private Random rnd;
	private Comparator<Driver> comparator;
	ArrayList<Line> metro;
	LinkedList<Passengers> passInStation;
	private ArrayList<Thread> threads;
	
	public Metro(){
		this.rnd = new Random();
		this.metro = new ArrayList<Line>();
		this.depot = new LinkedList<Wagon>();
		this.trains = new LinkedList<Train>();
		this.passInStation = new LinkedList<Passengers>();
	}
	
	public void createWagons(){
		for (int i = 0; i < wagonInDepot; i++) {
			Wagon wgn = new Wagon("Wagon " + i, (rnd.nextInt(100)<30));
			depot.add(wgn);
		}
	}
	
	public void createTrains(){
		int maxTrains = depot.size()/Train.maxWagon;
		for (int i = 0; i < maxTrains; i++) {
			Train tr = new Train("Train " + i, " " + i);
			while(!depot.isEmpty()){
				tr.addWagon(depot.poll());
				if(tr.size==Train.maxWagon)
					break;
			}
			if(tr.ready)
				trains.add(tr);
		}
		System.out.println(trains);
	}
	
	public void createLines(){
		redLine = new Line("Red");
		blueLine = new Line("Blue");
		greenLine = new Line("Green");
		
		metro.add(redLine);
		metro.add(blueLine);
		metro.add(greenLine);
	}
	
	public void createLineTrains(){
		redLine.setLineTrain(new LinkedList<Train>());
		blueLine.setLineTrain(new LinkedList<Train>());
		greenLine.setLineTrain(new LinkedList<Train>());
		
		Iterator<Train> iter = trains.iterator();
		
		while(iter.hasNext()){
			redLine.lineTrain.add(iter.next());
			if(iter.hasNext())
				blueLine.lineTrain.add(iter.next());
			if(iter.hasNext())
				greenLine.lineTrain.add(iter.next());
		}
	}
	public void manageDriverQueue (){
		comparator = new Comparator<Driver>() {
			@Override
			public int compare(Driver d1, Driver d2){
				if(d1.getExp() > d2.getExp()){
				return -1;
				}
				if(d1.getExp()<d2.getExp()){
					return 1;
				}
				return 0;
			}
		};
		driverQueue = new PriorityBlockingQueue<Driver>(5, comparator);
		for (int i = 0; i < 5; i++) {
			Driver d =new Driver("Name " + i, rnd.nextInt(100));
			driverQueue.add(d);
		}
		Iterator<Train> iter = trains.iterator();
		Driver d = null;
		while(iter.hasNext()){
			Train train = iter.next();
			try {
				d = driverQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			d.setCurTrain(train);
			doThreadsDrive(d);
			iter.remove();
		}
		for(Thread curThread: getThreads()){
			try {
				curThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void doThreadsDrive(Driver d){
		Random rnd = new Random();
		int km = rnd.nextInt(50);
		Thread th = new Thread(new Runnable(){
			public void run(){
				System.out.println("Driver " + d.name + " - " + d.exp + " run " + km + 
						" on the " + d.curTrain.name + " train");
			d.changeExp(km);
			try {
				Thread.sleep(rnd.nextInt(5) * 1000);
				driverQueue.put(d);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		th.start();
	}
	public ArrayList<Thread> getThreads() {
		return threads;
	}
	public void createLineStations(){
		for (int i = 0; i < 10; i++) {
			redLine.lineStation.add(new Station(redLine.name + "Station " + i));
			greenLine.lineStation.add(new Station(greenLine.name +"Station " + i));
			blueLine.lineStation.add(new Station(blueLine.name + "Station " + i));
		}
	}
	private void passengersEnterStations(){
		Thread thread = new Thread(new Runnable(){
			Random rnd = new Random();
			public void run(){
				for(Line line : metro){
					for(Station station : line.lineStation){
						for (int i = 0; i < Station.maxPass; i++) {
							try {
								station.pass.put(new Passengers("Passenger " + rnd.nextInt(100)));
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
		thread.start();
	}
	private void runTrainThread(Line line, Train train){
		Thread thread = new Thread(new Runnable(){
			Random rnd = new Random();
			int cntToOperate = 0, resultOper = 0;
			 public void run(){
				 for(Station station : line.lineStation){
					 cntToOperate = rnd.nextInt(50);
					for(Wagon wgn : train.wagon){
						System.out.println(" " + "Line - " +line.name + "; " + station.name + "; " + train.name + "; " + wgn.name + "; Pass - " + wgn.pass.size());
						resultOper = 0;
						if(!wgn.pass.isEmpty()){
							Iterator<Passengers> passIter = wgn.pass.iterator();
							cntToOperate = rnd.nextInt(20);
							while(passIter.hasNext() && cntToOperate > 0){
								wgn.pass.removeFirst();
								cntToOperate--;
								resultOper++;
							}
							System.out.println(" pass out " + resultOper + " ");
						}
						resultOper = 0;
						while(wgn.pass.size() < Wagon.maxCapacity){
							try {
								wgn.pass.add(station.pass.take());
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							resultOper++;
						}
						System.out.println(" pass in  " + resultOper + " ");
						System.out.println("");
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			 }
		});
		thread.start();
	}
	public void passengersInOut(){
		passengersEnterStations();
		for(Line line : metro){
			Iterator<Train> iterTrain = line.getLineTrain().iterator();
			while(iterTrain.hasNext()){
				Train train = iterTrain.next();
				runTrainThread(line, train);
			}
		}
	}
}
