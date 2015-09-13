package me.ramswaroop.common;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * <p/>
 * A HEAP is a specialized tree-based ABSTRACT DATA TYPE that satisfies the heap property:
 * min-heap: All non-leaf elements are either smaller than or equal to their left and right child.
 * max-heap: All non-leaf elements are either greater than or equal to their left and right child.
 * <p/>
 * Often implemented as an array, where the children of the element at index i are at index
 * 2i+1 (left child) and 2i+2 (right child).
 * <p/>
 * The first element (minimum or maximum, depending on chosen order) can be found in O(1).
 * Each successor can be found in O(log n). The algorithm in minHeapify() takes O(log n) time
 * Therefore, buildMinHeap() would take O(n log n) time BUT IF OBSERVED CAREFULLY IT TAKES 0(N) TIME.
 * <p/>
 * Used in the HeapSort algorithm. Also can be used to implement a PriorityQueue.
 *
 * @author: ramswaroop
 * @date: 8/2/15
 * @time: 11:57 AM
 * @see: http://staff.ustc.edu.cn/~csli/graduate/algorithms/book6/chap07.htm
 */
public class MinHeap {

    int[] heap;
    int size;

    public MinHeap(int[] heap) {
        this.size = heap.length;
        this.heap = Arrays.copyOf(heap, size);
    }

    /**
     * Makes the array {@param a} satisfy the min heap property starting from
     * {@param index} till the end of array.
     * <p/>
     * Time complexity: O(log n).
     *
     * @param index
     */
    public void minHeapify(int index) {
        int smallest = index;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        if (leftIndex < size && heap[index] > heap[leftIndex]) {
            smallest = leftIndex;
        }
        if (rightIndex < size && heap[smallest] > heap[rightIndex]) {
            smallest = rightIndex;
        }

        if (smallest != index) {
            swap(index, smallest);
            minHeapify(smallest);
        }
    }

    /**
     * Converts array {@param a} in to a min heap.
     * <p/>
     * Time complexity: O(n) and is not O(n log n).
     */
    public void buildMinHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    public void insert(int elem) {
        heap = Arrays.copyOf(heap, size + 1);
        int i = size;
        int parentIndex = (int) Math.ceil((i / 2) - 1);
        while (i > 0 && elem < heap[parentIndex]) {
            heap[i] = heap[parentIndex];
            i = parentIndex;
            parentIndex = (int) Math.ceil((parentIndex / 2) - 1);
        }
        heap[i] = elem;
        size++;
    }

    public int findMin() {
        if (size == 0) {
            return -1;
        } else {
            return heap[0];
        }
    }

    public int extractMin() {
        if (size == 0) return -1;

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        minHeapify(0);
        return min;
    }

    public int getSize() {
        return size;
    }

    public int[] getHeap() {
        return heap;
    }

    public void printHeap() {
        if (heap == null)
            System.out.print("null");
        int iMax = size - 1, i;
        if (iMax == -1)
            System.out.print("[]");

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (i = 0; i < iMax; i++) {
            b.append(heap[i]);
            b.append(", ");
        }
        System.out.println(b.append(heap[i]).append(']').toString());
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }

    // test cases
    public static void main(String[] args) {
        int[] a = new int[]{2, 4, 5, 1, 6, 7, 8};
        MinHeap minHeap = new MinHeap(a);
        minHeap.printHeap();
        minHeap.buildMinHeap();
        minHeap.printHeap();
        minHeap.extractMin();
        minHeap.printHeap();
        minHeap.insert(0);
        minHeap.printHeap();
    }
}
