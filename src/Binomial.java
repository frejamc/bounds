public abstract class Binomial
{
    private static long binCoeff(int n, int k)
    {
        if (k==0)
            return 1;
        else if (k>n-k)
            return binCoeff(n, n-k);
        else
            return binCoeff(n-1, k-1)*n/k;
    }

    public static double binPdf(int j, int n, double q) {
        return (binCoeff(n, j)*Math.pow(q,j)*Math.pow(1-q,n-j));
    }

    public static double binCdf(int j, int n, double q) {
        float res = 0;
        for (int l = j+1; l <= n; l++) {
            res += binPdf(l,n,q);
        }
        return res;
    }


}