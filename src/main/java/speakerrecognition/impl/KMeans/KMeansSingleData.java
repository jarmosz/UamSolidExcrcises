package speakerrecognition.impl.KMeans;

import speakerrecognition.impl.CustomException;
import speakerrecognition.impl.Matrixes;

public class KMeansSingleData {
    private int[] best_labels = null;
    private double[][] best_centers = null;
    private double best_inertia = Double.MAX_VALUE;
    private double[] distances = null;

    private KMeansGetCenteroidsService kMeansGetCenteroidsService;
    private KMeansCenterDense kMeansCenterDense;

    public KMeansSingleData(double[][] data, int n_clusters, double[] x_sq_norms, int max_iter, double tol, int numOfRows, int numOfCols) throws CustomException {
        kMeansGetCenteroidsService = new KMeansGetCenteroidsService();
        kMeansCenterDense = new KMeansCenterDense();

        double[][] centers = kMeansGetCenteroidsService.getInitCenteroids(data, n_clusters, x_sq_norms, numOfRows, numOfCols);
        this.distances = new double[data.length];

        for (int i = 0; i < max_iter; i++) {
            double[][] centers_old = centers.clone();
            KMeansLabelsInertiaData labelsInertia = new KMeansLabelsInertiaData(data, x_sq_norms, centers, this.distances);
            int[] labels = labelsInertia.getLabels().clone();
            double inertia = labelsInertia.getInertia();
            this.distances = labelsInertia.getDistances().clone();

            centers = kMeansCenterDense.getCenterDense(data, labels, n_clusters, distances);

            if (inertia < best_inertia) {
                this.best_labels = labels.clone();
                this.best_centers = centers.clone();
                this.best_inertia = inertia;
            }

            if (Matrixes.squared_norm(Matrixes.substractMatrixes(centers_old, centers)) <= tol)
                break;

        }
    }

    public int[] getBest_labels() {
        return best_labels;
    }

    public double[][] getBest_centers() {
        return best_centers;
    }

    public double getBest_inertia() {
        return best_inertia;
    }
}
