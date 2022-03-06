package com.example.flickagram.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.flickagram.Activities.DetailActivity;
import com.example.flickagram.Models.Photo;
import com.example.flickagram.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FlickerAdapter extends RecyclerView.Adapter<FlickerAdapter.viewHolder> {

    Context context;
    List<Photo> photoList;

    public FlickerAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_photos_rcv, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Photo data = photoList.get(position);

//        if (data.getTitle().length() >= 12) {
//            holder.title.setText("Image");
//        } else {
            holder.title.setText(data.getTitle());
//        }

        Picasso.get().load(data.getUrlH()).placeholder(R.drawable.placeholder).into(holder.photo);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) photoList);
                intent.putExtra("photo", data.getUrlH());
                intent.putExtra("title", data.getTitle());
                intent.putExtra("position", position);
                context.startActivity(intent);

                Animatoo.animateZoom(context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title;
        ConstraintLayout layout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            title = itemView.findViewById(R.id.title);
            layout = itemView.findViewById(R.id.itemLayout);
        }
    }

}
