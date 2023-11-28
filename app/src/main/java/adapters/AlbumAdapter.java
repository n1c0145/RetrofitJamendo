package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.eduardoloza.fragments.R;

import java.util.List;
import com.bumptech.glide.Glide;

import models.Album;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private List<Album> albums;
    private Context context;

    public AlbumAdapter(List<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Album album = albums.get(position);
        holder.setAlbumData(album);
    }

    @Override
    public int getItemCount(){
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgCover;
        private TextView tvTitle, tvArtist,tvGenre;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgCover = itemView.findViewById(R.id.albumCover);
            tvTitle=itemView.findViewById(R.id.albumTitle);
            tvArtist=itemView.findViewById(R.id.albumArtist);
            tvGenre=itemView.findViewById(R.id.albumGenre);
        }

        public void setAlbumData(Album album){
            Glide.with(context).load(album.getImage()).into(imgCover);
            tvTitle.setText(album.getName());
            tvArtist.setText(album.getArtistName());
            tvGenre.setText("");
        }
    }

}
