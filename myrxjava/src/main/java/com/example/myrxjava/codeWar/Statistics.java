package com.example.myrxjava.codeWar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.exp;
import static java.util.stream.Collectors.toList;

public class Statistics {

        public static void main(String[] args) throws IOException {


        /*int[] arr = {64630,11735,14216,99233,14470,4978,73429,38120,51135,67060};
        //mean(arr);
        //median(arr);
        mode(arr);*/

        //readTestCases();
        //3 7 8 5 12 14 21 15 18 14
        /*List<Integer> arr = new ArrayList<>();
        arr.add(10); arr.add(40);arr.add(30);arr.add(50);arr.add(20);
        List<Integer> freqs = new ArrayList<>();
        freqs.add(5); freqs.add(4);freqs.add(3);freqs.add(2);freqs.add(1);freqs.add(5);
        stdDev(arr);*/

        //poissonSpecialCase(0.88,1.55);

        //leastSquaredRegression();
        /*double[][] first = {{1,3,2},{1,4,1},{2,3,1}};
        double[][] second = {{2},{3},{4}};
        matrixTranspose(first);*/

        inputTaken();
    }


    public static double[][] invert(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];

        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    // Method to carry out the partial-pivoting Gaussian
    // elimination.  Here index[] stores pivoting order.

    public static void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    private static void inputTaken() throws IOException {
        File file = new File(System.getProperty("user.home"), "/Desktop/multiregre.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));


        String[] firstMultipleInput = bufferedReader.readLine().split(" ");
        System.out.println(Arrays.toString(firstMultipleInput));
        int n = Integer.parseInt(firstMultipleInput[0]);
        int m = Integer.parseInt(firstMultipleInput[1]);

        double[][] trainSet = new double[m][n + 1];
        double[][] Y = new double[m][1];
        int count = 0;

        while (count < m) {
            String[] next = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            double[] set = new double[next.length];
            set[0] = 1;
            for (int i = 1; i < next.length; i++) {
                set[i] = Double.parseDouble(next[i - 1]);
            }

            trainSet[count] = set;
            Y[count] = new double[]{Double.parseDouble(next[next.length - 1])};
            count++;
        }

        int q = Integer.parseInt(bufferedReader.readLine());
        count = 0;
        double[][] querySet = new double[q][n + 1];
        while (count < q) {

            String[] next = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            double[] set = new double[1 + next.length];
            set[0] = 1;
            for (int i = 1; i <= next.length; i++) {
                set[i] = Double.parseDouble(next[i - 1]);
            }
            querySet[count] = set;
            count++;
        }

        //B = (Xt*X)^-1*Xt*Y
        double[][] setTranspose = matrixTranspose(trainSet);
        double[][] multMatrix = matrixMultiplication(setTranspose, trainSet);
        double[][] inverseMatrix = invert(multMatrix);
        double[][] multTransY = matrixMultiplication(setTranspose, Y);
        double[][] B = matrixMultiplication(inverseMatrix, multTransY);

        double[][] predictedY = matrixMultiplication(querySet, B);

        for (int i = 0; i < predictedY.length; i++) {
            for (int j = 0; j < predictedY[0].length; j++) {
                System.out.printf("%.2f\n", predictedY[i][j]);
            }
        }


    }

    private static double[][] matrixMultiplication(double[][] x, double[][] x1) {
        int m1 = x.length;
        int n1 = x[0].length;
        int m2 = x1.length;
        int n2 = x1[0].length;
        //m1*n1,m2*n2 = m1*n2
        double[][] resultant = new double[m1][n2];
        if (m2 != n1) {
            return null;
        }

        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                int a = 0;
                while (a < n2) {
                    resultant[i][a] += x[i][j] * x1[j][a];
                    a++;
                }
            }
        }

