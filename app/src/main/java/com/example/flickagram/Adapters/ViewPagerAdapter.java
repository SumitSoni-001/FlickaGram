package com.example.flickagram.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.flickagram.Models.Photo;
import com.example.flickagram.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    LayoutInflater mLayoutInflater;

    List<Photo> photoList;
    int realPosition;

    // Viewpager Constructor
    public ViewPagerAdapter(Context context, List<Photo> photoList, int realPosition /*String[] images, String[] titles*/) {
        this.context = context;
        this.photoList = photoList;
        this.realPosition = realPosition;

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // Inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.detail_item, container, false);

        // Initializing views of detail_item.xml
        ImageView image = (ImageView) itemView.findViewById(R.id.detailPhoto);
        TextView title = (TextView) itemView.findViewById(R.id.title2);
        TextView shareLink = (TextView) itemView.findViewById(R.id.shareLink);
        TextView shareImage = (TextView) itemView.findViewById(R.id.shareImage);

        // Setting the image and title
        Picasso.get().load(photoList.get(position).getUrlH()).placeholder(R.drawable.placeholder).into(image);
        title.setText(photoList.get(position).getTitle());

        // OnClicks
        shareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkIntent = new Intent(Intent.ACTION_SEND);
                linkIntent.setType("text/plain");
                linkIntent.putExtra(Intent.EXTRA_TEXT , photoList.get(position).getUrlH());
                context.startActivity(Intent.createChooser(linkIntent , "share Link..."));
            }
        });

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                String BitmapPath = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "title", null);

                Uri ImgUri = Uri.parse(BitmapPath);

                Intent imageIntent = new Intent(Intent.ACTION_SEND);
                imageIntent.setType("image/*");
                imageIntent.putExtra(Intent.EXTRA_STREAM, ImgUri);
                imageIntent.putExtra(Intent.EXTRA_TEXT , "Shared from FlickaGram");
                imageIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(Intent.createChooser(imageIntent, "Share Image..."));
            }
        });

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

}
