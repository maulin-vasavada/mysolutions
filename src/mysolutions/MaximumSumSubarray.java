package mysolutions;

public class MaximumSumSubarray {

	private class Result {
		int sum;
		int[] range;

		public Result(int sum, int[] range) {
			this.sum = sum;
			this.range = range;
		}

		public int getSum() {
			return sum;
		}

		public int[] getRange() {
			return range;
		}
	}

	public Result findMaxSumSubarray(int[] a) {
		int[] range = new int[2];
		int minIdx = -1, minSum = 0, sum = 0, maxSum = 0;
		Result r = new Result(maxSum, range);

		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum < minSum) {
				minSum = sum;
				minIdx = i;
			}
			if (sum - minSum > maxSum) {
				maxSum = sum - minSum;
				range[0] = minIdx + 1;
				range[1] = i + 1;
				r = new Result(maxSum, range);
			}
		}
		return r;
	}
	
	private static void print(Result r) {
		System.out.println("Sum=" + r.getSum() + ", {" + r.getRange()[0] + ","
				+ r.getRange()[1] + ")");
	}
	public static void main(String[] args) {
		// −2,1,−3,4,−1,2,1,−5,4
		int a[] = new int[] { -2, -3, 1,  4, -10, 2, 1, -5, 4 };
		//a = new int[] { -2, -3, 6, -4, 5};
		MaximumSumSubarray m = new MaximumSumSubarray();
		print(m.findMaxSumSubarray(a));
	}
}