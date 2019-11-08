package speakerrecognition.impl.MFCC;

import speakerrecognition.impl.Matrixes;

public class MFCCMelfFilterBankService implements MathArrange, MathEnergy {
    private double[][] melfb(int p, int n, int fs) {

        double f0 = 700 / (double) fs;
        int fn2 = (int) Math.floor((double) n / 2);
        double lr = Math.log((double) 1 + 0.5 / f0) / (p + 1);
        double[] CF = arange(1, p + 1);

        for (int i = 0; i < CF.length; i++) {
            CF[i] = fs * f0 * (Math.exp(CF[i] * lr) - 1);
        }

        double[] bl = {0, 1, p, p + 1};

        for (int i = 0; i < bl.length; i++) {
            bl[i] = n * f0 * (Math.exp(bl[i] * lr) - 1);
        }

        int b1 = (int) Math.floor(bl[0]) + 1;
        int b2 = (int) Math.ceil(bl[1]);
        int b3 = (int) Math.floor(bl[2]);
        int b4 = Math.min(fn2, (int) Math.ceil(bl[3])) - 1;
        double[] pf = arange(b1, b4 + 1);

        for (int i = 0; i < pf.length; i++) {
            pf[i] = Math.log(1 + pf[i] / f0 / (double) n) / lr;
        }

        double[] fp = new double[pf.length];
        double[] pm = new double[pf.length];

        for (int i = 0; i < fp.length; i++) {
            fp[i] = Math.floor(pf[i]);
            pm[i] = pf[i] - fp[i];
        }

        double [][] M = new double[p][1 + fn2];
        int r = 0;

        for (int i = b2 - 1; i < b4; i++) {
            r = (int) fp[i] - 1;
            M[r][i + 1] += 2 * (1 - pm[i]);
        }

        for (int i = 0; i < b3; i++) {
            r = (int) fp[i];
            M[r][i + 1] += 2 * pm[i];
        }

        double[] temp_row = null;
        double row_energy = 0;
        //System.out.println(Integer.toString(M.length));
        for (int i = 0; i < M.length; i++) {
            temp_row = M[i];
            row_energy = energy(temp_row);
            if (row_energy < 0.0001)
                temp_row[i] = i;
            else {
                while (row_energy > 1.01) {
                    temp_row = Matrixes.row_mul(temp_row, 0.99);
                    row_energy = energy(temp_row);
                }
                while (row_energy < 0.99) {
                    temp_row = Matrixes.row_mul(temp_row, 1.01);
                    row_energy = energy(temp_row);
                }
            }
            M[i] = temp_row;

        }


        return M;
    }

    public double[][] getMelfb(int p, int n, int fs){
        return melfb(p, n, fs);
    }
}
