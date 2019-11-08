package speakerrecognition.impl.WavFile;

import speakerrecognition.impl.CustomException;

import java.io.IOException;

public interface Openable {
    void open() throws IOException, CustomException;
}
