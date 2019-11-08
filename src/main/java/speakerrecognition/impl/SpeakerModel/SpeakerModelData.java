package speakerrecognition.impl.SpeakerModel;

public class SpeakerModelData {
    private static final long serialVersionUID = 1L;
    private double[][] means=null;
    private double[][] covars=null;
    private double[] weights = null;
    private String name = null;

    public SpeakerModelData(double[][] means, double[][] covars, double[] weights, String name){
        this.means = means;
        this.covars=covars;
        this.weights=weights;
        this.name = name;
    }

    public double[][] getMeans(){
        return this.means;
    }

    public double[][] getCovars(){
        return this.covars;
    }

    public double[] getWeights(){
        return this.weights;
    }

    public String getName(){
        return this.name;
    }
}
