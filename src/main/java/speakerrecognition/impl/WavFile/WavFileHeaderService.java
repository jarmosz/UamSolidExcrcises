package speakerrecognition.impl.WavFile;

public class WavFileHeaderService {
    public int toInt(byte hb, byte lb){
        return ((int)hb << 8) | ((int)lb & 0xFF);
    }

    public int getFs(byte x1, byte x2, byte x3, byte x4){
        return ((int)x1 & 0xFF | (int)x2 << 8 | (int)x3 << 16 | (int)x4 << 24 );
    }

    public int getSamplesNum(byte x1, byte x2, byte x3, byte x4){
        return ((int)x1 & 0xFF | ((int)x2 << 8) & 0xFF00 | ((int)x3 << 16) & 0xFF0000 | ((int)x4 << 24) & 0xFF000000);
    }

    public int getChannelsNum(byte x1, byte x2){
        return ((int)x2 << 8) | ((int)x1 & 0xFF);
    }

}
