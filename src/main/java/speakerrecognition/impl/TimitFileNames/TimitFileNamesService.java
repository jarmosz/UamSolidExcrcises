package speakerrecognition.impl.TimitFileNames;

import speakerrecognition.impl.TimitFileNames.TimitFileNamesData;

import java.io.File;
import java.util.Arrays;


public class TimitFileNamesService {

	private TimitFileNamesData timitFileNamesData;

	public TimitFileNamesService(String name){
		this.timitFileNamesData = new TimitFileNamesData(name);
		createFolder();
	}
	
	public String[] getTimitNames(){
		return this.timitFileNamesData.getNames();
	}

	private void createFolder(){
		File folder = new File(this.timitFileNamesData.getTimitPath());
		File[] listOfFiles = folder.listFiles();
		int j=0;
		for (int i = 0; i < listOfFiles.length; i++) {
			String extension = listOfFiles[i].getName().substring(listOfFiles[i].getName().length()-3);
			if (listOfFiles[i].isFile() && extension.equals("wav")) {
				String fileName = listOfFiles[i].getName().substring(0,listOfFiles[i].getName().length()-5);
				if(!Arrays.asList(this.timitFileNamesData.getNames()).contains(fileName)){
					this.timitFileNamesData.getNames()[j]=fileName;
					j++;
				}
			}
		}
	}
	
	

}
