import java.util.*;

public class Main
{
	private int target = 60;
	private int maxNumOfDarts = 3;
	private ArrayList<ArrayList<Integer>> solutions;
	private ArrayList<ArrayList<Integer>> exploredSolutions;
	
	private int[] allDartScores = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,
		19,20,21,22,24,25,26,27,28,30,32,33,34,36,38,39,40,42,44,45,46,48,50,51,52,
		54,56,57,60};
	private int[] endingDartScores = {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,
			34,36,38,40,42,44,46,48,50,52,54,56,60};
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		this.solutions = new ArrayList<>();
		this.exploredSolutions = new ArrayList<>();
		System.out.println("Starting loopback");
		loopback(new ArrayList<Integer>());
		printSolutions();
		System.out.println("done");
	}
	
	
	private void loopback(final ArrayList<Integer> currentDarts) {
		if (isAlreadyExplored(currentDarts)) {
			return;
		}
		this.exploredSolutions.add(Collections.sort(new ArrayList(currentDarts)));
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
		if (0 == currentDarts.size()){
			for (int endingDartScore : this.endingDartScores){
				creatNewArrayAndLoopBack(currentDarts, endingDartScore);
				return;
			}
		}
		for (int dartScore : this.allDartScores){
			creatNewArrayAndLoopBack(currentDarts, dartScore);
		}
	}
	
	private void creatNewArrayAndLoopBack(ArrayList currentDarts, int dart) {
		ArrayList<Integer> newDartsArray = new ArrayList<>(currentDarts);
		newDartsArray.add(dart);
		loopback(newDartsArray);
	}
	
	private void printSolutions() {
		System.out.println("Solutions:");
		for(ArrayList arrayList : this.solutions){
			String solution = "";
			for(Integer dart : arrayList){
				solution += Integer.toString(dart) + ", ";
			}
			System.out.println(solution);
		}
	}
	
	private boolean isAlreadyExplored(ArrayList<Integer> currentDarts) {
		// TODO rewrite this to optimise it
		Random rand = new Random();
		if(rand.nextInt(100) == 99) {
			System.out.println(currentDarts);
		}
		ArrayList<Integer> tempCurrentDarts = new ArrayList(currentDarts);
		Collections.sort(tempCurrentDarts);
		for(ArrayList<Integer> arrayList : this.exploredSolutions){
			if (tempCurrentDarts.equals(arrayList)){
				return true;
			}
		}
		return false;
	}
	
	private int sumDarts(ArrayList<Integer> darts) {
		int sum = 0;
		for(Integer dart : darts) {
			sum += dart;
		}
		return sum; 
	}
}
