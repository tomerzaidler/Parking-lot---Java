package task1;
 
public class Task implements Runnable {
    private Car car;
    private ParkingLot pl;
 
    public Task(ParkingLot pl, Car car) {
        this.car = car;
        this.pl = pl;
    }
 
    public void run() {
        try {

//        	int slotIndex = pl.getParkingLot(car);
//        	if (slotIndex >= 0) {
//        		pl.getSlots().get(slotIndex).add(car);
//        		System.out.println("car no " + car.getCarNumber()+ " " + car.getOwner().toString() + " " + "place " + slotIndex + " " + "size " + pl.getSlots().get(slotIndex).getSize());
//        	} else {
//        		throw new IllegalArgumentException("No room for car no " + car.getCarNumber());
//        	}
        	add(false);
        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        	System.out.println("No room for car no " + car.getCarNumber());
        	System.out.println(e.getMessage());
        	add(true);
        }
    }
    
    public void add(boolean setRegularSlot) {
    	// if SetRegularSlot = true then add immediately to regular slot
    	synchronized(pl) {
    		int slotIndex = pl.getParkingLot(car, setRegularSlot);
        	if (slotIndex >= 0) {
        		boolean check = pl.getSlots().get(slotIndex).add(car);
        		if (check) {
        			System.out.println("car no " + car.getCarNumber()+ " " + car.getOwner().toString() + " " + "place " + slotIndex + " " + "size " + pl.getSlots().get(slotIndex).getSize());            	
        		} else {
//        			System.out.println("ERROR car no " + car.getCarNumber());          			            			
        		}
        	} else {
        		throw new IllegalArgumentException("No room for car no " + car.getCarNumber());
        	}       		
    	}
    }
}
