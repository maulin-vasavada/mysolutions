package mysolutions;

public class DoublePointerNode<T> {
	T data;
	DoublePointerNode<T> previous;
	DoublePointerNode<T> next;
	
	public DoublePointerNode(T data) {
		super();
		this.data = data;
		this.previous = null;
		this.next = null;
	}

	public DoublePointerNode(T data, DoublePointerNode<T> previous,
			DoublePointerNode<T> next) {
		super();
		this.data = data;
		this.previous = previous;
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DoublePointerNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(DoublePointerNode<T> previous) {
		this.previous = previous;
	}

	public DoublePointerNode<T> getNext() {
		return next;
	}

	public void setNext(DoublePointerNode<T> next) {
		this.next = next;
	}
}
