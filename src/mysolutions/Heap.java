package mysolutions;

public class Heap {

	public enum Type {
		MIN, MAX;
	}

	int[] data = null;
	int insertIdx;
	Type type = Type.MIN;

	public Heap() {
		this(Type.MIN, 100);
	}

	public Heap(Type type, int size) {
		this.type = type;
		this.data = new int[size];
	}

	public Heap(Type type, int[] input) {
		this(type, input.length);
		this.data = input;
		heapify();
		print();
	}

	public void heapify() {
		for (int i = data.length / 2 - 1; i >= 0; i--) {
			heapifyDown(i);
		}
	}

	void heapifyDown(int idx) {
		if ( idx < insertIdx ) {
			int heapifyIdx = getHeapifyIdx(idx);
			if (heapifyIdx != idx) {
				swap(idx, heapifyIdx);
				heapifyDown(heapifyIdx);
			}			
		}
	}

	int getHeapifyIdx(int idx) {

		int leftChildIdx = 2 * idx + 1;
		int rightChildIdx = 2 * (idx + 1);

		int heapifyIdx = idx;

		if (type == Type.MIN) {
			heapifyIdx = getHeapifyIdxForMin(heapifyIdx, leftChildIdx,
					rightChildIdx);
		} else {
			heapifyIdx = getHeapifyIdxForMax(heapifyIdx, leftChildIdx,
					rightChildIdx);
		}
		return heapifyIdx;
	}

	int getHeapifyIdxForMin(int heapifyIdx, int leftChildIdx, int rightChildIdx) {
		if (leftChildIdx < insertIdx && data[heapifyIdx] > data[leftChildIdx]) {
			heapifyIdx = leftChildIdx;
		}

		if (rightChildIdx < insertIdx
				&& data[heapifyIdx] > data[rightChildIdx]) {
			heapifyIdx = rightChildIdx;
		}
		return heapifyIdx;
	}

	int getHeapifyIdxForMax(int heapifyIdx, int leftChildIdx, int rightChildIdx) {
		if (leftChildIdx < insertIdx && data[heapifyIdx] < data[leftChildIdx]) {
			heapifyIdx = leftChildIdx;
		}

		if (rightChildIdx < insertIdx
				&& data[heapifyIdx] < data[rightChildIdx]) {
			heapifyIdx = rightChildIdx;
		}
		return heapifyIdx;
	}

	void heapifyUp(int idx) {
		int parentIdx = (int) (idx - 1) / 2;
		boolean doSwap = false;
		if (type == Type.MIN) {
			if (data[idx] < data[parentIdx]) {
				doSwap = true;
			}
		} else {
			if (data[idx] > data[parentIdx]) {
				doSwap = true;
			}
		}
		if (doSwap) {
			swap(idx, parentIdx);
			if (parentIdx != 0) {
				heapifyUp(parentIdx);
			}
		}
	}

	public void insert(int value) {
		if (insertIdx == data.length) {
			throw new RuntimeException("heap full");
		}

		data[insertIdx] = value;
		heapifyUp(insertIdx);
		insertIdx++;
	}

	public int delete() {
		int idx = 0;
		int ret = data[idx];
		insertIdx--;
		data[idx] = data[insertIdx];
		data[insertIdx] = 0;
		heapifyDown(idx);
		return ret;
	}

	private void swap(int idx1, int idx2) {
		int temp = data[idx1];
		data[idx1] = data[idx2];
		data[idx2] = temp;
	}

	void print() {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + ",");
		}
		System.out.println();
	}

	public int top() {
		return data[0];
	}

	public static void main(String[] args) {
		int[] input = new int[] { 95, 248, 72, 2, 41, 23, 5, 98 };
		Heap heap = new Heap(Type.MAX, input.length);
		for (int i = 0; i < input.length; i++) {
			heap.insert(input[i]);
			heap.print();
			System.out.println("*****");
		}
		System.out.println("top=" + heap.top());
		System.out.println("heap.delete=" + heap.delete());
		heap.print();
		heap.insert(1);
		heap.print();
	}
}