package speakerrecognition.impl.SpeakerRecognition;

import speakerrecognition.impl.CustomException;
import speakerrecognition.impl.KMeans.KMeansService;

public class ClustersFor2dDataByKMeans implements ClustersFor2dData {
    @Override
    public double[][] getMeansOfClustersFor2Ddata(double[][] data, int numOfClusters) throws CustomException {
        KMeansService kMeans = new KMeansService(data, numOfClusters);
        kMeans.fit();
        return kMeans.getkMeansData().getBest_cluster_centers();
    }
}
