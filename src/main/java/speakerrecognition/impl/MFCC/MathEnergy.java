package speakerrecognition.impl.MFCC;

public interface MathEnergy {
    default double energy(double[] x){
        double en = 0;
        for(int i=0; i<x.length;i++)
            en = en + Math.pow(x[i], 2);
        return en;
    }
}
