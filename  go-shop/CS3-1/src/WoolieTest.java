
public class WoolieTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Woolie w1 = new Woolie(Woolie.Ranking.PRIVATE);
		Woolie w2 = new Woolie(Woolie.Ranking.CORPORAL);
		Woolie w3 = new Woolie(Woolie.Ranking.GENERAL);
		Woolie w4 = new Woolie(Woolie.Ranking.CAPTAIN);
		Woolie w5 = new Woolie(Woolie.Ranking.PRIVATE);
		
		System.out.println(w1);
		System.out.println(w2);
		System.out.println(w3);
		System.out.println(w4);
		
		if(w1.compareTo(w2) < 0){
			System.out.println("test1 " + w2);
		}else{
			System.out.println("Test1 " + w1);
		}

		if(w3.compareTo(w4) < 0){
			System.out.println("test2 " + w4);
		}else{
			System.out.println("test2 " + w3);
		}
		
		BattleFront b = new BattleFront();
		b.enqueue(w4);
		b.enqueue(w3);
		try {
			System.out.println(b.dequeue());
		} catch (UnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(b.size());

	}

}
