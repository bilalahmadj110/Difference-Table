
public class AVE {

    public static void main(String[] args) {
        double[] a = new double[]{1, 2, 3, 4, 5, 6, 7, 8};
        double[] b = new double[]{12, 456, 4564, 4563, 56257, 457363};
        DifferenceTable table = new DifferenceTable(a, b);
        table.getGaussForward(2);

    }
}