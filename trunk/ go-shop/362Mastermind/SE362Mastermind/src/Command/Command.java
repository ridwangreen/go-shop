package Command;

import system.Peg;

public abstract class Command {
	public Peg[] pegs = new Peg[4];
	
	@Override
	public String toString() {
		String val = ""+this.getClass().getName();
		for (Peg c : pegs) {
			if(c!=null)
				val+=","+c.toString();
		}
		return val;
	}
	
	public abstract void execute();
	public abstract void undo();
}
