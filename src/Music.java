public class Music {
    static MusicPlayer bgMusic;
    static SFX soundFX;

    public static void bgMusic() {
        try {
            bgMusic = new MusicPlayer("/gui/sounds/bg_music.wav");
            bgMusic.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sfx() {
        try {
            soundFX = new SFX("/gui/sounds/click.wav");
            soundFX.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}