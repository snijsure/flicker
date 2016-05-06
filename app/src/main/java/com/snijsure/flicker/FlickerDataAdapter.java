package com.snijsure.flicker;
/**
 * Created by subodhnijsure on 5/4/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.snijsure.flicker.model.Photo;

import java.util.List;


class FlickerDataAdapter extends RecyclerView.Adapter<FlickerDataAdapter.PhotoHolder> {
    private static final String TAG="FlickerDataAdapter";
    private List<Photo> photoItems;


    FlickerDataAdapter(List<Photo> feed) {
        this.photoItems = feed;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View v = layoutInflater.inflate(R.layout.photo_list, viewGroup, false);
        return new PhotoHolder(v);
    }

    @Override
    public int getItemCount() {
        return photoItems.size();
    }

    public void clearData() {
        // clear the data
        photoItems.clear();
    }

    public void setData(List<Photo> feed) {
        this.photoItems = feed;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final PhotoHolder holder, int pos) {

        Photo item = photoItems.get(pos);
        holder.description.setText(item.getTitle());
        String imageUrl = "https://farm" + Integer.toString(item.getFarm()) +
                ".staticflickr.com/" +
                item.getServer() + "/"
                + item.getId() + "_" + item.getSecret() + ".jpg";
        //Log.d(TAG,"Image url " + imageUrl);
        //https://farm2.staticflickr.com/1702/24815584109_56a4c5467a.jpg
        UrlImageViewHelper.setUrlDrawable(holder.imageView, imageUrl,
                android.R.drawable.gallery_thumb);
    }

    public static class PhotoHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private ImageView imageView;
        PhotoHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
