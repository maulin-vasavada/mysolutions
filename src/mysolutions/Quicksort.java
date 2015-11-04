package mysolutions;

public class Quicksort {

	public void sort(int[] data) {
		quicksort(data, 0, data.length - 1);
	}

	void quicksort(int[] data, int left, int right) {
		if ( left >= right ) {
			return;
		}
		
		int pivotIdx = partition(data, left, right);
		quicksort(data, left, pivotIdx);
		quicksort(data, pivotIdx + 1, right);
	}

	int partition(int[] data, int left, int right) {
		
		int pivot = data[(left + right) / 2];

		while (left <= right) {
			while (data[left] < pivot) {
				left++;
			}
			while (data[right] > pivot) {
				right--;
			}

			if (left <= right) {
				swap(data, left, right);
				if (data[left] == pivot) {
					right--;
				} else if (data[right] == pivot) {
					left++;
				} else {
					left++;
					right--;
				}
			}
		}
		return left;
	}

	void swap(int[] data, int idx1, int idx2) {
		int temp = data[idx1];
		data[idx1] = data[idx2];
		data[idx2] = temp;
	}

	static void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + ",");
		}
		System.out.println("***************");
	}

	public static void main(String[] args) {
		int[] data = new int[] { 95, 248, 72, 2, 41, 23, 5, 98, 24, 67, 53 };
		Quicksort s = new Quicksort();
		s.sort(data);
		print(data);
	}
}
