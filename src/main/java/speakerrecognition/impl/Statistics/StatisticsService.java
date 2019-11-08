package speakerrecognition.impl.Statistics;

import speakerrecognition.impl.Statistics.*;

public final class StatisticsService {
	
	private MeanService statisticsMeanService;
	private StdDevService statisticsStdDevService;
	private VarianceService statisticsVarianceService;

	public StatisticsService(){
		statisticsMeanService = new StatisticsMeanService();
		statisticsStdDevService = new StatisticsStdDevService();
		statisticsVarianceService = new StatisticsVarianceService();
	}

	public MeanService getStatisticsMeanService() {
		return statisticsMeanService;
	}

	public StdDevService getStatisticsStdDevService() {
		return statisticsStdDevService;
	}

	public VarianceService getStatisticsVarianceService() {
		return statisticsVarianceService;
	}
}
