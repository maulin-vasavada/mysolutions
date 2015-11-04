package mysolutions.dp;

public class Knapsack {

	int[] w;
	int[] v;
	int W;

	public Knapsack(int[] w, int[] v, int w2) {
		super();
		this.w = w;
		this.v = v;
		W = w2;
	}

	public int knapsackRecursive() {
		int max = 0;
		for (int i = 0; i < v.length; i++) {
			int ans = knapsackRecursiveHelper(i + 1, W - w[i], v[i]);
			if (ans > max) {
				max = ans;
			}
		}
		return max;
	}

	private int knapsackRecursiveHelper(int nextItem, int remainingWeight,
			int totalValueSoFar) {

		if (remainingWeight == 0) {
			return totalValueSoFar;
		}

		if (remainingWeight < 0) {
			return totalValueSoFar - v[nextItem - 1];
		}

		int max = 0;

		for (int i = nextItem; i < v.length; i++) {
			int ans = knapsackRecursiveHelper(i + 1, remainingWeight - w[i],
					totalValueSoFar + v[i]);
			if (ans > max) {
				max = ans;
			}
		}
		return max;
	}

	public int knapsackRecursive2(int nextItem, int remainingCapacity) {
		if (remainingCapacity == 0 || nextItem == 0) {
			return 0;
		}

		// if we don't include item
		int option2 = knapsackRecursive2(nextItem - 1, remainingCapacity);

		if (w[nextItem] > remainingCapacity) {
			return option2;
		} else {
			// if we include item
			int option1 = v[nextItem]
					+ knapsackRecursive2(nextItem - 1, remainingCapacity
							- w[nextItem]);
			return Math.max(option1, option2);
		}
	}

	public int knapsackIterative() {
		int max = 0;
		int[][] matrix = new int[v.length][];
		int maxCols = W + 1;
		initMatrix(matrix, maxCols);

		for (int i = 1; i < v.length; i++) {
			for (int j = 1; j < maxCols; j++) {
				int option1 = matrix[i - 1][j];
				int option2 = 0;
				if (j - w[i] >= 0) {
					option2 = v[i] + matrix[i-1][j - w[i]];
				}
				matrix[i][j] = Math.max(option1, option2);
			}
		}
		max = matrix[v.length - 1][W];
		return max;
	}

	private void initMatrix(int[][] matrix, int maxCols) {
		for (int i = 0; i < v.length ; i++) {
			matrix[i] = new int[maxCols];
		}
		
		matrix[0][0] = 0;
		for (int i = 0; i < maxCols; i++) {
			if (w[0] <= i) {
				matrix[0][i] = v[0];
			}
		}
	}

	static void testRecursive() {

		int[] w = new int[] { 10, 20, 30 };
		int[] v = new int[] { 60, 100, 120 };
		int W = 50;
		Knapsack knapsack = new Knapsack(w, v, W);

		long start = System.nanoTime();
		int max = knapsack.knapsackRecursive();
		long end = System.nanoTime();
		System.out.println("recursive max=" + max + ", time=" + (end - start));
	}

	static void testRecursive2() {

		int[] w = new int[] { 10, 20, 30 };
		int[] v = new int[] { 60, 100, 120 };
		int W = 50;
		Knapsack knapsack = new Knapsack(w, v, W);

		long start = System.nanoTime();
		int max = knapsack.knapsackRecursive2(v.length - 1, W);
		long end = System.nanoTime();
		System.out.println("recursive2 max=" + max + ", time=" + (end - start));
	}

	static void testIterative() {

		int[] w = new int[] { 10, 20, 30 };
		int[] v = new int[] { 60, 100, 120 };
		int W = 50;
		Knapsack knapsack = new Knapsack(w, v, W);

		long start = System.nanoTime();
		int max = knapsack.knapsackIterative();
		long end = System.nanoTime();
		System.out.println("iterative max=" + max + ", time=" + (end - start));
	}

	public static void main(String[] args) {
		testRecursive();
		testRecursive2();
		testIterative();
	}
}