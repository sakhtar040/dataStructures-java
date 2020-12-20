package org.datastructures.java;

public class Sorting {
    //Bubble Sort --- n^2
    public void bubbleSort(){
        int[] array = {9,1,8,-1,7,2,6,3,5,4};
        boolean swapped;
        do {
            swapped = false;
            for(int i=0; i<array.length-1; i++){
                if(array[i] > array[i+1]){
                    swap(array, i, i+1);
                    swapped = true;
                }
            }
        }while(swapped);
        printArray("Bubble Sort", array);
    }

    //Selection Sort --- n^2
    public void selectionSort(){
        int[] array = {9,1,8,-1,7,2,6,3,5,4};
        for(int i=0; i<array.length; i++){
            int minIndex = i;
            for(int j=i+1; j<array.length; j++){
                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
        printArray("Selection Sort", array);
    }

    //Insertion Sort --- n^2
    public void insertionSort(){
        int[] array = {1,9,8,-1,7,2,6,3,5,4};
        for(int i=1; i<array.length; i++){
            int temp = array[i];
            int j;
            for(j=i; j>0 && array[j-1] > temp; j--){
                array[j] = array[j-1];
            }
            array[j] = temp;
        }
        printArray("Insertion Sort", array);
    }

    //Shell Sort --- (nlog(n))^2
    public void shellSort(){
        int[] array = {1,9,8,-1,7,2,6,3,5,4};
        for(int gap=array.length/2; gap>0; gap /= 2){
            for(int i=gap; i<array.length; i++){
                int temp = array[i];
                int j = i;
                while(j>=gap && array[j-gap] > temp){
                    array[j] = array[j-gap];
                    j = j-gap;
                }
                array[j] = temp;
            }
        }
        printArray("Shell Sort", array);
    }

    //Merge Sort --- nlog(n)
    public void mergeSort(int[] array, int start, int end){
        if(end-start < 2){
            return;
        }

        int mid = (start+end)/2;
        mergeSort(array, start, mid);
        mergeSort(array, mid, end);
        merge(array, start, mid, end);
    }

    public void merge(int[] array, int start, int mid, int end){
        if(array[mid - 1] <= array[mid]){
            return;
        }
        int i = start;
        int j = mid;
        int tempIndex = 0;
        int[] tempArray = new int[end-start];

        while(i<mid && j<end){
            tempArray[tempIndex++] = array[i] <= array[j] ? array[i++] : array[j++];
        }
        System.arraycopy(array, i, array, start+tempIndex, mid-i);
        System.arraycopy(tempArray, 0, array, start, tempIndex);
    }

    //Quick Sort --- nlog(n)
    public void quickSort(int[] array, int start, int end){
        if(end-start < 2){
            return;
        }

        int pivotIndex = partition(array, start, end);
        quickSort(array, start, pivotIndex);
        quickSort(array, pivotIndex+1, end);
    }

    public int partition(int[] array, int start, int end){
        int pivot = array[start];
        int i = start;
        int j = end;

        while(i<j){
            //Empty Loop Body
            while(i<j && array[--j] >= pivot);
            if(i<j){
                array[i] = array[j];
            }

            //Empty Loop Body
            while(i<j && array[++i] <= pivot);
            if(i<j){
                array[j] = array[i];
            }
        }

        array[j]=pivot;
        return j;
    }

    //Counting Sort --- n
    public void countingSort(){
        int[] array = {2,3,4,2,5,7,8,9,10,8};
        int min = 1;
        int max = 10;

        int[] tempArray = new int[(max-min)+1];

        for(int i=0; i<array.length; i++){
            tempArray[array[i]-min]++;
        }

        int j=0;
        for(int i=min; i<=max; i++){
            while(tempArray[i-min] > 0){
                array[j++] = i;
                tempArray[i-min]--;
            }
        }
        printArray("Counting Sort", array);
    }

    //Radix Sort --- n
    public void radixSort(){
        int[] array = {4725, 4586, 1330, 8792, 1594, 5792};
        int radix = 10;
        int width = 4;

        for(int i=0; i<width; i++){
            radixSingleSort(array, i, radix);
        }

        printArray("Radix Sort", array);
    }

    public void radixSingleSort(int[] array, int position, int radix){
        int numItems = array.length;
        int[] countArray = new int[radix];

        for(int value: array){
            countArray[getDigit(position, value, radix)]++;
        }

        //Adjust the count array
        for(int j=1; j<radix; j++){
            countArray[j] += countArray[j-1];
        }

        int[] tempArray = new int[numItems];
        for(int tempIndex = numItems-1; tempIndex >= 0; tempIndex--){
            tempArray[--countArray[getDigit(position, array[tempIndex], radix)]] = array[tempIndex];
        }

        for(int tempIndex = 0; tempIndex < numItems; tempIndex++){
            array[tempIndex] = tempArray[tempIndex];
        }

    }

    public int getDigit(int position, int value, int radix){
        return value / (int) Math.pow(10, position) % radix;
    }

    //Radix Sort Example
    public void radixSortExample(){
        String[] array = {"bcdef", "dbaqc", "abcde", "omadd", "bbbbb"};
        int radix = 26;
        int width = 5;

        for(int i=width-1; i>=0; i--){
            radixSingleSortExample(array, i, radix);
        }

        System.out.println("=====Radix Sort Example=====");
        for (String j : array) {
            System.out.println(j);
        }
        System.out.println("========================");
    }

    public void radixSingleSortExample(String[] array, int position, int radix){
        int numItems = array.length;
        int[] countArray = new int[radix];

        for(String value: array){
            countArray[getDigitExample(position, value)]++;
        }

        //Adjust the count array
        for(int j=1; j<radix; j++){
            countArray[j] += countArray[j-1];
        }

        String[] tempArray = new String[numItems];
        for(int tempIndex = numItems-1; tempIndex >= 0; tempIndex--){
            tempArray[--countArray[getDigitExample(position, array[tempIndex])]] = array[tempIndex];
        }

        for(int tempIndex = 0; tempIndex < numItems; tempIndex++){
            array[tempIndex] = tempArray[tempIndex];
        }

    }

    public int getDigitExample(int position, String value){
        return value.charAt(position) - 'a';
    }

    public void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void printArray(String sorting, int[] array){
        System.out.println("====="+sorting+"=====");
        for (int j : array) {
            System.out.println(j);
        }
        System.out.println("========================");
    }
}
