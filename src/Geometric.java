public abstract class Geometric {

        public static double geoPdf(int j, double q) {
            if (q < 0.5) {
                throw new RuntimeException("q must be greater than 0.5");
            }
            return (Math.pow((1-q)/q, j-1)*(1-(1-q)/q));
        }

        public static double geoCdf(int j, double q) {
            if (q < 0.5) {
                throw new RuntimeException("q must be greater than 0.5");
            }
            return Math.pow((1-q)/q, j);
        }
}
