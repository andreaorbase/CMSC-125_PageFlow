import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
    Long currentFrame;
    Clip clip;
    URL filePath;
    String status;
    AudioInputStream audioInputStream;

    public MusicPlayer(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        filePath = getClass().getResource(file);
        audioInputStream = AudioSystem.getAudioInputStream(filePath);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play() {
        //clip.setMicrosecondPosition(0);
        clip.start();
        status = "play";
    }

    public void pause() {
        if (status.equals("paused"))
            return;

        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (status.equals("play"))
            return;

        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        status = "end";
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(filePath);
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}