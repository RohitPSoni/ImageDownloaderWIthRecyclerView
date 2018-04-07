package soni.rohit.com.imagedownloader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.ByteArrayOutputStream;

public class DownloadedImage extends AppCompatActivity implements ICallbacks{

    private RecyclerView mImageListView;
    private ImageDownloaderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_image);
        mImageListView = (RecyclerView)findViewById(R.id.imageList);
        mImageListView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ImageDownloaderAdapter(Utils.getImageURLsArrayList(),this);
        mImageListView.setAdapter(mAdapter);
    }

    @Override
    public void displayImage(Bitmap bitmap) {
        Intent intent = new Intent(this,ImageViewActivity.class);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        intent.putExtra(Utils.BITMAP_KEY,byteArray);
        startActivity(intent);
    }
}
