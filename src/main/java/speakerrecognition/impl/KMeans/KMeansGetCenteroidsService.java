package speakerrecognition.impl.KMeans;

import speakerrecognition.impl.Matrixes;

import java.util.Arrays;

public class KMeansGetCenteroidsService {
    private double[][] init_centroids(double[][] data, int n_clusters, double[] x_sq_norms, int numOfRows, int numOfCols){
        double centers[][] = new double[n_clusters][numOfCols];

        try{
            int n_local_trials = 2+(int)(Math.log(n_clusters));
            int center_id = (int) Math.floor(Math.random() * numOfRows);
            for(int i=0;i<numOfCols;i++){
                centers[0][i] = data[center_id][i];
            }
            double[] closest_dist_sq =  Matrixes.euclidean_distances(centers[0], data, x_sq_norms);
            double current_pot = Matrixes.sum(closest_dist_sq);

            for(int c=1; c<n_clusters;c++){
                double[] rand_vals = Matrixes.genRandMatrix(current_pot, n_local_trials);
                double[] closest_dist_sq_cumsum = Matrixes.cumsum(closest_dist_sq);
                int[] candidate_ids = Matrixes.searchsorted(closest_dist_sq_cumsum, rand_vals);
                double[][] data_candidates = new double[n_local_trials][numOfCols];

                for(int z=0;z<n_local_trials;z++){
                    for(int j=0;j<numOfCols;j++){
                        data_candidates[z][j] = data[candidate_ids[z]][j];
                    }
                }

                int best_candidate = -1;
                double best_pot = 99999999;
                double[] best_dist_sq = null;

                double[][] distance_to_candidates = Matrixes.euclidean_distances(data_candidates, data, x_sq_norms);

                for(int trial=0;trial<n_local_trials;trial++){
                    double[] new_dist_sq = Matrixes.minimum(closest_dist_sq, Matrixes.select_row(distance_to_candidates, trial));
                    double new_pot = Matrixes.sum(new_dist_sq);

                    if(best_candidate==-1 | new_pot < best_pot){
                        best_candidate = candidate_ids[trial];
                        best_pot = new_pot;
                        best_dist_sq = Arrays.copyOf(new_dist_sq, new_dist_sq.length);
                    }
                }
                double[] center_temp = Arrays.copyOf(data[best_candidate], data[best_candidate].length);
                for(int ii=0;ii<centers[0].length;ii++){
                    centers[c][ii] = center_temp[ii];
                }
                current_pot = best_pot;
                closest_dist_sq = Arrays.copyOf(best_dist_sq, best_dist_sq.length);

            }
        }
        catch(Exception myEx)
        {
            myEx.printStackTrace();
            System.exit(1);
        }

        return centers;

    }

    public double[][] getInitCenteroids(double[][] data, int n_clusters, double[] x_sq_norms, int numOfRows, int numOfCols){
        return init_centroids(data, n_clusters, x_sq_norms, numOfRows, numOfCols);
    }
}
