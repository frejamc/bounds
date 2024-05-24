public abstract class Geometric {

        public static double pdf(int j, double q) {
            if (q < 0.5) {
                throw new RuntimeException("q must be greater than 0.5");
            }
            return (Math.pow((1-q)/q, j-1)*(1-(1-q)/q));
        }

        public static double complementaryCdf(int j, double q) {
            if (q < 0.5) {
                throw new RuntimeException("q must be greater than 0.5");
            }
            return Math.pow((1-q)/q, j);
        }
}
