package mysolutions;

public class LinkList<T> {

	Node<T> head;
	
	public void add(T data) {
		Node<T> n = new Node<T>(data);
		if ( head == null ) {
			head = n;
		} else {
			Node<T> current = head;
			while( current.next != null ) {
				current = current.next;
			}
			current.next = n;	
		}		
	}
	
	public void print() {
		Node<T> current = head;
		while( current != null ) {
			System.out.println(current.getData());
			current = current.next;
		}
	}
	
	public static void main(String[] args) {
		LinkList<Integer> ll = new LinkList<>();
		ll.add(5);
		ll.add(2);
		ll.add(52);
		ll.print();
	}
}