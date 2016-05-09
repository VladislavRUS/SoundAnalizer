import javax.sound.sampled.LineUnavailableException;

public class Main {
    public static void main(String[] args) throws LineUnavailableException {
        SoundController controller = new SoundController(new SoundView(new GraphView()), new SoundData());
        controller.manage();
    }
}
