package speakerrecognition.impl.KMeans;

import speakerrecognition.impl.CustomException;
import speakerrecognition.impl.Matrixes;
import speakerrecognition.impl.Statistics.StatisticsMeanService;
import speakerrecognition.impl.Statistics.StatisticsService;

public class KMeansData implements KMeansToleranceCountService {
    private int numOfClusters;
    private int numOfRows;
    private int numOfCols;
    private double[][] data;
    private int n_iter = 0;
    private int n_init = 10;
    private int max_iter = 300;
    private double tol = 0.0001;

    private double[][] best_cluster_centers = null;
    private int[] best_labels = null;
    private double best_inertia = Double.MAX_VALUE;
    private int n_iter_ = 0;

    private StatisticsMeanService statisticsMeanService;
    private KMeansSingleData kMeansSingleData;

    public KMeansData(double[][] x, int numOfClust) throws CustomException {
        this.numOfClusters = numOfClust;
        int n_init = 10;
        int max_iter = 300;
        double tol = 0.0001;
        this.tol = tolerance(x, tol);
        this.numOfRows = x.length;
        this.numOfCols = x[0].length;
        this.data = Matrixes.copy2dArray(x);
        this.best_cluster_centers = new double[numOfClust][x[0].length];
        this.best_labels = new int[x.length];
        statisticsMeanService = new StatisticsMeanService();
        kMeansSingleData = new KMeansSingleData(this.data, this.numOfClusters, Matrixes.einsum(this.data), this.max_iter, this.tol, this.numOfRows, this.numOfCols);
    }

    public StatisticsMeanService getStatisticsMeanService() {
        return statisticsMeanService;
    }

    public double[][] getData() {
        return data;
    }

    public int getNumOfClusters() {
        return numOfClusters;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfCols() {
        return numOfCols;
    }

    public int getN_iter() {
        return n_iter;
    }

    public int getN_init() {
        return n_init;
    }

    public int getMax_iter() {
        return max_iter;
    }

    public double getTol() {
        return tol;
    }

    public double[][] getBest_cluster_centers() {
        return best_cluster_centers;
    }

    public int[] getBest_labels() {
        return best_labels;
    }

    public double getBest_inertia() {
        return best_inertia;
    }

    public int getN_iter_() {
        return n_iter_;
    }

    public void setNumOfClusters(int numOfClusters) {
        this.numOfClusters = numOfClusters;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public void setNumOfCols(int numOfCols) {
        this.numOfCols = numOfCols;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public void setN_iter(int n_iter) {
        this.n_iter = n_iter;
    }

    public void setN_init(int n_init) {
        this.n_init = n_init;
    }

    public void setMax_iter(int max_iter) {
        this.max_iter = max_iter;
    }

    public void setTol(double tol) {
        this.tol = tol;
    }

    public void setBest_cluster_centers(double[][] best_cluster_centers) {
        this.best_cluster_centers = best_cluster_centers;
    }

    public void setBest_labels(int[] best_labels) {
        this.best_labels = best_labels;
    }

    public void setBest_inertia(double best_inertia) {
        this.best_inertia = best_inertia;
    }

    public void setN_iter_(int n_iter_) {
        this.n_iter_ = n_iter_;
    }

    public void setStatisticsMeanService(StatisticsMeanService statisticsMeanService) {
        this.statisticsMeanService = statisticsMeanService;
    }
}
