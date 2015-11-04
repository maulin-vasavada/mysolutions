package mysolutions.dp;

public class CoinChange {

	public int coinChangeWays(int[] coins, int N) {
		int validCombinations = 0;
		for (int i = 0; i < coins.length; i++) {
			validCombinations += coinChangeWaysHelper(coins, i, 0, N);
		}
		return validCombinations;
	}

	private int coinChangeWaysHelper(int[] coins, int i, int sum, int N) {
		sum += coins[i];

		if (sum > N) {
			return 0;
		}

		if (sum == N) {
			return 1;
		}

		int validCombinations = 0;
		for (int j = i; j < coins.length; j++) {
			validCombinations += coinChangeWaysHelper(coins, j, sum, N);
		}
		return validCombinations;
	}

	int[] minCoinsCache;

	public int minCoinsRecursive(int[] coins, int N) {
		minCoinsCache = new int[N];
		for (int i = 0; i < N; i++) {
			minCoinsCache[i] = Integer.MAX_VALUE;
		}
		return minCoinsRecursiveHelper(coins, N);
	}

	private int minCoinsRecursiveHelper(int[] coins, int N) {

		if (minCoinsCache[N - 1] != Integer.MAX_VALUE) {
			return minCoinsCache[N - 1];
		}

		int min = N;
		for (int coin : coins) {
			if (N - coin == 0) {
				return 1;
			}
			if (N - coin > 0) {
				int ans = minCoinsRecursiveHelper(coins, N - coin);
				if (ans < min) {
					min = ans;
				}
			}
		}
		minCoinsCache[N - 1] = min + 1;
		return min + 1;
	}

	public int minCoinsIterative(int[] coins, int N) {
		int min = N;
		int maxRow = coins.length;
		int maxCol = N + 1;

		int[][] matrix = new int[maxRow][];
		initMatrix(matrix, maxRow, maxCol, coins[0]);

		for (int i = 1; i < maxRow; i++) {
			for (int j = 1; j < maxCol; j++) {
				int q = (int) (j / coins[i]);
				int r = j % coins[i];
				matrix[i][j] = q + matrix[i - 1][r];
				if (j == maxCol - 1 && matrix[i][j] < min) {
					min = matrix[i][j];
				}
			}
		}

		return min;
	}

	public int minCoinsIterative2(int[] coins, int N) {
		int min = N;
		int maxRow = coins.length;
		int maxCol = N + 1;

		int[][] matrix = new int[maxRow][];
		initMatrix(matrix, maxRow, maxCol, coins[0]);

		for (int i = 1; i < maxRow; i++) {
			for (int j = 1; j < maxCol; j++) {
				if (j >= coins[i]) {
					matrix[i][j] = Math.min(matrix[i - 1][j], 1 + matrix[i][j
							- coins[i]]);
				} else {
					matrix[i][j] = matrix[i - 1][j];
				}
				if (j == maxCol - 1 && matrix[i][j] < min) {
					min = matrix[i][j];
				}
			}
		}

		return min;
	}

	private void initMatrix(int[][] matrix, int maxRow, int maxCol,
			int firstCoin) {
		for (int i = 0; i < maxRow; i++) {
			matrix[i] = new int[maxCol];
		}
		initFirstRow(matrix, maxCol, firstCoin);
		initFirstCol(matrix, maxRow);
	}

	private void initFirstCol(int[][] matrix, int maxRow) {
		matrix[0][0] = 0;
		for (int i = 1; i < maxRow; i++) {
			matrix[i][0] = 0;
		}
	}

	private void initFirstRow(int[][] matrix, int maxCol, int firstCoin) {
		matrix[0][0] = 0;
		for (int i = 1; i < maxCol; i++) {
			matrix[0][i] = (int) (i / firstCoin);
		}
	}

	static void testCoinChangeWaysRecursive() {
		int[] coins = new int[] { 1, 5, 10, 25 };
		int N = 29;
		CoinChange coinChange = new CoinChange();
		long start = System.nanoTime();
		int validCombinations = coinChange.coinChangeWays(coins, N);
		long end = System.nanoTime();
		System.out.println("recursive combinations=" + validCombinations
				+ ", time=" + (end - start));
	}

	static void testMinCoinsRecursive() {
		int[] coins = new int[] { 1, 5, 10, 21, 25 };
		int N = 63;
		CoinChange coinChange = new CoinChange();
		long start = System.nanoTime();
		int minCoinChange = coinChange.minCoinsRecursive(coins, N);
		long end = System.nanoTime();
		System.out.println("recursive minCoins=" + minCoinChange + ", time="
				+ (end - start));
	}

	static void testMinCoinsIterative() {
		int[] coins = new int[] { 1, 5, 10, 21, 25 };
		coins = new int[] { 1, 5, 6, 8 };
		int N = 63;
		N = 11;
		CoinChange coinChange = new CoinChange();
		long start = System.nanoTime();
		int minCoinChange = coinChange.minCoinsIterative(coins, N);
		long end = System.nanoTime();
		System.out.println("iterative minCoins=" + minCoinChange + ", time="
				+ (end - start));
	}

	static void testMinCoinsIterative2() {
		int[] coins = new int[] { 1, 5, 10, 21, 25 };
		coins = new int[] { 1, 5, 6, 8 };
		int N = 63;
		N = 11;
		CoinChange coinChange = new CoinChange();
		long start = System.nanoTime();
		int minCoinChange = coinChange.minCoinsIterative2(coins, N);
		long end = System.nanoTime();
		System.out.println("iterative2 minCoins=" + minCoinChange + ", time="
				+ (end - start));
	}

	public static void main(String[] args) {
//		testCoinChangeWaysRecursive();
//		testMinCoinsRecursive();
		testMinCoinsIterative2();
		testMinCoinsIterative();
	}
}