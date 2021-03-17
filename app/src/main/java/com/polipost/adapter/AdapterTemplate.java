package com.polipost.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polipost.R;
import com.polipost.activities.EditingPannelActivity;
import com.polipost.models.TemplatesData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterTemplate extends RecyclerView.Adapter<AdapterTemplate.MyVH> {
    List<TemplatesData> dataList;
    Context context;

    public AdapterTemplate(List<TemplatesData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_templetes, parent, false);
        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        String img = dataList.get(position).getImage();
        //Toast.makeText(context, "image "+img, Toast.LENGTH_SHORT).show();
        if (!img.equals(null)){

            Picasso.get().load(dataList.get(position).getImage()).placeholder(R.drawable.noimg).into(holder.imageView);

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageView);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, EditingPannelActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }
}