        return resultant;
    }

    private static double[][] matrixTranspose(double[][] x) {
        int m1 = x.length;
        int n1 = x[0].length;
        double[][] trans = new double[n1][m1];
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                trans[j][i] = x[i][j];
            }
        }

        return trans;
    }

    private static double[][] matrixInverse(double[][] x){
        int m1 = x.length;
        int n1 = x[0].length;
        double[][] trans = new double[m1][n1];
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                trans[i][j] = 1 / x[i][j];
            }
        }

        return trans;
    }

    private static void runSpearmanCorCoefficient() {
        List<Double> xValues = Stream.of("10 9.8 8 7.8 7.7 1.7 6 5 1.4 2".replaceAll("\\s+$", "").split(" "))
                .map(Double::parseDouble)
                .collect(toList());

        List<Double> yValues = Stream.of("200 44 32 24 22 17 15 12 8 4".replaceAll("\\s+$", "").split(" "))
                .map(Double::parseDouble)
                .collect(toList());
        spearmanRankCorrelationCoefficient(xValues, yValues);
    }


    private static void leastSquaredRegression() {
        int[] x = {95, 85, 80, 70, 60};
        int[] y = {85, 95, 70, 65, 70};
        int n = x.length;
        //calculate mean of x and y;
        double avgX = 0d, avgY = 0d, avgXY = 0d, avgXSqd = 0d;
        for (int i = 0; i < n; i++) {
            avgX += x[i];
            avgXSqd += Math.pow(x[i], 2);
            avgY += y[i];
            avgXY += x[i] * y[i];
        }

        double meanX = avgX / n;
        double meanY = avgY / n;

        double b = (n * avgXY - avgX * avgY) / (n * avgXSqd - Math.pow(avgX, 2));
        double a = meanY - b * meanX;

        double predictedY = a + b * 80;

        System.out.printf("%.3f", predictedY);


    }

    private static void spearmanRankCorrelationCoefficient(List<Double> x, List<Double> y) {

        LinkedHashMap<Double, Integer> freqX = new LinkedHashMap<>();
        LinkedHashMap<Double, Integer> freqY = new LinkedHashMap<>();
        boolean isDuplicate = false;
        for (int i = 0; i < x.size(); i++) {
            if (freqX.get(x.get(i)) == null) {
                freqX.put(x.get(i), 1);
            } else {
                int data = freqX.get(x.get(i)) + 1;
                freqX.put(x.get(i), data);

                isDuplicate = true;
            }

            if (freqY.get(y.get(i)) == null) {
                freqY.put(y.get(i), 1);
            } else {
                int data = freqY.get(y.get(i)) + 1;
                freqY.put(y.get(i), data);

                isDuplicate = true;
            }
        }


        if (isDuplicate) {
            List<Integer> xV = new ArrayList<>(freqX.values());
            List<Integer> yV = new ArrayList<>(freqY.values());
            pearsonCoefficientInt(xV.size(), xV, yV);
        } else {

            HashMap<Double, Integer> xV = rank(freqX);
            HashMap<Double, Integer> yV = rank(freqY);
            distinctRanks(xV, yV);
        }
    }

    private static HashMap<Double, Integer> rank(HashMap<Double, Integer> arr) {
        Double[] newDouble = new Double[arr.size()];
        arr.keySet().toArray(newDouble);
        SortAlgorithm.sort(newDouble, 0, newDouble.length - 1, false);
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            list.add(newDouble[i]);
        }

        for (int i = 0; i < arr.size(); i++) {
            arr.put(list.get(i), list.indexOf(newDouble[i]) + 1);
        }

        return arr;
    }

    private static void distinctRanks(HashMap<Double, Integer> x, HashMap<Double, Integer> y) {

        double diff = 0;
        Integer[] rankX = new Integer[x.size()];
        x.values().toArray(rankX);
        Integer[] rankY = new Integer[y.size()];
        y.values().toArray(rankY);
        for (int i = 0; i < x.size(); i++) {
            diff += Math.pow((rankX[i] - rankY[i]), 2);
        }

        int n = x.size();
        double spearRankCorel = 1 - ((diff * 6) / (n * (Math.pow(n, 2) - 1)));

        System.out.printf("%.3f\n", spearRankCorel);

    }

    private static void pearsonCoefficientInt(int n, List<Integer> x, List<Integer> y) {
        double meanx = mean(x);
        double meany = mean(y);
        double stdx = stdDevInt(x, meanx);
        double stdy = stdDevInt(y, meany);

        double cov = 0;
        for (int i = 0; i < n; i++) {
            cov += (x.get(i) - meanx) * (y.get(i) - meany);
        }

        double pearsonCorrelation = cov / (n * stdx * stdy);
        System.out.printf("%.3f\n", pearsonCorrelation);
    }

    private static void pearsonCoefficient(int n, List<Double> x, List<Double> y) {
        double meanx = meanDouble(x);
        double meany = meanDouble(y);
        double stdx = stdDev(x, meanx);
        double stdy = stdDev(y, meany);

        double cov = 0;
        for (int i = 0; i < n; i++) {
            cov += (x.get(i) - meanx) * (y.get(i) - meany);
        }

        double pearsonCorrelation = cov / (n * stdx * stdy);
        System.out.printf("%.3f\n", pearsonCorrelation);
    }

    private static double calculatePearsonCorrelation(double[] xValues, double[] yValues) {
        double xMean = calculateMean(xValues);
        double yMean = calculateMean(yValues);
        double xStdDev = calculateStdDev(xValues, xMean);
        double yStdDev = calculateStdDev(yValues, yMean);
        double upperSum = 0;
        for (int i = 0; i < xValues.length; i++) {
            upperSum += (xValues[i] - xMean) * (yValues[i] - yMean);
        }
        return upperSum / (xValues.length * xStdDev * yStdDev);
    }

    private static double calculateMean(double[] values) {
        double sum = 0;
        for (double value : values) sum += value;
        return sum / values.length;
    }

    private static double calculateStdDev(double[] values, double mean) {
        double upperSum = 0;
        for (double value : values) {
            upperSum += (value - mean) * (value - mean);
        }
        return Math.sqrt(upperSum / values.length);
    }


    private static void normalDistributionII() {
        double mean = 70;
        double sdev = 10;

        double r1 = 1 - normal(80, mean, sdev);
        double r3 = normal(60, mean, sdev);
        double r2 = 1 - r3;

        System.out.printf("%.2f\n", r1 * 100);
        System.out.printf("%.2f\n", r2 * 100);
        System.out.printf("%.2f\n", r3 * 100);
    }

    private static void probabilityOfElevatorCarrying() {
        double mean = 49 * 205;
        double sdev = 15 * Math.sqrt(49);

        double r1 = normal(9800, mean, sdev);

        System.out.printf("%.4f\n", r1);
    }

    private static void probAllStudentBuyingLastMinuteTicket() {
        double mean = 100 * 2.4;
        double sdev = 2.0 * Math.sqrt(100);

        double r1 = normal(250, mean, sdev);

        System.out.printf("%.4f\n", r1);
    }

    private static void lowerUpperInterval() {
        double mean = 500;
        double sdev = (1.96 * 80) / Math.sqrt(100);

        double A = mean - sdev;
        double B = mean + sdev;

        System.out.printf("%.2f\n", A);
        System.out.printf("%.2f\n", B);
    }

    public static double erf3(double z, double x) {
        int direction = z > 0 ? 1 : -1;
        double result = 0;
        double delta = .000001;
        double iz = 0;
        while (iz * direction < z * direction) {
            result += delta * Math.pow(Math.E, -(iz * iz)) * direction;
            iz += delta * direction;
        }
        //System.out.printf("result=%.5f\n",result);
        return 2. / Math.sqrt(Math.PI) * result;
    }

    static double erf2(double x) {
        int n = 100;
        double dt = x / n;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            double z = i * dt + 0.5 * dt;
            sum += exp(-z * z) * dt;
        }
        return sum * 2 / Math.sqrt(Math.PI);
    }

    private static double normal(double x, double mean, double sdev) {
        return .5 * (1 + erf((x - mean) / (sdev * Math.sqrt(2))));
    }

    public static double erf(double x) {
        // constants
        final double a1 = 0.254829592;
        final double a2 = -0.284496736;
        final double a3 = 1.421413741;
        final double a4 = -1.453152027;
        final double a5 = 1.061405429;
        final double p = 0.3275911;

        // Save the sign of x
        double sign = 1;
        if (x < 0) {
            sign = -1;
        }
        x = Math.abs(x);

        // A&S formula 7.1.26
        double t = 1.0 / (1.0 + p * x);
        double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * exp(-x * x);

        return sign * y;
    }

    public static double erf1(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));

        // use Horner's method
        double ans = 1 - t * exp(-z * z - 1.26551223 +
                t * (1.00002368 +
                        t * (0.37409196 +
                                t * (0.09678418 +
                                        t * (-0.18628806 +
                                                t * (0.27886807 +
                                                        t * (-1.13520398 +
                                                                t * (1.48851587 +
                                                                        t * (-0.82215223 +
                                                                                t * (0.17087277))))))))));
        if (z >= 0) return ans;
        else return -ans;
    }

    private static void poissonSpecialCase(double meanA, double meanB) {
        double costA = 160 + 40 * (meanA + (meanA * meanA));
        double costB = 128 + 40 * (meanB + (meanB * meanB));
        System.out.printf("%.3f\n", costA);
        System.out.printf("%.3f", costB);
    }

    private static void poissonDistribution(double mean, int k) {
        double poisDis = (double) (Math.pow(Math.E, -mean) * Math.pow(mean, k)) / factorial(k);
        System.out.printf("%.3f", poisDis);
    }

    private static void firstDefect(int nth, double p) {
        double geoDisProb = Math.pow((1 - p), nth - 1) * p;
        System.out.printf("%.3f", geoDisProb);
    }

    private static void noMoreThan2Rejects(int trial, int sel, double success) {
        double prob = 0;
        for (int i = 0; i <= sel; i++) {
            prob += binomialProbability(trial, i, success);
        }

        System.out.printf("%.3f\n", prob);
    }

    private static void atLeastTwoRejects(int trial, int sel, double success) {
        double prob = 0;
        for (int i = sel; i <= trial; i++) {
            prob += binomialProbability(trial, i, success);
        }

        System.out.printf("%.3f", prob);
    }

    private static int factorial(int n) {
        return (n == 0) ? 1 : n * factorial(n - 1);
    }

    private static double combination(int n, int r) {
        double comb = factorial(n) / ((factorial(n - r) * factorial(r)));

        return comb;
    }

    private static double binomialProbability(int trial, int sel, double success) {
        return combination(trial, sel) * Math.pow(success, sel) * Math.pow((1 - success), (trial - sel));

    }

    private static void russianFamily(int trial, int sel, double success) {
        double prob = 0;
        for (int i = 0; i <= sel; i++) {
            prob += binomialProbability(trial, i, success);
        }

        System.out.printf("%.3f", prob);
    }

    public static double stdDev(List<Integer> arr) {
        // Print your answers to 1 decimal place within this function
        double mean = mean(arr);
        double std = 0d;
        for (int i = 0; i < arr.size(); i++) {
            std += Math.pow((arr.get(i) - mean), 2);
        }

        std = std / arr.size();

        // System.out.println(String.format("%.1f",Math.sqrt(std)));
        return Math.sqrt(std);
    }

    public static double stdDev(List<Double> arr, double mean) {
        // Print your answers to 1 decimal place within this function
        double std = 0d;
        for (int i = 0; i < arr.size(); i++) {
            std += Math.pow((arr.get(i) - mean), 2);
        }

        std = std / arr.size();

        // System.out.println(String.format("%.1f",Math.sqrt(std)));
        return Math.sqrt(std);
    }

    public static double stdDevInt(List<Integer> arr, double mean) {
        // Print your answers to 1 decimal place within this function
        double std = 0d;
        for (int i = 0; i < arr.size(); i++) {
            std += Math.pow((arr.get(i) - mean), 2);
        }

        std = std / arr.size();

        // System.out.println(String.format("%.1f",Math.sqrt(std)));
        return Math.sqrt(std);
    }

    private static double mean(List<Integer> arr) {
        double avg = 0d;
        for (int i = 0; i < arr.size(); i++) {
            avg += arr.get(i);
        }

        return avg / arr.size();
    }

    private static double meanDouble(List<Double> arr) {
        double avg = 0d;
        for (int i = 0; i < arr.size(); i++) {
            avg += arr.get(i);
        }

        return avg / arr.size();
    }

    public static void interQuartile(List<Integer> values, List<Integer> freqs) {
        // Print your answer to 1 decimal place within this function
        List<Integer> expanded = new ArrayList<>();
        for (int i = 0; i < freqs.size(); i++) {
            for (int j = 0; j < freqs.get(i); j++) {
                expanded.add(values.get(i));
            }

        }

        Collections.sort(expanded);
        int lower, upper;
        int mid = expanded.size() / 2;
        if (expanded.size() % 2 == 0) {
            lower = mid - 1;
            upper = mid;
        } else {
            lower = mid - 1;
            upper = mid + 1;
        }

        double q1, q2;
        int n1 = lower + 1;
        int n2 = expanded.size() - upper;
        mid = n1 / 2;
        if (n1 % 2 == 0) {
            q1 = (double) (expanded.get(mid) + expanded.get(mid - 1)) / 2;
        } else {
            q1 = expanded.get(mid);
        }


        mid = (n2 / 2) + upper;
        if (n2 % 2 == 0) {
            q2 = (double) (expanded.get(mid) + expanded.get(mid - 1)) / 2;
        } else {
            q2 = expanded.get(mid);
        }

        System.out.println(String.format("%.1f", (q2 - q1)));
    }

    private static double[] quartiles(List<Integer> arr) {

        Collections.sort(arr);
        int l, r;
        double[] quarts = new double[3];
        int mid = arr.size() / 2;
        if (arr.size() % 2 == 0) {
            quarts[0] = (double) (arr.get(mid) + arr.get(mid - 1)) / 2;
            l = mid - 1;
            r = mid;
        } else {
            quarts[0] = arr.get(mid);
            l = mid - 1;
            r = mid + 1;
        }

        int n1 = l + 1;
        mid = n1 / 2;
        if (n1 % 2 == 0) {
            quarts[1] = (double) (arr.get(mid) + arr.get(mid - 1)) / 2;

        } else {
            quarts[1] = arr.get(mid);
        }

        int n2 = arr.size() - r;
        mid = (n2 / 2) + r;
        if (n2 % 2 == 0) {
            quarts[2] = (double) (arr.get(mid) + arr.get(mid - 1)) / 2;

        } else {
            quarts[2] = arr.get(mid);
        }


        return quarts;
    }


    private static void readTestCases() throws IOException {
        File desktop = new File(System.getProperty("user.home"), "/Desktop/statistics_testcase.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(desktop)));

        String firstMultipleInput = bufferedReader.readLine();

        int n = Integer.parseInt(firstMultipleInput);


        int[] arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .mapToInt(Integer::parseInt).toArray();

        mean(arr);
        median(arr);
        mode(arr);

        bufferedReader.close();
    }


    private static void mode(int[] arr) {
        int mode = 0;
        int firstIndex = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (freq.get(arr[i]) == null) {
                freq.put(arr[i], 1);
            } else {
                freq.put(arr[i], freq.get(arr[i]) + 1);
            }
        }

        for (Integer key : freq.keySet()) {
            if (mode < freq.get(key)) {
                mode = freq.get(key);
                firstIndex = key;
            }

            if (mode == freq.get(key)) {
                if (key < firstIndex) {
                    firstIndex = key;
                }
            }
        }
        System.out.println(firstIndex);
    }

    private static void weightedMean(int[] arr, int[] weights) {
        double sum = 0.0d;
        int weight = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            sum += arr[i] * weights[i];
            weight += weights[i];
        }

        double weightedMean = sum / weight;

        System.out.println(String.format("%.1f", weightedMean));
    }

    private static void mean(int[] arr) {
        double sum = 0.0d;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        double mean = sum / arr.length;

        System.out.println(String.format("%.1f", mean));
    }

    private static void median(int[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);

        double median = 0.0d;
        if (n % 2 == 0) {
            median = (double) (arr[n / 2] + arr[(n / 2) - 1]) / 2;

        } else {
            median = arr[(n / 2) + 1];
        }

        System.out.println(String.format("%.1f", median));
    }

    private static void sort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }

    }

    private static void merge(int[] arr, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;
        int[] left = new int[n1];
        int[] right = new int[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = arr[l + i];
        }

        for (int i = 0; i < n2; i++) {
            right[i] = arr[m + i + 1];
        }

        int i = 0;
        int j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }

            k++;
        }


        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}
