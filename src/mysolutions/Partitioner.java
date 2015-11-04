package mysolutions;

public class Partitioner {

	public int partition(int[] data, int low, int high) {
		int pivot = data[high];
		int i = low; // place for swapping
		for(int j=low; j < high ; j++) {
			if ( data[j] <= pivot ) {
				swap(data,i,j);
				i++;
			}
		}
		swap(data, i, high);
		return i;
	}
	
	void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

}
