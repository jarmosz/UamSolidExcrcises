package speakerrecognition.impl.SpeakerRecognition;

import speakerrecognition.impl.CustomException;
import speakerrecognition.impl.WavFile.WavFileService;

import java.io.IOException;

public class WavFileOpenService implements OpenableWavFile {
    @Override
    public int[] openWavFile(String resourcePath) throws IOException, CustomException {
        WavFileService wavFile = new WavFileService(resourcePath);
        wavFile.open();
        return wavFile.getSamples();
    }
}
