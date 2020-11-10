package task1;

public class HandicapSlot extends Slot {

	HandicapSlot() {
		super();
		setSize(3);
		type = "handicap";
	}

	@Override
	public boolean add(Car newCar) {
		if(newCar.getIsHandicap()) {
			setCar(newCar);
			this.setIsFree(false);
			return true;
		}
		return false;
	}
}
