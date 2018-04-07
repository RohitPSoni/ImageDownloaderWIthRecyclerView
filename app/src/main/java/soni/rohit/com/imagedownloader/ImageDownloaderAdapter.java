package soni.rohit.com.imagedownloader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by rohitsoni on 07/04/18.
 */

public class ImageDownloaderAdapter extends RecyclerView.Adapter<ImageDownloaderAdapter.ImageViewHolder> {
    private ICallbacks mCallbacks;

    private ArrayList<String>mUrls;
    public ImageDownloaderAdapter(ArrayList<String>urls, ICallbacks callbacks){
        this.mUrls = urls;
        this.mCallbacks = callbacks;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        final Button download = holder.download;
        download.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ImageDownloader imageDownloader = new ImageDownloader(mUrls.get(position));
                if (download.getText().equals("Download")) {
                    holder.imageView.setImageDrawable(null);
                    holder.progressBar.setVisibility(View.VISIBLE);
                    imageDownloader.execute(holder.imageView);
                    download.setText("Cancel");
                }else {
                    download.setText("Download");
                    imageDownloader.cancel(true);
                }
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable)holder.imageView.getDrawable()).getBitmap();
                mCallbacks.displayImage(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUrls.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        ImageView imageView;
        Button download;

        ImageViewHolder(View view){
            super(view);
            progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
            imageView = (ImageView)view.findViewById(R.id.image_view);
            download = (Button)view.findViewById(R.id.download);
        }
    }
}
