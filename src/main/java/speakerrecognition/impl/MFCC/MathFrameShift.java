package speakerrecognition.impl.MFCC;

public interface MathFrameShift {
    default int setFrameShift(int sample_rate){
        return (int) (0.0125*(double)(sample_rate));
    }
}
