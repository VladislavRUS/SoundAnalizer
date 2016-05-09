import javax.swing.*;
import java.awt.*;

public class SoundView {
    private GraphView graphView;

    public SoundView(GraphView graphView){
        this.graphView = graphView;
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout());
        frame.add(graphView);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(GraphView.WIDTH, GraphView.HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void drawData(SoundResult result){
        graphView.setSoundResult(result);
        graphView.updateUI();
    }
}
