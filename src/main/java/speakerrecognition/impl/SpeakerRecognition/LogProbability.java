package speakerrecognition.impl.SpeakerRecognition;

import speakerrecognition.impl.CustomException;
import speakerrecognition.impl.SpeakerModel.SpeakerModel;
import speakerrecognition.impl.SpeakerModel.SpeakerModelService;

import java.io.IOException;
import java.util.List;

public interface LogProbability {
    double getLogProbabilityOfDataUnderModel(SpeakerModelService model, double[][] dataToBeTested) throws CustomException;
    void printLogProbsForRecognition(List<SpeakerModelService> speakerModels, String resourceSoundSpeechFilePath) throws IOException, CustomException ;
}
