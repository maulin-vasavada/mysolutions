package mysolutions;

public class QuickSort2 {

	private Partitioner partitioner = new Partitioner();
	
	public void sort(int[] data) {
		quicksort(data, 0, data.length-1);
	}
	
	void quicksort(int[] data, int low, int high) {
		if ( low < high ) {
			int p = partitioner.partition(data, low, high);
			quicksort(data, low, p-1);
			quicksort(data, p+1, high);
		}
	}
		
	static void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + ",");
		}
		System.out.println("***************");
	}
	
	public static void main(String[] args) {
		int[] data = new int[] { 95, 248, 72, 2, 41, 23, 5, 98, 24, 67, 53 };
		QuickSort2 qs = new QuickSort2();
		qs.sort(data);
		print(data);
	}
}