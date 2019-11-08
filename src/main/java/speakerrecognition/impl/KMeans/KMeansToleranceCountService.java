package speakerrecognition.impl.KMeans;

import speakerrecognition.impl.Statistics.StatisticsService;

public interface KMeansToleranceCountService {

    final StatisticsService statisticsService = new StatisticsService();

    default double tolerance(double[][] x, double tol) {

        double[] temp = statisticsService.getStatisticsVarianceService().getVariance(x);

        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i] * tol;
        }


        return statisticsService.getStatisticsMeanService().getMean(temp);
    }
}
