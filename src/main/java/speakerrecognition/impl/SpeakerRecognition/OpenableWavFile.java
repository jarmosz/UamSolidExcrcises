package speakerrecognition.impl.SpeakerRecognition;

import speakerrecognition.impl.CustomException;

import java.io.IOException;

public interface OpenableWavFile {
    public int[] openWavFile(String resourcePath) throws IOException, CustomException;
}
