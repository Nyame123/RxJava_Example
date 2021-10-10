package com.example.myrxjava.combinatoricsGameTheory.hard;

/**
 * Gaussian Elimination to Solve Linear Equations
 * Difficulty Level : Hard
 * Last Updated : 15 Apr, 2021
 * The article focuses on using an algorithm for solving a system of linear equations.
 * We will deal with the matrix of coefficients. Gaussian Elimination does not work
 * on singular matrices (they lead to division by zero).
 * <p>
 * Input: For N unknowns, input is an augmented
 * matrix of size N x (N+1). One extra
 * column is for Right Hand Side (RHS)
 * mat[N][N+1] = {{3.0, 2.0,-4.0, 3.0},
 * {2.0, 3.0, 3.0, 15.0},
 * {5.0, -3, 1.0, 14.0}
 * };
 * Output: Solution to equations is:
 * 3.000000
 * 1.000000
 * 2.000000
 * <p>
 * Explanation:
 * Given matrix represents following equations
 * 3.0X1 + 2.0X2 - 4.0X3 =  3.0
 * 2.0X1 + 3.0X2 + 3.0X3 = 15.0
 * 5.0X1 - 3.0X2 +    X3 = 14.0
 * <p>
 * There is a unique solution for given equations,
 * solutions is, X1 = 3.0, X2 = 1.0, X3 = 2.0,
 * <p>
 * Algorithm:
 * <p>
 * Partial pivoting: Find the kth pivot by swapping rows,
 * to move the entry with the largest absolute value to the
 * pivot position. This imparts computational stability to the algorithm.
 * For each row below the pivot, calculate the factor f which makes
 * the kth entry zero, and for every element in the row subtract the
 * fth multiple of the corresponding element in the kth row.
 * Repeat above steps for each unknown. We will be left with a partial r.e.f. matrix.
 * <p>
 * https://www.geeksforgeeks.org/gaussian-elimination/
 **/
public class GaussianElimination {

    public static int N = 3; // Number of unknowns

    public static void main(String[] args) {
        /* input matrix */
        double mat[][] = {
                {3.0, 2.0, -4.0, 3.0},
                {2.0, 3.0, 3.0, 15.0},
                {5.0, -3, 1.0, 14.0}
        };

        gaussianElimination(mat);
    }

    // function to get matrix content
    static void gaussianElimination(double mat[][]) {

        /* reduction into r.e.f. */
        int singularFlag = forwardElim(mat);

        /* if matrix is singular */
        if (singularFlag != -1) {
            System.out.println("Singular Matrix.");

      /* if the RHS of equation corresponding to
               zero row  is 0, * system has infinitely
               many solutions, else inconsistent*/
            if (mat[singularFlag][N] != 0)
                System.out.print("Inconsistent System.");
            else
                System.out.print(
                        "May have infinitely many solutions.");

            return;
        }

    /* get solution to system and print it using
           backward substitution */
        backSub(mat);
    }

