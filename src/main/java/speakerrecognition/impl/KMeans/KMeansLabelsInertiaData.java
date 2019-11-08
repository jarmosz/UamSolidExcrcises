package speakerrecognition.impl.KMeans;

import speakerrecognition.impl.Matrixes;

public class KMeansLabelsInertiaData {
    private double[][] data = null;
    private int[] labels = null;
    private double[][] centers = null;
    private double[] distances = null;
    private double[] x_squared_norms = null;
    private double inertia = 0;

    private KMeansLabelsPrecomputeDenceData labelsPrecomputeDenceData;

    KMeansLabelsInertiaData(double[][] X, double[] x_squared_norms, double[][] centers, double[] distances){
        this.centers = centers;
        this.x_squared_norms = x_squared_norms;
        this.distances = distances;
        this.data = X;

        int n_samples = data.length;
        int[] labels = new int[n_samples];
        labels = Matrixes.addValue(labels, -1);

        KMeansLabelsPrecomputeDenceData labelsPrecomputeDenceData = new KMeansLabelsPrecomputeDenceData(this.data, this.x_squared_norms, this.centers, this.distances);
        this.labels = labelsPrecomputeDenceData.getLabels().clone();
        this.inertia = labelsPrecomputeDenceData.getInertia();
        this.distances = labelsPrecomputeDenceData.getDistances().clone();

    }

    public int[] getLabels() {
        return labels;
    }

    public double[] getDistances() {
        return distances;
    }

    public double getInertia() {
        return inertia;
    }
}
