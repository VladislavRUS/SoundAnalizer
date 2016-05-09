public class SoundController {

    private SoundData soundData;
    private SoundView soundView;

    public SoundController(SoundView soundView, SoundData soundData){
        this.soundData = soundData;
        this.soundView = soundView;
    }

    public void manage(){
        Thread thread = new Thread(()->{
            while (true){
                try {
                    SoundResult result = soundData.getData();
                    soundView.drawData(result);
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
