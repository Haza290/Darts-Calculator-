import java.util.*;

public class Main
{
	private int target = 60;
	private int maxNumOfDarts = 3;
	private ArrayList<ArrayList<Integer>> solutions;
	
	private HashMap<Integer,String[]> dartsHashmap;
	private HashMap<Integer,String[]> endingDartsHashmap;
	public static void main(String[] args)
	{
		new Main();
	}
	
	public Main() {
		createHashMaps();
		this.solutions = new ArrayList<>();
		loopback(new ArrayList<Integer>());
		printSolutions();
	}
	
	private void createHashMaps() {
		this.dartsHashmap = new HashMap<>();
		this.dartsHashmap.put(20,new String[]{"20","D10"});
		
		this.endingDartsHashmap = new HashMap<>();
	}
	
	private void loopback(ArrayList<Integer> currentDarts) {
		if (0 == currentDarts.size()) {
			for (Map.Entry<Integer,String[]> entry : this.endingDartsHashmap.entrySet()){
				ArrayList newDarts = new ArrayList(currentDarts);
				newDarts.add((Integer)entry.getKey());
				loopback(newDarts);
			}
		}
		if (isAlreadyExplored(currentDarts)) {
			return;
		}
		 
		int sumOfCurrentDarts = sumDarts(currentDarts);
		if (target == sumOfCurrentDarts) {
			solutions.add(currentDarts);
			return;
		}
		if (sumOfCurrentDarts > this.target) {
			return;
		}
		if (currentDarts.size() >= this.maxNumOfDarts) {
			return;
		}
		for (Map.Entry<Integer,String[]> entry : dartsHashmap.entrySet()){
			ArrayList newDarts = new ArrayList(currentDarts);
			newDarts.add((Integer)entry.getKey());
			loopback(newDarts);
		}
	}
	
	private void printSolutions()
	{
		// TODO write this
	}
	
	private boolean isAlreadyExplored(ArrayList<Integer> currentDarts) {
		// TODO write this
		return false;
	}
	
	private int sumDarts(ArrayList<Integer> darts){
		int sum = 0;
		for(Integer dart : darts) {
			sum += dart;
		}
		return sum; 
	}
}
