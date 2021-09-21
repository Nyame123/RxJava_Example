package com.example.myrxjava.ordinal.medium;

/**
 * Multiplication on Array : Range update query in O(1)
 * Difficulty Level : Medium
 * Last Updated : 04 May, 2021
 * Consider an array A[] of integers and the following two types of queries.
 * <p>
 * <p>
 * update(l, r, x): multiply x to all values from A[l] to A[r] (both inclusive).
 * printArray(): Prints the current modified array.
 * Examples:
 * <p>
 * <p>
 * Input: A[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
 * update(0, 2, 2)
 * update(1, 4, 3)
 * print()
 * update(4, 8, 5)
 * print()
 * Output: 2 6 6 3 15 5 5 5 5 1
 * Explanation:
 * The query update(0, 2, 2)
 * multiply 2 to A[0], A[1] and A[2].
 * After update, A[] becomes {2, 2, 2, 1, 1, 1, 1, 1, 1, 1}
 * Query update(1, 4, 3) multiply 3 to A[1],
 * A[2], A[3] and A[4]. After update, A[] becomes
 * {2, 6, 6, 3, 3, 1, 1, 1, 1, 1}.
 * Query update(4, 8, 5) multiply 5, A[4] to A[8].
 * After update, A[] becomes {2, 6, 6, 3, 15, 5, 5, 5, 5, 1}.
 * <p>
 * Input: A[] = {10, 5, 20, 40}
 * update(0, 1, 10)
 * update(1, 3, 20)
 * update(2, 2, 2)
 * print()
 * Output: 100 1000 800 800
 * <p>
 * https://www.geeksforgeeks.org/multiplication-on-array-range-update-query-in-o1/?ref=rp
 **/
public class MultiplicationOnArray {

    public static void main(String[] args) {
        // Array to be updated
        int ar[] = {10, 5, 20, 40};
        int n = ar.length;

        // Create and fill mul and div Array
        int[] mul = new int[n + 1];
        int[] div = new int[n + 1];

        multiplyRange(mul, div, n + 1);

        update(0, 1, 10, mul, div);
        update(1, 3, 20, mul, div);
        update(2, 2, 2, mul, div);

        initializeArray(n + 1, div, mul);

        printArray(ar, mul);
    }


    static void multiplyRange(int[] mul, int[] div, int i2) {
        for (int i = 0; i < i2; i++) {
            mul[i] = div[i] = 1;
        }

    }

    private static void initializeArray(int n, int[] div, int[] mul) {

        for (int i = 1; i < n; i++) {
            mul[i] = (mul[i] * mul[i - 1]) / div[i];
        }
    }

    static void update(int l, int r, int x, int[] mul,int[] div) {
        mul[l] *= x;
        div[r + 1] *= x;

    }


    static void printArray(int[] arr, int[] mul) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * mul[i];

            System.out.print(arr[i] + " ");

        }

    }

}

class GFG {

    // Creates a mul[] array for A[] and returns
// it after filling initial values.
    static void initialize(int mul[],
                           int div[], int size) {

        for (int i = 1; i < size; i++) {
            mul[i] = (mul[i] * mul[i - 1]) / div[i];
        }
    }

    // Does range update
    static void update(int l, int r, int x,
                       int mul[], int div[]) {
        mul[l] *= x;
        div[r + 1] *= x;
    }

    // Prints updated Array
    static void printArray(int ar[], int mul[],
                           int div[], int n) {
        for (int i = 0; i < n; i++) {
            ar[i] = ar[i] * mul[i];
            System.out.print(ar[i] + " ");
        }
    }

    // Driver code;
    public static void main(String[] args) {
        // Array to be updated
        int ar[] = {10, 5, 20, 40};
        int n = ar.length;

        // Create and fill mul and div Array
        int[] mul = new int[n + 1];
        int[] div = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            mul[i] = div[i] = 1;
        }

        update(0, 1, 10, mul, div);
        update(1, 3, 20, mul, div);
        update(2, 2, 2, mul, div);

        initialize(mul, div, n + 1);

        printArray(ar, mul, div, n);
    }
}
