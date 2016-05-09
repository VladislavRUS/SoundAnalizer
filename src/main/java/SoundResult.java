import java.util.Map;

public class SoundResult {
    private Map<Double, Double> soundData;
    private double maxMagnitude;

    public SoundResult(Map<Double, Double> soundData, double maxMagnitude) {
        this.soundData = soundData;
        this.maxMagnitude = maxMagnitude;
    }

    public Map<Double, Double> getSoundData() {
        return soundData;
    }

    public double getMaxMagnitude() {
        return maxMagnitude;
    }
}
