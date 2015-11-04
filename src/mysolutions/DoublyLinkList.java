package mysolutions;

public class DoublyLinkList<T> {
	DoublePointerNode<T> head;
	DoublePointerNode<T> tail;
	int count=0;
	public DoublePointerNode<T> add(T data) {
		DoublePointerNode<T> n = new DoublePointerNode<T>(data, null, null);
		if ( head == null ) {
			head = n;
			tail = head;
		} else {
			tail.next = n;
			n.previous = tail;
			tail = n;
		}
		count++;
		return n;
	}
	
	public void removeLast() {
		if ( head == tail ) {
			head = null;
			tail = null;
		} else {
			tail = tail.previous;
			tail.next = null;			
		}
		count--;
	}
	
	public int size() {
		return count;
	}
	
	public void moveToFront(DoublePointerNode<T> n) {
		if ( n == head ) {
			return;
		}
		if ( n == tail ) {
			tail = n.previous;
		}
		n.previous.next = n.next;
		if ( n.next != null ) {
			n.next.previous = n.previous;			
		}
		n.next = head;
		n.previous = null;
		head.previous = n;
		head = n;
	}
	
	public String print() {
		DoublePointerNode<T> current = head;
		StringBuilder sb = new StringBuilder();
		while ( current != null ) {
			DoublePointerNode<T> prev = current.previous;
			if ( prev != null ) {
				sb.append(String.valueOf(prev.getData())+" <-- ");
			}
			sb.append(String.valueOf(current.getData()));
			DoublePointerNode<T> next = current.next;
			if (next != null ) {
				sb.append(" --> "+String.valueOf(next.getData()));
			}
			sb.append(" , ");
			current = current.next;
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return print();
	}
	
	public static void main(String[] args) {
		DoublyLinkList<Integer> dll = new DoublyLinkList<>();
		DoublePointerNode<Integer> n1 = dll.add(5);
		DoublePointerNode<Integer> n2 = dll.add(64);
		DoublePointerNode<Integer> n3 = dll.add(12);
		dll.print();
		dll.moveToFront(n2);
		dll.print();
		dll.moveToFront(n3);
		dll.print();
	}
}
