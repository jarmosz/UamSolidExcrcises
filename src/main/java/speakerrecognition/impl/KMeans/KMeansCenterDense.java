package speakerrecognition.impl.KMeans;

public class KMeansCenterDense {
    private double[][] centers_dense(double[][] X, int[] labels, int n_clusters, double[] distances){
        double[][] result = new double[n_clusters][X[0].length];
        for(int j=0;j<X[0].length;j++){
            double[] sum = new double[n_clusters];
            for(int k=0;k<n_clusters;k++){
                int samples_num = 0;
                for(int z=0;z<labels.length;z++){
                    if(labels[z]==k){
                        sum[k] += X[z][j];
                        samples_num += 1;
                    }
                }
                sum[k] /= samples_num;

            }
            for(int i=0;i<n_clusters;i++)
                result[i][j] = sum[i];
        }
        return result;

    }

    public double[][] getCenterDense(double[][] X, int[] labels, int n_clusters, double[] distances){
        return centers_dense(X, labels, n_clusters, distances);
    }
}
