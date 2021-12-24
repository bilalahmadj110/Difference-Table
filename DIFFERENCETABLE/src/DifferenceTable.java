import java.util.ArrayList;

public class DifferenceTable {

    ArrayList<double[]> differenceTableColumn = new ArrayList<>();
    double[] yValues;
    double[] xValues;

    // Constructor
    public DifferenceTable(double[] xValues, double[] yValues) {
        this.yValues = yValues;
        this.xValues = xValues;
        for (int col = 1; col < yValues.length; col++) {
            double[] differenceTable = new double[yValues.length - col];
            if (col == 1) {
                for (int entry = 0; entry < yValues.length - col; entry++)
                    differenceTable[entry] = yValues[entry + 1] - yValues[entry];
            }
            else {
                double array[] = differenceTableColumn.get(col - 2);
                for (int entry = 0; entry < yValues.length - col; entry++) {
                    differenceTable[entry] = array[entry + 1] - array[entry];
                }
            }
            differenceTableColumn.add(differenceTable);
        }
    }

    public double[] getForward(int myIndex) {
        double[] returningArray = new double[yValues.length - (myIndex + 1)];
        for (int listIndex = 0; listIndex < yValues.length - (myIndex + 1); listIndex++)
            returningArray[listIndex] = differenceTableColumn.get(listIndex)[myIndex];
        return returningArray;
    }
    /*
    y   y1  y2  y3  y4  y5
    1
        1
    2       1
        2       1
    3       2       1
        3       2       1
    4       3       2
        4       3
    5       4
        5
    6
     */

    public double[] getGaussForward(int myIndex) {
        int index = myIndex;
        double[] returningArray = new double[yValues.length - (index + 2)];
        for (int listIndex = 0; listIndex < yValues.length - (index + 2); listIndex++) {
            returningArray[listIndex] = differenceTableColumn.get(listIndex)[index];
            returningArray[listIndex] = differenceTableColumn.get(listIndex)[index];
            if (listIndex % 2 == 0)
                index--;
        }
        for (double iter : returningArray)
            System.out.print(iter + " ");
        System.out.println();
        return returningArray;
    }

    public double[] getBackward(int myIndex) {
        double[] returningArray = new double[yValues.length - ((yValues.length ) - myIndex)];
        for (int col = 0; col < returningArray.length; col++)
            returningArray[col] = differenceTableColumn.get(col)[returningArray.length - col - 1];
        return returningArray;
    }

    public int getFactorial(int index) {
        int factorial = (index == 0? 1 : index);
        for (int i = 1; i < index; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public double[] calculateP_And_Factorial_Forward(double u, int index) {
        double returnU = 1;
        for (int pos = 1; pos < index; pos++)
            returnU *= (u - pos);
        if (index != 0)
            returnU *= u;

        return new double[]{returnU, getFactorial(index)};
    }

    public double[] calculateP_And_Factorial_Backward(double u, int index) {
        double returnU = 1;
        for (int pos = 1; pos < index; pos++)
            returnU *= (u + pos);
        if (index != 0)
            returnU *= u;
        return new double[]{returnU, getFactorial(index)};
    }
}