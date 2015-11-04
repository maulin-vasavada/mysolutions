package mysolutions.dp;

public class Fibo {

	public int fiboPlainRecursion(int n) {
		if (n == 0 || n == 1) {
			return 1;
		}

		return fiboPlainRecursion(n - 1) + fiboPlainRecursion(n - 2);
	}

	public int fiboRecursionWithCache(int[] fiboCache, int n) {

		if (fiboCache[n] > 0) {
			return fiboCache[n];
		}

		if (n == 0 || n == 1) {
			fiboCache[n] = 1;
			return 1;
		}

		fiboCache[n] = fiboRecursionWithCache(fiboCache, n - 1)
				+ fiboRecursionWithCache(fiboCache, n - 2);
		return fiboCache[n];
	}

	public int fiboIterative(int n) {
		int a = 0;
		int b = 1;
		for (int i = 0; i < n; i++) {
			int temp = a;
			a = b;
			b = temp + b;
		}
		return b;
	}

	static void testPlainRecursion() {
		Fibo f = new Fibo();
		long start = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			System.out.print(f.fiboPlainRecursion(i) + ",");
		}
		long end = System.nanoTime();
		System.out.println("plain recursion time=" + (end - start));
	}

	static void testRecursionWithCache() {
		Fibo f = new Fibo();
		int[] fiboCache = new int[10];
		long start = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			System.out.print(f.fiboRecursionWithCache(fiboCache, i) + ",");
		}
		long end = System.nanoTime();
		System.out.println("recursion with cache time=" + (end - start));
	}

	static void testIterative() {
		Fibo f = new Fibo();
		long start = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			System.out.print(f.fiboIterative(i) + ",");
		}
		long end = System.nanoTime();
		System.out.println("iterative time=" + (end - start));
	}

	public static void main(String[] args) {
		testPlainRecursion();
		testRecursionWithCache();
		testIterative();
	}
}
