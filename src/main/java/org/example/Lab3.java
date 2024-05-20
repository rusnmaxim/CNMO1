package org.example;
import java.util.function.Function;

public class Lab3 {

    public static void main(String[] args) {
        Function<Double, Double> f = x -> 0.3 * Math.exp(-0.7 * Math.sqrt(x)) - 2 * x * x + 4;
        Function<Double, Double> df = x -> -0.21 * Math.exp(-0.7 * Math.sqrt(x)) / Math.sqrt(x) - 4 * x;
        Function<Double, Double> d2f = x -> 0.3 * 0.7 * (0.7 / (2 * x) - 1 / (2 * x * Math.sqrt(x))) * Math.exp(-0.7 * Math.sqrt(x)) - 4;

        double firstFunction = lotmanMethod(f, df, d2f);
        System.out.printf("First answer: " + firstFunction);
    }
    public static double lotmanMethod(Function<Double, Double> f, Function<Double, Double> df, Function<Double, Double> d2f){
        double tolerance = 1e-6;
        double x0 = 1;

       return calculate(f, df, d2f, x0, tolerance);
    }

    public static double calculate(Function<Double, Double> f, Function<Double, Double> df, Function<Double, Double> d2f, double x0, double tolerance) {
        double x = x0;
        double xPrev;
        int maxIterations = 1000;
        System.out.println("n     |    xn    |    f(xn)       |xn - x(n-1)");

        for (int i = 0; i < maxIterations; i++) {
            xPrev = x;
            double fx = f.apply(xPrev);
            double dfx = df.apply(xPrev);
            double d2fx = d2f.apply(xPrev);

            x = xPrev - fx / dfx - 0.5 * Math.pow(fx / dfx, 2) * (d2fx / dfx);
            System.out.println(i +"    |" +xPrev +"    |  " + fx +"     |   "+  (x - xPrev));
            if (Math.abs(x - xPrev) < tolerance) {
                System.out.println("Converged in " + (i + 1) + " iterations ");
                break;
            }
        }
        return x;
    }

}
