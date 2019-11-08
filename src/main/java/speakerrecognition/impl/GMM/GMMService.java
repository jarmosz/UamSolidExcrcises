package speakerrecognition.impl.GMM;

public class GMMService {
	private GMMFitService gmmFitService;

	public GMMService(double[][] data, int compNum){
		gmmFitService = new GMMFitService(data, compNum);
	}

	public GMMFitService getGmmFitService() {
		return gmmFitService;
	}
}
