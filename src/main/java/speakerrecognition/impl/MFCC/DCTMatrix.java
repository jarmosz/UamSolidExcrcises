package speakerrecognition.impl.MFCC;

import speakerrecognition.impl.Matrixes;

public interface DCTMatrix {
    default double[][] dctmatrix(int n, int mfcc_num){
        double[][] d1 = new double[n][n];
        double[][] x = Matrixes.meshgrid_ox(n);
        double[][] y = Matrixes.meshgrid_oy(n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                x[i][j] = (x[i][j]*2+1)*Math.PI/(2*n);
            }
        }

        try{
            d1 = Matrixes.multiplyMatrixesElByEl(x, y);
        }
        catch(Exception myEx)
        {
            //System.out.println("An exception encourred: " + myEx.getMessage());
            myEx.printStackTrace();
            System.exit(1);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                d1[i][j] = Math.sqrt(2/(double)n)*Math.cos(d1[i][j]);
            }
        }
        for(int i=0;i<n;i++){
            d1[0][i] /= Math.sqrt(2);
        }

        double[][] d = new double[mfcc_num][n];
        for(int i=1;i<mfcc_num+1;i++){
            for(int j=0;j<n;j++){
                d[i-1][j] = d1[i][j];
            }

        }

        return d;
    }
}
