package task1;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class ParkingLot {
	private final ArrayList<Slot> slots;
	private ArrayList<Car> carsToSave;
	private int handicapsSlots;
	private int savedSlots;
	private int regularSlots;
	
	protected final LocalTime  openTime;
	protected final LocalTime  closeTime;
	
	ParkingLot(int numOfSlots, ArrayList<Car> cars) {
		slots = new ArrayList<Slot>();
		carsToSave = cars;
		openTime = LocalTime.parse( "08:00:00" );
		closeTime = LocalTime.parse( "23:00:00" );
		// set the number of slots for each type of slot
		handicapsSlots = numOfHandicapSlots(numOfSlots);
		savedSlots = numOfSavedSlots(numOfSlots);
		regularSlots = numOfRegularSlots(numOfSlots);
		// create the Array of slots
		setSlots(numOfSlots);
		// print the ParkingLot structure
		System.out.println("Handicap slots : " + 0 + "-" + (handicapsSlots-1));
		System.out.println("Saved slots : " + handicapsSlots + "-" + (handicapsSlots + savedSlots-1));
		System.out.println("Regular slots : " + (handicapsSlots + savedSlots) + "-" + (numOfSlots-1));
	}
	
	public ArrayList<Slot> getSlots() {
		return slots;
	}
	
	public int numOfHandicapSlots(int numOfSlots) {
		int num = (int) (numOfSlots * 0.2);
		return num;
	}
	
	public int numOfSavedSlots(int numOfSlots) {
		int num = (int) (numOfSlots * 0.1);
		return num;
	}
	
	public int numOfRegularSlots(int numOfSlots) {
		int num = (int) (numOfSlots - (handicapsSlots + savedSlots));
		return num;
	}
	
	public void initHandicapSlots() {
		for(int i = 0 ; i < handicapsSlots ; i++) {
			HandicapSlot handicapSlot = new HandicapSlot();
			slots.add(handicapSlot);
		}
	}
	public void initSavedSlots() {

		int slotsCounter = 0;
		int j = handicapsSlots;
		if (carsToSave.size() <= savedSlots) {
			while(j < handicapsSlots + savedSlots) {
				for( int i = 0 ; i < carsToSave.size() ; i++  ) {
					if(carsToSave.get(i).getOwner() == Owner.PROFESSOR) { // first save to PROFESSORS
						int ran = randomNumber(1, 3);
						SavedSlot savedSlot = new SavedSlot(ran, carsToSave.get(i));
						slots.add(savedSlot);
						j++;
						slotsCounter++;
					}
				}
				for( int i = 0 ; i < carsToSave.size() ; i++  ) {
					if(carsToSave.get(i).getOwner() == Owner.LECTURER) { // second save to LECTURERS
						int ran = randomNumber(1, 3);
						SavedSlot savedSlot = new SavedSlot(ran, carsToSave.get(i));
						slots.add(savedSlot);
						j++;
						slotsCounter++;
					}
				}
				// if few SavedSlots left empty than turn them into RegularSlots
				int freeSavedSlots = savedSlots - slotsCounter;
				savedSlots -= freeSavedSlots; // updated the number of saved slots
				regularSlots += freeSavedSlots; // add the empty "saved" slots amount into the number of regularSlots
			}
		}
		
		
	}
	
	public void initRegularSlots(int numOfSlots) {
		for(int i = handicapsSlots + savedSlots ; i < numOfSlots ; i++) {
			int ran = randomNumber(1, 3);
			RegularSlot regularSlot = new RegularSlot(ran);
			slots.add(regularSlot);
		}
	}
	
	public void setSlots(int numOfSlots) {
		// init the handicaps slots
		initHandicapSlots();
		// init the saved slots
		initSavedSlots();
		// init the regular slots
		initRegularSlots(numOfSlots);
		
	}
	public int randomNumber(int start, int end) {
		Random ran = new Random();
		int x = ran.nextInt((end - start) + 1) + start;
		return x;
	}
	
//	public int getFreeParkingLot(boolean setRegular, Car car) {
//		int slotIndex = getParkingLot(car);
//		return slotIndex;
//	}
	
	public  int getParkingLot(Car car, boolean setRegular) {
		int freeSlotIndex = -1; // init as "not found" 
		
		if (setRegular) {
			freeSlotIndex = getFreeSpot((handicapsSlots + savedSlots), slots.size(), car); // third check for free regular spot
			
			if (freeSlotIndex >= 0) {
				return freeSlotIndex;
			}
			
			return -1; // if not found
		}
		
		if (car.getIsHandicap()) {
			freeSlotIndex = getFreeSpot(0, handicapsSlots, car); // search free spot between handicaps slots
			if (freeSlotIndex >= 0) {
				return freeSlotIndex;
			}
//			System.out.println("Handicap - No room for car no " + car.getCarNumber());
			throw new IllegalArgumentException("Handicap Slot Error - No room for car no " + car.getCarNumber());
		} else if (car.getOwner() == Owner.PROFESSOR || car.getOwner() == Owner.LECTURER) { // second check if have saved spot
			if (this.carsToSave.contains(car)) {
				for(int i = handicapsSlots ; i < (handicapsSlots + savedSlots) ; i++) { 
					if (car.getCarNumber() == ((SavedSlot) slots.get(i)).getSavedCar().getCarNumber()) {
						if (slots.get(i).getIsFree() && slots.get(i).getCar() == null) {
							freeSlotIndex = i;						
						}
					}
				}
				if (freeSlotIndex >= 0) {
					return freeSlotIndex;
				}
//				System.out.println("Saved -No room for car no " + car.getCarNumber());	
				throw new IllegalArgumentException("Saved Slot Error - No room for car no " + car.getCarNumber());
			}
		}
		
		freeSlotIndex = getFreeSpot((handicapsSlots + savedSlots), slots.size(), car); // third check for free regular spot
		
		if (freeSlotIndex >= 0) {
			return freeSlotIndex;
		}
		
		return -1; // if not found
	}
	public int getFreeSpot(int start, int end, Car car) {
		ArrayList<Integer> freeIndexes = new ArrayList<Integer>();
		for(int i = start ; i < end ; i++) {
			if (slots.get(i).isCarFits(car) && slots.get(i).getCar() == null) {
				
//				freeIndexes.add(i);
				return i;
			}
		}
		return -1;
//		if (freeIndexes.size() == 0) {
//			return -1;			
//		} else {s
//			int ran = randomNumber(0, freeIndexes.size() - 1);
//			return freeIndexes.get(ran);
//		}
	}
}
