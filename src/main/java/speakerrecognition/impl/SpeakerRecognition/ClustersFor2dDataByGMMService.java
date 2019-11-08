package speakerrecognition.impl.SpeakerRecognition;

import speakerrecognition.impl.GMM.GMMService;

public class ClustersFor2dDataByGMMService {
    public double[][] getMeansOfClustersFor2DdataByGMM(double[][] data, int numOfClusters) {
        GMMService gmmService = new GMMService(data, numOfClusters);
        gmmService.getGmmFitService().fit();
        return gmmService.getGmmFitService().getGmmData().getMeans();
    }
}
