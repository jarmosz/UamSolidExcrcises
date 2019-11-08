package speakerrecognition.impl.GMM;

import speakerrecognition.impl.Matrixes;

public class GMMDoStep {
    private GMMData gmmData;
    private GMMCovarMStepDiag gmmCovarMStepDiag;

    public GMMDoStep(GMMData gmmData){
        this.gmmData = gmmData;
        this.gmmCovarMStepDiag = new GMMCovarMStepDiag();
    }

    public void doMstep(double[][] data, double[][] responsibilities, double [] weights, double [][] means, double [][] covars){
        try{
            double[] weightsHelper = Matrixes.sum(responsibilities, 0);
            double[][] weighted_X_sum = Matrixes.multiplyByMatrix(Matrixes.transpose(responsibilities), data);
            double[] inverse_weightsHelper = Matrixes.invertElements(Matrixes.addValue(weightsHelper, 10*this.gmmData.getEPS()));
            this.gmmData.setWeights(Matrixes.addValue(Matrixes.multiplyByValue(weightsHelper, 1.0/(Matrixes.sum(weightsHelper)+10*this.gmmData.getEPS())), this.gmmData.getEPS()));;
            this.gmmData.setMeans(Matrixes.multiplyByValue(weighted_X_sum, inverse_weightsHelper));
            this.gmmData.setCovars(this.gmmCovarMStepDiag.getCovarMStepDiag(this.gmmData.getMeans(), data, responsibilities, weighted_X_sum, inverse_weightsHelper, this.gmmData.getMin_covar()));
        }
        catch(Exception myEx)
        {
            myEx.printStackTrace();
            System.exit(1);
        }

    }
}
