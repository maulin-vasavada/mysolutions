package mysolutions.dp;

import java.util.ArrayList;
import java.util.List;

public class LIS {

	public int lisRecursive(int[] data) {
		int max = 0;
		for (int i = 0; i < data.length; i++) {
			int ans = lisStartingWith(i, data);
			if (ans > max) {
				max = ans;
			}
		}
		return max;
	}

	private int lisStartingWith(int startIdx, int[] data) {

		int max = 0;
		for (int secondIdx = startIdx + 1; secondIdx < data.length; secondIdx++) {

			if (data[startIdx] < data[secondIdx]) {
				int ans = lisStartingWith(secondIdx, data);
				if (ans > max) {
					max = ans;
				}
			}
		}
		return max + 1;
	}

	int[] cache;

	public int lisRecursiveWithCache(int[] data) {
		int max = 0;
		cache = new int[data.length];
		List<Integer> output = new ArrayList<>();
		int maxElement = -1;
		for (int i = 0; i < data.length; i++) {
			int ans = lisStartingWithUsingCache(i, data, output);
			if (ans > max) {
				max = ans;
				maxElement = i;
			}
		}
		if (maxElement != -1) {
			output.add(0, data[maxElement]);
		}
		//printOutput(output);
		return max;
	}

	private void printOutput(List<Integer> output) {
		System.out.println("**** START: LIS OUTPUT ****");
		for (int i = 0; i < output.size(); i++) {
			System.out.print(output.get(i) + ",");
		}
		System.out.println();
		System.out.println("**** END: LIS OUTPUT ****");
	}

	private int lisStartingWithUsingCache(int startIdx, int[] data,
			List<Integer> output) {
		if (getFromCache(startIdx) > 0) {
			return getFromCache(startIdx);
		}

		int max = 0;
		int maxElement = -1;
		for (int secondIdx = startIdx + 1; secondIdx < data.length; secondIdx++) {

			if (data[startIdx] < data[secondIdx]) {
				int ans = lisStartingWithUsingCache(secondIdx, data, output);
				if (ans > max) {
					max = ans;
					maxElement = secondIdx;
				}
			}
		}
		putToCache(startIdx, max + 1);
		if (maxElement != -1) {
			output.add(0, data[maxElement]);
		}

		return max + 1;
	}

	private void putToCache(int idx, int ans) {
		cache[idx] = ans;
	}

	private int getFromCache(int idx) {
		return cache[idx];
	}
	
	public int lisIterative(int[] data) {
		int max = 0;
		cache = new int[data.length];
		
		for(int i=data.length-1;i>=0;i--) {
			max=0;
			for(int j=i+1;j<data.length;j++) {
				if ( data[i] < data[j] && cache[j] > max) {
					max = cache[j];
				}
			}
			cache[i] = max+1;
		}
		
		max = 0;
		for(int i=0;i<cache.length;i++) {
			if ( cache[i] > max ) {
				max = cache[i];
			}
		}
		return max;
	}

	static void testRecursive() {
		LIS lis = new LIS();
		int[] data = new int[] { -7, 10, 9, 2, 3, 8, 8, 1 };
		// data = new int[] { 1, 25, 17, 18, 9 };
		long start = System.nanoTime();
		int ans = lis.lisRecursive(data);
		long end = System.nanoTime();
		System.out.println(ans + ", recursive time=" + (end - start));
	}

	static void testRecursiveWithCache() {
		LIS lis = new LIS();
		int[] data = new int[] { -7, 10, 9, 2, 3, 8, 8, 1 };
		// data = new int[] { -7,10,2,4,8 };
		long start = System.nanoTime();
		int ans = lis.lisRecursiveWithCache(data);
		long end = System.nanoTime();
		System.out.println(ans + ", with cache time=" + (end - start));
	}
	
	static void testIterative() {
		LIS lis = new LIS();
		int[] data = new int[] { -7, 10, 9, 2, 3, 8, 8, 1 };
		data = new int[] { -7, 10, 2,4,8 };
		long start = System.nanoTime();
		int ans = lis.lisIterative(data);
		long end = System.nanoTime();
		System.out.println(ans + ", iterative time=" + (end - start));
	}
	public static void main(String[] args) {
//		testRecursive();
//		testRecursiveWithCache();
		testIterative();
	}
}