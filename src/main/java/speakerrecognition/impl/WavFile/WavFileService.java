package speakerrecognition.impl.WavFile;
import speakerrecognition.impl.CustomException;

import java.io.IOException;
import java.io.RandomAccessFile;


public class WavFileService implements Openable {

	private WavFileData wavFileData;
	private WavFileHeaderService wavFileHeaderService;

	public WavFileService(String x){
		this.wavFileData = new WavFileData(x);
		this.wavFileHeaderService = new WavFileHeaderService();
	}

	public int[] getSamples(){
		return this.wavFileData.getSamples();
	}

	public int getFs(){
		return this.wavFileData.getFs();
	}


	public void open() throws IOException, CustomException {

		RandomAccessFile in = null;
		try{
			in = new RandomAccessFile(this.wavFileData.getFile_path().toString(), "r");
		}
		catch(Exception myEx)
		{
			myEx.printStackTrace();
			System.exit(1);
		}
		
		this.wavFileData.setByte_samples(new byte[(int) in.length()]);


		in.read(this.wavFileData.getByte_samples(), 0, (int) (in.length()));

		this.wavFileData.setSamples_num(this.wavFileHeaderService.getSamplesNum(this.wavFileData.getByte_samples()[40], this.wavFileData.getByte_samples()[41], this.wavFileData.getByte_samples()[42], this.wavFileData.getByte_samples()[43]));
		this.wavFileData.setChannels_num(this.wavFileHeaderService.getChannelsNum(this.wavFileData.getByte_samples()[22], this.wavFileData.getByte_samples()[23]));

		this.wavFileData.setSamples(new int[this.wavFileData.getSamples_num()/2/this.wavFileData.getChannels_num()]);

		this.wavFileData.setFs(this.wavFileHeaderService.getFs(this.wavFileData.getByte_samples()[24], this.wavFileData.getByte_samples()[25], this.wavFileData.getByte_samples()[26], this.wavFileData.getByte_samples()[27]));

		if(this.wavFileData.getChannels_num()==1){
			for (int i=44;i<(this.wavFileData.getSamples_num()+44)/2; i++){
				this.wavFileData.getSamples()[i-44] = this.wavFileHeaderService.toInt(this.wavFileData.getByte_samples()[(i-44)*2+45], this.wavFileData.getByte_samples()[(i-44)*2+44]);
			}
		}
		else if(this.wavFileData.getChannels_num()==2){
			int j=44;
			for (int i=44;i<(this.wavFileData.getSamples_num()+44)/2; i+=2){
				this.wavFileData.getSamples()[j-44] = (this.wavFileHeaderService.toInt(this.wavFileData.getByte_samples()[(i-44)*2+45], this.wavFileData.getByte_samples()[(i-44)*2+44])+this.wavFileHeaderService.toInt(this.wavFileData.getByte_samples()[(i-44)*2+47], this.wavFileData.getByte_samples()[(i-44)*2+46]))/2;
				j++;
			}
		}
		else{
			System.out.println("Too much channels, only 1 or 2 are supported");
		}

		in.close();

	}


}

