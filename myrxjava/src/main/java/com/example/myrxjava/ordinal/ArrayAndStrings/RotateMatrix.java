package com.example.myrxjava.ordinal.ArrayAndStrings;

public class RotateMatrix {

    public static void main(String[] args) {
        int[][] img = {
                new int[]{4,8,1,2},
                new int[]{10,11,5,6},
                new int[]{14,15,3,7},
                new int[]{9,12,13,16}
        };
        rotate2DImage(img);
    }

    static void rotateImgSwap(int[][] img){
        int n = img.length;
        int m = img[0].length;
        for (int i = 0; i < n/2; i++) {
            int first = i;
            int last = n - i - 1;
            for (int j = first; j < last; j++) {
                int offset = j - first;
                int top = img[first][j];
                //top <- left
                img[first][j] = img[last-offset][first];
                //left <- bottom
                img[last-offset][first] = img[last][last - offset];
                //bottom <- right
                img[last][last - offset] = img[j][last];

                //right <- top
                img[j][last] = top;

            }
        }

        printImage(img);
    }

    //Time complexity = O(n^2)
    //space complexity = O(n^2)
    static void rotate2DImage(int[][] img) {
        int n = img.length;
        int m = img[0].length;
        int[][] rotatedImg = new int[m][n];

        for (int i = 0; i < n; i++) {
            int newCol = n - 1 - i;
            for (int j = 0; j < m; j++) {
                int newRow = j;
                rotatedImg[newRow][newCol] = img[i][j];
                System.out.printf("%d, ", img[i][j]);
            }
            System.out.println();
        }

        printImage(rotatedImg);
    }

    static void printImage(int[][] img) {
        int n = img.length;
        int m = img[0].length;
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%d, ", img[i][j]);
            }
            System.out.println();
        }
    }
}
