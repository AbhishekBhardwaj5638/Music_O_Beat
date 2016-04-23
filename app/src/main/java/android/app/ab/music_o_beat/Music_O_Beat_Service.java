package android.app.ab.music_o_beat;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by abhishekbhardwaj5638 on 4/20/2016.
 */
public class Music_O_Beat_Service extends Service implements MediaPlayer.OnErrorListener,MediaPlayer.OnPreparedListener,
MediaPlayer.OnCompletionListener{
    private IBinder bindMusic= new MusicBinder();
    private MediaPlayer beatPlayer;
    private ArrayList<MySong> mySongs;
    private int songPosn;

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize the player
        songPosn=0;
        //create Player
        beatPlayer =new MediaPlayer();
        initMediaPlayer();

    }
    public void initMediaPlayer() {
    //setting beatPlayer's properties
        beatPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        beatPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        beatPlayer.setOnErrorListener(this);
        beatPlayer.setOnCompletionListener(this);
        beatPlayer.setOnPreparedListener(this);

    }
    public void setList(ArrayList<MySong> theSong){
        mySongs=theSong;
    }
    public class MusicBinder extends Binder{
        Music_O_Beat_Service getService(){
            return Music_O_Beat_Service.this;
        }
    }

    public IBinder onBind(Intent intent) {
        return bindMusic;
    }

    public boolean onUnBind(Intent intent){
        beatPlayer.stop();
        beatPlayer.release();
        return false;

    }


    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
