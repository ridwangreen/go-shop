/*
 * Version: $Id: WoolieWar.java,v 1.2 2009/03/10 13:53:39 vcss233 Exp $
 * Revisions: $Log: WoolieWar.java,v $
 * Revisions: Revision 1.2  2009/03/10 13:53:39  vcss233
 * Revisions: made WoolieWar.truce volatile
 * Revisions:
 * Revisions: Revision 1.1  2009/03/09 17:48:42  vcss233
 * Revisions: Initial revision
 * Revisions:
 */

import java.util.Random;

/**
 * The WoolieWar is a battle simulation program. Woolie's are randomly
 * recruited by recruiter threads and inserted into the battlefront based on
 * their military rank. A warmonger thread removes woolie's from the front
 * of the battlefront and sends them to their untimely deaths. The main
 * program updates the progress of the war periodically. Once the death toll
 * passes an acceptable limit, a truce is declared where all the threads
 * shut down and the final battle statistics are reported.
 * 
 * @author Sean Strout 
 */
public class WoolieWar {
	/**
	 * The battleFront is a priority queue of woolie's that are used
	 * to fight the war.
	 */
	private BattleFront<Woolie> battleFront = new BattleFront<Woolie>();

	/**
	 * The acceptable casualty number needed to further the agenda.
	 */
	private final int ACCEPTABLE_LOSS = 50;

	/**
	 * The number of recruiters that are out recruiting woolie's.
	 */
	private final int MAX_RECRUITERS = 3;

	/**
	 * The WOOLIEWAR sleeps for this amount of time (ms) during the war,
	 * and then wakes up to update the readers.
	 */
	private final int WOOLIEWAR_SLEEP_TIME = 2000;

	/**
	 * Once a truce is reached the war is over.  It is declared 
	 * volatile so that the threads do not cache its initial state.
	 */
	private volatile boolean truce = false;

	/**
	 * The collection of recruiter threads.
	 */
	private Recruiter[] recruiters = new Recruiter[MAX_RECRUITERS];

	/**
	 * The warmonger thread.
	 */
	private Warmonger warmonger;

	/**
 	 * Used to block multiple recruiters from creating woolie's
	 * at the same time and potentially corrupting id's
	 */
	private Object create = new Object();

	/**
	 * Used for randomization.
	 */
	private Random rand = new Random();

	/**
	 * The constructor starts all the recruiter threads, and the
	 * warmonger thread.
	 */
	public WoolieWar() {
		for (int i = 0; i < MAX_RECRUITERS; i++) {
			recruiters[i] = new Recruiter("RECRUITER " + i);
			recruiters[i].start();
		}
		warmonger = new Warmonger("WARMONGER");
		warmonger.start();
	}

	/**
	 * The recruiter is a nested class that is responsible for creating
	 * random woolie's and inserting them into the battlefront. 
	 */
	public class Recruiter extends Thread {
		/**
		 * The recruiter threads sleep for this random amount of
		 * time (ms) before recruiting again.
		 */
		private final int RECRUITER_SLEEP_TIME = 3000;

		/**
		 * Constructs the recruiter.
		 * 
		 * @param name The name for this recruiter.
		 */
		public Recruiter(String name) {
			super(name);
		}

