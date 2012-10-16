/*
 * Class Banker
 * 
 */
public class Banker {

	private int nUnits;// the amount of units of the resource

	/**
	 * Initializes the banker to manage nUnits units of the resource
	 * 
	 * @param nUnits
	 */
	public Banker(int nUnits) {

		this.nUnits = nUnits;
	}

	/**
	 * attempts to register a claim for up to nUnits units of a resource
	 * 
	 * @param nUnits
	 */
	// TODO: figure out threads part
	public void setClaim(int nUnits) {

		if (nUnits <= 0) {
			System.exit(1);
		}
		String name = Thread.currentThread().getName();
		System.out.println("Thread " + name + " sets a claim for " + nUnits
				+ " units");
	}

	/**
	 * Request nUnits more resources
	 * 
	 * @param nUnits
	 */
	// TODO: Threads!!!
	public void request(int nUnits) {

		if (nUnits <= 0) {
			System.exit(1);
		}
		String name = Thread.currentThread().getName();
		System.out.println("Thread " + name + " requests " + nUnits + " units");
		
	}

}
