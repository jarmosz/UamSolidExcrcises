package speakerrecognition.impl.GMM;

import speakerrecognition.impl.Matrixes;

public class LogMultivariateNormalDensity {
    public double[][] log(double[][] data, double[][] means, double[][] covars) {
        double[][] lpr = new double[data.length][means.length];
        int n_samples = data.length;
        int n_dim = data[0].length;

        try {
            double[] sumLogCov = Matrixes.sum(Matrixes.makeLog(covars), 1); //np.sum(np.log(covars), 1)
            double[] sumDivMeanCov = Matrixes.sum(Matrixes.divideElements(Matrixes.power(means, 2), covars), 1); //np.sum((means ** 2) / covars, 1)
            double[][] dotXdivMeanCovT = Matrixes.multiplyByValue(Matrixes.multiplyByMatrix(data, Matrixes.transpose(Matrixes.divideElements(means, covars))), -2); //- 2 * np.dot(X, (means / covars).T)
            double[][] dotXdivOneCovT = Matrixes.multiplyByMatrix(Matrixes.power(data, 2), Matrixes.transpose(Matrixes.invertElements(covars)));


            sumLogCov = Matrixes.addValue(sumLogCov, n_dim * Math.log(2 * Math.PI)); //n_dim * np.log(2 * np.pi) + np.sum(np.log(covars), 1)
            sumDivMeanCov = Matrixes.addMatrixes(sumDivMeanCov, sumLogCov); // n_dim * np.log(2 * np.pi) + np.sum(np.log(covars), 1) + np.sum((means ** 2) / covars, 1)
            dotXdivOneCovT = Matrixes.sum(dotXdivOneCovT, dotXdivMeanCovT); //- 2 * np.dot(X, (means / covars).T) + np.dot(X ** 2, (1.0 / covars).T)
            dotXdivOneCovT = Matrixes.addValue(dotXdivOneCovT, sumDivMeanCov); // (n_dim * np.log(2 * np.pi) + np.sum(np.log(covars), 1) + np.sum((means ** 2) / covars, 1) - 2 * np.dot(X, (means / covars).T) + np.dot(X ** 2, (1.0 / covars).T))
            lpr = Matrixes.multiplyByValue(dotXdivOneCovT, -0.5);
        } catch (Exception myEx) {
            System.out.println("An exception encourred: " + myEx.getMessage());
            myEx.printStackTrace();
            System.exit(1);
        }
        return lpr;
    }
}
