import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody {
	Queue<Note> notes;
	
	public Melody(Queue<Note> notes) {
		this.notes = notes;
	}
	
	public double getTotalDuration() {
		double totalSeconds = 0;
		double repeatTime = 0;
		boolean isRepeating = false;
		
		int size = notes.size();
		
		for(int e = 0; e < size; e++) {
			if(notes.peek().isRepeat() || isRepeating) {
				totalSeconds += notes.peek().getDuration();
				repeatTime += notes.peek().getDuration();
				if(notes.peek().isRepeat()) {
					isRepeating = !isRepeating;
				}
				notes.offer(notes.poll());
			}
			else {
				totalSeconds += notes.peek().getDuration();
				notes.offer(notes.poll());
			}
		}
		return totalSeconds + repeatTime;
	}
	
	public String toString() {
		String temp = "";
		int size = notes.size();
		for(int e = 0; e < size; e++) {
			temp += notes.peek().toString();
			temp += "\n";
			notes.offer(notes.poll());
		}
		return temp;
	}
	
	public void changeTempo(double tempo) {
		int size = notes.size();
		for(int e = 0; e < size; e++) {
			notes.peek().setDuration(notes.peek().getDuration()*tempo);
			notes.offer(notes.poll());
		}
	}
	
	public void reverse() {
		Stack<Note> temp = new Stack<>();
		
		while(!notes.isEmpty()) {
			temp.push(notes.poll());
		}
		while(!temp.isEmpty()) {
			notes.offer(temp.pop());
		}
	}
	
	public void append(Melody other) {
		int size = other.getNotes().size();
		for(int e = 0; e < size; e++) {
			notes.offer(other.getNotes().peek());
			other.getNotes().offer(other.getNotes().poll());
		}
	}
	
	public void play() {
		int size = notes.size();
		boolean isRepeating = false;
		Queue<Note> repeat = new LinkedList<>();
		
		for(int e = 0; e < size; e++) {
			if(notes.peek().isRepeat() || isRepeating) {
				notes.peek().play();
				repeat.offer(notes.peek());
				if(notes.peek().isRepeat()) {
					isRepeating = !isRepeating;
				}
				notes.offer(notes.poll());
			}
			else {
				notes.peek().play();
				notes.offer(notes.poll());
			}
			if(!isRepeating) {
				repeat = playRepeat(repeat);
			}
		}
	}
	
	private Queue<Note> playRepeat(Queue<Note> repeat) {
		while(!repeat.isEmpty()) {
			repeat.poll().play();
		}
		return repeat;
	}
	
	public Queue<Note> getNotes(){
		return notes;
	}
}
