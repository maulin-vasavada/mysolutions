package mysolutions.dp;

public class BestPath {

	int[][] matrix;
	int maxI;
	int maxJ;
	int[][] cache;
	StringBuilder sb = new StringBuilder();

	public int bestPathRecursive(int[][] matrix) {
		int max = 0;

		this.matrix = matrix;
		initCache(Integer.MIN_VALUE);
		maxI = matrix.length - 1;
		maxJ = matrix[0].length - 1;

		max = bestPathRecursiveHelper(0, 0);
		//sb.append(matrix[0][0]);
		//System.out.println("Best path=" + sb + "****");
		return max;
	}

	private void initCache(int value) {
		cache = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				cache[i][j] = value;
			}
		}
	}

	private int bestPathRecursiveHelper(int i, int j) {

		if (i <= maxI && j <= maxJ && cache[i][j] != Integer.MIN_VALUE) {
			return cache[i][j];
		}

		if (i == maxI && j == maxJ) {
			cache[i][j] = matrix[i][j];
			return matrix[i][j];
		}

		int leftMax = Integer.MIN_VALUE;
		if (i < maxI) {
			leftMax = bestPathRecursiveHelper(i + 1, j);
		}
		int rightMax = Integer.MIN_VALUE;
		if (j < maxJ) {
			rightMax = bestPathRecursiveHelper(i, j + 1);
		}

		int max = Integer.MIN_VALUE;
		if (leftMax > rightMax) {
			max = leftMax;
			//sb.append(matrix[i + 1][j] + ",");
		} else {
			max = rightMax;
			//sb.append(matrix[i][j + 1] + ",");
		}
		int myCost = max + matrix[i][j];
		cache[i][j] = myCost;
		return myCost;
	}

	public int bestPathIterative(int[][] matrix) {
		int max = 0;
		this.matrix = matrix;
		initCache(0);
		computeLastRow();
		computeLastCol();
		computeRest();
		max = cache[0][0];
		return max;
	}

	private void computeRest() {
		int lastRow = matrix.length - 1;

		for (int i = lastRow - 1; i >= 0; i--) {
			computeRow(i);
		}
	}

	private void computeRow(int i) {
		int lastCol = matrix[0].length - 1;
		for (int j = lastCol - 1; j >= 0; j--) {
			cache[i][j] = Math.max(cache[i + 1][j], cache[i][j + 1]) + matrix[i][j];
		}
	}

	private void computeLastRow() {
		int lastRow = matrix.length - 1;
		int lastCol = matrix[0].length - 1;
		cache[lastRow][lastCol] = matrix[lastRow][lastCol];

		for (int col = lastCol - 1; col >= 0; col--) {
			cache[lastRow][col] = cache[lastRow][col + 1]
					+ matrix[lastRow][col];
		}
	}

	private void computeLastCol() {
		int lastRow = matrix.length - 1;
		int lastCol = matrix[0].length - 1;
		cache[lastRow][lastCol] = matrix[lastRow][lastCol];

		for (int row = lastRow - 1; row >= 0; row--) {
			cache[row][lastCol] = cache[row + 1][lastCol]
					+ matrix[row][lastCol];
		}
	}

	static void testRecursive() {
		int[][] matrix = new int[][] { { 7, 1, 0, -1, 25 },
				{ 100, 4, -14, 10, 23 }, { 33, -50, 5, 2, 6 },
				{ -10, 20, -4, 1, 9 }, { 40, 0, 5, -23, 4 } };
		long start = System.nanoTime();
		int max = new BestPath().bestPathRecursive(matrix);
		long end = System.nanoTime();
		System.out.println("recursive max=" + max + ", time=" + (end - start));
	}

	static void testIterative() {
		int[][] matrix = new int[][] { { 7, 1, 0, -1, 25 },
				{ 100, 4, -14, 10, 23 }, { 33, -50, 5, 2, 6 },
				{ -10, 20, -4, 1, 9 }, { 40, 0, 5, -23, 4 } };
		long start = System.nanoTime();
		int max = new BestPath().bestPathIterative(matrix);
		long end = System.nanoTime();
		System.out.println("iterative max=" + max + ", time=" + (end - start));
	}

	public static void main(String[] args) {
		testRecursive();
		testIterative();
	}
}
