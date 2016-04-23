package android.app.ab.music_o_beat;

import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;

/**
 * Created by abhishekbhardwaj5638 on 4/19/2016.
 */
public class Music_O_Beat_Controller extends MediaController {

    public Music_O_Beat_Controller(Context context) {
        super(context);
    }
    public void hide(){

    }
  public int  getPosn(){

    return player.getCurrentPosition(); }

    public int getDur(){
        return player.getDuration();
    }
    public void isPng(){
        return player.isPlaying();
    }
    public void pausePlayer(){
        return player.pause();
    }
    public void seek(int Posn){
        return player.toSeek();
    }
    public void play(){
        return player.start();
    }
    public void playPrev(){

    }
}
