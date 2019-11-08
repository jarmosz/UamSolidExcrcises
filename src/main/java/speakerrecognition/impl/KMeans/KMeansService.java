package speakerrecognition.impl.KMeans;

import speakerrecognition.impl.CustomException;
import speakerrecognition.impl.Matrixes;

public class KMeansService implements FitService {

	private KMeansData kMeansData;

	public KMeansService(double[][] x, int numOfClust) throws CustomException {
		kMeansData = new KMeansData(x, numOfClust);
	}

	@Override
	public void fit() {
		double[][] cluster_centers = null;
		int[] labels = null;
		double inertia = 0;

		try{

			double[] X_mean = kMeansData.getStatisticsMeanService().getMean(kMeansData.getData());
			for(int i=0;i<this.kMeansData.getNumOfRows();i++){
				for(int j=0; j<this.kMeansData.getNumOfCols(); j++){
					this.kMeansData.getData()[i][j] -= X_mean[j];
				}
			}



			double[] x_squared_norms = Matrixes.einsum(this.kMeansData.getData());

			for(int i=0;i<this.kMeansData.getN_init();i++){
				KMeansSingleData kmeansSingle = new KMeansSingleData (this.kMeansData.getData(), this.kMeansData.getNumOfClusters(), x_squared_norms, this.kMeansData.getMax_iter(), this.kMeansData.getTol(), this.kMeansData.getNumOfRows(), this.kMeansData.getNumOfCols());
				cluster_centers = kmeansSingle.getBest_centers().clone();
				inertia = kmeansSingle.getBest_inertia();
				labels = kmeansSingle.getBest_labels().clone();

				if (inertia<this.kMeansData.getBest_inertia()){
					this.kMeansData.setBest_labels(labels.clone());
					this.kMeansData.setBest_inertia(inertia);
					this.kMeansData.setBest_cluster_centers(cluster_centers.clone());

				}

			}

			this.kMeansData.setBest_cluster_centers(Matrixes.addValue(this.kMeansData.getBest_cluster_centers(), X_mean));
		}
		catch(Exception myEx)
		{
			myEx.printStackTrace();
			System.exit(1);
		}
	}

	public KMeansData getkMeansData() {
		return kMeansData;
	}
}
