package player;


public class PlayerProxy {

	private Players roster[];
	private CodeMaker maker;
	private CodeBreaker breaker;
	private PlayerType makerType;
	private PlayerType breakerType;

	public PlayerProxy(PlayerType makerType, PlayerType breakerType) {

		roster = new Players[2];
		this.makerType = makerType;
		this.breakerType = breakerType;
		
		if(makerType == PlayerType.LOCAL){
			maker = new CodeMaker_Local();
		}else if(makerType == PlayerType.AI){
			maker = new CodeMaker_AI();
		}else{
			maker = new CodeMaker_Remote();
		}
		roster[0] = maker;
		
		if(breakerType == PlayerType.LOCAL){
			breaker = new CodeBreaker_Local();
		}else if(breakerType == PlayerType.AI){
			breaker = new CodeBreaker_AI();
		}else{
			breaker = new CodeBreaker_Remote();
		}roster[1] = breaker;
	}

	/**
	 * The method for making a turn if the player is an AI It checks if the
	 * player is an AI and if it then the player will make move. Otherwise it
	 * does nothing
	 */
	public void nextBreakerTurnAI() {
		
		if(breakerType == PlayerType.AI){
			nextBreakerTurn();
		}
	}
	
	/**
	 * The method for making a turn if the player is an AI It checks if the
	 * player is an AI and if it then the player will make move. Otherwise it
	 * does nothing
	 */
	public void nextMakerTurnAI() {

		if(makerType == PlayerType.AI){
			nextMakerTurn();
		}
	}

	/**
	 * Calls the next player and tells it to makeMove
	 */
	public void nextBreakerTurn() {

		breaker.makeMove();
	}
	
	/**
	 * Calls the next player and tells it to makeMove
	 */
	public void nextMakerTurn() {

		maker.makeMove();
	}

	/**
	 * Getter for the codemaker
	 * 
	 * @return the code maker
	 */
	public CodeMaker getMaker() {

		return maker;
	}

	/**
	 * Getter for the codebreaker
	 * 
	 * @return the code breaker
	 */
	public CodeBreaker getBreaker() {

		return breaker;
	}
	
	/**
	 * Getter for the maker type
	 * 
	 * @return
	 */
	public PlayerType getMakerType(){
		
		return makerType;
	}
	
	/**
	 * getter for the breaker type
	 * 
	 * @return
	 */
	public PlayerType getBreakerType(){
		
		return breakerType;
	}
}
