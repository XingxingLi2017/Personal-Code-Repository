import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;


public class Dijkstra {
    public static void main(String[] args)
    {
       Graph g = new Graph();
       GraphNode n1 = new GraphNode(1);
       GraphNode n2 = new GraphNode(2);
       GraphNode n3 = new GraphNode(3);
       GraphNode n4 = new GraphNode(4);
       GraphNode n5 = new GraphNode(5);
       GraphNode n6 = new GraphNode(6);
       n1.neighbors.put(n2, 1);
       n1.neighbors.put(n3, 3);
       g.vertice.add(n1);
       n2.neighbors.put(n1, 1);
       n2.neighbors.put(n3, 4);
       n2.neighbors.put(n4, 6);
       n2.neighbors.put(n5, 2);
       g.vertice.add(n2);
       n3.neighbors.put(n1, 3);
       n3.neighbors.put(n2, 4);
       n3.neighbors.put(n4, 1);
       n3.neighbors.put(n5, 5);
       g.vertice.add(n3);
       n4.neighbors.put(n2, 6);
       n4.neighbors.put(n3, 1);
       n4.neighbors.put(n5, 3);
       g.vertice.add(n4);
       n5.neighbors.put(n2, 2);
       n5.neighbors.put(n3, 5);
       n5.neighbors.put(n4, 3);
       n5.neighbors.put(n6, 2);
       g.vertice.add(n5);
       n6.neighbors.put(n5, 2);
       g.vertice.add(n6);
       MyPriorityQueue pq = new MyPriorityQueue();
       pq.offer(n1, 0);
       List<Node> res = new ArrayList<>();
       while(pq.size() > 0) {
    	   // Expand
    	   Node curr = pq.poll();
    	   // debug info
    	   System.out.println("pq while loop, expande, Key = 3, distance = " + curr.distance);
    	   res.add(curr);
    	   // generate
    	   HashMap<GraphNode, Integer> neighbors = curr.e.neighbors;
    	   for(Iterator<Entry<GraphNode, Integer>> itr = neighbors.entrySet().iterator() ; itr.hasNext() ;) {
    		   Entry<GraphNode, Integer> entry = itr.next();
    		   GraphNode e = entry.getKey();
    		   int weight = entry.getValue();
    		   if(pq.ifExist(e)) {
    			   pq.update(e, curr.distance + weight);
    		   }else {
    			   pq.offer(e, curr.distance + weight);
    		   }
    	   }
       }
       
       for(Node temp : res) {
    	   System.out.println("KEY: " + temp.e.key + ", Distance: "+temp.distance);
       }  
    }
}
class Graph{
	public List<GraphNode> vertice;
	public Graph() {
		vertice = new ArrayList<>();
	}
}
class GraphNode{
	public int key;
	public HashMap<GraphNode, Integer> neighbors; //<GraphNode, weight>
	public GraphNode(int key) {
		this.key = key;
		neighbors = new HashMap<>();
	}
}

class Node{
	public GraphNode e;
	public int distance; // the distance to the source node
	public Node(GraphNode e, int distance) {
		this.e = e;
		this.distance = distance;
	}
}

class MyPriorityQueue{
	private List<Node> heap;
	private HashMap<GraphNode, Integer> hash;
	private int start;
	private int end;
	
	public MyPriorityQueue() {
		heap = new ArrayList<>();
		hash = new HashMap<>();
		start = 0 ;
		end = start;
	}
	
	public void offer(GraphNode node, int distance) {
		// hash.containsKey(e) == true && hash.ifExist(e) == false 
		// => node have be expanded
		if(hash.containsKey(node)) return;
		
		Node e = new Node(node, distance);
		if(end >= heap.size()) heap.add(e);
		else heap.set(end, e);
		hash.put(node, end);
		end++;
		// percolate up
		percolateUp(end-1);
		
		// debug info
		System.out.println("offer: Key = " + node.key + ", position = "+ hash.get(node) + " distance = " + heap.get(hash.get(node)).distance);
	}
	public Node poll() {
		if(end == 0) return null;
		
		Node res = heap.get(start);
		swap(start, end-1);
		end--;
		percolateDown(start);
		
		// denote this node is out
		hash.put(res.e, -1);
		return res;
	}
	public Node peek() {
		if(end == 0) return null;
		return heap.get(start);
	}
	public boolean ifExist(GraphNode e) {
		return hash.containsKey(e) && hash.get(e) >= 0;
	}
	public void update(GraphNode e, int distance) {
		if(hash.containsKey(e) && hash.get(e) >= 0) {
			int position = hash.get(e);
			if(distance < heap.get(position).distance) {
				heap.get(position).distance = distance;
				percolateUp(position);
			}
			// debug info
 			System.out.println("update: Key = " + e.key + ", position = "+ position + " distance = " + heap.get(position).distance + ", new distance = " + distance);
		}
	}
	public int size() {
		return end - start;
	}
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private void percolateUp(int i) {
		int target = i;
		while(target >= start) {
			int nextPosition = getParent(i); 
			if(nextPosition >= start && 
					heap.get(nextPosition).distance > heap.get(target).distance) {
				swap(nextPosition, target);
				target = nextPosition;
			}
			else
				return;
		}
	}
	private void percolateDown(int i) {
		int target = i;
		while(target < end) {
			int l = getLeft(target);
			int r = getRight(target);
			int nextPosition = target;
			if(l < end && heap.get(l).distance < heap.get(nextPosition).distance) {
				nextPosition = l;
			}
			if(r < end && heap.get(r).distance < heap.get(nextPosition).distance) {
				nextPosition = r;
			}
			if(nextPosition == target) return;
			else {
				swap(nextPosition, target);
				target = nextPosition;
			}
		}
	}
	private void swap(int i, int j) {
		Node nodeI = heap.get(i);
		Node nodeJ = heap.get(j);
		hash.put(nodeI.e, j);
		hash.put(nodeJ.e, i);
		heap.set(i, nodeJ);
		heap.set(j, nodeI);
	}
	private int getLeft(int i) {
		return (i * 2) + 1;
	}
	private int getRight(int i) {
		return (i * 2) + 2;
	}
	private int getParent(int i) {
		return (i - 1) / 2;
	}
}