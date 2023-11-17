import java.io.File;
import java.util.Scanner;

/**
 * @author Ali Mirzakhalili
 */
public class ParkingLot {

	private static final String SEPARATOR = ","; //The delimiter that separates values

	private static final String SECTIONER = "###"; //The delimiter that separates the parking lot design section from the parked car data section

	private int numRows;  //Instance variable for storing the number of rows in a parking lot

	private int numSpotsPerRow; //Instance variable for storing the number of spaces per row in a parking lot

	private CarType[][] lotDesign; //Instance variable (two-dimensional array) for storing the lot design

	private Car[][] occupancy; //Instance variable (two-dimensional array) for storing occupancy information for the spots in the lot

	/**
	 * Constructs a parking lot by loading a file
	 * 
	 * @param strFilename is the name of the file
	 */
	public ParkingLot(String strFilename) throws Exception {

		if (strFilename == null) {
			System.out.println("File name cannot be null.");
			return;
		}

		// determine numRows and numSpotsPerRow; you can do so by
		// writing your own code or alternatively completing the 
		// private calculateLotDimensions(...) that I have provided
		calculateLotDimensions(strFilename);

		// instantiate the lotDesign and occupancy variables!
		// WRITE YOUR CODE HERE!
		lotDesign = new CarType[numRows][numSpotsPerRow];
		occupancy = new Car[numRows][numSpotsPerRow];

		// populate lotDesign and occupancy; you can do so by
		// writing your own code or alternatively completing the 
		// private populateFromFile(...) that I have provided
		populateFromFile(strFilename);
	}

	/**
	 * Parks a car (c) at a give location (i, j) within the parking lot.
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @param c is the car to be parked
	 */
	public void park(int i, int j, Car c) {
		if (canParkAt(i,j,c) == true){
			occupancy[i][j] = c;
		}
	}

	/**
	 * Removes the car parked at a given location (i, j) in the parking lot
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return the car removed; the method returns null when either i or j are out
	 *         of range, or when there is no car parked at (i, j)
	 */
	public Car remove(int i, int j) {
		Car removed = occupancy[i][j];
		occupancy[i][j] = null;
		return removed;
	}

	/**
	 * Checks whether a car (which has a certain type) is allowed to park at
	 * location (i, j)
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return true if car c can park at (i, j) and false otherwise
	 */
	public boolean canParkAt(int i, int j, Car c) {
		if (i < occupancy.length){ 
			if (j < occupancy[i].length){
				if (occupancy[i][j] == null){
					if (Util.getLabelByCarType(lotDesign[i][j]) != "N"){
						if (Util.getLabelByCarType(c.getType()) == "E"){
							return true;
						}
						if (Util.getLabelByCarType(c.getType()) == "S"){
							if (Util.getLabelByCarType(lotDesign[i][j]) == "S" || Util.getLabelByCarType(lotDesign[i][j]) == "R" || Util.getLabelByCarType(lotDesign[i][j]) == "L"){
								return true;
							}
						}
						if (Util.getLabelByCarType(lotDesign[i][j]) == "R"){
							if (Util.getLabelByCarType(lotDesign[i][j]) == "R" || Util.getLabelByCarType(lotDesign[i][j]) == "L"){
								return true;
							}
						}
						else{
							if (Util.getLabelByCarType(lotDesign[i][j]) == "L"){
								return true;
							}
						}
					}
				}
			}
		}
		System.out.println("Car " + c.toString() + " cannot be parked at " + "(" + i + "," + j + ")");
		return false;
	}

	/**
	 * @return the total capacity of the parking lot excluding spots that cannot be
	 *         used for parking (i.e., excluding spots that point to CarType.NA)
	 */
	public int getTotalCapacity() {
		int cap = 0;
		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numSpotsPerRow; j++){
				if (Util.getLabelByCarType(lotDesign[i][j]) != "N"){
					cap++;
				}
			}
		}
		return cap;

	}

	/**
	 * @return the total occupancy of the parking lot (i.e., the total number of
	 *         cars parked in the lot)
	 */
	public int getTotalOccupancy() {
		int occ = 0;
		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numSpotsPerRow; j++){
				if (occupancy[i][j] != null){
					occ++;
				}
			}
		}
		return occ;	
	}

	//figures out the rows and spots per row using information from the file
	private void calculateLotDimensions(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));
		int rows = 0;

		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			String[] line = str.split("[ ,]+");
			if (line[0].equals("")){
				continue;
			}
			else if (str.equals(SECTIONER)){
				scanner.close();
				break;
			}
			System.out.println(str);
			numSpotsPerRow = line.length;
			rows++;
		}
		numRows = rows;
	}

	//fills in the arrays lotDesign and occupancy using information from the file
	private void populateFromFile(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));

		int row = 0;
		// while loop for reading the lot design
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			String[] line = str.split("[ ,]+");
			if (line[0].equals("")){
				continue;
			}
			else if (str.equals(SECTIONER)){
				break;
			}
			for (int i = 0; i < numSpotsPerRow; i++){
				lotDesign[row][i] = Util.getCarTypeByLabel(line[i]);
			}
			row++;
		}

		// while loop for reading occupancy data
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			if (str.equals(SECTIONER) == false && str.equals("") == false){
				String[] line = str.split("[ ,]+");
				int x = Integer.valueOf(line[0]);
				int y = Integer.valueOf(line[1]);
				CarType t = Util.getCarTypeByLabel(line[2]);
				Car vroom = new Car(t, line[3]);
				park(x, y, vroom);
			}

		}

		scanner.close();
	}

	/**
	 * Produce string representation of the parking lot
	 * 
	 * @return String containing the parking lot information
	 */
	public String toString() {
		// NOTE: The implementation of this method is complete. You do NOT need to change it for the assignment.
		StringBuffer buffer = new StringBuffer();
		buffer.append("==== Lot Design ====").append(System.lineSeparator());

		for (int i = 0; i < lotDesign.length; i++) {
			for (int j = 0; j < lotDesign[0].length; j++) {
				buffer.append((lotDesign[i][j] != null) ? Util.getLabelByCarType(lotDesign[i][j])
						: Util.getLabelByCarType(CarType.NA));
				if (j < numSpotsPerRow - 1) {
					buffer.append(", ");
				}
			}
			buffer.append(System.lineSeparator());
		}

		buffer.append(System.lineSeparator()).append("==== Parking Occupancy ====").append(System.lineSeparator());

		for (int i = 0; i < occupancy.length; i++) {
			for (int j = 0; j < occupancy[0].length; j++) {
				buffer.append(
						"(" + i + ", " + j + "): " + ((occupancy[i][j] != null) ? occupancy[i][j] : "Unoccupied"));
				buffer.append(System.lineSeparator());
			}

		}
		return buffer.toString();
	}

	/**
	 * <b>main</b> of the application. The method first reads from the standard
	 * input the name of the file to process. Next, it creates an instance of
	 * ParkingLot. Finally, it prints to the standard output information about the
	 * instance of the ParkingLot just created.
	 * 
	 * @param args command lines parameters (not used in the body of the method)
	 * @throws Exception
	 */

	public static void main(String args[]) throws Exception {

		StudentInfo.display();

		System.out.print("Please enter the name of the file to process: ");

		Scanner scanner = new Scanner(System.in);

		String strFilename = scanner.nextLine();

		ParkingLot lot = new ParkingLot(strFilename);

		System.out.println("Total number of parkable spots (capacity): " + lot.getTotalCapacity());

		System.out.println("Number of cars currently parked in the lot: " + lot.getTotalOccupancy());

		System.out.print(lot);

	}
}