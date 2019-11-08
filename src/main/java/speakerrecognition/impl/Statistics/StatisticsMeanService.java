package speakerrecognition.impl.Statistics;

public class StatisticsMeanService implements MeanService {
    @Override
    public double getMean(double[] data) {
        double sum = 0.0;
        for(double a : data)
            sum += a;
        return sum/data.length;
    }

    @Override
    public double[] getMean(double[][] data) {
        int numOfRows = data.length;
        int numOfCols = data[0].length;

        double sum[] = new double[numOfCols];
        for(int j=0;j<numOfCols;j++){
            for(int i=0;i<numOfRows;i++){
                sum[j] += data[i][j];
            }
            sum[j] /= numOfRows;
        }
        return sum;
    }
}
