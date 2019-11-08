package speakerrecognition.impl.GMM;

public class ScoreSamples {
    private ScoreSamplesData scoreSamplesData;

    ScoreSamples(double[][] X, double[][] means, double[][] covars, double[] weights, int numOfComponents){
        scoreSamplesData = new ScoreSamplesData(X, means, covars, weights, numOfComponents);
    }

    public ScoreSamplesData getScoreSamplesData() {
        return scoreSamplesData;
    }

    public double[] getLogProb(){
        return this.scoreSamplesData.getLogProb();
    }

    public double[][] getResponsibilities(){
        return this.scoreSamplesData.getResponsibilities();
    }
}
