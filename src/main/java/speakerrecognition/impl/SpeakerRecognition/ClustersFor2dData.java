package speakerrecognition.impl.SpeakerRecognition;

import speakerrecognition.impl.CustomException;

public interface ClustersFor2dData {
    double[][] getMeansOfClustersFor2Ddata(double[][] data, int numOfClusters) throws CustomException;
}
