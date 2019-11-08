package speakerrecognition.impl.GMM;

public class GMMData {
    private static final double EPS = 2.2204460492503131e-16;
    private int n_init=10;
    private int n_iter = 10;
    private int numOfRows;
    private int numOfCols;
    private int maxIter;
    private double threshold;
    private int numOfComponents;
    private double[][] observations;
    private double min_covar = 0.001;
    private boolean converged = false;
    private double current_log_likelihood = 0;
    private double prev_log_likelihood = Double.NaN;
    private double tol = 0.001;

    private double[] log_likelihoods = null;
    private double[][] responsibilities = null;

    private double[][] means = null;
    private double[] weights = null;
    private double[][] covars = null;

    private double[][] best_means = null;
    private double[] best_weights = null;
    private double[][] best_covars = null;


    public GMMData(double[][] data, int compNum){
        this.observations = data;
        this.numOfRows = data.length;
        this.numOfCols = data[0].length;
        this.numOfComponents = compNum;
        this.means = new double[compNum][data[0].length];
        this.weights = new double[data.length];
        this.covars = new double[compNum][data[0].length];
    }

    public double[][] getMeans() {
        return means;
    }

    public double[] getWeights() {
        return weights;
    }

    public double[][] getCovars() {
        return covars;
    }

    public double getEPS() {
        return EPS;
    }

    public double getMin_covar() {
        return min_covar;
    }

    public int getN_init() {
        return n_init;
    }

    public int getN_iter() {
        return n_iter;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfCols() {
        return numOfCols;
    }

    public int getMaxIter() {
        return maxIter;
    }

    public double getThreshold() {
        return threshold;
    }

    public int getNumOfComponents() {
        return numOfComponents;
    }

    public double[][] getObservations() {
        return observations;
    }

    public boolean isConverged() {
        return converged;
    }

    public double getCurrent_log_likelihood() {
        return current_log_likelihood;
    }

    public double getPrev_log_likelihood() {
        return prev_log_likelihood;
    }

    public double getTol() {
        return tol;
    }

    public double[] getLog_likelihoods() {
        return log_likelihoods;
    }

    public double[][] getResponsibilities() {
        return responsibilities;
    }

    public double[][] getBest_means() {
        return best_means;
    }

    public double[] getBest_weights() {
        return best_weights;
    }

    public double[][] getBest_covars() {
        return best_covars;
    }

    public void setMeans(double[][] means) {
        this.means = means;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public void setCovars(double[][] covars) {
        this.covars = covars;
    }

    public void setN_init(int n_init) {
        this.n_init = n_init;
    }

    public void setN_iter(int n_iter) {
        this.n_iter = n_iter;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public void setNumOfCols(int numOfCols) {
        this.numOfCols = numOfCols;
    }

    public void setMaxIter(int maxIter) {
        this.maxIter = maxIter;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public void setNumOfComponents(int numOfComponents) {
        this.numOfComponents = numOfComponents;
    }

    public void setObservations(double[][] observations) {
        this.observations = observations;
    }

    public void setMin_covar(double min_covar) {
        this.min_covar = min_covar;
    }

    public void setConverged(boolean converged) {
        this.converged = converged;
    }

    public void setCurrent_log_likelihood(double current_log_likelihood) {
        this.current_log_likelihood = current_log_likelihood;
    }

    public void setPrev_log_likelihood(double prev_log_likelihood) {
        this.prev_log_likelihood = prev_log_likelihood;
    }

    public void setTol(double tol) {
        this.tol = tol;
    }

    public void setLog_likelihoods(double[] log_likelihoods) {
        this.log_likelihoods = log_likelihoods;
    }

    public void setResponsibilities(double[][] responsibilities) {
        this.responsibilities = responsibilities;
    }

    public void setBest_means(double[][] best_means) {
        this.best_means = best_means;
    }

    public void setBest_weights(double[] best_weights) {
        this.best_weights = best_weights;
    }

    public void setBest_covars(double[][] best_covars) {
        this.best_covars = best_covars;
    }
}
