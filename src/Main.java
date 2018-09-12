import java.util.*;

public class Main
{
	private int target = 6;
	private int maxNumOfDarts = 1;
	private ArrayList<ArrayList<Integer>> solutions;
	private ArrayList<ArrayList<Integer>> exploredSolutions;
	private ArrayList<ArrayList<String>> printableSolutions;
	
	private int[] allDartScores = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,
		19,20,21,22,24,25,26,27,28,30,32,33,34,36,38,39,40,42,44,45,46,48,50,51,52,
		54,56,57,60};
	private int[] endingDartScores = {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,
			34,36,38,40,42,44,46,48,50,52,54,56,60};
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		Long startTime = System.currentTimeMillis();
		this.solutions = new ArrayList<>();
		this.exploredSolutions = new ArrayList<>();
		System.out.println("Starting loopback");
		loopback(new ArrayList<Integer>());
		printSolutions();
		Long endTime = System.currentTimeMillis();
		System.out.println("done in: " + (endTime - startTime) + "ms");
		System.out.println("solutions size: " + this.solutions.size());
		System.out.println("printable solutions size: " + this.printableSolutions.size());
	}
	
	
	private void loopback(final ArrayList<Integer> currentDarts) {
		if (isAlreadyExplored(currentDarts)) {
			return;
		}
		ArrayList<Integer> tmpArray = new ArrayList(currentDarts);
		Collections.sort(tmpArray);
		this.exploredSolutions.add(tmpArray);
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
		postProcessing(solutions);
		System.out.println("Solutions:");
		for(ArrayList<String> arrayList : this.printableSolutions){
			String solution = "";
			for(String dart : arrayList){
				solution += dart + ", ";
			}
			solution = solution.substring(0, solution.length() -2);
			System.out.println(solution);
		}
	}
	
	private void postProcessing(ArrayList<ArrayList<Integer>> solutionIntArray){
		
		printableSolutions = new ArrayList<>();
		for (ArrayList<Integer> dartScoreArray : solutionIntArray) {
			postProcessingLoopback(dartScoreArray, new ArrayList<String>());
		}
		for (ArrayList<String> printableSolutionsArray : printableSolutions) {
			Collections.reverse(printableSolutionsArray);
		}
		
	}
	
	private void postProcessingLoopback(ArrayList<Integer> unprocessesDartScores, ArrayList<String> processedDartScores) {
					
		if (unprocessesDartScores.size() == 0) {
			this.printableSolutions.add(processedDartScores);
			return;
		}
		ArrayList<Integer> nextLoopbackIntArray = new ArrayList(unprocessesDartScores);
		nextLoopbackIntArray.remove(0);
		
		int currentDartScore = unprocessesDartScores.get(0);
		if (((currentDartScore % 2) == 0) && ((currentDartScore / 2) <= 20)) {
			String dartScoreString = "D" + currentDartScore/2;
			createArrayAndLoopbackPostProcessing(processedDartScores, dartScoreString, nextLoopbackIntArray);
		}
		if (currentDartScore == 50) {
			createArrayAndLoopbackPostProcessing(processedDartScores, "Bull", nextLoopbackIntArray);
		}
		if (processedDartScores.size() > 0){
			if (currentDartScore <= 20) {
				createArrayAndLoopbackPostProcessing(processedDartScores, Integer.toString(currentDartScore), nextLoopbackIntArray);
			}
			if ((currentDartScore % 3) == 0) {
				String dartScoreString = "T" + currentDartScore/3;
				createArrayAndLoopbackPostProcessing(processedDartScores, dartScoreString, nextLoopbackIntArray);
			}
			if (currentDartScore == 25) {
				createArrayAndLoopbackPostProcessing(processedDartScores, "Outer Bull", nextLoopbackIntArray);
			}
		}
	}

	private void createArrayAndLoopbackPostProcessing(ArrayList<String> processedDartScores, String newElement, ArrayList<Integer> nextLoopbackIntArray)
	{
		ArrayList<String> tmpStringArray = new ArrayList(processedDartScores);
		tmpStringArray.add(newElement);
		postProcessingLoopback(nextLoopbackIntArray, tmpStringArray);
	}
	
	private boolean isAlreadyExplored(ArrayList<Integer> currentDarts) {
		// TODO rewrite this to optimise it
		//Random rand = new Random();
		//if(rand.nextInt(100) == 99) {
		//	System.out.println(currentDarts);
		//}
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
