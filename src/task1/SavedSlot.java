package task1;

public class SavedSlot extends Slot {
	
	private final Car savedCar;
	
	SavedSlot(int newSize, Car savedToCar) {
		super(newSize);
		setSize(newSize);
		validateSavedCar(savedToCar);
		savedCar = savedToCar;
		type = "saved";
	}
	// get
	public Car getSavedCar() {
		return savedCar;
	}
	
	private void validateSavedCar(Car savedToCar) {
		try {
			if(savedToCar.getOwner() != Owner.LECTURER && savedToCar.getOwner() != Owner.PROFESSOR) {
				throw new VerifyError("Cannot create saved slot: Owner is not a lecturer or professor");
			}
		} catch(VerifyError err) {
			System.out.println(err.getMessage());
		}
	}
	
	@Override
	public boolean add(Car newCar) {
		if (newCar.getOwner() == Owner.LECTURER || newCar.getOwner() == Owner.PROFESSOR) {
			if(newCar.getCarNumber() == savedCar.getCarNumber()) {
				setCar(newCar);
				this.setIsFree(false);
				return true;
			}			
		}				
		return false;
	}

}
