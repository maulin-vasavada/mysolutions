package mysolutions;

public class PascalsTriangle {

	public void triangle(int rows) {
		int[] previous = null;
		int[] current = null;

		for (int i = 1; i <= rows; i++) {
			current = new int[i];
			if (previous == null) {
				current[0] = 1;
			} else {
				current[0] = 1;
				current[i - 1] = 1;
				for (int j = 1; j <= i - 2; j++) {
					current[j] = previous[j - 1] + previous[j];
				}
			}
			print(current);
			previous = current;
		}
	}

	/*
	 * 1 1 1 1 2 1 1 3 3 1 1 4 6 4 1
	 */
	public void triangle2(int rows) {
		int[] previous = null;
		int[] current = null;

		if ( rows <= 0 ) {
			return;
		}
		
		current = new int[1];
		current[0] = 1;
		print(current);
		
		if ( rows == 1 ) {
			return;
		}
		
		current = new int[2];
		current[0] = 1;
		current[1] = 1;
		print(current);

		if ( rows == 2 ) {
			return;
		}
		
		previous = current;
		
		for (int i = 3; i <= rows; i++) {
			current = new int[i];
			if (previous == null) {
				current[0] = 1;
			} else {
				current[0] = 1;
				if (i % 2 != 0) {
					int middle = i / 2;
					current[middle] = previous[middle - 1] + previous[middle];
				}
				for (int j = 1; j < i / 2; j++) {
					current[j] = previous[j - 1] + previous[j];
					current[i - 1 - j] = current[j];
				}
				current[i-1] = 1;
			}
			print(current);
			previous = current;
		}
	}

	void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		PascalsTriangle pt = new PascalsTriangle();
		pt.triangle(10);
	}
}