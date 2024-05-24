
public class Main {

    public static void main(String[] args) {
        double security = Math.pow(2,-30);  // Safety violation probability
        double honestFraction =  2./3.;      // Honest mining ratio
        bitcoinBlockWait(security, honestFraction);
        ethereumBlockWait(security, honestFraction);
    }

private static void ethereumBlockWait(double maxViolationProbability, double honestFraction) {
        double delta = 2.;                  // Propagation delay in seconds
        double lambda = 1./13.;             // Mining rate in blocks per second
        System.out.println("Ethereum: " + blockWait(maxViolationProbability, honestFraction, delta, lambda));
    }

    private static void bitcoinBlockWait(double maxViolationProbability, double honestFraction) {
        double delta = 4.;                 // Propagation delay in seconds
        double lambda = 1./600.;            // Mining rate in blocks per second
        System.out.println("Bitcoin: " + blockWait(maxViolationProbability, honestFraction, delta, lambda));
    }

    private static int blockWait(double maxViolationProbability, double honestFraction, double delta, double lambda) {
        double p = honestFraction*Math.exp(-delta*lambda);
        int k = 2;                          // Block depth
        while (true) {
            double lower = lower(k,p);      // Lower bound
            double upper = upper(k,p);      // Upper bound
            //System.out.println("upper " + upper + "  k: " + k);
            // System.out.println("lower " + lower + "  k: " + k);
            if (lower < maxViolationProbability) {return k;}
            k++;
        }
    }

    // Source: https://dl.acm.org/doi/abs/10.1145/3558535.3559791
    private static double upper(int k, double p) {
        double res = Geometric.complementaryCdf(k,p);
        for (int i = 1; i <= k; i++) {
            double subres = 0;
            for (int j = 0; j <= k-i; j++) {
                subres += Binomial.pdf(j, 2*k+1-i, 1-p)*Geometric.complementaryCdf(2*k+1-2*i-2*j,p);
            }
            res += Geometric.pdf(i,p)*(Binomial.complementaryCdf(k-i,2*k+1-i,1-p)+subres);
        }
        return res;
    }

    // Source: https://dl.acm.org/doi/abs/10.1145/3558535.3559791
    private static double lower(int k, double p) {
        double res = Geometric.complementaryCdf(k,p);
        for (int i = 1; i <= k; i++) {
            double subres = 0;
            for (int j = 0; j <= k-i; j++) {
                subres += Binomial.pdf(j, 2*k+1-i, 1-p)*Geometric.complementaryCdf(2*k+2-2*i-2*j,p);
            }
            res += Geometric.pdf(i,p)*(Binomial.complementaryCdf(k-i,2*k+1-i,1-p)+subres);
        }
        return res;
    }

    /* Simpler, but weaker bounds */
    private static double weakUpper(int k, double p) {
        return (2+2*Math.sqrt(p/(1-p)))*Math.pow(4*p*(1-p),k);
    }

    private static double weakLower(int k, double p) {
        return (1./(Math.sqrt(k)))*Math.pow(4*p*(1-p),k);
    }
}