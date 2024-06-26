public abstract class Binomial
{
    // Source: https://rosettacode.org/wiki/Evaluate_binomial_coefficients#Java
    private static long binCoeff(int n, int k)
    {
        if (k==0)
            return 1;
        else if (k>n-k)
            return binCoeff(n, n-k);
        else
            return binCoeff(n-1, k-1)*n/k;
    }

    public static double pdf(int j, int n, double q) {
        return (binCoeff(n, j)*Math.pow(q,j)*Math.pow(1-q,n-j));
    }

    public static double complementaryCdf(int j, int n, double q) {
        double res = 0;
        for (int l = j+1; l <= n; l++) {
            res += pdf(l,n,q);
        }
        return res;
    }
}