    // function for elementary operation of swapping two
    // rows
    static void swapRow(double mat[][], int i, int j) {
        // printf("Swapped rows %d and %d\n", i, j);

        for (int k = 0; k <= N; k++) {
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }

    // function to print matrix content at any stage
    static void print(double mat[][]) {
        for (int i = 0; i < N; i++, System.out.println())
            for (int j = 0; j <= N; j++)
                System.out.print(mat[i][j] + "  ");
        System.out.println();
    }

    // function to reduce matrix to r.e.f.
    static int forwardElim(double mat[][]) {
        for (int k = 0; k < N; k++) {

            // Initialize maximum value and index for pivot
            int iMax = k;
            int vMax = (int) mat[iMax][k];

            /* find greater amplitude for pivot if any */
            for (int i = k + 1; i < N; i++)
                if (Math.abs(mat[i][k]) > vMax) {
                    vMax = (int) mat[i][k];
                    iMax = i;
                }

            /* if a principal diagonal element  is zero,
             * it denotes that matrix is singular, and
             * will lead to a division-by-zero later. */
            if (mat[k][iMax] == 0)
                return k; // Matrix is singular

            /* Swap the greatest value row with current row
             */
            if (iMax != k)
                swapRow(mat, k, iMax);

            for (int i = k + 1; i < N; i++) {

                /* factor f to set current row kth element
                 * to 0, and subsequently remaining kth
                 * column to 0 */
                double f = mat[i][k] / mat[k][k];
                double pv = mat[k][k];
                double withMax = mat[i][k];

        /* subtract fth multiple of corresponding
                   kth row element*/
                for (int j = k + 1; j <= N; j++) {
                    /*mat[i][j] *=  pv;
                    mat[i][j] -= mat[k][j] * withMax;*/
                    mat[i][j] -= mat[k][j] * f;
                }


                /* filling lower triangular matrix with
                 * zeros*/
                mat[i][k] = 0;
            }

            print(mat);        //for matrix state
        }

        print(mat);            //for matrix state
        return -1;
    }

    // function to calculate the values of the unknowns
    static void backSub(double mat[][]) {
        double x[] = new double[N]; // An array to store solution

    /* Start calculating from last equation up to the
           first */
        for (int i = N - 1; i >= 0; i--) {

            /* start with the RHS of the equation */
            x[i] = mat[i][N];

      /* Initialize j to i+1 since matrix is upper
               triangular*/
            for (int j = i + 1; j < N; j++) {

                /* subtract all the lhs values
                 * except the coefficient of the variable
                 * whose value is being calculated */
                x[i] -= mat[i][j] * x[j];
            }

      /* divide the RHS by the coefficient of the
               unknown being calculated */
            x[i] = x[i] / mat[i][i];
        }

        System.out.println();
        System.out.println("Solution for the system:");
        for (int i = 0; i < N; i++) {
            System.out.format("%.6f", x[i]);
            System.out.println();
        }
    }
}

class GaussianEliminationEqn {

    public static void main(String[] args) {
        double mat[][] = {
                {3.0, 2.0, -4.0, 3.0},
                {2.0, 3.0, 3.0, 15.0},
                {5.0, -3, 1.0, 14.0}
        };
/*
        double mat[][] = {
                {2.0, 4.0, 12.0},
                {1.0, 8.0, 30.0},
        };*/

        forwardElimination(mat);
        substitutionMethod(mat);

    }

    static int pivotPos(double[][] mat, int j) {
        double max = mat[j][j];
        int pos = j;
        for (int i = j+1; i < mat.length; i++) {
            if (mat[i][j] > max) {
                max = mat[i][j];
                pos = i;
            }
        }

        return pos;
    }

    static void swapRows(int i, int j, double[][] mat) {
        for (int k = 0; k < mat[j].length; k++) {
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }

    static void substitutionMethod(double[][] mat) {

        double[] result = new double[mat[0].length - 1];
        int k = mat[0].length - 2;
        for (int i = mat.length - 1; i >= 0; i--) {//iterate from the row major

            int rightSide = mat[i].length - 1;
            for (int j = rightSide - 1; j > 0; j--) { //iterate from the column major
                if (i != j)
                    mat[i][rightSide] -= result[j] * mat[i][j];
            }

            result[k--] = mat[i][rightSide] / mat[i][i];
        }

        for (double res : result) {
            System.out.println(res);
        }
    }

    static void forwardElimination(double[][] mat) {
        //in the first pass, we find the pivot by getting the largest number
        //and swap the largest number with the pivot if not same position
        int n = mat.length; //the number of rows
        int m = mat[0].length; //the number of columns
        for (int i = 0; i < m - 2; i++) { //the last column is the right-hand side, so ignore

            //search for the biggest number and swap with the pivot;
            int pivotPos = pivotPos(mat, i);
            double pivItem = mat[pivotPos][i];
            if (pivotPos != i) {
                //swap the rows
                swapRows(i, pivotPos, mat);
                //pivotPos = i;
            }

           /* double div = mat[i][i];
            for (int k = i; k < m; k++) { //iterating through the columns to make update changes
                mat[i][k] /= div;
            }*/

            double pivot = mat[i][i];
            for (int j = i + 1; j < n; j++) {

                //find the factor to reduce the column to row echelon form ie r.e.f
                double factor = (float) mat[i][i] / mat[j][i];
                for (int k = i; k < m; k++) { //iterating through the columns to make update changes
                    double temp = mat[i][k];
                    mat[j][k] *= factor;
                    mat[j][k] -= temp;

                }

            }

        }
    }
}
