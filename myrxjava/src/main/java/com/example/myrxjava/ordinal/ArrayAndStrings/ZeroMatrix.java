package com.example.myrxjava.ordinal.ArrayAndStrings;

import java.util.ArrayList;
import java.util.List;

public class ZeroMatrix {
    public static void main(String[] args) {
        int[][] img = {
                new int[]{4, 8, 1, 2},
                new int[]{0, 6, 5, 6},
                new int[]{14, 15, 3, 7},
                new int[]{9, 12, 13, 16}
        };
        zeroMatrix(img);
    }

    //Time Complexity = O(n^2)
    //Space Complexity = O(n)
    static void zeroMatrix(int[][] img) {

        List<Position> zeroPos = new ArrayList<>();
        int n = img.length;
        int m = img[0].length;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                if (img[i][j] == 0) {
                    zeroPos.add(new Position(i, j));
                }
            }
        }


        for (int i = 0; i < zeroPos.size(); i++) {
            Position position = zeroPos.get(i);
            for (int j = 0; j < n; j++) {
                img[j][position.col] = 0;
            }
            for (int j = 0; j < m; j++) {
                img[position.row][j] = 0;
            }
        }

        printImage(img);
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


    static class Position {
        int row;
        int col;

        Position(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

}

