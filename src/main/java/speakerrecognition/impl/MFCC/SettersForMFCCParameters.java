package speakerrecognition.impl.MFCC;

public interface SettersForMFCCParameters {
    private int setFrameLen(int sample_rate){
        return (int) (0.025*(double)(sample_rate));
    }


}
