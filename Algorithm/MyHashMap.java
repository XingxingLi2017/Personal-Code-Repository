import java.util.Arrays;


public class MyHashMap<K, V> {
	private Object[] array;
	private static final int DEFAULT_CAPACITY = 16;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private int size;
	private float loadFactor;

	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	public MyHashMap(int capacity, float loadFactor) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Capacity can't be less than 0.");
		}
		array = new Object[capacity];
		this.size = 0;
		this.loadFactor = loadFactor;

	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public V put(K key, V value) {
		int index = hash(key) % array.length;
		// empty bucket
		if (array[index] == null) {
			array[index] = new Node(key, value);
			size++;
			return null;
		} else { // non-empty bucket
			// check if the key exists
			Node<K, V> curr = (Node<K, V>)array[index];
			while (curr != null) {
				if (equalsKey(key,curr.getKey())) {
					break;
				}
				curr = curr.next;
			}
			// replace node
			if (curr != null) {
				V old = curr.getValue();
				curr.setValue(value);
				return old;
			} else { // add node
				Node<K, V> newer = new Node(key, value);
				newer.next = (Node)array[index];
				array[index] = newer;
				size++;
				// rehashing
				if (needRehash()) {
					rehash();
				}
				return null;
			}
		}
	}
	public V get(K key) {
		int index = hash(key) % array.length;
		if (array[index] == null) return null;
		Node<K, V> curr = (Node)array[index];
		while (curr != null) {
			if (equalsKey(key, curr.getKey())) {
				return curr.getValue();
			}
			curr = curr.next;
		}
		return null;
	}
	private boolean needRehash() {
		return (size / (array.length + 0.0)) >= loadFactor;
	}

	private void rehash() {
		Object[] old = array;
		array = new Object[old.length * 2];
		for (int i = 0 ; i < old.length ; i++) {
			if (old[i] != null) {
				Node<K, V> curr = (Node<K, V>)old[i];
				while (curr != null) {
					put(curr.getKey(), curr.getValue());
					curr = curr.next;
				}
			}
		}
	}

	private boolean equalsKey(K k1, K k2) {
		return k1 == k2 || (k1 != null && k1.equals(k2));
	}
	// the return value should be greater than 0
	private int hash(K key) {
		if (key == null) return 0;
		int hashCode = key.hashCode();
		hashCode = hashCode & 0x7fffffff;
		return hashCode ^ (hashCode>>16);
	}
	public void clear() {
		Arrays.fill(array, null);
		size = 0;
	}
	public boolean containsKey(K key) {
		return get(key) == null ? false : true;
	}
	public boolean containsValue(V value) {
		if (isEmpty()) return false;
		for (int i = 0 ; i < array.length ; i++) {
			Node<K, V> curr = (Node<K, V>) array[i];
			while (curr != null) {
				if (equalsValue(value, curr.getValue())) {
					return true;
				}
				curr = curr.next;
			}
		}
		return false;
	}
	private boolean equalsValue(V v1, V v2) {
		return v1 == v2 || (v1 != null && v1.equals(v2));
	}
	public V remove (K key) {
		int index = hash(key) % array.length;
		if (array[index] == null) return null;
		Node<K, V> curr = (Node) array[index];
		if (equalsKey(curr.getKey(), key)) {
			array[index] = curr.next;
			curr.next = null;
			return curr.getValue();
		} else {
			Node<K, V> prev = curr;
			while (curr != null) {
				if (equalsKey(curr.key, key)) {
					prev.next = curr.next;
					curr.next = null;
					return curr.getValue();
				}
			}
		}
		return null;
	}
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0 ; i < array.length ; i++) {
			Node<K, V> curr = (Node<K, V>) array[i];
			while (curr != null) {
				sBuilder.append("["+curr.getKey() + ", "+ curr.getValue()+"]\n");
				curr = curr.next;
			}
		}
		return sBuilder.toString();
	}

	 public static class Node<K, V> {
		private final K key;
		private V value;
		Node<K, V> next;
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
	}
}
