package com.riprlutuk.bengkeldealer.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.riprlutuk.bengkeldealer.R;
import com.squareup.picasso.Picasso;
import uk.co.senab.photoview.PhotoViewAttacher;

public class detail_gambar_activity extends AppCompatActivity {
    private String detail_gambar;
    private PhotoViewAttacher mViewAttacher = null;
    private ImageView mDetailGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_gambar);
        detail_gambar = getIntent().getExtras().getString("detail_gambar");
        mDetailGambar = (ImageView) findViewById(R.id.detail_gambar);
        mViewAttacher = new PhotoViewAttacher(mDetailGambar);
        mViewAttacher.canZoom();
        Picasso.with(detail_gambar_activity.this).load(detail_gambar).fit().centerInside().placeholder(R.drawable.anim_loading).into(mDetailGambar);
    }
}
