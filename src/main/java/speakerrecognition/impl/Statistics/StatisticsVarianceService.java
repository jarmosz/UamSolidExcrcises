package speakerrecognition.impl.Statistics;

public class StatisticsVarianceService implements VarianceService {

    MeanService meanService;

    public StatisticsVarianceService() {
        meanService = new StatisticsMeanService();
    }


    @Override
    public double getVariance(double[] data) {
        double mean = this.meanService.getMean(data);
        double temp = 0;
        for(double a :data)
            temp += (mean-a)*(mean-a);
        return temp/data.length;
    }

    @Override
    public double[] getVariance(double[][] data) {
        int numOfRows = data.length;
        int numOfCols = data[0].length;

        double means[] = this.meanService.getMean(data);
        double[] temp = new double[numOfCols];

        for(int j=0;j<numOfCols;j++){
            for(int i=0;i<numOfRows;i++){
                temp[j] += Math.pow((data[i][j]-means[j]), 2);
            }
            temp[j] /= numOfRows;
        }

        return temp;
    }
}
