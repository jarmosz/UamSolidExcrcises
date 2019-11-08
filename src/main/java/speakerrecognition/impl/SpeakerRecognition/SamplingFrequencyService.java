package speakerrecognition.impl.SpeakerRecognition;

import speakerrecognition.impl.CustomException;
import speakerrecognition.impl.WavFile.WavFileService;

import java.io.IOException;

public class SamplingFrequencyService implements SamplingFrequency {
    @Override
    public int getSamplingFrequency(String resourceSoundFilePath) throws IOException, CustomException {
        WavFileService wavFile = new WavFileService(resourceSoundFilePath);
        wavFile.open();
        return wavFile.getFs();
    }
}
