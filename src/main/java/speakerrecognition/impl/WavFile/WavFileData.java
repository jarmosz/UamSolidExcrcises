package speakerrecognition.impl.WavFile;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WavFileData {
    private byte[] byte_samples;
    private int[] samples;
    private int fs;
    private Path file_path;
    private int samples_num;
    private int channels_num;

    WavFileData(String x){
        this.file_path = Paths.get(x);
    }

    int[] getSamples(){
        return this.samples;
    }

    int getFs(){
        return this.fs;
    }

    public byte[] getByte_samples() {
        return byte_samples;
    }

    public Path getFile_path() {
        return file_path;
    }

    public int getSamples_num() {
        return samples_num;
    }

    public int getChannels_num() {
        return channels_num;
    }

    public void setByte_samples(byte[] byte_samples) {
        this.byte_samples = byte_samples;
    }

    public void setSamples(int[] samples) {
        this.samples = samples;
    }

    public void setFs(int fs) {
        this.fs = fs;
    }

    public void setFile_path(Path file_path) {
        this.file_path = file_path;
    }

    public void setSamples_num(int samples_num) {
        this.samples_num = samples_num;
    }

    public void setChannels_num(int channels_num) {
        this.channels_num = channels_num;
    }
}
