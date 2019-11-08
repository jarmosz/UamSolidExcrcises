package speakerrecognition.impl.GMM;

import speakerrecognition.impl.KMeans.KMeansService;
import speakerrecognition.impl.Matrixes;
import speakerrecognition.impl.Statistics.StatisticsService;

public class GMMFitService implements FitService {

    private GMMData gmmData;
    private GMMDoStep gmmDoStep;
    private StatisticsService statisticsService;

    public GMMFitService(double[][] data, int compNum){
        gmmData = new GMMData(data, compNum);
        gmmDoStep = new GMMDoStep(this.gmmData);
        statisticsService = new StatisticsService();
    }

    @Override
    public void fit() {
        double change = 0;

        try{

            double[][] cv = new double[this.gmmData.getNumOfCols()][this.gmmData.getNumOfCols()];
            double max_log_prob = Double.NEGATIVE_INFINITY;

            for(int i=0;i<this.gmmData.getN_init();i++){
                KMeansService kMeans = new KMeansService(this.gmmData.getObservations(), this.gmmData.getNumOfComponents());
                kMeans.fit();
                this.gmmData.setMeans(kMeans.getkMeansData().getBest_cluster_centers());
                this.gmmData.setWeights(Matrixes.fillWith(this.gmmData.getWeights(), (double)1/this.gmmData.getNumOfComponents()));
                this.gmmData.setCovars(Matrixes.cov(Matrixes.transpose(this.gmmData.getObservations()))); //np.cov(X.T), gmm.py line 450
                cv = Matrixes.eye(this.gmmData.getObservations()[0].length, this.gmmData.getMin_covar()); //self.min_covar * np.eye(X.shape[1])
                this.gmmData.setCovars(Matrixes.addMatrixes(this.gmmData.getCovars(), cv));
                this.gmmData.setCovars(Matrixes.duplicate(Matrixes.chooseDiagonalValues(this.gmmData.getCovars()), this.gmmData.getNumOfComponents()));

                for(int j=0;j<this.gmmData.getN_iter();j++){
                    this.gmmData.setPrev_log_likelihood(this.gmmData.getCurrent_log_likelihood());
                    ScoreSamples scoreSamples = new ScoreSamples(this.gmmData.getObservations(), this.gmmData.getMeans(), this.gmmData.getCovars(), this.gmmData.getWeights(), this.gmmData.getNumOfComponents());
                    this.gmmData.setLog_likelihoods(scoreSamples.getLogProb());
                    this.gmmData.setResponsibilities(scoreSamples.getResponsibilities());
                    this.gmmData.setCurrent_log_likelihood(statisticsService.getStatisticsMeanService().getMean(this.gmmData.getLog_likelihoods()));

                    if(!Double.isNaN(this.gmmData.getPrev_log_likelihood())){
                        change = Math.abs(this.gmmData.getCurrent_log_likelihood() - this.gmmData.getPrev_log_likelihood());
                        if(change<this.gmmData.getTol()){
                            this.gmmData.setConverged(true);
                            break;
                        }

                    }

                    /// do m-step - gmm.py line 509
                    gmmDoStep.doMstep(this.gmmData.getObservations(), this.gmmData.getResponsibilities(), gmmData.getWeights(), gmmData.getMeans(), gmmData.getCovars());

                }

                if (gmmData.getCurrent_log_likelihood() > max_log_prob){
                    max_log_prob = gmmData.getCurrent_log_likelihood();
                    this.gmmData.setBest_means(this.gmmData.getMeans());
                    this.gmmData.setBest_covars(this.gmmData.getCovars());
                    this.gmmData.setBest_weights(this.gmmData.getWeights());
                }
            }

            if(Double.isInfinite(max_log_prob))
                System.out.println("EM algorithm was never able to compute a valid likelihood given initial parameters");
        }
        catch(Exception myEx)
        {
            //System.out.println("An exception encourred: " + myEx.getMessage());
            myEx.printStackTrace();
            System.exit(1);
        }
    }

    public GMMData getGmmData() {
        return gmmData;
    }
}
