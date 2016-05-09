public class FFT {

    public static Complex[] fft(byte[] x){
        int N = x.length;
        if(N == 1) {
            return new Complex[]{new Complex(x[0], 0)};
        }
        if(N % 2 != 0) {
            throw new RuntimeException("Not power of 2!");
        }

        //First even
        byte[] even = new byte[N/2];
        for(int i = 0; i <  N/2; i++){
            even[i] = x[2*i];
        }
        Complex[] q = fft(even);

        //Then odd
        byte[] odd = even;
        for(int i = 0; i < N/2; i++){
            odd[i] = x[2*i+1];
        }
        Complex[] r = fft(odd);

        //Combine
        Complex[] y = new Complex[N];
        for(int i = 0; i < N/2; i++){
            double angle = 2 * i * Math.PI / N;
            Complex tmp = new Complex(Math.cos(angle), Math.sin(angle));
            y[i]       = q[i].plus(tmp.times(r[i]));
            y[i + N/2] = q[i].minus(tmp.times(r[i]));
        }
        return y;
    }
}
