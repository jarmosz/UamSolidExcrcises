package speakerrecognition.impl.GMM;

import speakerrecognition.impl.Matrixes;

public class ScoreSamplesData {
    private double[][] data = null;
    private double[] log_likelihoods = null;
    private double[][] means = null;
    private double[][] covars = null;
    private double[] weights = null;
    private double[] logprob = null;
    private double[][] responsibilities = null;

    private LogMultivariateNormalDensity logMultivariateNormalDensity;


    ScoreSamplesData(double[][] X, double[][] means, double[][] covars, double[] weights, int numOfComponents){
        this.data = X;
        this.log_likelihoods = new double[X.length];
        this.responsibilities = new double[X.length][numOfComponents];
        this.means = means;
        this.covars = covars;
        this.weights = weights;
        this.logMultivariateNormalDensity = new LogMultivariateNormalDensity();


        try{
            double[][] lpr = logMultivariateNormalDensity.log(this.data, this.means, this.covars);
            lpr = Matrixes.addValue(lpr, Matrixes.makeLog(this.weights));
            this.logprob = Matrixes.logsumexp(lpr);
            this.responsibilities = Matrixes.exp(Matrixes.substractValue(lpr, logprob));
        }
        catch(Exception myEx)
        {
            myEx.printStackTrace();
            System.exit(1);
        }

    }

    public double[] getLogProb() {
        return logprob;
    }

    public double[][] getResponsibilities() {
        return responsibilities;
    }
}
