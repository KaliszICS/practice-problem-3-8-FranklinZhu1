import java.util.HashMap;

public class PracticeProblem {

	public static int fibHelper(HashMap<Integer, Integer> fibmap, int num) {
		switch (num) {
			case 0: return 0;
			case 1: return 1;
			default:
				int a = (fibmap.containsKey(num - 1)) ? fibmap.get(num - 1) : fibHelper(fibmap, num - 1), b = (fibmap.containsKey(num - 2)) ? fibmap.get(num - 2) : fibHelper(fibmap, num - 2); // check if it's already memoized in the hashmap, recurse if not
				fibmap.put(num - 1, a);
				fibmap.put(num - 2, b); // memoize both values
				return a + b;
		}
	}

	public static int fib(int num) {
		HashMap<Integer, Integer> fibmap = new HashMap<Integer, Integer>();
		return fibHelper(fibmap, num);
	}

	public static int stairStepper(int step, int moneyCount, int[] cost, int[] minCost) {
		moneyCount += cost[step]; // add to cost of current path
		int[] options = {999999, 999999}; // to store the costs of both 1 and 2-step paths
		for (int i = 1; i <= 2; ++i) {
			if (step + i > minCost.length - 1) break; // out of bounds
			if (minCost[step + i] == -1 || moneyCount < minCost[step + i]) { // if step not visited, or current iteration is cheapest
				minCost[step + i] = moneyCount; // set new minCost there
				if (step + i == minCost.length - 1) return moneyCount; // reached top of floor
				options[i - 1] = stairStepper(step + i, moneyCount, cost, minCost); // recurse
			}
		}
		return Math.min(options[0], options[1]);
	}

	public static int minCostClimbingStairs(int[] cost) { // isn't this dp?
		switch (cost.length) {
			case 0: return 0;
			case 1: return cost[0];
			default:
				int[] minCost = new int[cost.length + 1]; // to account for top of stairs
				for (int i = 0; i < cost.length + 1; ++i) minCost[i] = -1;
				return Math.min(stairStepper(0, 0, cost, minCost), stairStepper(1, 0, cost, minCost));
		}
	}
}
