package speakerrecognition.impl.MFCC;

public interface MathPreemphasis {
    default double[] preemphasis(double[] x, double pre_emph){
        double[] y = new double[x.length];
        y[0] = x[0];
        for(int i=1;i<x.length;i++){
            y[i] = x[i]-pre_emph*x[i-1];
        }
        return y;
    }

}
