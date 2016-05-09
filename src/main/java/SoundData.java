import javax.sound.sampled.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class SoundData {
    private static final int SAMPLE_RATE = 8000; //Hz
    public static final int bufferSize = 2048;
    private final AudioFormat format;
    private final DataLine.Info info;
    private final TargetDataLine line;

    public SoundData() throws LineUnavailableException {
        format = getFormat();
        info = new DataLine.Info(TargetDataLine.class, format);
        if(!AudioSystem.isLineSupported(info)){
            System.out.println("Not supported!");
        }
        line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();
    }

    public SoundResult getData() throws LineUnavailableException {
        byte[] buffer = new byte[bufferSize];
        int cnt = line.read(buffer, 0, buffer.length);
        SoundResult result = null;
        if(cnt > 0){
            Complex[] complexResults = FFT.fft(buffer);
            Map<Double, Double> soundData = new LinkedHashMap<>();
            double maxMagnitude = 0;
            for(int i = 0; i < bufferSize/2; i++){
                double freq = Math.round(i*SAMPLE_RATE/bufferSize);
                double magnitude = Math.abs(Math.round(complexResults[i].getReal()));
                if(magnitude > maxMagnitude){
                    maxMagnitude = magnitude;
                }
                soundData.put(freq, magnitude);
            }
            result = new SoundResult(soundData, maxMagnitude);
        }
        return result;
    }

    private AudioFormat getFormat(){
        float sampleRate = SAMPLE_RATE;
        int sampleSizeInBits = 8;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }
}
