package speakerrecognition.impl.SpeakerModel;

import speakerrecognition.impl.CustomException;
import speakerrecognition.impl.Matrixes;

public class SpeakerModelService implements SpeakerModel{
	private SpeakerModelData speakerModelData;

	public SpeakerModelService(double[][] means, double[][] covars, double[] weights, String name){
		speakerModelData = new SpeakerModelData(means, covars,  weights, name);
	}

	@Override
	public double getScore(double[][] data) throws CustomException {
		return 0;
	}

	public SpeakerModelData getSpeakerModelData() {
		return speakerModelData;
	}

	private double[][] logMultivariateNormalDensity(double[][] data, double[][] means, double[][] covars) throws CustomException {
		double[][] lpr = new double[data.length][means.length];
		int n_samples = data.length;
		int n_dim = data[0].length;

		double[] sumLogCov = Matrixes.sum(Matrixes.makeLog(covars), 1); //np.sum(np.log(covars), 1)
		double[] sumDivMeanCov = Matrixes.sum(Matrixes.divideElements(Matrixes.power(this.speakerModelData.getMeans(), 2), this.speakerModelData.getCovars()),1); //np.sum((means ** 2) / covars, 1)
		double[][] dotXdivMeanCovT = Matrixes.multiplyByValue(Matrixes.multiplyByMatrix(data, Matrixes.transpose(Matrixes.divideElements(means, covars))), -2); //- 2 * np.dot(X, (means / covars).T)
		double[][] dotXdivOneCovT = Matrixes.multiplyByMatrix(Matrixes.power(data,  2), Matrixes.transpose(Matrixes.invertElements(covars)));


		sumLogCov = Matrixes.addValue(sumLogCov,n_dim * Math.log(2*Math.PI)); //n_dim * np.log(2 * np.pi) + np.sum(np.log(covars), 1)
		sumDivMeanCov = Matrixes.addMatrixes(sumDivMeanCov, sumLogCov); // n_dim * np.log(2 * np.pi) + np.sum(np.log(covars), 1) + np.sum((means ** 2) / covars, 1)
		dotXdivOneCovT = Matrixes.sum(dotXdivOneCovT, dotXdivMeanCovT); //- 2 * np.dot(X, (means / covars).T) + np.dot(X ** 2, (1.0 / covars).T)
		dotXdivOneCovT = Matrixes.addValue(dotXdivOneCovT, sumDivMeanCov); // (n_dim * np.log(2 * np.pi) + np.sum(np.log(covars), 1) + np.sum((means ** 2) / covars, 1) - 2 * np.dot(X, (means / covars).T) + np.dot(X ** 2, (1.0 / covars).T))
		lpr = Matrixes.multiplyByValue(dotXdivOneCovT, -0.5);

		return lpr;
	};
}
