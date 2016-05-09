import javax.sound.sampled.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class SoundData {
    private static final int SAMPLE_RATE = 8000; //Hz
    public static final int MAX_FREQUENCY = 3500; //Hz
    public static final int bufferSize = 4096;
    private final TargetDataLine line;
    //Holds data, recorded from micro
    private static Map<Double, Double> soundData = new LinkedHashMap<>();
    //Result of the capturing, containing samples and max magnitude
    private static SoundResult result = new SoundResult(soundData, 0);
    private static byte[] buffer = new byte[bufferSize];
    private static double maxMagnitude = 0;

    public SoundData() throws LineUnavailableException {
        AudioFormat format = getFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if(!AudioSystem.isLineSupported(info)){
            System.out.println("Not supported!");
        }
        line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();
    }

    public SoundResult getData() throws LineUnavailableException {
        int cnt = line.read(buffer, 0, buffer.length);
        if(cnt > 0){
            Complex[] complexResults = FFT.fft(buffer);
            maxMagnitude = 0;
            for(int i = 0; i < bufferSize/2; i++){
                double freq = Math.round(i*SAMPLE_RATE/bufferSize);
                if(freq > MAX_FREQUENCY)
                    continue;
                double magnitude = Math.abs(Math.round(complexResults[i].getReal()));
                if(magnitude > maxMagnitude){
                    maxMagnitude = magnitude;
                }
                soundData.put(freq, magnitude);
            }
            result.setSoundData(soundData);
            result.setMaxMagnitude(maxMagnitude);
        }
        return result;
    }

    private AudioFormat getFormat(){
        float sampleRate = SAMPLE_RATE;
        int sampleSizeInBits = 8;
        int channels = 1;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, true, true);
    }
}
