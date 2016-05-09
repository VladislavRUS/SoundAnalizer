import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GraphView extends JPanel{

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 600;
    private SoundResult soundResult;
    private static Font mainFreq = new Font("Verdana", Font.BOLD, 25);
    private static Font underLine = new Font("Verdana", Font.ITALIC, 10);

    public void setSoundResult(SoundResult soundResult) {
        this.soundResult = soundResult;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(soundResult != null) {
            Map<Double, Double> data = soundResult.getSoundData();
            double maxMagnitude = soundResult.getMaxMagnitude();
            int magScale = (int) Math.ceil(maxMagnitude / HEIGHT);
            int freqScale = (int)Math.ceil(Math.ceil(SoundData.MAX_FREQUENCY/(WIDTH))) + 1;
            for (Map.Entry<Double, Double> entry : data.entrySet()) {
                int freq = entry.getKey().intValue();
                Double mag = entry.getValue();
                g.setFont(mainFreq);
                if(mag == maxMagnitude){
                    g.drawString(String.valueOf(freq) + " HZ ", WIDTH/2, HEIGHT/2);
                }
                g.drawLine(freq/freqScale, HEIGHT - 60, freq/freqScale, HEIGHT - 60 - mag.intValue()/magScale);
            }
            for(int i = 0; i < SoundData.MAX_FREQUENCY; i += 100){
                g.setFont(underLine);
                g.drawString(String.valueOf(i), i/freqScale, HEIGHT - 50);
            }
            soundResult.getSoundData().clear();
        }
    }
}
