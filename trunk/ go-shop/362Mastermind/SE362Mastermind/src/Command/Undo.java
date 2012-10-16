package Command;
import java.util.LinkedList;
import java.util.List;

public class Undo extends Command {
	private List<Command> commandStack = new LinkedList<Command>();
	public Undo() {
	}
	
	@Override
	public void execute() {
	}
	
	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}
}
