package speakerrecognition.impl.TimitFileNames;

import java.io.File;
import java.util.Arrays;

public class TimitFileNamesData {
    private String timitPath = null;
    private String[] names = new String[630];

    public TimitFileNamesData(String name){
        this.timitPath = name;
    }

    public String getTimitPath() {
        return timitPath;
    }

    public String[] getNames() {
        return names;
    }
}
