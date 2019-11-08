package speakerrecognition.impl.MFCC;

public interface MathArrange {
     default double[] arange(int x1, int x2){
        double[] temp = null;
        try{
            temp = new double[x2-x1];
            for(int i=0;i<temp.length;i++){
                temp[i] = x1+i;
            }

        }
        catch(IndexOutOfBoundsException e){
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }
        return temp;
    }
}
