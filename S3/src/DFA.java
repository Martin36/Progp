
/* Kodskelett för labb S3 i DD1361 Programmeringsparadigm
 *
 * Författare: Per Austrin
 */
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class DFA {
	int startState;
	int maxCount;
	int counter;
	ArrayList<State> states;
	int maxDepth = 30;
	
	public DFA(int stateCount, int startState) {
		this.startState = startState;
		this.states = new ArrayList<State>(stateCount); 
		for(int i = 0; i < stateCount; i++){
			this.states.add(i, new State());
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
		this.counter = 0;
		this.maxCount = maxCount;
		ArrayList<String> acceptedStrings = new ArrayList<String>();
		State startState = this.states.get(this.startState);
		ArrayList<String> emptyList = new ArrayList<String>();
		acceptedStrings = (ArrayList<String>) findAcceptedStrings(startState, ' ', "", emptyList, 0);
		/*
		while(counter < maxCount){
			//Hitta en sträng som accepteras
			Hashtable<Character, State> transitions = currentState.getTransitions();		//Övergångarna från nuvarande tillståndet
			if(transitions.isEmpty()){		//Då finns det inga övergångar från nuvarande tillståndet
				break;
			}
			counter++;
		}
		*/
		
		return acceptedStrings;
	}
	
	//Rekursiv funktion för att hitta strängar
	private List<String> findAcceptedStrings(State currentState, 
			char transitionChar, String currentString, List<String> foundStrings, int depth){
		if(this.counter >= this.maxCount || depth >= this.maxDepth){
			return foundStrings;			
		}
		Hashtable<Character, State> transitions = currentState.getTransitions();		//Övergångarna från nuvarande tillståndet
		StringBuilder sb = new StringBuilder();
		sb.append(currentString);
		if(!currentState.isStart()){		//Om det är start tillståndet så vill vi inte lägga till nåt tecken
			sb.append(transitionChar);		//Det tecknet som tog oss till det här tillståndet			
		}
		if(currentState.isStart()){			//Det är bara första gången vi inte vill lägga till tecken för start tillståndet
			currentState.removeStart();
		}
		if(currentState.isAccepting()){		//Hittat en ny godkänd sträng
			foundStrings.add(sb.toString());
			this.counter++;
		}
		if(transitions.isEmpty()){
			return foundStrings;
		}

		
		List<String> newFoundStrings = null;
		Enumeration<Character> enumerator = transitions.keys();		//Tecknen för de olika övergångarna
		
		while(enumerator.hasMoreElements()){
			Character transitionCharToNextState = enumerator.nextElement();
			State nextState = transitions.get(transitionCharToNextState);
			String startString = sb.toString();
			
			newFoundStrings = findAcceptedStrings(nextState, transitionCharToNextState.charValue(),
					startString, foundStrings, depth+1);
			
		}
		
		return newFoundStrings;
	}
	//Icke rekursiv funktion
	private List<String> findAcceptedStrings2(State startState){
		Hashtable<Character, State> transitions = startState.getTransitions();		//Övergångarna från nuvarande tillståndet
		
		
		return null;
	}
}