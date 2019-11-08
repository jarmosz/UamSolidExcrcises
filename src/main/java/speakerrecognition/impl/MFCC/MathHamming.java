package speakerrecognition.impl.MFCC;

public interface MathHamming {
    default double[] hamming(int frame_len){
        double[] window_temp = new double[frame_len];
        for(int i=0;i<window_temp.length;i++){
            window_temp[i] = 0.54-0.46*Math.cos(2*Math.PI/(double)frame_len*((double)i+0.5));
        }
        return window_temp;
    }
}
