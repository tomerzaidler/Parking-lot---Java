package task1;

/**
 * @author Tomer Zaidler - 312468549
 */

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Car> saveToCars = new ArrayList<Car>();
		Car car1 = new Car(1, 1, false, Owner.PROFESSOR);
		Car car2 = new Car(2, 3, false, Owner.PROFESSOR);
		Car car3 = new Car(3, 3, false, Owner.PROFESSOR);
//		Car car4 = new Car(4, 1, false, Owner.STUDENT);
		saveToCars.add(car1);
		saveToCars.add(car2);
		saveToCars.add(car3);
		final ParkingLot pl = new ParkingLot(250, saveToCars);
//		pl.getSlots().get(0).add(car3);
//		System.out.println(pl.getSlots().get(0).getCar().getCarNumber());
		ArrayList<Car> carsToPark = new ArrayList<Car>();
		
		for(int i = 0; i < 200; i++) { // set the carsToPark Array
			Car car = new Car(i + 1); // this Car constructor will create a random car!
			carsToPark.add(car);
		}
		// set max 3 workers
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		
		for(int i = 0; i < carsToPark.size(); i++) { 
			Task task = new Task(pl, carsToPark.get(i));
 
            executor.execute(task);
		}
		executor.shutdown();
	}

}
