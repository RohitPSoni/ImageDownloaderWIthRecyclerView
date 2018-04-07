package soni.rohit.com.imagedownloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;

/**
 * Created by rohitsoni on 07/04/18.
 */

public class ImageDownloader extends AsyncTask<ImageView, Void, Bitmap> {

    ImageView imageView = null;
    String mURL;
    public ImageDownloader(String url){
        this.mURL = url;
    }

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        if (isCancelled()){
            return null;
        }
        this.imageView = imageViews[0];
        return download_Image(mURL);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }

    private Bitmap download_Image(String url) {

        Bitmap bmp =null;
        try{
            URL imageURL = new URL(url);
            bmp = BitmapFactory.decodeStream(imageURL.openStream());
        }catch(Exception e){}
        return bmp;
    }
}
