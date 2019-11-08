package speakerrecognition.impl.SpeakerRecognition;

import speakerrecognition.impl.CustomException;
import speakerrecognition.impl.MFCC.MFCCService;
import speakerrecognition.impl.SpeakerModel.SpeakerModelService;
import speakerrecognition.impl.WavFile.WavFileService;

import java.io.IOException;
import java.util.List;

public class LogProbabilityService implements LogProbability {
    @Override
    public double getLogProbabilityOfDataUnderModel(SpeakerModelService model, double[][] dataToBeTested) throws CustomException {
        return model.getScore(dataToBeTested);
    }

    @Override
    public void printLogProbsForRecognition(List<SpeakerModelService> speakerModels, String resourceSoundSpeechFilePath) throws IOException, CustomException {
        double finalScore = Long.MIN_VALUE;
        String bestFittingModelName = "";
        for(SpeakerModelService model : speakerModels){
            WavFileService wavFile1 = new WavFileService(resourceSoundSpeechFilePath);
            wavFile1.open();
            int[] x3 = wavFile1.getSamples();
            int fs3 = wavFile1.getFs();
            MFCCService mfcc3 = new MFCCService(x3, fs3);
            double[][] speaker_mfcc3 = mfcc3.getMFCC();
            double scoreForTest1 = model.getScore(speaker_mfcc3);
            System.out.println("Test speech from file "+resourceSoundSpeechFilePath + " is similar to model "+ model.getSpeakerModelData().getName()+" with log probability "+scoreForTest1);
        }
    }
}
