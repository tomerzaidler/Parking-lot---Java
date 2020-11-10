package task1;

public abstract class Slot {
	private int size;
	protected Car car;
	protected boolean isFree;
	public String type;
	
	public Slot() {
		isFree = true;
		type = "";
	}
	
	Slot(int newSize) {
		size = newSize;
		isFree = true;
		type = "";
	}
	
	Slot(int newSize, Car newCar) {
		size = newSize;
		car = newCar;
		isFree = true;
		type = "";
	}
	

	// getters
	public int getSize() {
		return size;
	}
	public Car getCar() {
		return car;
	}
	public boolean getIsFree() {
		return isFree;
	}
	// setters
	public void setSize(int size) {
		this.size = size;
	}
	public void setIsFree(boolean isFree) {
		this.isFree = isFree;
	}
	public void setCar(Car car) {
		this.car = null;
		this.car = new Car(car.getCarNumber(), car.getSize(), car.getIsHandicap(), car.getOwner());
	}
	// methods
	public boolean isCarFits(Car car) {
		if(isFree) {
			if(car.getSize() <= size) {
				return true;
			}
		}
		return false;
	}
	public void remove(Car car) {
		if(car.getCarNumber() == this.car.getCarNumber());
		{
			this.car = null;
			this.setIsFree(true);
		}
	}
	
	public abstract boolean add(Car newCar);
}
