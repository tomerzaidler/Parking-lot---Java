package task1;

import java.time.LocalTime;

public class RegularSlot extends Slot {
	
	LocalTime  startTime;
	LocalTime  endTime;
	
	RegularSlot(int newSize) {
		super(newSize);
		setSize(newSize);
		startTime = LocalTime.parse( "08:00:00" );
		endTime = LocalTime.parse( "23:00:00" );
		type = "regular";
	}

	@Override
	public boolean add(Car newCar) {
		LocalTime current = LocalTime.now();
		if(checkTime(current)) {
			this.setCar(newCar);
			this.setIsFree(false);
			return true;
		}
		return false;
	}
	
	public boolean checkTime(LocalTime currentTime) {
		if(currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
			return true;
		}
		return false;
	}
}
