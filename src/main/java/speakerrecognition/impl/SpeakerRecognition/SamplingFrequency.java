package speakerrecognition.impl.SpeakerRecognition;

import speakerrecognition.impl.CustomException;

import java.io.IOException;

public interface SamplingFrequency {
    public int getSamplingFrequency(String resourceSoundFilePath) throws IOException, CustomException;
}
