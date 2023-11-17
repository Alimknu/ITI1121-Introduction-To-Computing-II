/**
 * @author Ali Mirzakhalili
 */
public class Car {

	private CarType type; //Instance variable for storing the type of the car

	private String plateNum; //Instance variable for storing the car plate number

	public CarType getType() { //@return the type of this car
		return this.type;
	}

	public void setType(CarType type) { //Sets the type of the car, @param type is the car type
		this.type = type;
	}

	public String getPlateNum() { //return the plate number
		return this.plateNum;
	}

	public void setPlateNum(String plateNum) { //Sets the car plate number, @param plateNum is the car plate number
		this.plateNum = plateNum;
	}

	public Car(CarType type, String plateNum) { //Constructor for Car, @param type is the type of the car, @param plateNum is the car plate number
		this.type = type;
		this.plateNum = plateNum;
	}

	public String toString() { //Returns a string representation of the car
		return Util.getLabelByCarType(type) + '(' + plateNum + ')';
	}
}