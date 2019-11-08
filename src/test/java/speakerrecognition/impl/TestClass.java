package speakerrecognition.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import speakerrecognition.impl.GMM.GMMFitService;
import speakerrecognition.impl.MFCC.MFCCService;
import speakerrecognition.impl.SpeakerModel.SpeakerModelService;
import speakerrecognition.impl.SpeakerRecognition.LogProbabilityService;
import speakerrecognition.impl.SpeakerRecognition.SpeakerRecognitionService;
import speakerrecognition.impl.WavFile.WavFileService;


public class TestClass {
	
	private SpeakerRecognitionService speakerRecognition = new SpeakerRecognitionService();
	
	@Test
	public void testCase() throws IOException, ClassNotFoundException, CustomException {
		
		//given

		
		WavFileService wavFile = new WavFileService("src\\test\\resources\\training\\speaker1_2.WAV");
		wavFile.open();
		int[] x = wavFile.getSamples();
		int fs = wavFile.getFs();
		MFCCService mfcc = new MFCCService(x, fs);
		double[][] speaker_mfcc = mfcc.getMFCC();
		GMMFitService gmmService = new GMMFitService(speaker_mfcc, 32);
		gmmService.fit();
		SpeakerModelService speakerModel1 = new SpeakerModelService(gmmService.getGmmData().getMeans(), gmmService.getGmmData().getCovars(), gmmService.getGmmData().getWeights(), "speaker1model");
		
		WavFileService wavFile2 = new WavFileService("src\\test\\resources\\training\\speaker2_2.WAV");
		wavFile2.open();
		int[] x2 = wavFile2.getSamples();
		int fs2 = wavFile2.getFs();
		MFCCService mfcc2 = new MFCCService(x2, fs2);
		double[][] speaker_mfcc2 = mfcc2.getMFCC();
		GMMFitService gmmService2 = new GMMFitService(speaker_mfcc2, 32);
		gmmService2.fit();
		SpeakerModelService speakerModel = new SpeakerModelService(gmmService2.getGmmData().getMeans(), gmmService2.getGmmData().getCovars(), gmmService2.getGmmData().getWeights(), "speaker2model");
		
		List<SpeakerModelService> speakerModels = Arrays.asList(speakerModel1, speakerModel);
		
		//when
		
		System.out.println(speakerRecognition.recognize(speakerModels, "src\\test\\resources\\test\\speaker1_1.WAV"));
		System.out.println(speakerRecognition.recognize(speakerModels, "src\\test\\resources\\test\\speaker2_1.WAV"));

		LogProbabilityService logProbabilityService = new LogProbabilityService();
		logProbabilityService.printLogProbsForRecognition(speakerModels, "src\\test\\resources\\test\\speaker1_1.WAV");
		logProbabilityService.printLogProbsForRecognition(speakerModels, "src\\test\\resources\\test\\speaker2_1.WAV");
		
		//then

	}
	
}
