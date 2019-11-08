package speakerrecognition.impl.MFCC;

import org.jtransforms.fft.DoubleFFT_1D;
import speakerrecognition.impl.Matrixes;

public class MFCCService implements MathPreemphasis{
	private MFCCData mfccData;

	public MFCCService(int[] x, int y){
		this.mfccData = new MFCCData(x,y);
	}

	private void extract_MFCC(){

		if(this.mfccData.getSamples()!=null){
			DoubleFFT_1D fftDo = new DoubleFFT_1D(this.mfccData.getFrame_len());
			double[] fft1 = new double[this.mfccData.getFrame_len() * 2];
			double[] fft_final = new double[this.mfccData.getFrame_len()/2+1];
			int frames_num = (int)((double)(this.mfccData.getSamples().length - this.mfccData.getFrame_len())/(double)(this.mfccData.getFrame_shift()))+1;
			this.mfccData.setMfcc_coeffs(new double[frames_num][mfccData.getMfcc_num()]);
			double[] frame = new double[this.mfccData.getFrame_len()];

			for(int i=0;i<frames_num;i++){

				for(int j=0;j<this.mfccData.getFrame_len();j++){
					frame[j] = (double)this.mfccData.getSamples()[i*this.mfccData.getFrame_shift()+j];
				}

				try{
					frame = Matrixes.row_mul(frame, mfccData.getWindow());

					frame = preemphasis(frame, this.mfccData.getPre_emph());
					System.arraycopy(frame, 0, fft1, 0, this.mfccData.getFrame_len());
					fftDo.realForwardFull(fft1);
					/*for(double d: fft1) {
			          System.out.println(d);
					}*/

					for(int k=0;k<(this.mfccData.getFrame_len()/2+1);k++){
						fft_final[k] = Math.pow(Math.sqrt(Math.pow(fft1[k*2],2)+Math.pow(fft1[k*2+1],2)), 2);

						if(fft_final[k]<this.mfccData.getPower_spectrum_floor()) fft_final[k]=this.mfccData.getPower_spectrum_floor();
					}

					double[] dot_prod = Matrixes.multiplyByMatrix(this.mfccData.getMelfb_coeffs(), fft_final);
					for(int j=0;j<dot_prod.length;j++){
						dot_prod[j] = Math.log(dot_prod[j]);
					}
					dot_prod = Matrixes.multiplyByMatrix(this.mfccData.getD1(), dot_prod);
					this.mfccData.setMfcc_coeffsByIndex(dot_prod, i);
				}
				catch(Exception myEx)
				{
					System.out.println("An exception encourred: " + myEx.getMessage());
					myEx.printStackTrace();
					System.exit(1);
				}

			}
			//this.mfcc_coeffs =
		}
		else{
			System.out.println("Vector of input samples is null");
		}

	}

	public double[][] getMFCC(){
		extract_MFCC();
		return mfccData.getMfcc_coeffs();
	}
}