		/**
		 * The recruiter work is done here. Each cycle they randomly
		 * create a woolie and enlist them into the war by enqueueing
	 	 * them into the battlefront. After each recruitment they
		 * sleep for a random period of time before enlisting again. 
		 * The thread terminates when a truce is declared.
		 */
		public void run() {
			int numRecruited = 0;
			System.out.println(getName() + 
				": Beginning woolie recruitment.");

			while (!truce) {
				Woolie soldier;		
				// we make sure only one recruiter creates
				// a Woolie at a time, otherwise the id's
				// could become non-unique.
				synchronized (create) {
					soldier = new Woolie(Woolie.Ranking.values()[rand.nextInt(Woolie.NUM_RANKS)]);
				}
				System.out.println(getName() +
					 ": enlisting " + soldier);
				battleFront.enqueue(soldier);
				numRecruited++;
				try {
					sleep(rand.nextInt(RECRUITER_SLEEP_TIME));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// report stats
			System.out.println(getName() + 
				": retires after enlisting " + 
				numRecruited + " woolies");
		}
	}

	/**
	 * The warmonger is a nested class responsible for waging the war.  
	 * At random intervalsm it takes woolie's off the front of the
	 * battlefront and sends them to their untimely death.  This cycle is
	 * repeated until a truce is declared.
	 */
	public class Warmonger extends Thread {
		/**
		 * Tracks the death toll.
		 */
		private int deathToll = 0;

		/**
		 * The warmonger sleeps this random amount of time (ms)
		 * before getting another woolie from the battle front
		 * to fight and die
		 */ 
		private final int WARMONGER_SLEEP_TIME = 1000;

		/**
		 * Constructs the warmonger.
		 * 
		 * @param name The warmonger's name.
		 */
		public Warmonger(String name) {
			super(name);
		}

		/**
		 * Accessor for the current death toll.
		 * 
		 * @return The number of woolie's that have died for the
		 * cause.
		 */
		public int getDeathToll() {
			return deathToll;
		}

		/**
		 * The warmonger's work is done here.  It randomly takes
		 * woolies from the front of the battlefront and sends them
		 * to fight/die.  It keeps track of each woolie it sends to
		 * battle for statistics reporting.
		 */
		public void run() {
			// Keep track of how many woolie's of each military
			// rank died
			int[] rankCasualties = new int[Woolie.NUM_RANKS];
			
			System.out.println(getName() + ": Ready for battle!");
			// while there is war, prepare the next woolie and
			// send them off to fight (a.k.a. die)
			while (!truce) {
				try {
					Woolie casualty = battleFront.dequeue();
					System.out.println(getName() + ": Sending " + casualty
							+ " off to fight!");
					rankCasualties[casualty.getRank().ordinal()]++;
					deathToll++;
				} catch (UnderflowException ufe) {
					// We get here if the battlefront is empty and the warmonger needs
					// a soldier to fight.
					System.out.println(getName()
							+ ": No woolies are ready for battle!!");
				}
				try {
					sleep(rand.nextInt(WARMONGER_SLEEP_TIME));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// report stats
			System.out.println(getName() + 
				": Waves the white flag!");
			System.out.println(getName() + ": " +
				 warmonger.getDeathToll() + 
				" woolies died honorably in service to our woolieland:");
			for (int i = 0; i < rankCasualties.length; i++) {
				System.out.println(getName() +
					 ":\t" + rankCasualties[i] + 
					" deceased " + 
					Woolie.Ranking.values()[i] + "'s");
			}
		}

	}

	/**
	 * The WOOLIEWAR runs on the main thread and is responsible for
	 * reporting the current status of the war. Once the casualties
	 * cross the acceptable limit, it notifies the recruiters
	 * and warmonger to retire because the war is over. Once all
	 * threads complete, it reports statistics.
	 */
	public void conduct() {
		// loop until too many woolies die
		while (warmonger.getDeathToll() < ACCEPTABLE_LOSS) {
			System.out.println("WOOLIEWAR: The death toll is at "
					+ warmonger.getDeathToll() +
					 " woolies.");
			try {
				Thread.sleep(WOOLIEWAR_SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// wait for the recruiters and warmonger to notice the war
		// is over and naturally terminate
		System.out.println("WOOLIEWAR: The death toll has become unacceptable.  Declaring truce!");
		truce = true;
		for (int i = 0; i < recruiters.length; i++) {
			try {
				recruiters[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			warmonger.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// report on the final death toll and survivor count
		System.out.println("WOOLIEWAR: The war is over!");
		System.out.println("WOOLIEWAR: " + battleFront.size()
				+ " woolies will be returning home safely:");
		while (!battleFront.empty()) {
			try {
				System.out.println("WOOLIEWAR:\t" +
					 battleFront.dequeue() +
					 " receives the purple heart!");
			} catch (UnderflowException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Simulation complete.");
	}

	/**
	 * The main method conducts the war of the woolies.
	 *
	 * @param args Command line arguments (unused).
	 */
	public static void main(String[] args) {
		WoolieWar ww = new WoolieWar();
		ww.conduct();
	}
}
