package mysolutions;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	Map<Object, DoublePointerNode<Object>> map = new HashMap<>();
	DoublyLinkList<Object> dll = new DoublyLinkList<>();
	int MAX_SIZE;
	
	public LRUCache() {
		this(5);
	}
	public LRUCache(int size) {
		this.MAX_SIZE = size;
	}

	public void put(Object key, Object data) {
		int currentSize = dll.size();
		if (currentSize == MAX_SIZE) {
			dll.removeLast();
		}

		DoublePointerNode<Object> value = dll.add(data);
		map.put(key, value);
	}
	
	public Object get(Object key) {
		DoublePointerNode<Object> value = map.get(key);
		if (value != null) {
			dll.moveToFront(value);
			return value.getData();
		} else {
			return null;
		}
	}

	public void printValues() {
		dll.print();
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(3);
		put(cache, 1);
		put(cache, 2);
		put(cache, 3);
		get(cache, 3);
		put(cache, 4);
		put(cache, 5);
		get(cache, 5);
		put(cache, 6);
		put(cache, 7);
	}

	static void put(LRUCache cache, int i) {
		cache.put(Integer.valueOf(i), String.valueOf(i));
		cache.printValues();
	}
	
	static Object get(LRUCache cache, int i) {
		Object value = cache.get(Integer.valueOf(i));
		cache.printValues();
		return value;
	}
}