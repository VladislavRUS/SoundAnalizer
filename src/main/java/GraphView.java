import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GraphView extends JPanel{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private SoundResult soundResult;
    private long lastTextRedraw;

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
            int freqScale = (int) Math.ceil(4000/WIDTH);
            for (Map.Entry<Double, Double> entry : data.entrySet()) {
                int freq = entry.getKey().intValue();
                Double mag = entry.getValue();
                g.setFont(new Font("Verdana", Font.BOLD, 25));
                if(mag == maxMagnitude && (System.currentTimeMillis() - lastTextRedraw > 50)){
                    g.drawString(String.valueOf(freq) + " HZ ", WIDTH/2, HEIGHT/2);
                    lastTextRedraw = System.currentTimeMillis();
                }
                g.drawLine(freq/freqScale, HEIGHT - 40, freq / freqScale, HEIGHT - 40 - mag.intValue()/magScale);
            }
        }
    }
}