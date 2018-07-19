package Sort;

import java.util.Random;

public class HeapSort {
    public static void main(String[] args)
    {
        Random rand = new Random();
        int len = 50;
        int[] arr = new int[len];
        // generate random numbers
        for(int i = 0 ; i < len ; i++){
            arr[i] = rand.nextInt(21);
        }

        for(int i = 0 ; i < len ; i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();
        // heap sort
        SimpleHeap heap = new SimpleHeap(arr);
        SimpleHeap heap2 = new SimpleHeap(arr);
        heap.heapify();
        heap.printHeap();
        heap.heapSort();
        arr = heap.getHeap();
        // shift up
        heap2.heapifyShiftUp();
        heap2.printHeap();
        heap2.heapSort();
        arr = heap2.getHeap();

        // print out
        System.out.println();
        for(int i = 0 ; i < len ; i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();

    }

}

// a simple max heap
class SimpleHeap{
    private int[] heap;
    private boolean isHeapified = false;
    public SimpleHeap(int[] arr){
        this.heap = arr;
    }

    // return the heap array
    public int[] getHeap(){
        return heap;
    }

    // heap sort
    public void heapSort(){
        if(isHeapified){
            int end = heap.length;
            while (end > 0){
                swap(0, end-1);
                end--;
                shiftDown(0,end);
            }
        }else{
            System.out.println("the heap is not built up.");
        }
    }

    // check if the heap is built up
    public boolean isHeapified() {
        return isHeapified;
    }

    // build the heap with shiftUP method
    public void heapifyShiftUp(){
        if(heap.length < 2)
            return;
        int end  = 1;
        while(end < heap.length){
            shiftUp(end, 0);
            end++;
        }
        isHeapified = true;
    }

    // build the heap
    public void heapify(){
        // build the heap bottom-up
        int start = getParentNode(heap.length-1);
        while(start >= 0){
            shiftDown(start,heap.length);
            start--;
        }
        isHeapified = true;
    }

    // put the node i into the proper position in the heap;
    // the end indicate the boundary of the shift down range
    private void shiftDown(int i, int end){
        int target = i;
        while (target < end)
        {  
            int l = getLeftChild(target);
            int r = getRightChild(target);
            int nextPosition = target;
            if(l < end && heap[nextPosition] < heap[l]){
                nextPosition = l;
            }
            if(r < end && heap[nextPosition] < heap[r]){
                nextPosition = r;
            }
            if(nextPosition == target)
                return;
            else {
                swap(nextPosition, target);
                target = nextPosition;
            }
        }
    }

    // move a node upwards to the proper position
    private void shiftUp(int i, int start){
        int target = i;
        while(target > start){
            int parent = getParentNode(target);
            if(heap[parent] < heap[target]){
                swap(parent, target);
                target = parent;
            }else
                return;
        }
    }

    private void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int getParentNode(int i){
        return (i-1)/2;
    }
    private int getLeftChild(int i){
        return i*2+1;
    }
    private int getRightChild(int i){
        return i*2+2;
    }
    // print out the heap
    public void printHeap(){
        // get the height of the heap tree
        int treeHeight =(int)Math.ceil(Math.log(heap.length)/Math.log(2));
        // the current depth
        int depth = 0;
        // i is the current node
        for(int i = 0 ; i < heap.length ; i++){
            // now i is the first element of current layer
            if(i == (int)(Math.pow(2, depth)-1)){
                System.out.println();
                // print the prefix spaces, which is 2^(height-depth)
                int prefixSpaces = (int)(Math.pow(2, treeHeight-depth))-2;
                for(int j = 0 ; j < prefixSpaces ; j++){
                    System.out.printf("%s", ".");
                }
                // increase the depth
                depth++;
            }
            // print the nodes in the current layer
            System.out.printf("%s", heap[i]);
            // print the interval spaces, which is 2^(height-depth+1)-1
            int intervalSpaces = (int)(Math.pow(2, treeHeight-depth+2));
            for(int j = 0 ; j < intervalSpaces ; j++){
                System.out.printf("%s",".");
            }
        }
    }
}
