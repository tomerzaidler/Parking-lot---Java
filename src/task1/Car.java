package task1;

import java.util.Random;

public class Car {
	private final int carNumber;
	private int size;
	private boolean isHandicap;
	private Owner owner;
	
	Car(int number) { // creates random car!
		/*
		 * * Generate random size, isHandicap and owner. 
		 */
		Owner[] owners = {Owner.PROFESSOR, Owner.LECTURER, Owner.STUDENT, Owner.VISITOR}; 
		carNumber = number; // set the carNumber
		size = randomNumber(1, 3); // set the size
		int randomOwner = randomNumber(0,3);
		owner = owners[randomOwner]; // set the owner
		int randomHandicap = randomNumber(0,1);
		isHandicap = randomHandicap == 1 ? true : false; // set isHandicap
	}

	Car(int number, int carSize, boolean handicap, Owner person) {
		carNumber = number;
		size = carSize;
		isHandicap = handicap;
		owner = person;
	}

	// getters
	public int getCarNumber() {
		return carNumber;
	}

	public int getSize() {
		return size;
	}

	public boolean getIsHandicap() {
		return isHandicap;
	}

	public Owner getOwner() {
		return owner;
	}

	// setters
	public void setSize(int size) {
		this.size = size;
	}

	public void setHandicap(boolean isHandicap) {
		this.isHandicap = isHandicap;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public int randomNumber(int start, int end) {
		Random ran = new Random();
		int x = ran.nextInt((end - start) + 1) + start;
		return x;
	}
}
