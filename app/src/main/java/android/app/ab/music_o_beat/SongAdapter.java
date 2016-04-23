package android.app.ab.music_o_beat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abhishekbhardwaj5638 on 4/19/2016.
 */
public class SongAdapter extends BaseAdapter {
    ArrayList<MySong> song;
    //create object of the layout inflater to inflate the layout
    LayoutInflater songListInflater;

         public SongAdapter (ArrayList<MySong>thisSong,Context c)
         {
             song=thisSong;
             songListInflater=LayoutInflater.from(c);
         }

    @Override
    public int getCount() {
        return song.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout songLayout=(LinearLayout)songListInflater.inflate(R.layout.song_list_value_layout,parent,false);
        TextView tvTitle=(TextView)songLayout.findViewById(R.id.tvtitle);
        TextView tvArtist=(TextView)songLayout.findViewById(R.id.tvartist);

            MySong currentSong= song.get(position);

        tvTitle.setText(currentSong.getTitle());
        tvArtist.setText(currentSong.getArtist());
        songLayout.setTag(position);
        return songLayout;

    }
}
