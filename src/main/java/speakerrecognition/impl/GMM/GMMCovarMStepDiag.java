package speakerrecognition.impl.GMM;

import speakerrecognition.impl.Matrixes;

public class GMMCovarMStepDiag {
    private double[][] covarMstepDiag(double[][] means, double[][] X, double[][] responsibilities, double[][] weighted_X_sum, double[] norm, double min_covar){
        double[][] temp = null;
        try{
            double[][] avg_X2 = Matrixes.multiplyByValue(Matrixes.multiplyByMatrix(Matrixes.transpose(responsibilities), Matrixes.multiplyMatrixesElByEl(X, X)), norm);
            double[][] avg_means2 = Matrixes.power(means, 2);
            double[][] avg_X_means = Matrixes.multiplyByValue(Matrixes.multiplyMatrixesElByEl(means, weighted_X_sum),norm);
            temp = Matrixes.addValue(Matrixes.addMatrixes(Matrixes.substractMatrixes(avg_X2, Matrixes.multiplyByValue(avg_X_means, 2)), avg_means2), min_covar);
        }
        catch(Exception myEx)
        {
            System.out.println("An exception encourred: " + myEx.getMessage());
            myEx.printStackTrace();
            System.exit(1);
        }
        return temp;
    }

    public double [][] getCovarMStepDiag(double[][] means, double[][] X, double[][] responsibilities, double[][] weighted_X_sum, double[] norm, double min_covar){
        return covarMstepDiag(means, X, responsibilities, weighted_X_sum, norm, min_covar);
    }
}
