import java.util.Hashtable;

//Representerar ett tillstånd i automaten
public class State {
	boolean isStart = false;
	boolean isAccepting = false;
	Hashtable<Character, State> transitions; 
	
	public State(){
		this.transitions = new Hashtable<Character, State>();
	}
	public void addTransition(char c,State toState){
		Character key = Character.valueOf(c);
		this.transitions.put(key, toState);		//Add new transition to hash table
	}
	public void setStart(){
		this.isStart = true;
	}
	public void removeStart(){
		this.isStart = false;
	}
	public void setAccepting(){
		this.isAccepting = true;
	}
	public void removeAccepting(){
		this.isAccepting = false;
	}
}
