package android.app.ab.music_o_beat;

/**
 * Created by abhishekbhardwaj5638 on 4/18/2016.
 */
public class MySong {

    //this Class is used for song information

    private long id;
    private String title,artist;

    MySong(long song_Id ,String song_Title,String song_Artist)
    {
        id=song_Id;
        title=song_Title;
        artist=song_Artist;
    }
    public long getId (){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getArtist(){
        return artist;
    }
}
