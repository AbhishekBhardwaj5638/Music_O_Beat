package android.app.ab.music_o_beat;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import android.app.ab.music_o_beat.Music_O_Beat_Service;

public class Music_O_BeatActivity extends AppCompatActivity implements android.widget.MediaController.MediaPlayerControl {
    Music_O_Beat_Controller mController;
    ArrayList<MySong> songList;
    ListView songView;

    Music_O_Beat_Service musicService;
    Intent playIntent;
    private boolean musicBound=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music__o__beat);

        songView=(ListView)findViewById(R.id.song_list);
        songList=new ArrayList<MySong>();

        getSongList();//getting song list


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private ServiceConnection musicConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Music_O_Beat_Service.MusicBinder binder=(Music_O_Beat_Service.MusicBinder)service;
            //get Service
            musicService=binder.getService();
            //pass List
            musicService.setList(songList);
            musicBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound=false;
        }
    };
    public void onStart(){
        super.onStart();
        if (playIntent==null){
            playIntent=new Intent(this, Music_O_Beat_Service.class);
            bindService(playIntent,musicConnection, Context.BIND_AUTO_CREATE);
        }
    }
      /*  public void setmController(){
            mController=new Music_O_Beat_Controller(this);
            mController.setPrevNextListeners(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    playNext();
                }
            },new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    playPrev();
                }
            });
            mController.setMediaPlayer(this);
            mController.setAnchorView(R.id.song_list);
            mController.setEnabled(true);
        }*/
        public void getSongList(){
            ContentResolver myMusicResolver=getContentResolver();
            Uri musicUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Cursor myMusicCursor=myMusicResolver.query(musicUri,null,null,null,null);

            if(myMusicCursor!=null && myMusicCursor.moveToFirst()) {
                //getting columns
                int idColumn = myMusicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
                int titleColumn = myMusicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                int artistColumn = myMusicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

                do {
                    long id = myMusicCursor.getLong(idColumn);
                    String title=myMusicCursor.getString(titleColumn);
                    String artist=myMusicCursor.getString(artistColumn);
                    songList.add(new MySong(id,title,artist));

                } while (myMusicCursor.moveToNext());
            }
            SongAdapter songAdapter= new SongAdapter(songList,this);
            songView.setAdapter(songAdapter);
            }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music__o__beat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(int pos) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
