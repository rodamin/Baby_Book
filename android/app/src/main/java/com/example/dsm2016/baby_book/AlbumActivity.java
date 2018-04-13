package com.example.dsm2016.baby_book;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class AlbumActivity extends BaseActivity {

    private ImageView preview;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        preview = (ImageView)findViewById(R.id.preview);
        preview.setImageResource(R.drawable.preview_example);

        gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));

    }

    public class ImageAdapter extends BaseAdapter{
        private Context context;

        private Integer[] images= {R.drawable.ic_age, R.drawable.ic_baby, R.drawable.ic_graph, R.drawable.ic_calendar, R.drawable.ic_music};

        public ImageAdapter(Context context){
            this.context = context;
        }

        public int getCount(){
            return images.length;
        }

        public Object getItem(int pos) {
            return null;
        }

        public long getItemId(int pos) {
            return 0;
        }

        public View getView(int pos, View convertView, ViewGroup parent) {
            ImageView imageView;

            if(convertView==null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(400, 500));
//                imageView.getScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(10, 10,10, 10);
            } else {
                imageView = (ImageView)convertView;
            }

            imageView.setImageResource(images[pos]);

            return imageView;
        }
    }
}
