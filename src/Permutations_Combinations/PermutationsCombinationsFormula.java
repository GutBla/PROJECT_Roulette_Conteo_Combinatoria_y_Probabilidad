package Permutations_Combinations;

public class PermutationsCombinationsFormula {

    public static int factorial(int num) {
        if (num == 0) return 1;
        return num * factorial(num - 1);
    }

    public static int permutation(int n, int r) {
        return factorial(n) / factorial(n - r);
    }

    public static int combination(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }
}
