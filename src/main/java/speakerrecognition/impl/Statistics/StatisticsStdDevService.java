package speakerrecognition.impl.Statistics;

public class StatisticsStdDevService implements StdDevService{

    VarianceService varianceService;

    public StatisticsStdDevService() {
        varianceService = new StatisticsVarianceService();
    }

    @Override
    public double getStdDev(double[] data) {
        return Math.sqrt(varianceService.getVariance(data));
    }

    @Override
    public double[] getStdDev(double[][] data) {
        int numOfCols = data[0].length;

        double[] temp = this.varianceService.getVariance(data);
        for(int i=0;i<numOfCols;i++){
            temp[i] = Math.sqrt(temp[i]);
        }
        return temp;
    }
}
