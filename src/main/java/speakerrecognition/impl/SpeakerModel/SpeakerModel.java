package speakerrecognition.impl.SpeakerModel;

import speakerrecognition.impl.CustomException;
import speakerrecognition.impl.Matrixes;

public interface SpeakerModel {
    public double getScore(double[][] data) throws CustomException;

}
