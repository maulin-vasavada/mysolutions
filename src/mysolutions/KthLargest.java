package mysolutions;

import java.util.Arrays;

import mysolutions.Heap.Type;

public class KthLargest {

	public int getKthLargest(int[] input, int k) {
		Heap heap = new Heap(Type.MAX, input.length);
		for (int i = 0; i < input.length; i++) {
			heap.insert(input[i]);
			heap.print();
			System.out.println("*****");
		}

		int kthLargest = -1;
		for (int j = 0; j < k; j++) {
			kthLargest = heap.delete();
		}
		return kthLargest;
	}
	
	public int getKthLargest2(int[] input, int k) {
		Heap heap = new Heap(Type.MIN, k);
		for (int i = 0; i < k; i++) {
			heap.insert(input[i]);
			heap.print();
			System.out.println("*****");
		}
		
		for(int i=k; i<input.length;i++) {
			if ( input[i] > heap.top() ) {
				System.out.println("replacing "+heap.top()+" with "+input[i]);
				heap.delete();
				heap.insert(input[i]);
			}
		}

		return heap.top();
	}
	
	public int getKthLargest3(int[] input, int k) {
		Partitioner partitioner = new Partitioner();
		
		int pivotIdx = -1;
		int low = 0;
		int high = input.length-1;
		int kthLargestIdx = input.length - k;
	
		while ( pivotIdx != kthLargestIdx ) {
			pivotIdx = partitioner.partition(input, low, high);
			if ( pivotIdx < kthLargestIdx ) {
				low = pivotIdx+1;
			} else if ( pivotIdx > kthLargestIdx ) {
				high = pivotIdx-1;
			}
		}
		return input[pivotIdx];
	}

	public static void main(String[] args) {
		int[] input = new int[] { 95, 248, 72, 2, 41, 23, 5, 98, 24, 67, 53 };
		int k = 7;

		KthLargest kthLargest = new KthLargest();
		int result1 = kthLargest.getKthLargest(input, k);
		int result2 = kthLargest.getKthLargest2(input, k);
		int result3 = kthLargest.getKthLargest3(input, k);
		Arrays.sort(input);
		System.out.println(k + " th Largest =" + result1+","+result2
				+ ","+result3+ ", match with expected? "
				+ ( input[input.length - k] == result1 && result1 == result2) );
	}
}
