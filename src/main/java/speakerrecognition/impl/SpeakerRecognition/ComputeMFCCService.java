package speakerrecognition.impl.SpeakerRecognition;

import speakerrecognition.impl.MFCC.MFCCService;

public class ComputeMFCCService implements ComputableMFCC {
    @Override
    public double[][] computeMFCC(int[] soundSamples, int fs) {
        MFCCService mfcc = new MFCCService(soundSamples, fs);
        double[][] speaker_mfcc = mfcc.getMFCC();
        return speaker_mfcc;
    }
}
