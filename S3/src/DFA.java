
/* Kodskelett för labb S3 i DD1361 Programmeringsparadigm
 *
 * Författare: Per Austrin
 */
import java.util.List;
import java.util.ArrayList;

public class DFA {
	ArrayList<State> states;
	
	public DFA(int stateCount, int startState) {
		this.states = new ArrayList<State>(stateCount); 
		for(int i = 0; i < stateCount; i++){
			this.states.set(i, new State());
		}
		this.states.get(startState).setStart();
	}

	public void setAccepting(int state) {
		this.states.get(state).setAccepting();
	}

	public void addTransition(int from, int to, char c) {
		this.states.get(from).addTransition(c, this.states.get(to));
	}

	public List<String> getAcceptingStrings(int maxCount) {
		
		
		
		return new ArrayList<String>();
	}
}