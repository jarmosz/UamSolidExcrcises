package speakerrecognition.impl.MFCC;

public class MFCCData implements MathHamming, MathFrameShift, MathFrameLength, DCTMatrix {
    private int frame_len;
    private int frame_shift;
    private int fft_size;// = 256;
    private  int melfilter_bands = 40;
    private  int mfcc_num = 13;
    private  double power_spectrum_floor = 0.0001;
    private  double pre_emph = 0.95;
    private double[] window = null;
    private double[][] M = null;
    private double[][] melfb_coeffs = null;
    private double[][] mfcc_coeffs = null;
    private int[] samples = null;
    private int fs;
    private double[][] D1 = null;

    private MFCCMelfFilterBankService mfccMelfFilterBankService;

    public MFCCData(int[] x, int y){
        this.fs = y;
        this.samples = x;
        this.frame_len = 256;
        this.fft_size = this.frame_len;
        this.frame_shift = setFrameShift(fs);
        window = hamming(frame_len);

        mfccMelfFilterBankService = new MFCCMelfFilterBankService();

        this.melfb_coeffs = mfccMelfFilterBankService.getMelfb(melfilter_bands, fft_size, fs);

        this.D1 = dctmatrix(melfilter_bands, mfcc_num);

        if(this.melfb_coeffs==null) System.out.println("Cannot initialize melfilter bank");
    }

    public double[][] getMfcc_coeffs() {
        return mfcc_coeffs;
    }

    public int getFrame_len() {
        return frame_len;
    }

    public int[] getSamples() {
        return samples;
    }

    public int getFrame_shift() {
        return frame_shift;
    }

    public int getFft_size() {
        return fft_size;
    }

    public  int getMelfilter_bands() {
        return melfilter_bands;
    }

    public  int getMfcc_num() {
        return mfcc_num;
    }

    public  double getPower_spectrum_floor() {
        return power_spectrum_floor;
    }

    public  double getPre_emph() {
        return pre_emph;
    }

    public double[] getWindow() {
        return window;
    }

    public double[][] getM() {
        return M;
    }

    public double[][] getMelfb_coeffs() {
        return melfb_coeffs;
    }

    public int getFs() {
        return fs;
    }

    public double[][] getD1() {
        return D1;
    }

    public void setFrame_len(int frame_len) {
        this.frame_len = frame_len;
    }

    public void setFrame_shift(int frame_shift) {
        this.frame_shift = frame_shift;
    }

    public void setFft_size(int fft_size) {
        this.fft_size = fft_size;
    }

    public  void setMelfilter_bands(int melfilter_bands) {
        this.melfilter_bands = melfilter_bands;
    }

    public  void setMfcc_num(int mfcc_num) {
        this.mfcc_num = mfcc_num;
    }

    public  void setPower_spectrum_floor(double power_spectrum_floor) {
        this.power_spectrum_floor = power_spectrum_floor;
    }

    public  void setPre_emph(double pre_emph) {
        this.pre_emph = pre_emph;
    }

    public void setWindow(double[] window) {
        this.window = window;
    }

    public void setM(double[][] m) {
        M = m;
    }

    public void setMelfb_coeffs(double[][] melfb_coeffs) {
        this.melfb_coeffs = melfb_coeffs;
    }

    public void setSamples(int[] samples) {
        this.samples = samples;
    }

    public void setFs(int fs) {
        this.fs = fs;
    }

    public void setD1(double[][] d1) {
        D1 = d1;
    }

    public void setMfcc_coeffs(double[][] mfcc_coeffs) {
        this.mfcc_coeffs = mfcc_coeffs;
    }

    public void setMfcc_coeffsByIndex(double[] value, int index) {
        this.mfcc_coeffs[index] = value;
    }
}
