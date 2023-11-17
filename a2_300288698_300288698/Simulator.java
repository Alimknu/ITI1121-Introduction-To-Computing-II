/**
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Ali Mirzakhalili, University of Ottawa
 *
 */
public class Simulator {

	/**
	 * Maximum duration a car can be parked in the lot
	 */
	public static final int MAX_PARKING_DURATION = 8 * 3600;

	/**
	 * Total duration of the simulation in (simulated) seconds
	 */
	public static final int SIMULATION_DURATION = 24 * 3600;

	/**
	 * The probability distribution for a car leaving the lot based on the duration
	 * that the car has been parked in the lot
	 */
	public static final TriangularDistribution departurePDF = new TriangularDistribution(0, MAX_PARKING_DURATION / 2,
			MAX_PARKING_DURATION);

	/**
	 * The probability that a car would arrive at any given (simulated) second
	 * This probability is calculated in the constructor based on the perHourArrivalRate
	 * passed to the constructor.
	 */
	private Rational probabilityOfArrivalPerSec;

	/**
	 * The simulation clock. Initially the clock should be set to zero; the clock
	 * should then be incremented by one unit after each (simulated) second.
	 */
	private int clock;

	/**
	 * Total number of steps (simulated seconds) that the simulation should run for.
	 * This value is fixed at the start of the simulation. The simulation loop
	 * should be executed for as long as clock < steps. When clock == steps, the
	 * simulation is finished.
	 */
	private int steps;

	/**
	 * Instance of the parking lot being simulated.
	 */
	private ParkingLot lot;

	/**
	 * Queue for the cars wanting to enter the parking lot
	 */
	private Queue<Spot> incomingQueue;

	/**
	 * Queue for the cars wanting to leave the parking lot
	 */
	private Queue<Spot> outgoingQueue;

	/**
	 * @param lot                 is the parking lot to be simulated
	 * @param perHourArrivalRate  is the HOURLY rate at which cars show up in front of the lot
	 * @param steps               is the total number of steps for simulation
	 */
	public Simulator(ParkingLot lot, int perHourArrivalRate, int steps) {

		this.lot = lot;

		this.steps = steps;

		this.clock = 0;
		
		// YOUR CODE HERE! YOU SIMPLY NEED TO COMPLETE THE LINES BELOW:

		// What should the two questions marks be filled with? 
		// Hint: you are being given a perHourArrivalRate. 
		// All you need to do is to convert this hourly rate into 
		// a per-second rate (probability).
		
		this.probabilityOfArrivalPerSec = new Rational(perHourArrivalRate, 3600); //this might be the issue

		
		// Finally, you need to initialize the incoming and outgoing queues

		incomingQueue = new LinkedQueue<Spot>();
		outgoingQueue = new LinkedQueue<Spot>();

	}

	/**
	 * Simulate the parking lot for the number of steps specified by the steps
	 * instance variable.
	 * In this method, you will implement the algorithm shown in Figure 3 of the A2 description.
	 */
	public void simulate() {
	
		// Local variables can be defined here.

		Spot newSpot;
		Car newCar;
		Spot currentSpot;
		Spot queuedCar = null;

		this.clock = 0; //Step 1

		// Note that for the specific purposes of A2, clock could have been 
		// defined as a local variable too.

		while (clock < steps) {//Step 2
	
			if (RandomGenerator.eventOccurred(probabilityOfArrivalPerSec)){//Step 3
				newCar = RandomGenerator.generateRandomCar();
				newSpot = new Spot(newCar, this.clock);
				incomingQueue.enqueue(newSpot); //Step 4
			} 

			for (int i = 0; i < lot.getNumRows(); i++){ 
				for (int j = 0; j < lot.getNumSpotsPerRow(); j++){ //Step 5
					int durationParked = 0;
					currentSpot = lot.getSpotAt(i,j);
					if (currentSpot == null){
						continue;
					}
					durationParked = clock - currentSpot.getTimestamp(); //Step 6

					if (durationParked >= MAX_PARKING_DURATION){ //Step 7
						currentSpot = lot.remove(i,j);
						outgoingQueue.enqueue(currentSpot);
					}
					else{//Step 8
						if (RandomGenerator.eventOccurred(departurePDF.pdf(durationParked))){ //Step 9/10
							currentSpot = lot.remove(i,j);
							outgoingQueue.enqueue(currentSpot);
						}
					}
				}

			}

			if (incomingQueue.isEmpty() == false){ //Step 13
				if (queuedCar == null){
					queuedCar = incomingQueue.dequeue();
				}
				if (queuedCar !=null && lot.attemptParking(queuedCar.getCar(), clock)){
					System.out.println(queuedCar.getCar() + " ENTERED at timestep " + clock + "; occupancy is at " + lot.getTotalOccupancy());
					queuedCar = null;
				}
			}

			if (outgoingQueue.isEmpty() == false){ //Step 14
				newSpot = outgoingQueue.dequeue();
				System.out.println(newSpot.getCar() + " EXITED at timestep " + clock + "; occupancy is at " + lot.getTotalOccupancy());
			}

			clock++; //Step 15
		}
	}

	/**
	 * <b>main</b> of the application. The method first reads from the standard
	 * input the name of the parking-lot design. Next, it simulates the parking lot
	 * for a number of steps (this number is specified by the steps parameter). At
	 * the end, the method prints to the standard output information about the
	 * instance of the ParkingLot just created.
	 * 
	 * @param args command lines parameters (not used in the body of the method)
	 * @throws Exception
	 */

	public static void main(String args[]) throws Exception {

		StudentInfo.display();
		
		if (args.length < 2) {
			System.out.println("Usage: java Simulator <lot-design filename> <hourly rate of arrival>");
			System.out.println("Example: java Simulator parking.inf 11");
			return;
		}

		if (!args[1].matches("\\d+")) {
			System.out.println("The hourly rate of arrival should be a positive integer!");
			return;
		}

		ParkingLot lot = new ParkingLot(args[0]);

		System.out.println("Total number of parkable spots (capacity): " + lot.getTotalCapacity());

		Simulator sim = new Simulator(lot, Integer.parseInt(args[1]), SIMULATION_DURATION);

		long start, end;

		System.out.println("=== SIMULATION START ===");
		start = System.currentTimeMillis();
		sim.simulate();
		end = System.currentTimeMillis();
		System.out.println("=== SIMULATION END ===");

		System.out.println();

		System.out.println("Simulation took " + (end - start) + "ms.");

		System.out.println();

		int count = 0;

		// The Queue interface does not provide a method to get the size of the queue.
		// We thus have to dequeue all the elements to count how many elements the queue has!
		
		while (!sim.incomingQueue.isEmpty()) {
			sim.incomingQueue.dequeue();
			count++;
		}

		System.out.println("Length of car queue at the front at the end of simulation: " + count);
	}
}
