package speakerrecognition.impl.SpeakerRecognition;

public interface ComputableMFCC {
    double[][] computeMFCC(int[] soundSamples, int fs);
}
