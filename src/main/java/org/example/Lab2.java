package org.example;

import java.util.Arrays;

public class Lab2 {
    public static void main(String[] args) {
        int n = 4;
        double[][] A = {
                {0.389, 0.273, 0.126, 0.418, 0.144},
                {0.329, 2.796, 0.179, 0.278, 0.297},
                {0.186, 0.275, 2.987, 0.316, 0.529},
                {0.197, 0.219, 0.274, 3.127, 0.869}
        };
        double[] x = new double[n];  // Initial guess: x = [0, 0, 0, 0]
        int length = A.length;
        double []max = new double[length];
        for (int i =0; i< length; i++) {
            max[i] = A[i][0];
            for (int j = 1; j < A[i].length; j++) {
                if(max[i] < A[i][j]){
                    max[i] = A[i][j];
                }
            }
            for (int K =0; K< A[i].length; K++) {
                A[i][K] = A[i][K] /  max[i];
            }
        }

        double tolerance = 1e-3;
        boolean isResultLessThanTolerance;
        int k = 0;
        int posOfMax = 0;
        do {
            double maxNeviazka = Math.abs(A[k%4][4]);
            for (int i = k; i < length; i++) {
                if (maxNeviazka < Math.abs(A[i][4])) {
                    maxNeviazka = Math.abs(A[i][4]);
                    posOfMax = i;
                }
            }
            System.out.println(maxNeviazka);
            for (int i = 0; i < length; i++) {
                if (posOfMax == i){
                    A[i][4] = 0;
                    double old = A[i][4];
                    A[i][4] = 0 - A[i][k%4] * maxNeviazka;
                    System.out.println(Math.abs(old)+ " - " + A[i][k%4] + " *" + maxNeviazka +" = " + A[i][4]);
                    continue;
                }
                double old = A[i][4];
                A[i][4] = Math.abs(A[i][4]) - A[i][k%4] * maxNeviazka;
                System.out.println(Math.abs(old)+ " - " + A[i][k%4] + " *" + maxNeviazka +" = " + A[i][4]);
            }

            System.out.println("---------------");


            isResultLessThanTolerance =
                    A[0][4] < tolerance
                    && A[1][4] < tolerance
                    && A[2][4] < tolerance
                    && A[3][4] < tolerance;
            x[k%4] += maxNeviazka;

            k++;
        }while (!isResultLessThanTolerance);
        System.out.println(Arrays.toString(x));
    }
}
