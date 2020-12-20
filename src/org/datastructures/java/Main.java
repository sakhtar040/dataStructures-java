package org.datastructures.java;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        int factorial = main.factorial(10);
        System.out.println("factorial: " + factorial);

        Sorting sorting = new Sorting();
        //Bubble Sort --- n^2
        sorting.bubbleSort();
        //Selection Sort --- n^2
        sorting.selectionSort();
        //Insertion Sort --- n^2
        sorting.insertionSort();
        //Shell Sort --- (nlog(n))^2
        sorting.shellSort();

        //Merge Sort --- nlog(n)
        int[] array = {1,9,8,-1,7,2,6,3,5,4};
        sorting.mergeSort(array, 0, array.length);
        sorting.printArray("Merge Sort", array);

        //Quick Sort --- nlog(n)
        int[] array1 = {1,9,8,-1,7,2,6,3,5,4};
        sorting.quickSort(array1, 0, array1.length);
        sorting.printArray("Quick Sort", array1);

        //Counting Sort --- n
        sorting.countingSort();

        //Radix Sort --- n
        sorting.radixSort();
        sorting.radixSortExample();
    }

    public int factorial(int data){
        return data == 0 ? 1 : data * factorial(data-1);
    }
}
